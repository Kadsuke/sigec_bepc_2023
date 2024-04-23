/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Pays;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Structure;
import bf.menapln.entity.Type;
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
public class CandidatJuryRepository extends Repository implements InterfaceDAO{

    public CandidatJuryRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        CandidatJury candidat = (CandidatJury)objet;
        String sql;
        sql = "INSERT INTO candidatJury(session_id,jury_id,candidat_id,centreSecondaire_id)\n"
                +"VALUES(?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, candidat.getSession().getId());
        pstmt.setLong(2, candidat.getJury().getId());
        pstmt.setLong(3, candidat.getCandidat().getId());
        if(candidat.getCentreSecondaire() == null){
            pstmt.setObject(4, null);
        }else{
            pstmt.setLong(4, candidat.getCentreSecondaire().getId());
        }
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
        String sql = "SELECT * FROM viewCandidatJury";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("jury_id") != null){
                candidat.setJury(Jury.id(rs.getLong("jury_id")));
                candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            }
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }
    public List<Objet> getCandidatOrdered() throws SQLException {
        String sql = "SELECT * FROM viewCandidatJury ORDER BY juryLibelle,nom,prenom";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            candidat.setJury(Jury.id(rs.getLong("jury_id")));
            candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            candidat.setCandidat(new Candidat(rs));
            candidat.getCandidat().setEtablissementCandidat(new Etablissement(rs));
            if(rs.getString("numeroPV") != null){
                candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            }
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }
    
    public List<Objet> getCandidatJury(Serie serie, Localite villeComposition) throws SQLException {
        String sql = "SELECT * FROM viewCandidatJury where serie_id = ? and villeComposition = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, serie.getId());
        stmt.setLong(2, villeComposition.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("jury_id") != null){
                candidat.setJury(Jury.id(rs.getLong("jury_id")));
                candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            }
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            liste.add(candidat);
        }
        return liste;
    }



    public List<Candidat> getCandidatJuryT(Jury jury) throws SQLException {
        String sql;
        PreparedStatement pstmt = null;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? \n"
                +" and tourComposition_id = ? and codeDecisionJury = ?\n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, jury.getId());
                pstmt.setLong(2, 1);
                pstmt.setLong(3, 2);
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ?\n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, jury.getId());
            break;
        }
         ResultSet rs    = pstmt.executeQuery();
         List<Candidat> liste = new ArrayList<Candidat>();
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
             liste.add(candidat);
         }
         return liste;
     }







    
    public List<Objet> getCandidatJury(Jury jury) throws SQLException {
        String sql;
        PreparedStatement stmt = null;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? and codeDecisionJury = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setInt(2, jury.getEtape().getTourCode());
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
            break;
        }
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            candidat.setJury(Jury.id(rs.getLong("jury_id")));
            candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            if(rs.getString("numeroPV") != null){
                candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            }
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }
    
    public List<Objet> getCandidatJury(Jury jury,Serie serie) throws SQLException {
        String sql;
        PreparedStatement stmt = null;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? and serie_id = ? \n"
                +" and tourComposition_id = ? and codeDecisionJury = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setLong(2, serie.getId());
                stmt.setLong(3, 1);
                stmt.setLong(4, 2);
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? and serie_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setLong(2, serie.getId());
            break;
        }
        
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Candidat candidat = Candidat.id(rs.getLong("candidat_id"));
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
            candidat.setNumeroPv(rs.getInt("numeroPV"));
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

    public Objet updateCentreSecondaire(Objet objet) throws SQLException {
        CandidatJury candidatJury = (CandidatJury)objet;
        String sql = "UPDATE candidatJury set centreSecondaire_id = ? where session_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        if(candidatJury.getCentreSecondaire() == null){
            pstmt.setObject(1, null);
        }else{
            pstmt.setLong(1, candidatJury.getCentreSecondaire().getId());
        }
        pstmt.setLong(2, candidatJury.getSession().getId());
        pstmt.setLong(3, candidatJury.getCandidat().getId());
        pstmt.executeUpdate();
        return candidatJury;
    }

    public List<Objet> getCandidatJurySansSerie(Jury jury) throws SQLException {
        String sql;
        PreparedStatement stmt = null;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? \n"
                +" and tourComposition_id = ? and codeDecisionJury = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setLong(2, 1);
                stmt.setLong(3, 2);
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
            break;
        }
        
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Candidat candidat = Candidat.id(rs.getLong("candidat_id"));
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
            candidat.setNumeroPv(rs.getInt("numeroPV"));
            candidat.setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatByCeb(Structure ceb) throws SQLException {
        String sql = "SELECT * FROM viewCandidatJury where parent_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, ceb.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("jury_id") != null){
                candidat.setJury(Jury.id(rs.getLong("jury_id")));
                candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            }
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            //candidat.getCandidat().setVilleComposition(Localite.id(rs.getLong("villeComposition")));
            //candidat.getCandidat().getVilleComposition().setNomLoclite(rs.getString("nomLocalite"));
            
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatByCebByJury(Structure ceb,Jury jury) throws SQLException {
        String sql = "SELECT * FROM viewCandidatJury where parent_id = ? and jury_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, ceb.getId());
        stmt.setLong(2, jury.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("jury_id") != null){
                candidat.setJury(Jury.id(rs.getLong("jury_id")));
                candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            }
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            //candidat.getCandidat().setVilleComposition(Localite.id(rs.getLong("villeComposition")));
            //candidat.getCandidat().getVilleComposition().setNomLoclite(rs.getString("nomLocalite"));
            
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatByCebByJuryByCentre(Structure ceb,Jury jury,Structure centre) throws SQLException {
        String sql = "SELECT * FROM viewCandidatJury where parent_id = ? and jury_id = ? and centre_id = ?";
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, ceb.getId());
        stmt.setLong(2, jury.getId());
        stmt.setLong(3, centre.getId());
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("jury_id") != null){
                candidat.setJury(Jury.id(rs.getLong("jury_id")));
                candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            }
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            //candidat.getCandidat().setVilleComposition(Localite.id(rs.getLong("villeComposition")));
            //candidat.getCandidat().getVilleComposition().setNomLoclite(rs.getString("nomLocalite"));
            
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatCentre(CentreSecondaire centreSecondaire) throws SQLException {
        String sql;
        PreparedStatement stmt = null;
        
        switch(centreSecondaire.getJury().getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where centreSecondaire_id = ? and codeDecisionJury = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, centreSecondaire.getId());
                stmt.setInt(2, centreSecondaire.getJury().getEtape().getTourCode());
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where centreSecondaire_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, centreSecondaire.getId());
            break;
        }
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            if(rs.getString("centreSecondaire_id") != null){
                candidat.setCentreSecondaire(CentreSecondaire.id(rs.getLong("centreSecondaire_id")));

            }
            if(rs.getString("libelle") != null){
                candidat.getCentreSecondaire().setLibelle(rs.getString("libelle"));
            }
            
            candidat.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            candidat.getCandidat().setIue(rs.getString("iue"));
            candidat.getCandidat().setNom(rs.getString("nom"));
            candidat.getCandidat().setPrenom(rs.getString("prenom"));
            candidat.getCandidat().setSexe(rs.getString("sexe"));
            candidat.getCandidat().setTelephone(rs.getString("telephone"));
            candidat.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            candidat.getCandidat().setLieuNaissance(rs.getString("lieuNaissance"));
            candidat.getCandidat().setNumeroActeNaissance(rs.getLong("numeroActeNaissance"));
            candidat.getCandidat().setLienActeNaissance(rs.getString("lienActeNaissance"));
            candidat.getCandidat().setLienPhoto(rs.getString("lienPhoto"));
            if(rs.getString("numeroPV") != null){
                candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            }
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatJurySerie(Jury jury,Serie serie) throws SQLException {
        String sql;
        PreparedStatement stmt = null;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? and serie_id = ? and codeDecisionJury = ? and centreSecondaire_id is null\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setLong(2, serie.getId());
                stmt.setInt(3, jury.getEtape().getTourCode());
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where jury_id = ? and serie_id = ? and centreSecondaire_id is null\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, jury.getId());
                stmt.setLong(2, serie.getId());
            break;
        }
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            candidat.setJury(Jury.id(rs.getLong("jury_id")));
            candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            candidat.setCandidat(new Candidat(rs));
            candidat.getCandidat().setEtablissementCandidat(new Etablissement(rs));
            if(rs.getString("numeroPV") != null){
                candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            }
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
           // candidat.getCandidat().setVilleComposition(Localite.id(rs.getLong("villeComposition")));
           // candidat.getCandidat().getVilleComposition().setNomLoclite(rs.getString("nomLocalite"));
            
            liste.add(candidat);
        }
        return liste;
    }

    public List<Objet> getCandidatBySerieAndCentre(Jury jury,CentreSecondaire centreSecondaire, Serie serie) throws SQLException {
        String sql;
        PreparedStatement stmt = null;

        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatJury where centreSecondaire_id = ? and codeDecisionJury = ? and serie_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, centreSecondaire.getId());
                stmt.setInt(2, centreSecondaire.getJury().getEtape().getTourCode());
                stmt.setLong(3, serie.getId());
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where centreSecondaire_id = ? and serie_id = ?\n"
                +" ORDER BY numeroPV";
                stmt = this.factory.connect().prepareStatement(sql);
                stmt.setLong(1, centreSecondaire.getId());
                stmt.setLong(2, serie.getId());

            break;
        }
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatJury candidat = new CandidatJury();
            candidat.setJury(Jury.id(rs.getLong("jury_id")));
            candidat.getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            candidat.setCandidat(new Candidat(rs));
            candidat.getCandidat().setEtablissementCandidat(new Etablissement(rs));
            if(rs.getString("numeroPV") != null){
                candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            }
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
           // candidat.getCandidat().setVilleComposition(Localite.id(rs.getLong("villeComposition")));
           // candidat.getCandidat().getVilleComposition().setNomLoclite(rs.getString("nomLocalite"));
            
            liste.add(candidat);
        }
        return liste;
    }
    
    public Objet updateAptitude(Objet objet) throws SQLException {
        CandidatJury candidatJury = (CandidatJury)objet;
        if(candidatJury.getCandidat().getSport() == "Inapte"|| candidatJury.getCandidat().getSport().equals("Inapte")){
            String sql = "UPDATE inscription set sport = ? where candidat_id = ?";
            PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
            pstmt.setString(1, candidatJury.getCandidat().getSport());
            pstmt.setLong(2, candidatJury.getCandidat().getId());
            pstmt.executeUpdate();
            String sql1 = "INSERT INTO dispense(session_id,candidat_id,epreuve_id) VALUES(?,?,?)";
            PreparedStatement pstmt2 = this.factory.connect().prepareStatement(sql1);
            pstmt2.setLong(1, 1);
            pstmt2.setLong(2, candidatJury.getCandidat().getId());
            pstmt2.setLong(3, 13);
            pstmt2.executeUpdate();

        }else if(candidatJury.getCandidat().getSport() == "Apte" || candidatJury.getCandidat().getSport().equals("Apte")){
            String sql = "UPDATE inscription set sport = ? where session_id = ? and candidat_id = ?";
            PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
            pstmt.setString(1, candidatJury.getCandidat().getSport());
            pstmt.setLong(2, 1);
            pstmt.setLong(3, candidatJury.getCandidat().getId());
            pstmt.executeUpdate();
            String sql2 = "DELETE FROM dispense where candidat_id = ?";
            PreparedStatement pstmt2 = this.factory.connect().prepareStatement(sql2);
            pstmt2.setLong(1, candidatJury.getCandidat().getId());
            pstmt2.executeUpdate();
        }
       
        return candidatJury;
    }

    public Objet updateSerie(Objet objet) throws SQLException {
        CandidatJury candidatJury = (CandidatJury)objet;
            String sql = "UPDATE inscription set serie_id = ? where candidat_id = ?";
            PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
            pstmt.setLong(1, candidatJury.getCandidat().getSerie().getId());
            pstmt.setLong(2, candidatJury.getCandidat().getId());
            pstmt.executeUpdate();
        return candidatJury;
    }
    
}
