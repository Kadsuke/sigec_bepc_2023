/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.StructureService;
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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class EtablissementForm extends Panneau{
    
    private ContainerObject locPanel;
    private ContainerObject infoEtabPanel;
    private ContainerObject controlPanel;
    
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


    private JTextField codeField;
    private JTextField nomEtabField;
    private JTextField acronymeField;
    private JTextField capaciteField;
    private JComboBox statutField;
    private JComboBox offreEnsField;
    private JComboBox typeStructureField;
    
    public SaveEtablissementListener saveEtablissementListener = new SaveEtablissementListener();
    public RegionStateListener regionStateListener = new RegionStateListener();
    
    //DefaultComboBoxModel comboModel;
    public EtablissementForm(Fenetre fenetre) {
        super(fenetre);
       
        

        this.centreExamenLabel = new JLabel("Centre d'examen");
        this.centreExamenField = new JComboBox();
        this.centreExamenField.setPreferredSize(new Dimension(280,30));
        this.centreExamenPanel = new ContainerObject();
        this.centreExamenPanel.setLayout(new BoxLayout(this.centreExamenPanel,BoxLayout.Y_AXIS));
        this.centreExamenPanel.add(this.centreExamenLabel);
        this.centreExamenPanel.add(this.centreExamenField);
        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communeField.setPreferredSize(new Dimension(280,30));
        this.communePanel = new ContainerObject();
        this.communeField.addActionListener(new CommuneStateListener(this.centreExamenField));
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.setPreferredSize(new Dimension(280,30));
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.setPreferredSize(new Dimension(280,30));
        this.regionField.addActionListener(new bf.menapln.actionListener.RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);

        
        
        this.locPanel = new ContainerObject();
        this.locPanel.setPreferredSize(new Dimension(this.fenetre.getWidth()-20,90));
        this.locPanel.add(regionPanel);
        this.locPanel.add(provincePanel);
        this.locPanel.add(communePanel);
        this.locPanel.add(centreExamenPanel);
        this.locPanel.setBorder(BorderFactory.createTitledBorder("Localisation de l'établissement"));
        
        JLabel codeLabel = new JLabel("Code");
        codeField = new JTextField();
        codeField.setPreferredSize(new Dimension(200,30));
        
        ContainerObject codePanel = new ContainerObject();
        codePanel.setLayout(new BoxLayout(codePanel,BoxLayout.Y_AXIS));
        codePanel.add(codeLabel);
        codePanel.add(codeField);
        
        JLabel nomEtabLabel = new JLabel("Nom de l'étab.");
        nomEtabField = new JTextField();
        nomEtabField.setPreferredSize(new Dimension(200,30));
        
        ContainerObject nomEtabPanel = new ContainerObject();
        nomEtabPanel.setLayout(new BoxLayout(nomEtabPanel,BoxLayout.Y_AXIS));
        nomEtabPanel.add(nomEtabLabel);
        nomEtabPanel.add(nomEtabField);
        
        JLabel acronymeLabel = new JLabel("Acronyme");
        acronymeField = new JTextField();
        acronymeField.setPreferredSize(new Dimension(200,30));
        
        ContainerObject acronymePanel = new ContainerObject();
        acronymePanel.setLayout(new BoxLayout(acronymePanel,BoxLayout.Y_AXIS));
        acronymePanel.add(acronymeLabel);
        acronymePanel.add(acronymeField);
        
        JLabel statutLabel = new JLabel("Statut");
        statutField = new JComboBox();
        statutField.setPreferredSize(new Dimension(200,30));
        //statutField.addItem("Public");
        //statutField.addItem("Privé");
        
        ContainerObject statutPanel = new ContainerObject();
        statutPanel.setLayout(new BoxLayout(statutPanel,BoxLayout.Y_AXIS));
        statutPanel.add(statutLabel);
        statutPanel.add(statutField);
        
        ContainerObject infEtabLine1 = new ContainerObject();
        infEtabLine1.add(codePanel);
        infEtabLine1.add(nomEtabPanel);
        infEtabLine1.add(acronymePanel);
        infEtabLine1.add(statutPanel);
        
        JLabel capaciteLabel = new JLabel("Capcité");
        capaciteField = new JTextField();
        capaciteField.setPreferredSize(new Dimension(280,30));
        
        ContainerObject capacitePanel = new ContainerObject();
        capacitePanel.setLayout(new BoxLayout(capacitePanel,BoxLayout.Y_AXIS));
        capacitePanel.add(capaciteLabel);
        capacitePanel.add(capaciteField);
        
        JLabel offreEnsLabel = new JLabel("Offre d'ens.");
        offreEnsField = new JComboBox();
        offreEnsField.setPreferredSize(new Dimension(280,30));
        /*offreEnsField.addItem("Général");
        offreEnsField.addItem("Tecnique");
        offreEnsField.addItem("Professionel");*/
        
        ContainerObject offreEnsPanel = new ContainerObject();
        offreEnsPanel.setLayout(new BoxLayout(offreEnsPanel,BoxLayout.Y_AXIS));
        offreEnsPanel.add(offreEnsLabel);
        offreEnsPanel.add(offreEnsField);
        
        JLabel typeStructureLabel = new JLabel("Type de cand.");
        typeStructureField = new JComboBox();
        typeStructureField.setPreferredSize(new Dimension(280,30));
        //typeStructureField.addItem("Regulier");
        //typeStructureField.addItem("Libre");
        
        ContainerObject typeCandidatPanel = new ContainerObject();
        typeCandidatPanel.setLayout(new BoxLayout(typeCandidatPanel,BoxLayout.Y_AXIS));
        typeCandidatPanel.add(typeStructureLabel);
        typeCandidatPanel.add(typeStructureField);
        
        ContainerObject infEtabLine2 = new ContainerObject();
        infEtabLine2.add(offreEnsPanel);
        infEtabLine2.add(capacitePanel);
        infEtabLine2.add(typeCandidatPanel);
        
        this.infoEtabPanel = new ContainerObject();
        this.infoEtabPanel.setPreferredSize(new Dimension(this.fenetre.getWidth()-20,160));
        this.infoEtabPanel.setLayout(new BoxLayout(this.infoEtabPanel,BoxLayout.Y_AXIS));
        this.infoEtabPanel.setBorder(BorderFactory.createTitledBorder("Information sur l'étatblissement"));
        this.infoEtabPanel.add(infEtabLine1);
        this.infoEtabPanel.add(infEtabLine2);
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(this.saveEtablissementListener);
        this.reset = new Bouton("Annuler");
        
        this.controlPanel = new ContainerObject();
        this.controlPanel.add(save);
        this.controlPanel.add(reset);
        
        
        this.body.add(this.locPanel);
        this.body.add(this.infoEtabPanel);
        this.body.add(this.controlPanel);
        
        
        
        //this.body.setLayout(new BorderLayout());
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des établissements");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(this.fenetre.getBarreDeMenu().etablissementListener);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();        
        TypeService typeService = new TypeService();
        
        List statuts = typeService.getAll("Statut étab.");
        for(Object statut : statuts){
            this.statutField.addItem((Type)statut);
        }
        
        List offres = typeService.getAll("Offre ens.");
        for(Object offre : offres){
            this.offreEnsField.addItem((Type)offre);
        }
        
        List typeStructures = typeService.getAll("Type struct.");
        for(Object ts : typeStructures){
            this.typeStructureField.addItem((Type)ts);
        }
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
        
        /*List communes = localiteService.getAll("commune");
        for(Object commune : communes){
            this.communeField.addItem((Localite)commune);
        }*/
    }
    
    class SaveEtablissementListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                StructureService service = new StructureService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("codeStructure", codeField.getText());
                service.getFormData().put("nomStrucure", nomEtabField.getText());
                service.getFormData().put("acronymeStructure", acronymeField.getText());
                service.getFormData().put("localiteStructure", ((Localite)communeField.getSelectedItem()).getId().toString());
                service.getFormData().put("centreExamenStructure", ((Localite)centreExamenField.getSelectedItem()).getId().toString());
                service.getFormData().put("statutEtablissement", ((Type)statutField.getSelectedItem()).getId().toString());
                service.getFormData().put("offreEnseignement", ((Type)offreEnsField.getSelectedItem()).getId().toString());
                service.getFormData().put("typeStructure", ((Type)typeStructureField.getSelectedItem()).getId().toString());
                service.save();
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class RegionStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            LocaliteService localiteService;
            try {
                localiteService = new LocaliteService();
                List provinces = localiteService.getByParentId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
                DefaultComboBoxModel comboModel = (DefaultComboBoxModel)provinceField.getModel();
                comboModel.removeAllElements();
                comboModel.addElement(new Localite());
                for(Object province : provinces){
                    comboModel.addElement(province);
                    //provinceField.addItem((Localite)province);
                }
                provinceField.setModel(comboModel);
            } catch (SQLException ex) {
                Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class ProvincesStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //System.out.println("est" + (((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() == null));
            LocaliteService localiteService;
            try {
                localiteService = new LocaliteService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List communes = localiteService.getByParentId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)communeField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Localite());
                    for(Object commune : communes){
                        comboModel.addElement(commune);
                        //provinceField.addItem((Localite)province);
                    }
                    communeField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
