/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class Resultat extends SessionEntity{
    private Candidat candidat;
    private Type tour;
    private Integer totalPondere;
    private Integer totalPondereMax;
    private Double moyenne;
    private Type decisionJury;
    private String mention;
    
    public Resultat(){
        super();
    }
    
    public Resultat(ResultSet rs) throws SQLException{
        super(rs);
        this.setCandidat(new Candidat(rs));
        this.getCandidat().setEtablissementCandidat(new Etablissement(rs));
        this.setTotalPondere(rs.getInt("totalPondere"));
        this.setTotalPondereMax(rs.getInt("totalPondereMax"));
        this.setMoyenne(rs.getDouble("moyenne"));
        this.setDecisionJury(Type.id(rs.getLong("codeDecisionJury")));
        this.getDecisionJury().setLibelle(rs.getString("decisionJury"));
        this.setMention(rs.getString("mention"));
    }
    
    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Type getTour() {
        return tour;
    }

    public void setTour(Type tour) {
        this.tour = tour;
    }

    public Integer getTotalPondere() {
        return totalPondere;
    }

    public void setTotalPondere(Integer totalPondere) {
        this.totalPondere = totalPondere;
    }

    public Integer getTotalPondereMax() {
        return totalPondereMax;
    }

    public void setTotalPondereMax(Integer totalPondereMax) {
        this.totalPondereMax = totalPondereMax;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Type getDecisionJury() {
        return decisionJury;
    }

    public void setDecisionJury(Type decisionJury) {
        this.decisionJury = decisionJury;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }
    
    
}
