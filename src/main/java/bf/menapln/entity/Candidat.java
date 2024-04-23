/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import bf.menapln.enumeration.ConcoursRatache;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Candidat extends Objet{
    private Localite secteur;
    private Structure etablissementCandidat;
    
    private Type typeCandidat;
    private Type typeInscription;
    private Serie serie;
    private Localite centreExamen;
    private Type concoursRatache;
    
    private String iue;
    private String nom;
    private String prenom;
    private String telephone;
    private String dernierDiplome;
    private Long anneeDernierDiplome;
    
    private String sexe;
    private Pays paysNaissance;
    private Pays nationalite;
    
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private Long numeroActeNaissance;
    private String lienActeNaissance;
    private String lienPhoto;

    private String situationHandicap;
    private NatureHandicap natureHandicap;
    private Type typeHandicap;
    private Type prescriptionHandicap;
    private String sport;
    private Integer numeroPv;
    
    private Inscription inscription;
    
    private List<Objet> copies;
    
    private Double totalPondere = 0.0;
    private int totalCoefDispense = 0;
    Boolean elimine = false;
    
    
    private boolean inJury;

    
    
    
    public Candidat(){
        super();
    }
    
    public Candidat(Map data){
        this.setIue((String) data.get("iue"));
        this.setNom((String) data.get("nom"));
        this.setPrenom((String) data.get("prenom"));
        this.setSexe((String) data.get("sexe"));
        this.setDateNaissance((LocalDate) data.get("dateNaissance"));
        this.setLieuNaissance((String) data.get("lieuNaissance"));
        this.setNumeroActeNaissance(Long.parseLong((String) data.get("numeroActeNaissance")));
        this.setTelephone((String) data.get("telephone"));
        this.setSecteur(Localite.id(Long.parseLong((String) data.get("secteur_id"))));
        this.setEtablissementCandidat( Etablissement.id(Long.parseLong((String) data.get("etablissement_id"))));
        this.setTypeCandidat(Type.id(Long.parseLong((String) data.get("typeCandidat_id"))));
        this.setTypeInscription(Type.id(Long.parseLong((String) data.get("typeInscription_id"))));
        this.setSerie(Serie.id(Long.parseLong((String) data.get("serie_id"))));
        this.setPaysNaissance(Pays.id(Long.parseLong((String) data.get("paysNaissance_id"))));
        this.setNationalite(Pays.id(Long.parseLong((String) data.get("nationalite_id"))));
        this.setDernierDiplome((String) data.get("dernierDiplome"));
        this.setAnneeDernierDiplome(Long.parseLong((String) data.get("anneeDernierDiplome")));
        this.setSport((String) data.get("sport"));
        this.setCentreExamen(Localite.id(Long.parseLong((String) data.get("centreExamen_id"))));
        this.setConcoursRatache(Type.id(Long.parseLong((String)data.get("concours_id"))));

    }
    
    public Candidat(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("candidat_id"));
        this.setIue(rs.getString("iue"));
        this.setNom(rs.getString("nom"));
        this.setPrenom(rs.getString("prenom"));
        this.setSexe(rs.getString("sexe"));
        this.setTelephone(rs.getString("telephone"));
        this.setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
        this.setNumeroPv(rs.getInt("numeroPV"));
        this.setSport(rs.getString("sport"));
        this.setEtablissementCandidat(Structure.id(rs.getLong("etablissement_id")));
        this.getEtablissementCandidat().setNomStructure(rs.getString("nomStructure"));
        this.setLieuNaissance(rs.getString("lieuNaissance"));
        this.setCentreExamen(Localite.id(rs.getLong("centreExamen_id")));
        this.getCentreExamen().setNomLoclite(rs.getString("nomLocalite"));
    }
    
    public static Candidat id(Long id){
        Candidat c = new Candidat();
        c.setId(id);
        return c;
    }
    
    
    public Localite getCentreExamen() {
        return centreExamen;
    }

    public void setCentreExamen(Localite centreExamen) {
        this.centreExamen = centreExamen;
    }

    public Type getConcoursRatache() {
        return concoursRatache;
    }

    public void setConcoursRatache(Type concoursRatache) {
        this.concoursRatache = concoursRatache;
    }

    public String getDernierDiplome() {
        return dernierDiplome;
    }

    public void setDernierDiplome(String dernierDiplome) {
        this.dernierDiplome = dernierDiplome;
    }

    public Long getNumeroActeNaissance() {
        return numeroActeNaissance;
    }

    public void setNumeroActeNaissance(Long numeroActeNaissance) {
        this.numeroActeNaissance = numeroActeNaissance;
    }

    public Type getTypeHandicap() {
        return typeHandicap;
    }

    public void setTypeHandicap(Type typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public String getSport() {
        return sport;
    }

    
    public void setSport(String sport) {
        this.sport = sport;
    }

    public Type getTypeCandidat() {
        return typeCandidat;
    }

    public void setTypeCandidat(Type typeCandidat) {
        this.typeCandidat = typeCandidat;
    }

    public Type getTypeInscription() {
        return typeInscription;
    }

    public void setTypeInscription(Type typeInscription) {
        this.typeInscription = typeInscription;
    }

    

    public Long getAnneeDernierDiplome() {
        return anneeDernierDiplome;
    }

    public void setAnneeDernierDiplome(Long anneeDernierDiplome) {
        this.anneeDernierDiplome = anneeDernierDiplome;
    }

    public Pays getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(Pays paysNiassance) {
        this.paysNaissance = paysNiassance;
    }

    public Pays getNationalite() {
        return nationalite;
    }

    public void setNationalite(Pays nationalite) {
        this.nationalite = nationalite;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public Candidat nom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getIue() {
        return iue;
    }

    public void setIue(String identifiant) {
        this.iue = identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Candidat prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public Candidat sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public Candidat telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public Candidat lieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSituationHandicap() {
        return situationHandicap;
    }

    public Candidat situationHandicap(String situationHandicap) {
        this.situationHandicap = situationHandicap;
        return this;
    }

    public void setSituationHandicap(String situationHandicap) {
        this.situationHandicap = situationHandicap;
    }


    public Localite getSecteur() {
        return secteur;
    }

    public Candidat secteur(Localite localite) {
        this.secteur = localite;
        return this;
    }

    public void setSecteur(Localite localite) {
        this.secteur = localite;
    }

    public Structure getEtablissementCandidat() {
        return etablissementCandidat;
    }

    public Candidat etablissementCandidat(Etablissement etablissement) {
        this.etablissementCandidat = etablissement;
        return this;
    }

    public void setEtablissementCandidat(Structure etablissement) {
        this.etablissementCandidat = etablissement;
    }

    public Serie getSerie() {
        return serie;
    }

    public Candidat serie(Serie serie) {
        this.serie = serie;
        return this;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    // Debut Nature Handicap
    public NatureHandicap getNatureHandicap() {
        return natureHandicap;
    }

    public Candidat natureHandicap(NatureHandicap natureHandicap) {
        this.natureHandicap = natureHandicap;
        return this;
    }

    public void setNatureHandicap(NatureHandicap natureHandicap) {
        this.natureHandicap = natureHandicap;
    }
    
    public Type getPrescriptionHandicap() {
        return prescriptionHandicap;
    }

    public Candidat prescriptionHandicap(Type prescriptionHandicap) {
        this.prescriptionHandicap = prescriptionHandicap;
        return this;
    }

    public void setPrescriptionHandicap(Type prescriptionHandicap) {
        this.prescriptionHandicap = prescriptionHandicap;
    }

    public String getLienActeNaissance() {
        return lienActeNaissance;
    }

    public void setLienActeNaissance(String lienActeNaissance) {
        this.lienActeNaissance = lienActeNaissance;
    }

    public String getLienPhoto() {
        return lienPhoto;
    }

    public void setLienPhoto(String lienPhoto) {
        this.lienPhoto = lienPhoto;
    }

    public boolean isInJury() {
        return inJury;
    }

    public void setInJury(boolean inJury) {
        this.inJury = inJury;
    }

    public Integer getNumeroPv() {
        return numeroPv;
    }

    public void setNumeroPv(Integer numeroPv) {
        this.numeroPv = numeroPv;
    }

    public List<Objet> getCopies() {
        return copies;
    }

    public void setCopies(List<Objet> copies) {
        this.copies = copies;
    }
    
    public void addCopie(Objet copie){
        this.copies.add(copie);
    }

    public Double getTotalPondere() {
        return totalPondere;
    }

    public void setTotalPondere(Double totalPondere) {
        System.out.println(totalPondere);
        this.totalPondere += totalPondere;
    }
    
    public Double getTotaPondereMax(){
        return (this.getTotalCoef()*20.0);
    }
    
    public Double moyenne(){
        DecimalFormat df = new DecimalFormat("0.00");
        String moyToString = df.format((double)this.getTotalPondere()/this.getTotalCoef());
        return Double.parseDouble(moyToString.replace(",", "."));
      // return (this.getTotalPondere()/this.getTotalCoef());

    }
    
    public String decisionJury(){
        
        return this.etatRachat(this.getInscription().getSession().getMoyRachat(), this.moyenne(), this.getInscription().getSession().getMoyAdmissible());
    }
    
    public String etatRachat(Double moyRachat,Double moyenne,Double moyAdmissibilite){
        if(this.getElimine()){
            return "Radié";
        }else if(moyenne >=10){
            return "Admis";
        }else if(moyenne >= 9.95){
            return "Rachat";
        }else if(moyenne >=7.0){
            return "Admissible";
        }
        else{
            return "Ajourné";
        }

    }
    public Integer codeDecisionJury(){
        switch(this.decisionJury()){
            case "Admissible":
                return 2;
            case "Ajourné":
                return 3;
            case "Rachat":
                return 4;
            case "Radié":
                return 5;
            default:
                return 1;
        }
    }
    
    public String mention(){
        return  (this.moyenne() < this.getInscription().getSession().getMoyAdmission())?"Insuffisant":(this.moyenne() < 12)?"Passable":(this.moyenne() < 14)?"A. Bien":(this.moyenne() < 16)?"Bien":(this.moyenne() < 18)?"Très bien":"Excellent";
    }
    
    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        inscription.setCandidat(this);
        this.inscription = inscription;
    }
    
    public boolean isApte(){
        return (this.sport.equals("Apte"))?true:false;
    }

    public Double getTotalCoefDispense() {
        return totalCoefDispense*1.0;
    }
    
    
    
    public void setTotalCoefDispense(int totalCoefDispense) {
        this.totalCoefDispense += totalCoefDispense;
    }
    
    public Double getTotalCoef(){
        return this.getSerie().getTotalCoef()-this.getTotalCoefDispense()*1.0;
    }

    public Boolean getElimine() {
        return elimine;
    }

    public void setElimine(Boolean elimine) {
        this.elimine = elimine;
    }
    
    
    
    
}
