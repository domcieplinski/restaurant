package gui.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PdfGenerator {
    private String address = "";
    public PdfGenerator(double orderValue, int orderCounter){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        try{
            String path = "newssss.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);
            getInvoiceHeader(orderValue);
            Paragraph paragraph = new Paragraph(address);
            document.add(paragraph);
            Paragraph paragraph2 = new Paragraph("Order #" + orderCounter + " " + dtf.format(now));
            document.add(paragraph2);

            document.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }


    }
    private void getInvoiceHeader(double orderValue){
        this.address = "Company Name \nStreet 55\n123-123 City\n" + orderValue;
    }


}

