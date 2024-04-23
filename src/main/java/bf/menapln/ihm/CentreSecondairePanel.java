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
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.JuryTableModel;
import bf.menapln.ihm.tableModel.centreSecondaireModel;
import bf.menapln.service.CentreSecondaireService;
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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class CentreSecondairePanel extends Panneau{

    private ContainerObject localitePanel;
    private final ContainerObject listePanel;
    
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;

    private JLabel centreExamenLabel;
    private JComboBox centreExamenField;
    private ContainerObject centreExamenPanel;

    private JComboBox juryField;
    
    private JLabel centreCompositionLabel;
    private JComboBox centreCompositionField;
    private ContainerObject centreCompositionPanel;
    
    private JLabel nomLabel;
    private JTextField nomField;
    private ContainerObject nomPanel;
    
    private final JSplitPane bodyPanel;

    public CentreSecondairePanel(Fenetre fenetre) {
        super(fenetre);
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
       
        
        this.nomLabel = new JLabel("Libelle Centre Secondaire");
        this.nomField = new JTextField();
        this.nomField.setPreferredSize(new Dimension(200,30));
        this.nomField.setMaximumSize(new Dimension(300,30));
        this.nomPanel = new ContainerObject();
        this.nomPanel.setLayout(new BoxLayout(this.nomPanel,BoxLayout.Y_AXIS));
        this.nomPanel.add(this.nomLabel);
        this.nomPanel.add(this.nomField);

        this.centreCompositionLabel = new JLabel("Centre Composition");
        this.centreCompositionField = new JComboBox();
        this.centreCompositionField.setPreferredSize(new Dimension(200,30));
        this.centreCompositionField.setMaximumSize(new Dimension(300,30));
        
        this.centreCompositionPanel = new ContainerObject();
        this.centreCompositionPanel.setLayout(new BoxLayout(this.centreCompositionPanel,BoxLayout.Y_AXIS));
        this.centreCompositionPanel.add(this.centreCompositionLabel);
        this.centreCompositionPanel.add(this.centreCompositionField);

        
        this.centreExamenLabel = new JLabel("Centre d'examen");
        this.centreExamenField = new JComboBox();
        this.centreExamenField.setPreferredSize(new Dimension(200,30));
        this.centreExamenField.setMaximumSize(new Dimension(300,30));        
        this.centreExamenPanel = new ContainerObject();
        this.centreExamenField.addActionListener(CentreStateListener.juryField(juryField));
        this.centreExamenField.addActionListener(new CentreStateListener(this.centreCompositionField));
        this.centreExamenPanel.setLayout(new BoxLayout(this.centreExamenPanel,BoxLayout.Y_AXIS));
        this.centreExamenPanel.add(this.centreExamenLabel);
        this.centreExamenPanel.add(this.centreExamenField);

        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communeField.addActionListener(CommuneStateListener.centreExamenField(this.centreExamenField));
        this.communeField.setPreferredSize(new Dimension(200,30));
        this.communeField.setMaximumSize(new Dimension(300,30));
        this.communePanel = new ContainerObject();
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.setPreferredSize(new Dimension(200,30));
        this.provinceField.setMaximumSize(new Dimension(300,30));
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.setPreferredSize(new Dimension(200,30));
        this.regionField.setMaximumSize(new Dimension(300,30));
        this.regionField.addActionListener(new RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);
        
        this.save.addActionListener(new CentreSecondaireSaveListener());

        this.localitePanel = new ContainerObject();
        this.localitePanel.setBorder(BorderFactory.createTitledBorder("Localité"));
        this.localitePanel.setLayout(new BoxLayout(this.localitePanel,BoxLayout.Y_AXIS));
        this.localitePanel.setMaximumSize(new Dimension(300,450));
        this.localitePanel.setAlignmentY(TOP_ALIGNMENT);
        this.localitePanel.add(this.regionPanel);
        this.localitePanel.add(this.provincePanel);
        this.localitePanel.add(this.communePanel);
        this.localitePanel.add(this.centreExamenPanel);
        this.localitePanel.add(this.centreCompositionPanel);
        this.localitePanel.add(this.nomPanel);
        this.localitePanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.localitePanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);             
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Liste des centre secondaire du jury"
        );
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(this.fenetre.getBarreDeMenu().candidatPosteJuryListener);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }

        this.service = new CentreSecondaireService();
        CentreSecondaireService centreSecondaireService = new CentreSecondaireService();
        List centreSecondaire = centreSecondaireService.getAll();
        this.tableModel = new centreSecondaireModel(centreSecondaire,this.fenetre);
        JTable liste = new JTable(this.tableModel);
        liste.setRowHeight(25);
        this.listePanel.add(new JScrollPane(liste));
    }

    class CentreSecondaireSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                service = new CentreSecondaireService();
                service.setFormData(new HashMap());
                service.getFormData().put("session", fenetre.getUserSession().getSession());
                service.getFormData().put("jury", fenetre.getUserSession().getJury());
                service.getFormData().put("etablissement", ((Centre)centreCompositionField.getSelectedItem()).getEtatblissement().getId().toString());
                service.getFormData().put("libelle", nomField.getText());
                service.save();
               
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(CandidatPosteJuryForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
