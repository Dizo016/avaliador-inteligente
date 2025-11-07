package com.ucsal.avaliador_inteligente.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucsal.avaliador_inteligente.model.Alternativa;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IAQuestaoService {

    private final QuestaoRepository questaoRepository;

    @Value("${groq.api.key}")
    private String apiKey;

    private final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    public List<Questao> buscarQuestoesPorTema(String tema) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String prompt = "Gere 5 questões objetivas de múltipla escolha no estilo do ENEM sobre o tema: \"" + tema + "\". " +
                "Formate a resposta como um array JSON (Não use texto solto nem markdown) no seguinte modelo:\n" +
                "[\n" +
                "  {\n" +
                "    \"enunciado\": \"Texto da pergunta...\",\n" +
                "    \"tema\": \"Tema central da questão\",\n" +
                "    \"origem\": \"ENEM 2019\",\n" +
                "    \"alternativas\": [\n" +
                "      {\"descricao\": \"Texto da alternativa A\", \"correta\": false},\n" +
                "      {\"descricao\": \"Texto da alternativa B\", \"correta\": true},\n" +
                "      {\"descricao\": \"Texto da alternativa C\", \"correta\": false},\n" +
                "      {\"descricao\": \"Texto da alternativa D\", \"correta\": false},\n" +
                "      {\"descricao\": \"Texto da alternativa E\", \"correta\": false}\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        Map<String, Object> body = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "temperature", 0.7
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GROQ_URL, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro na chamada da IA: " + response.getStatusCode());
        }

        try {
            String content = new ObjectMapper().readTree(response.getBody())
                    .get("choices").get(0).get("message").get("content").asText();

            int indexInicioJson = content.indexOf("[");
            if (indexInicioJson == -1) {
                throw new RuntimeException("Resposta da IA não contém JSON válido: " + content);
            }

            String jsonLimpo = content.substring(indexInicioJson).trim();
            JsonNode questoesJson = mapper.readTree(jsonLimpo);
            List<Questao> questoesSalvas = new ArrayList<>();

            for (JsonNode q : questoesJson) {
                Questao questao = new Questao();
                questao.setEnunciado(q.get("enunciado").asText());
                questao.setTema(q.get("tema").asText());
                questao.setOrigem(q.get("origem").asText());

                List<Alternativa> alternativas = new ArrayList<>();
                for (JsonNode alt : q.get("alternativas")) {
                    Alternativa a = new Alternativa();
                    a.setDescricao(alt.get("descricao").asText());
                    a.setCorreta(alt.get("correta").asBoolean());
                    a.setQuestao(questao);
                    alternativas.add(a);
                }

                questao.setAlternativas(alternativas);
                questoesSalvas.add(questaoRepository.save(questao));
            }

            return questoesSalvas;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a resposta da IA", e);
        }
    }
}
