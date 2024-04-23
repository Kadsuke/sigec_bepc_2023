package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.CandidatSalle;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Pays;
import bf.menapln.entity.SalleComposition;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandidatSalleRepository extends Repository implements InterfaceDAO{

    public CandidatSalleRepository(Factory factory) {
        super(factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        CandidatSalle candidatSalle = (CandidatSalle)objet;
        String sql;
        sql = "INSERT INTO candidatSalle(salleComposition_id,candidat_id)\n"
                +"VALUES(?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, candidatSalle.getSalleComposition().getId());
        pstmt.setLong(2, candidatSalle.getCandidat().getId());
        pstmt.executeUpdate();
        return candidatSalle;    }

    @Override
    public Objet delete(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upadte'");
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        String sql = "SELECT * FROM viewCandidatSalle";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatSalle candidat = new CandidatSalle();
            if(rs.getString("salleComposition_id") != null){
                candidat.setSalleComposition(SalleComposition.id(rs.getLong("salleComposition_id")));
                candidat.getSalleComposition().setLibelleSalle(rs.getString("libelleSalle"));
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
        String sql = "SELECT * FROM viewCandidatSalle ORDER BY libelleSalle,nom,prenom";
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CandidatSalle candidat = new CandidatSalle();
            if(rs.getString("salleComposition_id") != null){
                candidat.setSalleComposition(SalleComposition.id(rs.getLong("salleComposition_id")));
                candidat.getSalleComposition().setLibelleSalle(rs.getString("libelleSalle"));
            }
            if(rs.getString("session_id") != null){
                candidat.getSalleComposition().setSession(Session.id(rs.getLong("session_id")));
                candidat.getSalleComposition().getSession().setAnnee(rs.getLong("annee"));
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
            if(rs.getString("numeroPV") != null){candidat.getCandidat().setNumeroPv(rs.getInt("numeroPV"));}
            candidat.getCandidat().setPaysNaissance(Pays.id(rs.getLong("paysNaissance_id")));
            candidat.getCandidat().setNationalite(Pays.id(rs.getLong("paysNationalite_id")));
            candidat.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            candidat.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            liste.add(candidat);
        }
        return liste;
    }
    
    /* public List<Objet> getCandidatJury(Serie serie, Localite villeComposition) throws SQLException {
        String sql = "SELECT * FROM viewCandidatSalle where serie_id = ? and villeComposition = ?";
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
    } */



    public List<Candidat> getCandidatJuryT(SalleComposition salleComposition) throws SQLException {
        String sql;
        PreparedStatement pstmt = null;
        switch(salleComposition.getJury().getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatSalle where salleComposition_id = ? \n"
                +" and tourComposition_id = ? \n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, salleComposition.getId());
                pstmt.setLong(2, 1);
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where salleComposition_id = ?\n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, salleComposition.getId());
            break;
        }
         ResultSet rs = pstmt.executeQuery();
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


    
    public List<Objet> getCandidatSalle(SalleComposition salleComposition) throws SQLException, NullPointerException {
        String sql;
        PreparedStatement pstmt = null;
        switch(salleComposition.getJury().getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewCandidatSalle where salleComposition_id = ? \n"
                +" and tourComposition_id = ? \n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, salleComposition.getId());
                pstmt.setLong(2, 1);
            break;
            default:
                sql = "SELECT * FROM viewCandidatJury where salleComposition_id = ?\n"
                +" ORDER BY numeroPV";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, salleComposition.getId());
            break;
        }
         ResultSet rs = pstmt.executeQuery();
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
             liste.add(candidat);
         }
         return liste;
    }
    
   /*  public List<Objet> getCandidatJury(Jury jury,Serie serie) throws SQLException {
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
     */
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
                stmt.setLong(3, 1);
                stmt.setLong(4, 2);
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
    
    
}
