/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Composition;
import bf.menapln.entity.ConcoursRatache;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Inscription;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Membre;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Personnel;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class FeuilleCompositionRepository extends Repository implements InterfaceDAO{

    public FeuilleCompositionRepository(Factory factory) {
        super(factory);
    }

    @Override
     public Objet save(Objet objet) throws SQLException {
        FeuilleComposition feuile = (FeuilleComposition)objet;
        String sql;
        sql = "INSERT INTO feuilleComposition(session_id,jury_id,tourComposition_id,serie_id,\n"
                +" epreuve_id,candidat_id,presence,cycle)\n"
                +"VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, feuile.getComposition().getSession().getId());
        pstmt.setLong(2, feuile.getComposition().getJury().getId());
        pstmt.setLong(3, feuile.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuile.getCandidat().getSerie().getId());
        pstmt.setLong(5, feuile.getComposition().getEpreuve().getId());
        pstmt.setLong(6, feuile.getCandidat().getId());
        pstmt.setBoolean(7, feuile.isPresent());
        pstmt.setInt(8, 1);
        pstmt.executeUpdate();
        feuile.setCycle(1);
        return feuile;
    }

    public Objet updatePresence(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set presence = ? where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setBoolean(1, feuille.isPresent());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        return feuille;
    }   

    @Override
    public Objet delete(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Objet upadteAnonymat(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set anonymat = ?,cycle = 2 where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setString(1, feuille.getAnonymat());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        feuille.setCycle(2);
        return feuille;
    }
    
    public Objet upadteCorrecteur(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set correcteur_id = ?,cycle = 3  where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, feuille.getCorrecteur().getId());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        feuille.setCycle(3);
        return feuille;
    }
    
    public Objet upadteNote(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set note = ? where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setDouble(1, feuille.getNote());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        return feuille;
    }

    public Objet upadtePresence(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set presence = ? where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setBoolean(1, feuille.isPresent());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        return feuille;
    }
    
    public Objet verouiller(Objet objet) throws SQLException {
        FeuilleComposition feuille = (FeuilleComposition)objet;
        String sql = "UPDATE feuilleComposition set verouille = ? where session_id = ? and tourComposition_id = ? and epreuve_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setBoolean(1, feuille.isVerouille());
        pstmt.setLong(2, feuille.getComposition().getSession().getId());
        pstmt.setLong(3, feuille.getComposition().getTourComposition().getId());
        pstmt.setLong(4, feuille.getComposition().getEpreuve().getId());
        pstmt.setLong(5, feuille.getCandidat().getId());
        pstmt.executeUpdate();
        return feuille;
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Objet> getFeuille(Session session, Jury jury, Serie serie, Epreuve epreuve, Type tour) throws SQLException{
        String sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and serie_id = ? and epreuve_id = ? and tourComposition_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, serie.getId());
        pstmt.setLong(4, epreuve.getId());
        pstmt.setLong(5, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setComposition(new Composition());
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));
            feuille.setCycle(rs.getInt("cycle"));

            
            feuille.getComposition().setSession(Session.id(rs.getLong("session_id")));
            feuille.getComposition().getSession().setAnnee(rs.getLong("annee"));
            
            
            feuille.getComposition().setJury(Jury.id(rs.getLong("jury_id")));
            feuille.getComposition().getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            feuille.getComposition().setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            feuille.getComposition().getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            feuille.getComposition().getEpreuve().setCoefficient(rs.getLong("coefficient"));
            
            
            
            feuille.getComposition().setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            feuille.getComposition().getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            
            feuille.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            feuille.getCandidat().setIue(rs.getString("iue"));
            feuille.getCandidat().setNom(rs.getString("nom"));
            feuille.getCandidat().setPrenom(rs.getString("prenom"));
            feuille.getCandidat().setSexe(rs.getString("sexe"));
            feuille.getCandidat().setTelephone(rs.getString("telephone"));
            feuille.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            feuille.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            feuille.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            //feuille.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            feuille.getCandidat().setInscription(new Inscription());
            feuille.getCandidat().getInscription().setDispenses(this.getCandidatDispence(session, feuille.getCandidat()));
            
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            
            liste.add(feuille);
        }
        return liste;
    }
    
    public List<Objet> getFeuille(Session session, Jury jury, Serie serie, Type tour) throws SQLException{
        String sql;
        PreparedStatement pstmt;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ?";
                String sql2 = "SELECT f.* FROM viewFeuilleComposition f \n"
                +"INNER JOIN viewResultat r ON r.candidat_id = f.candidat_id where \n"
                +"f.session_id = ? and f.jury_id = ? and f.serie_id = ? \n"
                +"and f.tourComposition_id = ? and f.estComposeSecTour = ? and r.codeDecisionJury = ?";
                pstmt = this.factory.connect().prepareStatement(sql+" UNION "+sql2+" ORDER BY epreuveLibelle");
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, tour.getId());
                pstmt.setLong(5, session.getId());
                pstmt.setLong(6, jury.getId());
                pstmt.setLong(7, serie.getId());
                pstmt.setLong(8, 1);
                pstmt.setBoolean(9, false);
                pstmt.setLong(10, 2);
            break;
            default:
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ? ORDER BY epreuveLibelle";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, tour.getId());
            break;
        }
        ResultSet rs    = pstmt.executeQuery();
        int i = 0;
        List<Objet> liste = new ArrayList<Objet>();
        Map candIds = new HashMap();
        Map epreuveIds = new HashMap();
        Candidat candidat = null;
        int indice = 0;
        int epreuveIndice = 0;
        while (rs.next()) {
            if(!candIds.containsKey(rs.getLong("candidat_id"))){
                candidat = new Candidat(rs);
                
                candidat.setCopies(new ArrayList());
                
                candidat.setSerie(serie);
                candidat.setInscription(new Inscription());
                candidat.getInscription().setDispenses(this.getCandidatDispence(session, candidat));
                candidat.getInscription().setSession(session);
                
                candIds.put(candidat.getId(), indice);
                liste.add(candidat);
                indice++;
            }
            
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setCandidat(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
            feuille.setComposition(new Composition());
            
            //feuille.getComposition().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            if(!epreuveIds.containsKey(rs.getLong("epreuve_id"))){
                Epreuve epreuve = (Epreuve.id(rs.getLong("epreuve_id")));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
            
                serie.addEpreuve(epreuve);
                
                if(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getInscription().getDispenses().containsKey(epreuve.getId())){
                    ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setTotalCoefDispense(epreuve.getCoefficient().intValue());
                }
                
                epreuveIds.put(epreuve.getId(), epreuveIndice);
                epreuveIndice++;
            }
            
            //((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).initTotalCoef(indice);
            
            feuille.getComposition().setEpreuve(serie.getEpreuves().get((int) epreuveIds.get(rs.getLong("epreuve_id"))));
                        
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));

            
           // System.out.println("PV "+candida"Candidat Eli"+candidat.getElimine()+"Presence"+feuille.isPresent());
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            feuille.getComposition().setSession(session);
            
            feuille.getComposition().setJury(jury);
            
            feuille.getComposition().setTourComposition(tour);
             
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(!((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getElimine() && (!feuille.isPresent())){
                ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setElimine(true);
            }

            ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).addCopie(feuille);
            i++;
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
    
    public Map getCandidatDispence(Session session,Candidat candidat) throws SQLException {
        String sql = "SELECT * FROM dispense d INNER JOIN epreuve e ON e.epreuve_id = d.epreuve_id where \n"
        +"session_id = ? and candidat_id = ? ORDER BY epreuveLibelle";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, candidat.getId());
        ResultSet rs    = pstmt.executeQuery();
        Map dispenses = new HashMap();
        while(rs.next()){
            dispenses.put(rs.getLong("epreuve_id"), new Epreuve(rs,1));
        }
        return dispenses;
    }
    
    @Override
    public Long lastInsertedId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Objet> getFeuilleWithoutSerie(Session session, Jury jury, Epreuve epreuve, Type tour) throws SQLException{
        String sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setComposition(new Composition());
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));
            feuille.setCycle(rs.getInt("cycle"));
            
            feuille.getComposition().setSession(Session.id(rs.getLong("session_id")));
            feuille.getComposition().getSession().setAnnee(rs.getLong("annee"));
            
            
            feuille.getComposition().setJury(Jury.id(rs.getLong("jury_id")));
            feuille.getComposition().getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            feuille.getComposition().setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            feuille.getComposition().getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            feuille.getComposition().getEpreuve().setCoefficient(rs.getLong("coefficient"));

            
            
            feuille.getComposition().setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            feuille.getComposition().getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            
            feuille.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            feuille.getCandidat().setIue(rs.getString("iue"));
            feuille.getCandidat().setNom(rs.getString("nom"));
            feuille.getCandidat().setPrenom(rs.getString("prenom"));
            feuille.getCandidat().setSexe(rs.getString("sexe"));
            feuille.getCandidat().setTelephone(rs.getString("telephone"));
            feuille.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            feuille.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            
            feuille.getCandidat().setInscription(new Inscription());
            feuille.getCandidat().getInscription().setDispenses(this.getCandidatDispence(session, feuille.getCandidat()));
            
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            
            liste.add(feuille);
        }
        return liste;
    }
/* 
    public List<Objet> getFeuilleWithoutSerieForCorrecteur(Session session, Jury jury, Epreuve epreuve, Correcteur correcteur, Type tour) throws SQLException{
        String sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ? and correcteur_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        pstmt.setLong(5, correcteur.getPersonnel().getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setComposition(new Composition());
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));
            
            feuille.getComposition().setSession(Session.id(rs.getLong("session_id")));
            feuille.getComposition().getSession().setAnnee(rs.getLong("annee"));
            
            feuille.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
            feuille.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            feuille.getComposition().setJury(Jury.id(rs.getLong("jury_id")));
            feuille.getComposition().getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            feuille.getComposition().setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            feuille.getComposition().getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            feuille.getComposition().getEpreuve().setCoefficient(rs.getLong("coefficient"));
            
            
            
            feuille.getComposition().setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            feuille.getComposition().getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            
            feuille.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            feuille.getCandidat().setIue(rs.getString("iue"));
            feuille.getCandidat().setNom(rs.getString("nom"));
            feuille.getCandidat().setPrenom(rs.getString("prenom"));
            feuille.getCandidat().setSexe(rs.getString("sexe"));
            feuille.getCandidat().setTelephone(rs.getString("telephone"));
            feuille.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            feuille.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            
            feuille.getCandidat().setInscription(new Inscription());
            feuille.getCandidat().getInscription().setDispenses(this.getCandidatDispence(session, feuille.getCandidat()));
            
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            
            liste.add(feuille);
        }
        return liste;
    }
    */ 
    
    public List<Objet> getFeuilleWithoutSerieForCorrecteur(Session session, Jury jury, Epreuve epreuve, Correcteur correcteur, Type tour) throws SQLException{
        String sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ? and correcteur_id = ?";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        pstmt.setLong(5, correcteur.getPersonnel().getId());
        ResultSet rs    = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setComposition(new Composition());
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));
            
            feuille.getComposition().setSession(Session.id(rs.getLong("session_id")));
            feuille.getComposition().getSession().setAnnee(rs.getLong("annee"));
            
            
            feuille.getComposition().setJury(Jury.id(rs.getLong("jury_id")));
            feuille.getComposition().getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            feuille.getComposition().setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            feuille.getComposition().getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            feuille.getComposition().getEpreuve().setCoefficient(rs.getLong("coefficient"));
            
            
            
            feuille.getComposition().setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            feuille.getComposition().getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            
            feuille.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            feuille.getCandidat().setIue(rs.getString("iue"));
            feuille.getCandidat().setNom(rs.getString("nom"));
            feuille.getCandidat().setPrenom(rs.getString("prenom"));
            feuille.getCandidat().setSexe(rs.getString("sexe"));
            feuille.getCandidat().setTelephone(rs.getString("telephone"));
            feuille.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            feuille.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            feuille.getCandidat().setSerie(Serie.id(rs.getLong("serie_id")));
          //  feuille.getCandidat().getSerie().setSerieLibelle(rs.getString("serieLibelle"));
            
            
            feuille.getCandidat().setInscription(new Inscription());
            feuille.getCandidat().getInscription().setDispenses(this.getCandidatDispence(session, feuille.getCandidat()));
            
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            
            liste.add(feuille);
        }
        return liste;
    }
  

    public List<Objet> getFeuilleByConcoursRatache(Session session, Jury jury,Serie serie, Type concours, Type tour) throws SQLException{

        String sql;
        PreparedStatement pstmt;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and concoursRatache = ? and tourComposition_id = ?";
                String sql2 = "SELECT f.* FROM viewFeuilleComposition f \n"
                +"INNER JOIN viewResultat r ON r.candidat_id = f.candidat_id where \n"
                +"f.session_id = ? and f.jury_id = ? and f.serie_id = ? and f.concoursRatache = ? \n"
                +"and f.tourComposition_id = ? and f.estComposeSecTour = ? and r.codeDecisionJury = ? and estTypeFrancais = ?";
                pstmt = this.factory.connect().prepareStatement(sql+" UNION "+sql2+" ORDER BY epreuveLibelle");
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, concours.getId());
                pstmt.setLong(5, tour.getId());
                pstmt.setLong(6, session.getId());
                pstmt.setLong(7, jury.getId());
                pstmt.setLong(8, serie.getId());
                pstmt.setLong(9, concours.getId());
                pstmt.setLong(10, 1);
                pstmt.setBoolean(11, false);
                pstmt.setLong(12, 2);
                pstmt.setBoolean(13, false);

            break;
            default:
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and concoursRatache = ? and tourComposition_id = ? ORDER BY epreuveLibelle";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, concours.getId());
                pstmt.setLong(5, tour.getId());
            break;
        }
        ResultSet rs    = pstmt.executeQuery();
        int i = 0;
        List<Objet> liste = new ArrayList<Objet>();
        List<Epreuve> epreuveListe = new ArrayList<Epreuve>();
        Map candIds = new HashMap();
        Map epreuveIds = new HashMap();
        Map concoursIds = new HashMap();
        Candidat candidat = null;
        Epreuve epreuveN = null;
        int indice = 0;
        int epreuveIndice = 0;
        while (rs.next()) {
            if(!candIds.containsKey(rs.getLong("candidat_id"))){
                candidat = new Candidat(rs);
                
                candidat.setCopies(new ArrayList());
                
                candidat.setSerie(serie);
                candidat.setInscription(new Inscription());
                candidat.getInscription().setDispenses(this.getCandidatDispence(session, candidat));
                candidat.getInscription().setSession(session);
                
                candIds.put(candidat.getId(), indice);
                liste.add(candidat);
                indice++;
            }
            
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setCandidat(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
            feuille.setComposition(new Composition());
            
            Double noteMaxFr = 0.0;
            Double noteMath1 = 0.0;
            if(!epreuveIds.containsKey(rs.getLong("epreuve_id"))){
                Epreuve epreuve = (Epreuve.id(rs.getLong("epreuve_id")));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
            if(epreuve.getEpreuveLibelle().equals("Français")){
                epreuve.setEpreuvesLie(this.getEpreuveTypeFrancais(session, jury, serie, tour, ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id"))))));
                epreuve.setNote(rs.getDouble("note"));
                noteMaxFr = epreuve.maxMoyenne();
            }
                serie.addEpreuve(epreuve);
                
                if(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getInscription().getDispenses().containsKey(epreuve.getId())){
                    ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setTotalCoefDispense(epreuve.getCoefficient().intValue());
                }
                
                epreuveIds.put(epreuve.getId(), epreuveIndice);
                epreuveIndice++;
            }
            
            
            feuille.getComposition().setEpreuve(serie.getEpreuves().get((int) epreuveIds.get(rs.getLong("epreuve_id"))));
                        
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));

            
            if(rs.getString("note") != null){
                 if(rs.getString("epreuveLibelle").equals("Français")){
                    Double arrondir = (double) Math.round(noteMaxFr);
                    feuille.setNote(arrondir);
                }else if(rs.getString("epreuveLibelle").equals("Mathematique")){
                   noteMath1 = this.getEpreuveTypeMath(session, jury, serie, tour, ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
                    if(noteMath1 > rs.getDouble("note")){
                        feuille.setNote(noteMath1);
                    }else{
                        feuille.setNote(rs.getDouble("note"));
                    }
                }
                else{
                    feuille.setNote(rs.getDouble("note"));

               }
            }
            
            feuille.getComposition().setSession(session);
            
            feuille.getComposition().setJury(jury);
            
            feuille.getComposition().setTourComposition(tour);
             
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(!((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getElimine() && (!feuille.isPresent())){
                ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setElimine(true);
            }

            ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).addCopie(feuille);
            i++;
        }
        return liste;
    }


    public List<Epreuve> getEpreuveTypeFrancais(Session session, Jury jury,Serie serie, Type tour, Candidat candidat) throws SQLException{
        String sql;
        PreparedStatement pstmt; 
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ? and estTypeFrancais = ? and candidat_id = ? ORDER BY epreuveLibelle";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, 1);
                pstmt.setLong(5, 1);
                pstmt.setLong(6, candidat.getId());

        ResultSet rs    = pstmt.executeQuery();
        
        List<Epreuve> epreuveListe = new ArrayList<Epreuve>();
        while (rs.next()) {         
                Epreuve epreuve = (Epreuve.id(rs.getLong("epreuve_id")));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                epreuve.setNote(rs.getDouble("note"));
                epreuveListe.add(epreuve);                
        }
        this.factory.close();
        return epreuveListe;
    }

     public Double getEpreuveTypeMath(Session session, Jury jury,Serie serie, Type tour, Candidat candidat) throws SQLException{
        String sql;
        PreparedStatement pstmt; 
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ? and epreuveLibelle = ? and candidat_id = ? ORDER BY epreuveLibelle";
                pstmt = this.factory.connect().prepareStatement(sql);
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, 1);
                pstmt.setString(5, "Mathematique");
                pstmt.setLong(6, candidat.getId());
        ResultSet rs    = pstmt.executeQuery();
        
        Double note = (rs.getDouble("note"));
        this.factory.close();
        return note;
    }

    public List<Objet> getFeuilleTest(Session session, Jury jury ,Epreuve epreuve, Type tour) throws SQLException{
        String sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and epreuve_id = ? and tourComposition_id = ? order by numeroPV";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, epreuve.getId());
        pstmt.setLong(4, tour.getId());
        ResultSet rs  = pstmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setComposition(new Composition());
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));
            feuille.setCycle(rs.getInt("cycle"));
            
            feuille.getComposition().setSession(Session.id(rs.getLong("session_id")));
            feuille.getComposition().getSession().setAnnee(rs.getLong("annee"));
            
            
            feuille.getComposition().setJury(Jury.id(rs.getLong("jury_id")));
            feuille.getComposition().getJury().setJuryLibelle(rs.getString("juryLibelle"));
            
            feuille.getComposition().setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
            feuille.getComposition().getEpreuve().setEpreuveLibelle(rs.getString("epreuveLibelle"));
            feuille.getComposition().getEpreuve().setCoefficient(rs.getLong("coefficient"));
            
            
            
            feuille.getComposition().setTourComposition(Type.id(rs.getLong("tourComposition_id")));
            feuille.getComposition().getTourComposition().setLibelle(rs.getString("tourCompositionLibelle"));
            
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            
            feuille.setCandidat(Candidat.id(rs.getLong("candidat_id")));
            feuille.getCandidat().setIue(rs.getString("iue"));
            feuille.getCandidat().setNom(rs.getString("nom"));
            feuille.getCandidat().setPrenom(rs.getString("prenom"));
            feuille.getCandidat().setSexe(rs.getString("sexe"));
            feuille.getCandidat().setTelephone(rs.getString("telephone"));
            feuille.getCandidat().setDateNaissance(LocalDate.parse(rs.getString("dateNaissance")));
            feuille.getCandidat().setNumeroPv(rs.getInt("numeroPV"));
            
            feuille.getCandidat().setInscription(new Inscription());
            feuille.getCandidat().getInscription().setDispenses(this.getCandidatDispence(session, feuille.getCandidat()));
            
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(rs.getString("note") != null){
                feuille.setNote(rs.getDouble("note"));
            }
            
            
            liste.add(feuille);
        }
        return liste;
    }
    

    
     public List<Objet> getAllByFeuille(Session session, Jury jury, Serie serie,Type concours, Type tour) throws SQLException{

        String sql;
        PreparedStatement pstmt;
        switch(jury.getEtape().getTourCode()){
            case 2:
                sql = "SELECT * FROM viewFeuilleComposition where \n"
                +"session_id = ? and jury_id = ? and serie_id = ? and tourComposition_id = ?";
                String sql2 = "SELECT f.* FROM viewFeuilleComposition f \n"
                +"INNER JOIN viewResultat r ON r.candidat_id = f.candidat_id where \n"
                +"f.session_id = ? and f.jury_id = ? and f.serie_id = ? \n"
                +"and f.tourComposition_id = ? and f.estComposeSecTour = ? and r.codeDecisionJury = ? and estTypeFrancais = ?";
                pstmt = this.factory.connect().prepareStatement(sql+" UNION "+sql2+" ORDER BY epreuveLibelle");
                pstmt.setLong(1, session.getId());
                pstmt.setLong(2, jury.getId());
                pstmt.setLong(3, serie.getId());
                pstmt.setLong(4, tour.getId());
                pstmt.setLong(5, session.getId());
                pstmt.setLong(6, jury.getId());
                pstmt.setLong(7, serie.getId());
                pstmt.setLong(8, 1);
                pstmt.setBoolean(9, false);
                pstmt.setLong(10, 2);
                pstmt.setBoolean(11, false);

            break;
            default:
            sql = "SELECT * FROM viewFeuilleComposition where \n"
            +"session_id = ? and jury_id = ? and serie_id = ? and concoursRatache = ? and tourComposition_id = ? ORDER BY epreuveLibelle";
            pstmt = this.factory.connect().prepareStatement(sql);
            pstmt.setLong(1, session.getId());
            pstmt.setLong(2, jury.getId());
            pstmt.setLong(3, serie.getId());
            pstmt.setLong(4, concours.getId());
            pstmt.setLong(5, tour.getId());
            break;
        }
        ResultSet rs    = pstmt.executeQuery();
        int i = 0;
        List<Objet> liste = new ArrayList<Objet>();
        List<Epreuve> epreuveListe = new ArrayList<Epreuve>();
        Map candIds = new HashMap();
        Map epreuveIds = new HashMap();
        Map concoursIds = new HashMap();
        Candidat candidat = null;
        Epreuve epreuveN = null;
        int indice = 0;
        int epreuveIndice = 0;

        while (rs.next()) {
            if(!candIds.containsKey(rs.getLong("candidat_id"))){
                candidat = new Candidat(rs);
                candidat.setCopies(new ArrayList());
                candidat.setSerie(serie);
                candidat.setInscription(new Inscription());
                candidat.getInscription().setDispenses(this.getCandidatDispence(session, candidat));
                candidat.getInscription().setSession(session);
                candIds.put(candidat.getId(), indice);
                liste.add(candidat);
                indice++;
            }
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setCandidat(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
            feuille.setComposition(new Composition());
            if(!epreuveIds.containsKey(rs.getLong("epreuve_id"))){
                Epreuve epreuve = (Epreuve.id(rs.getLong("epreuve_id")));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                serie.addEpreuve(epreuve);
                
                if(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getInscription().getDispenses().containsKey(epreuve.getId())){
                    ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setTotalCoefDispense(epreuve.getCoefficient().intValue());
                }
                
                epreuveIds.put(epreuve.getId(), epreuveIndice);
                epreuveIndice++;
            }
            
            
            feuille.getComposition().setEpreuve(serie.getEpreuves().get((int) epreuveIds.get(rs.getLong("epreuve_id"))));
                        
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));

            
            if(rs.getString("note") != null){
               feuille.setNote(rs.getDouble("note"));

            }
            
            feuille.getComposition().setSession(session);
            
            feuille.getComposition().setJury(jury);
            
            feuille.getComposition().setTourComposition(tour);
             
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(!((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getElimine() && (!feuille.isPresent())){
                ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setElimine(true);
            }

            ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).addCopie(feuille);
            i++;
        }
        System.out.println("Liste"+liste.size());

        return liste;
    }


    public List<Objet> getAllByFeuilleD(Session session, Jury jury, Serie serie,Type concours, Type tour) throws SQLException{

        String sql;
        PreparedStatement pstmt;

        sql = "SELECT * FROM viewFeuilleComposition where \n"
        +"session_id = ? and jury_id = ? and serie_id = ? and concoursRatache = ? and tourComposition_id = ?";
        String sql2 = "SELECT f.* FROM viewFeuilleComposition f \n"
        +"INNER JOIN viewResultat r ON r.candidat_id = f.candidat_id where \n"
        +"f.session_id = ? and f.jury_id = ? and f.serie_id = ? and f.concoursRatache = ? \n"
        +"and f.tourComposition_id = ? and f.estComposeSecTour = ? and r.codeDecisionJury = ? and estTypeFrancais = ?";
        pstmt = this.factory.connect().prepareStatement(sql+" UNION "+sql2+" ORDER BY epreuveLibelle");
        pstmt.setLong(1, session.getId());
        pstmt.setLong(2, jury.getId());
        pstmt.setLong(3, serie.getId());
        pstmt.setLong(4, concours.getId());
        pstmt.setLong(5, tour.getId());
        pstmt.setLong(6, session.getId());
        pstmt.setLong(7, jury.getId());
        pstmt.setLong(8, serie.getId());
        pstmt.setLong(9, concours.getId());
        pstmt.setLong(10, 1);
        pstmt.setBoolean(11, false);
        pstmt.setLong(12, 2);
        pstmt.setBoolean(13, false);

        ResultSet rs    = pstmt.executeQuery();
        
        int i = 0;
        List<Objet> liste = new ArrayList<Objet>();
        List<Epreuve> epreuveListe = new ArrayList<Epreuve>();
        Map candIds = new HashMap();
        Map epreuveIds = new HashMap();
        Map concoursIds = new HashMap();
        Candidat candidat = null;
        Epreuve epreuveN = null;
        int indice = 0;
        int epreuveIndice = 0;
        while (rs.next()) {

            if(!candIds.containsKey(rs.getLong("candidat_id"))){
                candidat = new Candidat(rs);
                candidat.setCopies(new ArrayList());
                candidat.setSerie(serie);
                candidat.setInscription(new Inscription());
                candidat.getInscription().setDispenses(this.getCandidatDispence(session, candidat));
                candidat.getInscription().setSession(session);
                candIds.put(candidat.getId(), indice);
                liste.add(candidat);
                indice++;
            }            
            FeuilleComposition feuille = new FeuilleComposition();
            feuille.setCandidat(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
            feuille.setComposition(new Composition());
            
            Double noteMaxFr = 0.0;
            Double noteMath1 = 0.0;
            if(!epreuveIds.containsKey(rs.getLong("epreuve_id"))){
                Epreuve epreuve = (Epreuve.id(rs.getLong("epreuve_id")));
                epreuve.setEpreuveLibelle(rs.getString("epreuveLibelle"));
                epreuve.setCoefficient(rs.getLong("coefficient"));
                if(epreuve.getEpreuveLibelle().equals("Français")){
                    epreuve.setEpreuvesLie(this.getEpreuveTypeFrancais(session, jury, serie, tour, ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id"))))));
                    epreuve.setNote(rs.getDouble("note"));
                    noteMaxFr = epreuve.maxMoyenne();
                }
                serie.addEpreuve(epreuve);
                if(((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getInscription().getDispenses().containsKey(epreuve.getId())){
                    ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setTotalCoefDispense(epreuve.getCoefficient().intValue());
                }
                
                epreuveIds.put(epreuve.getId(), epreuveIndice);
                epreuveIndice++;
            }
            feuille.getComposition().setEpreuve(serie.getEpreuves().get((int) epreuveIds.get(rs.getLong("epreuve_id"))));
                        
            feuille.setPresent(rs.getBoolean("presence"));
            feuille.setAnonymat(rs.getString("anonymat"));
            feuille.setVerouille(rs.getBoolean("verouille"));

            if(rs.getString("note") != null){
                  if(rs.getString("epreuveLibelle").equals("Français")){
                    feuille.setNote(noteMaxFr);
                }else if(rs.getString("epreuveLibelle").equals("Mathematique")){
                   noteMath1 = this.getEpreuveTypeMath(session, jury, serie, tour, ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))));
                    if(noteMath1 > rs.getDouble("note")){
                        feuille.setNote(noteMath1);
                    }else{
                        feuille.setNote(rs.getDouble("note"));
                    }
                }
                else{
                    feuille.setNote(rs.getDouble("note"));

               }
            }
            
            feuille.getComposition().setSession(session);
            
            feuille.getComposition().setJury(jury);
            
            feuille.getComposition().setTourComposition(tour);
             
            feuille.getComposition().setDateComposition(LocalDate.parse(rs.getString("dateComposition")));
            feuille.getComposition().setHeureDebutComposition(LocalTime.parse(rs.getString("heureDebutComposition")));
            feuille.getComposition().setHeureFinComposition(LocalTime.parse(rs.getString("heureFinComposition")));
            feuille.getComposition().setFraude(rs.getBoolean("fraude"));
            feuille.getComposition().setObservation(rs.getString("observation"));
            feuille.setCorrecteur(Personnel.id(rs.getLong("correcteur_id")));
            feuille.getCorrecteur().setMatricule(rs.getString("matricule"));
            
            if(!((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).getElimine() && (!feuille.isPresent())){
                ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).setElimine(true);
            }

            ((Candidat)liste.get((int) candIds.get(rs.getLong("candidat_id")))).addCopie(feuille);
            i++;
        }
        this.factory.close();
        return liste;
    }

}
