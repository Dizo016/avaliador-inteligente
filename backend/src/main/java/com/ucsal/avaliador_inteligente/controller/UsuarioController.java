package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.UsuarioRequestDTO;
import com.ucsal.avaliador_inteligente.dto.UsuarioResponseDTO;
import com.ucsal.avaliador_inteligente.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.cadastrar(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
