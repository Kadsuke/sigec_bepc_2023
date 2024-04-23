/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Centre;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.service.CentreSecondaireService;
import bf.menapln.service.CentreService;
import bf.menapln.service.JuryService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author coulibaly
 */
public class CentreStateListener implements ActionListener{
    private JComboBox centreSecondaireField = null;
    private JComboBox juryField = null;
    private JComboBox centreCompositionField = null;
    private JComboBox centreExamenField = null;
    
    public CentreStateListener(){
        super();
    }

    public CentreStateListener(JComboBox centreCompositionField){
        this.centreCompositionField = centreCompositionField;
    }

    public static CentreStateListener juryField(JComboBox juryField){
        CentreStateListener centreState = new CentreStateListener();
        centreState.juryField = juryField;
        return centreState;
    }

    public static CentreStateListener centreSecondaireField(JComboBox centreSecondaireField){
        CentreStateListener centreState = new CentreStateListener();
        centreState.centreSecondaireField = centreSecondaireField;
        return centreState;
    }

    public static CentreStateListener centreCompositionField(JComboBox centreCompositionField){
        CentreStateListener centreState = new CentreStateListener();
        centreState.centreCompositionField = centreCompositionField;
        return centreState;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(this.juryField != null){
            try {
                JuryService juryService = new JuryService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List jurys = juryService.getJury(((Localite)((JComboBox)ae.getSource()).getSelectedItem()));
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.juryField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Jury());
                    for(Object jury : jurys){
                        comboModel.addElement(jury);
                        //provinceField.addItem((Localite)province);
                    }
                    this.juryField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(this.centreCompositionField != null){
            try {
                CentreService  centreService = new CentreService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List centrecompositions = centreService.getCentresByCentre(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId());
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.centreCompositionField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Centre());
                    for(Object centrecomposition : centrecompositions){
                        comboModel.addElement(centrecomposition);
                        //provinceField.addItem((Localite)province);
                    }
                    this.centreCompositionField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(this.centreSecondaireField != null){
            try {
                CentreSecondaireService centreSecondaireService = new CentreSecondaireService();
                if(((Jury)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Jury)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List centreSecondaire = centreSecondaireService.getAllByJury(((Jury)((JComboBox)ae.getSource()).getSelectedItem()));
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.centreSecondaireField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new CentreSecondaire());
                    for(Object cenSec : centreSecondaire){
                        comboModel.addElement(cenSec);
                        //provinceField.addItem((Localite)province);
                    }
                    this.centreSecondaireField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }

    public JComboBox getJuryField() {
        return juryField;
    }

    public void setJuryField(JComboBox juryField) {
        this.juryField = juryField;
    }

    public JComboBox getCentreCompositonField() {
        return centreCompositionField;
    }

    public void setCentreCompositionField(JComboBox centreCompositionField) {
        this.centreCompositionField = centreCompositionField;
    }

    public JComboBox getCentreExamenField() {
        return centreExamenField;
    }

    public void setCentreExamenField(JComboBox centreCompositionField) {
        this.centreCompositionField = centreCompositionField;
    }
    
}
