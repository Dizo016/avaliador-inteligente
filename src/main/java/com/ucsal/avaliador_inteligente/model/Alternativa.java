package com.ucsal.avaliador_inteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alternativas_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String letra;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;
}
