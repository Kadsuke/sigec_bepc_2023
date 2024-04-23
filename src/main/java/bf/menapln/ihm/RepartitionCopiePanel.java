/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.AnonymatPDF;
import bf.menapln.Reporting.PDF;
import bf.menapln.Reporting.TestPDF;
import bf.menapln.actionListener.EpreuveStateListener;
import bf.menapln.actionListener.SerieStateListener;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.tableModel.RepartitionCopieTableModel;
import bf.menapln.service.EpreuveService;
import bf.menapln.service.FeuilleCompositionService;
import bf.menapln.service.SerieService;
import com.itextpdf.layout.element.Paragraph;
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

/**
 *
 * @author coulibaly
 */
public class RepartitionCopiePanel extends ContainerObject implements ActionListener{
    
    private Panneau container;
    
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private ContainerObject seriePanel;
    private JLabel serieLabel;
    private JComboBox serieField;
    
    private ContainerObject epreuvePanel;
    private JComboBox epreuveField;
    private JLabel epreuveeLabel;
    
    private ContainerObject correcteurPanel;
    private JComboBox correcteurField;
    private JLabel correcteurLabel;
    
    private Bouton genererRepartition;
    private Bouton print;
    
    
    private ObjetModel model;
    public RepartitionCopiePanel(Panneau container) throws SQLException{
        this.container = container;
        
        this.correcteurLabel = new JLabel("Correcteurs");
        this.correcteurField = new JComboBox();
        this.correcteurField.setPreferredSize(new Dimension(200,30));
        this.correcteurField.setMaximumSize(new Dimension(400,30));
        
        this.correcteurPanel = new ContainerObject();
        this.correcteurPanel.setLayout(new BoxLayout(this.correcteurPanel,BoxLayout.Y_AXIS));
        this.correcteurPanel.add(this.correcteurLabel);
        this.correcteurPanel.add(this.correcteurField);
        
        this.epreuveeLabel = new JLabel("Epreuve");
        this.epreuveField = new JComboBox();
        this.epreuveField.addActionListener(this);
        this.epreuveField.addActionListener(new EpreuveStateListener(this.correcteurField,this.serieField,this.container.fenetre));
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuveField.setMaximumSize(new Dimension(400,30));
        
        this.epreuvePanel = new ContainerObject();
        this.epreuvePanel.setLayout(new BoxLayout(this.epreuvePanel,BoxLayout.Y_AXIS));
        this.epreuvePanel.add(this.epreuveeLabel);
        this.epreuvePanel.add(this.epreuveField);
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.addActionListener(SerieStateListener.epreuveAnonyme(this.epreuveField,this.container.getFenetre()));
        this.serieField.addActionListener(this);
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(400,30));
        
        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);

        this.correcteurField.addActionListener(new AnonymatByCorrecteurFilter());
                
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.epreuvePanel);
        this.filtrePanel.add(this.correcteurPanel);
        

        EpreuveService eService = new EpreuveService();
        List epreuves = null;
        epreuves = eService.getEpreuveCopiesAllSansSport(this.container.fenetre.getUserSession().getSession(), this.container.fenetre.getUserSession().getJury(), this.container.fenetre.getUserSession().getTourComposition());
        this.epreuveField.addItem(new Epreuve());
        for(Object epreuve : epreuves){
            this.epreuveField.addItem(epreuve);
        }
        
        
        this.container.service = new FeuilleCompositionService();
        this.model = new RepartitionCopieTableModel(new ArrayList(),this.container.getFenetre());
        
        JTable tableau = new JTable(this.model);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.genererRepartition = new Bouton("Repartir les copies");
        this.genererRepartition.addActionListener(new GenererRepartitionListener());
        this.container.save = new Bouton("Enregistrer");
        this.container.save.addActionListener(new SaveRepartitionListener());
        
        this.print = new Bouton("Aperçu Av. imp.");
        this.print.addActionListener(new PrintAnonymatListener());
        
        this.container.formControlPanel = new ContainerObject();
        this.container.formControlPanel.add(this.genererRepartition);
        this.container.formControlPanel.add(this.container.save);
        this.container.formControlPanel.add(this.print);
        
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
        
        this.container.body.add(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(this.epreuveField.getSelectedItem() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            List data;
            /* try {
                data = ((FeuilleCompositionService)this.container.service).getFeuilleWithoutSerie(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(), us.getTourComposition());
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NullPointerException e){

            } */
            try {
                //data = ((FeuilleCompositionService)this.container.service).getFeuille(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(), (Epreuve)epreuveField.getSelectedItem(), us.getTourComposition());
                data = ((FeuilleCompositionService)this.container.service).getFeuilleWithoutSerie(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(), us.getTourComposition());
                
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
                if(!data.isEmpty()){
                    if(((FeuilleComposition)data.get(0)).isReparti()){
                        this.container.save.setEnabled(true);
                    }else{
                        this.container.save.setEnabled(false);
                        this.print.setEnabled(false);
                    }
                
                    if(((FeuilleComposition)data.get(0)).getCycle() >= 3){
                        this.container.save.setEnabled(false);
                        this.genererRepartition.setEnabled(false);
                        this.print.setEnabled(true);
                    }else{
                        this.genererRepartition.setEnabled(true);
                    }
                }

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class GenererRepartitionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            container.service.repartirCopie(model, correcteurField);
            genererRepartition.setEnabled(false);
            container.save.setEnabled(true);
        }
        
    }
    
    class SaveRepartitionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            for(Objet feuille : model.getModelData()){
                try {
                    ((FeuilleCompositionService)container.service).upadteCorrecteur(feuille);
                } catch (SQLException ex) {
                    Logger.getLogger(RepartitionCopiePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            model.fireTableDataChanged();
            container.save.setEnabled(false);
            print.setEnabled(true);
        }
        
    }
    
    class AnonymatByCorrecteurFilter implements ActionListener{

       /*  @Override
        public void actionPerformed(ActionEvent ae) {

            Correcteur correcteur = (Correcteur)correcteurField.getSelectedItem();
            System.out.println("Correct Field "+correcteurField.getSelectedItem());
            if(correcteur != null){
                List<Objet> data = new ArrayList<Objet>();
                if(data != null && model != null){
                    for(Objet copie : model.getModelData()){
                        if(((FeuilleComposition)copie).getCorrecteur().getMatricule().equals(correcteur.getPersonnel().getMatricule())){
                            data.add(copie);
                        }
                    }
                    model.setModelData(data);
                    model.fireTableDataChanged();
                }
               
            }
        } */

        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Correcteur correcteur = (Correcteur)correcteurField.getSelectedItem();
            if(correcteur != null && correcteur.getPersonnel() != null){
                List<Objet> data = new ArrayList<Objet>();
                for(Objet copie : model.getModelData()){
                    if(((FeuilleComposition)copie).getCorrecteur().getMatricule().equals(correcteur.getPersonnel().getMatricule())){
                        data.add(copie);
                    }
                }
                model.setModelData(data);
                model.fireTableDataChanged();
            }
        }
        
    }
    
    /* class PrintAnonymatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            AnonymatPDF anonymat = new AnonymatPDF(container.fenetre);
            try {
                anonymat.setPath("anonymat_"+epreuveField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                anonymat.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                anonymat.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n ------- \n Secrétariat Général \n ------- \n Direction Générale des Examens et Concours (DGEC)"));
                anonymat.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Anonymat "+
                epreuveField.getSelectedItem().toString().toLowerCase()+" "+container.fenetre.getUserSession().getJury().getEtape()+"\n Correcteur "+" : "+correcteurField.getSelectedItem().toString().toLowerCase()));
                anonymat.setTableHeaderData(new String[]{"Anonymat","Note / 20"});
                anonymat.setListeCandidat(model.getModelData());
                anonymat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                SaisiePanel p = new SaisiePanel(container.fenetre);
                //PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(anonymat));
                p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                //FileInfo file = new FileInfo("header.pdf");
                //file.Directory.Create();

                new TestPDF().ManipulatePdf("header.pdf");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RepartitionCopiePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    } */

    class PrintAnonymatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Correcteur correcteur = (Correcteur)correcteurField.getSelectedItem();
            if(correcteur != null && correcteur.getPersonnel() != null){
            
                AnonymatPDF anonymat = new AnonymatPDF(container.fenetre);
                try {
                    anonymat.setPath("anonymat_"+epreuveField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                    anonymat.getSignataire()[0] = correcteur.toString();
                    anonymat.getFonction()[0] = "Le correcteur";
                    
                    //anonymat.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                    //anonymat.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n ------- \n Secrétariat Général \n ------- \n Direction Générale des Examens et Concours (DGEC)"));
                    anonymat.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Anonymat "+
                    ((Epreuve)epreuveField.getSelectedItem()).getEpreuveLibelle().toLowerCase()+" "+container.fenetre.getUserSession().getJury().getEtape()+"\n Correcteur "+" : "+correcteurField.getSelectedItem().toString().toLowerCase()+"\nTotal Copie : "+model.getRowCount()));
                    anonymat.setTableHeaderData(new String[]{"Anonymat/PV","Note / 20"});
                    anonymat.setListeCandidat(model.getModelData());
                    
                    anonymat.generatePDF();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    SaisiePanel p = new SaisiePanel(container.fenetre);
                    //PVPanel bodyPanel = new PVPanel(p);
                    p.body.setLayout(new BorderLayout());
                    p.body.add(PDF.pdfViewer(anonymat));
                    p.initComposant();
                    container.fenetre.setContainer(p);
                    container.fenetre.initComponent();
                } catch (SQLException ex) {
                    Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
