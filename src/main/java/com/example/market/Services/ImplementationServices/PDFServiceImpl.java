package com.example.market.Services.ImplementationServices;

import com.example.market.Services.PDFService;
import com.example.market.models.Client;
import com.example.market.models.ClientProduct;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class PDFServiceImpl implements PDFService {

        @Override
        public ByteArrayOutputStream generatePDF(HttpServletResponse response, Client client, Set<ClientProduct> sales)
                        throws IOException {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                // La linea 56 perimte que una vez creado el pdf se guarde en la response,
                // eso hace que se descargue al encontrar la respuesta
                PdfWriter pdfWriter = new PdfWriter(baos);
                PdfDocument pdfDocument = new PdfDocument((pdfWriter));
                Document document = new Document(pdfDocument);

                pdfDocument.setDefaultPageSize(PageSize.A4);
                /*
                 * String date = LocalDateTime.now().format(ISO_LOCAL_DATE) +"  " +
                 * LocalTime.now().format(ISO_TIME).substring(0,5);
                 */

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String date = LocalDateTime.now().format(formatter);

                // Concepto similar a grid de css
                float col = 280f;
                float columnWidth[] = { col, col };

                Table table = new Table(columnWidth);
                String imgFile = "https://cdn.discordapp.com/attachments/1016816395486503003/1021830775781457961/unknown1__1_-removebg-preview.png";
                ImageData data = ImageDataFactory.create(imgFile);
                Image img = new Image(data);
                img.setMaxHeight(125);
                img.setMarginLeft(10f);

                table.setBackgroundColor(new DeviceRgb(5, 71, 105))
                                .setFontColor(new DeviceRgb(255, 255, 255));
                table.addCell(new Cell().add(img).setMarginRight(15f).setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph("Artics\n  Collect em' all\n" + date))
                                .setFontSize(20f)
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setPaddings(30f, 10f, 30f, 0f)
                                .setBorder(Border.NO_BORDER));

                float colWidth[] = { 80f, 300f, 100f, 80f };
                Table clientTableInfo = new Table(columnWidth);

                clientTableInfo.addCell(new Cell(0, 4)
                                .add(new Paragraph("Client Information"))
                                .setBold()
                                .setBorder(Border.NO_BORDER));

                clientTableInfo.addCell(new Cell()
                                .add(new Paragraph("Client Name: "))
                                .setBorder(Border.NO_BORDER));
                clientTableInfo.addCell(new Cell()
                                .add(new Paragraph(client.getFirstName()))
                                .setBorder(Border.NO_BORDER));
                clientTableInfo.addCell(new Cell()
                                .add(new Paragraph("Date: "))
                                .setBorder(Border.NO_BORDER));
                clientTableInfo.addCell(new Cell()
                                .add(new Paragraph(date))
                                .setBorder(Border.NO_BORDER));

                float itemInfoColWidth[] = { 70f, 135f, 225f, 80f, 70f };
                Table dataTable = new Table(itemInfoColWidth);
                dataTable.setFontSize(10f);

                dataTable.addCell(new Cell()
                                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                                .setFontColor(new DeviceRgb(255, 255, 255))
                                .add(new Paragraph("Product Id")).setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE));
                dataTable.addCell(new Cell()
                                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                                .setFontColor(new DeviceRgb(255, 255, 255))
                                .add(new Paragraph("Product Name")).setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE));
                dataTable.addCell(new Cell()
                                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                                .setFontColor(new DeviceRgb(255, 255, 255))
                                .add(new Paragraph("Created")).setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE));
                dataTable.addCell(new Cell()
                                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                                .setFontColor(new DeviceRgb(255, 255, 255))
                                .add(new Paragraph("Price")).setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE));

                document.add(table);
                document.add(new Paragraph("\n"));
                document.add(clientTableInfo);

                document.add(new Paragraph("\n"));
                document.add(new Paragraph("PURCHASE DETAILS").setUnderline().setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("\n"));

                dataTable.startNewRow();

                for (ClientProduct sale : sales) {
                        dataTable.setFontSize(10f);
                        dataTable.addCell(new Cell().add(new Paragraph(String.valueOf(sale.getProduct().getId())))
                                        .setTextAlignment(TextAlignment.CENTER));
                        dataTable.addCell(new Cell().add(new Paragraph(sale.getProduct().getName()))
                                        .setTextAlignment(TextAlignment.CENTER));
                        dataTable.addCell(new Cell()
                                        .add(new Paragraph(String.valueOf(sale.getProduct().getCreationDate())))
                                        .setTextAlignment(TextAlignment.CENTER));
                        dataTable.addCell(new Cell().add(new Paragraph("$" + sale.getProduct().getPrice()))
                                        .setTextAlignment(TextAlignment.CENTER));
                        dataTable.startNewRow();
                }

                document.add(dataTable);
                document.add(new Paragraph("\n"));
                /*
                 * document.add(new
                 * Paragraph("PURCHASE DETAILS").setUnderline().setTextAlignment(TextAlignment.
                 * CENTER));
                 * 
                 * for (ClientProduct sale : sales){
                 * Paragraph p = new Paragraph();
                 * p.add(sale.toString()).setItalic();
                 * document.add(p);
                 * document.add(new Paragraph("\n"));
                 * }
                 */
                Table footer = new Table(1).useAllAvailableWidth();

                Cell cell = new Cell().add(new Paragraph("Artics Digitals | Collect 'em all ")
                                .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER);
                footer.addCell(cell);
                /*
                 * DateTimeFormatter formatter =
                 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                 */
                cell = new Cell().add(new Paragraph(LocalDateTime.now().format(formatter))
                                .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER);
                footer.addCell(cell);
                pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new TableFooterEventHandler(footer));
                document.close();
                return baos;
        }

        private static class TableFooterEventHandler implements IEventHandler {
                private Table table;

                public TableFooterEventHandler(Table table) {
                        this.table = table;
                }

                @Override
                public void handleEvent(Event currentEvent) {
                        PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
                        PdfDocument pdfDoc = docEvent.getDocument();
                        PdfPage page = docEvent.getPage();
                        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

                        new Canvas(canvas, new Rectangle(36, 20, page.getPageSize().getWidth() - 72, 50))
                                        .add(table)
                                        .close();
                }
        }

}