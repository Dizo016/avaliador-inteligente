package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.QuestaoRequestDTO;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.service.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService questaoService;

    @PostMapping
    public ResponseEntity<Questao> cadastrar(@RequestBody QuestaoRequestDTO dto) {

        Questao questao = questaoService.cadastrarQuestao(dto);
        return ResponseEntity.ok(questao);
    }
}
