package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.AlternativaRequestDTO;
import com.ucsal.avaliador_inteligente.dto.QuestaoRequestDTO;
import com.ucsal.avaliador_inteligente.dto.RespostaCorretaDTO;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.service.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{questaoId}/alternativas")
    public ResponseEntity<Void> adicionarAlternativa(
            @PathVariable Long questaoId,
            @RequestBody AlternativaRequestDTO dto
    ) {
        questaoService.adicionarAlternativa(questaoId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{questaoId}/resposta-correta")
    public ResponseEntity<Void> definirRespostaCorreta(
            @PathVariable Long questaoId,
            @RequestBody RespostaCorretaDTO dto
    ) {
        questaoService.definirRespostaCorreta(questaoId, dto.getIndice());
        return  ResponseEntity.ok().build();
    }
}
