/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Objet;
import bf.menapln.entity.User;
import bf.menapln.repository.MenuRepository;
import bf.menapln.repository.UserRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class UserService extends Service{
    public UserService() throws SQLException{
        super();
        this.implementationDAO = new UserRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        User user = new User(this.formData);
        return this.implementationDAO.save(user);
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
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Objet connect() throws SQLException{
        boolean connected = false;
        User user = new User();
        user.setUsername((String) this.formData.get("username"));
        user.setPwd((String) this.formData.get("pwd"));
        
        User userBD = (User) this.implementationDAO.getById(user);
        if(this.isConnected(user, userBD)){
            this.implementationDAO = new MenuRepository(this.factory);
            ((MenuRepository)this.implementationDAO).getByProfil(userBD.getProfil());
            connected = true;
        }
        return (connected)? userBD : null;
    }
    
    public boolean isConnected(User user, User userBD) throws SQLException{
        boolean connected = false;
        if(userBD.getPwd().equals(user.getPwd())){
            connected = true;
        }
        
        return connected;
    }
    
}
