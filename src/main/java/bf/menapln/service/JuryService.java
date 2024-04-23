/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.TourComposition;
import bf.menapln.repository.EtatJuryRepository;
import bf.menapln.repository.JuryRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class JuryService extends Service{
    
    public JuryService() throws SQLException{
        super();
        this.implementationDAO = new JuryRepository(this.factory);
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException {
        this.validate();
        Jury jury = new Jury(this.formData);
        return this.implementationDAO.save(jury);
    }

    @Override
    public List<Objet> getByParentId(String str) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        return this.implementationDAO.getAll();
    }
    
    public List<Objet> getJury(Localite centreExamen) throws SQLException {
        return ((JuryRepository)this.implementationDAO).getJury(centreExamen);
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

    @Override
    public Objet getById(Long id) throws SQLException {
        Jury jury = (Jury) this.implementationDAO.getById(id);
        this.implementationDAO = new EtatJuryRepository(this.factory);
        jury.setEtape((TourComposition) this.implementationDAO.getById(jury));
        return jury;
    }
    
    public void valideEtape(Jury jury) throws SQLException {
        this.implementationDAO = new EtatJuryRepository(this.factory);
        switch(jury.getEtape().getTourCode()){
            case 1 :
                TourComposition secondTour = (TourComposition) ((EtatJuryRepository)this.implementationDAO).getById(jury,false);
                try {
                    secondTour.setActif(true);

                } catch (Exception e) {
                    // TODO: handle exception
                }
                jury.getEtape().setValide(true);
                jury.getEtape().setActif(false);
                ((EtatJuryRepository)this.implementationDAO).updateEtape(jury);
                ((EtatJuryRepository)this.implementationDAO).valideEtape(jury);
                jury.setEtape(secondTour);
                ((EtatJuryRepository)this.implementationDAO).updateEtape(jury);
                //System.out.println(jury.getEtape().getTourCode());
            break;
            case 2 :
                jury.getEtape().setValide(true);
            break;
        }
    }
    
}
