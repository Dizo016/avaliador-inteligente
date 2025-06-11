package com.ucsal.avaliador_inteligente.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String tema;         // Ex: "Geografia"
    private String origem;       // Ex: "ENEM 2018"

    @ManyToOne
    @JoinColumn(name = "prova_id")
    @JsonBackReference
    private Prova prova;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas = new ArrayList<>();

    public void adicionarAlternativa(Alternativa alternativa) {
        alternativa.setQuestao(this);
        this.alternativas.add(alternativa);
    }
}
