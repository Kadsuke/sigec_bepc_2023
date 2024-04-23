/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Serie extends Objet{
    private Type offreEnseignement;
    private String serieLibelle;
    private String definition;
    private List<Epreuve> epreuves = new ArrayList<Epreuve>();
    private Integer totalCoef = 0;
    
    public Serie(){
        super();
    }
    
    public Serie(Map data){
        if(data.containsKey("offreEnseignement")){
            this.setOffreEnseignement(Type.id(Long.parseLong((String) data.get("offreEnseignement"))))
                    .setSerieLibelle((String) data.get("serieLibelle"))
                .setDefinition((String) data.get("definition"));
        }
        if(data.containsKey("serie_id")){
            this.setId(Long.parseLong((String) data.get("serie_id")));
        }
    }
    
    public Serie(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("serie_id"));
        this.setSerieLibelle(rs.getString("serieLibelle"));
        this.setDefinition(rs.getString("definition"));     
    }
    
    public static Serie id(Long id){
        Serie s = new Serie();
        s.setId(id);
        return s;
    }
    
    public Type getOffreEnseignement() {
        return offreEnseignement;
    }

    public Serie setOffreEnseignement(Type offreEnseignement) {
        this.offreEnseignement = offreEnseignement;
        return this;
    }

    public String getSerieLibelle() {
        return serieLibelle;
    }

    public Serie setSerieLibelle(String serieLibelle) {
        this.serieLibelle = serieLibelle;
        return this;
    }

    public String getDefinition() {
        return definition;
    }

    public Serie setDefinition(String definition) {
        this.definition = definition;
        return this;
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public Serie setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
        return this;
    }
    
    public Serie addEpreuve(Epreuve epreuve){
        this.epreuves.add(epreuve);
        this.totalCoef += epreuve.getCoefficient().intValue();
        return this;
    }
    
    public String toString(){
        return this.getSerieLibelle();
    }

    public Integer getTotalCoef() {
        return totalCoef;
    }

    public void setTotalCoef(Integer totalCoef) {
        this.totalCoef = totalCoef;
    }
    
}
