/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author coulibaly
 */
public class TestPDF {

    public void ManipulatePdf(String dest) throws FileNotFoundException
    {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(doc));

        for (int i = 0; i < 3; i++)
        {
            doc.add(new Paragraph("Test " + (i + 1)));
            if (i != 2)
            {
                doc.add(new AreaBreak());
            }
        }

        doc.close();
    }

    private class TextFooterEventHandler implements IEventHandler
    {
        protected Document doc;

        public TextFooterEventHandler(Document doc)
        {
            this.doc = doc;
        }
        
        @Override
        public void handleEvent(Event currentEvent)
        {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            Rectangle pageSize = docEvent.getPage().getPageSize();
            PdfFont font = null;
            try {
                font = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
            }
            catch (IOException e) 
            {
                //Console.class. .Error.WriteLine(e.Message);
            }

            float coordX = ((pageSize.getLeft() + doc.getLeftMargin())
                             + (pageSize.getRight() - doc.getRightMargin())) / 2;
            float headerY = pageSize.getTop() - doc.getTopMargin() + 10;
            float footerY = doc.getBottomMargin();
            Canvas canvas = new Canvas(docEvent.getPage(), pageSize);
            canvas
                .setFont(font)
                .setFontSize(5)
                .showTextAligned("this is a header", coordX, headerY, TextAlignment.CENTER)
                .showTextAligned("this is a footer", coordX, footerY, TextAlignment.CENTER)
                .close();
        }
    }
    
}
