/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Inscription;
import bf.menapln.entity.Objet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class InscriptionRepository extends Repository implements InterfaceDAO{

    public InscriptionRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Inscription inscription = (Inscription)objet;
        String sql;
        sql = "INSERT INTO inscription(session_id,candidat_id,etablissement_id,typeCandidat_id,serie_id,\n"
                + "typeInscription_id,natureHandicap_id,prescription_id,secteurVillage,\n"
                + "sport,dernierDiplome,anneeDernierDiplome,dateInscription,centreExamen_id,concoursRatache)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, inscription.getSession().getId());
        pstmt.setLong(2, inscription.getCandidat().getId());
        pstmt.setLong(3, inscription.getCandidat().getEtablissementCandidat().getId());
        pstmt.setLong(4, inscription.getCandidat().getTypeCandidat().getId());
        pstmt.setLong(5, inscription.getCandidat().getSerie().getId());
        pstmt.setLong(6, inscription.getCandidat().getTypeInscription().getId());
        if(inscription.getCandidat().getNatureHandicap() == null){
            pstmt.setObject(7, null);
        }else{
            pstmt.setLong(7, inscription.getCandidat().getNatureHandicap().getId());
        }
        if(inscription.getCandidat().getPrescriptionHandicap() == null){
            pstmt.setObject(8, null);
        }else{
            pstmt.setLong(8, inscription.getCandidat().getPrescriptionHandicap().getId());
        }
        pstmt.setLong(9, inscription.getCandidat().getSecteur().getId());
        pstmt.setString(10, inscription.getCandidat().getSport());
        pstmt.setString(11, inscription.getCandidat().getDernierDiplome());
        pstmt.setLong(12, inscription.getCandidat().getAnneeDernierDiplome());
        pstmt.setString(13, inscription.getAddDate().toString());
        pstmt.setLong(14, inscription.getCandidat().getCentreExamen().getId());
        pstmt.setLong(15, inscription.getCandidat().getConcoursRatache().getId());
        pstmt.executeUpdate();
        return inscription;
    }

    @Override
    public Objet delete(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Objet upadtePV(Objet objet) throws SQLException {
        Inscription inscription = (Inscription)objet;
        String sql = "UPDATE inscription set numeroPV = ? where session_id = ? and candidat_id = ?";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, inscription.getCandidat().getNumeroPv());
        pstmt.setLong(2, inscription.getSession().getId());
        pstmt.setLong(3, inscription.getCandidat().getId());
        pstmt.executeUpdate();
        return inscription;
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
