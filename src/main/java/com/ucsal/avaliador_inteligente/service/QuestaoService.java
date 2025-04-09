package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.QuestaoRequestDTO;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final ProvaRepository provaRepository;

    public Questao cadastrarQuestao(QuestaoRequestDTO dto) {
        Prova prova = provaRepository.findById(dto.getProvaId())
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        Questao questao = new Questao();
        questao.setEnunciado(dto.getEnunciado());
        questao.setProva(prova);

        return questaoRepository.save(questao);
    }
}
