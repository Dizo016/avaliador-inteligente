package com.ucsal.avaliador_inteligente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes_feedback_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvalicaoFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    @Column(length = 500)
    private String comentario;
    private LocalDateTime dataAvaliacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "feedback_ia_id")
    @JsonIgnore
    private FeedbackIA feedbackIA;
}
