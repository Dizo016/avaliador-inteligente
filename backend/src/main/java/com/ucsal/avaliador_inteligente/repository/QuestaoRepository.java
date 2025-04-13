package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}
