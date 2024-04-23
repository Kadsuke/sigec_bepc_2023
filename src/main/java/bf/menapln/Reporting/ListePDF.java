/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.CandidatJury;
import bf.menapln.ihm.Fenetre;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

/**
 *
 * @author coulibaly
 */
public class ListePDF extends PDF{

    public ListePDF(Fenetre fenetre) {
        super(fenetre);
        
    }

    @Override
    public void setTableBody(Table table) {
        for(Object copie : this.listeCandidat){
            table.addCell(((CandidatJury)copie).getCandidat().getNumeroPv().toString());
            table.addCell(((CandidatJury)copie).getCandidat().getNom());
            table.addCell(((CandidatJury)copie).getCandidat().getPrenom());
            table.addCell(((CandidatJury)copie).getCandidat().getSexe());
            table.addCell("");
            table.addCell(((CandidatJury)copie).getCandidat().getSport());
            table.addCell(((CandidatJury)copie).getCandidat().getEtablissementCandidat().getNomStructure());
        }
    }

    @Override
    public void body() {
        super.body();
        this.fonction[0] = "Le président du jury";
        this.signataire[0] = this.fenetre.getUserSession().getUser().getPersonnel().getNom();
        this.signataire[0] = this.signataire[0].concat(" ");
        this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        Table tab = new Table(this.tableHeaderData.length);
        tab.setWidth(UnitValue.createPercentValue(100));
        this.setTableHeader(tab);
        this.setTableBody(tab);
        this.document.add(tab);
    }
    
}
