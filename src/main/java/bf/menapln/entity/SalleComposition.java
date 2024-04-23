package bf.menapln.entity;

import java.util.Map;

import com.itextpdf.layout.element.List;

public class SalleComposition extends Objet{
    private Session session;
    private Jury jury;
    private String libelleSalle;
    private Type tourComposition;
    private Integer capacite;

    public SalleComposition(Map data) {
        super(data);
        this.setSession((Session) data.get("session_id"));
        this.setJury((Jury)data.get("jury"));
        this.setLibelleSalle((String)data.get("libelleSalle"));
        this.setTourComposition((Type) data.get("tourComposition_id"));
        this.setCapacite((Integer) data.get("capacite"));
    }
    public SalleComposition() {
        super();
    }
    public static SalleComposition id(Long id){
        SalleComposition salleComposition = new SalleComposition();
        salleComposition.setId(id);
        return salleComposition;
    }

    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public Jury getJury() {
        return jury;
    }
    public void setJury(Jury jury) {
        this.jury = jury;
    }
    public String getLibelleSalle() {
        return libelleSalle;
    }
    public void setLibelleSalle(String libelleSalle) {
        this.libelleSalle = libelleSalle;
    }
    public Type getTourComposition() {
        return tourComposition;
    }
    public void setTourComposition(Type tourComposition) {
        this.tourComposition = tourComposition;
    }
    public Integer getCapacite() {
        return capacite;
    }
    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }
    @Override
    public String toString() {
        return this.libelleSalle;
    }

    

    
}
