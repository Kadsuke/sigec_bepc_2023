/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Epreuve;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.MembreService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author coulibaly
 */
public class EpreuveStateListener implements ActionListener{
    private JComboBox correcteurField;
    private JComboBox serieField;
    private Fenetre fenetre;
    
    public  EpreuveStateListener(JComboBox correcteurField,JComboBox serieField,Fenetre fenetre){
        this.correcteurField = correcteurField;
        this.serieField = serieField;
        this.fenetre = fenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Epreuve epreuve = (Epreuve)((JComboBox)ae.getSource()).getSelectedItem();
        if(epreuve != null && epreuve.getId() != null){
            try {
                MembreService service = new MembreService();
                List membres;
                if(epreuve.getEpreuveLibelle().equals("Dictée") || epreuve.getEpreuveLibelle().equals("Expression Ecrite") || epreuve.getEpreuveLibelle().equals("Langue")){
                    membres = service.getCorrecteursFrancais(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), epreuve);
                }else if(epreuve.getEpreuveLibelle().equals("Anglais Oral")){
                    membres = service.getCorrecteursAnglais(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), epreuve);

                }
                else{
                    membres = service.getCorrecteurs(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), epreuve);
                }
                DefaultComboBoxModel model = (DefaultComboBoxModel) this.correcteurField.getModel();

                model.removeAllElements();
                model.addAll(membres);    
                /* for(Object membre : membres){
                        model.addElement(membre);
                    } */
                this.correcteurField.setModel(model);
                    
            } catch (SQLException ex) {
                Logger.getLogger(EpreuveStateListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    
    }
    
}
