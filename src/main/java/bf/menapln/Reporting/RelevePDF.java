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
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import static java.awt.Color.yellow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author coulibaly
 */
public class RelevePDF extends PDF{

    public RelevePDF(Fenetre fenetre) {
        super(fenetre);
        this.HasDefaultHeader = false;
        this.setPageSize(PageSize.A4.rotate());
        this.topMargin = 30;
        this.bottomMargin = 10;
        this.setQrcodeSize(50f);
    }
    
    @Override
    public void body(){
        int i = 0;
        for(Object objet : this.listeCandidat){
            Candidat candidat = (Candidat)objet;
            if(candidat.decisionJury().equals("Admis") || candidat.decisionJury().equals("Ajourné")){
            String inputDate = candidat.getDateNaissance().toString();
            LocalDate date = LocalDate.parse(inputDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String outputDate = date.format(formatter);
            
            Table head = new Table(3);
            head.setWidth(UnitValue.createPercentValue(100)).setTextAlignment(TextAlignment.CENTER);
            head.addCell(new Cell()
                    .add(new Paragraph().add(new Text(candidat.getNom()+" "+candidat.getPrenom()).setBold().setFontSize(15)))
                    .add(new Paragraph().add("Né le/en "+outputDate+" à "+candidat.getLieuNaissance()))
                    .setTextAlignment(TextAlignment.LEFT)
                    .setWidth(UnitValue.createPercentValue(40)).setBorder(Border.NO_BORDER));
            head.addCell(new Cell().add(new Paragraph()).setWidth(UnitValue.createPercentValue(15)).setBorder(Border.NO_BORDER));
            head.addCell(new Cell()
                    .add(new Paragraph().add("BEPC SESSION "+this.fenetre.getUserSession().getSession().getAnnee()))
                    //.add(new Paragraph().add("Série (option) ".toUpperCase()+candidat.getSerie().getSerieAcronyme()+", "+candidat.getSerie().getDefinition().toUpperCase()))
                    .add(new Paragraph().add("Etablissement : "+candidat.getEtablissementCandidat().getNomStructure())
                    )
                    .add(new Paragraph().add("\n"))
                    .add(new Paragraph().add(this.fenetre.getUserSession().getJury().getJuryLibelle()+"    N°PV : "+candidat.getNumeroPv()).setFontSize(14))
                    .setWidth(UnitValue.createPercentValue(55)).setBorder(Border.NO_BORDER));
            
            Table releve = new Table(11);
            releve.setWidth(UnitValue.createPercentValue(100)).setTextAlignment(TextAlignment.CENTER);
            releve.addHeaderCell(new Cell(1,3).add(new Paragraph("Matières")));
            releve.addHeaderCell(new Cell(1,3).add(new Paragraph("Premier tour")));
            releve.addHeaderCell(new Cell(1,2).add(new Paragraph("Second tour")));
            releve.addHeaderCell(new Cell(1,3).add(new Paragraph("Récapitulatif")));
            
            releve.addHeaderCell(new Cell().add(new Paragraph("Code")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Libelé")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Coéf.")));
            
            releve.addHeaderCell(new Cell().add(new Paragraph("Note/20")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Note pondérée")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Sur")));
            
            releve.addHeaderCell(new Cell().add(new Paragraph("Note/20")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Meilleure note")));
            
            releve.addHeaderCell(new Cell().add(new Paragraph("Note retenue")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Note pondérée")));
            releve.addHeaderCell(new Cell().add(new Paragraph("Sur")));
            
            for(Objet objetCopie : candidat.getCopies()){
                FeuilleComposition copie = (FeuilleComposition)objetCopie;
                releve.addCell(new Cell().add(new Paragraph(copie.getComposition().getEpreuve().getTypeEpreuve().getLibelle())));
                releve.addCell(new Cell().add(new Paragraph(copie.getComposition().getEpreuve().getEpreuveLibelle()).setTextAlignment(TextAlignment.LEFT)));
                releve.addCell(new Cell().add(new Paragraph(copie.getComposition().getEpreuve().getCoefficient().toString())));
                
                releve.addCell(new Cell().add(new Paragraph((copie.getNote() != null)?copie.getNote().toString():"")));
                releve.addCell(new Cell().add(new Paragraph((copie.getNote() != null)?copie.getNotePondere().toString():"")));
                releve.addCell(new Cell().add(new Paragraph(copie.getComposition().getEpreuve().getTotalPondereMax().toString())));
                
                releve.addCell(new Cell().add(new Paragraph()));
                releve.addCell(new Cell().add(new Paragraph()));
                
                releve.addCell(new Cell().add(new Paragraph()));
                releve.addCell(new Cell().add(new Paragraph()));
                releve.addCell(new Cell().add(new Paragraph()));
            }
            releve.addCell(new Cell(2,1).add(new Paragraph().add(this.qrCode())).setBorder(Border.NO_BORDER));
            releve.addCell(new Cell().add(new Paragraph("Totaux : ").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            releve.addCell(new Cell().add(new Paragraph(Double.toString(candidat.getTotalCoef()))).setBorder(Border.NO_BORDER));
                
            releve.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
            Cell c = new Cell().add(new Paragraph(Double.toString(candidat.getTotalPondere())));
            float[] yellow = new float[]{0, 0, 0, 1};
            c.setNextRenderer(new RoundedCellRenderer(c,yellow,false));
            releve.addCell(c);
            releve.addCell(new Cell().add(new Paragraph(Double.toString(candidat.getTotaPondereMax()))).setBorder(Border.NO_BORDER));
                
            releve.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
            releve.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
              
            releve.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
            Cell notep = new Cell().add(new Paragraph("-------"));
            notep.setNextRenderer(new RoundedCellRenderer(notep,yellow,false));
            
            releve.addCell(notep);
            releve.addCell(new Cell(2,1).add(new Paragraph()).setBorder(Border.NO_BORDER));
            
            Cell situationDefinitif = new Cell(1,9).setMargin(10)
                    .add(new Paragraph(new Text(" Situation définifitive :   Totaux : "+candidat.getTotalPondere()+" sur "+candidat.getTotaPondereMax()+"     Moyenne générale : "+candidat.moyenne()+" sur 20")).setBold().setFontSize(15)
                    //.add(new Paragraph(new Text(" Situation définifitive : \t")).setTextAlignment(TextAlignment.CENTER))
                    //.add(new Paragraph(new Text(" Situation définifitive : \t")).setTextAlignment(TextAlignment.RIGHT)
            );
            situationDefinitif.setNextRenderer(new RoundedCellRenderer(situationDefinitif,yellow,false));
            
            releve.addCell(situationDefinitif);
            
            Table foot = new Table(3);
            foot.setWidth(UnitValue.createPercentValue(100)).setTextAlignment(TextAlignment.CENTER);
            foot.addCell(new Cell()
                    .add(new Paragraph().add(new Text("Décision du jury à l'issue de la délibération du premier tour").setUnderline().setFontSize(10)))
                    .add(new Paragraph().add(new Text(candidat.decisionJury()).setBold().setFontSize(14)))
                    //.setTextAlignment(TextAlignment.LEFT)
                    .setWidth(UnitValue.createPercentValue(25)).setBorder(Border.NO_BORDER));
            foot.addCell(new Cell()
                    .add(new Paragraph().add(new Text("NB : Il n'est délivré qu'un seul original de relevé de notes.\n" +
"Il appartient à l'intéressé(e) d'en faire établir des copies certifiées conformes.\n" +
"Le présent relevé ne doit comporter ni rature, ni surcharge, sous peine de nullité.").setFontSize(8).setItalic()))
                    .add(new Paragraph().add(new Text("Fait à ouagadougou le ..................").setBold()))
                    .add(new Paragraph().add(new Text("Le président du jury\n\n\n".concat(this.fenetre.getUserSession().getUser().getPersonnel().getNom()).concat(" ").concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom())).setBold()))
                    .setWidth(UnitValue.createPercentValue(50)).setBorder(Border.NO_BORDER));
            foot.addCell(new Cell()
                    .add(new Paragraph().add(new Text("Décision du jury à l'issue de la délibération du second tour").setUnderline().setFontSize(10)))
                    .add(new Paragraph().add("---------"))
                    .setWidth(UnitValue.createPercentValue(25)).setBorder(Border.NO_BORDER));
            
            
            this.document.add(head);
            this.document.add(releve);
            this.document.add(foot);
            
            i++;
            if(i < this.listeCandidat.size()){
                this.document.add(new AreaBreak());
            }
        }
    }
    }
    
    
    @Override
    public void setTableBody(Table table) {
        
    }
    
}
