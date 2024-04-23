/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
import bf.menapln.repository.LocaliteRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class LocaliteService extends Service{
    public LocaliteService() throws SQLException{
        super();
        this.implementationDAO = new LocaliteRepository(this.factory);
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Localite localite = new Localite(this.formData);
        //System.out.println(this.formData);
        this.implementationDAO.save(localite);
        System.out.println(localite.getTypeLocalite().getId());
        return localite;
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        //System.out.println(this.implementationDAO.getAll().size());
        List liste = null;
        switch(str){
            case "region":
                liste = ((LocaliteRepository)this.implementationDAO).getRegion();
            break;
            case "province":
                liste = ((LocaliteRepository)this.implementationDAO).getProvince();
            break;
            case "commune":
                liste = ((LocaliteRepository)this.implementationDAO).getCommune();
            break;
            default:
                liste = this.implementationDAO.getAll();
            break;
        }
        return liste;
    }

    @Override
    public void validate() throws EmptyDataException {
        if(this.formData.containsKey("typeLocalite") && ((String)this.formData.get("typeLocalite")).isEmpty()){
            throw new EmptyDataException("Type localité vide");
        }
        if(this.formData.containsKey("nomLocalite") && ((String)this.formData.get("nomLocalite")).isEmpty()){
            throw new EmptyDataException("Libellé de la localité vide");
        }
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        return this.implementationDAO.getByParentId(Long.parseLong(str));
    }
    
    public List<Objet> getVilleComposition(Session session,Localite parent) throws SQLException {
        return ((LocaliteRepository)this.implementationDAO).getVilleComposition(session, parent);
    }

    public List<Objet> getByTypeLocalite(String parent, String type) throws SQLException {
        return ((LocaliteRepository)this.implementationDAO).getAllLocaliteByType(Long.parseLong(parent), Long.parseLong(type));
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
