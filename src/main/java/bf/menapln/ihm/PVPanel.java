/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.PDF;
import bf.menapln.Reporting.PvDeliberation;
import bf.menapln.Reporting.RelevePDF;
import bf.menapln.entity.Candidat;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Type;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.tableModel.PVTableModel;
import bf.menapln.service.ConcoursRatacheService;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.FeuilleCompositionService;
import bf.menapln.service.ResultatService;
import bf.menapln.service.SerieService;
import bf.menapln.service.Service;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class PVPanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;

    private JLabel concoursLabel;
    private JComboBox concoursField;
    private ContainerObject councoursPanel;
    
    private Bouton print;
    private Bouton printReleve;

    
    
    
    
    private Panneau container;
    
    private ObjetModel model;
    private JTable tableau;
    public PVPanel(Panneau container) throws SQLException{
        this.container = container;
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        //this.serieField.addActionListener(this);
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(400,30));

        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);

        this.concoursLabel = new JLabel("Concours");
        this.concoursField = new JComboBox();
        this.concoursField.addActionListener(this);
        this.concoursField.setPreferredSize(new Dimension(200,30));
        this.concoursField.setMaximumSize(new Dimension(400,30));
        
        this.councoursPanel = new ContainerObject();
        this.councoursPanel.setLayout(new BoxLayout(this.councoursPanel,BoxLayout.Y_AXIS));
        this.councoursPanel.add(this.concoursLabel);
        this.councoursPanel.add(this.concoursField);
        
        this.print = new Bouton("Imprimer");
        this.print.addActionListener(new PrintPVListener());

        this.printReleve = new Bouton("Imprimer relevés");
        this.printReleve.addActionListener(new PrintRelleveListener());
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.seriePanel);
        this.filtrePanel.add(this.councoursPanel);
        this.filtrePanel.add(this.print);
        
        SerieService seriService = new SerieService();
        List series = seriService.getSerie(this.container.fenetre.getUserSession().getJury());
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);
        }

        ConcoursRatacheService concoursRatacheService = new ConcoursRatacheService();
        List concoursrataches = concoursRatacheService.getAll("ratache");
        this.concoursField.addItem(new Type());
        for(Object concour : concoursrataches){
            this.concoursField.addItem(concour);
    }
        this.container.service = new FeuilleCompositionService();
        this.model = new PVTableModel(new ArrayList(),this.container.getFenetre());
        
        tableau = new JTable(this.model);
        tableau.setRowHeight(25);
      
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.container.save = new Bouton("Valider le PV");
        this.container.save.addActionListener(new ValidatePVListener());
        
        this.container.formControlPanel = new ContainerObject();
        this.container.formControlPanel.add(this.container.save);
        this.container.formControlPanel.add(this.print);
        this.container.formControlPanel.add(this.printReleve);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Serie s = (Serie)(serieField.getSelectedItem());
        Type c = (Type)((JComboBox)ae.getSource()).getSelectedItem();
        s.setTotalCoef(0);
        
        if(s.getId() != null && c.getId() !=null){
            UserSession us = this.container.getFenetre().getUserSession();
            
            EpreuveSerieService serieService;
            try {
                serieService = new EpreuveSerieService();
                s = (Serie)serieService.getByIdTour(s.getId(),us.getJury());
               
                this.model.setModelTitle(serieService.setColonnePVProvisoire(this.tableau, s.getEpreuves()));
                
               /// List data = ((FeuilleCompositionService)this.container.service).getFeuilleByConcoursRatache(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(),(Type)concoursField.getSelectedItem(), us.getJury().getEtape());
               List data = new ArrayList();
               if(us.getJury().getEtape().getId()==1){
                data = ((FeuilleCompositionService)this.container.service).getAllByFeuillePremier(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(),(Type)concoursField.getSelectedItem(), us.getJury().getEtape());
            }else if(us.getJury().getEtape().getId()==2){
                data = ((FeuilleCompositionService)this.container.service).getAllByFeuilleDeuxieme(us.getSession(), us.getJury(), (Serie)serieField.getSelectedItem(),(Type)concoursField.getSelectedItem(), us.getJury().getEtape());
            }
              
               this.model.setModelData(data);
                this.model.fireTableDataChanged();
                
                Service.setPVPColWidth(this.tableau);
                
            } catch (SQLException ex) {
                Logger.getLogger(PVPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } 
    
    class ValidatePVListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean isRachat = false;
            for(Objet candidat : model.getModelData()){
                if(((Candidat)candidat).decisionJury() == "Rachat"){
                    isRachat = true;
                }
            }
            if(!isRachat){
                for(Objet candidat : model.getModelData()){
                    try {
                        ResultatService rService; rService = new ResultatService();
                        rService.setFormData(new HashMap());
                        rService.getFormData().put("candidat", candidat);
                        rService.save();
                         for(Objet feuille : ((Candidat)candidat).getCopies()){
                            ((FeuilleComposition)feuille).setVerouille(true);
                            try {
                                ((FeuilleCompositionService)container.service).verouiller((FeuilleComposition)feuille);
                            } catch (SQLException ex) {
                                Logger.getLogger(PVPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (SQLException | EmptyDataException ex) {
                        Logger.getLogger(PVPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                model.fireTableDataChanged();
            }else{
                
            }
        }
        
    }

    class PrintPVListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            PvDeliberation pv = new PvDeliberation(container.fenetre);
            pv.setPath("pv_deliberation_"+serieField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
            pv.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury()+" : Option "+serieField.getSelectedItem()+" : Concours "+concoursField.getSelectedItem()+"\n PROCES VERBAL DE DELIBERATION DU "+
            " "+container.fenetre.getUserSession().getJury().getEtape()));
            pv.setTableHeaderData(model.getModelTitle());
            pv.setListeCandidat(model.getModelData());
            try {
                pv.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PvDeliberation.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                SaisiePanel p = new SaisiePanel(container.fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(pv));
                p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        
    }

    class PrintRelleveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            RelevePDF resultat = new RelevePDF(container.fenetre);
            try {
                resultat.setPath("releves_"+serieField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                resultat.setListeCandidat(model.getModelData());
                resultat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                DeliberationPanel p = new DeliberationPanel(container.fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(resultat));
                p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    }
}
