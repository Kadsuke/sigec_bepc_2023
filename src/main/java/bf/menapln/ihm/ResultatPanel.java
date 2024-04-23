/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.Reporting.Attestation;
import bf.menapln.Reporting.PDF;
import bf.menapln.Reporting.PDFL;
import bf.menapln.Reporting.Releve;
import bf.menapln.Reporting.ResultatPDF;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.tableModel.ResultatTableModel;
import bf.menapln.service.CentreSecondaireService;
import bf.menapln.service.ResultatService;
import bf.menapln.service.SerieService;
import com.itextpdf.layout.element.Paragraph;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author coulibaly
 */
public class ResultatPanel extends ContainerObject implements ActionListener{
    private ContainerObject filtrePanel;
    private ContainerObject candidatPanel;
    
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;

    private JLabel juryLabel;
    private JComboBox juryField;
    private ContainerObject juryPanel;
    
    private JLabel centreSecondaireLabel;
    private JComboBox centreSecondaireField;
    private ContainerObject centreSecondairePanel;


    private JLabel decisionLabel;
    private JComboBox decisionField;
    private ContainerObject decisionPanel;
    
    private Panneau container;
    
    private ObjetModel model;
    private JTable tableau;
    private ResultatService service;

    private Bouton releveNote;
    private Bouton attestation;
    public ResultatPanel(Panneau container) throws SQLException{
        super();
        
        this.container = container;
        
        Type ajourne = Type.id(Long.parseLong("1"));
        ajourne.setLibelle("Admis");
        Type admissible = Type.id(Long.parseLong("2"));
        admissible.setLibelle("Admissibles");
        Type admis = Type.id(Long.parseLong("3"));
        admis.setLibelle("Ajournés");
        
        this.decisionLabel = new JLabel("Dec. Jury");
        this.decisionField = new JComboBox();
        this.decisionField.addItem(ajourne);
        this.decisionField.addItem(admissible);
        this.decisionField.addItem(admis);
        this.decisionField.addActionListener(this);
        this.decisionField.setPreferredSize(new Dimension(200,30));
        this.decisionField.setMaximumSize(new Dimension(400,30));
        
        this.decisionPanel = new ContainerObject();
        this.decisionPanel.setLayout(new BoxLayout(this.decisionPanel,BoxLayout.Y_AXIS));
        this.decisionPanel.add(this.decisionLabel);
        this.decisionPanel.add(this.decisionField);

        this.centreSecondaireLabel = new JLabel("Centre Secondaire");
        this.centreSecondaireField = new JComboBox();
        this.centreSecondaireField.addActionListener(new CentreResultatListener());
        this.centreSecondaireField.setPreferredSize(new Dimension(200,30));
        this.centreSecondaireField.setMaximumSize(new Dimension(400,30));
        
        this.centreSecondairePanel = new ContainerObject();
        this.centreSecondairePanel.setLayout(new BoxLayout(this.centreSecondairePanel,BoxLayout.Y_AXIS));
        this.centreSecondairePanel.add(this.centreSecondaireLabel);
        this.centreSecondairePanel.add(this.centreSecondaireField);
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.setPreferredSize(new Dimension(this.getWidth(),60));
        this.filtrePanel.add(this.decisionPanel);
        this.filtrePanel.add(this.centreSecondairePanel);

        
        CentreSecondaireService centreSecondaireService = new CentreSecondaireService();

        List list = centreSecondaireService.getAll();
        this.centreSecondaireField.addItem(new CentreSecondaire());
        for(Object epreuve : list){
            this.centreSecondaireField.addItem(epreuve);
        }

        
        this.service = new ResultatService();
        this.model = new ResultatTableModel(((ResultatService)this.service).getResultat(this.container.fenetre.getUserSession().getJury(),(Type)decisionField.getSelectedItem()),this.container.getFenetre());
        
        tableau = new JTable(this.model);
        tableau.setRowHeight(25);
      
        this.candidatPanel = new ContainerObject();
        this.candidatPanel.setLayout(new BorderLayout());
        this.candidatPanel.add(new JScrollPane(tableau));
        
        this.attestation = new Bouton("Attestation");
        this.attestation.addActionListener(new PrintAttestationListener());
        //this.attestation.setEnabled(false);

        this.releveNote = new Bouton("Releve");
        this.releveNote.addActionListener(new PrintReleveListener());
        this.releveNote.setEnabled(false);

        this.container.save = new Bouton("Imprimer");
        this.container.save.addActionListener(new PrintResultatListener());
        
        this.container.formControlPanel = new ContainerObject();
        this.container.formControlPanel.add(this.attestation);
        this.container.formControlPanel.add(this.releveNote);
        this.container.formControlPanel.add(this.container.save);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(this.filtrePanel,BorderLayout.NORTH);
        this.add(this.candidatPanel,BorderLayout.CENTER);
        this.add(this.container.formControlPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(this.model != null){
                this.model.setModelData(this.service.getResultat(this.container.fenetre.getUserSession().getJury(), (Type)decisionField.getSelectedItem()));
                this.model.fireTableDataChanged();
                centreSecondaireField.setSelectedIndex(-1);

            }
            Type t = (Type)decisionField.getSelectedItem();
            if(t.getLibelle().equals("Admis")){
                this.attestation.setEnabled(true);
            }else{
                 this.attestation.setEnabled(false);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     class CentreResultatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if(centreSecondaireField.getSelectedItem() != null){
                    model.setModelData(service.getResultatCentreSecondaire((CentreSecondaire)centreSecondaireField.getSelectedItem(), container.fenetre.getUserSession().getJury(), (Type)decisionField.getSelectedItem()));
                    model.fireTableDataChanged();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (NullPointerException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    }
    
    class PrintResultatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ResultatPDF resultat = new ResultatPDF(container.fenetre);
            try {
                resultat.setPath("resultat_"+decisionField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                resultat.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                resultat.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n ------- \n Secrétariat Général \n ------- \n Direction Générale des Examens et Concours (DGEC)"));
                resultat.setTitle(new Paragraph("\nBEPC "+container.fenetre.getUserSession().getSession().getAnnee()+" : "+container.fenetre.getUserSession().getJury().toString()+"\n Sous réserve d'un contrôle approfondi, les candidats dont les noms suivent sont déclarés "+
                decisionField.getSelectedItem().toString().toLowerCase()+" à l'issue des épreuves du "+container.fenetre.getUserSession().getJury().getEtape()));
                resultat.setTableHeaderData(new String[]{"N° Table","Nom & prénom(s)","Sexe","Date & lieu de naissance","Mention"});
                resultat.setListeCandidat(model.getModelData());
                resultat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                DeliberationPanel p = new DeliberationPanel(container.fenetre);
                //PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(resultat));
                //p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    }


    class PrintAttestationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Attestation resultat = new Attestation((container.fenetre));
            try {
                resultat.setPath("attestation "+decisionField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                resultat.setDevise(new Paragraph("BURKINA FASO \n Unité - Progrès - Justice \n\n\n N° : 2023/R03/J65/05"));
                resultat.setTimbre(new Paragraph("MINISTERE DE L'EDUCATION NATIONALE, DE L'ALPHABETISATION ET DE LA PROMOTION DES LANGUES NATIONALES\n \nSECRETARIAT GENERAL \n \nDIRECTION GENERALE DES EXAMENS ET CONCOURS"));
                resultat.setTitle(new Paragraph("\nLE DIRECTEUR GENERAL DES EXAMENS\n ET CONCOURS SOUSSIGNE, ATTESTE QUE: "));
              //  resultat.setTableHeaderData(new String[]{"N° Table","Nom & prénom(s)","Sexe","Date & lieu de naissance","Mention"});
               // resultat.body();
                resultat.setListeCandidat(model.getModelData());
                resultat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                DeliberationPanel p = new DeliberationPanel(container.fenetre);
                //PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDF.pdfViewer(resultat));
                //p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    }

    class PrintReleveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Releve resultat = new Releve((container.fenetre));
            try {
                resultat.setPath("attestation "+decisionField.getSelectedItem().toString().toLowerCase()+"_"+container.fenetre.getUserSession().getJury().getEtape().toString().replace(" ",""));
                resultat.setDevise(new Paragraph("Burkina Faso \n Unité - Progrès - Justice"));
                resultat.setTimbre(new Paragraph("Ministère de l'Education Nationale, de l'Alphabétisation et de la promotion des langues nationales (MENAPLN)\n Secrétariat Général \n Direction Générale des Examens et Concours (DGEC)"));
                resultat.setTitle(new Paragraph("\nLE DIRECTEUR GENERALE DES EXAMENS\n ET CONCOURS SOUSSIGNE,ATTESTE QUE: "));
              //  resultat.setTableHeaderData(new String[]{"N° Table","Nom & prénom(s)","Sexe","Date & lieu de naissance","Mention"});
               // resultat.body();
                resultat.setListeCandidat(model.getModelData());
                resultat.generatePDF();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Panneau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                DeliberationPanel p = new DeliberationPanel(container.fenetre);
                //PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(PDFL.pdfViewer(resultat));
                p.initComposant();
                container.fenetre.setContainer(p);
                container.fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(ResultatPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
    }
}
