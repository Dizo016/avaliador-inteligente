package com.ucsal.avaliador_inteligente.dto;

import lombok.Data;

@Data
public class RespostaAlunoRequestDTO {

    private Long alunoId;
    private Long questaoId;
    private Long provaId;
    private Integer respostaMarcada;
}
