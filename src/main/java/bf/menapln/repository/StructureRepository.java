/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Structure;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class StructureRepository extends Repository implements InterfaceDAO{

    public StructureRepository(Factory factory) {
        super(factory);
    }

    @Override
    public Objet save(Objet objet) throws SQLException {
        Etablissement structure = (Etablissement)objet;
        String sql;
        sql = "INSERT INTO structure(typeStructure_id,parent_id,localite_id,codeStructure,nomStructure,acronymeStructure,centreExamen_id)\n"
                + "    VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, structure.getTypeStructure().getId());
        if(structure.getParent().getId() == null){
            pstmt.setObject(2, null);
        }else{
            pstmt.setLong(2, structure.getParent().getId());
        }
        pstmt.setLong(3, structure.getLocalite().getId());
        pstmt.setString(4,structure.getCodeStructure());
        pstmt.setString(5, structure.getNomStructure());
        pstmt.setString(6, structure.getAcronymeStructure());
        pstmt.setLong(7, structure.getCentreExamen().getId());

        pstmt.executeUpdate();
        structure.setId(this.lastInsertedId());
        return structure;
    }

    @Override
    public Objet delete(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Objet upadte(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Objet> getAll() throws SQLException {
        String sql = "SELECT * FROM structure";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Etablissement etab = new Etablissement();
            etab.setId(rs.getLong("structure_id"));
            etab.setCodeStructure(rs.getString("codeStructure"));
            etab.setNomStructure(rs.getString("nomStructure"));
            etab.setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(etab);
        }
           return liste;
    }

    @Override
    public Objet getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Long lastInsertedId() throws SQLException {
       String sql = "SELECT * FROM structure ORDER BY structure_id DESC LIMIT 1";
        
        Statement stmt  = this.factory.connect().createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        Long id = null;
        while (rs.next()) {
            id = rs.getLong("structure_id");
        }
        return id;
    }

    @Override
    public List<Objet> getByParentId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Objet> getByLocaliteId(Long id) throws SQLException {
        String sql = "SELECT * FROM structure where localite_id = ?";
        
        PreparedStatement stmt  = this.factory.connect().prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs    = stmt.executeQuery();
        List<Objet> liste = new ArrayList<Objet>();
        while (rs.next()) {
            Etablissement etab = new Etablissement();
            etab.setId(rs.getLong("structure_id"));
            etab.setCodeStructure(rs.getString("codeStructure"));
            etab.setNomStructure(rs.getString("nomStructure"));
            etab.setAcronymeStructure(rs.getString("acronymeStructure"));
            
            liste.add(etab);
        }
           return liste;
    }

    @Override
    public Objet getById(Objet objet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
