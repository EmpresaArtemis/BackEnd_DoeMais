package br.com.artemis.poctcc.service.geradorRelatorio;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioDoadorDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RelatorioDoadorService {

    public static ByteArrayInputStream montarRelatorioDoador(
        List<RelatorioDoadorDTO> relatorioDoadorDTO,
        ResumoCadastroDataDTO resumoCadastroDataDTO
    ){
       try{

//           response.setContentType("apllication/pdf");
//
//           response.addHeader("Content-Disposition", "inline; filename=" + "relatorioOng.pdf");
           Document document = new Document(PageSize.A4);

           ByteArrayOutputStream out = new ByteArrayOutputStream();

           PdfWriter.getInstance( document, out);

           Paragraph quantidadeDoadores = new Paragraph();

           PdfPTable table = montarTabelaDoador(quantidadeDoadores, resumoCadastroDataDTO);

           PdfPTable table2 = new PdfPTable(3);
           PdfPCell cell2 = new PdfPCell (new Paragraph (20F, "Lista de Doadores" , FontFactory.getFont(FontFactory.HELVETICA, 18F)));

           Image image = Image.getInstance("C:/Users/alani/Documents/GitHub/DM_Artemis_back/DM_server/Imagem/logo-white.jpeg");
           image.scaleToFit(150, 150);
           image.setAbsolutePosition(445, 765);

           cell2.setColspan (3);
           cell2.setPadding(5);
           cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell2.setBackgroundColor(new BaseColor(229, 123, 16, 80));
           cell2.setPadding (10.0f);
           table2.addCell (cell2);


           cell2 = new PdfPCell (new Paragraph (20F, "Nome do doador", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
           cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
           cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
           cell2.setPadding (10.0f);
           table2.addCell (cell2);

           cell2 = new PdfPCell (new Paragraph (20F, "CPF", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
           cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
           cell2.setPadding(5);
           cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
           cell2.setPadding (10.0f);
           table2.addCell (cell2);

           cell2 = new PdfPCell (new Paragraph (20F, "Idade", FontFactory.getFont(FontFactory.HELVETICA, 15F)));
           cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
           cell2.setBackgroundColor (new BaseColor(229, 123, 16, 80));
           cell2.setPadding (10.0f);
           table2.addCell(cell2);

           for (RelatorioDoadorDTO doadorDTO : relatorioDoadorDTO) {
               table2.addCell(doadorDTO.getNomeDoador());
               table2.addCell(new Phrase(doadorDTO.getCpf()));
               table2.addCell(new Phrase(String.valueOf(doadorDTO.getDtNasc())));
               table2.addCell(new Phrase(doadorDTO.getIdade().toString()));
           }
           table.setSpacingAfter(20);

           cell2 = new PdfPCell(new Paragraph(String.valueOf(table2)));
           cell2.setPadding(10.0f);
           cell2.setBackgroundColor(BaseColor.BLACK);
           table2.addCell(cell2);

           document.addAuthor("Doe+");
           document.addTitle("Relatorio De Doadores");

           Paragraph paragraph = new Paragraph(new Phrase(20F , "Relatorio De Doadores" , FontFactory.getFont(FontFactory.HELVETICA, 18F)));
           paragraph.setAlignment(1);

           document.open();
           document.add(paragraph);
           document.add(image);
           document.add( Chunk.NEWLINE);
           document.add(quantidadeDoadores);
           document.add(table);
           document.add( Chunk.NEWLINE);
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

    private static PdfPTable montarTabelaDoador(Paragraph quantidadeDoadores, ResumoCadastroDataDTO resumoCadastroDataDTO) {
        quantidadeDoadores.setSpacingAfter(20);

        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell (new Paragraph (20F, "Quantidade de doadores", FontFactory.getFont(FontFactory.HELVETICA, 18F)));


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

        Map<String, Integer> qtdDoador = new HashMap<>();

        qtdDoador.put("Semana", resumoCadastroDataDTO.getSemana().intValue());
        qtdDoador.put("Mês", resumoCadastroDataDTO.getMês().intValue());
        qtdDoador.put("Ano", resumoCadastroDataDTO.getAno().intValue());
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);

        for (int i = 0; i < qtdDoador.size(); i++){
            table.addCell(new ArrayList<>(qtdDoador.keySet()).get(i));
            cell1.setPhrase((new Phrase((new ArrayList<>(qtdDoador.values()).get(i).toString()))));
            cell1.setPadding(5);
            table.addCell(cell1);
        }
        table.setSpacingAfter(20);
        return table;
    }
}
