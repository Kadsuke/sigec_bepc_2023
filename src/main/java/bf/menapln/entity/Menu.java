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
public class Menu extends Objet{
    private String menuLibelle;
    private Menu parent;
    private Profil profil;
    
    public Menu(){
        super();
    }
    
    public Menu(Map data){
        this.setMenuLibelle((String) data.get("menuLibelle"));
        this.setParent((data.get("menuParent_id") != null)?Menu.id(Long.parseLong((String) data.get("menuParent_id"))):new Menu());
    }
    
    public Menu(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("menu_id"));
        this.setParent(Menu.id(rs.getLong("menuParent_id")));
        this.setMenuLibelle(rs.getString("menuLibelle"));
    }
    
    public static Menu id(Long id){
        Menu m = new Menu();
        m.setId(id);
        return m;
    }

    public String getMenuLibelle() {
        return menuLibelle;
    }

    public void setMenuLibelle(String menuLibelle) {
        this.menuLibelle = menuLibelle;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
    
    
}
