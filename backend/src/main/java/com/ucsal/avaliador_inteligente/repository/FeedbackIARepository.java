package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.FeedbackIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackIARepository extends JpaRepository<FeedbackIA, Long> {
}
