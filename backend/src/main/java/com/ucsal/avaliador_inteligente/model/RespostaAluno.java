package com.ucsal.avaliador_inteligente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas_aluno_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer respostaMarcada;
    private LocalDateTime dataResposta;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Usuario aluno;

    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;

    @ManyToOne
    @JoinColumn(name = "prova_id")
    private Prova prova;

    @OneToOne(mappedBy = "respostaAluno", cascade = CascadeType.ALL)
    private FeedbackIA feedbackIA;

    public boolean isCorreta() {
        return questao != null && questao.isCorreta(this.respostaMarcada);
    }
}
