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
public class Structure extends Objet{
    private Type typeStructure;
    private String codeStructure;
    private String nomStructure;
    private String acronymeStructure;
    private Localite localite;
    private Localite centreExamen;
    private Structure parent;
    
    public Structure(){
        super();
    }
    public Structure(Map data){
        this.setCodeStructure((String) data.get("codeStructure"))
        .setNomStructure((String) data.get("nomStrucure"))
        .setAcronymeStructure((String) data.get("acronymeStructure"))
        .setLocalite(Localite.id(Long.parseLong((String)data.get("localiteStructure"))))
        .setCentreExamen(Localite.id(Long.parseLong((String)data.get("centreExamenStructure"))))
        .setTypeStructure(Type.id(Long.parseLong((String) data.get("typeStructure"))))
        .setParent(new Structure());
    }
    
    public static Structure id(Long id){
        Structure structure = new Structure();
        structure.setId(id);
        return structure;
    }
    
    public Structure(ResultSet rs) throws SQLException {
        this.setCodeStructure(rs.getString("codeStructure"))
        .setNomStructure(rs.getString("nomStructure"))
        .setAcronymeStructure(rs.getString("acronymeStructure"))
        //.setLocalite(Localite.id(rs.getLong("localite_id")))
        .setTypeStructure(Type.id(rs.getLong("typeStructure_id")));
    }
    public Type getTypeStructure() {
        return typeStructure;
    }

    public Structure setTypeStructure(Type typeStructure) {
        this.typeStructure = typeStructure;
        return this;
    }

    public String getCodeStructure() {
        return codeStructure;
    }

    public Structure setCodeStructure(String codeStructure) {
        this.codeStructure = codeStructure;
        return this;
    }

    public String getNomStructure() {
        return nomStructure;
    }

    public Structure setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
        return this;
    }

    public String getAcronymeStructure() {
        return acronymeStructure;
    }

    public Structure setAcronymeStructure(String acronymeStructure) {
        this.acronymeStructure = acronymeStructure;
        return this;
    }

    public Localite getLocalite() {
        return localite;
    }

    public Structure setCentreExamen(Localite centreExamen) {
        this.centreExamen = centreExamen;
        return this;
    }

    public Localite getCentreExamen() {
        return centreExamen;
    }

    public Structure setLocalite(Localite localite) {
        this.localite = localite;
        return this;
    }


    public Structure getParent() {
        return parent;
    }

    public Structure setParent(Structure parent) {
        this.parent = parent;
        return this;
    }
    
    public String toString(){
        return this.acronymeStructure;
    }
    
}
