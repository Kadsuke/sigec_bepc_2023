/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

/**
 *
 * @author coulibaly
 */
public class PvDeliberation extends PDF{

    public PvDeliberation(Fenetre fenetre) {
        super(fenetre);
        this.fonction[0] = "Le président du jury";
        this.signataire[0] = this.fenetre.getUserSession().getUser().getPersonnel().getNom();
        this.signataire[0] = this.signataire[0].concat(" ");
        this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        this.setPageSize(PageSize.A4.rotate());
    }

    @Override
    public void setTableBody(Table table) {
        int feuilleIndice = 0;
        for(Objet objet :this.listeCandidat){
            Candidat candidat = ((Candidat)objet);
            Integer columnIndex = 0;
            while(columnIndex < this.tableHeaderData.length){
                 /* if(columnIndex == this.tableHeaderData.length-1){
                    table.addCell(candidat.mention());
                }else  */if(columnIndex == this.tableHeaderData.length-1){
                    table.addCell(candidat.decisionJury());
                }else if(columnIndex == this.tableHeaderData.length-2){
                    table.addCell(candidat.moyenne().toString());
                }else if(columnIndex == this.tableHeaderData.length-3){
                    table.addCell(candidat.getTotalPondere()+" / "+(candidat.getTotaPondereMax()));
                }else if(columnIndex%2 == 0){
                    feuilleIndice = columnIndex/2;
                    table.addCell((feuilleIndice < candidat.getCopies().size())? (((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getAnonymat() != null)?((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getAnonymat() : candidat.getNumeroPv().toString() :null);
                }else if(columnIndex%2 == 1){
                    feuilleIndice = (columnIndex-1)/2;
                    Double note = (feuilleIndice < candidat.getCopies().size())? ((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getNote():null;
                    table.addCell((note == null)?"D":note.toString());
                }
                columnIndex++;
            }
        }
        
    }
    
    @Override
    public void body(){
        super.body();
        this.document.add(new Paragraph().add("Effectif des inscrits : "+this.listeCandidat.size()));
        this.document.add(new Paragraph().add("Effectif des presents :"));
        this.document.add(new Paragraph().add("Effectif des absents :"));
        this.document.add(new Paragraph().add("Proposés pour l'admission :"));
        Table tab = new Table(this.tableHeaderData.length);
        tab.setWidth(UnitValue.createPercentValue(100));
        this.setTableHeader(tab);
        this.setTableBody(tab);
        this.document.add(tab);
    }
}
