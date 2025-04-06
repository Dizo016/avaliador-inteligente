package com.ucsal.avaliador_inteligente.model;

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

    @ManyToMany
    @JoinTable(
            name = "prova_questoes_tb",
            joinColumns = @JoinColumn(name = "prova_id"),
            inverseJoinColumns = @JoinColumn(name = "questao_id")
    )
    private List<Questao> questoes = new ArrayList<>();

    public void adicionarQuestao(Questao questao) {
        this.questoes.add(questao);
    }

    public void removerQuestao(Questao questao) {
        this.questoes.remove(questao);
    }
}
