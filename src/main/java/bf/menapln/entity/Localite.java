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
public class Localite extends Objet{
    private String codeLocalite;
    private String nomLoclite;
    private Type typeLocalite;
    private Localite parentLocalite;
    private String chefLieu;

    /*private Integer nombreDeJury;

    private Integer nombreParJury;

    private Long totalCandidat;

    private Boolean deleted;

    private Localite localite;
    
    private Localite departement;
    */
    public Localite(){
        super();
    }
    public Localite(Map data){
        super(data);
        this.setCodeLocalite((String) data.get("codeLocalite"));
        this.setNomLoclite((String) data.get("nomLocalite"));
        this.setTypeLocalite(Type.id(Long.parseLong((String) data.get("typeLocalite"))));
        this.setParentLocalite(Localite.id(Long.parseLong((String) data.get("parentLocalite"))));
    }
    
    public static Localite id(Long id){
        id = (id == null || id == 0)?null:id;
        Localite localite = new Localite();
        localite.setId(id);
        return localite;
    }
    
    public Localite(ResultSet rs) throws SQLException {
        this.setCodeLocalite(rs.getString("codeLocalite"));
        this.setNomLoclite(rs.getString("nomLocalite"));
        //this.setTypeLocalite(Type.id(rs.getLong("typeLocalite_id")));
        //this.setParentLocalite(Localite.id(Long.parseLong((String) data.get("parentLocalite"))));
    }
    
    public Type getTypeLocalite() {
        return typeLocalite;
    }

    public Localite typeLocalite(Type typeLocalite) {
        this.typeLocalite = typeLocalite;
        return this;
    }

    public void setTypeLocalite(Type typeLocalite) {
        this.typeLocalite = typeLocalite;
    }

    public String getCodeLocalite() {
        return codeLocalite;
    }

    public void setCodeLocalite(String codeLocalite) {
        this.codeLocalite = codeLocalite;
    }

    public String getNomLoclite() {
        return nomLoclite;
    }

    public void setNomLoclite(String nomLoclite) {
        this.nomLoclite = nomLoclite;
    }

    public Localite getParentLocalite() {
        return parentLocalite;
    }

    public void setParentLocalite(Localite parentLocalite) {
        this.parentLocalite = parentLocalite;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String toString(){
        return this.nomLoclite;
    }
}
