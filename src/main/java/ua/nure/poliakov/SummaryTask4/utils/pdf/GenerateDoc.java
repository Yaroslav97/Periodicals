package ua.nure.poliakov.SummaryTask4.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GenerateDoc {

    public static void main(String[] args) {
        Document doc = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(doc, new FileOutputStream("Report.pdf"));
            doc.open();
            doc.add(new Paragraph("Hello pdf"));
            doc.addCreationDate();
            doc.addCreator("Periodicals");
            doc.close();
            pdfWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
