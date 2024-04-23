/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Type;
import bf.menapln.enumeration.Sexe;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.SerieSelectionTableModel;
import bf.menapln.service.CandidatPosteJuryService;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.LocaliteService;
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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author coulibaly
 */
public class CandidatPosteJuryForm extends Panneau{
    
    private ContainerObject formePanel;
    private ContainerObject line1;
    private ContainerObject line2;
    private ContainerObject localitePanel;
    private ContainerObject epreuveSeriePanel;
    private ContainerObject infoCandidatPanel;
    
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;
    
    private JLabel structureLabel;
    private JComboBox structureField;
    private ContainerObject structurePanel;
    
    private JLabel posteLabel;
    private JComboBox posteField;
    private ContainerObject postePanel;
    
    private JLabel epreuveLabel;
    private JComboBox epreuveField;
    private ContainerObject epreuvePanel;
    
    private ContainerObject seriePanel;
    private SerieSelectionTableModel serieModel;
    
    private JLabel nomLabel;
    private JTextField nomField;
    private ContainerObject nomPanel;
    
    private JLabel prenomLabel;
    private JTextField prenomField;
    private ContainerObject prenomPanel;
    
    private JLabel sexeLabel;
    private JComboBox sexeField;
    private ContainerObject sexePanel;
    
    private JLabel telephoneLabel;
    private JTextField telephoneField;
    private ContainerObject telephonePanel;
    
    private JLabel matriculeLabel;
    private JTextField matriculeField;
    private ContainerObject matriculePanel;
    
    private JLabel nipLabel;
    private JTextField nipField;
    private ContainerObject nipPanel;
    
    private JTable serieTableau;
    
    public CandidatPosteJuryForm(Fenetre fenetre) {
        super(fenetre);
        
        this.structureLabel = new JLabel("Structure");
        this.structureField = new JComboBox();
        this.structureField.setMinimumSize(new Dimension(200,30));
        this.structureField.setMaximumSize(new Dimension(300,30));
        this.structurePanel = new ContainerObject();
        this.structurePanel.setLayout(new BoxLayout(this.structurePanel,BoxLayout.Y_AXIS));
        this.structurePanel.add(this.structureLabel);
        this.structurePanel.add(this.structureField);
        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communeField.addActionListener(CommuneStateListener.etablissementField(this.structureField));
        this.communeField.setMinimumSize(new Dimension(200,30));
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
        
        this.localitePanel = new ContainerObject();
        this.localitePanel.setBorder(BorderFactory.createTitledBorder("Localité"));
        this.localitePanel.setLayout(new BoxLayout(this.localitePanel,BoxLayout.Y_AXIS));
        this.localitePanel.setMaximumSize(new Dimension(300,350));
        this.localitePanel.setAlignmentY(TOP_ALIGNMENT);
        this.localitePanel.add(this.regionPanel);
        this.localitePanel.add(this.provincePanel);
        this.localitePanel.add(this.communePanel);
        this.localitePanel.add(this.structurePanel);
        
        this.posteLabel = new JLabel("Poste*");
        this.posteField = new JComboBox();
        this.posteField.setPreferredSize(new Dimension(200,30));
        this.postePanel = new ContainerObject();
        this.postePanel.setLayout(new BoxLayout(this.postePanel,BoxLayout.Y_AXIS));
        this.postePanel.add(this.posteLabel);
        this.postePanel.add(this.posteField);
        
        this.epreuveLabel = new JLabel("Epreuve");
        this.epreuveField = new JComboBox();
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuvePanel = new ContainerObject();
        this.epreuvePanel.setLayout(new BoxLayout(this.epreuvePanel,BoxLayout.Y_AXIS));
        this.epreuvePanel.add(this.epreuveLabel);
        this.epreuvePanel.add(this.epreuveField);
        
        this.seriePanel = new ContainerObject();
        //this.seriePanel.setBorder(BorderFactory.createTitledBorder("tes"));
        this.seriePanel.setLayout(new BorderLayout());
        //this.seriePanel.setMaximumSize(new Dimension(220,50));
        
        
        this.epreuveSeriePanel = new ContainerObject();
        this.epreuveSeriePanel.setLayout(new BoxLayout(this.epreuveSeriePanel,BoxLayout.Y_AXIS));
        this.epreuveSeriePanel.setBorder(BorderFactory.createTitledBorder("Poste & Discipline"));
        this.epreuveSeriePanel.setMaximumSize(new Dimension(300,350));
        this.epreuveSeriePanel.setAlignmentY(TOP_ALIGNMENT);
        this.epreuveSeriePanel.add(this.postePanel);
        this.epreuveSeriePanel.add(this.epreuvePanel);
        this.epreuveSeriePanel.add(this.seriePanel);
        
        this.prenomLabel = new JLabel("Prénom*");
        this.prenomField = new JTextField();
        this.prenomField.setPreferredSize(new Dimension(200,30));
        //this
        this.prenomPanel = new ContainerObject();
        this.prenomPanel.setLayout(new BoxLayout(this.prenomPanel,BoxLayout.Y_AXIS));
        this.prenomPanel.add(this.prenomLabel);
        this.prenomPanel.add(this.prenomField);
        
        this.nomLabel = new JLabel("Nom*");
        this.nomField = new JTextField();
        this.nomField.setPreferredSize(new Dimension(200,30));
        this.nomPanel = new ContainerObject();
        this.nomPanel.setLayout(new BoxLayout(this.nomPanel,BoxLayout.Y_AXIS));
        this.nomPanel.add(this.nomLabel);
        this.nomPanel.add(this.nomField);
        
        this.sexeLabel = new JLabel("Sexe*");
        this.sexeField = new JComboBox(Sexe.values());
        this.sexeField.setPreferredSize(new Dimension(200,30));
        this.sexePanel = new ContainerObject();
        this.sexePanel.setLayout(new BoxLayout(this.sexePanel,BoxLayout.Y_AXIS));
        this.sexePanel.add(this.sexeLabel);
        this.sexePanel.add(this.sexeField);
        
        this.telephoneLabel = new JLabel("Tél.");
        this.telephoneField= new JTextField();
        this.telephoneField.setPreferredSize(new Dimension(200,30));
        this.telephonePanel = new ContainerObject();
        this.telephonePanel.setLayout(new BoxLayout(this.telephonePanel,BoxLayout.Y_AXIS));
        this.telephonePanel.add(this.telephoneLabel);
        this.telephonePanel.add(this.telephoneField);
        
        this.matriculeLabel = new JLabel("Matricule/CNIB*");
        this.matriculeField= new JTextField();
        this.matriculeField.setPreferredSize(new Dimension(200,30));
        this.matriculePanel = new ContainerObject();
        this.matriculePanel.setLayout(new BoxLayout(this.matriculePanel,BoxLayout.Y_AXIS));
        this.matriculePanel.add(this.matriculeLabel);
        this.matriculePanel.add(this.matriculeField);
        
      
    
        this.nipLabel = new JLabel("NIP.*");
        this.nipField= new JTextField();
        this.nipField.setPreferredSize(new Dimension(200,30));
        this.nipPanel = new ContainerObject();
        this.nipPanel.setLayout(new BoxLayout(this.nipPanel,BoxLayout.Y_AXIS));
        this.nipPanel.add(this.nipLabel);
        this.nipPanel.add(this.nipField);
        
        this.infoCandidatPanel = new ContainerObject();
        this.infoCandidatPanel.setBorder(BorderFactory.createTitledBorder("Information sur le candidat"));
        this.infoCandidatPanel.setLayout(new BoxLayout(this.infoCandidatPanel,BoxLayout.Y_AXIS));
        this.infoCandidatPanel.setMaximumSize(new Dimension(300,350));
        this.infoCandidatPanel.setAlignmentY(TOP_ALIGNMENT);
        this.infoCandidatPanel.add(this.matriculePanel);
        this.infoCandidatPanel.add(this.nipPanel);
        this.infoCandidatPanel.add(this.nomPanel);
        this.infoCandidatPanel.add(this.prenomPanel);
        this.infoCandidatPanel.add(this.sexePanel);
        this.infoCandidatPanel.add(this.telephonePanel);
        
        
        this.line1 = new ContainerObject();
        this.line1.setLayout(new BoxLayout(this.line1,BoxLayout.X_AXIS));
        //this.line1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.line1.add(this.localitePanel);
        this.line1.add(this.epreuveSeriePanel);
        this.line1.add(this.infoCandidatPanel);
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new CandidatPosteJurySaveListener());
        
        this.line2 = new ContainerObject();
        this.line2.add(this.save);
        
        this.formePanel = new ContainerObject();
        this.formePanel.setLayout(new BorderLayout());
        this.formePanel.add(this.line1,BorderLayout.CENTER);
        this.formePanel.add(this.line2,BorderLayout.SOUTH);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.formePanel);
                
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Liste des candidats aux postes de jury");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(this.fenetre.getBarreDeMenu().candidatPosteJuryListener);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
        TypeService tService = new TypeService();
        tService.setImplementation("Poste jury");
        List postes = tService.getAll("");
        this.posteField.addItem(new Type());
        for(Object poste : postes){
            this.posteField.addItem(poste);
        }
        
        EpreuveSerieService esService = new EpreuveSerieService();
        List epreuves = esService.getAllSerieByEpreuveNofr();
        this.epreuveField.addItem(new Epreuve());
        for(Object epreuve : epreuves){
            this.epreuveField.addItem(epreuve);
        }
        
        SerieService serieService = new SerieService();
        this.serieModel = new SerieSelectionTableModel(serieService.getAll(""),this.fenetre);
        serieTableau = new JTable(this.serieModel);
        serieTableau.getColumnModel().getColumn(0).setMaxWidth(20);
        serieTableau.setRowHeight(25);
        //serieTableau.setEnabled(false);
        this.seriePanel.add(new JScrollPane(serieTableau));
        
        this.epreuveField.addActionListener(new EpreuveStateListener());
        this.posteField.addActionListener(new PosteStateListener());
    
    }
    
    class EpreuveStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            serieModel.setModelData(((Epreuve)epreuveField.getSelectedItem()).getSeries());
            serieModel.fireTableDataChanged();
        }
        
    }
    
    class PosteStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!((Type)posteField.getSelectedItem()).getLibelle().equals("Correcteur")){
                epreuveField.setEnabled(false);
                serieTableau.setEnabled(false);
                epreuveField.setSelectedIndex(0);
            }else{
                epreuveField.setEnabled(true);
                serieTableau.setEnabled(true);
            }
        }
        
    }
    
    class CandidatPosteJurySaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {            
            try {
                service = new CandidatPosteJuryService();
                service.setFormData(new HashMap());
                service.getFormData().put("session", fenetre.getUserSession().getSession());
                service.getFormData().put("localite", communeField.getSelectedItem());
                service.getFormData().put("structure", structureField.getSelectedItem());
                service.getFormData().put("poste", posteField.getSelectedItem());
                service.getFormData().put("epreuve", epreuveField.getSelectedItem());
                service.getFormData().put("matricule", matriculeField.getText());
                service.getFormData().put("nip", nipField.getText());
                service.getFormData().put("nom", nomField.getText());
                service.getFormData().put("prenom", prenomField.getText());
                service.getFormData().put("sexe", sexeField.getSelectedItem());
                service.getFormData().put("telephone", telephoneField.getText());
                service.save();
                    JOptionPane.showMessageDialog(fenetre,"Enregistré avec succès");
                matriculeField.setText("");
                nipField.setText("");
                nomField.setText("");
                prenomField.setText("");
                telephoneField.setText("");
               
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(CandidatPosteJuryForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
