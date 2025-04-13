package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.RespostaAlunoRequestDTO;
import com.ucsal.avaliador_inteligente.factory.ChatGptFeedbackFactory;
import com.ucsal.avaliador_inteligente.model.*;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import com.ucsal.avaliador_inteligente.repository.RespostaAlunoRepository;
import com.ucsal.avaliador_inteligente.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RespostaAlunoService {

    private final RespostaAlunoRepository respostaAlunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuestaoRepository questaoRepository;
    private final ProvaRepository provaRepository;
    private final ChatGptFeedbackFactory chatGptFeedbackFactory;

    public RespostaAluno registrarResposta(RespostaAlunoRequestDTO dto) {
        Usuario aluno = usuarioRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Questao questao = questaoRepository.findById(dto.getQuestaoId())
                .orElseThrow(() -> new RuntimeException ("Questão não encontrada"));
        Prova prova = provaRepository.findById(dto.getProvaId())
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        RespostaAluno resposta = new RespostaAluno();
        resposta.setAluno(aluno);
        resposta.setQuestao(questao);
        resposta.setRespostaMarcada(dto.getRespostaMarcada());
        resposta.setDataResposta(LocalDateTime.now());
        resposta.setProva(prova);

        FeedbackIA feedbackIA = chatGptFeedbackFactory.gerarFeedback(resposta);
        resposta.setFeedbackIA(feedbackIA);

        return respostaAlunoRepository.save(resposta);
    }
}
