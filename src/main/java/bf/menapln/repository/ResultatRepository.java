/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Resultat;
import bf.menapln.entity.Serie;
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
public class ResultatRepository extends Repository implements InterfaceDAO{

    public ResultatRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Candidat candidat = (Candidat)objet;
        String sql;
        sql = "INSERT INTO resultat(session_id,candidat_id,tourComposition_id,totalPondere,\n"
                +"totalPondereMax,moyenne,codeDecisionJury,decisionJury,mention)\n"
                +"VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, candidat.getInscription().getSession().getId());
        pstmt.setLong(2, candidat.getId());
        pstmt.setLong(3, ((FeuilleComposition)candidat.getCopies().get(0)).getComposition().getTourComposition().getId());
        pstmt.setDouble(4, candidat.getTotalPondere());
        pstmt.setDouble(5, candidat.getTotaPondereMax());
        pstmt.setDouble(6, candidat.moyenne());
        pstmt.setInt(7, candidat.codeDecisionJury());
        pstmt.setString(8, candidat.decisionJury());
        pstmt.setString(9, candidat.mention());
        pstmt.executeUpdate();
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
        String sql = "SELECT * FROM viewResultat";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Resultat resultat = new Resultat(rs);
            liste.add(resultat);
        }
        return liste;
    }
    
    public List<Objet> getResultat(Serie serie,Jury jury,Type decision) throws SQLException{
        String sql = "SELECT * FROM viewResultat where serie_id = ? and tourComposition_id = ? and codeDecisionJury = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, serie.getId());
        stmt.setLong(2, jury.getEtape().getId());
        stmt.setLong(3, decision.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Resultat resultat = new Resultat(rs);
            liste.add(resultat);
        }
        return liste;
    }

    public List<Objet> getResultatCentreSecondaire(CentreSecondaire centre,Jury jury,Type decision) throws SQLException{
        String sql = "SELECT * FROM viewResultat where centreSecondaire_id = ? and tourComposition_id = ? and codeDecisionJury = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, centre.getId());
        stmt.setLong(2, jury.getEtape().getId());
        stmt.setLong(3, decision.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Resultat resultat = new Resultat(rs);
            liste.add(resultat);
        }
        return liste;
    }

    public List<Objet> getResultatSansSerie(Jury jury,Type decision) throws SQLException{
        String sql = "SELECT * FROM viewResultat where tourComposition_id = ? and codeDecisionJury = ? and centreSecondaire_id is NULL";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, jury.getEtape().getId());
        stmt.setLong(2, decision.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Resultat resultat = new Resultat(rs);
            liste.add(resultat);
        }
        System.out.println(liste.size());
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
