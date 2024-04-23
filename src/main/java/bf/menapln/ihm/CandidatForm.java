/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Pays;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Structure;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.CandidatService;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.PaysService;
import bf.menapln.service.SerieService;
import bf.menapln.service.SessionService;
import bf.menapln.service.TypeService;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author coulibaly
 */
public class CandidatForm extends Panneau{
    private ContainerObject formPanel;
    private ContainerObject localisationPanel;
    private ContainerObject infoCandidaturePanel;
    private ContainerObject infoCandidatPanel;
    private ContainerObject situationHandicapPanel;
    private ContainerObject infoParentPanel;
    private ContainerObject line1;
    private ContainerObject line2;
    private ContainerObject line3;
    private ContainerObject line4;
    
    private JLabel sessionLabel;
    private JComboBox sessionField;
    private ContainerObject sessionPanel;
    
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;
    
    private JLabel etablissementLabel;
    private JComboBox etablissementField;
    private ContainerObject etablissementPanel;
    
    private JLabel secteurCandidatLabel;
    private JComboBox secteurCandidatField;
    private ContainerObject secteurCandidatPanel;
    
    private JLabel typeCandidatureLabel;
    private JComboBox typeCandidatureField;
    private ContainerObject typeCandidaturePanel;
    
    private JLabel typeInscriptionLabel;
    private JComboBox typeInscriptionField;
    private ContainerObject typeInscriptionPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;
    
    private JLabel iueLabel;
    private JTextField iueField;
    private ContainerObject iuePanel;
    
    private JLabel nomLabel;
    private JTextField nomField;
    private ContainerObject nomPanel;
    
    private JLabel prenomLabel;
    private JTextField prenomField;
    private ContainerObject prenomPanel;
    
    private JLabel sexeLabel;
    private JComboBox sexeField;
    private ContainerObject sexePanel;
    
    private JLabel paysNaissanceLabel;
    private JComboBox paysNaissanceField;
    private ContainerObject paysNaissancePanel;
    
    private JLabel nationaliteLabel;
    private JComboBox nationaliteField;
    private ContainerObject nationalitePanel;
    
    private JLabel telephoneLabel;
    private JTextField telephoneField;
    private ContainerObject telephonePanel;
    
    private JLabel dateNaissanceLabel;
    private DatePicker dateNaissanceField;
    private ContainerObject dateNaissancePanel;
    
    private JLabel numActeNaissanceLabel;
    private JTextField numActeNaissanceField;
    private ContainerObject numActeNaissancePanel;
    
    private JLabel lieuNaissanceLabel;
    private JTextField lieuNaissanceField;
    private ContainerObject lieuNaissancePanel;
    
    private JLabel dernierDiplomeLabel;
    private JTextField dernierDiplomeField;
    private ContainerObject dernierDiplomePanel;
    
    private JLabel anneeDernierDiplomeLabel;
    private JTextField anneeDernierDiplomeField;
    private ContainerObject anneeDernierDiplomePanel;
    
    private JLabel typeHandicapLabel;
    private JComboBox typeHandicapField;
    private ContainerObject typeHandicapPanel;
    
    private JLabel natureHandicapLabel;
    private JComboBox natureHandicapField;
    private ContainerObject natureHandicapPanel;
    
    private JLabel prescriptionLabel;
    private JComboBox prescriptionField;
    private ContainerObject prescriptionPanel;
    
    private JLabel sportLabel;
    private JComboBox sportField;
    private ContainerObject sportPanel;

    private JLabel centreExamenLabel;
    private JComboBox centreExamenField;
    private ContainerObject centreExamenPanel;

    private JLabel concoursRatacheLabel;
    private JComboBox concoursRatacheField;
    private ContainerObject concoursRatachePanel;
    
    public CandidatForm(Fenetre fenetre) {
        super(fenetre);
        
        
        
        this.secteurCandidatLabel = new JLabel("Secteur/village cand.");
        this.secteurCandidatField = new JComboBox();
        this.secteurCandidatField.setPreferredSize(new Dimension(200,30));
        this.secteurCandidatPanel = new ContainerObject();
        this.secteurCandidatPanel.setLayout(new BoxLayout(this.secteurCandidatPanel,BoxLayout.Y_AXIS));
        this.secteurCandidatPanel.add(this.secteurCandidatLabel);
        this.secteurCandidatPanel.add(this.secteurCandidatField);
        
        this.etablissementLabel = new JLabel("Etablissement cand.");
        this.etablissementField = new JComboBox();
        this.etablissementField.setPreferredSize(new Dimension(200,30));
        this.etablissementPanel = new ContainerObject();
        this.etablissementPanel.setLayout(new BoxLayout(this.etablissementPanel,BoxLayout.Y_AXIS));
        this.etablissementPanel.add(this.etablissementLabel);
        this.etablissementPanel.add(this.etablissementField);

        this.centreExamenLabel = new JLabel("Centre d'examen");
        this.centreExamenField = new JComboBox();
        this.centreExamenField.setPreferredSize(new Dimension(200,30));
        this.centreExamenPanel = new ContainerObject();
        this.centreExamenPanel.setLayout(new BoxLayout(this.centreExamenPanel,BoxLayout.Y_AXIS));
        this.centreExamenPanel.add(this.centreExamenLabel);
        this.centreExamenPanel.add(this.centreExamenField);
        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communeField.setPreferredSize(new Dimension(200,30));
        this.communePanel = new ContainerObject();
        this.communeField.addActionListener(new CommuneStateListener(this.secteurCandidatField,this.etablissementField, this.centreExamenField));
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.setPreferredSize(new Dimension(200,30));
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.setPreferredSize(new Dimension(200,30));
        this.regionField.addActionListener(new RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);
        
        this.localisationPanel = new ContainerObject();
        this.localisationPanel.setLayout(new BoxLayout(this.localisationPanel,BoxLayout.X_AXIS));
        this.localisationPanel.setBorder(BorderFactory.createTitledBorder("Localisation du candidat"));
        ((TitledBorder)this.localisationPanel.getBorder()).setTitleColor(Color.red);
        //((TitledBorder)this.localisationPanel.getBorder()).setBorder(BorderFactory.createLineBorder(Color.red));
        this.localisationPanel.add(this.regionPanel);
        this.localisationPanel.add(this.provincePanel);
        this.localisationPanel.add(this.communePanel);
        this.localisationPanel.add(this.centreExamenPanel);
        this.localisationPanel.add(this.secteurCandidatPanel);
        this.localisationPanel.add(this.etablissementPanel);
        
        this.typeCandidatureLabel = new JLabel("Type candidature");
        this.typeCandidatureField = new JComboBox();
        this.typeCandidatureField.setPreferredSize(new Dimension(200,30));
        this.typeCandidaturePanel = new ContainerObject();
        this.typeCandidaturePanel.setLayout(new BoxLayout(this.typeCandidaturePanel,BoxLayout.Y_AXIS));
        this.typeCandidaturePanel.add(this.typeCandidatureLabel);
        this.typeCandidaturePanel.add(this.typeCandidatureField);
        
        this.typeInscriptionLabel = new JLabel("Type insc.");
        this.typeInscriptionField = new JComboBox();
        this.typeInscriptionField.setPreferredSize(new Dimension(200,30));
        this.typeInscriptionPanel = new ContainerObject();
        this.typeInscriptionPanel.setLayout(new BoxLayout(this.typeInscriptionPanel,BoxLayout.Y_AXIS));
        this.typeInscriptionPanel.add(this.typeInscriptionLabel);
        this.typeInscriptionPanel.add(this.typeInscriptionField);
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);

        this.concoursRatacheLabel = new JLabel("Concours Rata.");
        this.concoursRatacheField = new JComboBox();
        this.concoursRatacheField.setPreferredSize(new Dimension(200,30));
        this.concoursRatachePanel = new ContainerObject();
        this.concoursRatachePanel.setLayout(new BoxLayout(this.concoursRatachePanel,BoxLayout.Y_AXIS));
        this.concoursRatachePanel.add(this.concoursRatacheLabel);
        this.concoursRatachePanel.add(this.concoursRatacheField);
        
        this.infoCandidaturePanel = new ContainerObject();
        this.infoCandidaturePanel.setLayout(new BoxLayout(this.infoCandidaturePanel,BoxLayout.X_AXIS));
        this.infoCandidaturePanel.setBorder(BorderFactory.createTitledBorder("Information sur la candidature"));
        ((TitledBorder)this.infoCandidaturePanel.getBorder()).setTitleColor(Color.red);
        //((TitledBorder)this.infoCandidaturePanel.getBorder()).setBorder(BorderFactory.createLineBorder(Color.red));
        this.infoCandidaturePanel.add(this.typeCandidaturePanel);
        this.infoCandidaturePanel.add(this.typeInscriptionPanel);
        this.infoCandidaturePanel.add(this.seriePanel);
        this.infoCandidaturePanel.add(this.concoursRatachePanel);
        
        this.iueLabel = new JLabel("Identifiant");
        this.iueField = new JTextField();
        this.iueField.setPreferredSize(new Dimension(200,30));
        
        this.iuePanel = new ContainerObject();
        this.iuePanel.setLayout(new BoxLayout(this.iuePanel,BoxLayout.Y_AXIS));
        this.iuePanel.add(this.iueLabel);
        this.iuePanel.add(this.iueField);
        
        this.prenomLabel = new JLabel("Prénom");
        this.prenomField = new JTextField();
        this.prenomField.setPreferredSize(new Dimension(200,30));
        this.prenomPanel = new ContainerObject();
        this.prenomPanel.setLayout(new BoxLayout(this.prenomPanel,BoxLayout.Y_AXIS));
        this.prenomPanel.add(this.prenomLabel);
        this.prenomPanel.add(this.prenomField);
        
        this.nomLabel = new JLabel("Nom");
        this.nomField = new JTextField();
        this.nomField.setPreferredSize(new Dimension(200,30));
        this.nomPanel = new ContainerObject();
        this.nomPanel.setLayout(new BoxLayout(this.nomPanel,BoxLayout.Y_AXIS));
        this.nomPanel.add(this.nomLabel);
        this.nomPanel.add(this.nomField);
        
        this.sexeLabel = new JLabel("Sexe");
        this.sexeField = new JComboBox();
        this.sexeField.setPreferredSize(new Dimension(200,30));
        this.sexeField.addItem("Masculin");
        this.sexeField.addItem("Feminin");
        this.sexePanel = new ContainerObject();
        this.sexePanel.setLayout(new BoxLayout(this.sexePanel,BoxLayout.Y_AXIS));
        this.sexePanel.add(this.sexeLabel);
        this.sexePanel.add(this.sexeField);
        
        this.paysNaissanceLabel = new JLabel("Pays Nais.");
        this.paysNaissanceField= new JComboBox();
        this.paysNaissanceField.setPreferredSize(new Dimension(200,30));
        this.paysNaissancePanel = new ContainerObject();
        this.paysNaissancePanel.setLayout(new BoxLayout(this.paysNaissancePanel,BoxLayout.Y_AXIS));
        this.paysNaissancePanel.add(this.paysNaissanceLabel);
        this.paysNaissancePanel.add(this.paysNaissanceField);
        
        this.nationaliteLabel = new JLabel("Nationalité");
        this.nationaliteField= new JComboBox();
        this.nationaliteField.setPreferredSize(new Dimension(200,30));
        this.nationalitePanel = new ContainerObject();
        this.nationalitePanel.setLayout(new BoxLayout(this.nationalitePanel,BoxLayout.Y_AXIS));
        this.nationalitePanel.add(this.nationaliteLabel);
        this.nationalitePanel.add(this.nationaliteField);
        
        this.telephoneLabel = new JLabel("Tél.");
        this.telephoneField= new JTextField();
        this.telephoneField.setPreferredSize(new Dimension(200,30));
        this.telephonePanel = new ContainerObject();
        this.telephonePanel.setLayout(new BoxLayout(this.telephonePanel,BoxLayout.Y_AXIS));
        this.telephonePanel.add(this.telephoneLabel);
        this.telephonePanel.add(this.telephoneField);
        
        this.dateNaissanceLabel = new JLabel("Date nais.");
        this.dateNaissanceField= new DatePicker();
        this.dateNaissanceField.setPreferredSize(new Dimension(200,30));
        this.dateNaissancePanel = new ContainerObject();
        this.dateNaissancePanel.setLayout(new BoxLayout(this.dateNaissancePanel,BoxLayout.Y_AXIS));
        this.dateNaissancePanel.add(this.dateNaissanceLabel);
        this.dateNaissancePanel.add(this.dateNaissanceField);
        
        this.numActeNaissanceLabel = new JLabel("Num. Acte");
        this.numActeNaissanceField= new JTextField();
        this.numActeNaissanceField.setPreferredSize(new Dimension(200,30));
        this.numActeNaissancePanel = new ContainerObject();
        this.numActeNaissancePanel.setLayout(new BoxLayout(this.numActeNaissancePanel,BoxLayout.Y_AXIS));
        this.numActeNaissancePanel.add(this.numActeNaissanceLabel);
        this.numActeNaissancePanel.add(this.numActeNaissanceField);
        
        this.lieuNaissanceLabel = new JLabel("Lieu nais.");
        this.lieuNaissanceField= new JTextField();
        this.lieuNaissanceField.setPreferredSize(new Dimension(200,30));
        this.lieuNaissancePanel = new ContainerObject();
        this.lieuNaissancePanel.setLayout(new BoxLayout(this.lieuNaissancePanel,BoxLayout.Y_AXIS));
        this.lieuNaissancePanel.add(this.lieuNaissanceLabel);
        this.lieuNaissancePanel.add(this.lieuNaissanceField);
        
        this.dernierDiplomeLabel = new JLabel("Dernier diplome");
        this.dernierDiplomeField= new JTextField();
        this.dernierDiplomeField.setPreferredSize(new Dimension(200,30));
        this.dernierDiplomePanel = new ContainerObject();
        this.dernierDiplomePanel.setLayout(new BoxLayout(this.dernierDiplomePanel,BoxLayout.Y_AXIS));
        this.dernierDiplomePanel.add(this.dernierDiplomeLabel);
        this.dernierDiplomePanel.add(this.dernierDiplomeField);
        
        this.anneeDernierDiplomeLabel = new JLabel("Année Der. dip.");
        this.anneeDernierDiplomeField = new JTextField();
        this.anneeDernierDiplomeField.setPreferredSize(new Dimension(200,30));
        this.anneeDernierDiplomePanel = new ContainerObject();
        this.anneeDernierDiplomePanel.setLayout(new BoxLayout(this.anneeDernierDiplomePanel,BoxLayout.Y_AXIS));
        this.anneeDernierDiplomePanel.add(this.anneeDernierDiplomeLabel);
        this.anneeDernierDiplomePanel.add(this.anneeDernierDiplomeField);
        
        this.line1 = new ContainerObject();
        this.line1.setLayout(new BoxLayout(this.line1,BoxLayout.X_AXIS));
        this.line1.add(this.iuePanel);
        this.line1.add(this.nomPanel);
        this.line1.add(this.prenomPanel);
        this.line1.add(this.telephonePanel);
        
        this.line2 = new ContainerObject();
        this.line2.setLayout(new BoxLayout(this.line2,BoxLayout.X_AXIS));
        this.line2.add(this.sexePanel);
        this.line2.add(this.paysNaissancePanel);
        this.line2.add(this.nationalitePanel);
        
        this.line3 = new ContainerObject();
        this.line3.setLayout(new BoxLayout(this.line3,BoxLayout.X_AXIS));
        this.line3.add(this.dateNaissancePanel);
        this.line3.add(this.lieuNaissancePanel);
        this.line3.add(this.numActeNaissancePanel);
        
        this.line4 = new ContainerObject();
        this.line4.setLayout(new BoxLayout(this.line4,BoxLayout.X_AXIS));
        this.line4.add(this.dernierDiplomePanel);
        this.line4.add(this.anneeDernierDiplomePanel);
        
        this.infoCandidatPanel = new ContainerObject();//BorderFactory.createTitledBorder("Information sur le candidat")
        this.infoCandidatPanel.setLayout(new BoxLayout(this.infoCandidatPanel,BoxLayout.Y_AXIS));
        this.infoCandidatPanel.setBorder(BorderFactory.createTitledBorder( "Information sur le candidat"));
        ((TitledBorder)this.infoCandidatPanel.getBorder()).setTitleColor(Color.red);
        this.infoCandidatPanel.add(this.line1);
        this.infoCandidatPanel.add(this.line2);
        this.infoCandidatPanel.add(this.line3);
        this.infoCandidatPanel.add(this.line4);
        
        this.typeHandicapLabel = new JLabel("Type handicap");
        this.typeHandicapField = new JComboBox();
        this.typeHandicapField.setPreferredSize(new Dimension(200,30));
        this.typeHandicapPanel = new ContainerObject();
        this.typeHandicapPanel.setLayout(new BoxLayout(this.typeHandicapPanel,BoxLayout.Y_AXIS));
        this.typeHandicapPanel.add(this.typeHandicapLabel);
        this.typeHandicapPanel.add(this.typeHandicapField);
        
        this.natureHandicapLabel = new JLabel("Nat. handicap");
        this.natureHandicapField = new JComboBox();
        this.natureHandicapField.setPreferredSize(new Dimension(200,30));
        this.natureHandicapPanel = new ContainerObject();
        this.natureHandicapPanel.setLayout(new BoxLayout(this.natureHandicapPanel,BoxLayout.Y_AXIS));
        this.natureHandicapPanel.add(this.natureHandicapLabel);
        this.natureHandicapPanel.add(this.natureHandicapField);
        
        this.prescriptionLabel = new JLabel("Prescription");
        this.prescriptionField = new JComboBox();
        this.prescriptionField.setPreferredSize(new Dimension(200,30));
        this.prescriptionPanel = new ContainerObject();
        this.prescriptionPanel.setLayout(new BoxLayout(this.prescriptionPanel,BoxLayout.Y_AXIS));
        this.prescriptionPanel.add(this.prescriptionLabel);
        this.prescriptionPanel.add(this.prescriptionField);
        
        this.sportLabel = new JLabel("Sport");
        this.sportField = new JComboBox();
        this.sportField.setPreferredSize(new Dimension(200,30));
        this.sportField.addItem("Apte");
        this.sportField.addItem("Inapte");
        this.sportPanel = new ContainerObject();
        this.sportPanel.setLayout(new BoxLayout(this.sportPanel,BoxLayout.Y_AXIS));
        this.sportPanel.add(this.sportLabel);
        this.sportPanel.add(this.sportField);
        
        this.situationHandicapPanel = new ContainerObject();
        this.situationHandicapPanel.setLayout(new BoxLayout(this.situationHandicapPanel,BoxLayout.X_AXIS));
        this.situationHandicapPanel.setBorder(BorderFactory.createTitledBorder("Situation de handicap"));
        ((TitledBorder)this.situationHandicapPanel.getBorder()).setTitleColor(Color.red);
        this.situationHandicapPanel.add(this.typeHandicapPanel);
        this.situationHandicapPanel.add(this.natureHandicapPanel);
        this.situationHandicapPanel.add(this.prescriptionPanel);
        this.situationHandicapPanel.add(this.sportPanel);
        
        this.save = new Bouton("Enregistre");
        this.save.addActionListener(new SaveCandidatListener());
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.localisationPanel);
        this.formPanel.add(this.infoCandidaturePanel);
        this.formPanel.add(this.infoCandidatPanel);
        this.formPanel.add(this.situationHandicapPanel);
        this.formPanel.add(this.formControlPanel);
        this.formPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.body.setLayout(new BorderLayout());
        this.body.add(new JScrollPane(this.formPanel));
        
        
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        //this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.getTitle().setText("Inscription des candidats");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(this.fenetre.getBarreDeMenu().etablissementListener);
        this.head.initComposant();
        
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
        
        TypeService typeService = new TypeService();
        
        List typeCandidats = typeService.getAll("Type candidat");
        for(Object typeCandidat : typeCandidats){
            this.typeCandidatureField.addItem(typeCandidat);
        }
        
        List typeInscs = typeService.getAll("Type insc.");
        for(Object typeInsc : typeInscs){
            this.typeInscriptionField.addItem(typeInsc);
        }

        List typeConc = typeService.getAll("Concours rata.");
        for(Object typeConco : typeConc){
            this.concoursRatacheField.addItem(typeConco);
        }
        
        SerieService serieService = new SerieService();
        List series = serieService.getAll("");
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);
        }
        
        PaysService paysService = new PaysService();
        List listePays = paysService.getAll("");
        
        for(Object pays : listePays){
            this.paysNaissanceField.addItem(pays);
        }
        //List listeNationalite = paysService.getAll("");
        for(Object pays : listePays){
            Pays natio = new Pays();
            natio.setPaysToString(false);
            natio.setId(((Pays)pays).getId());
            natio.setNationalite(((Pays)pays).getNationalite());
            this.nationaliteField.addItem(natio);
        }
    }
    
    public void resetForm(){
        this.iueField.setText(null);
        this.nomField.setText(null);
        this.prenomField.setText(null);
        this.telephoneField.setText(null);
        this.dateNaissanceField.setText(null);
        this.lieuNaissanceField.setText(null);
        this.numActeNaissanceField.setText(null);
        this.dernierDiplomeField.setText(null);
        this.anneeDernierDiplomeField.setText(null);
    }
    
    class SaveCandidatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CandidatService service;
            SessionService sessionService;
            try {
                sessionService = new SessionService();
                List sessions = sessionService.getAll("");
                service = new CandidatService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("session_id", ((Session)sessions.get(0)).getId().toString());
                service.getFormData().put("iue", iueField.getText());
                service.getFormData().put("nom", nomField.getText());
                service.getFormData().put("prenom", prenomField.getText());
                service.getFormData().put("sexe", sexeField.getSelectedItem().toString());
                service.getFormData().put("dateNaissance", dateNaissanceField.getDate());
                service.getFormData().put("lieuNaissance", lieuNaissanceField.getText());
                service.getFormData().put("numeroActeNaissance", numActeNaissanceField.getText());
                service.getFormData().put("telephone", telephoneField.getText());
                service.getFormData().put("secteur_id", ((Localite)secteurCandidatField.getSelectedItem()).getId().toString());
                service.getFormData().put("centreExamen_id", ((Localite)centreExamenField.getSelectedItem()).getId().toString());
                service.getFormData().put("etablissement_id", ((Structure)etablissementField.getSelectedItem()).getId().toString());
                service.getFormData().put("typeCandidat_id", ((Type)typeCandidatureField.getSelectedItem()).getId().toString());
                service.getFormData().put("typeInscription_id", ((Type)typeInscriptionField.getSelectedItem()).getId().toString());
                service.getFormData().put("concours_id", ((Type)concoursRatacheField.getSelectedItem()).getId().toString());
                service.getFormData().put("serie_id", ((Serie)serieField.getSelectedItem()).getId().toString());
                service.getFormData().put("paysNaissance_id", ((Pays)paysNaissanceField.getSelectedItem()).getId().toString());
                service.getFormData().put("nationalite_id", ((Pays)nationaliteField.getSelectedItem()).getId().toString());
                service.getFormData().put("dernierDiplome", dernierDiplomeField.getText());
                service.getFormData().put("anneeDernierDiplome", anneeDernierDiplomeField.getText());
                service.getFormData().put("sport", sportField.getSelectedItem());
                service.save();
                resetForm();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
