/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.AnonymatPVPDF;
import bf.menapln.Reporting.PDF;
import bf.menapln.actionListener.SerieStateListener;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.AnonymatTableModel;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.service.EpreuveService;
import bf.menapln.service.FeuilleCompositionService;
import bf.menapln.service.SerieService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.layout.element.Paragraph;

/**
 *
 * @author coulibaly
 */
public class AnonymatPanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private ContainerObject seriePanel;
    private ContainerObject epreuvePanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private JComboBox epreuveField;
    private JLabel epreuveeLabel;
    
    private Bouton genererAnonymat;
    private Bouton print;
    private Panneau container;
    
    private ObjetModel model;
    public AnonymatPanel(Panneau container) throws SQLException{        
        this.container = container;
        
        this.epreuveeLabel = new JLabel("Epreuve");
        this.epreuveField = new JComboBox();
        this.epreuveField.addActionListener(this);
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuveField.setMaximumSize(new Dimension(400,30));
        
        this.epreuvePanel = new ContainerObject();
        this.epreuvePanel.setLayout(new BoxLayout(this.epreuvePanel,BoxLayout.Y_AXIS));
        this.epreuvePanel.add(this.epreuveeLabel);
        this.epreuvePanel.add(this.epreuveField);
        
/*         this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.addActionListener(SerieStateListener.epreuveCopies(this.epreuveField,this.container.getFenetre()));
        this.serieField.addActionListener(this);
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(400,30));
        
        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField); */
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.epreuvePanel);
        


        EpreuveService eService = new EpreuveService();
        List epreuves = null;
        epreuves = eService.getEpreuveCopiesAllSansOrale(this.container.fenetre.getUserSession().getSession(), this.container.fenetre.getUserSession().getJury(), this.container.fenetre.getUserSession().getTourComposition());
        this.epreuveField.addItem(new Epreuve());
        for(Object epreuve : epreuves){
            this.epreuveField.addItem(epreuve);
        }
        

        this.container.service = new FeuilleCompositionService();
        this.model = new AnonymatTableModel(new ArrayList(),this.container.getFenetre());
        
        JTable tableau = new JTable(this.model);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        

        this.genererAnonymat = new Bouton("Générer");
        this.genererAnonymat.addActionListener(new GenererAnonymatListener());
        this.container.save = new Bouton("Enregistrer");
        this.container.save.addActionListener(new SaveAnonymatListener());
        this.print = new Bouton("Aperçu av. imp.");
        this.print.addActionListener(new PrintAnonymatPVListener());
        
        this.container.formControlPanel = new ContainerObject();
        this.container.formControlPanel.add(this.genererAnonymat);
        this.container.formControlPanel.add(this.container.save);
        this.container.formControlPanel.add(this.print);

        
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(this.epreuveField.getSelectedItem() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            List data;
            try {
               // data = ((FeuilleCompositionService)this.container.service).getFeuille(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(), (Epreuve)epreuveField.getSelectedItem(), us.getJury().getEtape());
                data = ((FeuilleCompositionService)this.container.service).getFeuilleWithoutSerie(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(), us.getJury().getEtape());
                
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
                if(((FeuilleComposition)data.get(0)).isAnonymous()){
                    this.container.save.setEnabled(true);
                }else{
                    this.container.save.setEnabled(false);
                    this.print.setEnabled(false);
                }
                
                if(((FeuilleComposition)data.get(0)).getCycle() >= 2){
                    this.container.save.setEnabled(false);
                    this.genererAnonymat.setEnabled(false);
                    this.print.setEnabled(true);
                }else{
                    this.genererAnonymat.setEnabled(true);
                }

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
/* 
    class FiltreStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            UserSession us = container.getFenetre().getUserSession();
                List data;
                try {
                    data = ((FeuilleCompositionService)container.service).getFeuilleWithoutSerie(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(), us.getJury().getEtape());
                    model.setModelData(data);
                    model.fireTableDataChanged();
                } catch (SQLException ex) {
                    Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    } */

    class GenererAnonymatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            List anonymats = container.service.random(model.getRowCount());
            int row = 0;
            for(Objet objet : model.getModelData()){
                FeuilleComposition feuille = (FeuilleComposition)objet;
                feuille.setAnonymat(anonymats.get(row).toString());
                feuille.setAnonymous(true);
                row++;
            }
            model.fireTableDataChanged();
            container.save.setEnabled(true);
        }
        
    }

    class MatiereCopieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            List anonymats = container.service.random(model.getRowCount());
            System.out.println(anonymats);
            int row = 0;
            for(Objet objet : model.getModelData()){
                FeuilleComposition feuille = (FeuilleComposition)objet;
                feuille.setAnonymat(anonymats.get(row).toString());
                row++;
            }
            model.fireTableDataChanged();
        }
        
    }

    
    class SaveAnonymatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                FeuilleCompositionService fcService = new FeuilleCompositionService();
                for(Objet objet : model.getModelData()){
                    FeuilleComposition feuille = (FeuilleComposition)objet;
                    if(!feuille.getAnonymat().isEmpty()){
                        fcService.upadteAnonymat(feuille);
                    }
                    //System.out.println(feuille.getComposition().getTourComposition().getId());
                }
                model.fireTableDataChanged();
                container.save.setEnabled(false);
                genererAnonymat.setEnabled(false);
                print.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    class PrintAnonymatPVListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            AnonymatPVPDF anonymat = new AnonymatPVPDF(container.fenetre);
            
                anonymat.setPath("anonymatPV_"+epreuveField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                anonymat.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                anonymat.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n ------- \n Secrétariat Général \n ------- \n Direction Générale des Examens et Concours (DGEC)"));
                anonymat.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Anonymat "+
                epreuveField.getSelectedItem().toString().toLowerCase()+" "+container.fenetre.getUserSession().getJury().getEtape()));
                anonymat.setTableHeaderData(new String[]{"PV","Anonymat"});
                anonymat.setListeCandidat(model.getModelData());
            try {
                anonymat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                SaisiePanel p = new SaisiePanel(container.fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(anonymat));
                //p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        
    }
}
