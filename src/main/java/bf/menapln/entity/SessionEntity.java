/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public abstract class SessionEntity extends Objet{
    protected Session session;
    protected Jury jury;
    
    public SessionEntity(){
        super();
    }
    
    public SessionEntity(Map data){
        this.setSession((Session) data.get("session"));
    }
    
    public SessionEntity(ResultSet rs) throws SQLException{
        this.setSession(new Session(rs));
    }
    
    public void setStatement(PreparedStatement ps) throws SQLException{
        ps.setLong(1, this.session.getId());
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
    
}
