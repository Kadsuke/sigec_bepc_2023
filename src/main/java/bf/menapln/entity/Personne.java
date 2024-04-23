/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import bf.menapln.enumeration.Sexe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public abstract class Personne extends Objet{
    protected String nip;
    protected String nom;
    protected String prenom;
    protected Sexe sexe;
    protected String telephone;
    
    public Personne(){
        super();
    }
    public Personne(Map data){
        this.setNip((String) data.get("nip"));
        this.setNom((String) data.get("nom"));
        this.setPrenom((String) data.get("prenom"));
        this.setSexe((Sexe)data.get("sexe"));
        this.setTelephone((String) data.get("telephone"));
    }
    
    public Personne(ResultSet rs) throws SQLException{
        this.setNip(rs.getString("nip"));
        this.setNom(rs.getString("nom"));
        this.setPrenom(rs.getString("prenom"));
        this.setSexe(Sexe.valueOf(rs.getString("sexe")));
        this.setTelephone(rs.getString("telephone"));
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
}
