package com.ucsal.avaliador_inteligente.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ucsal.avaliador_inteligente.dto.ProvaRequestDTO;
import com.ucsal.avaliador_inteligente.model.Alternativa;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;
import com.ucsal.avaliador_inteligente.model.Usuario;
import com.ucsal.avaliador_inteligente.repository.ProvaRepository;
import com.ucsal.avaliador_inteligente.repository.QuestaoRepository;
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
            q.setProva(prova); // mantém o vínculo bidirecional
        }

        prova.setQuestoes(questoesSelecionadas);
        provaRepository.save(prova);
    }

    public List<Prova> listarTodas() {
        return provaRepository.findAll();
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

        doc.add(new Paragraph("📄 Prova: " + prova.getTitulo()).setBold().setFontSize(16));

        int num = 1;
        for (Questao questao : prova.getQuestoes()) {
            doc.add(new Paragraph(num + ". " + questao.getEnunciado()).setBold());

            char letra = 'A';
            for (Alternativa alt : questao.getAlternativas()) {
                String textoAlt = letra + ") " + alt.getDescricao();
                doc.add(new Paragraph(textoAlt));
                letra++;
            }

            doc.add(new Paragraph("\n"));
            num++;
        }

        doc.close();
        return baos.toByteArray();
    }

    @Transactional
    public Prova gerarProvaComGabarito(String titulo, String tema, Usuario criador) {
        // Gerar questões via IA
        List<Questao> questoes = iaQuestaoService.buscarQuestoesPorTema(tema);

        // Criar prova
        Prova prova = new Prova();
        prova.setTitulo(titulo);
        prova.setDataCriacao(LocalDate.now());
        prova.setCriador(criador);
        prova.setQuestoes(new ArrayList<>());

        for (Questao questao : questoes) {
            prova.adicionarQuestao(questao); // já vincula a prova na questão
        }

        // Gerar PDF da prova (sem respostas)
        byte[] pdfProva = ProvaPdfGenerator.gerarPdf(prova);
        prova.setArquivoPdf(pdfProva);

        // Persistir prova no banco
        Prova provaSalva = provaRepository.save(prova);

        // Gerar gabarito vinculado à prova
        Gabarito gabarito = gabaritoService.gerarGabaritoParaProva(provaSalva);

        // Vincular gabarito à prova e salvar novamente
        provaSalva.setGabarito(gabarito);
        return provaRepository.save(provaSalva);
    }
}
