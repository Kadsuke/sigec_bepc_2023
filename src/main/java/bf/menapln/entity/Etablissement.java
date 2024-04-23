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
public class Etablissement extends Structure{

    private Type statut;
    private Type offreEnseignement;
    private Centre centre;
    private boolean definedAsCentre = false;

    public Etablissement(){
        super();
        this.centre = new Centre();
    }
    
    public Etablissement(Map data){
        super(data);
        this.setStatut(Type.id(Long.parseLong((String) data.get("statutEtablissement"))))
            .setOffreEnseignement(Type.id(Long.parseLong((String) data.get("offreEnseignement"))));
    }
    
    public Etablissement(ResultSet rs) throws SQLException{
        super(rs);
        //this.setStatut(Type.id(rs.getLong("statut_id")))
        //    .setOffreEnseignement(Type.id(rs.getLong("offreEnseignement_id")));
    }
    
    public static Etablissement id(Long id){
        Etablissement structure = new Etablissement();
        structure.setId(id);
        return structure;
    }
    
    public Type getStatut() {
        return statut;
    }

    public Etablissement setStatut(Type statut) {
        this.statut = statut;
        return this;
    }

    public Type getOffreEnseignement() {
        return offreEnseignement;
    }

    public Etablissement setOffreEnseignement(Type offreEnseignement) {
        this.offreEnseignement = offreEnseignement;
        return this;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        if(centre.getSession() != null && centre.getSession().getId() != null){
            this.setDefinedAsCentre(true);
        }
        this.centre = centre;
    }

    public boolean isDefinedAsCentre() {
        return definedAsCentre;
    }

    public void setDefinedAsCentre(boolean definedAsCentre) {
        this.definedAsCentre = definedAsCentre;
    }
    
}
