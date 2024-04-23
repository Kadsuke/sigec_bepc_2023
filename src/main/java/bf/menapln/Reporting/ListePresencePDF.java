/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.Epreuve;
import bf.menapln.ihm.Fenetre;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class ListePresencePDF extends ListePDF{
    private List<Epreuve> epreuves;
    private Paragraph epreuve;
    public ListePresencePDF(Fenetre fenetre) {
        super(fenetre);
    }
    
    //@Override
    public void setTableBody() {
        int i = 0;
        for(Epreuve e : this.epreuves){
            i++;
            this.setEpreuve(new Paragraph().add("Epreuve : "+e.getEpreuveLibelle()));
            this.document.add(this.title.setTextAlignment(TextAlignment.CENTER));
            this.document.add(this.epreuve.setTextAlignment(TextAlignment.CENTER));
            this.document.add(new Paragraph().add("Date composition :............................. Heure début : ................... Heure Fin : ...................").setTextAlignment(TextAlignment.LEFT));
            this.document.add(new Paragraph().add("Epreuve composée sans incidence (Oui/Non) ?: ............................. ").setTextAlignment(TextAlignment.LEFT));
            this.document.add(new Paragraph().add("Si non, décrire l'incidence : ")
                    .add("................................................................................................................"));
            this.document.add(new Paragraph().add("............................................................................................................................................................"));
            this.document.add(new Paragraph().add("............................................................................................................................................................"));
            
            Table table = new Table(this.tableHeaderData.length);
            table.setWidth(UnitValue.createPercentValue(100));
            this.setTableHeader(table);
            for(Object copie : this.listeCandidat){
                table.addCell(((CandidatJury)copie).getCandidat().getNumeroPv().toString());
                table.addCell(((CandidatJury)copie).getCandidat().getNom());
                table.addCell(((CandidatJury)copie).getCandidat().getPrenom());
                table.addCell(((CandidatJury)copie).getCandidat().getSexe());
                table.addCell(((CandidatJury)copie).getCandidat().getEtablissementCandidat().getNomStructure());
                table.addCell("");
            }
            this.document.add(table);
            if(i < this.epreuves.size()){
                this.document.add(new AreaBreak());
            }
        }
    }
    
    @Override
    public void body() {
        this.fonction[0] = "Le président de jury";
        this.signataire[0] = this.fenetre.getUserSession().getUser().getPersonnel().getNom();
        this.signataire[0] = this.signataire[0].concat(" ");
        this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        this.setTableBody();
    }
    
    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    public Paragraph getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Paragraph epreuve) {
        this.epreuve = epreuve;
    }
    
    
}
