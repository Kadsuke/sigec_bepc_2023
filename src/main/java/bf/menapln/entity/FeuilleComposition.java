/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class FeuilleComposition extends Objet{
    
    private Composition composition;
    private Personnel correcteur;
    private Candidat candidat;
    private boolean present = true;
    private String anonymat;
    private Double note;
    private boolean verouille;
    private boolean verouilleAnonymat;
    private Integer cycle = 0;
    private boolean anonymous;
    private boolean reparti;

    
    public FeuilleComposition(){
        super();
    }
    
    public FeuilleComposition(Map data){
        this.setComposition((Composition) data.get("composition"));
        this.setCandidat((Candidat) data.get("candidat"));
        this.setPresent((boolean) data.get("presence"));
    }
    
    
    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public boolean isPresent() {
        //this.present = true;
        return present;
    }

    public boolean isPresentTrue() {
        return true;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public String getAnonymat() {
        return anonymat;
    }

    public void setAnonymat(String anonymat) {
        this.anonymat = anonymat;
    }

    public Personnel getCorrecteur() {
        return correcteur;
    }

    public void setCorrecteur(Personnel correcteur) {
        this.correcteur = correcteur;
    }

    public Double getNote() {
        
        return this.note;
    }

    public void setNote(Double note) {
        if(this.composition.getEpreuve().getEpreuveLibelle().equals("Langue") || this.composition.getEpreuve().getEpreuveLibelle().equals("Expression Ecrite") || this.composition.getEpreuve().getEpreuveLibelle().equals("Français")){
                if(note>= 0 && note <= 20){
                    this.note = note;
                    this.candidat.setTotalPondere(this.getNotePondere());
                }else{
                    this.note = null;
            }
        }else{
            if (note % 1 == 0 || note % 1 == 0.5){
                if(note>= 0 && note <= 20){
                    this.note = note;
                    this.candidat.setTotalPondere(this.getNotePondere());
                }else{
                    this.note = null;
                }
            }else{
                this.note = null;
            }
        }
    }
    
    public Double getNotePondere(){
        return (this.note != null)?this.note*this.composition.getEpreuve().getCoefficient().intValue():null;
    }

    public boolean isVerouille() {
        return verouille;
    }

    public void setVerouille(boolean verouille) {
        this.verouille = verouille;
    }

    public boolean isAnonymatVerouille() {
        return verouilleAnonymat;
    }

    public void setAnonymatVerouille(boolean verouilleAnonymat) {
        this.verouilleAnonymat = verouilleAnonymat;
    }

    public static double arrondir(double nombre) {
        return Math.round(nombre);
    }

    public void setCorrecteur(String value) {
    }

    
    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
        if(this.cycle >= 2){
            this.setAnonymous(true);
        }
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public boolean isReparti() {
        return reparti;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
    public void setReparti(boolean reparti) {
        this.reparti = reparti;
    }
     public String etatAnonymat(){
        String etat = null;
        if(this.isAnonymous()){
            if(this.getCycle() >= 2){
                etat = "Enregistré";
            }else{
                etat = "Non enregistré";
            }
         }
        return etat;
     }

     public String etatReparti(){
        String etat = null;
        if(this.isReparti()){
            if(this.getCycle() >= 3){
                etat = "Enregistré";
            }else{
                etat = "Non enregistré";
            }
         }
        return etat;
    }
    
}
