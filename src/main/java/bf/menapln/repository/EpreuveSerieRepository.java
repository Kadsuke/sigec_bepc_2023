/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class EpreuveSerieRepository extends Repository implements InterfaceDAO{

    public EpreuveSerieRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Serie serie = (Serie)objet;
        String sql;
        sql = "INSERT INTO epreuveSerie(epreuve_id,serie_id,session_id,typeEpreuve_id,coefficient,duree,estComposeSecTour,estComposePreTour,estRachetable,estTypeFrancais)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, serie.getEpreuves().get(0).getId());
        pstmt.setLong(2, serie.getId());
        pstmt.setLong(3, serie.getEpreuves().get(0).getSession().getId());
        pstmt.setLong(4, serie.getEpreuves().get(0).getTypeEpreuve().getId());
        pstmt.setLong(5, serie.getEpreuves().get(0).getCoefficient());
        pstmt.setString(6, serie.getEpreuves().get(0).getDuree().toString());
        pstmt.setBoolean(7, serie.getEpreuves().get(0).EstComposeSecTour());
        pstmt.setBoolean(8, serie.getEpreuves().get(0).EstComposePreTour());
        pstmt.setBoolean(9, serie.getEpreuves().get(0).EstRachetable());
        pstmt.setBoolean(10, serie.getEpreuves().get(0).getEstTypeFrancais());

        
        pstmt.executeUpdate();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }
    
    public List<Epreuve> getAllSerieByEpreuve() throws SQLException{
        String sql = "SELECT * FROM viewEpreuveSerie";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        
        List<Epreuve> epreuves = new ArrayList<Epreuve>();
        int i = 0;
        Map epreuveid = new HashMap<Long,Integer>();
        Epreuve epreuve = null;
        while (rs.next()) {
            if(!epreuveid.containsKey(rs.getLong("epreuve_id"))){
                epreuve = Epreuve.id(rs.getLong("epreuve_id"));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
                epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
                epreuve.setEstComposePreTour(rs.getBoolean("estComposePreTour"));
                epreuve.setEstRachetable(rs.getBoolean("estRachetable"));
                epreuve.setEstTypeFrancais(rs.getBoolean("estTypeFrancais"));
                epreuve.setSession(Session.id(rs.getLong("session_id")));
                epreuve.getSession().setAnnee(rs.getLong("annee"));
                epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
                epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
                epreuveid.put(epreuve.getId(), i);
                epreuves.add(epreuve);
                i++;
            }
            Serie serie = Serie.id(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            epreuves.get((int) epreuveid.get(rs.getLong("epreuve_id"))).addSerie(serie);
        }
        return epreuves;
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        String sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? and estComposePreTour = ? ORDER BY epreuveLibelle";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.setBoolean(2, true);
        ResultSet rs    = stmt.executeQuery();
        Serie serie = null;
        while (rs.next()) {
            if(serie == null){
                serie = new Serie();
                serie.setId(rs.getLong("serie_id"));
                serie.setSerieLibelle(rs.getString("serieLibelle"));
                serie.setDefinition(rs.getString("definition"));
            }
            Epreuve epreuve = Epreuve.id(rs.getLong("epreuve_id"));
            epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
            epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
            epreuve.setCoefficient(rs.getLong("coefficient"));
            epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
            epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
            epreuve.setEstComposePreTour(rs.getBoolean("estComposePreTour"));
            epreuve.setEstTypeFrancais(rs.getBoolean("estTypeFrancais"));
            epreuve.setEstRachetable(rs.getBoolean("estRachetable"));
            epreuve.setSession(Session.id(rs.getLong("session_id")));
            epreuve.getSession().setAnnee(rs.getLong("annee"));
            epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
            epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
            serie.addEpreuve(epreuve);
        }
        return (serie == null)?new Serie():serie;
    }
    
    public Objet getEpreuveSecTourBySerie(Long id) throws SQLException {
        String sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? and estComposeSecTour = ? ORDER BY epreuveLibelle";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.setBoolean(2, true);
        ResultSet rs    = stmt.executeQuery();
        Serie serie = null;
        while (rs.next()) {
            if(serie == null){
                serie = new Serie();
                serie.setId(rs.getLong("serie_id"));
                serie.setSerieLibelle(rs.getString("serieLibelle"));
                serie.setDefinition(rs.getString("definition"));
            }
            Epreuve epreuve = Epreuve.id(rs.getLong("epreuve_id"));
            epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
            epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
            epreuve.setCoefficient(rs.getLong("coefficient"));
            epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
            epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
            epreuve.setSession(Session.id(rs.getLong("session_id")));
            epreuve.getSession().setAnnee(rs.getLong("annee"));
            epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
            epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
            serie.addEpreuve(epreuve);
        }
        return (serie == null)?new Serie():serie;
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        return null;
    }


    public List<Epreuve> getAllEpreuveByNoFrancais() throws SQLException{
        String sql = "SELECT * FROM viewEpreuveSerie";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        
        List<Epreuve> epreuves = new ArrayList<Epreuve>();
        int i = 0;
        Map epreuveid = new HashMap<Long,Integer>();
        Epreuve epreuve = null;
        while (rs.next()) {
            if(!epreuveid.containsKey(rs.getLong("epreuve_id"))){
                epreuve = Epreuve.id(rs.getLong("epreuve_id"));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
                epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
                epreuve.setEstComposePreTour(rs.getBoolean("estComposePreTour"));
                epreuve.setEstRachetable(rs.getBoolean("estRachetabe"));
                epreuve.setEstTypeFrancais(rs.getBoolean("estTypeFrancais"));
                epreuve.setSession(Session.id(rs.getLong("session_id")));
                epreuve.getSession().setAnnee(rs.getLong("annee"));
                epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
                epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
                epreuveid.put(epreuve.getId(), i);
                epreuves.add(epreuve);
                i++;
            }
            Serie serie = Serie.id(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            epreuves.get((int) epreuveid.get(rs.getLong("epreuve_id"))).addSerie(serie);
        }
        return epreuves;
    }
    
    public Objet getEpreuvePreTourBySerie(Long id) throws SQLException {
        String sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? and estComposePreTour = ? ORDER BY epreuveLibelle";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.setBoolean(2, true);
        ResultSet rs    = stmt.executeQuery();
        Serie serie = null;
        while (rs.next()) {
            if(serie == null){
                serie = new Serie();
                serie.setId(rs.getLong("serie_id"));
                serie.setSerieLibelle(rs.getString("serieLibelle"));
                serie.setDefinition(rs.getString("definition"));
            }
            Epreuve epreuve = Epreuve.id(rs.getLong("epreuve_id"));
            epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
            epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
            epreuve.setCoefficient(rs.getLong("coefficient"));
            epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
            epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
            epreuve.setSession(Session.id(rs.getLong("session_id")));
            epreuve.getSession().setAnnee(rs.getLong("annee"));
            epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
            epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
            serie.addEpreuve(epreuve);
        }
        return (serie == null)?new Serie():serie;
    }


    public List<Epreuve> getAllSerieByEpreuveNofr() throws SQLException{
        String sql = "SELECT * FROM viewEpreuveSerie where estTypeFrancais = ? and typeEpreuve_id != ? and epreuve_id != ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setBoolean(1, false);
        stmt.setLong(2, 3);
        stmt.setLong(3, 15);
        ResultSet rs    = stmt.executeQuery();
        
        List<Epreuve> epreuves = new ArrayList<Epreuve>();
        int i = 0;
        Map epreuveid = new HashMap<Long,Integer>();
        Epreuve epreuve = null;
        while (rs.next()) {
            if(!epreuveid.containsKey(rs.getLong("epreuve_id"))){
                epreuve = Epreuve.id(rs.getLong("epreuve_id"));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
                epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
                epreuve.setEstComposePreTour(rs.getBoolean("estComposePreTour"));
                epreuve.setEstRachetable(rs.getBoolean("estRachetable"));
                epreuve.setEstTypeFrancais(rs.getBoolean("estTypeFrancais"));
                epreuve.setSession(Session.id(rs.getLong("session_id")));
                epreuve.getSession().setAnnee(rs.getLong("annee"));
                epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
                epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
                epreuveid.put(epreuve.getId(), i);
                epreuves.add(epreuve);
                i++;
            }
            Serie serie = Serie.id(rs.getLong("serie_id"));
            serie.setSerieLibelle(rs.getString("serieLibelle"));
            serie.setDefinition(rs.getString("definition"));
            epreuves.get((int) epreuveid.get(rs.getLong("epreuve_id"))).addSerie(serie);
        }
        return epreuves;
    }

    public Objet getByIdTour(Long id,Jury jury) throws SQLException {
        String sql;
        PreparedStatement pstmt;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? and estTypeFrancais = ? ORDER BY epreuveLibelle";
                pstmt  = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                pstmt.setBoolean(2, false);
            break;
            default:
                sql = "SELECT * FROM viewEpreuveSerie where serie_id = ? and estComposePreTour = ? ORDER BY epreuveLibelle";
                pstmt  = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                pstmt.setBoolean(2, true);
            break;
        }
        ResultSet rs    = pstmt.executeQuery();
        Serie serie = null;
        while (rs.next()) {
            if(serie == null){
                serie = new Serie();
                serie.setId(rs.getLong("serie_id"));
                serie.setSerieLibelle(rs.getString("serieLibelle"));
                serie.setDefinition(rs.getString("definition"));
            }
            Epreuve epreuve = Epreuve.id(rs.getLong("epreuve_id"));
            epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
            epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
            epreuve.setCoefficient(rs.getLong("coefficient"));
            epreuve.setDuree(LocalTime.parse(rs.getString("duree")));
            epreuve.setEstComposeSecTour(rs.getBoolean("estComposeSecTour"));
            epreuve.setEstComposePreTour(rs.getBoolean("estComposePreTour"));
            epreuve.setEstTypeFrancais(rs.getBoolean("estTypeFrancais"));
            epreuve.setEstRachetable(rs.getBoolean("estRachetable"));
            epreuve.setSession(Session.id(rs.getLong("session_id")));
            epreuve.getSession().setAnnee(rs.getLong("annee"));
            epreuve.setTypeEpreuve(Type.id(rs.getLong("typeEpreuve_id")));
            epreuve.getTypeEpreuve().setLibelle(rs.getString("typeEpreuveLibelle"));
            serie.addEpreuve(epreuve);
        }
        return (serie == null)?new Serie():serie;
    }
    
}
