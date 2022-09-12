package gui.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import gui.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.prefs.Preferences;


public class PdfGenerator {
    Preferences pref = Preferences.userNodeForPackage(Settings.class);
    private String title = pref.get("title", "root");
    private String street = pref.get("street", "root");
    private String state = pref.get("state", "root");
    private String zipcode = pref.get("zipcode", "root");
    private float column2Width[] = {140, 140, 140, 140};
    private Table table2 = new Table(column2Width);
    public PdfGenerator(double orderValue, int orderCounter, ArrayList<String[]> orderList){
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


            /* Preparing table header to *.pdf document */
            Paragraph paragraph = new Paragraph("INVOICE");
            paragraph.setMargins(30f,0f, 30f, 80f);
            paragraph.setFontSize(20f);

            table.addCell(new Cell().add(paragraph)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER)
            );

            Paragraph paragraph1 = new Paragraph(title + "\n" + street + "\n" + zipcode + " " + state );
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
            String getDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String getYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String clock = String.valueOf(now.getHour()) + ":" + String.valueOf(now.getMinute());
            Paragraph paragraph2 = new Paragraph("\nOrder " + getDay + "/" + getMonth + "/" + getYear + "/" + orderCounter + " " + clock + "\n\n\n")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(15f);
            document.add(paragraph2);




            table2.addCell(new Cell()
                    .add(new Paragraph("Description"))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));

            table2.addCell(new Cell()
                    .add(new Paragraph("Price net"))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));
            table2.addCell(new Cell()
                    .add(new Paragraph("Tax value"))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));
            table2.addCell(new Cell()
                    .add(new Paragraph("Total price"))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));
            makeTable(orderList);
            table2.addCell(new Cell().add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER));
            table2.addCell(new Cell().add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER));
            table2.addCell(new Cell().add(new Paragraph("Total amount"))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(orderValue)))
                    .setBackgroundColor(new DeviceRgb(148,213,255))
                    .setFontColor(new DeviceRgb(255,255,255)));

            document.add(table2);
            String imageFile = "qr.png";
            ImageData data = ImageDataFactory.create(imageFile);
            Image img = new Image(data);
            document.add(img);



            document.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    private void makeTable(ArrayList<String[]> orderList){
       for(int i = 0; i < orderList.size(); i++){
            //String slowo = orderList.get(i)[];
           table2.addCell(new Cell()
                 .add(new Paragraph(orderList.get(i)[0])));
           table2.addCell(new Cell()
                   .add(new Paragraph(orderList.get(i)[1])));
           table2.addCell(new Cell()
                   .add(new Paragraph(orderList.get(i)[2])));
           table2.addCell(new Cell()
                   .add(new Paragraph(orderList.get(i)[2])));

        }

    }

    private void getInvoiceHeader(){
       // this.address = pref.get("title", "root");
    }



}

