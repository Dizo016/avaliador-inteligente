package com.ucsal.avaliador_inteligente.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    @Value("${openai.api.key}")
    private String apikey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String gerarFeedback(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apikey);

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[] {
                        Map.of("role", "system", "content", "Você é um corretor de provas escolares."),
                        Map.of("role", "user", "content", prompt)
                }
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        int attempt = 0;
        boolean success = false;
        String responseMessage = "";

        while (attempt < 5 && !success) {
            try {
                ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    Map<String, Object> body = response.getBody();
                    var choices = (java.util.List<Map<String, Object>>) body.get("choices");
                    var message = (Map<String, Object>) choices.get(0).get("message");

                    responseMessage = message.get("content").toString();
                    success = true; // Feedback gerado com sucesso
                } else {
                    throw new RuntimeException("Erro ao se comunicar com o ChatGPT: " + response.getStatusCode());
                }
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                    // Esperar antes de tentar novamente
                    System.out.println("Limite de requisições excedido. Tentando novamente...");
                    try {
                        Thread.sleep(10000);  // Aguardar 10 segundos antes de tentar novamente
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw new RuntimeException("Erro ao se comunicar com o ChatGPT", e);
                }
            }
            attempt++;
        }

        if (!success) {
            responseMessage = "ChatGPT não pode ser conectado por falta de créditos ou excesso de requisições.";
        }

        return responseMessage;
    }
}
