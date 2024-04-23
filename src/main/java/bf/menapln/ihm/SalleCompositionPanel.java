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
import bf.menapln.entity.Objet;
import bf.menapln.entity.SalleComposition;
import bf.menapln.ihm.tableModel.JuryTableModel;
import bf.menapln.ihm.tableModel.SalleCompositionCandidatTableModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CentreService;
import bf.menapln.service.JuryService;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.SalleCompositionService;
import bf.menapln.service.Service;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author coulibaly
 */
public class SalleCompositionPanel extends Panneau{

    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel capaciteSalleLabel;
    private JTextField capaciteSalleField;
    private ContainerObject capaciteSallePanel;

    private JLabel resultatLabel;
    private JTextField resultatField;
    private ContainerObject resultatPanel;

    private JLabel candidatRestLabel;
    private JTextField candidatRestField;
    private ContainerObject candidatRestPanel;
    
    private JLabel decision1Label;
    private JRadioButton decision1Field;

    private JRadioButton decision2Field;

    private JRadioButton decision3Field;

    private JTextField juryCapaciteField;
    
    private final ContainerObject formPanel;
    private ButtonGroup group;
    private final ContainerObject resultPanel;
    private final ContainerObject decisonPanel;
    private final ContainerObject finalPanel;

    private final ContainerObject listePanel;
    private final JSplitPane bodyPanel;

    private int reste;
    private int nombreSalle;
    ArrayList<SalleComposition> salleAlls = new ArrayList<SalleComposition>();
    public SalleCompositionPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        this.group = new ButtonGroup();


        this.capaciteSalleLabel = new JLabel("Nombre Candidat Par Salle");
        this.capaciteSalleField = new JTextField();
        this.capaciteSalleField.getDocument().addDocumentListener(new capaciteListener());
        this.capaciteSalleField.setPreferredSize(new Dimension(200,30));
        this.capaciteSalleField.setMinimumSize(new Dimension(200,30));
        this.capaciteSalleField.setMaximumSize(new Dimension(400,30));
        
        this.capaciteSallePanel = new ContainerObject();
        this.capaciteSallePanel.setLayout(new BoxLayout(this.capaciteSallePanel,BoxLayout.Y_AXIS));
        this.capaciteSallePanel.add(this.capaciteSalleLabel);
        this.capaciteSallePanel.add(this.capaciteSalleField);
        
        this.save.addActionListener(new SalleSaveListener());


        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.setBorder(BorderFactory.createTitledBorder("Nombre Candidat Par Salle"));
        ((TitledBorder)this.formPanel.getBorder()).setTitleColor(Color.blue);
        this.formPanel.add(this.capaciteSallePanel);

        this.resultatLabel = new JLabel("Nombre de Salle");
        this.resultatField = new JTextField();
        this.resultatField.setEditable(false);
        this.resultatField.setPreferredSize(new Dimension(200,30));
        this.resultatField.setMinimumSize(new Dimension(200,30));
        this.resultatField.setMaximumSize(new Dimension(400,30));
        
        this.resultatPanel = new ContainerObject();
        this.resultatPanel.setLayout(new BoxLayout(this.resultatPanel,BoxLayout.Y_AXIS));
        this.resultatPanel.add(this.resultatLabel);
        this.resultatPanel.add(this.resultatField);

        this.candidatRestLabel = new JLabel("Reste de Candidat");
        this.candidatRestField = new JTextField();
        this.candidatRestField.setEditable(false);
        this.candidatRestField.setPreferredSize(new Dimension(200,30));
        this.candidatRestField.setMinimumSize(new Dimension(200,30));
        this.candidatRestField.setMaximumSize(new Dimension(400,30));
        
        this.candidatRestPanel = new ContainerObject();
        this.candidatRestPanel.setLayout(new BoxLayout(this.candidatRestPanel,BoxLayout.Y_AXIS));
        this.candidatRestPanel.add(this.candidatRestLabel);
        this.candidatRestPanel.add(this.candidatRestField);

        this.resultPanel = new ContainerObject();
        this.resultPanel.setLayout(new BoxLayout(this.resultPanel,BoxLayout.Y_AXIS));
        this.resultPanel.setBorder(BorderFactory.createTitledBorder("Resultat du Calcul"));
        ((TitledBorder)this.resultPanel.getBorder()).setTitleColor(Color.blue);
        this.resultPanel.add(this.resultatPanel);
        this.resultPanel.add(this.candidatRestPanel);

        this.decision1Field = new JRadioButton("Créer une salle et refaire une repartion équitable");
        this.decision1Field.setEnabled(false);
        this.decision1Field.addActionListener(new ActionDecison1());
        this.decision1Field.setPreferredSize(new Dimension(200,30));
        this.decision1Field.setMinimumSize(new Dimension(200,30));
        this.decision1Field.setMaximumSize(new Dimension(400,30));
        

        this.decision2Field = new JRadioButton("Répartir le reste des candidat dans les autres salles");
        this.decision2Field.setEnabled(false);
        this.decision2Field.addActionListener(new ActionDecison2());
        this.decision2Field.setPreferredSize(new Dimension(200,30));
        this.decision2Field.setMinimumSize(new Dimension(200,30));
        this.decision2Field.setMaximumSize(new Dimension(400,30));
        
        this.decision3Field = new JRadioButton("Créer une salle juste pour le reste des candidats");
        this.decision3Field.setEnabled(false);
        this.decision3Field.addActionListener(new ActionDecison3());
        this.decision3Field.setPreferredSize(new Dimension(200,30));
        this.decision3Field.setMinimumSize(new Dimension(200,30));
        this.decision3Field.setMaximumSize(new Dimension(400,30));
        
        group.add(this.decision1Field);
        group.add(this.decision2Field);
        group.add(this.decision3Field);

        this.decisonPanel = new ContainerObject();
        this.decisonPanel.setLayout(new BoxLayout(this.decisonPanel,BoxLayout.Y_AXIS));
        this.decisonPanel.setBorder(BorderFactory.createTitledBorder("Répartition Candidats restants"));
      ((TitledBorder)this.decisonPanel.getBorder()).setTitleColor(Color.blue);
        this.decisonPanel.add(this.decision1Field);
        this.decisonPanel.add(this.decision2Field);
        this.decisonPanel.add(this.decision3Field);
        this.decisonPanel.add(this.formControlPanel);

        
        this.finalPanel = new ContainerObject();
        this.finalPanel.setLayout(new BoxLayout(this.finalPanel,BoxLayout.Y_AXIS));
        this.finalPanel.setBorder(BorderFactory.createTitledBorder("Gestion Salle"));
        ((TitledBorder)this.finalPanel.getBorder()).setTitleColor(Color.blue);
        this.finalPanel.add(this.formPanel);
        this.finalPanel.add(this.resultPanel);
        this.finalPanel.add(this.decisonPanel);
        //this.finalPanel.add(this.formControlPanel);

        

        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.finalPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.save.setEnabled(false);
        this.service = new CandidatJuryService();
        this.tableModel = new SalleCompositionCandidatTableModel(((CandidatJuryService)this.service).getCandidatJury(this.fenetre.getUserSession().getJury()),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        this.listePanel.add(new JScrollPane(tableau));
        
    }

    class capaciteListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            Integer nombreCandidat = tableModel.getRowCount();
            Integer nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
            if(nombreCandidat > 0 && nombreCandidatSalle > 0){
                reste = nombreCandidat % nombreCandidatSalle;
                nombreSalle = (nombreCandidat - reste)/nombreCandidatSalle;
                resultatField.setText(""+nombreSalle);
                candidatRestField.setText(""+reste);
                if(reste == 0){
                    try {
                    decision1Field.setEnabled(false);
                    decision2Field.setEnabled(false);
                    decision3Field.setEnabled(false);
                        DecisionFunction(nombreCandidatSalle, nombreSalle, reste);
                    } catch (EmptyDataException e1) {
                        e1.printStackTrace();
                    }
                    save.setEnabled(true);
                }else{
                    decision1Field.setEnabled(true);
                    decision2Field.setEnabled(true);
                    decision3Field.setEnabled(true);
                }

            }
            
        }
        

        @Override
        public void removeUpdate(DocumentEvent e) {
            Integer nombreCandidat = tableModel.getRowCount();
            Integer nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
            if(nombreCandidat > 0 && nombreCandidatSalle > 0){
                reste = nombreCandidat % nombreCandidatSalle;
                nombreSalle = (nombreCandidat - reste)/nombreCandidatSalle;
                resultatField.setText(""+nombreSalle);
                candidatRestField.setText(""+reste);
                if(reste == 0){
                    try {
                    decision1Field.setEnabled(false);
                    decision2Field.setEnabled(false);
                    decision3Field.setEnabled(false);
                        DecisionFunction(nombreCandidatSalle, nombreSalle, reste);
                    } catch (EmptyDataException e1) {
                        e1.printStackTrace();
                    }
                    save.setEnabled(true);
                }else{
                    decision1Field.setEnabled(true);
                    decision2Field.setEnabled(true);
                    decision3Field.setEnabled(true);
                }

            }


        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            Integer nombreCandidat = tableModel.getRowCount();
            Integer nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
            if(nombreCandidat > 0 && nombreCandidatSalle > 0){
                reste = nombreCandidat % nombreCandidatSalle;
                nombreSalle = (nombreCandidat - reste)/nombreCandidatSalle;
                resultatField.setText(""+nombreSalle);
                candidatRestField.setText(""+reste);
                if(reste == 0){
                    try {
                    decision1Field.setEnabled(false);
                    decision2Field.setEnabled(false);
                    decision3Field.setEnabled(false);
                        DecisionFunction(nombreCandidatSalle, nombreSalle, reste);
                    } catch (EmptyDataException e1) {
                        e1.printStackTrace();
                    }
                    save.setEnabled(true);
                }else{
                    decision1Field.setEnabled(true);
                    decision2Field.setEnabled(true);
                    decision3Field.setEnabled(true);
                }

            }

        }

    }

    public void DecisionFunction(int nombreCSalle,int nombreSalle,int reste) throws EmptyDataException{
        try {
            SalleComposition salleTMPs = new SalleComposition();
            for(int i=0; i < nombreSalle; i++){
                    salleTMPs.setJury(fenetre.getUserSession().getJury());
                    salleTMPs.setSession(fenetre.getUserSession().getSession());
                    salleTMPs.setTourComposition(fenetre.getUserSession().getTourComposition());
                if(i<reste){
                    salleTMPs.setCapacite(nombreCSalle + 1);
                }else{
                    salleTMPs.setCapacite(nombreCSalle);
                }
                    salleAlls.add(i,salleTMPs);
                    System.out.println(salleAlls.get(i).getLibelleSalle());
            }
           // }
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    class ActionDecison1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                Integer nombreCandidat = tableModel.getRowCount();
                int nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
                int rest;
                rest = nombreCandidat % (nombreSalle+1);
                nombreCandidatSalle = (nombreCandidat - rest) / (nombreSalle+1);
                reste = nombreCandidat % (nombreSalle+1);
                try {
                    DecisionFunction(nombreCandidatSalle,nombreSalle+1,rest);
                } catch (EmptyDataException | NullPointerException e1) {
                    e1.printStackTrace();
                }
                capaciteSalleField.setEditable(false);
                resultatField.setText("");
                candidatRestField.setText("");
                save.setEnabled(true);

    
    }
}

    class ActionDecison2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Integer nombreCandidat = tableModel.getRowCount();
            Integer nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
            int rest;
            rest = nombreCandidat % (nombreSalle);
            nombreCandidatSalle = (nombreCandidat - rest) / (nombreSalle);
            reste = nombreCandidat % (nombreSalle);
            try {
                DecisionFunction(nombreCandidatSalle,nombreSalle,rest);
            } catch (EmptyDataException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            capaciteSalleField.setEditable(false);
            resultatField.setText("");
            candidatRestField.setText("");
            decision1Field.setEnabled(false);
            decision2Field.setEnabled(false);
            decision3Field.setEnabled(false);
            save.setEnabled(true);

        }
    
    }

    class ActionDecison3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Integer nombreCandidat = tableModel.getRowCount();
            Integer nombreCandidatSalle = Integer.parseInt(capaciteSalleField.getText());
            int rest = 0;
            int nb;
            if(nombreSalle <= 1){
                rest = nombreCandidat - nombreCandidatSalle;
            }else{
                rest = nombreCandidat % nombreSalle;
            }
            nb = (nombreCandidat - rest) / (nombreSalle);
            try {
                System.out.println(rest);
                DecisionFunction(nb,nombreSalle + 1,0);
            } catch (EmptyDataException e1) {
                e1.printStackTrace();
            }

            capaciteSalleField.setEditable(false);
            resultatField.setText("");
            candidatRestField.setText("");
            decision1Field.setEnabled(false);
            decision2Field.setEnabled(false);
            decision3Field.setEnabled(false);
            save.setEnabled(true);


          }
    
    }
    
    class SalleSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                SalleCompositionService serviceSalle = new SalleCompositionService();
                serviceSalle.setFormData(new HashMap<String,String>());
                     for(int i=0; i < salleAlls.size(); i++){
                        serviceSalle.getFormData().put("session_id", salleAlls.get(i).getSession());
                        serviceSalle.getFormData().put("jury", salleAlls.get(i).getJury());
                        serviceSalle.getFormData().put("tourComposition_id",salleAlls.get(i).getTourComposition());
                        serviceSalle.getFormData().put("libelleSalle", salleAlls.get(i).getLibelleSalle());
                        serviceSalle.getFormData().put("capacite", salleAlls.get(i).getCapacite());
                        serviceSalle.save();
                      }
                    } catch (SQLException  | NullPointerException | EmptyDataException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
