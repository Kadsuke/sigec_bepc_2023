/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Type;
import bf.menapln.repository.ResultatRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteException;

/**
 *
 * @author coulibaly
 */
public class ResultatService extends Service{
        int maxRetryCount = 5;
        int retryDelayMillis = 1000;
        int retryCount = 0;
        boolean isDatabaseAccessible = false;
    public ResultatService() throws SQLException{
        super();
        this.implementationDAO = new ResultatRepository(this.factory);
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        return this.implementationDAO.save((Objet) this.formData.get("candidat"));
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Objet> getResultat(Serie serie,Jury jury,Type decision) throws SQLException{
        return ((ResultatRepository)this.implementationDAO).getResultat(serie,jury, decision);
    }

    public List<Objet> getResultatCentreSecondaire(CentreSecondaire centreSecondaire,Jury jury,Type decision) throws SQLException{
        return ((ResultatRepository)this.implementationDAO).getResultatCentreSecondaire(centreSecondaire,jury, decision);
    }

    public List<Objet> getResultat(Jury jury,Type decision) throws SQLException{
        return ((ResultatRepository)this.implementationDAO).getResultatSansSerie(jury, decision);
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validate() throws EmptyDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
