package bf.menapln.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;

public class CentreSecondaireRepository extends Repository implements InterfaceDAO{

    public CentreSecondaireRepository(Factory factory) {
        super(factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        CentreSecondaire centreSecondaire = (CentreSecondaire)objet;
        String sql;
        sql = "INSERT INTO centreSecondaire(session_id,etablissement_id,jury_id,libelle)\n"
                +"VALUES(?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, centreSecondaire.getSession().getId());
        pstmt.setLong(2, centreSecondaire.getCentreCompositon().getEtatblissement().getId());
        pstmt.setLong(3, centreSecondaire.getJury().getId());
        pstmt.setString(4, centreSecondaire.getLibelle());
        pstmt.executeUpdate();
        return centreSecondaire;
    }

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
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM viewCentreSecondaire where session_id is not null";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        ResultSet rs    = stmt.executeQuery();
         List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CentreSecondaire centreS = new CentreSecondaire();
            centreS.setId(rs.getLong("centreSecondaire_id"));
            centreS.setLibelle(rs.getString("libelle"));
            centreS.setSession(Session.id(rs.getLong("session_id")));
            centreS.getSession().setAnnee(rs.getLong("annee"));
            centreS.setJury(Jury.id(rs.getLong("jury_id")));
            centreS.getJury().setJuryLibelle(rs.getString("juryLibelle"));            
            liste.add(centreS);
        }
        return liste;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByParentId'");
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Long lastInsertedId() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastInsertedId'");
    }

    public List<Objet> getCentresSecondaire(Jury jury) throws SQLException {
        String sql = "SELECT * FROM viewCentreSecondaire where session_id is not null and jury_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, jury.getId());
        ResultSet rs    = stmt.executeQuery();
         List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            CentreSecondaire centreS = CentreSecondaire.id(rs.getLong("centreSecondaire_id"));
            centreS.setLibelle(rs.getString("libelle"));
            centreS.setSession(Session.id(rs.getLong("session_id")));
            centreS.getSession().setAnnee(rs.getLong("annee"));
            centreS.setJury(jury.id(rs.getLong("jury_id")));
            centreS.getJury().setJuryLibelle(rs.getString("juryLibelle"));            
            liste.add(centreS);
        }
        return liste;
    }
    
}
