/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.AnonymatPDF;
import bf.menapln.Reporting.NotePDF;
import bf.menapln.Reporting.PDF;
import bf.menapln.Reporting.TestPDF;
import bf.menapln.actionListener.EpreuveStateListener;
import bf.menapln.actionListener.SerieStateListener;
import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Membre;
import bf.menapln.entity.Personnel;
import bf.menapln.entity.Serie;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.NoteTableModel;
import bf.menapln.ihm.tableModel.ObjetModel;
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
public class NotePanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    private ContainerObject seriePanel;
    private ContainerObject epreuvePanel;
    private ContainerObject correcteurPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private JComboBox epreuveField;
    private JLabel epreuveeLabel;
    private JLabel correcteurLabel;
    private JComboBox correcteurField;
    
    private Panneau container;
    
    private ObjetModel model;
    private JTable tableau;
    private Bouton print;
    public NotePanel(Panneau container) throws SQLException{
        
        this.container = container;
        
        this.correcteurLabel = new JLabel("Correcteur");
        this.correcteurField = new JComboBox();
        this.correcteurField.addActionListener(this);
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
   
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.epreuvePanel);
        this.filtrePanel.add(correcteurPanel);
        
        EpreuveService eService = new EpreuveService();
        List epreuves = null;
        epreuves = eService.getEpreuveCopiesAll(this.container.fenetre.getUserSession().getSession(), this.container.fenetre.getUserSession().getJury(), this.container.fenetre.getUserSession().getTourComposition());
        this.epreuveField.addItem(new Epreuve());
        for(Object epreuve : epreuves){
            this.epreuveField.addItem(epreuve);
        }
        
        this.container.service = new FeuilleCompositionService();
        this.model = new NoteTableModel(new ArrayList(),this.container.getFenetre());
        
        tableau = new JTable(this.model);
        tableau.setRowHeight(25);
        
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.print = new Bouton("Aperçu av. imp.");
        this.print.addActionListener(new PrintNoteListener());
        
        this.container.formControlPanel = new ContainerObject();
        this.container.formControlPanel.add(this.print);
        
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(correcteurField.getSelectedItem() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            List data;
            try {
                data = ((FeuilleCompositionService)this.container.service).getFeuilleWithoutSerieForCorrecteur(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(),(Correcteur)correcteurField.getSelectedItem(), us.getTourComposition());
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
                String col1 = (!this.model.getModelData().isEmpty()  && ((FeuilleComposition)this.model.getModelData().get(0)).getAnonymat() != null)? "Anonymat":"N°PV";
                this.tableau.getColumnModel().getColumn(1).setHeaderValue(col1);

            } catch (SQLException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NullPointerException e){

            }
        }else if (correcteurField.getSelectedItem() == null && epreuveField.getSelectedItem() != null){
            UserSession us = this.container.getFenetre().getUserSession();
            List data;
            try {
                data = ((FeuilleCompositionService)this.container.service).getFeuilleWithoutSerie(us.getSession(), us.getJury(), (Epreuve)epreuveField.getSelectedItem(), us.getTourComposition());
                this.model.setModelData(data);
                this.model.fireTableDataChanged();
                String col1 = (!this.model.getModelData().isEmpty()  && ((FeuilleComposition)this.model.getModelData().get(0)).getAnonymat() != null)? "Anonymat":"N°PV";
                this.tableau.getColumnModel().getColumn(1).setHeaderValue(col1);

            } catch (SQLException ex) {
                Logger.getLogger(AbscenceBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NullPointerException e){

            }
        }
    }
    
    class PrintNoteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            NotePDF note = new NotePDF(container.fenetre);
            try {
                note.setPath("note_"+epreuveField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                note.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                note.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n ------- \n Secrétariat Général \n ------- \n Direction Générale des Examens et Concours (DGEC)"));
                note.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Note "+
                epreuveField.getSelectedItem().toString().toLowerCase()+" "+container.fenetre.getUserSession().getJury().getEtape()+"\n Correcteur "+" : "+correcteurField.getSelectedItem().toString().toLowerCase()+"\nTotal Copie : "+model.getRowCount()));
                note.setTableHeaderData(new String[]{"Anonymat","Note / 20"});
                note.setListeCandidat(model.getModelData());
                note.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                SaisiePanel p = new SaisiePanel(container.fenetre);
                //PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(note));
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
        
    }
}
