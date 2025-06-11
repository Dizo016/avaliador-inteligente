package com.ucsal.avaliador_inteligente.service;

import com.ucsal.avaliador_inteligente.model.Gabarito;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.util.GabaritoPdfGenerator;
import com.ucsal.avaliador_inteligente.repository.GabaritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GabaritoService {

    private final GabaritoRepository gabaritoRepository;

    public Gabarito gerarGabaritoParaProva(Prova prova) {
        byte[] pdf = GabaritoPdfGenerator.gerarPdf(prova);

        Gabarito gabarito = new Gabarito();
        gabarito.setDataCriacao(LocalDate.now());
        gabarito.setProva(prova);
        gabarito.setArquivoPdf(pdf);

        return gabaritoRepository.save(gabarito);
    }

    public Optional<Gabarito> buscarPorId(Long id) {
        return gabaritoRepository.findById(id);
    }
}
