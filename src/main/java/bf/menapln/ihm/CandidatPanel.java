/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author coulibaly
 */
public class CandidatPanel extends Panneau{
    public CandidatPanel(Fenetre fenetre) throws SQLException{
        super(fenetre);
        ListePanel liste = new ListePanel(this);
        this.body.setLayout(new BorderLayout());
        this.body.add(liste);
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText(this.fenetre.getUserSession().getJury().getJuryLibelle()+" : Liste des candidats");
        this.head.initComposant();
    }

    
    
   
    
    /*class ActiveJuryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            fenetre.getJury().setEtat(true);
            fenetre.getBarreDeMenu().initComponent();
        }
        
    }*/
}
