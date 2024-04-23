/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Composition;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.EpreuveSerie;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class CompositionRepository extends Repository implements InterfaceDAO{
    
    public CompositionRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Composition compo = (Composition)objet;
        String sql;
        sql = "INSERT INTO composition(session_id,jury_id,tourComposition_id,\n"
                +" epreuve_id,dateComposition,heureDebutComposition,heureFinComposition,fraude,observation,incidence)\n"
                +"VALUES(?,?,?,?,?,?,?,?,?,?)";
        //System.out.println(compo.getSession().getId()+" "+compo.getJury().getId()+" "+compo.getTourComposition().getId()+" "+compo.getSerie().getId()+" "+compo.getEpreuve().getId());
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, compo.getSession().getId());
        pstmt.setLong(2, compo.getJury().getId());
        pstmt.setLong(3, compo.getTourComposition().getId());
        pstmt.setLong(4, compo.getEpreuve().getId());
        pstmt.setString(5, compo.getDateComposition().toString());
        pstmt.setString(6, compo.getHeureDebutComposition().toString());
        pstmt.setString(7, compo.getHeureFinComposition().toString());
        pstmt.setBoolean(8, compo.isFraude());
        pstmt.setString(9, compo.getObservation());
        pstmt.setBoolean(10, compo.isIncident());

        pstmt.executeUpdate();
        return compo;
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
        String sql = "SELECT * FROM viewComposition";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Composition compo = new Composition();
            
            compo.setSession(Session.id(rs.getLong("session_id")));
            compo.getSession().setAnnee(rs.getLong("annee"));
            
            compo.setJury(Jury.id(rs.getLong("jury_id")));
            compo.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            compo.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            compo.getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            
            compo.setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            compo.getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            compo.setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            compo.setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            compo.setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            compo.setFraude(rs.getBoolean("fraude"));
            compo.setObservation(rs.getString("observation"));
            
            liste.add(compo);
        }
        return liste;
    }
    
    public List<Objet> getComposition(Session session,Jury jury,Type tour) throws SQLException{
        List<Objet> liste = new ArrayList();
        
        String sql = "SELECT * FROM viewComposition where session_id = ? and jury_id = ? and tourComposition_id = ?";
        
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        while (rs.next()) {
            Composition compo = new Composition();
            
            compo.setSession(Session.id(rs.getLong("session_id")));
            compo.getSession().setAnnee(rs.getLong("annee"));
            
            
            compo.setJury(Jury.id(rs.getLong("jury_id")));
            compo.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            compo.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            compo.getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            
            compo.setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            compo.getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            compo.setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            compo.setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            compo.setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            compo.setFraude(rs.getBoolean("fraude"));
            compo.setObservation(rs.getString("observation"));
            
            liste.add(compo);
        }
        return liste;
    }
    
    public Composition get(Session session,Jury jury,Serie serie, Epreuve epreuve,Type tour) throws SQLException{
         String sql = "SELECT * FROM viewComposition where session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ?";
        
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
       // pstmt.setLong(3, serie.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        Composition compo = null;
        while (rs.next()) {
            compo = new Composition();
            
            compo.setSession(Session.id(rs.getLong("session_id")));
            compo.getSession().setAnnee(rs.getLong("annee"));
            
            compo.setJury(Jury.id(rs.getLong("jury_id")));
            compo.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            compo.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            compo.getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            
            compo.setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            compo.getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            compo.setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            compo.setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            compo.setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            compo.setFraude(rs.getBoolean("fraude"));
            compo.setObservation(rs.getString("observation"));
            
            
        }
        return compo;
    }
    public Long countFeuille(Session session,Jury jury,Serie serie, Epreuve epreuve, Type tour ) throws SQLException{
        String sql = "SELECT COUNT(*) as nb FROM feuilleComposition\n"
                +" where session_id = ? and jury_id = ? and serie_id = ? and epreuve_id = ? and tourComposition_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, serie.getId());
        pstmt.setLong(4, epreuve.getId());
        pstmt.setLong(5, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        Long nb = null;
        while (rs.next()) {
            nb = rs.getLong("nb");
        }
        return nb;
    }
    public Long countFeuilleSansSerie(Session session,Jury jury, Epreuve epreuve, Type tour ) throws SQLException{
        String sql = "SELECT COUNT(*) as nb FROM feuilleComposition\n"
                +" where session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        Long nb = null;
        while (rs.next()) {
            nb = rs.getLong("nb");
        }
        return nb;
    }
    
    public List<Objet> getEpreuveCompose(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        String sql = "SELECT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewComposition where session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ?";
       
       PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
       pstmt.setLong(1, session.getId());
       pstmt.setLong(2, jury.getId());
       pstmt.setLong(3, serie.getId());
       pstmt.setLong(4, tour.getId());
       ResultSet rs    = pstmt.executeQuery();
       List<Objet> liste = new ArrayList<Objet>();
       while (rs.next()) {
           liste.add(new Epreuve(rs)); 
       }
       return liste;
   }
/* 
    public List<Epreuve> getEpreuveComposeWithoutSerir(Session session,Jury jury,Type tour) throws SQLException{
        String sql = "SELECT Distinct epreuve_id,epreuveLibelle,epreuveAcronyme,coefficient FROM viewComposition where session_id = ? and jury_id = ? and tourComposition_id = ?";
       PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
       pstmt.setLong(1, session.getId());
       pstmt.setLong(2, jury.getId());
       pstmt.setLong(3, tour.getId());
       ResultSet rs    = pstmt.executeQuery();
       List<Epreuve> liste = new ArrayList<Epreuve>();
       Map epreuveid = new HashMap<Long,Integer>();
       while (rs.next()) {
           liste.add(new Epreuve(rs)); 
       }
       return liste;
   }
     */
    
     public List<EpreuveSerie> getEpreuveComposeWithoutSerir(Session session,Jury jury,Type tour) throws SQLException{
        // String sql = "SELECT Distinct epreuve_id,epreuveLibelle,epreuveAcronyme,coefficient,serie_id,serieLibelle,definition FROM viewComposition where session_id = ? and jury_id = ? and tourComposition_id = ?";
        // String sql = "SELECT Distinct epreuve_id,epreuveLibelle,epreuveAcronyme,coefficient FROM ViewEpreuveComposer where session_id = ? and jury_id = ? and tour = ?";
       /*  String sql = "SELECT DISTINCT e.*,eps.coefficient,eps.estComposeSecTour,j.jury_id,sess.session_id,t.tourComposition_id as tour\n"
        +"FROM epreuve e \n"
         +"JOIN epreuveSerie eps ON eps.epreuve_id = e.epreuve_id\n"
         +"JOIN feuilleComposition c ON eps.epreuve_id = e.epreuve_id AND c.serie_id=eps.serie_id\n"
         +"INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
         +"INNER JOIN session sess ON sess.session_id = c.session_id\n"
         +"INNER JOIN jury j ON j.jury_id = c.jury_id\n"
        +"where sess.session_id = ? and j.jury_id = ? and t.tourComposition_id = ?"; */

        String sql =" SELECT DISTINCT e.*,eps.coefficient,eps.estComposeSecTour,j.jury_id,sess.session_id,t.tourComposition_id as tour\n"
            +"FROM feuilleComposition c\n"
			+"LEFT JOIN epreuve e ON e.epreuve_id = c.epreuve_id\n"
			+"JOIN epreuveSerie eps ON eps.epreuve_id = e.epreuve_id\n"
			+"INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
			+"INNER JOIN session sess ON sess.session_id = c.session_id\n"
			+"INNER JOIN jury j ON j.jury_id = c.jury_id\n"
        +"where sess.session_id = ? and j.jury_id = ? and t.tourComposition_id = ?";
        // String sql = "SELECT * FROM ViewEpreuveComposer where session_id = ? and jury_id = ?";
       PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
       pstmt.setLong(1, session.getId());
       pstmt.setLong(2, jury.getId());
       pstmt.setLong(3, tour.getId());
       ResultSet rs    = pstmt.executeQuery();
       List<EpreuveSerie> liste = new ArrayList<EpreuveSerie>();
       Map epreuveid = new HashMap<Long,Integer>();
       while (rs.next()) {
            Epreuve ep = new Epreuve(rs);
            ep.setCoefficient(rs.getLong("coefficient"));
           // Serie sr = new Serie(rs);
            EpreuveSerie eps = new EpreuveSerie(rs);
            eps.setEpreuve(ep);
           // eps.setSerie(sr);
           liste.add(eps); 
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

    public Composition getSansSerie(Session session,Jury jury, Epreuve epreuve,Type tour) throws SQLException{
        String sql = "SELECT * FROM viewComposition where session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ?";
       
       PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
       pstmt.setLong(1, session.getId());
       pstmt.setLong(2, jury.getId());
       pstmt.setLong(3, epreuve.getId());
       pstmt.setLong(4, tour.getId());
       ResultSet rs    = pstmt.executeQuery();
       Composition compo = null;
       while (rs.next()) {
           compo = new Composition();
           
           compo.setSession(Session.id(rs.getLong("session_id")));
           compo.getSession().setAnnee(rs.getLong("annee"));
           
           compo.setJury(Jury.id(rs.getLong("jury_id")));
           compo.getJury().setJuryLibelle(rs.getString("juryLibelle"));
           
           compo.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
           compo.getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
           
           compo.setTourComposition(Type.id(rs.getLong("tourComposition_id")));
           compo.getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
           
           compo.setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
           compo.setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
           compo.setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
           compo.setFraude(rs.getBoolean("fraude"));
           compo.setObservation(rs.getString("observation"));
           
           
       }
       return compo;
   }
 
   public List<Objet> getCompositionNoDuplicate(Session session,Jury jury,Type tour) throws SQLException{
    List<Objet> liste = new ArrayList();
    
    String sql = "SELECT * FROM composition c INNER JOIN session sess ON sess.session_id = c.session_id \n"
    +"INNER JOIN jury j ON j.jury_id = c.jury_id\n"
    +"INNER JOIN epreuve e ON e.epreuve_id = c.epreuve_id\n"
    +"INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
    +"where c.session_id = ? and c.jury_id = ? and c.tourComposition_id = ?";
    
    PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
    pstmt.setLong(1, session.getId());
    pstmt.setLong(2, jury.getId());
    pstmt.setLong(3, tour.getId());
    ResultSet rs    = pstmt.executeQuery();
    while (rs.next()) {
        Composition compo = new Composition();
        
        compo.setSession(Session.id(rs.getLong("session_id")));
        compo.getSession().setAnnee(rs.getLong("annee"));
        
        
        compo.setJury(Jury.id(rs.getLong("jury_id")));
        compo.getJury().setJuryLibelle(rs.getString("juryLibelle"));
        
        compo.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
        compo.getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
        
        compo.setTourComposition(Type.id(rs.getLong("tourComposition_id")));
        compo.getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
        
        compo.setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
        compo.setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
        compo.setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
        compo.setFraude(rs.getBoolean("fraude"));
        compo.setObservation(rs.getString("observation"));
        
        liste.add(compo);
    }
    return liste;
}
   
   
}
