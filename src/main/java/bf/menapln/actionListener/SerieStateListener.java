/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Serie;
import bf.menapln.ihm.BarreDeMenu;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.CompositionService;
import bf.menapln.service.EpreuveService;
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
public class SerieStateListener implements ActionListener{
    private JComboBox epreuveField;
    private Fenetre fenetre;
    private String typeEpreuve = "allBySerie";
    
    public SerieStateListener(JComboBox epreuveField){
        this.epreuveField = epreuveField;
    }
    
    public static SerieStateListener epreuveCompose(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuveCompose";
        return st;
    }
    
    public static SerieStateListener epreuveCopies(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuveCopies";
        return st;
    }
    
    public static SerieStateListener epreuveAnonyme(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuveAnonyme";
        return st;
    }
    
    public static SerieStateListener epreuveCorrige(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuveCorrige";
        return st;
    }
    
    public static SerieStateListener epreuveSecTour(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuveSecTour";
        return st;
    }

    public static SerieStateListener epreuvePreTour(JComboBox epreuveField,Fenetre f){
        SerieStateListener st = new SerieStateListener(epreuveField);
        st.fenetre = f;
        st.typeEpreuve = "epreuvePreTour";
        return st;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Serie s = (Serie)((JComboBox)ae.getSource()).getSelectedItem();
            if(s.getId() != null){
               try {
                    List epreuves = null;
                    EpreuveService eService = new EpreuveService();
                    EpreuveSerieService seriService = new EpreuveSerieService();
                    switch(this.typeEpreuve){
                        case "allBySerie":
                            s = (Serie)seriService.getById(s.getId());
                            epreuves = s.getEpreuves();
                        break;
                        case "epreuveCompose":
                            CompositionService csService = new CompositionService();
                            epreuves = csService.getEpreuveCompose(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), s, this.fenetre.getUserSession().getTourComposition());
                        break;
                        case "epreuveCopies":
                            epreuves = eService.getEpreuveCopiesAll(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), this.fenetre.getUserSession().getTourComposition());
                        break;
                        case "epreuveAnonyme":
                            epreuves = eService.getEpreuveAnonyme(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), s, this.fenetre.getUserSession().getTourComposition());
                        break;
                        case "epreuveCorrige":
                            epreuves = eService.getEpreuveCorrige(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), s, this.fenetre.getUserSession().getTourComposition());
                        break;
                        case "epreuveSecTour":
                            s = (Serie)seriService.getEpreuveSecTourBySerie(s.getId());
                            epreuves = s.getEpreuves();
                        break;
                        case "epreuvePreTour":
                            s = (Serie)seriService.getEpreuvePreTourBySerie(s.getId());
                            epreuves = s.getEpreuves();
                        break;
                    }
                    DefaultComboBoxModel model = (DefaultComboBoxModel) this.epreuveField.getModel();
                    model.removeAllElements();
                    //model.addElement(new Epreuve());
                    for(Object epreuve : epreuves){
                        model.addElement(epreuve);
                    }
                    epreuveField.setModel(model);
                } catch (SQLException ex) {
                    Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
}
