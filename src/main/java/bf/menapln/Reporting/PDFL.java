/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author coulibaly
 */
public abstract class PDFL implements IEventHandler{
    protected Document document;
    protected PdfDocument pdfDocument;
    protected PdfWriter writer;
    
    protected Paragraph timbre;
    protected Paragraph devise;
    protected Paragraph title;
    
    protected String path;
    
    protected String[] tableHeaderData;
    protected List<Objet> listeCandidat;
    
    protected Fenetre fenetre;
    
    public PDFL(Fenetre fenetre) {
        this.fenetre = fenetre;
    }
    
    public static JPanel pdfViewer(PDFL pdf){
        SwingController controller = new SwingController();
        SwingViewBuilder factory = new SwingViewBuilder(controller);
        JPanel viewerComponentPanel = factory.buildViewerPanel();

            // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
            new org.icepdf.ri.common.MyAnnotationCallback(
                controller.getDocumentViewController()));


            // Now that the GUI is all in place, we can try openning a PDF
            controller.openDocument(pdf.getPath());
            return viewerComponentPanel;
    }
    
    public Table header(){
        Table tab = new Table(3);
        tab.setWidth(UnitValue.createPercentValue(100)).setTextAlignment(TextAlignment.CENTER);
        tab.addCell(new Cell().add(this.timbre).setWidth(UnitValue.createPercentValue(55)).setBorder(Border.NO_BORDER));
        tab.addCell(new Cell().add(new Paragraph()).setWidth(UnitValue.createPercentValue(15)).setBorder(Border.NO_BORDER));
        tab.addCell(new Cell().add(this.devise).setWidth(UnitValue.createPercentValue(30)).setBorder(Border.NO_BORDER));
        return tab;
    }
    
    public void setTableHeader(Table table){
        for(String str : this.tableHeaderData){
            table.addHeaderCell(str);
        }
    }
    
    public abstract void setTableBody(Table table);
    
    public abstract void body();
    
    public abstract void footer();
    
    public void generatePDF() throws FileNotFoundException{
        this.writer = new PdfWriter(this.path);
        this.pdfDocument = new PdfDocument(this.writer);
        this.document = new Document(this.pdfDocument);
        this.document.setTopMargin(140);
        this.pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, this);
        //this.header();
        this.body();
        this.footer();
        this.document.close();
    }

    public Paragraph getTimbre() {
        return timbre;
    }

    public void setTimbre(Paragraph timbre) {
        this.timbre = timbre;
    }

    public Paragraph getTitle() {
        return title;
    }

    public void setTitle(Paragraph title) {
        this.title = title;
    }
    
    public String setStateDirectory(){
        String userHome = System.getProperty("user.home");
        String separator = System.getProperty("file.separator");
        String desktop = "Bureau";
        File desktopPath = new File(userHome.concat(separator).concat(desktop));
        if(!desktopPath.exists()){
            desktop = "Desktop";
        }
        String basePath = userHome.concat(separator)
                .concat(desktop)
                .concat(separator)
                .concat("Bepc_").concat(fenetre.getUserSession().getSession().getAnnee().toString())
                .concat(separator);
        File directory = new File(basePath);
        if(!directory.exists()){
            directory.mkdir();
        }
        return basePath;
    }
    
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        new Canvas(documentEvent.getPage(),documentEvent.getPage().getPageSize())
            .showTextAligned(new Paragraph()
                .setWidth(documentEvent.getPage().getPageSize().getWidth()-this.document.getLeftMargin()*2)
                .add(this.header()), documentEvent.getPage().getPageSize().getX()+this.document.getLeftMargin(), documentEvent.getPage().getPageSize().getTop()-140, TextAlignment.LEFT)
            .close();
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = this.setStateDirectory().concat(path);
    }

    public Paragraph getDevise() {
        return devise;
    }

    public void setDevise(Paragraph devise) {
        this.devise = devise;
    }

    public List<Objet> getListeCandidat() {
        return listeCandidat;
    }

    public void setListeCandidat(List<Objet> listeCandidat) {
        this.listeCandidat = listeCandidat;
    }

    public String[] getTableHeaderData() {
        return tableHeaderData;
    }

    public void setTableHeaderData(String[] tableHeaderData) {
        this.tableHeaderData = tableHeaderData;
    }
    
    
}
