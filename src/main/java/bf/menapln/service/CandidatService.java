/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.Inscription;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.repository.CandidatRepository;
import bf.menapln.repository.InscriptionRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CandidatService extends Service{
    public CandidatService() throws SQLException{
        super();
        this.implementationDAO = new CandidatRepository(this.factory);
    }

    @Override
    public Objet save() throws SQLException {
        this.validate();
        Candidat c = new Candidat(this.formData);
        Inscription insc = new Inscription(this.formData);
        insc.setCandidat(c);
        this.implementationDAO.save(c);
        this.implementationDAO = new InscriptionRepository(this.factory);
        this.implementationDAO.save(insc);
        System.out.println("saved");
        return insc;
    }

    @Override
    public void validate() {
        
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getCandidat(Serie serie, Localite villeComposition) throws SQLException {
        return ((CandidatRepository)this.implementationDAO).getCandidat(serie, villeComposition);
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
    
    public void updatePV(Objet objet) throws SQLException {
        this.implementationDAO = new InscriptionRepository(this.factory);
        Inscription i = new Inscription();
        i.setSession(((CandidatJury)objet).getSession());
        i.setCandidat(((CandidatJury)objet).getCandidat());
        ((InscriptionRepository)this.implementationDAO).upadtePV(i);
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        return this.implementationDAO.getById(id);
    }
}
