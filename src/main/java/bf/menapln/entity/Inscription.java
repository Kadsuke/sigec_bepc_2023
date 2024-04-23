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
public class Inscription extends Objet{
    private Session session;
    private Candidat candidat;
    private Map<Long,Epreuve> dispenses;
    
    public Inscription(){
        super();
    }
    
    public Inscription(Map data){
        this.setSession(Session.id(Long.parseLong((String) data.get("session_id"))));
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Map<Long, Epreuve> getDispenses() {
        return dispenses;
    }

    public void setDispenses(Map<Long, Epreuve> dispenses) {
        this.dispenses = dispenses;
    }
    
   public boolean isDispense(Epreuve epreuve){
       return (this.dispenses.containsKey(epreuve.getId()))? true : false;
   }
}
