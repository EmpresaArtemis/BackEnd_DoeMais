package br.com.artemis.poctcc.service.geradorRelatorio;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioOngDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeradorRelatorioService {

    public static ByteArrayInputStream montarRelatorioOngs(
            List<RelatorioOngDTO> relatorioOngDTO,
            ResumoCadastroDataDTO resumoCadastroDataDTO
    ) {

        try{
            Document document = new Document(PageSize.A4);
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance( document, out);

            Paragraph quantidadeOng = new Paragraph();

            PdfPTable table = montarTabelaOng(quantidadeOng, resumoCadastroDataDTO);

            //Lista De Ongs

            PdfPTable table2 = new PdfPTable(4);
            PdfPCell cell2 = new PdfPCell (new Paragraph (20F, "Lista de Instituições", FontFactory.getFont(FontFactory.HELVETICA, 18F)));

            Image image = Image.getInstance("C:/Users/alani/Documents/GitHub/DM_Artemis_back/DM_server/Imagem/logo-white.jpeg");
            image.scaleToFit(150, 150);
            image.setAbsolutePosition(445, 765);

            cell2.setColspan (4);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);
            table2.addCell(cell2);

            cell2 = new PdfPCell (new Paragraph (20F, "Nome da instituição", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);
            table2.addCell(cell2);

            cell2 = new PdfPCell (new Paragraph (20F, "CNPJ", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);
            table2.addCell(cell2);

            cell2 = new PdfPCell (new Paragraph (20F, "Razão social", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);
            table2.addCell(cell2);

            cell2 = new PdfPCell (new Paragraph (20F, "Categoria", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
            cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
            cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
            cell2.setPadding (10.0f);
            table2.addCell(cell2);

            float[] columnWidths = new float[]{7f, 7f, 5f, 5f};
            table2.setWidths(columnWidths);


            relatorioOngDTO.forEach(ong -> {
                table2.addCell(ong.getNomeOng());
                table2.addCell(new Phrase(ong.getCnpj()));
                table2.addCell(new Phrase(ong.getRazaoSocial()));
                table2.addCell(new Phrase(ong.getCategoria()));



            });
            for (int i = 0; i < 12; i++){
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            }


            document.addAuthor("Doe+");
            document.addTitle("Relatorio Da Ong");

            Paragraph paragraph = new Paragraph (20F, "Relatorio da Instituição", FontFactory.getFont(FontFactory.HELVETICA, 18F));
            paragraph.setAlignment(1);

            document.open();
            document.add(paragraph);
            document.add(image);
            document.add( Chunk.NEWLINE );
//            document.add(quantidadeOng);
            document.add(table);
            document.add( Chunk.NEWLINE );
            document.add(table2);

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException de ){
            System.err.println(de.getMessage());
        } catch (MalformedURLException de) {
            de.printStackTrace();
        } catch (IOException de) {
            de.printStackTrace();
        }
        return null;
    }

    private static PdfPTable montarTabelaOng(Paragraph quantidadeOng, ResumoCadastroDataDTO resumoCadastroDataDTO) {


        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell (new Paragraph (20F, "Quantidade de instituições", FontFactory.getFont(FontFactory.HELVETICA, 18F)));
        cell1.setColspan (2);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBackgroundColor(new BaseColor(229, 123, 16, 80));
        cell1.setPadding (10.0f);

        table.addCell(cell1);
        cell1 = new PdfPCell (new Paragraph (20F, "Instituições cadastradas", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
        cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell1.setPadding (10.0f);


        table.addCell(cell1);

        cell1 = new PdfPCell (new Paragraph (20F, "Quantidade", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
        cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell1.setPadding (10.0f);

        table.addCell (cell1);

        Map<String,Long> qtdOng = new HashMap<>();


        qtdOng.put("Semana", resumoCadastroDataDTO.getSemana());
        qtdOng.put("Mês", resumoCadastroDataDTO.getMês());
        qtdOng.put("Ano", resumoCadastroDataDTO.getAno());


        for (int i = 0; i < qtdOng.size(); i++) {
            table.addCell(new ArrayList<>(qtdOng.keySet()).get(i));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPhrase(new Phrase(new ArrayList<>(qtdOng.values()).get(i).toString()));
            table.addCell(cell1);
        }
        table.setSpacingAfter(20);
        return table;
    }
}
