/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.sql.SQLException;
import javax.swing.JLabel;

/**
 *
 * @author coulibaly
 */
public class ReclamationPanel extends Panneau{
    public ReclamationPanel(Fenetre fenetre){
        super(fenetre);
        this.add(new JLabel("Reclamation"));
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
    }
}
