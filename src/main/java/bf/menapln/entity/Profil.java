/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Profil extends Objet{
    private String profilLibelle;
    private List<Objet> menus;
    
    public Profil(){
        super();
    }
    
    public Profil(Map data){
        this.setProfilLibelle((String) data.get("profilLibelle"));
    }
    
    public Profil(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("profil_id"));
        this.setProfilLibelle(rs.getString("profilLibelle"));
    }
    
    public static Profil id(Long id){
        Profil p = new Profil();
        p.setId(id);
        return p;
    }

    public String getProfilLibelle() {
        return profilLibelle;
    }

    public Profil setProfilLibelle(String profilLibelle) {
        this.profilLibelle = profilLibelle;
        return this;
    }

    public List<Objet> getMenus() {
        return menus;
    }

    public void setMenus(List<Objet> menus) {
        this.menus = menus;
    }
    
    public void addMenu(Menu menu){
        menu.setProfil(this);
        this.menus.add(menu);
    }
    
    @Override
    public String toString(){
        return this.profilLibelle;
    }
    
}
