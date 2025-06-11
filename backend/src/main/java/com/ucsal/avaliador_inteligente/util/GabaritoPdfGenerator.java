package com.ucsal.avaliador_inteligente.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ucsal.avaliador_inteligente.model.Alternativa;
import com.ucsal.avaliador_inteligente.model.Prova;
import com.ucsal.avaliador_inteligente.model.Questao;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class GabaritoPdfGenerator {

    public static byte[] gerarPdf(Prova prova) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Gabarito - " + prova.getTitulo()).setBold().setFontSize(16f));
            document.add(new Paragraph(" ")); // espaço

            List<Questao> questoes = prova.getQuestoes();
            int numero = 1;

            for (Questao questao : questoes) {
                document.add(new Paragraph(numero++ + ") " + questao.getEnunciado()));

                char letra = 'A';
                for (Alternativa alt : questao.getAlternativas()) {
                    String textoAlternativa = letra++ + ") " + alt.getDescricao();
                    if (Boolean.TRUE.equals(alt.getCorreta())) {
                        textoAlternativa += "  (CORRETA)";
                    }
                    document.add(new Paragraph(textoAlternativa));
                }

                document.add(new Paragraph(" "));
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF do gabarito", e);
        }
    }
}
