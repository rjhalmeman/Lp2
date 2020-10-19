package pdf_gerarpdfitext;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CriarPDF {

    public CriarPDF() {
// criação do documento 
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("MeuPdf.pdf"));
            document.open(); // adicionando um parágrafo no documento 

            document.setPageSize(PageSize.A4);

            // com margens 
            // Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
            // document.add(new Paragraph("Primeira linha"));
            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Paragraph p1 = new Paragraph("Exemplo de criação de PDF via Java", f);

            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(20);
            document.add(new Paragraph(p1));
            // document.add(new Paragraph("terceira linha"));
            document.add(new Paragraph("  "));

            /* 
             document.newPage();
             document.add(new Paragraph("Novo parágrafo na nova página")); 
             */
//            Image figura = Image.getInstance("usinaM.png");
//            document.add(figura);
//            document.add(new Paragraph("  "));
            //tabela
            // PdfPTable table = new PdfPTable(2); //número de colunas
            PdfPTable table = new PdfPTable(new float[]{0.3f, 0.7f}); // colunas com larguras diferentes em %

            table.setWidthPercentage(100.0f);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell header = new PdfPCell();

//            header.setBackgroundColor(BaseColor.CYAN);
//            header.setBorderWidthBottom(1.0f);
//            header.setBorderColorBottom(BaseColor.BLUE);
//            header.setBorder(Rectangle.AUTHOR);
//            header.setBorder(Rectangle.BOTTOM);
//            header.setBorder(Rectangle.LEFT);
            header.setColspan(2);
            Paragraph h = new Paragraph("Lista de alunos");
            header.addElement(h);

            table.addCell(header);
            table.addCell("Nome");
            table.addCell("Assinatura");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            document.add(table);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
