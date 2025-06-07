package com.ucsal.avaliador_inteligente.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvaRequestDTO {
    private String titulo;
    private List<Long> questoesIds;
}
