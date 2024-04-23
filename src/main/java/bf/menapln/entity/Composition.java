/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Composition extends Objet{
    private Session session;
    private Jury jury;
    private Epreuve epreuve;
    private Type tourComposition;
    private LocalDate dateComposition;
    private LocalTime heureDebutComposition;
    private LocalTime heureFinComposition;
    private boolean incident;
    private boolean fraude;
    private String observation;
    private List<Objet> feuilles = new ArrayList();
    private List<Objet> candidats = new ArrayList();
    
    public Composition(){
        super();
    }
    
    public Composition(Map data){
        this.setSession((Session) data.get("session"));
        this.setJury((Jury) data.get("jury"));
        this.setTourComposition((Type) data.get("tourComposition"));
        this.setEpreuve((Epreuve) data.get("epreuve"));
        this.setDateComposition((LocalDate) data.get("dateComposition"));
        this.setHeureDebutComposition((LocalTime) data.get("heureDebut"));
        this.setHeureFinComposition((LocalTime) data.get("heureFin"));
        this.setFraude((boolean) data.get("fraude"));
        this.setIncident((boolean) data.get("incident"));
        this.setObservation((String) data.get("observation"));
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Jury getJury() {
        return jury;
    }

    public void setJury(Jury jury) {
        this.jury = jury;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Type getTourComposition() {
        return tourComposition;
    }

    public void setTourComposition(Type tourComposition) {
        this.tourComposition = tourComposition;
    }

    public LocalDate getDateComposition() {
        return dateComposition;
    }

    public void setDateComposition(LocalDate dateComposition) {
        this.dateComposition = dateComposition;
    }

    public LocalTime getHeureDebutComposition() {
        return heureDebutComposition;
    }

    public void setHeureDebutComposition(LocalTime heureDebutComposition) {
        this.heureDebutComposition = heureDebutComposition;
    }

    public LocalTime getHeureFinComposition() {
        return heureFinComposition;
    }

    public void setHeureFinComposition(LocalTime heureFinComposition) {
        this.heureFinComposition = heureFinComposition;
    }

    public boolean isIncident() {
        return incident;
    }

    public void setIncident(boolean incident) {
        this.incident = incident;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    public boolean isFraude() {
        return fraude;
    }

    public void setFraude(boolean fraude) {
        this.fraude = fraude;
    }

    public List<Objet> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Objet> candidats) {
        this.candidats = candidats;
    }
    
    public void addCandidat(Candidat c){
        this.candidats.add(c);
    }

    public List<Objet> getFeuilles() {
        return feuilles;
    }

    public void setFeuilles(List<Objet> feuilles) {
        this.feuilles = feuilles;
    }
    
    public void addFeuille(FeuilleComposition f){
        this.feuilles.add(f);
    }
    
    public void createFeuille(){
        if(!this.candidats.isEmpty()){
            for(Objet candidat : candidats){
                FeuilleComposition feuille = new FeuilleComposition();
                feuille.setCandidat((Candidat)candidat);
                feuille.setComposition(this);
                //feuille.setPresent(true);
                this.addFeuille(feuille);
            }
        }
    }
}
