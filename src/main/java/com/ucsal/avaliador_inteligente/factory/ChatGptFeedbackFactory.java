package com.ucsal.avaliador_inteligente.factory;

import com.ucsal.avaliador_inteligente.model.FeedbackIA;
import com.ucsal.avaliador_inteligente.model.RespostaAluno;
import com.ucsal.avaliador_inteligente.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ChatGptFeedbackFactory implements FeedbackFactory {

    private final ChatGptService chatGptService;

    @Override
    public FeedbackIA gerarFeedback(RespostaAluno respostaAluno) {
        String prompt = construirPrompt(respostaAluno);
        String respostaIA = chatGptService.gerarFeedback(prompt);

        if (respostaIA.contains("ChatGPT não pode ser conectado")) {
            FeedbackIA feedback = new FeedbackIA();
            feedback.setTexto(respostaIA);
            feedback.setDataGeracao(LocalDateTime.now());
            feedback.associarResposta(respostaAluno);
            return feedback;
        }

        FeedbackIA feedback = new FeedbackIA();
        feedback.setTexto(respostaIA);
        feedback.setDataGeracao(LocalDateTime.now());
        feedback.associarResposta(respostaAluno);

        return feedback;
    }

    private String construirPrompt(RespostaAluno resposta) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Você é um corretor de provas. Abaixo está a questão e a resposta marcada por um aluno:\n");
        prompt.append("Questão: ").append(resposta.getQuestao().getEnunciado()).append("\n");

        resposta.getQuestao().getAlternativas().forEach(a ->
                prompt.append(a.getLetra()).append(") ").append(a.getTexto()).append("\n")
        );

        prompt.append("Resposta do aluno: Letra ")
                .append(resposta.getRespostaMarcada())
                .append("\n");

        prompt.append("Explique se a resposta está correta ou não e dê um feedback educativo de até 3 linhas.");

        return prompt.toString();
    }
}
