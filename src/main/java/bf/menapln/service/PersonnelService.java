/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Personnel;
import bf.menapln.repository.CandidatPosteJuryRepository;
import bf.menapln.repository.PersonnelRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class PersonnelService extends Service{
    public PersonnelService() throws SQLException{
        super();
        this.implementationDAO = new PersonnelRepository(this.factory);
    }
    
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Personnel p = new Personnel(this.formData);
        return this.implementationDAO.save(p);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }

    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        switch(str){
            case "candidat":
                this.implementationDAO = new CandidatPosteJuryRepository(this.factory);
            break;
            default:
                this.implementationDAO = new PersonnelRepository(this.factory);
        }
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
