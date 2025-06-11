package com.ucsal.avaliador_inteligente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "gabaritos_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gabarito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;

    @Lob
    @Column(name = "arquivo_pdf")
    private byte[] arquivoPdf;

    @OneToOne
    @JoinColumn(name = "prova_id", unique = true)
    @JsonIgnore
    private Prova prova;
}
