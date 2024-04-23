package bf.menapln.Reporting;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import bf.menapln.entity.Resultat;
import bf.menapln.ihm.Fenetre;

public class Releve extends PDFL{

    public Releve(Fenetre fenetre) {
        super(fenetre);
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
        this.document.add(this.title.setTextAlignment(TextAlignment.CENTER));
        float offSet = 36;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;
        if((((Resultat)resultat).getCandidat().getSexe() == "Masculin")){
            this.document.add(new Paragraph().add("Monsieur "+(((Resultat)resultat).getCandidat().getNom()+" "+((Resultat)resultat).getCandidat().getPrenom())));  
        }else{
            this.document.add(new Paragraph().add("Mademoiselle "+(((Resultat)resultat).getCandidat().getNom()+" "+((Resultat)resultat).getCandidat().getPrenom())));  
        }
        //this.document.add(new Paragraph().add("Née le"+(((Resultat)resultat).getCandidat().getDateNaissance().toString())));
        this.document.add(new Paragraph().add("Née le 25-08-2002"));
        this.document.add(new Paragraph().add("à "+(((Resultat)resultat).getCandidat().getLieuNaissance())));
        this.document.add(new Paragraph().add("\n"));
        this.document.add(new Paragraph().add("a subi avec succès les épreuves de l'examen du \n").setBold());
        this.document.add(new Paragraph().add(" BREVET D'ETUDES DU PREMIER CYCLE (BEPC)").setBold().setFontSize(15).setItalic());

        this.document.add(new Paragraph().add("Session de 2023 - Centre de OUAGADOUGOU"));  
        //(((Resultat)resultat).getCandidat().getCentreExamen().toString())))
        this.document.add(new Paragraph().add("Jury de " +fenetre.getUserSession().getJury().getJuryLibelle()));
        this.document.add(new Paragraph().add("N° PV: "+((Resultat)resultat).getCandidat().getNumeroPv().toString()));
        this.document.add(new Paragraph().add("En foi de quoi, la présente attestation lui est délivrée pour servir et valoir ce que de droit."));
        this.document.add(new Paragraph().add("\n"));
        this.document.add(new Paragraph().add("\tOUAGADOUGOU, le 10/05/2023").setTextAlignment(TextAlignment.RIGHT));
        this.document.add(new Paragraph().add("\tP. le Directeur Général \n").setTextAlignment(TextAlignment.RIGHT));
        this.document.add(new Paragraph().add("des Examens et Concours, le Président du jury").setTextAlignment(TextAlignment.RIGHT));
        this.document.add(new Paragraph().add("TAO SOULEYMANE\n").setTextAlignment(TextAlignment.RIGHT));
        this.document.add(new Paragraph().add("NB:\n").setBold().setTextAlignment(TextAlignment.LEFT));
        this.document.add(new Paragraph().add("Valable jusqu'au 31 décembre 2024\n").setBold().setTextAlignment(TextAlignment.LEFT));
        this.document.add(new Paragraph().add("a) Il n'est délivré qu'une seule attestation.\n"+"Il appartient au lauréat d'en faire\n"+"des photocopies légalisées;\n").setFontSize(10));
        this.document.add(new Paragraph().add("b) Cette attestation ne doit comporter ni rature\n"+"ni surcharge sous peine de nullité;\n").setFontSize(10));
        this.document.add(new Paragraph().add("c) L'original de la présente attestation devra être\n"+"présenté pour le retrait du diplôme.\n").setFontSize(10));


        
        }
        
      }

    @Override
    public void footer() {
        // TODO Auto-generated method stub
    }
    
}
