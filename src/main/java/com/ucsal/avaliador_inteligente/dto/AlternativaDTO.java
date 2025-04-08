package com.ucsal.avaliador_inteligente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlternativaDTO {

    private Long id;
    private String letra;
    private String texto;
}
