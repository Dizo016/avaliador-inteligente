package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.QuestaoComAlternativasDTO;
import com.ucsal.avaliador_inteligente.service.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/provas")
@RequiredArgsConstructor
public class ProvaController {

    private final ProvaService provaService;

    @GetMapping("/{provaId}/questoes")
    public ResponseEntity<List<QuestaoComAlternativasDTO>> listarQuestoes(@PathVariable Long provaId) {
        List<QuestaoComAlternativasDTO> lista = provaService.listarQuestoesDaProva(provaId);
        return ResponseEntity.ok(lista);
    }
}
