/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.SerieStateListener;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Serie;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CompositionTableModel;
import bf.menapln.service.CompositionService;
import bf.menapln.service.EpreuveService;
import bf.menapln.service.SerieService;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author coulibaly
 */
public class CompositionPanel extends Panneau{
    private Bouton addComposition;
    private Bouton abscence;
   // private Bouton fraude;
    
    private ContainerObject controlPanel;
    
    private ContainerObject epreuveCompose;
    private ContainerObject epreuveComposeForm;
    
    private JSplitPane bodyPanel;
    
    private JComboBox serieField;
    private JComboBox epreuveField;
    private DatePicker dateField;
    
    private TimePicker heureDebutField;
    private ContainerObject heureDebutPanel;
    
    private JLabel heureFinLabel;
    private TimePicker heureFinField;
    private ContainerObject heureFinPanel;
    
    private JLabel incidentLabel;
    private JCheckBox incidentField;
    private ContainerObject incidentPanel;
    
    private JLabel fraudeLabel;
    private JCheckBox fraudeField;
    private ContainerObject fraudePanel;
    
    private JLabel autreLabel;
    private JTextArea autreField;
    private ContainerObject autrePanel;
    
    public AbsenceListener abscenceListener = new AbsenceListener();
    public FraudeListener fraudeListener = new FraudeListener();
    
    public CompositionPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.addComposition = new Bouton("Ajouter composition");
        this.addComposition.addActionListener(this.fenetre.getBarreDeMenu().compositionListener);
        
        this.abscence = new Bouton("Copies");
        this.abscence.addActionListener(abscenceListener);
        
       /*  this.fraude = new Bouton("fraude");
        this.fraude.addActionListener(this.fraudeListener); */
        
        JLabel epreuveLabel = new JLabel("Epreuve :");
        epreuveField = new JComboBox();
        epreuveField.setPreferredSize(new Dimension(200,30));
        epreuveField.setMaximumSize(new Dimension(200,30));
        
        ContainerObject epreuvePanel = new ContainerObject();
        epreuvePanel.setLayout(new BoxLayout(epreuvePanel,BoxLayout.Y_AXIS));
        epreuvePanel.add(epreuveLabel);
        epreuvePanel.add(epreuveField);
        

        
        ContainerObject epreuveLine = new ContainerObject();
        epreuveLine.setLayout(new BoxLayout(epreuveLine,BoxLayout.X_AXIS));
        epreuveLine.setBorder(BorderFactory.createTitledBorder("Choix épreuve"));
       // epreuveLine.add(seriePanel);
        epreuveLine.add(epreuvePanel);
        
        JLabel dateLabel = new JLabel("Date de composition");
        dateField = new DatePicker();
        dateField.setPreferredSize(new Dimension(400,30));
        dateField.setMaximumSize(new Dimension(400,30));
        
        ContainerObject datePanel = new ContainerObject();
        datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.Y_AXIS));
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        
        JLabel heureDebutLabel = new JLabel("H. déb.");
        heureDebutField = new TimePicker();
        heureDebutField.setPreferredSize(new Dimension(150,30));
        heureDebutField.setMaximumSize(new Dimension(150,30));
        
        heureDebutPanel = new ContainerObject();
        heureDebutPanel.setLayout(new BoxLayout(heureDebutPanel,BoxLayout.Y_AXIS));
        heureDebutPanel.setPreferredSize(new Dimension(this.fenetre.getWidth()/8,40));
        heureDebutPanel.add(heureDebutLabel);
        heureDebutPanel.add(heureDebutField);
        
        heureFinLabel = new JLabel("H. fin");
        heureFinField = new TimePicker();
        heureFinField.setPreferredSize(new Dimension(150,30));
        heureFinField.setMaximumSize(new Dimension(150,30));
        
        ContainerObject heureFinPanel = new ContainerObject();
        heureFinPanel.setLayout(new BoxLayout(heureFinPanel,BoxLayout.Y_AXIS));
        heureFinPanel.add(heureFinLabel);
        heureFinPanel.add(heureFinField);
        
        ContainerObject heuresCompoPanel = new ContainerObject();
        heuresCompoPanel.setLayout(new BoxLayout(heuresCompoPanel,BoxLayout.X_AXIS));
        heuresCompoPanel.add(heureDebutPanel);
        heuresCompoPanel.add(heureFinPanel);
        
        
        ContainerObject CompoPanel = new ContainerObject();
        CompoPanel.setLayout(new BoxLayout(CompoPanel,BoxLayout.Y_AXIS));
        CompoPanel.setBorder(BorderFactory.createTitledBorder("Horaires de composition"));
        CompoPanel.add(datePanel);
        CompoPanel.add(heuresCompoPanel);
        
        incidentField = new JCheckBox("Incident");
        incidentPanel = new ContainerObject();
        incidentPanel.add(incidentField);
        
        fraudeField = new JCheckBox("Fraude");
        fraudePanel = new ContainerObject();
        
        fraudePanel.add(fraudeField);
        
        
        this.autreLabel = new JLabel("Observation sur l'incident");
        this.autreField = new JTextArea(" ");
        this.autrePanel = new ContainerObject();
        this.autrePanel.setLayout(new BoxLayout(this.autrePanel,BoxLayout.Y_AXIS));
        this.autrePanel.add(autreLabel);
        this.autrePanel.add(autreField);
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new CompositionSaveListener());
        this.reset = new Bouton("Annuler");
        
        this.controlPanel = new ContainerObject();
        this.controlPanel.add(this.save);
        this.controlPanel.add(this.reset);
        
        this.epreuveCompose = new ContainerObject();
        this.epreuveCompose.setLayout(new BoxLayout(this.epreuveCompose,BoxLayout.Y_AXIS));
        this.epreuveComposeForm = new ContainerObject();
        this.epreuveComposeForm.setMaximumSize(new Dimension(400,300));
        this.epreuveComposeForm.setLayout(new BoxLayout(this.epreuveComposeForm,BoxLayout.Y_AXIS));
        this.epreuveComposeForm.add(epreuveLine);
        this.epreuveComposeForm.add(CompoPanel);
        this.epreuveComposeForm.add(incidentPanel);
        this.epreuveComposeForm.add(this.fraudePanel);
        this.epreuveComposeForm.add(this.autrePanel);
        this.epreuveComposeForm.add(controlPanel);
        
        this.bodyPanel = new JSplitPane();
        this.bodyPanel.setLayout(new BoxLayout(this.bodyPanel,BoxLayout.X_AXIS));
        
       
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.epreuveCompose,this.epreuveComposeForm);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
        //this.fenetre.pack();
        
        
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.ongletPanel = new JPanel();
        this.head.ongletPanel.add(this.addComposition);
        this.head.ongletPanel.add(this.abscence);
       // this.head.ongletPanel.add(this.fraude);

        
        this.head.getTitle().setText(this.head.getTitle().getText()+" :Composition");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
       /*  if(this.fenetre.getUserSession().getJury().getEtape().getTourCode() == 2){
            this.serieField.addActionListener(SerieStateListener.epreuveSecTour(epreuveField, this.fenetre));
        }else{
            this.serieField.addActionListener(SerieStateListener.epreuvePreTour(epreuveField, this.fenetre));
        }


         SerieService seriService = new SerieService();
        List series = seriService.getSerie(this.fenetre.getUserSession().getJury());
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);
        } */

         EpreuveService epreuveService = new EpreuveService();
        if(this.fenetre.getUserSession().getTourComposition().getId()==1){
            List epreuves = epreuveService.getAllEpreuveByTour1(this.fenetre.getUserSession().getSession(),this.fenetre.getUserSession().getJury());
            this.epreuveField.addItem(new Epreuve());
            for(Object epreuve : epreuves){
                this.epreuveField.addItem(epreuve);}
        }else if(this.fenetre.getUserSession().getTourComposition().getId()==2){
            List epreuves = epreuveService.getAllEpreuveByTour2(this.fenetre.getUserSession().getSession());
            this.epreuveField.addItem(new Epreuve());
            for(Object epreuve : epreuves){
                this.epreuveField.addItem(epreuve);
            }
        }
        

        this.service = new CompositionService();
        this.tableModel = new CompositionTableModel(((CompositionService)this.service).getCompositionNoDuplicate(this.fenetre.getUserSession().getSession(), this.fenetre.getUserSession().getJury(), this.fenetre.getUserSession().getJury().getEtape()),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        this.epreuveCompose.add(new JScrollPane(tableau));
        
    }

    public JComboBox getEpreuveField() {
        return epreuveField;
    }

    public void setEpreuveField(JComboBox epreuveField) {
        this.epreuveField = epreuveField;
    }
    
    
    class AbsenceListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
             
            try {
                CompositionPanel p = new CompositionPanel(fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(new AbscenceBodyPanel(p));
                ////p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(CompositionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class FraudeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            FraudePanel fraudeBody = new FraudePanel();
            CompositionPanel p = new CompositionPanel(fenetre);
            p.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,fraudeBody.getListePanel(),fraudeBody.getFormPanel());
            p.bodyPanel.setDividerLocation((fenetre.getWidth()/4)*3);
            p.body.setLayout(new BorderLayout());
            p.body.add(p.bodyPanel);
            try {
                ////p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(CompositionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class CompositionSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            service.setFormData(new HashMap());
            service.getFormData().put("session", fenetre.getUserSession().getSession());
            service.getFormData().put("jury", fenetre.getUserSession().getJury());
            service.getFormData().put("epreuve", epreuveField.getSelectedItem());
            service.getFormData().put("tourComposition", fenetre.getUserSession().getTourComposition());
            service.getFormData().put("dateComposition", dateField.getDate());
            service.getFormData().put("heureDebut", heureDebutField.getTime());
            service.getFormData().put("heureFin", heureFinField.getTime());
            service.getFormData().put("fraude", fraudeField.isSelected());
            service.getFormData().put("incident", incidentField.isSelected());
            service.getFormData().put("observation", autreField.getText());
            try {

                tableModel.addRow(service.save());
                //tableModel.setModelData(((CompositionService)service).getCompositionNoDuplicate(fenetre.getUserSession().getSession(), fenetre.getUserSession().getJury(), fenetre.getUserSession().getJury().getEtape()));
                tableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(CompositionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EmptyDataException ex) {
                Logger.getLogger(CompositionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    /*class SerieStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Serie s = (Serie)serieField.getSelectedItem();
            if(s.getId() != null){
                EpreuveSerieService seriService;
                try {
                    seriService = new EpreuveSerieService();
                    s = (Serie)seriService.getById(s.getId());
                    DefaultComboBoxModel model = (DefaultComboBoxModel) epreuveField.getModel();
                    model.removeAllElements();
                    for(Object epreuve : s.getEpreuves()){
                        model.addElement(epreuve);
                        //epreuveField.addItem(epreuve);
                        //System.out.println(((Epreuve)epreuve).getEpreuveLibelle());
                    }
                    epreuveField.setModel(model);
                } catch (SQLException ex) {
                    Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        
    }*/
    
}
