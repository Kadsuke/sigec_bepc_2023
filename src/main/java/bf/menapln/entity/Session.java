/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author coulibaly
 */
public class Session extends Objet{
    private Long annee;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateDebutSessNorm;
    private LocalDate dateFinSessNorm;
    private Boolean statutSession;
    private Boolean statutInsc;
    private LocalDate dateDebutInscription;
    private LocalDate dateFindInscrition;
    private Boolean clotureInscription;
    private Double moyAdmission;
    private Double moyRachat;
    private Double moyAdmissible;
    //private Set<ListeSerieEtablissement> listeSerieEtablissements = new HashSet<>();
    
    public Session(){
        super();
    }
    public Session(Map data){
        this.setAnnee(Long.parseLong((String) data.get("annee")))
        .setDateDebut((LocalDate) data.get("dateDebutInsc"))
        .setDateFin((LocalDate) data.get("dateFinInsc"))
        .setDateDebutInscription((LocalDate) data.get("dateDebutInsc"))
        .setDateFindInscrition((LocalDate) data.get("dateFinInsc"))
        .setDateDebutSessNorm((LocalDate) data.get("dateDebutSessNorm"))
        .setDateFinSessNorm((LocalDate) data.get("dateFinSessNorm"))
        .setMoyAdmission(Double.parseDouble((String) data.get("moyAdmission")))
        .setMoyRachat(Double.parseDouble((String) data.get("moyRachat")))
        .setmoyAdmissible(Double.parseDouble((String) data.get("moyAdmissible")));

        
    }
    
    public static Session id(Long id){
        Session session = new Session();
        session.setId(id);
        return session;
    }
    
    public Session(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("session_id"));
        this.setAnnee(rs.getLong("annee"));
        this.setMoyRachat(rs.getDouble("moyRachat"));
        this.setMoyAdmission(rs.getDouble("moyAdmission"));
        this.setmoyAdmissible(rs.getDouble("moyAdmissible"));
    }
    
    public Long getAnnee() {
        return annee;
    }

    public Session annee(Long annee) {
        this.annee = annee;
        return this;
    }

    public Session setAnnee(Long annee) {
        this.annee = annee;
        return this;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Session dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public Session setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Session dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public Session setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public LocalDate getDateDebutSessNorm() {
        return dateDebutSessNorm;
    }

    public Session setDateDebutSessNorm(LocalDate dateDebutSessNorm) {
        this.dateDebutSessNorm = dateDebutSessNorm;
        return this;
    }

    public LocalDate getDateFinSessNorm() {
        return dateFinSessNorm;
    }

    public Session setDateFinSessNorm(LocalDate dateFinSessNorm) {
        this.dateFinSessNorm = dateFinSessNorm;
        return this;
    }

    public Boolean getStatutSession() {
        return statutSession;
    }

    public void setStatutSession(Boolean statutSession) {
        this.statutSession = statutSession;
    }

    public Boolean getStatutInsc() {
        return statutInsc;
    }

    public void setStatutInsc(Boolean statutInsc) {
        this.statutInsc = statutInsc;
    }
    
    
    
    public LocalDate getDateDebutInscription() {
        return dateDebutInscription;
    }

    public Session dateDebutInscription(LocalDate dateDebutInscription) {
        this.dateDebutInscription = dateDebutInscription;
        return this;
    }

    public Session setDateDebutInscription(LocalDate dateDebutInscription) {
        this.dateDebutInscription = dateDebutInscription;
        return this;
    }

    public LocalDate getDateFindInscrition() {
        return dateFindInscrition;
    }

    public Session dateFindInscrition(LocalDate dateFindInscrition) {
        this.dateFindInscrition = dateFindInscrition;
        return this;
    }

    public Session setDateFindInscrition(LocalDate dateFindInscrition) {
        this.dateFindInscrition = dateFindInscrition;
        return this;
    }

    

    public Boolean isClotureInscription() {
        return clotureInscription;
    }

    public Session clotureInscription(Boolean clotureInscription) {
        this.clotureInscription = clotureInscription;
        return this;
    }

    public void setClotureInscription(Boolean clotureInscription) {
        this.clotureInscription = clotureInscription;
    }

    public Double getMoyAdmission() {
        return moyAdmission;
    }

    public Session setMoyAdmission(Double moyAdmission) {
        this.moyAdmission = moyAdmission;
        return this;
    }

    public Double getMoyRachat() {
        return moyRachat;
    }

    public Session setMoyRachat(Double moyRachat) {
        this.moyRachat = moyRachat;
        return this;
    }

    public Double getMoyAdmissible() {
        return moyAdmissible;
    }

    public Session setmoyAdmissible(Double moyAdmissible) {
        this.moyAdmissible = moyAdmissible;
        return this;
    }
    @Override
    public String toString() {
        return this.getAnnee().toString();
    }
}
