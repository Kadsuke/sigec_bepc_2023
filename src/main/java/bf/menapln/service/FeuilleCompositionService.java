/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Membre;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Personnel;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import bf.menapln.repository.FeuilleCompositionRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class FeuilleCompositionService extends Service{
    public FeuilleCompositionService() throws SQLException{
        super();
        this.implementationDAO = new FeuilleCompositionRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        FeuilleComposition feuille = (FeuilleComposition)this.formData.get("feuille");
        return this.implementationDAO.save(feuille);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

        
    public Objet updatePresence(Objet objet) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).updatePresence(objet);
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getFeuille(Session session, Jury jury, Serie serie, Epreuve epreuve, Type tour) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuille(session, jury, serie, epreuve, tour);
    }

    public List<Objet> getFeuilleWithoutSerie(Session session, Jury jury, Epreuve epreuve, Type tour) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuilleWithoutSerie(session, jury, epreuve, tour);
    }
    
    public List<Objet> getFeuilleWithoutSerieForCorrecteur(Session session, Jury jury, Epreuve epreuve,Correcteur correcteur, Type tour) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuilleWithoutSerieForCorrecteur(session, jury, epreuve, correcteur, tour);
    }
    
    public List<Objet> getFeuille(Session session, Jury jury, Serie serie, Type tour) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuille(session, jury, serie, tour);
    }

    public List<Objet> getFeuilleByConcoursRatache(Session session, Jury jury,Serie serie, Type concours, Type tour) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuilleByConcoursRatache(session, jury,serie, concours, tour);
    }

    public List<Objet> getFeuilleTest(Session session, Jury jury, Epreuve epreuve, Type tour) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).getFeuilleTest(session, jury, epreuve, tour);
    }

    public List<Objet> getAllByFeuillePremier(Session session, Jury jury,Serie serie, Type concours, Type tour) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).getAllByFeuille(session, jury, serie,concours, tour);
    }

    public List<Objet> getAllByFeuilleDeuxieme(Session session, Jury jury,Serie serie, Type concours, Type tour) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).getAllByFeuilleD(session, jury, serie,concours, tour);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Objet upadteAnonymat(Objet objet) throws SQLException{
        return ((FeuilleCompositionRepository)this.implementationDAO).upadteAnonymat(objet);
    }
    
    public Objet upadteCorrecteur(Objet objet) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).upadteCorrecteur(objet);
    }
    
    public Objet upadteNote(Objet objet) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).upadteNote(objet);
    }

    public Objet upadtePresence(Objet objet) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).upadteNote(objet);
    }
    
    public Objet verouiller(Objet objet) throws SQLException {
        return ((FeuilleCompositionRepository)this.implementationDAO).verouiller(objet);
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
