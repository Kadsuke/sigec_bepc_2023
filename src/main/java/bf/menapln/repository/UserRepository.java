/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Personnel;
import bf.menapln.entity.Profil;
import bf.menapln.entity.User;
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
public class UserRepository extends Repository implements InterfaceDAO{

    public UserRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        User user = (User)objet;
        String sql;
        sql = "INSERT INTO user(profil_id,personnel_id,username,pwd) VALUES(?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, user.getProfil().getId());
        if(user.getPersonnel().getId() == null){
            pstmt.setObject(2, null);
        }else{
            pstmt.setLong(2, user.getPersonnel().getId());
        }
        pstmt.setString(3, user.getUsername());
        pstmt.setString(4, user.getPwd());
        pstmt.executeUpdate();
        user.setId(this.lastInsertedId());
        return user;
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
        String sql = "SELECT * FROM viewUser";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            User user = new User(rs);
            user.setProfil(new Profil(rs));
            user.setPersonnel(new Personnel(rs));
            liste.add(user);
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
        User user = (User)objet;
        String sql = "SELECT * FROM viewUser where username = ?";
         PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        ResultSet rs    = pstmt.executeQuery();
        User userBD = null;
        while (rs.next()) {
            userBD = new User(rs);
            userBD.setProfil(new Profil(rs));
            userBD.setPersonnel(new Personnel(rs));
            userBD.setJury(Jury.id(rs.getLong("jury_id")));
        }
        return userBD;
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        String sql = "SELECT * FROM user ORDER BY user_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        Long id = null;
        while (rs.next()) {
           id = rs.getLong("user_id");
        }
        return id;
    }
    
}
