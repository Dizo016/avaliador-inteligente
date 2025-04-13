package com.ucsal.avaliador_inteligente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "feedbacks_ia_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String texto;
    private LocalDateTime dataGeracao;

    @OneToOne
    @JoinColumn(name = "resposta_aluno_id")
    @JsonIgnore
    private RespostaAluno respostaAluno;

    @OneToMany(mappedBy = "feedbackIA", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvalicaoFeedback> avaliacoes;

    public boolean foiAvaliado() {
        return avaliacoes != null && !avaliacoes.isEmpty();
    }

    public void associarResposta(RespostaAluno respostaAluno) {
        this.respostaAluno = respostaAluno;
        respostaAluno.setFeedbackIA(this);
    }

}
