package bf.menapln.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Type;

public class ConcoursRatacheRepository extends Repository implements InterfaceDAO {

    public ConcoursRatacheRepository(Factory factory) {
        super(factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        Type type = (Type)objet;
        String sql;
        sql = "INSERT INTO concoursratache(concoursLibelle) VALUES(?)";

        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setString(1, type.getLibelle());
        pstmt.executeUpdate();
        type.setId(this.lastInsertedId());
        return type;
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
        String sql = "SELECT * FROM concoursratache";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Type type = new Type();
            type.setId(rs.getLong("concours_id"));
            type.setLibelle(rs.getString("concoursLibelle"));
            
            liste.add(type);
        }
           return liste;    }

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
        String sql = "SELECT * FROM concoursratache ORDER BY concours_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
            id = rs.getLong("concours_id");
        }
        return id;    }
    
}
