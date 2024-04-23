/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Localite;
import bf.menapln.service.LocaliteService;
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
public class LocaliteParentStateListener implements ActionListener{
    private JComboBox localiteFilleField;
    
    public LocaliteParentStateListener(JComboBox p){
        this.localiteFilleField = p;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        LocaliteService localiteService;
        try {
            localiteService = new LocaliteService();
            List provinces = localiteService.getByParentId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
            DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.localiteFilleField.getModel();
            comboModel.removeAllElements();
            comboModel.addElement(new Localite());
            for(Object province : provinces){
                comboModel.addElement(province);
                        //provinceField.addItem((Localite)province);
            }
            this.localiteFilleField.setModel(comboModel);
        } catch (SQLException ex) {
            //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
