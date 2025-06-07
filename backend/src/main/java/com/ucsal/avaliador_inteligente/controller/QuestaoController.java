package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.service.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService questaoService;

    @GetMapping("/enem")
    public ResponseEntity<List<Questao>> buscarPorTema(@RequestParam String tema) {
        List<Questao> questoes = questaoService.buscarPorTema(tema);
        return ResponseEntity.ok(questoes);
    }

    @GetMapping
    public ResponseEntity<List<Questao>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(questaoService.buscarPorId(id));
    }
}
