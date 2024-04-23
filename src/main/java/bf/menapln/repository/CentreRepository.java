/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Centre;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
import bf.menapln.entity.Structure;
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
public class CentreRepository extends Repository implements InterfaceDAO{

    public CentreRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Centre centre = (Centre)objet;
        String sql;
        sql = "INSERT INTO centreComposition(session_id,etablissement_id,capacite)\n"
                +"VALUES(?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, centre.getSession().getId());
        pstmt.setLong(2, centre.getEtatblissement().getId());
        pstmt.setLong(3, centre.getCapacite());
        pstmt.executeUpdate();
        return centre;
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
        String sql = "SELECT * FROM viewCentre";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Centre centre = new Centre();
            if(rs.getLong("session_id") != 0){
                centre.setCapacite(rs.getLong("capacite"));
                centre.setSession(Session.id(rs.getLong("session_id")));
                centre.getSession().setAnnee(rs.getLong("annee"));
            }else{
                centre.setSession(new Session());
            }
            
            centre.setEtatblissement(Etablissement.id(rs.getLong("structure_id")));
            centre.getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            centre.getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            centre.getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(centre);
        }
        return liste;
    }
    
    public List<Objet> getCentres() throws SQLException {
        String sql = "SELECT * FROM viewCentre where session_id is not null";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Centre centre = new Centre();
            centre.setCapacite(rs.getLong("capacite"));
            centre.setSession(Session.id(rs.getLong("session_id")));
            centre.getSession().setAnnee(rs.getLong("annee"));
            
            centre.setEtatblissement(Etablissement.id(rs.getLong("structure_id")));
            centre.getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            centre.getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            centre.getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(centre);
        }
        return liste;
    }
    
    public List<Objet> getCentres(Localite villeComposition) throws SQLException {
        String sql = "SELECT * FROM viewCentre where session_id is not null and localite_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, villeComposition.getId());
        ResultSet rs    = stmt.executeQuery();
         List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Centre centre = new Centre();
            centre.setCapacite(rs.getLong("capacite"));
            centre.setSession(Session.id(rs.getLong("session_id")));
            centre.getSession().setAnnee(rs.getLong("annee"));
            
            centre.setEtatblissement(Etablissement.id(rs.getLong("structure_id")));
            centre.getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            centre.getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            centre.getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(centre);
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
    
    public List<Objet> getCentresByCentreExamen(Long centreExamen) throws SQLException {
        String sql = "SELECT * FROM viewCentre where session_id is not null and centreExamen_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, centreExamen);
        ResultSet rs    = stmt.executeQuery();
         List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Centre centre = new Centre();
            centre.setCapacite(rs.getLong("capacite"));
            centre.setSession(Session.id(rs.getLong("session_id")));
            centre.getSession().setAnnee(rs.getLong("annee"));
            
            centre.setEtatblissement(Etablissement.id(rs.getLong("structure_id")));
            centre.getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            centre.getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            centre.getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(centre);
        }
        return liste;
    }
    
}
