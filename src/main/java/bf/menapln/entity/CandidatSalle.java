package bf.menapln.entity;

import java.util.Map;

public class CandidatSalle extends Objet{
    private SalleComposition salleComposition;
    private Candidat candidat;
       
    public CandidatSalle(Map data) {
        super(data);
        this.setSalleComposition((SalleComposition) data.get("salleComposition"));
        this.setCandidat((Candidat) data.get("candidat"));
    }

    public static CandidatSalle id(Long id){
        CandidatSalle candidatSalle = new CandidatSalle();
        candidatSalle.setId(id);
        return candidatSalle;
    }

    public CandidatSalle() {
        super();
    }
    public SalleComposition getSalleComposition() {
        return salleComposition;
    }
    public void setSalleComposition(SalleComposition salleComposition) {
        this.salleComposition = salleComposition;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    
}
