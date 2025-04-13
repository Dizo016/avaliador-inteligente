package com.ucsal.avaliador_inteligente.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvaliacaoFeedbackRequestDTO {

    private Integer nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
}
