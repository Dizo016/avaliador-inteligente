package com.ucsal.avaliador_inteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestaoDetalhadaDTO {

    private Long id;
    private String enunciado;
    private Integer respostaCorreta;
    private List<AlternativaDTO> alternativas;
}
