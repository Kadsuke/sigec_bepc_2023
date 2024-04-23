/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
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
public class CandidatPosteJuryRepository extends Repository implements InterfaceDAO{

    public CandidatPosteJuryRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        CandidatPosteJury p = (CandidatPosteJury)objet;
        String sql;
        sql = "INSERT INTO candidatPosteJury(session_id,localiteCandidat_id,structure_id,\n"
                + "poste_id,epreuve_id,nom,prenom,sexe,telephone,matricule,nip)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, p.getSession().getId());
        pstmt.setLong(2, p.getLocaliteCand().getId());
        pstmt.setLong(3, p.getStructure().getId());
        pstmt.setLong(4, p.getPoste().getId());
        if(p.getEpreuve().getId()==null){
            pstmt.setObject(5, null);
        }else{
            pstmt.setLong(5, p.getEpreuve().getId());
        }
        pstmt.setString(6, p.getCandidat().getNom());
        pstmt.setString(7, p.getCandidat().getPrenom());
        pstmt.setString(8, p.getCandidat().getSexe().toString());
        pstmt.setString(9, p.getCandidat().getTelephone());
        pstmt.setString(10, p.getCandidat().getMatricule());
        pstmt.setString(11, p.getCandidat().getNip());
        pstmt.executeUpdate();
        p.getCandidat().setId(this.lastInsertedId());
        return p;
    }

    @Override
    public Objet delete(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set etatCandidature = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getEtatCandidature());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        String sql = "SELECT * FROM viewCandidatPosteJury";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        CandidatPosteJury candidat = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("candidat_id"))){
                candidat = new CandidatPosteJury(rs);
                candidat.getCandidat().setId(rs.getLong("candidat_id"));
                ids.put(rs.getLong("candidat_id"), "");
                liste.add(candidat);
            }
            candidat.getEpreuve().addSerie(new Serie(rs));
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
       String sql = "SELECT * FROM candidatPosteJury ORDER BY candidat_id DESC LIMIT 1";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
            id = rs.getLong("candidat_id");
        }
        return id;
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Objet updateNom(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set nom = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getCandidat().getNom());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }

    public Objet updatePrenom(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set prenom = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getCandidat().getPrenom());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }

    public Objet updateMatricule(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set matricule = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getCandidat().getMatricule());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }

    public Objet updateNip(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set nip = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getCandidat().getNip());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }
    
    public Objet updateTelephone(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set telephone = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setString(1, candidat.getCandidat().getTelephone());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        return candidat;
    }

    public Objet updateEpreuve(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        String sql = "UPDATE candidatPosteJury set epreuve_id = ? WHERE candidat_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, candidat.getEpreuve().getId());
        stmt.setLong(2, candidat.getCandidat().getId());
        stmt.executeUpdate();
        String sql2 = "UPDATE epreuveCandidatPosteJury set epreuve_id = ? WHERE candidat_id = ?";
        PreparedStatement stmt2  = this.factory.connect().prepareStatement(sql2);
        stmt2.setLong(1, candidat.getEpreuve().getId());
        stmt2.setLong(2, candidat.getCandidat().getId());
        stmt2.executeUpdate();
        return candidat;
    }

    public List<Objet> getAllWithoutRetenu() throws SQLException {
        String sql = "SELECT * FROM viewCandidatPosteJury where etatCandidature = 'Retenu'";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        Map ids = new HashMap();
        CandidatPosteJury candidat = null;
        while (rs.next()) {
            if(!ids.containsKey(rs.getLong("candidat_id"))){
                candidat = new CandidatPosteJury(rs);
                candidat.getCandidat().setId(rs.getLong("candidat_id"));
                ids.put(rs.getLong("candidat_id"), "");
                liste.add(candidat);
            }
            candidat.getEpreuve().addSerie(new Serie(rs));
        }
        return liste;
    }
}
