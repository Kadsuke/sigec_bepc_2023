/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Membre;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class MembreJuryRepository extends Repository implements InterfaceDAO{

    public MembreJuryRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Membre p = (Membre)objet;
        String sql;
        sql = "INSERT INTO membreJury(session_id,jury_id,poste_id,personnel_id,membreDeliberant) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        p.setStatement(pstmt);
        pstmt.executeUpdate();
        return p;
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
        String sql = "SELECT * FROM viewMembreJury";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        Membre membre = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("personnel_id"))){
                membre = new Membre(rs);
                membre.getPersonnel().setId(rs.getLong("personnel_id"));
                ids.put(rs.getLong("personnel_id"), "");
                liste.add(membre);
            }
            //membre.getEpreuve().addSerie(new Serie(rs));
        }
        return liste;
    }

    public List<Objet> getCorrecteurs(Jury jury){
        
        return null;
    }
    
    public List<Objet> getCorrecteurs(Epreuve epreuve){
        
        return null;
    }
    
    public List<Objet> getCorrecteurs(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        String sql = "SELECT * FROM viewMembreJury where session_id = ? and jury_id = ? and epreuve_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        Correcteur membre = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("personnel_id"))){
                membre = new Correcteur(rs);
                membre.getPersonnel().setId(rs.getLong("personnel_id"));
                ids.put(rs.getLong("personnel_id"), "");
                liste.add(membre);
            }
            //membre.getEpreuve().addSerie(new Serie(rs));
        }
        return liste;
    }

    public List<Objet> getCorrecteursFr(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        String sql = "SELECT * FROM viewMembreJury where session_id = ? and jury_id = ? and epreuveLibelle = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setString(3, "Français");
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        Correcteur membre = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("personnel_id"))){
                membre = new Correcteur(rs);
                membre.getPersonnel().setId(rs.getLong("personnel_id"));
                ids.put(rs.getLong("personnel_id"), "");
                liste.add(membre);
            }
            //membre.getEpreuve().addSerie(new Serie(rs));
        }
        return liste;
    }

    public List<Objet> getCorrecteursAng(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        String sql = "SELECT * FROM viewMembreJury where session_id = ? and jury_id = ? and epreuveLibelle = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setString(3, "Anglais Ecrit");
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        Correcteur membre = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("personnel_id"))){
                membre = new Correcteur(rs);
                membre.getPersonnel().setId(rs.getLong("personnel_id"));
                ids.put(rs.getLong("personnel_id"), "");
                liste.add(membre);
            }
            //membre.getEpreuve().addSerie(new Serie(rs));
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
    public Long lastInsertedId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
