/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.SerieStateListener;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.tableModel.AbscenceTableModel;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.CompositionService;
import bf.menapln.service.FeuilleCompositionService;
import bf.menapln.service.SerieService;
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
public class AbscenceBodyPanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private ContainerObject seriePanel;
    private ContainerObject epreuvePanel;
    private ContainerObject formControlPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private JComboBox epreuveField;
    private JLabel epreuveLabel;
    private Bouton save;
   
    
    private Panneau container;
    
    private ObjetModel model;
    
    public AbscenceBodyPanel(Panneau container) throws SQLException{
        super();
        this.container = container;
        
        this.epreuveLabel = new JLabel("Epreuve");
        this.epreuveField = new JComboBox();
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuveField.setMaximumSize(new Dimension(400,30));
        
        this.epreuvePanel = new ContainerObject();
        this.epreuveField.addActionListener(this);
        this.epreuvePanel.setLayout(new BoxLayout(this.epreuvePanel,BoxLayout.Y_AXIS));
        this.epreuvePanel.add(this.epreuveLabel);
        this.epreuvePanel.add(this.epreuveField);
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.addActionListener(SerieStateListener.epreuveCompose(this.epreuveField,this.container.getFenetre()));
        this.serieField.addActionListener(this);
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(400,30));
        
        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.seriePanel);
        this.filtrePanel.add(this.epreuvePanel);
        
        this.save = new Bouton("Init. copies");
        this.save.addActionListener(new FeuilleSaveListener());
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.setLayout(new BoxLayout(this.formControlPanel,BoxLayout.X_AXIS));
        this.formControlPanel.add(this.save);
        
        SerieService seriService = new SerieService();
        List series = seriService.getSerie(this.container.fenetre.getUserSession().getJury());
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);
        }

        this.container.service = new CompositionService();
        List data = new ArrayList<FeuilleComposition>();
        this.model = new AbscenceTableModel(data,this.container.getFenetre());
        
        JTable tableau = new JTable(this.model);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.formControlPanel,BorderLayout.SOUTH);
        
    }

    public Panneau getContainer() {
        return container;
    }

    public void setContainer(Panneau container) {
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(this.serieField.getSelectedItem() != null && this.epreuveField.getSelectedItem() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            List data;
            try {
                //System.out.println(us.getJury().getEtape().getId()+""+us.getJury().getEtape().getTourCode());
                data = ((CompositionService)this.container.service).getFeuille(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(), (Epreuve)epreuveField.getSelectedItem(), us.getJury().getEtape());
                this.model.setModelData((data != null)?data:new ArrayList());
                this.model.fireTableDataChanged();
                if(((FeuilleComposition)data.get(0)).getCycle() >= 1){
                    this.save.setEnabled(false);
                    ((AbscenceTableModel)this.model).setSaved(true);
                }else{
                    this.save.setEnabled(true);
                    ((AbscenceTableModel)this.model).setSaved(false);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class FeuilleSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                FeuilleCompositionService fcService = new FeuilleCompositionService();
                fcService.setFormData(new HashMap());
                for(Objet feuille : model.getModelData()){
                    fcService.getFormData().put("feuille", feuille);
                    fcService.save();
                    model.fireTableDataChanged();
                    save.setEnabled(false);
                }
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
