/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.Resultat;
import bf.menapln.ihm.Fenetre;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

/**
 *
 * @author coulibaly
 */
public class ResultatPDF extends PDF{

    public ResultatPDF(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void setTableBody(Table table){
        String etab = "";
        for(Object resultat : this.listeCandidat){
            resultat = (Resultat)resultat;
            if(!etab.equals(((Resultat)resultat).getCandidat().getEtablissementCandidat().getNomStructure())){
                etab = ((Resultat)resultat).getCandidat().getEtablissementCandidat().getNomStructure();
                Cell c = new Cell(1,5).add(new Paragraph(etab)).setHeight(35);
                table.addCell(c).setTextAlignment(TextAlignment.CENTER);
            }  
            table.addCell(((Resultat)resultat).getCandidat().getNumeroPv().toString());
            table.addCell(((Resultat)resultat).getCandidat().getNom()+" "+((Resultat)resultat).getCandidat().getPrenom());
            table.addCell(((Resultat)resultat).getCandidat().getSexe());
            table.addCell(((Resultat)resultat).getCandidat().getDateNaissance().toString());
            table.addCell(((Resultat)resultat).getMention());
        }
    }

    @Override
    public void body() {
        this.fonction[0] = "Le président du jury";
        
        this.signataire[0] = this.fenetre.getUserSession().getUser().getPersonnel().getNom();
        this.signataire[0] = this.signataire[0].concat(" ");
        this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        
        this.document.add(this.title.setTextAlignment(TextAlignment.CENTER));
        Table tab = new Table(this.tableHeaderData.length);
        tab.setWidth(UnitValue.createPercentValue(100));
        this.setTableHeader(tab);
        this.setTableBody(tab);
        this.document.add(tab);
        
        
    }
    
}
