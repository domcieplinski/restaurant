package gui.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.io.FileNotFoundException;

public class PdfGenerator {
    public static void main(String[] args){
        try{
            String path = "newssss.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);
            Paragraph paragraph = new Paragraph("test");
            document.add(paragraph);

            document.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }


    }
}
