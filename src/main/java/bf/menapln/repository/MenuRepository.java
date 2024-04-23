/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Menu;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Profil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class MenuRepository extends Repository implements InterfaceDAO{

    public MenuRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Menu menu = (Menu)objet;
        String sql;
        sql = "INSERT INTO menu(menuParent_id,menuLibelle) VALUES(?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        if(menu.getParent().getId() == null){
            pstmt.setObject(1, null);
        }else{
            pstmt.setLong(1, menu.getParent().getId());
        }
        pstmt.setString(2, menu.getMenuLibelle());
        pstmt.executeUpdate();
        menu.setId(this.lastInsertedId());
        return menu;
    }

    @Override
    public Objet delete(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        String sql = "SELECT * FROM menu";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Menu menu = new Menu(rs);
            liste.add(menu);
        }
        return liste;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Objet getByProfil(Objet objet) throws SQLException {
        Profil profil = (Profil)objet;
        String sql = "SELECT * FROM menu m INNER JOIN profilMenus p ON m.menu_id = p.menu_id and p.profil_id = ?";
        
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, profil.getId());
        ResultSet rs    = pstmt.executeQuery();
        profil.setMenus(new ArrayList());
        while (rs.next()) {
            profil.addMenu(new Menu(rs));
        }
        return profil;
    }

    @Override
    public Long lastInsertedId() throws SQLException {
       String sql = "SELECT * FROM menu ORDER BY menu_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        Long id = null;
        while (rs.next()) {
           id = rs.getLong("menu_id");
        }
        return id;
    }
    
}
