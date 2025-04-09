package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}
