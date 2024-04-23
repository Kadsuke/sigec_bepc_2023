/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.repository.SerieRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SerieService extends Service{
    
    public SerieService() throws SQLException{
        super();
        this.implementationDAO = new SerieRepository(this.factory);
    }
    
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Serie serie = new Serie(this.formData);
        this.implementationDAO.save(serie);
        System.out.println(serie.getId()+" "+serie.getSerieLibelle()+" "+serie.getDefinition()+" "+serie.getOffreEnseignement().getId());
        return serie;
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getSerie(Session session, Localite villeComposition) throws SQLException {
        return ((SerieRepository)this.implementationDAO).getSerie(session, villeComposition);
    }
    
    public List<Objet> getSerie(Jury jury) throws SQLException {
        return ((SerieRepository)this.implementationDAO).getSerie(jury);
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
        return ((SerieRepository)this.implementationDAO).getById(id);

    }
    
}
