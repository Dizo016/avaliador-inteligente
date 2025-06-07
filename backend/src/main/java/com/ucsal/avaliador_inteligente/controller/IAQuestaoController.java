package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.service.IAQuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes/ia")
@RequiredArgsConstructor
public class IAQuestaoController {

    private final IAQuestaoService iaQuestaoService;

    @PostMapping("/buscar")
    public ResponseEntity<List<Questao>> gerarQuestoesPorTema(@RequestParam String tema) {
        try {
            List<Questao> questoes = iaQuestaoService.buscarQuestoesPorTema(tema);
            return ResponseEntity.ok(questoes);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Mostra o erro no console
            return ResponseEntity.status(500).body(null);
        }
    }

}
