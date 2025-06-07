package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;

    public List<Questao> buscarPorTema(String tema) {
        return questaoRepository.findByTemaContainingIgnoreCase(tema);
    }

    public List<Questao> listarTodas() {
        return questaoRepository.findAll();
    }

    public Questao buscarPorId(Long id) {
        return questaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada com ID: " + id));
    }
}
