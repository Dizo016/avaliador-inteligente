package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.ProvaRequestDTO;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.service.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provas")
@RequiredArgsConstructor
public class ProvaController {

    private final ProvaService provaService;

    @PostMapping
    public ResponseEntity<Void> criarProva(@RequestBody ProvaRequestDTO dto) {
        provaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Prova>> listarProvas() {
        return ResponseEntity.ok(provaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prova> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(provaService.buscarPorId(id));
    }
}
