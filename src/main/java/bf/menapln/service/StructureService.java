/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Structure;
import bf.menapln.repository.EtablissementRepository;
import bf.menapln.repository.StructureRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class StructureService extends Service{
    public StructureService() throws SQLException{
        super();
        this.setImplementation("Etablissement");
    }
    
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Structure structure = new Etablissement(this.formData);
        this.implementationDAO.save(structure);
        System.out.println(structure.getId());
        return structure;
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        System.out.println(this.implementationDAO.getAll().size());
        return this.implementationDAO.getAll();
    }

    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        switch(str){
            case "Etablissement":
                this.implementationDAO = new EtablissementRepository(this.factory);
            break;
            default:
                this.implementationDAO = new StructureRepository(this.factory);
            break;
        }
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Objet> getByLocaliteId(String str) throws SQLException {
        return ((StructureRepository)this.implementationDAO).getByLocaliteId(Long.parseLong(str));
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
