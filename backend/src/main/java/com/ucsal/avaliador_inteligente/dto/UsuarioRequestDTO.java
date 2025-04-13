package com.ucsal.avaliador_inteligente.dto;

import com.ucsal.avaliador_inteligente.model.enums.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

}
