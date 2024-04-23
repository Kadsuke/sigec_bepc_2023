/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Pays;
import bf.menapln.entity.Serie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CandidatRepository extends Repository implements InterfaceDAO{

    public CandidatRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Candidat candidat = (Candidat)objet;
        String sql;
        sql = "INSERT INTO candidat(iue,paysNaissance_id,paysNationalite_id,nom,prenom,sexe,telephone,\n"
                +"dateNaissance,numeroActeNaissance,lieuNaissance,lienActeNaissance,lienPhoto)\n"
                +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setString(1, candidat.getIue());
        pstmt.setLong(2, candidat.getPaysNaissance().getId());
        pstmt.setLong(3, candidat.getNationalite().getId());
        pstmt.setString(4, candidat.getNom());
        pstmt.setString(5, candidat.getPrenom());
        pstmt.setString(6, candidat.getSexe());
        pstmt.setString(7, candidat.getTelephone());
        pstmt.setString(8, candidat.getDateNaissance().toString());
        pstmt.setLong(9, candidat.getNumeroActeNaissance());
        pstmt.setString(10, candidat.getLieuNaissance());
        pstmt.setString(11, candidat.getLienActeNaissance());
        pstmt.setString(12, candidat.getLienPhoto());
        pstmt.executeUpdate();
        candidat.setId(this.lastInsertedId());
        return candidat;
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
       String sql = "SELECT * FROM viewCandidat";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Candidat candidat = new Candidat();
            candidat.setId(rs.getLong("candidat_id"));
            candidat.setIue(rs.getString("iue"));
            candidat.setNom(rs.getString("nom"));
            candidat.setPrenom(rs.getString("prenom"));
            candidat.setSexe(rs.getString("sexe"));
            candidat.setTelephone(rs.getString("telephone"));
            candidat.setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.setLienPhoto(rs.getString("lienPhoto"));
            candidat.setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            liste.add(candidat);
        }
        return liste;
    }
    
    public List<Objet> getCandidat(Serie serie, Localite centreExamen) throws SQLException {
       String sql = "SELECT * FROM viewCandidat where serie_id = ? and villeComposition = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, serie.getId());
        stmt.setLong(2, centreExamen.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Candidat candidat = new Candidat();
            candidat.setId(rs.getLong("candidat_id"));
            candidat.setIue(rs.getString("iue"));
            candidat.setNom(rs.getString("nom"));
            candidat.setPrenom(rs.getString("prenom"));
            candidat.setSexe(rs.getString("sexe"));
            candidat.setTelephone(rs.getString("telephone"));
            candidat.setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.setLienPhoto(rs.getString("lienPhoto"));
            candidat.setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        String sql = "SELECT * FROM viewCandidat where numeroPV = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs    = stmt.executeQuery();

        Candidat candidatt = new Candidat();

        while (rs.next()) {
          //  candidatt.setId(rs.getLong("candidat_id"));
            candidatt.setIue(rs.getString("iue"));
            candidatt.setNom(rs.getString("nom"));
            candidatt.setPrenom(rs.getString("prenom"));
            candidatt.setSexe(rs.getString("sexe"));
            candidatt.setTelephone(rs.getString("telephone"));
            candidatt.setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidatt.setLieuNaissance(rs.getString("lieuNaissance"));
            candidatt.setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidatt.setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidatt.setLienPhoto(rs.getString("lienPhoto"));
            candidatt.setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidatt.setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidatt.setSport(rs.getString("sport"));
            candidatt.setSerie(Serie.id(rs.getLong("serie_id")));
            candidatt.getSerie().setSerieLibelle(rs.getString("serieLibelle"));
        }
        return candidatt;    
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        String sql = "SELECT * FROM candidat ORDER BY candidat_id DESC LIMIT 1";
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
    
}
