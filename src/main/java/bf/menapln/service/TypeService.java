/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Type;
import bf.menapln.repository.ConcoursRatacheRepository;
import bf.menapln.repository.OffreEnseignementRepository;
import bf.menapln.repository.PrescriptionRepository;
import bf.menapln.repository.StatutEtablissementRepository;
import bf.menapln.repository.TypeCandidatRepository;
import bf.menapln.repository.TypeEpreuveRepository;
import bf.menapln.repository.TypeHandicapRepository;
import bf.menapln.repository.TypeInscriptionRepository;
import bf.menapln.repository.TypeLocaliteRepository;
import bf.menapln.repository.TypeStructureRepository;
import bf.menapln.repository.posteMembreJuryRepository;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class TypeService extends Service{
    public TypeService() throws SQLException{
        super();
    }

    @Override
    public Objet save() throws SQLException, EmptyDataException{
        this.validate();
        Type type = new Type(this.formData);
        this.setImplementation((String)this.formData.get("typoField"));
        this.implementationDAO.save(type);
        System.out.println(type.getId());
        return type;
    }

    @Override
    public void validate() throws EmptyDataException{
        if(this.formData.containsKey("typoField") && ((String)this.formData.get("typoField")).isEmpty()){
            throw new EmptyDataException("Typologie vide");
        }
        if(this.formData.containsKey("typeLibelle") && ((String)this.formData.get("typeLibelle")).isEmpty()){
            throw new EmptyDataException("Libellé de type est vide");
        }
    }

    @Override
    public List<Objet> getAll(String str) throws SQLException {
        this.setImplementation(str);
        return this.implementationDAO.getAll();
    }

    @Override
    public void setImplementation(String str) {
        switch(str){
            case "Localité":
                this.implementationDAO = new TypeLocaliteRepository(this.factory);
            break;
            case "Statut étab.":
                this.implementationDAO = new StatutEtablissementRepository(this.factory);
            break;
            case "Offre ens.":
                this.implementationDAO = new OffreEnseignementRepository(this.factory);
            break;
            case "Type struct.":
                this.implementationDAO = new TypeStructureRepository(this.factory);
            break;
            case "Type épreuve":
                this.implementationDAO = new TypeEpreuveRepository(this.factory);
            break;
            case "Poste jury":
                this.implementationDAO = new posteMembreJuryRepository(this.factory);
            break;
            case "Type candidat":
                this.implementationDAO = new TypeCandidatRepository(this.factory);
            break;
            case "Type insc.":
                this.implementationDAO = new TypeInscriptionRepository(this.factory);
            break;
            case "Type handicap":
                this.implementationDAO = new TypeHandicapRepository(this.factory);
            break;
            case "Prescription hand.":
                this.implementationDAO = new PrescriptionRepository(this.factory);
            break;
            case "Concours rata.":
                this.implementationDAO = new ConcoursRatacheRepository(this.factory);
            break;
        }    
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
