package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.AvaliacaoFeedbackRequestDTO;
import com.ucsal.avaliador_inteligente.model.AvalicaoFeedback;
import com.ucsal.avaliador_inteligente.model.FeedbackIA;
import com.ucsal.avaliador_inteligente.repository.AvalicaoFeedbackRepository;
import com.ucsal.avaliador_inteligente.repository.FeedbackIARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AvaliacaoFeedbackService {

    private final AvalicaoFeedbackRepository avalicaoFeedbackRepository;
    private final FeedbackIARepository feedbackIARepository;

    public AvalicaoFeedback avaliarFeedback(Long feedBackId, AvaliacaoFeedbackRequestDTO dto) {
        FeedbackIA feedbackIA = feedbackIARepository.findById(feedBackId)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado"));

        AvalicaoFeedback avalicaoFeedback = new AvalicaoFeedback();
        avalicaoFeedback.setNota(dto.getNota());
        avalicaoFeedback.setComentario(dto.getComentario());
        avalicaoFeedback.setDataAvaliacao(LocalDateTime.now());
        avalicaoFeedback.setFeedbackIA(feedbackIA);

        return avalicaoFeedbackRepository.save(avalicaoFeedback);
    }
}
