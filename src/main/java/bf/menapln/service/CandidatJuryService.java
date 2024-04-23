/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Structure;
import bf.menapln.entity.Type;
import bf.menapln.repository.CandidatJuryRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CandidatJuryService extends Service{
    public CandidatJuryService() throws SQLException{
        super();
        this.implementationDAO = new CandidatJuryRepository(this.factory);
    }
    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        CandidatJury candidat = new CandidatJury(this.formData);
        return this.implementationDAO.save(candidat);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    public List<Objet> getCandidatJurySerie(Jury jury,Serie serie) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJurySerie(jury, serie);
    }
    
    public List<Objet> getCandidatOrdered() throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatOrdered();
    }
    
    public List<Objet> getCandidatJury(Serie serie, Localite villeComposition) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJury(serie, villeComposition);
    }
    
    public List<Objet> getCandidatJury(Jury jury,Serie serie) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJury(jury,serie);
    }

    public List<Objet> getCandidatJurySansSerie(Jury jury) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJurySansSerie(jury);
    }

    
    
    public List<Objet> getCandidatJury(Jury jury) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJury(jury);
    }
    public List<Candidat> getCandidatJuryT(Jury jury) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatJuryT(jury);
    }

    public List<Objet> getCandidatCentre(CentreSecondaire centreSecondaire) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatCentre(centreSecondaire);
    }

    public List<Objet> getCandidatBySerieAndCentre(Jury jury,CentreSecondaire centreSecondaire, Serie serie) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatBySerieAndCentre(jury,centreSecondaire,serie);
    }
    

    @Override
    public void validate() throws EmptyDataException {
        if(this.formData.containsKey("jury") && (Jury)this.formData.get("jury") == null){
            throw new EmptyDataException("Aucun jury choisi");
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

    public void updateCentreSecond(Objet objet) throws SQLException {
        this.implementationDAO = new CandidatJuryRepository(this.factory);
        CandidatJury candi = new CandidatJury();
        candi.setCentreSecondaire(((CandidatJury)objet).getCentreSecondaire());
        candi.setSession(((CandidatJury)objet).getSession());
        candi.setCandidat(((CandidatJury)objet).getCandidat());
        ((CandidatJuryRepository)this.implementationDAO).updateCentreSecondaire(candi);
    }
    
    public Objet updateAptitude(Objet objet) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).updateAptitude(objet);
    }

    public Objet updateSerie(Objet objet) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).updateSerie(objet);
    }
    
    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<Objet> getCandidatByCeb(Structure ceb) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatByCeb(ceb);
    }

    public List<Objet> getCandidatByCebByJury(Structure ceb, Jury jury) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatByCebByJury(ceb,jury);
    }

    public List<Objet> getCandidatByCebByJuryByCentre(Structure ceb, Jury jury,Structure centre) throws SQLException {
        return ((CandidatJuryRepository)this.implementationDAO).getCandidatByCebByJuryByCentre(ceb,jury,centre);
    }



}
