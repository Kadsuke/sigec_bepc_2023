/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
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
public class SerieRepository extends Repository implements InterfaceDAO{

    public SerieRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Serie serie = (Serie)objet;
        String sql;
        sql = "INSERT INTO serie(offreEnseignement_id,serieLibelle,definition) VALUES(?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, serie.getOffreEnseignement().getId());
        pstmt.setString(2, serie.getSerieLibelle());
        pstmt.setString(3, serie.getDefinition());
        pstmt.executeUpdate();
        serie.setId(this.lastInsertedId());
        return serie;
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
        String sql = "SELECT * FROM serie INNER JOIN offreEnseignement o ON o.offreEnseignement_id = serie.offreEnseignement_id";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Serie serie = new Serie();
            serie.setId(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            serie.setOffreEnseignement(Type.id(rs.getLong("offreEnseignement_id")));
            serie.getOffreEnseignement().setLibelle(rs.getString("offreEnseignementLibelle"));
            liste.add(serie);
        }
        return liste;
    }
    
    public List<Objet> getSerie(Session session, Localite villeComposition) throws SQLException {
        String sql = "SELECT * FROM serieVilleComposition where session_id = ? and localite_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, session.getId());
        stmt.setLong(2, villeComposition.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Serie serie = new Serie();
            serie.setId(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            //serie.setOffreEnseignement(Type.id(rs.getLong("offreEnseignement_id")));
            //serie.getOffreEnseignement().setLibelle(rs.getString("offreEnseignementLibelle"));
            liste.add(serie);
        }
        return liste;
    }
    
    public List<Objet> getSerie(Jury jury) throws SQLException {
        String sql = "SELECT distinct serie_id, serieLibelle,definition FROM viewCandidatJury where jury_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, jury.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Serie serie = new Serie();
            serie.setId(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            //serie.setOffreEnseignement(Type.id(rs.getLong("offreEnseignement_id")));
            //serie.getOffreEnseignement().setLibelle(rs.getString("offreEnseignementLibelle"));
            liste.add(serie);
        }
        return liste;
    }

    @Override
    public Objet getById(Long id) throws SQLException {

        String sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? ORDER BY epreuveLibelle";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs    = stmt.executeQuery();
        Serie serie = null;
        while (rs.next()) {
            if(serie == null){
                serie = new Serie();
                serie.setId(rs.getLong("serie_id"));
                serie.setSerieLibelle(rs.getString("serieLibelle"));
                serie.setDefinition(rs.getString("definition"));
            }
        }
        return (serie == null)?new Serie():serie;
    }

    @Override
    public Long lastInsertedId() throws SQLException {
       String sql = "SELECT * FROM serie ORDER BY serie_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
            id = rs.getLong("serie_id");
        }
        return id;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
