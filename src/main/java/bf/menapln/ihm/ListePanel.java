/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.ListePDF;
import bf.menapln.Reporting.ListePresencePDF;
import bf.menapln.Reporting.PDF;
import bf.menapln.Reporting.PDFL;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Serie;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CandidatJuryTableModel;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CentreSecondaireService;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.SerieService;
import com.itextpdf.layout.element.Paragraph;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author coulibaly
 */
public class ListePanel extends ContainerObject implements ActionListener{
    private Bouton print;
    private Bouton activeJury;
    
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;

    private JLabel centreSecondaireLabel;
    private JComboBox centreSecondaireField;
    private ContainerObject centreSecondairePanel;
    
    private Bouton printListe;
    private Bouton printListePresence;
    
    private ContainerObject filtrePanel;
    private ContainerObject listePanel;
    private ContainerObject printPanel;
    private Panneau container;

    private JLabel filtreTextLabel;
    private JTextField filtreTextField;
    private ContainerObject filtreTextPanel;
    
    private ObjetModel tableModel;
    //private JTextField filtreTextField;
    private TableRowSorter<DefaultTableModel> sortere;
    public ListePanel(Panneau container) throws SQLException{
        this.container = container;
        this.print = new Bouton("Imprimer");
        this.activeJury = new Bouton("Activer le jury");
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.setMaximumSize(new Dimension(300,30));
        this.serieField.addActionListener(this);
        this.seriePanel = new ContainerObject();
        this.seriePanel.setLayout(new BoxLayout(this.seriePanel,BoxLayout.Y_AXIS));
        this.seriePanel.add(this.serieLabel);
        this.seriePanel.add(this.serieField);

        this.centreSecondaireLabel = new JLabel("Centre Secondaire");
        this.centreSecondaireField = new JComboBox();
        this.centreSecondaireField.setMaximumSize(new Dimension(300,30));
        this.centreSecondaireField.addActionListener(new CentreSecondaireCandidat());
        this.centreSecondairePanel = new ContainerObject();
        this.centreSecondairePanel.setLayout(new BoxLayout(this.centreSecondairePanel,BoxLayout.Y_AXIS));
        this.centreSecondairePanel.add(this.centreSecondaireLabel);
        this.centreSecondairePanel.add(this.centreSecondaireField);

        this.filtreTextLabel = new JLabel("Rechercher");
        this.filtreTextField = new JTextField();
        this.filtreTextField.setMaximumSize(new Dimension(300,30));
       // this.filtreTextField.addActionListener(this);
        this.filtreTextField.getDocument().addDocumentListener(new rechercheListener());
        this.filtreTextPanel = new ContainerObject();
        this.filtreTextPanel.setLayout(new BoxLayout(this.filtreTextPanel,BoxLayout.Y_AXIS));
        this.filtreTextPanel.add(this.filtreTextLabel);
        this.filtreTextPanel.add(this.filtreTextField);
        
        SerieService seriService = new SerieService();
        List series = seriService.getSerie(this.container.fenetre.getUserSession().getJury());
        this.serieField.addItem(new Serie());
        for(Object serie : series){
            this.serieField.addItem(serie);
        }

        CentreSecondaireService centreSecondaireService = new CentreSecondaireService();

        List list = centreSecondaireService.getAll();
        this.centreSecondaireField.addItem(new CentreSecondaire());
        for(Object centre : list){
            this.centreSecondaireField.addItem(centre);
        }
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.add(this.seriePanel);
        this.filtrePanel.add(this.centreSecondairePanel);
        this.filtrePanel.add(this.filtreTextPanel);
        
        this.container.service = new CandidatJuryService();
        this.tableModel = new CandidatJuryTableModel(new ArrayList(),this.container.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(this.tableModel);
                tableau.setRowSorter(sorter);
                for (int i = 0; i < tableau.getColumnCount(); i++) {
                    sorter.setSortable(i, true);
                }
                sortere = new TableRowSorter(this.tableModel);
                tableau.setRowSorter(sortere);
                

        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        this.listePanel.add(new JScrollPane(tableau));
        
        this.printListe = new Bouton("Imprimer la liste");
        this.printListe.addActionListener(new PrintListeCandidatListener());
        this.printListePresence = new Bouton("Imprimer en liste de présence");
        this.printListePresence.addActionListener(new PrintListePresenceListener());
        this.printPanel = new ContainerObject();
        this.printPanel.setLayout(new BoxLayout(this.printPanel,BoxLayout.X_AXIS));
        this.printPanel.add(this.printListe);
        this.printPanel.add(this.printListePresence);
        
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.listePanel,BorderLayout.CENTER);
        this.add(this.printPanel,BorderLayout.SOUTH);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(((Serie)this.serieField.getSelectedItem()).getId() != null){
                this.tableModel.setModelData(((CandidatJuryService)this.container.service).getCandidatJurySerie(this.container.fenetre.getUserSession().getJury(),(Serie)this.serieField.getSelectedItem()));
                this.tableModel.fireTableDataChanged();
                centreSecondaireField.setSelectedIndex(-1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    class CentreSecondaireCandidat implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CentreSecondaire s = ((CentreSecondaire)centreSecondaireField.getSelectedItem());
                Serie c = ((Serie)serieField.getSelectedItem());

                if(s.getId() != null && c.getId() != null){
                    tableModel.setModelData(((CandidatJuryService)container.service).getCandidatBySerieAndCentre(container.fenetre.getUserSession().getJury(),((CentreSecondaire)centreSecondaireField.getSelectedItem()),(Serie)serieField.getSelectedItem()));
                    tableModel.fireTableDataChanged();

                }
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CandidatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }        }


    }
    class PrintListeCandidatListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            ListePDF liste = new ListePDF(container.fenetre);
            String path = "liste_"+container.fenetre.getUserSession().getJury()+"_";
            path = path.concat("option_"+serieField.getSelectedItem().toString()+"_");
            path = path.concat(container.fenetre.getUserSession().getJury().getEtape().toString());
            liste.setPath(path.replace(" ","")+".pdf");
            
            liste.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Liste des candidats de la série "+
            serieField.getSelectedItem().toString()+" du "+container.fenetre.getUserSession().getJury().getEtape()));
            
            liste.setTableHeaderData(new String[]{"N°PV","Nom","Prénom(s)","Sexe","Prescription hand.","Sport","Etab"});
            liste.setListeCandidat(tableModel.getModelData());
            try {
                liste.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                CandidatPanel p = new CandidatPanel(container.fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(liste));
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        
    }
    
    class PrintListePresenceListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ListePresencePDF liste = new ListePresencePDF(container.fenetre);
            Serie s = (Serie)serieField.getSelectedItem();
            try {
                EpreuveSerieService serieService = new EpreuveSerieService();
                s = (Serie)serieService.getById(s.getId());
                liste.setEpreuves(s.getEpreuves());
            } catch (SQLException ex) {
                Logger.getLogger(ListePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            String path = "liste_presence_"+container.fenetre.getUserSession().getJury()+"_";
            path = path.concat("option_"+serieField.getSelectedItem().toString()+"_");
            path = path.concat(container.fenetre.getUserSession().getJury().getEtape().toString());
            liste.setPath(path.replace(" ","")+".pdf");
            
            liste.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Liste de présence de l'option "+
            serieField.getSelectedItem().toString()+" du "+container.fenetre.getUserSession().getJury().getEtape()+"\n"));
            
            liste.setTableHeaderData(new String[]{"N°PV","Nom","Prénom(s)","Sexe","Etab","Signature"});
            liste.setListeCandidat(tableModel.getModelData());
            try {
                liste.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                CandidatPanel p = new CandidatPanel(container.fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(liste));
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
}

private void filtrer() {
    String texteFiltre = filtreTextField.getText();
    try {
        // Utilisation du texte de recherche pour configurer le filtre sur le TableRowSorter
        sortere.setRowFilter(RowFilter.regexFilter("(?i)" + texteFiltre)); // Utilise un filtre insensible à la casse
    } catch (PatternSyntaxException ex) {
        // Gestion d'une expression régulière invalide
        ex.printStackTrace();
    }
}

class rechercheListener implements DocumentListener{

    @Override
    public void insertUpdate(DocumentEvent e) {
        filtrer();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        filtrer();    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        filtrer();    }

}

}
