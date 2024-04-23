/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import bf.menapln.repository.EpreuveRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class EpreuveService extends Service{
    public EpreuveService() throws SQLException{
        super();
        this.implementationDAO = new EpreuveRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Epreuve epreuve = new Epreuve(this.formData);
        this.implementationDAO.save(epreuve);
        System.out.println(epreuve.getId());
        return epreuve;
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getAllEpreuveByTour1(Session session,Jury jury) throws SQLException {
        return (((EpreuveRepository) this.implementationDAO).getAllEpreuveByTour1(session,jury));
    }

    public List<Objet> getAllEpreuveByTour2(Session session) throws SQLException {
        return (((EpreuveRepository) this.implementationDAO).getAllEpreuveByTour2(session));
    }


    public List<Objet> getEpreuveCopies(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveCopies(session, jury, serie, tour);
    }

    public List<Objet> getEpreuveCopiesAll(Session session,Jury jury,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveCopiesAll(session, jury, tour);
    }
    
    public List<Objet> getEpreuveCopiesAllSansOrale(Session session,Jury jury,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveCopiesAllSansOrale(session, jury, tour);
    }

    public List<Objet> getEpreuveCopiesAllSansSport(Session session,Jury jury,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveCopiesAllSansSport(session, jury, tour);
    }
    
    
    public List<Objet> getEpreuveAnonyme(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveAnonyme(session, jury, serie, tour);
    }
    
    public List<Objet> getEpreuveCorrige(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        return ((EpreuveRepository)this.implementationDAO).getEpreuveCorrige(session, jury, serie, tour);
    }
    
    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
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
    
}
