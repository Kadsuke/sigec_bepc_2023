package bf.menapln.service;

import java.sql.SQLException;
import java.util.List;

import bf.menapln.entity.Objet;
import bf.menapln.entity.SalleComposition;
import bf.menapln.repository.SalleCompositionRepository;
import exception.EmptyDataException;

public class SalleCompositionService extends Service{

    public SalleCompositionService() throws SQLException {
        super();
        //TODO Auto-generated constructor stub
        this.implementationDAO = new SalleCompositionRepository(this.factory);

    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        // TODO Auto-generated method stub
        SalleComposition salleComposition = new SalleComposition(this.formData);
        return this.implementationDAO.save(salleComposition);   
     }

     public void saveSpecial(SalleComposition salleComposition) throws SQLException, EmptyDataException {
        // TODO Auto-generated method stub
        this.implementationDAO.save(salleComposition);   
     }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByParentId'");
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    public List<SalleComposition> getAllByTour(Long session,Long tour) throws SQLException {
        // TODO Auto-generated method stub
        return ((SalleCompositionRepository)this.implementationDAO).getAllByTour(session, tour);
    }
    
    public List<SalleComposition> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
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
