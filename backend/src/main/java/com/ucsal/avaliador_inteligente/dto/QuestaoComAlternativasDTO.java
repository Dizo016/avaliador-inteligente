package com.ucsal.avaliador_inteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoComAlternativasDTO {

    private Long id;
    private String enunciado;
    private List<AlternativaDTO> alternativas;

}
