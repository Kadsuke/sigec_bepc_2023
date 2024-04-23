/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CentreStateListener;
import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.Centre;
import bf.menapln.entity.Localite;
import bf.menapln.ihm.tableModel.JuryTableModel;
import bf.menapln.service.CentreService;
import bf.menapln.service.JuryService;
import bf.menapln.service.LocaliteService;
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
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class JuryPanel extends Panneau{

    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;
    
    private  JLabel centreExamenLabel;
    private  JComboBox centreExamenField;
    private  ContainerObject centreExamenPanel;

    private JLabel centreCompositionLabel;
    private JComboBox centreCompositionField;
    private ContainerObject centreCompositionPanel;
    
    private JLabel juryLibelleLabel;
    private JTextField juryLibelleField;
    private ContainerObject juryLibellePanel;
    
    private JLabel juryCapaciteLabel;
    private JTextField juryCapaciteField;
    private ContainerObject juryCapacitePanel;
    
    private final ContainerObject formPanel;
    private final ContainerObject listePanel;
    private final JSplitPane bodyPanel;
    public JuryPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());


        this.centreCompositionLabel = new JLabel("Centre Composition");
        this.centreCompositionField = new JComboBox();
        this.centreCompositionField.setPreferredSize(new Dimension(200,30));
        this.centreCompositionField.setMaximumSize(new Dimension(400,30));
        this.centreCompositionField.setMinimumSize(new Dimension(200,30));
        
        this.centreCompositionPanel = new ContainerObject();
        this.centreCompositionPanel.setLayout(new BoxLayout(this.centreCompositionPanel,BoxLayout.Y_AXIS));
        this.centreCompositionPanel.add(this.centreCompositionLabel);
        this.centreCompositionPanel.add(this.centreCompositionField);

        this.centreExamenLabel = new JLabel("Centre d'Examen");
        this.centreExamenField = new JComboBox();
        this.centreExamenField.setPreferredSize(new Dimension(200,30));
        this.centreExamenField.setMaximumSize(new Dimension(400,30));
        this.centreExamenField.setMinimumSize(new Dimension(200,30));
        this.centreExamenPanel = new ContainerObject();
        this.centreExamenField.addActionListener(new CentreStateListener(this.centreCompositionField));
        this.centreExamenPanel.setLayout(new BoxLayout(this.centreExamenPanel,BoxLayout.Y_AXIS));
        this.centreExamenPanel.add(this.centreExamenLabel);
        this.centreExamenPanel.add(this.centreExamenField);

        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communeField.setPreferredSize(new Dimension(200,30));
        this.communeField.setMaximumSize(new Dimension(400,30));
        this.communeField.setMinimumSize(new Dimension(200,30));
        this.communePanel = new ContainerObject();
        this.communeField.addActionListener(new CommuneStateListener(this.centreExamenField));
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.setPreferredSize(new Dimension(200,30));
        this.provinceField.setMaximumSize(new Dimension(400,30));
        this.provinceField.setMinimumSize(new Dimension(200,30));
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.setPreferredSize(new Dimension(200,30));
        this.regionField.setMaximumSize(new Dimension(400,30));
        this.regionField.setMinimumSize(new Dimension(200,30));
        this.regionField.addActionListener(new RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);
        
        this.juryLibelleLabel = new JLabel("Libellé jury");
        this.juryLibelleField = new JTextField();
        this.juryLibelleField.setPreferredSize(new Dimension(200,30));
        this.juryLibelleField.setMinimumSize(new Dimension(200,30));
        this.juryLibelleField.setMaximumSize(new Dimension(400,30));
        
        this.juryLibellePanel = new ContainerObject();
        this.juryLibellePanel.setLayout(new BoxLayout(this.juryLibellePanel,BoxLayout.Y_AXIS));
        this.juryLibellePanel.add(this.juryLibelleLabel);
        this.juryLibellePanel.add(this.juryLibelleField);
        
        this.juryCapaciteLabel = new JLabel("Capacité jury");
        this.juryCapaciteField = new JTextField();
        this.juryCapaciteField.setPreferredSize(new Dimension(200,30));
        this.juryCapaciteField.setMinimumSize(new Dimension(200,30));
        this.juryCapaciteField.setMaximumSize(new Dimension(400,30));
        
        this.juryCapacitePanel = new ContainerObject();
        this.juryCapacitePanel.setLayout(new BoxLayout(this.juryCapacitePanel,BoxLayout.Y_AXIS));
        this.juryCapacitePanel.add(this.juryCapaciteLabel);
        this.juryCapacitePanel.add(this.juryCapaciteField);
        
        this.save.addActionListener(new JurySaveListener());
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.regionPanel);
        this.formPanel.add(this.provincePanel);
        this.formPanel.add(this.communePanel);
        this.formPanel.add(this.centreExamenPanel);
        this.formPanel.add(this.centreCompositionPanel);
        this.formPanel.add(this.juryLibellePanel);
        this.formPanel.add(this.juryCapacitePanel);
        this.formPanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
      /*   
        CentreService centreService = new CentreService();
        List centres = centreService.getCentres();
        for(Object centre : centres){
            this.centreCompositionField.addItem(centre);
        }*/
        
        this.service = new JuryService();
        List jurys = this.service.getAll("");
        this.tableModel = new JuryTableModel(jurys,this.fenetre);
        JTable liste = new JTable(this.tableModel);
        liste.setRowHeight(25);
        this.listePanel.add(new JScrollPane(liste));
    }
    
    class JurySaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                //String parentloc = (((Localite)parentLocaliteField.getSelectedItem()).getId() != null)?((Localite)parentLocaliteField.getSelectedItem()).getId().toString():"0";
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("session_id", fenetre.getUserSession().getSession().getId().toString());
                service.getFormData().put("etablissement_id", ((Centre)centreCompositionField.getSelectedItem()).getEtatblissement().getId().toString());
                service.getFormData().put("centreExamen_id", ((Localite)centreExamenField.getSelectedItem()).getId().toString());
                service.getFormData().put("juryLibelle", juryLibelleField.getText());
                service.getFormData().put("juryCapacite", juryCapaciteField.getText());
              //  service.getFormData().put("centreExamen_id", ((Localite)centreExamenField.getSelectedItem()).getId().toString());
                service.save();

                fenetre.getBarreDeMenu().juryListener.actionPerformed(ae);
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
