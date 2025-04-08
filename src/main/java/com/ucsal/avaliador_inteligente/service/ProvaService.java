package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.AlternativaDTO;
import com.ucsal.avaliador_inteligente.dto.QuestaoComAlternativasDTO;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository provaRepository;

    public List<QuestaoComAlternativasDTO> listarQuestoesDaProva(Long provaId) {
        Prova prova = provaRepository.findById(provaId)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        return prova.getQuestoes().stream().map(q -> {
            List<AlternativaDTO> alternativas = q.getAlternativas().stream()
                    .map(a -> new AlternativaDTO(a.getId(), a.getLetra(), a.getTexto()))
                    .toList();

            return new QuestaoComAlternativasDTO(q.getId(), q.getEnunciado(), alternativas);
        }).toList();
    }
}
