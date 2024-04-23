/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

/**
 *
 * @author coulibaly
 */
public class UserSession extends Objet{
    private Session session;
    private Jury jury;
    private Type tourComposition;
    private User user;

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

    public Type getTourComposition() {
        return tourComposition;
    }

    public void setTourComposition(Type tourComposition) {
        this.tourComposition = tourComposition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
