package com.ucsal.avaliador_inteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questoes_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    private Integer respostaCorreta;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas = new ArrayList<>();

    public boolean isCorreta(int respostaAluno) {
        return this.respostaCorreta != null && this.respostaCorreta == respostaAluno;
    }

    public void adicionarAlternativa(Alternativa alternativa) {
        alternativa.setQuestao(this);
        this.alternativas.add(alternativa);
    }
}
