/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Composition;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.EpreuveSerie;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import bf.menapln.repository.CompositionRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CompositionService extends Service{   
    
    public CompositionService() throws SQLException{
        super();
        this.implementationDAO = new CompositionRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Composition c = new Composition(this.formData);
        return this.implementationDAO.save(c);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getComposition(Session session,Jury jury,Type tour) throws SQLException {
        return ((CompositionRepository)this.implementationDAO).getComposition(session, jury, tour);
    }
    
    public List<Objet> getCompositionNoDuplicate(Session session,Jury jury,Type tour) throws SQLException {
        return ((CompositionRepository)this.implementationDAO).getCompositionNoDuplicate(session, jury, tour);
    }
    
    
    public List<Objet> getFeuille(Session session, Jury jury, Serie serie, Epreuve epreuve, Type tour) throws SQLException{
        List feuilles = null;
        if(this.countFeuilles(session, jury, serie, epreuve, tour) == 0){
            CandidatJuryService cjService = new CandidatJuryService();
            List candidats = cjService.getCandidatJury(jury, serie);
            Composition compo = ((CompositionRepository)this.implementationDAO).get(session, jury, serie, epreuve, tour);
            if(compo != null){
                compo.setCandidats(candidats);
                compo.createFeuille();
                feuilles = compo.getFeuilles();
            }
        }else{
            FeuilleCompositionService fcService = new FeuilleCompositionService();
            feuilles = fcService.getFeuille(session, jury, serie, epreuve, tour);
        }
        return feuilles;
    }
    
    public List<Objet> getEpreuveCompose(Session session,Jury jury,Serie serie,Type tour) throws SQLException{
        return ((CompositionRepository)this.implementationDAO).getEpreuveCompose(session, jury, serie, tour);
    }

    public List<EpreuveSerie> getEpreuveComposeWithoutSerie(Session session,Jury jury,Type tour) throws SQLException{
        return ((CompositionRepository)this.implementationDAO).getEpreuveComposeWithoutSerir(session, jury, tour);
    }
    
    @Override
    public void validate() throws EmptyDataException {
        
    }

    @Override
    public void setImplementation(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Long countFeuilles(Session session, Jury jury, Serie serie, Epreuve epreuve, Type tour) throws SQLException{
        return ((CompositionRepository)this.implementationDAO).countFeuille(session, jury, serie, epreuve, tour);
    }

    public Long countFeuillesSansSerie(Session session, Jury jury, Epreuve epreuve, Type tour) throws SQLException{
        return ((CompositionRepository)this.implementationDAO).countFeuilleSansSerie(session, jury, epreuve, tour);
    }

    @Override
    public Objet update(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Objet> getFeuilleSansSerie(Session session, Jury jury, Epreuve epreuve, Type tour) throws SQLException{
        List feuilles = null;
        if(this.countFeuillesSansSerie(session, jury, epreuve, tour) == 0){
            CandidatJuryService cjService = new CandidatJuryService();
            List candidats = cjService.getCandidatJurySansSerie(jury);
            Composition compo = ((CompositionRepository)this.implementationDAO).getSansSerie(session, jury, epreuve, tour);
            if(compo != null){
                compo.setCandidats(candidats);
                compo.createFeuille();
                feuilles = compo.getFeuilles();
            }
        }else{
            FeuilleCompositionService fcService = new FeuilleCompositionService();
            feuilles = fcService.getFeuilleWithoutSerie(session, jury, epreuve, tour);
        }
        return feuilles;
    }
    
}
