package com.ucsal.avaliador_inteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestaoComAlternativasDTO {

    private Long id;
    private String enunciado;
    private List<AlternativaDTO> alternativas;

}
