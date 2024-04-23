/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.repository.EpreuveSerieRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class EpreuveSerieService extends Service{
    
    public EpreuveSerieService() throws SQLException{
        super();
        this.implementationDAO = new EpreuveSerieRepository(this.factory);
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Serie serie = new Serie(this.formData);
        serie.addEpreuve(new Epreuve(this.formData));
        return this.implementationDAO.save(serie);
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
     public List<Epreuve> getAllSerieByEpreuve() throws SQLException {
        return ((EpreuveSerieRepository)this.implementationDAO).getAllSerieByEpreuve();
    }
    public List<Epreuve> getAllSerieByEpreuveNofr() throws SQLException {
        return ((EpreuveSerieRepository)this.implementationDAO).getAllSerieByEpreuveNofr();
    }
    
    @Override
    public Objet getById(Long id) throws SQLException {
        return this.implementationDAO.getById(id);
    }

    public Objet getByIdTour(Long id,Jury jury) throws SQLException {
        return ((EpreuveSerieRepository)this.implementationDAO).getByIdTour(id,jury);
    }
    
    public Objet getEpreuveSecTourBySerie(Long id) throws SQLException {
        return ((EpreuveSerieRepository)this.implementationDAO).getEpreuveSecTourBySerie(id);
    }

    public Objet getEpreuvePreTourBySerie(Long id) throws SQLException {
        return ((EpreuveSerieRepository)this.implementationDAO).getEpreuvePreTourBySerie(id);
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
    
}
