package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.dto.RespostaAlunoRequestDTO;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.model.RespostaAluno;
import com.ucsal.avaliador_inteligente.model.Usuario;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import com.ucsal.avaliador_inteligente.repository.RespostaAlunoRepository;
import com.ucsal.avaliador_inteligente.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespostaAlunoService {

    private final RespostaAlunoRepository respostaAlunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuestaoRepository questaoRepository;
    private final ProvaRepository provaRepository;

    public RespostaAluno registrarResposta(RespostaAlunoRequestDTO dto) {
        Usuario aluno = usuarioRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Questao questao = questaoRepository.findById(dto.getQuestaoId())
                .orElseThrow(() -> new RuntimeException ("Questão não encontrada"));
        Prova prova = provaRepository.findById(dto.getProvaId())
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        RespostaAluno resposta = new RespostaAluno();
        resposta.setAluno(aluno);
        resposta.setQuestao(questao);
        resposta.setProva(prova);
        resposta.setRespostaMarcada(dto.getRespostaMarcada());

        return respostaAlunoRepository.save(resposta);
    }
}
