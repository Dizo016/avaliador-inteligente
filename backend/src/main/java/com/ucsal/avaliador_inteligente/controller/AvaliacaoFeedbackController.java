package com.ucsal.avaliador_inteligente.controller;

import com.ucsal.avaliador_inteligente.dto.AvaliacaoFeedbackRequestDTO;
import com.ucsal.avaliador_inteligente.model.AvalicaoFeedback;
import com.ucsal.avaliador_inteligente.service.AvaliacaoFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes-feedback")
@RequiredArgsConstructor
public class AvaliacaoFeedbackController {

    private final AvaliacaoFeedbackService avaliacaoFeedbackService;

    @PostMapping("/{feedbackId}")
    public ResponseEntity<AvalicaoFeedback>avaliarFeedback(
            @PathVariable Long feedbackId,
            @RequestBody AvaliacaoFeedbackRequestDTO dto) {
        AvalicaoFeedback avalicaoFeedback = avaliacaoFeedbackService.avaliarFeedback(feedbackId, dto);
        return ResponseEntity.ok(avalicaoFeedback);
    }
}
