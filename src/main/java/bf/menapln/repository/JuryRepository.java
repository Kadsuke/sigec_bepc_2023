/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Centre;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
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
public class JuryRepository extends Repository implements InterfaceDAO{

    public JuryRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Jury jury = (Jury)objet;
        String sql;
        sql = "INSERT INTO jury(session_id,etablissement_id,juryLibelle,juryCapacite,centreExamen_id)\n"
                + "VALUES(?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, jury.getCentre().getSession().getId());
        pstmt.setLong(2, jury.getCentre().getEtatblissement().getId());
        pstmt.setString(3, jury.getJuryLibelle());
        pstmt.setLong(4, jury.getJuryCapacite());
        pstmt.setLong(5, jury.getCentreExamen().getId());
        pstmt.executeUpdate();
        jury.setId(this.lastInsertedId());
        return jury;
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
        String sql = "SELECT * FROM viewJury";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Jury jury = new Jury();
            jury.setJuryCapacite(rs.getLong("juryCapacite"));
            jury.setJuryLibelle(rs.getString("juryLibelle"));
            jury.setCentre(new Centre());
            jury.getCentre().setCapacite(rs.getLong("capacite"));
            jury.getCentre().setSession(Session.id(rs.getLong("session_id")));
            jury.getCentre().getSession().setAnnee(rs.getLong("annee"));
           
            jury.getCentre().setEtatblissement(Etablissement.id(rs.getLong("etablissement_id")));
            jury.getCentre().getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            jury.getCentre().getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            jury.getCentre().getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(jury);
        }
        return liste;
    }
    
    public List<Objet> getJury(Localite centreExamen) throws SQLException {
        String sql = "SELECT * FROM viewJury where centreExamen_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, centreExamen.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Jury jury = Jury.id(rs.getLong("jury_id"));
            jury.setJuryCapacite(rs.getLong("juryCapacite"));
            jury.setJuryLibelle(rs.getString("juryLibelle"));
            jury.setCentre(new Centre());
            jury.getCentre().setCapacite(rs.getLong("capacite"));
            jury.getCentre().setSession(Session.id(rs.getLong("session_id")));
            jury.getCentre().getSession().setAnnee(rs.getLong("annee"));
           
            jury.getCentre().setEtatblissement(Etablissement.id(rs.getLong("etablissement_id")));
            jury.getCentre().getEtatblissement().setCodeStructure(rs.getString("codeStructure"));
            jury.getCentre().getEtatblissement().setNomStructure(rs.getString("nomStructure"));
            jury.getCentre().getEtatblissement().setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(jury);
        }
        return liste;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override 
    public Objet getById(Long id) throws SQLException {
        String sql = "SELECT * FROM viewJury where jury_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs    = stmt.executeQuery();
        Jury jury = null;
        while (rs.next()) {
            jury = new Jury(rs);
            jury.setSession(new Session(rs));
        }
        return jury;
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        String sql = "SELECT * FROM viewJury ORDER BY jury_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
           id = rs.getLong("jury_id");
        }
        return id;
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
