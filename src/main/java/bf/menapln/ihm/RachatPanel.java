/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Type;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.tableModel.PVTableModel;
import bf.menapln.ihm.tableModel.RachatTableModel;
import bf.menapln.service.ConcoursRatacheService;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.FeuilleCompositionService;
import bf.menapln.service.ResultatService;
import bf.menapln.service.SerieService;
import bf.menapln.service.Service;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class RachatPanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;

    private JLabel concoursLabel;
    private JComboBox concoursField;
    private ContainerObject councoursPanel;
    
    private Bouton print;
    
    
    
    
    private Panneau container;
    
    private ObjetModel model;
    private JTable tableau;
    public RachatPanel(Panneau container) throws SQLException{
        this.container = container;
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.addActionListener(this);
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(400,30));

        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);
        
        this.print = new Bouton("Imprimer");
        this.print.setEnabled(false);
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.seriePanel);
        this.filtrePanel.add(this.print);
        
        SerieService seriService = new SerieService();
        List series = seriService.getSerie(this.container.fenetre.getUserSession().getJury());
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);

}
        this.container.service = new FeuilleCompositionService();
        this.model = new RachatTableModel(new ArrayList(),this.container.getFenetre());
        
        tableau = new JTable(this.model);
        tableau.setRowHeight(25);
      
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.container.formControlPanel = new ContainerObject();
        //this.container.formControlPanel.add(this.container.save);
        //this.container.formControlPanel.add(this.print);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Serie s = (Serie)(serieField.getSelectedItem());
        if(s.getId() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            EpreuveSerieService serieService;
            try {
                serieService = new EpreuveSerieService();
                s = (Serie)serieService.getByIdTour(s.getId(),us.getJury());
               
                this.model.setModelTitle(serieService.setColonnePV(this.tableau, s.getEpreuves()));
                
                //List data = ((FeuilleCompositionService)this.container.service).getFeuilleByConcoursRatache(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(),(Type)concoursField.getSelectedItem(), us.getJury().getEtape());
                List data = new ArrayList<>();
                List<Objet> feuille =((FeuilleCompositionService)this.container.service).getFeuille(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(), us.getJury().getEtape());

                for (Objet objet : feuille) {
                    if(((Candidat)objet).decisionJury().equals("Rachat")){
                        data.add(objet);
                    }
                }
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
                
                Service.setPVPColWidth(this.tableau);
                
            } catch (SQLException ex) {
                Logger.getLogger(PVPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
