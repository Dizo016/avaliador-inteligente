package com.ucsal.avaliador_inteligente.factory;

import com.ucsal.avaliador_inteligente.model.FeedbackIA;
import com.ucsal.avaliador_inteligente.model.RespostaAluno;

public interface FeedbackFactory {
    FeedbackIA gerarFeedback(RespostaAluno respostaAluno);
}
