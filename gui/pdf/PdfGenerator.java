package gui.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import gui.Settings;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;





public class PdfGenerator {
    Preferences pref = Preferences.userNodeForPackage(Settings.class);
    private String address = "";
    public PdfGenerator(double orderValue, int orderCounter){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        try{
            String path = "newssss.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);
            float column = 280f;
            float columnWidth[] = {column, column};

            Table table = new Table(columnWidth);
            table.setBackgroundColor(new DeviceRgb(148, 213, 255 ))
                    .setFontColor(new DeviceRgb(255,255,255));

            getInvoiceHeader();

            /* Preparing table header to *.pdf document */
            Paragraph paragraph = new Paragraph("INVOICE");
            paragraph.setMargins(30f,0f, 30f, 80f);
            paragraph.setFontSize(20f);

            table.addCell(new Cell().add(paragraph)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER)
            );

            Paragraph paragraph1 = new Paragraph(address);
            paragraph1.setMarginRight(10f);
            paragraph1.setFontSize(11f);

            table.addCell(new Cell().add(paragraph1)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER)
            );
            /* ---------------------------------------- */
            document.add(table);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
            String getMonth = simpleDateFormat.format(new Date());
            Paragraph paragraph2 = new Paragraph("Order " + orderCounter + "/" + getMonth)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(15f);
            document.add(paragraph2);


            float column2Width[] = {80, 300, 100, 80};
            Table table2 = new Table(column2Width);


            document.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }


    }
    private void getInvoiceHeader(){
        this.address = pref.get("title", "root");
    }



}

