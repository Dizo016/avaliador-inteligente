package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.*;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Prova cadastrarProva(ProvaRequestDTO dto) {
        Prova prova = new Prova();
        prova.setTitulo(dto.getTitulo());
        return provaRepository.save(prova);
    }

    public List<ProvaResumoDTO> listarTodasAsProvas() {
        return provaRepository.findAll().stream()
                .map(prova -> new ProvaResumoDTO(prova.getId(), prova.getTitulo()))
                .toList();
    }

    public ProvaComQuestoesDTO buscarProvaComQuestoes (Long id) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        ProvaComQuestoesDTO dto = new ProvaComQuestoesDTO();
        dto.setId(prova.getId());
        dto.setTitulo(prova.getTitulo());

        List<QuestaoComAlternativasDTO> questoesDTO = prova.getQuestoes().stream().map(questao -> {
            QuestaoComAlternativasDTO qdto = new QuestaoComAlternativasDTO();
            qdto.setId(questao.getId());
            qdto.setEnunciado(questao.getEnunciado());
            qdto.setAlternativas(
                    questao.getAlternativas()
                            .stream()
                            .map(alt -> new AlternativaDTO(alt.getId(), alt.getLetra(), alt.getTexto()))
                            .collect(Collectors.toList()));
            return qdto;
        }).toList();

        dto.setQuestoes(questoesDTO);
        return dto;
    }
}
