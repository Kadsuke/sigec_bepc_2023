/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.BorderLayout;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class LocaliteForm extends Panneau{
    
    public LocaliteForm(Fenetre fenetre) {
        super(fenetre);
       
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des localités");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(this.fenetre.getBarreDeMenu().localiteListener);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        
        
    }
    
}
