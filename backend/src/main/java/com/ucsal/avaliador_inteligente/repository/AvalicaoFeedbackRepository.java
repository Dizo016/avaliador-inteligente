package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.AvalicaoFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvalicaoFeedbackRepository extends JpaRepository<AvalicaoFeedback, Long> {
}
