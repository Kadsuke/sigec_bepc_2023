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
public class Personnel extends Personne{
    private String matricule;
    
    public Personnel(){
        super();
    }
    
    public Personnel(Map data){
        super(data);
        this.setMatricule((String) data.get("matricule"));
    }
    
    public Personnel(ResultSet rs) throws SQLException {
        super(rs);
        this.setMatricule(rs.getString("matricule"));
    }
    
    public static Personnel id(Long id){
        Personnel p = new Personnel();
        p.setId(id);
        return p;
    }

    public void setStatement(PreparedStatement ps) throws SQLException{
        //ps.setString(0, nip);
    }
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
    
}
