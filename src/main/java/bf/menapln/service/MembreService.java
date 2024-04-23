/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.EpreuveSerie;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.repository.DisciplineCorrecteurRepository;
import bf.menapln.repository.MembreJuryRepository;
import bf.menapln.repository.PersonnelRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class MembreService extends Service{
    public MembreService() throws SQLException{
        super();
        this.implementationDAO = new MembreJuryRepository(this.factory);
    } 
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Correcteur membre = new Correcteur(this.formData);
        this.implementationDAO = new PersonnelRepository(this.factory);
        this.implementationDAO.save(membre.getPersonnel());
        this.implementationDAO = new MembreJuryRepository(this.factory);
        this.implementationDAO.save(membre);
        if(membre.getPoste().getLibelle().equals("Correcteur")){
            this.implementationDAO = new DisciplineCorrecteurRepository(this.factory);
            for(Objet serie : membre.getEpreuve().getSeries()){
                membre.setEpreuveSerie(new EpreuveSerie());
                membre.getEpreuveSerie().setEpreuve(((Correcteur)membre).getEpreuve());
                membre.getEpreuveSerie().setSerie((Serie)serie);
                this.implementationDAO.save(membre);
            }
        }
        return membre;
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getCorrecteurs(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        return ((MembreJuryRepository)this.implementationDAO).getCorrecteurs(session, jury, epreuve);
    }

    public List<Objet> getCorrecteursFrancais(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        return ((MembreJuryRepository)this.implementationDAO).getCorrecteursFr(session, jury, epreuve);
    }

    public List<Objet> getCorrecteursAnglais(Session session,Jury jury,Epreuve epreuve) throws SQLException{
        return ((MembreJuryRepository)this.implementationDAO).getCorrecteursAng(session, jury, epreuve);
    }

    @Override
    public void validate() throws EmptyDataException {
        Jury jury = (Jury)this.formData.get("jury");
        if(jury == null || jury.getId() == null){
            throw new EmptyDataException("Aucun jury selectionné");
        }
    }

    @Override
    public void setImplementation(String str) {
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
