package Main;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PDDocument pdfDocument = null;
        try {
            String caminho = "/home/radames/Diário Desempenho - lp2 - 2022 - TurmaB.pdf";
            pdfDocument = PDDocument.load(caminho);
            PDFTextStripper stripper = new PDFTextStripper();
            String t = stripper.getText(pdfDocument);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println("deu erro");
        }
    }

}
