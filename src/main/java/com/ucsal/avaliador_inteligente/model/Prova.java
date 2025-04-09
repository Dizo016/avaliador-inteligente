package com.ucsal.avaliador_inteligente.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provas_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Questao> questoes = new ArrayList<>();

    public void adicionarQuestao(Questao questao) {
        questao.setProva(this);
        this.questoes.add(questao);
    }

    public void removerQuestao(Questao questao) {
        questao.setProva(null);
        this.questoes.remove(questao);
    }
}
