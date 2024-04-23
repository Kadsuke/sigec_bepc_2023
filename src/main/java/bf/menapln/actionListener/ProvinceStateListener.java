/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Localite;
import bf.menapln.entity.Session;
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
public class ProvinceStateListener implements ActionListener{
        private JComboBox communeField;
        private Session session;
        private String type = "Commune";
        
        public ProvinceStateListener(JComboBox combo){
            this.communeField = combo;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //System.out.println("est" + (((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() == null));
            LocaliteService localiteService;
            try {
                localiteService = new LocaliteService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List communes = null;
                    switch(this.type){
                        case "VilleComposition":
                            communes = localiteService.getVilleComposition(this.session,((Localite)((JComboBox)ae.getSource()).getSelectedItem()));
                        break;
                        default:
                            communes = localiteService.getByParentId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
                        break;
                        
                    }
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)communeField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Localite());
                    for(Object commune : communes){
                        comboModel.addElement(commune);
                    }
                    communeField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
        
        
    }
