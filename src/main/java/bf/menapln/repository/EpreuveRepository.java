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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class EpreuveRepository extends Repository implements InterfaceDAO{

    public EpreuveRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Epreuve epreuve = (Epreuve)objet;
        String sql;
        sql = "INSERT INTO epreuve(epreuveLibelle,epreuveAcronyme) VALUES(?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setString(1, epreuve.getEpreuveLibelle());
        pstmt.setString(2, epreuve.getEpreuveAcronyme());
        pstmt.executeUpdate();
        epreuve.setId(this.lastInsertedId());
        return epreuve;
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
        String sql = "SELECT * FROM epreuve";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Epreuve epreuve = new Epreuve();
            epreuve.setId(rs.getLong("epreuve_id"));
            epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
            epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
            
            liste.add(epreuve);
        }
        return liste;
    }
    
    public List<Objet> getEpreuveCopies(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
         String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
                 +"where session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ?";
        
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
    
    public List<Objet> getEpreuveAnonyme(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
                 +"where session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ? and anonymat is not null";
        
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
    
    public List<Objet> getEpreuveCorrige(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
         String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
                 +"where session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ? and correcteur_id is not null";
         
         String sql1 = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewEpreuveSerie\n"
                 +"where session_id = ? and serie_id = ? and typeEpreuveLibelle != 'Ecrite'";
        
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql+" UNION "+sql1);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, serie.getId());
        pstmt.setLong(4, tour.getId());
        pstmt.setLong(5, session.getId());
        pstmt.setLong(6, serie.getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            liste.add(new Epreuve(rs)); 
        }
        return liste;
    }
    
    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        String sql = "SELECT * FROM epreuve ORDER BY epreuve_id DESC LIMIT 1";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
            id = rs.getLong("epreuve_id");
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

    public List<Objet> getEpreuveCopiesAll(Session session,Jury jury,Type tour) throws SQLException{
        String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
                +"where session_id = ? and jury_id = ? and tourComposition_id = ?";
       
       PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
       pstmt.setLong(1, session.getId());
       pstmt.setLong(2, jury.getId());
       pstmt.setLong(3, tour.getId());
       ResultSet rs    = pstmt.executeQuery();
       List<Objet> liste = new ArrayList<Objet>();
       while (rs.next()) {
           liste.add(new Epreuve(rs)); 
       }
       return liste;
   }

   public List<Objet> getEpreuveCopiesAllSansOrale(Session session,Jury jury,Type tour) throws SQLException{
    String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
            +"where session_id = ? and jury_id = ? and tourComposition_id = ? and typeEpreuve_id != ? and typeEpreuve_id != ?";
   
   PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
   pstmt.setLong(1, session.getId());
   pstmt.setLong(2, jury.getId());
   pstmt.setLong(3, tour.getId());
   pstmt.setLong(4, 2);
   pstmt.setLong(5, 3);
   ResultSet rs    = pstmt.executeQuery();
   List<Objet> liste = new ArrayList<Objet>();
   while (rs.next()) {
       liste.add(new Epreuve(rs)); 
   }
   return liste;
}

public List<Objet> getEpreuveCopiesAllSansSport(Session session,Jury jury,Type tour) throws SQLException{
    String sql = "SELECT DISTINCT epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewFeuilleComposition\n"
            +"where session_id = ? and jury_id = ? and tourComposition_id = ? and typeEpreuve_id != ?";
   
   PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
   pstmt.setLong(1, session.getId());
   pstmt.setLong(2, jury.getId());
   pstmt.setLong(3, tour.getId());
   pstmt.setLong(4, 3);
   ResultSet rs    = pstmt.executeQuery();
   List<Objet> liste = new ArrayList<Objet>();
   while (rs.next()) {
       liste.add(new Epreuve(rs)); 
   }
   return liste;
}

   public List<Objet> getAllEpreuveByTour1(Session session,Jury jury) throws SQLException {
    String sql = "SELECT Distinct epreuve_id,epreuveLibelle,epreuveAcronyme FROM\n" 
	+"viewEpreuveSerie v\n"
	+"INNER JOIN viewCandidatJury cad ON cad.serie_id = v.serie_id\n"
	+"where estComposePreTour = ? and v.session_id = ? and cad.jury_id = ? ORDER BY epreuveLibelle";
    


    PreparedStatement pstmt  = this.factory.connect().prepareStatement(sql);
    pstmt.setBoolean(1, true);
    pstmt.setLong(2, session.getId());
    pstmt.setLong(3, jury.getId());

    ResultSet rs    = pstmt.executeQuery();
    List<Objet> liste = new ArrayList<Objet>();
    while (rs.next()) {
        Epreuve epreuve = new Epreuve();
        epreuve.setId(rs.getLong("epreuve_id"));
        epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
        epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
        
        liste.add(epreuve);
    }
    return liste;
}

public List<Objet> getAllEpreuveByTour2(Session session) throws SQLException {
    String sql = "SELECT Distinct epreuve_id,epreuveLibelle,epreuveAcronyme FROM viewEpreuveSerie v where estComposeSecTour = ? and session_id = ? ORDER BY epreuveLibelle";
    PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
    stmt.setBoolean(1, true);
    stmt.setLong(2, session.getId());
    ResultSet rs    = stmt.executeQuery();
    List<Objet> liste = new ArrayList<Objet>();
    while (rs.next()) {
        Epreuve epreuve = new Epreuve();
        epreuve.setId(rs.getLong("epreuve_id"));
        epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
        epreuve.setEpreuveAcronyme(rs.getString("epreuveAcronyme"));
        
        liste.add(epreuve);
    }
    return liste;
}
    
}
