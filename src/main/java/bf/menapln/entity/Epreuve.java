/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kadsuke
 */
public class Epreuve extends Objet{
    private String epreuveLibelle;
    private String epreuveAcronyme;
    private Long coefficient;
    private LocalTime duree;
    private boolean estComposeSecTour;
    private boolean estComposePreTour;
    private boolean estRachetable;
    private boolean estTypeFrancais;
    private Type typeEpreuve;
    private Session session;
    private List<Objet> series = new ArrayList<Objet>();
    private List<Epreuve> epreuvesLie = new ArrayList<Epreuve>();
    private Double note;
    
    public Epreuve(){
        super();
    }
    
    public Epreuve(Map data){
        this.setEpreuveLibelle((String) data.get("epreuveLibelle"))
                .setEpreuveAcronyme((String) data.get("epreuveAcronyme"));
        if(data.containsKey("coefficient")){
            this.setCoefficient(Long.parseLong((String) data.get("coefficient")))
                    .setDuree(LocalTime.parse((CharSequence) data.get("duree")))
                    .setEstComposeSecTour((boolean) data.get("estComposeSecTour"))
                    .setEstComposePreTour((boolean) data.get("estComposePreTour"))
                    .setEstRachetable((boolean) data.get("estRachetable"))
                    .setEstTypeFrancais((boolean) data.get("estTypeFrancais"))
                    .setSession(Session.id(Long.parseLong((String) data.get("session_id"))))
                    .setTypeEpreuve(Type.id(Long.parseLong((String) data.get("typeEpreuve_id"))));
            this.setId(Long.parseLong((String) data.get("epreuve_id")));
            
        }
    }
    
    public Epreuve(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("epreuve_id"));
        this.setEpreuveLibelle(rs.getString("epreuveLibelle"));
        this.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
       // this.setCoefficient(rs.getLong("coefficient"));
    }

    public Epreuve(ResultSet rs,int i) throws SQLException{
        this.setId(rs.getLong("epreuve_id"));
        this.setEpreuveLibelle(rs.getString("epreuveLibelle"));
        this.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
        }     
    
    public static Epreuve id(Long id){
        Epreuve e = new Epreuve();
        e.setId(id);
        return e;
    }
    
    public String getEpreuveLibelle() {
        return epreuveLibelle;
    }

    public Epreuve setEpreuveLibelle(String epreuveLibelle) {
        this.epreuveLibelle = epreuveLibelle;
        return this;
    }

    public String getEpreuveAcronyme() {
        return epreuveAcronyme;
    }

    public Epreuve setEpreuveAcronyme(String epreuveAcronyme) {
        this.epreuveAcronyme = epreuveAcronyme;
        return this;
    }

    public List<Objet> getSeries() {
        return series;
    }

    public Epreuve setSeries(List<Objet> series) {
        this.series = series;
        return this;
    }
    
    public Epreuve addSerie(Serie serie){
        this.series.add(serie);
        return this;
    }

    public Long getCoefficient() {
        return coefficient;
    }

    public Epreuve setCoefficient(Long coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public Long getTotalPondereMax(){
        return this.getCoefficient()*20;
    }

    public Epreuve setDuree(LocalTime duree) {
        this.duree = duree;
        return this;
    }

    public boolean EstComposeSecTour() {
        return estComposeSecTour;
    }

    public Epreuve setEstComposeSecTour(boolean estComposeSecTour) {
        this.estComposeSecTour = estComposeSecTour;
        return this;
    }
    
     public boolean EstComposePreTour() {
        return estComposePreTour;
    }

    public Epreuve setEstComposePreTour(boolean estComposePreTour) {
        this.estComposePreTour = estComposePreTour;
        return this;
    }
    
     public boolean EstRachetable() {
        return estRachetable;
    }

    public Epreuve setEstRachetable(boolean estRachetable) {
        this.estRachetable = estRachetable;
        return this;
    }

    public boolean getEstTypeFrancais() {
        return estTypeFrancais;
    }

    public Epreuve setEstTypeFrancais(boolean estTypeFrancais) {
        this.estTypeFrancais = estTypeFrancais;
        return this;
    }

    public Type getTypeEpreuve() {
        return typeEpreuve;
    }

    public Epreuve setTypeEpreuve(Type typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public Epreuve setSession(Session session) {
        this.session = session;
        return this;
    }
    
    public String seriesToString(){
        String str = "";
        int i = 0;
        for(Objet serie : this.series){
            str += (i == 0)?"":";";
            str += ((Serie)serie).getSerieLibelle();
            i++;
        }
        return str;
    }
    
    
    public List<Epreuve> getEpreuvesLie() {
        return epreuvesLie;
    }

    public void setEpreuvesLie(List<Epreuve> epreuvesLie) {
        this.epreuvesLie = epreuvesLie;
    }

    public void addEpreuve(Epreuve epreuve){
        this.epreuvesLie.add(epreuve);
    }

    
    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Double moyenneEpreuveLie(){
        Double moy=0.0;
        for (Epreuve epreuve : epreuvesLie) {
            moy += epreuve.getCoefficient()*epreuve.getNote()/5;
        }
        return moy;
    }

    public Double maxMoyenne(){
      return Math.max(this.note, this.moyenneEpreuveLie());
    }

    @Override
    public String toString(){
        return this.epreuveLibelle;
    }
    
}
