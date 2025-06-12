package com.ucsal.avaliador_inteligente.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ucsal.avaliador_inteligente.dto.ProvaRequestDTO;
import com.ucsal.avaliador_inteligente.dto.ProvaResumoDTO;
import com.ucsal.avaliador_inteligente.model.Alternativa;
import com.ucsal.avaliador_inteligente.model.Gabarito;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.model.Usuario;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
import com.ucsal.avaliador_inteligente.repository.UsuarioRepository;
import com.ucsal.avaliador_inteligente.util.ProvaPdfGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvaService {

    private final UsuarioRepository usuarioRepository;
    private final ProvaRepository provaRepository;
    private final QuestaoRepository questaoRepository;
    private final IAQuestaoService iaQuestaoService;
    private final GabaritoService gabaritoService;

    public void criar(ProvaRequestDTO dto) {
        Prova prova = new Prova();
        prova.setTitulo(dto.getTitulo());
        prova.setDataCriacao(LocalDate.now());

        List<Questao> questoesSelecionadas = questaoRepository.findAllById(dto.getQuestoesIds());
        for (Questao q : questoesSelecionadas) {
            q.setProva(prova);
        }

        prova.setQuestoes(questoesSelecionadas);
        provaRepository.save(prova);
    }

    public List<Prova> listarTodas() {
        return provaRepository.findAll();
    }

    public List<ProvaResumoDTO> listarTodasResumidas() {
        return provaRepository.findAll().stream()
                .map(p -> new ProvaResumoDTO(
                        p.getId(),
                        p.getTitulo(),
                        p.getDataCriacao(),
                        p.getCriador() != null ? p.getCriador().getNome() : "Desconhecido"
                ))
                .toList();
    }

    public Prova buscarPorId(Long id) {
        return provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada com ID: " + id));
    }

    public byte[] gerarPdfProva(Long provaId) {
        Prova prova = buscarPorId(provaId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        doc.add(new Paragraph("Prova: " + prova.getTitulo()).setBold().setFontSize(16));

        int num = 1;
        for (Questao questao : prova.getQuestoes()) {
            doc.add(new Paragraph(num + ". " + questao.getEnunciado()).setBold());

            char letra = 'A';
            for (Alternativa alt : questao.getAlternativas()) {
                String textoAlt = letra + ") " + alt.getDescricao();
                doc.add(new Paragraph(textoAlt));
                letra++;
            }

            doc.add(new Paragraph(""));
            num++;
        }

        doc.close();
        return baos.toByteArray();
    }

    @Transactional
    public Prova gerarProvaComGabarito(String titulo, String tema, Long criadorId) {
        Usuario criador = usuarioRepository.findById(criadorId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Questao> questoes;
        try {
            questoes = iaQuestaoService.buscarQuestoesPorTema(tema);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar questões com a IA", e);
        }

        if (questoes == null || questoes.isEmpty()) {
            throw new RuntimeException("Nenhuma questão retornada pela IA");
        }

        Prova prova = new Prova();
        prova.setTitulo(titulo);
        prova.setDataCriacao(LocalDate.now());
        prova.setCriador(criador);
        prova.setQuestoes(new ArrayList<>());

        Prova provaSalva = provaRepository.save(prova);

        for (Questao questao : questoes) {
            if (questao != null) {
                questao.setProva(provaSalva);
                provaSalva.adicionarQuestao(questao);
            }
        }

        byte[] pdfProva = ProvaPdfGenerator.gerarPdf(provaSalva);
        provaSalva.setArquivoPdf(pdfProva);

        Prova provaComQuestoes = provaRepository.save(provaSalva);

        Gabarito gabarito = gabaritoService.gerarGabaritoParaProva(provaComQuestoes);
        provaComQuestoes.setGabarito(gabarito);

        return provaRepository.save(provaComQuestoes);
    }



}
