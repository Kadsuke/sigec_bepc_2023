/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Jury extends Objet{
    private Centre centre;
    private Localite centreExamen;
    private String juryLibelle;
    private Long juryCapacite;
    private TourComposition etape;
    private Session session;
    
    public Jury(){
        super();
        this.etat = false;
    }
    
    public Jury(Map data){
        this.setJuryLibelle((String) data.get("juryLibelle"));
        this.setJuryCapacite(Long.parseLong((String) data.get("juryCapacite")));
        this.setCentre(new Centre());
        this.getCentre().setEtatblissement(Etablissement.id(Long.parseLong((String) data.get("etablissement_id"))));
        this.setCentreExamen(Localite.id(Long.parseLong((String) data.get("centreExamen_id"))));
        this.getCentre().setSession(Session.id(Long.parseLong((String) data.get("session_id"))));
    }
    
    public static Jury id(Long id){
        Jury jury = new Jury();
        jury.setId(id);
        return jury;
    }
    
    public Jury(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("jury_id"));
        this.setJuryLibelle(rs.getString("juryLibelle"));
        this.setJuryCapacite(rs.getLong("juryCapacite"));
    }
    
    public String getJuryLibelle() {
        return juryLibelle;
    }

    public void setJuryLibelle(String juryLibelle) {
        this.juryLibelle = juryLibelle;
    }

    public Long getJuryCapacite() {
        return juryCapacite;
    }

    public void setJuryCapacite(Long juryCapacite) {
        this.juryCapacite = juryCapacite;
    }

    public TourComposition getEtape() {
        return etape;
    }

    public void setEtape(TourComposition etape) {
        this.etape = etape;
    }

    public Localite getCentreExamen() {
        return centreExamen;
    }

    public void setCentreExamen(Localite centreExamen) {
        this.centreExamen = centreExamen;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
    
    @Override
    public String toString(){
        return this.juryLibelle;
    }
    
}
