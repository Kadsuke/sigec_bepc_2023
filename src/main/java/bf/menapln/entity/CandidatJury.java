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
public class CandidatJury extends Objet{
    private Session session;
    private Jury jury;
    private CentreSecondaire centreSecondaire;
    private Candidat candidat;
    
    private boolean present = true;
    
    public CandidatJury(){
        super();
    }
    
    public CandidatJury(Map data){
        this.setSession((Session) data.get("session"));
        this.setJury((Jury) data.get("jury"));
        this.setCandidat((Candidat) data.get("candidat"));
        if(data.containsKey("centreSecondaire")){
            this.setCentreSecondaire((CentreSecondaire) data.get("centreSecondaire"));

        }else{
            this.setCentreSecondaire(null);
        }
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

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        if(this.jury != null){
            candidat.setInJury(true);
        }
        this.candidat = candidat;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public CentreSecondaire getCentreSecondaire() {
        return centreSecondaire;
    }

    public void setCentreSecondaire(CentreSecondaire centreSecondaire) {
        this.centreSecondaire = centreSecondaire;
    }
    
    
}
