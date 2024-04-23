package bf.menapln.service;

import java.sql.SQLException;
import java.util.List;

import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.repository.CentreSecondaireRepository;
import exception.EmptyDataException;

public class CentreSecondaireService extends Service{

    public CentreSecondaireService() throws SQLException {
        super();
        this.implementationDAO = new CentreSecondaireRepository(this.factory);

        //TODO Auto-generated constructor stub
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        // TODO Auto-generated method stub
        this.validate();
        CentreSecondaire centreSecondaire = new CentreSecondaire(this.formData);
        return this.implementationDAO.save(centreSecondaire);
    }
    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");

    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }   

    public List<Objet> getAll() throws SQLException {
        return ((CentreSecondaireRepository)this.implementationDAO).getAll();
    }   
    
    public List<Objet> getCentreSecondaireJury(Jury jury) throws SQLException {
        return ((CentreSecondaireRepository)this.implementationDAO).getCentresSecondaire(jury);
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
    public void validate()  {
        // TODO Auto-generated method stub
    }

    @Override
    public void setImplementation(String str) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setImplementation'");
    }

    public List<Objet> getAllByJury(Jury jury) throws SQLException {
        // TODO Auto-generated method stub
        return ((CentreSecondaireRepository)this.implementationDAO).getCentresSecondaire(jury);    }  
    
}
