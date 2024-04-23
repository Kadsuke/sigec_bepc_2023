/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bf.menapln.dto;

import bf.menapln.entity.Objet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public interface InterfaceDAO {
    public Objet save(Objet objet) throws SQLException;
    public Objet delete(Objet objet) throws SQLException;
    public Objet upadte(Objet objet) throws SQLException;
    public List<Objet> getAll() throws SQLException;
    public List<Objet> getByParentId(Long id) throws SQLException;
    public Objet getById(Long id) throws SQLException;
    public Objet getById(Objet objet) throws SQLException;
    public Long lastInsertedId()throws SQLException;
    
}
