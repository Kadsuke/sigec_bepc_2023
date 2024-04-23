/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.EpreuveSerie;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.repository.CandidatPosteJuryRepository;
import bf.menapln.repository.EpreuveCandidatPosteJuryRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CandidatPosteJuryService extends Service{
    public CandidatPosteJuryService() throws SQLException{
        super();
        this.implementationDAO = new CandidatPosteJuryRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        CandidatPosteJury candidat = new CandidatPosteJury(this.formData);
        this.implementationDAO.save(candidat);
        this.implementationDAO = new EpreuveCandidatPosteJuryRepository(this.factory);
        for(Object serie : candidat.getEpreuve().getSeries()){
            candidat.setEs(new EpreuveSerie());
            candidat.getEs().setEpreuve(candidat.getEpreuve());
            candidat.getEs().setSerie((Serie) serie);
            //System.out.println( ((Serie)serie).isChecked() +" "+((Serie)serie).getId() );
            this.implementationDAO.save(candidat);
        }
        return candidat;
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }

    public List<Objet> getAllWithoutRetenu() throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).getAllWithoutRetenu();
    }

    
    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        CandidatPosteJury candidat = (CandidatPosteJury)objet;
        candidat.setEtatCandidature("Retenu");
        return this.implementationDAO.upadte(candidat);
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Objet updateNom(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updateNom(objet);
    }

    public Objet updatePrenom(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updatePrenom(objet);
    }

    public Objet updateMatricule(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updateMatricule(objet);
    }

    public Objet updateNip(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updateNip(objet);
    }

    public Objet updateTelephone(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updateTelephone(objet);
    }

    public Objet updateEpreuve(Objet objet) throws SQLException {
        return ((CandidatPosteJuryRepository)this.implementationDAO).updateEpreuve(objet);
    }
}
