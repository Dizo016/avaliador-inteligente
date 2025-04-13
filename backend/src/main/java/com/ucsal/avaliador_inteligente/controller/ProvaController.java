package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.ProvaRequestDTO;
import com.ucsal.avaliador_inteligente.dto.QuestaoComAlternativasDTO;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.service.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Prova> cadastrarProva(@RequestBody ProvaRequestDTO dto) {
        Prova prova = provaService.cadastrarProva(dto);
        return ResponseEntity.ok(prova);
    }
}
