/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.ihm.Fenetre;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

/**
 *
 * @author coulibaly
 */
public abstract class Anonymat extends PDF{

    public Anonymat(Fenetre fenetre) {
        super(fenetre);
        this.fonction[0] = "";
        
        this.signataire[0] = "";
       // this.signataire[0] = this.signataire[0].concat(" ");
        //this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        //this.setBottomMargin(300);
        //this.setTopMargin(300);
        this.setQrcodeSize(0f);
        
    }
     @Override
    public abstract void setTableBody(Table table);

    @Override
    public void body() {
        super.body();
        
        float offSet = 36;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet - 5, offSet, columnWidth, columnHeight-200),
            new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight-200),
            new Rectangle(
                offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight-200)};
        this.document.setRenderer(new ColumnDocumentRenderer(this.document, columns));

        
        Table tab = new Table(this.tableHeaderData.length);
        tab.setWidth(UnitValue.createPercentValue(100));
        this.setTableHeader(tab);
        this.setTableBody(tab);
        this.document.add(tab);
    }

}
