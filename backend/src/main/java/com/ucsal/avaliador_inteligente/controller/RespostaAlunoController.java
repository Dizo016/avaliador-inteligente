package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.RespostaAlunoRequestDTO;
import com.ucsal.avaliador_inteligente.model.RespostaAluno;
import com.ucsal.avaliador_inteligente.service.RespostaAlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/respostas")
@RequiredArgsConstructor
public class RespostaAlunoController {

    private final RespostaAlunoService respostaAlunoService;

    @PostMapping
    public ResponseEntity<RespostaAluno> responder(@RequestBody RespostaAlunoRequestDTO dto) {
        System.out.println("RECEBIDO: " + dto);
        RespostaAluno resposta = respostaAlunoService.registrarResposta(dto);
        return ResponseEntity.ok(resposta);
    }
}
