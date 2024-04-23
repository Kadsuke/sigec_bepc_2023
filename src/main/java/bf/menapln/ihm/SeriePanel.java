/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Serie;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.SerieService;
import bf.menapln.service.TypeService;
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
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class SeriePanel extends Panneau{
    
    private JSplitPane bodyPanel;
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    
    private JLabel offreEnsLabel;
    private JComboBox offreEnsField;
    
    private JLabel serieLabel;
    private JTextField serieField;
    
    private JLabel serieDefLabel;
    private JTextArea serieDefField;
    
    public SaveSerieListener saveSerieListener = new SaveSerieListener();
    
    public SeriePanel(Fenetre fenetre) {
        super(fenetre);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
        this.offreEnsLabel = new JLabel("Offre d'ens.");
        this.offreEnsField = new JComboBox();
        this.offreEnsField.setPreferredSize(new Dimension(200,30));
        this.offreEnsField.setMaximumSize(new Dimension(300,30));
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JTextField();
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(300,30));
        
        this.serieDefLabel = new JLabel("Définition");
        this.serieDefField = new JTextArea();
        this.serieDefField.setPreferredSize(new Dimension(200,100));
        this.serieDefField.setMaximumSize(new Dimension(300,100));
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(this.saveSerieListener);
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.offreEnsLabel);
        this.formPanel.add(this.offreEnsField);
        this.formPanel.add(this.serieLabel);
        this.formPanel.add(this.serieField);
        this.formPanel.add(this.serieDefLabel);
        this.formPanel.add(this.serieDefField);
        this.formPanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/3)*2);
        
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
        this.foot.add(new JLabel("foot"));
    }
    
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des options");
        this.head.getAddNewEntity().setText("Ajouter une option");
        //this.head.getAddNewEntity().addActionListener(new AddEtablissementListener());
        
        TypeService typeService = new TypeService();
        List offres = typeService.getAll("Offre ens.");
        for(Object offre : offres){
            this.offreEnsField.addItem((Type)offre);
        }
        
        List series = null;
        try {
            this.service = new SerieService();
            series = this.service.getAll("");
            System.out.println(series.size());
        } catch (SQLException ex) {
            Logger.getLogger(SessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[][] data = new String[series.size()][6];
        for(int line = 0 ; line < series.size() ; line++){
            data[line][0] = ((Serie)series.get(line)).getId().toString();
            data[line][1] = ((Serie)series.get(line)).getSerieLibelle();
            data[line][2] = ((Serie)series.get(line)).getDefinition();
            data[line][3] = ((Serie)series.get(line)).getOffreEnseignement().getLibelle();
            data[line][4] = "";//((Session)sessions.get(line)).getDateDebutSessNorm().toString();
            data[line][5] = "";//((Session)sessions.get(line)).getDateFinSessNorm().toString();
            
        }
        
        
        //Les titres des colonnes
        String title[] = {"ID","Option", "Définition", "Offre ens.","",""};
        TableModel model = new TableModel(data,title);
        JTable tableau = new JTable(model);
        //tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
       
        this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        //this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        this.listePanel.add(new JScrollPane(tableau));
    }
    
    class SaveSerieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("event");
            try {
                service = new SerieService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("offreEnseignement", ((Type)offreEnsField.getSelectedItem()).getId().toString());
                service.getFormData().put("serieLibelle", serieField.getText());
                service.getFormData().put("definition",serieDefField.getText());
                service.save();
                fenetre.getBarreDeMenu().serieListener.actionPerformed(ae);
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
