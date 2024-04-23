package bf.menapln.service;

import java.sql.SQLException;
import java.util.List;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatSalle;
import bf.menapln.entity.Objet;
import bf.menapln.entity.SalleComposition;
import bf.menapln.repository.CandidatSalleRepository;
import exception.EmptyDataException;

public class CandidatSalleService extends Service{

    public CandidatSalleService() throws SQLException {
        super();
        this.implementationDAO = new CandidatSalleRepository(this.factory);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        // TODO Auto-generated method stub
        CandidatSalle candidat = new CandidatSalle(this.formData);
        return this.implementationDAO.save(candidat);    }

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

    public List<Objet> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return ((CandidatSalleRepository)this.implementationDAO).getAll();
    }

    public List<Objet> getBySalleId(SalleComposition salle) throws SQLException,NullPointerException {
        // TODO Auto-generated method stub
        return ((CandidatSalleRepository)this.implementationDAO).getCandidatSalle(salle);
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
