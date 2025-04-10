package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.AlternativaRequestDTO;
import com.ucsal.avaliador_inteligente.dto.QuestaoRequestDTO;
import com.ucsal.avaliador_inteligente.model.Alternativa;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void adicionarAlternativa(Long questaoId, AlternativaRequestDTO dto) {
        Questao questao = questaoRepository.findById(questaoId)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));

        Alternativa alternativa = new Alternativa();
        alternativa.setLetra(dto.getLetra());
        alternativa.setTexto(dto.getTexto());
        alternativa.setQuestao(questao);

        questao.getAlternativas().add(alternativa);
        questaoRepository.save(questao);
    }

    public void definirRespostaCorreta(Long questaoId, Integer indice) {
        Questao questao = questaoRepository.findById(questaoId)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));

        questao.setRespostaCorreta(indice);
        questaoRepository.save(questao);
    }
}
