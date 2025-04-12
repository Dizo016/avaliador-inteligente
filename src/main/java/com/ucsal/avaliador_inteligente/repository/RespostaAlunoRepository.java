package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.RespostaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaAlunoRepository extends JpaRepository<RespostaAluno, Long> {
}
