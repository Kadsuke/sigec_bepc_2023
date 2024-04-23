package bf.menapln.service;

import java.sql.SQLException;
import java.util.List;

import bf.menapln.entity.Objet;
import bf.menapln.repository.ConcoursRatacheRepository;
import exception.EmptyDataException;

public class ConcoursRatacheService extends Service{

    public ConcoursRatacheService() throws SQLException {
        super();
        this.implementationDAO = new ConcoursRatacheRepository(this.factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByParentId'");
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        List tes = this.implementationDAO.getAll();
        System.out.println(tes.size());
        // TODO Auto-generated method stub
        return this.implementationDAO.getAll();
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void validate() throws EmptyDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }

    @Override
    public void setImplementation(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setImplementation'");
    }
    
}
