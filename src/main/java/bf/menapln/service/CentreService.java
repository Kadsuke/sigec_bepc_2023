/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Centre;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.repository.CentreRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CentreService extends Service{
    public CentreService() throws SQLException{
        super();
        this.implementationDAO = new CentreRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Centre centre = new Centre(this.formData);
        return this.implementationDAO.save(centre);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getCentres() throws SQLException {
        return ((CentreRepository)this.implementationDAO).getCentres();
    }
    
    public List<Objet> getCentres(Localite villeComposition) throws SQLException {
        return ((CentreRepository)this.implementationDAO).getCentres(villeComposition);
    }

    public List<Objet> getCentresByCentre(Long centreExamen) throws SQLException {
        return ((CentreRepository)this.implementationDAO).getCentresByCentreExamen(centreExamen);
    }

    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Objet> getCentresByEtabCentreExam() throws SQLException {
        return ((CentreRepository)this.implementationDAO).getCentres();
    }
}
