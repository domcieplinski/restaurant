package gui.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;

public class PdfGenerator {
    private String address = "";
    public PdfGenerator(){
        try{
            String path = "newssss.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);
            getInvoiceHeader();
            Paragraph paragraph = new Paragraph(address);
            document.add(paragraph);

            document.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }


    }
    private void getInvoiceHeader(){
        this.address = "Company Name \nStreet 55\n123-123 City";
    }


}

