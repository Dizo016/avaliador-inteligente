package com.ucsal.avaliador_inteligente.repository;

import com.ucsal.avaliador_inteligente.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {
}
