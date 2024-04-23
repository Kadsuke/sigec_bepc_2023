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
public class Membre extends SessionEntity{
    protected Type poste;
    protected Personnel personnel;
    protected boolean membreDeliberant;
    
    public Membre(Map data){
        super(data);
        this.setJury((Jury) data.get("jury"));
        this.setPersonnel((Personnel) data.get("personnel"));
        this.setPoste((Type) data.get("poste"));
        this.setMembreDeliberant((boolean) data.get("membreDeliberant"));
    }
    
    public Membre(ResultSet rs) throws SQLException{
        super(rs);
        this.setPoste(Type.id(rs.getLong("poste_id")));
        this.getPoste().setLibelle(rs.getString("posteLibelle"));
        this.setPersonnel(new Personnel(rs));
        this.setMembreDeliberant(rs.getBoolean("membreDeliberant"));
    }
    
    @Override
    public void setStatement(PreparedStatement ps) throws SQLException{
        super.setStatement(ps);
        ps.setLong(2, this.jury.id);
        ps.setLong(3, this.poste.id);
        ps.setLong(4, this.personnel.id);
        ps.setBoolean(5, this.membreDeliberant);
    }
    
    public Type getPoste() {
        return poste;
    }

    public void setPoste(Type poste) {
        this.poste = poste;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public boolean isMembreDeliberant() {
        return membreDeliberant;
    }

    public void setMembreDeliberant(boolean membreDeliberant) {
        this.membreDeliberant = membreDeliberant;
    }
}
