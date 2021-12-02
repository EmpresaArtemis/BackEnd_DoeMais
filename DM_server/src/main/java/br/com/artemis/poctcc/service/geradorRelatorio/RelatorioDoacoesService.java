package br.com.artemis.poctcc.service.geradorRelatorio;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioDoacoesDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import javafx.scene.layout.Border;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioDoacoesService {

    public static ByteArrayInputStream montarRelatorioDoacoes(
            List<RelatorioDoacoesDTO> relatorioDoacoesDTOS,
            ResumoCadastroDataDTO resumoCadastroDataDTO
    ){
        try{

            Document document = new Document(PageSize.A4);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance( document, out );

            Paragraph quantidadeDoacoes = new Paragraph();

            PdfPTable table = montarRelatorioDoacoes(quantidadeDoacoes, resumoCadastroDataDTO);

            // Lista de doações

            PdfPTable table2 = new PdfPTable(2);
            PdfPCell cell2 = new PdfPCell (new Paragraph (20F, "Lista de doações", FontFactory.getFont(FontFactory.HELVETICA, 18F)));

            Image image = Image.getInstance("C:/Users/alani/Documents/GitHub/DM_Artemis_back/DM_server/Imagem/logo-white.jpeg");
            image.scaleToFit(150, 150);
            image.setAbsolutePosition(445, 765);



            cell2.setColspan (2);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);

            table2.addCell(cell2);
            cell2 = new PdfPCell (new Paragraph (20F, "Nome do Item", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);


            table2.addCell(cell2);

            cell2 = new PdfPCell (new Paragraph (20F, "Data", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);

            table2.addCell (cell2);

//            Paragraph doacaoLista = new Paragraph("Lista De Doações");
//            doacaoLista.setSpacingAfter(20);
////            doacaoLista.add(cell2);

            float[] columnWidths = new float[]{20f, 5f};
            table2.setWidths(columnWidths);

//            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//            table2.addCell("Data");
//            table2.addCell(new Phrase("Nome do item"));
//            cell2.setBackgroundColor(BaseColor.GRAY);
//            table2.addCell(cell2);

            relatorioDoacoesDTOS.forEach(doacao -> {
                table2.addCell(doacao.getNomeItem());
                table2.addCell(new Phrase(doacao.getDataCriacao()));
            });
            for (int i = 0; i < 12; i++){
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            }

            document.addAuthor("Doe+");
            document.addTitle("Relatorio da Ong");

            Paragraph paragraph = new Paragraph (20F, "Relatorio de doações", FontFactory.getFont(FontFactory.HELVETICA, 18F));
            paragraph.setAlignment(1);



            document.open();
            document.add(paragraph);
            document.add(image);
            document.add(Chunk.NEWLINE);
//            document.add(quantidadeDoacoes);
            document.add(table);
            document.add( Chunk.NEWLINE );
            document.add(table2);

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        }catch (DocumentException de){
            System.err.println(de.getMessage());
        } catch (MalformedURLException de) {
            de.printStackTrace();
        } catch (IOException de) {
            de.printStackTrace();
        }
        return null;
    }

    private static PdfPTable montarRelatorioDoacoes(Paragraph quantidadeDoacao, ResumoCadastroDataDTO resumoCadastroDataDTO) {
        quantidadeDoacao.setSpacingAfter(20);

        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell (new Paragraph (20F, "Quantidade de doações realizadas", FontFactory.getFont(FontFactory.HELVETICA, 18F)));
        cell1.setColspan (2);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBackgroundColor(new BaseColor(229, 123, 16, 80));
        cell1.setPadding (10.0f);
        table.addCell(cell1);

        cell1 = new PdfPCell (new Paragraph (20F, "Doações cadastradas", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
        cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell1.setPadding (10.0f);
        table.addCell(cell1);

        cell1 = new PdfPCell (new Paragraph (20F, "Quantidade", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
        cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell1.setPadding (10.0f);
        table.addCell (cell1);

        Map<String, Long> qtdDoacao = new HashMap<>();

        qtdDoacao.put("Semana", resumoCadastroDataDTO.getSemana());
        qtdDoacao.put("Mês", resumoCadastroDataDTO.getMês());
        qtdDoacao.put("Ano", resumoCadastroDataDTO.getAno());

        for (int i = 0; i < qtdDoacao.size(); i++) {
            table.addCell(new ArrayList<>(qtdDoacao.keySet()).get(i));
            cell1.setPhrase(new Phrase(new ArrayList<>(qtdDoacao.values()).get(i).toString()));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
        }
        table.setSpacingAfter(20);
        return table;
    }
}
