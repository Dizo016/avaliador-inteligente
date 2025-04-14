package com.ucsal.avaliador_inteligente.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvaComQuestoesDTO {
    private Long id;
    private String titulo;
    private List<QuestaoComAlternativasDTO> questoes;
}
