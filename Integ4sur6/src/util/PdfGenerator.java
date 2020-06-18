/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//mch lazem n7ot email s7i7 chnab3eth 
//bch tab3th lel client enti wela livreur?
//livreur ama enti hak 7atetha client nn?? si nn bilehi excuti 5a nchoufou email
//adresse statique elli tab3thelha
package util;
import service.ServiceCommande;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class PdfGenerator {
     public static final String BasicPath = " C:\\Users\\USER\\Desktop\\JavaFXApplication4\\src\\ressources\\PdfGenerated\\";
    

    public static void main(String args[]) throws IOException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//    Document document=    createPdf(DEST);
////    writeOnPdf(document,"Article ");
//        ServiceVente sv = new ServiceVente();
//        ServiceArticle sa = new ServiceArticle();
//        ServiceAchat sAchat = new ServiceAchat();
//        ServiceClient sc = new ServiceClient();
//        ServiceUtilisateur su = new ServiceUtilisateur();
//        ServiceFournisseur sf = new ServiceFournisseur();
//        ServiceLigneStock sl = new ServiceLigneStock();
//
//        Document doc = createPdf(DESTSOF);
//
//        doc.add(sl.generateLigneStockPdfTable());
//
//        doc.close();

    }

    public static Document createPdf(String dest) {
        PdfWriter writer;
        PdfDocument pdf;
        Document document = null;
        try {
            writer = new PdfWriter(dest);
            pdf = new PdfDocument(writer);
            document = new Document(pdf, PageSize.A4.rotate());
            document.setMargins(10, 5, 5, 5);
            //document.add(new Paragraph(""));

        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage());
        }

        return document;

    }

    public void manipulatePdf(String cov, String src, String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        PdfDocument cover = new PdfDocument(new PdfReader(cov));
        cover.copyPagesTo(1, 1, pdfDoc, 1, new PdfPageFormCopier());
        cover.close();
        pdfDoc.close();
    }

//    public static Document writeOnPdf(Document doc, String para) {
//
//        doc.add(new Paragraph(para));
//
//        return doc;
//
//    }
}
