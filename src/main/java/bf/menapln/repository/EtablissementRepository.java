/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.repository;

import bf.menapln.dto.Factory;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Objet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class EtablissementRepository extends StructureRepository{
    
    public EtablissementRepository(Factory factory) {
        super(factory);
    }
    
    @Override
    public Objet save(Objet objet) throws SQLException {
       Etablissement etab = (Etablissement) super.save(objet);
       String sql;
        sql = "INSERT INTO etablissement(structure_id,statut_id,offreEnseignement_id)\n"
                + "    VALUES(?,?,?)";
        PreparedStatement pstmt = this.factory.connect().prepareStatement(sql);
        pstmt.setLong(1, etab.getId());
        pstmt.setLong(2, etab.getStatut().getId());
        pstmt.setLong(3, etab.getOffreEnseignement().getId());
        pstmt.executeUpdate();
       return etab;
    }
    
}
