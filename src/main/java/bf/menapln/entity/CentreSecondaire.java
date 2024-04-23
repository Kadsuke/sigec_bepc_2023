package bf.menapln.entity;

import java.util.Map;

public class CentreSecondaire extends Objet {
    private Jury jury;
    private Centre centreCompositon;
    private Session session;
    private String libelle;

    public CentreSecondaire(Map data) {
        super(data);
        this.setJury((Jury) data.get("jury"));
        this.setCentreCompositon(new Centre());
        this.getCentreCompositon().setEtatblissement(Etablissement.id(Long.parseLong((String) data.get("etablissement"))));
        this.setSession((Session) data.get("session"));
        this.setLibelle((String) data.get("libelle"));
    }

    public static CentreSecondaire id(Long id){
        CentreSecondaire centreSecondaire = new CentreSecondaire();
        centreSecondaire.setId(id);
        return centreSecondaire;
    }
    public Jury getJury() {
        return jury;
    }

    public void setJury(Jury jury) {
        this.jury = jury;
    }


    public Centre getCentreCompositon() {
        return centreCompositon;
    }

    @Override
    public String toString() {
        return this.libelle;
    }

    public void setCentreCompositon(Centre centreCompositon) {
        this.centreCompositon = centreCompositon;
    }

    public Session getSession() {
        return session;
    }





    public void setSession(Session session) {
        this.session = session;
    }





    public String getLibelle() {
        return libelle;
    }





    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }





    public CentreSecondaire() {
    }

    
}
