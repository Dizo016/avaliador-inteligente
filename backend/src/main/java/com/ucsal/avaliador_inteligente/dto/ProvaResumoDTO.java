package com.ucsal.avaliador_inteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProvaResumoDTO {
    private Long id;
    private String titulo;
    private LocalDate dataCriacao;
    private String nomeCriador;
}
