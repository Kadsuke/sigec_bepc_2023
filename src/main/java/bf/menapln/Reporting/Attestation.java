package bf.menapln.Reporting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import bf.menapln.entity.Resultat;
import bf.menapln.ihm.Fenetre;

public class Attestation extends PDF{

    public Attestation(Fenetre fenetre) {
        super(fenetre);
        this.fonction[0] = "";
        
        this.signataire[0] = "";
       // this.signataire[0] = this.signataire[0].concat(" ");
        //this.signataire[0] = this.signataire[0].concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        //this.setBottomMargin(300);
        //this.setTopMargin(300);
        this.setQrcodeSize(0f);

        //TODO Auto-generated constructor stub
    }

    @Override
    public void setTableBody(Table table) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTableBody'");
    }

    @Override
    public void body() {
        for(Object resultat : this.listeCandidat){
            resultat = (Resultat)resultat;
            String inputDate = (((Resultat)resultat).getCandidat().getDateNaissance().toString());
        LocalDate date = LocalDate.parse(inputDate);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String outputDate = date.format(formatter);
        String outputDat = today.format(formatter);
        this.document.add(this.title.setTextAlignment(TextAlignment.CENTER));
        float offSet = 36;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;
        if((((Resultat)resultat).getCandidat().getSexe().equals("Masculin"))){
            this.document.add(new Paragraph().add("Monsieur "+(((Resultat)resultat).getCandidat().getNom()+" "+((Resultat)resultat).getCandidat().getPrenom())));  
        }else if((((Resultat)resultat).getCandidat().getSexe().equals("Feminin"))){
            this.document.add(new Paragraph().add("Mademoiselle "+(((Resultat)resultat).getCandidat().getNom()+" "+((Resultat)resultat).getCandidat().getPrenom())));  
        }
        this.document.add(new Paragraph().add("Née le/en "+outputDate));
        this.document.add(new Paragraph().add("à "+(((Resultat)resultat).getCandidat().getLieuNaissance())));
        this.document.add(new Paragraph().add("a subi avec succès les épreuves de l'examen du \n").setBold());
        this.document.add(new Paragraph().add(" BREVET D'ETUDES DU PREMIER CYCLE (BEPC)").setBold().setFontSize(15).setItalic());
        this.document.add(new Paragraph().add("Session de 2023 - Centre de "+((Resultat)resultat).getCandidat().getCentreExamen().getNomLoclite()));  
        this.document.add(new Paragraph().add("Jury de "+this.fenetre.getUserSession().getJury().getJuryLibelle()));
        this.document.add(new Paragraph().add("N° PV: "+((Resultat)resultat).getCandidat().getNumeroPv().toString()));
        this.document.add(new Paragraph().add("En foi de quoi, la présente attestation lui est délivrée pour servir et valoir ce que de droit."));
        String president = this.fenetre.getUserSession().getUser().getPersonnel().getNom();
        president = president.concat(" ");
        president = president.concat(this.fenetre.getUserSession().getUser().getPersonnel().getPrenom());
        Table foot = new Table(2);
            foot.setWidth(UnitValue.createPercentValue(100)).setTextAlignment(TextAlignment.CENTER);
            foot.addCell(new Cell()
                    .setWidth(UnitValue.createPercentValue(55)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
            foot.addCell(new Cell()
                    .add(new Paragraph().add(((Resultat)resultat).getCandidat().getCentreExamen().getNomLoclite() +" "+ outputDat))
                    .add(new Paragraph().add("P. le Directeur Général \n"))
                    .add(new Paragraph().add("des Examens et Concours,Le président du jury"))
                    .add(new Paragraph().add("\n\n\n\n"))
                    .add(new Paragraph().add(president))
                    .setWidth(UnitValue.createPercentValue(45)).setBorder(Border.NO_BORDER));
            this.document.add(foot);
        
            this.document.add(new Paragraph().add("NB:\n").setBold().setTextAlignment(TextAlignment.LEFT));
            this.document.add(new Paragraph().add("Valable jusqu'au 31 décembre 2024\n").setBold().setTextAlignment(TextAlignment.LEFT));
            this.document.add(new Paragraph().add("a) Il n'est délivré qu'une seule attestation.\n"+"Il appartient au lauréat d'en faire\n"+"des photocopies légalisées;\n").setFontSize(8));
            this.document.add(new Paragraph().add("b) Cette attestation ne doit comporter ni rature\n"+"ni surcharge sous peine de nullité;\n").setFontSize(8));
            this.document.add(new Paragraph().add("c) L'original de la présente attestation devra être\n"+"présenté pour le retrait du diplôme.\n").setFontSize(8));
    

        
        }
        
      }
    
}
