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
public class Centre extends Objet{
    private Etablissement etatblissement;
    private Session session;
    private Long capacite;

    public Centre(){
        super();
    }
    
    public Centre(Map data){
        this.setEtatblissement((Etablissement) data.get("etablissement"));
        this.setSession((Session) data.get("session"));
        this.setCapacite((Long) data.get("capacite"));
    }
    
    public Etablissement getEtatblissement() {
        return etatblissement;
    }

    public void setEtatblissement(Etablissement etatblissement) {
        etatblissement.setCentre(this);
        this.etatblissement = etatblissement;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getCapacite() {
        return capacite;
    }

    public void setCapacite(Long capacite) {
        this.capacite = capacite;
    }

/*     @Override
    public String toString() {
        return "Centre [etatblissement=" + etatblissement + ", session=" + session + ", capacite=" + capacite + "]";
    } */
    
 
    @Override
    public String toString(){
        return ""+etatblissement;

    }
}
