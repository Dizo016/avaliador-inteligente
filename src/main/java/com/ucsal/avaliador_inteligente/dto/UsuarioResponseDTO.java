package com.ucsal.avaliador_inteligente.dto;

import com.ucsal.avaliador_inteligente.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
}
