/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
import bf.menapln.repository.SessionRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SessionService extends Service{
    public SessionService() throws SQLException{
        super();
        this.implementationDAO = new SessionRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException{
        this.validate();
        Session session = new Session(this.formData);
        this.implementationDAO.save(session);
        /*session.setAnnee(2023);
        session.setDateDebut(LocalDate.parse("2023-01-01"));
        session.setDateFin(LocalDate.parse("2023-07-01"));
        session.setDateDebutInscription(LocalDate.parse("2023-01-01"));
        session.setDateFindInscrition(LocalDate.parse("2023-03-01"));
        session.setDateDebutSessNorm(LocalDate.parse("2023-04-01"));
        session.setDateFinSessNorm(LocalDate.parse("2023-05-01"));
        session.setDateDebutSessRemp(LocalDate.parse("2023-05-02"));
        session.setDateFinSessRemp(LocalDate.parse("2023-07-01"));
        session.setMoyAdmission(Double.parseDouble("10"));
        session.setMoyRachat(Double.parseDouble("8"));
        this.implementationDAO.save(session);*/
        System.out.println("id : "+session.getId());
        return session;
    }
    
    public List<Objet> getAll() throws SQLException{
        return this.implementationDAO.getAll();
    }

    @Override
    public void validate() {
        
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
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
        return this.implementationDAO.getById(id);
    }
    
    public Objet getActiveSession() throws SQLException {
        return ((SessionRepository)this.implementationDAO).getActiveSession();
    }
    
}
