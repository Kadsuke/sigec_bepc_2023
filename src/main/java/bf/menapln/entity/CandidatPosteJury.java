/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class CandidatPosteJury extends SessionEntity {

    private Personnel candidat;
    private Epreuve epreuve;
    private Structure structure;
    private Localite localiteCand;
    private Type poste;
    private EpreuveSerie es;
    private String EtatCandidature;
    
    public CandidatPosteJury(){
        super();
    }

    public CandidatPosteJury(Map data) {
        super(data);
        if (data.containsKey("epreuve")) {
            this.setEpreuve((Epreuve) data.get("epreuve"));
        }
        this.setCandidat(new Personnel(data));
        if (data.containsKey("structure")) {
            this.setStructure((Structure) data.get("structure"));
        }

        if (data.containsKey("localite")) {
            this.setLocaliteCand((Localite) data.get("localite"));
        }

        if (data.containsKey("poste")) {
            this.setPoste((Type) data.get("poste"));
        }
    }

    public CandidatPosteJury(ResultSet rs) throws SQLException {
        super(rs);
        this.setCandidat(new Personnel(rs));
        this.setEpreuve(new Epreuve(rs));
        this.setStructure(new Structure(rs));
        this.setLocaliteCand(new Localite(rs));
        this.setPoste(Type.id(rs.getLong("poste_id")));
        this.getPoste().setLibelle(rs.getString("posteLibelle"));
        this.setEtatCandidature(rs.getString("etatCandidature"));
    }

    public Personnel getCandidat() {
        return candidat;
    }

    public void setCandidat(Personnel candidat) {
        this.candidat = candidat;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Localite getLocaliteCand() {
        return localiteCand;
    }

    public void setLocaliteCand(Localite localiteCand) {
        this.localiteCand = localiteCand;
    }

    public Type getPoste() {
        return poste;
    }

    public void setPoste(Type poste) {
        this.poste = poste;
    }

    public String getEtatCandidature() {
        return EtatCandidature;
    }

    public void setEtatCandidature(String EtatCandidature) {
        this.EtatCandidature = EtatCandidature;
    }

    public EpreuveSerie getEs() {
        return es;
    }

    public void setEs(EpreuveSerie es) {
        this.es = es;
    }
    
}
