package com.ucsal.avaliador_inteligente.model;

import com.ucsal.avaliador_inteligente.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public boolean isProfessor() {
        return this.getTipoUsuario() == TipoUsuario.PROFESSOR;
    }

    public boolean isAluno() {
        return this.getTipoUsuario() == TipoUsuario.ALUNO;
    }
}
