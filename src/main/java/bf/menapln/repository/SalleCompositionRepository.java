package bf.menapln.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Objet;
import bf.menapln.entity.SalleComposition;

public class SalleCompositionRepository extends Repository implements InterfaceDAO{

    public SalleCompositionRepository(Factory factory) {
        super(factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        SalleComposition salleComposition = (SalleComposition)objet;
        String sql;
        sql = "INSERT INTO salleComposition(session_id,jury_id,libelleSalle,tourComposition_id,capacite) VALUES(?,?,?,?,?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, salleComposition.getSession().getId());
        pstmt.setLong(2, salleComposition.getJury().getId());
        pstmt.setString(3, salleComposition.getLibelleSalle());
        pstmt.setLong(4, salleComposition.getTourComposition().getId());
        pstmt.setLong(5, salleComposition.getCapacite());
        pstmt.executeUpdate();
        //serie.setId(this.lastInsertedId());
        return salleComposition;    }

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
        String sql = "SELECT * FROM salleComposition";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            SalleComposition salle = new SalleComposition();
            salle.setId(rs.getLong("salleComposition_id"));
            salle.setLibelleSalle(rs.getString("libelleSalle"));
            salle.setCapacite(rs.getInt("capacite"));
            liste.add(salle);
        }
        return liste;
    }

    public List<SalleComposition> getAllByTour(Long session,Long tour) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM salleComposition where session_id = ? and tourComposition_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, session);
        stmt.setLong(2, tour);
        ResultSet rs    = stmt.executeQuery();
        List<SalleComposition> liste = new ArrayList<SalleComposition>();
        while (rs.next()) {
            SalleComposition salle = new SalleComposition();
            salle.setId(rs.getLong("salleComposition_id"));
            salle.setLibelleSalle(rs.getString("libelleSalle"));
            salle.setCapacite(rs.getInt("capacite"));
            liste.add(salle);
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
    
}
