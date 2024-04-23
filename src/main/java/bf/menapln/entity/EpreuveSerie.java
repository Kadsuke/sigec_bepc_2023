/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class EpreuveSerie extends Objet{
    private Session session;
    private Serie serie;
    private Epreuve epreuve;


    public EpreuveSerie() {
    }
    public EpreuveSerie(ResultSet rs) throws SQLException{
        this.setId(rs.getLong("epreuve_id"));
        this.setEpreuve(Epreuve.id(rs.getLong("epreuve_id")));
        //this.setSerie(Serie.id(rs.getLong("serie_id")));
        }  
    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public String toString() {
       return  this.getEpreuve().getEpreuveAcronyme();
    }
    
    
}
