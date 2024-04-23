/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CandidatJuryTableModel;
import bf.menapln.service.CandidatJuryService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class CandidatPanelCopie extends Panneau{
    private JLabel centreSecondaireLabel;
    private JComboBox centreSecondaireField;
    private ContainerObject centreSecondairePanel;

    private ContainerObject westPanel;
    private ContainerObject listePanel;

    private JTextField filtreTextField;
    private TableRowSorter<DefaultTableModel> sortere;
    public CandidatPanelCopie(Fenetre fenetre){
        super(fenetre);

        this.centreSecondaireLabel = new JLabel("Centre Secondaire");
        this.centreSecondaireField = new JComboBox();
        this.centreSecondairePanel = new ContainerObject();
        //this.communeField.addActionListener(new CommuneStateListener(this.secteurCandidatField,this.etablissementField));
        this.centreSecondairePanel.setLayout(new BoxLayout(this.centreSecondairePanel,BoxLayout.Y_AXIS));
        this.centreSecondairePanel.add(this.centreSecondaireLabel);
        this.centreSecondairePanel.add(this.centreSecondaireField);


        Bouton print = new Bouton("Imprimer");
        Bouton activeJury = new Bouton("Activer le jury");
        
        activeJury.addActionListener(new ActiveJuryListener());
        String texte = this.fenetre.getUserSession().getJury().getJuryLibelle();
        

        this.westPanel = new ContainerObject();
        this.westPanel.setLayout(new BoxLayout(this.westPanel,BoxLayout.X_AXIS));
        this.westPanel.add(this.centreSecondairePanel);
        
        this.head.getSearchPanel().add(this.westPanel,BorderLayout.WEST);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());

        this.head.getTitle().setText(texte+" : Liste des candidats");
        this.head.getAddNewEntity().setText("Ajouter un candidat");
        this.head.getAddNewEntity().setEnabled(false);
        
        this.head.ongletPanel = new JPanel();
        this.head.ongletPanel.add(print);
        this.head.ongletPanel.add(this.head.getAddNewEntity());
        
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.EAST);
        this.body.setLayout(new BorderLayout());
        this.foot.add(new JLabel("Brevet d'étude du Prémier Cycle"));
    }
    
     
    public void initComposant() throws SQLException{
        super.initComposant();
        
        this.service = new CandidatJuryService();
        this.tableModel = new CandidatJuryTableModel(((CandidatJuryService)this.service).getCandidatJury(this.fenetre.getUserSession().getJury()),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(this.tableModel);
        tableau.setRowSorter(sorter);
        this.head.initComposant();
        this.body.add(new JScrollPane(tableau));

        for (int i = 0; i < tableau.getColumnCount(); i++) {
            sorter.setSortable(i, true);
        }
        
        filtreTextField = new JTextField("Recherche");
        filtreTextField.getDocument().addDocumentListener(new rechercheListener());
        filtreTextField.setPreferredSize(new Dimension(200,30));
        filtreTextField.setMinimumSize(new Dimension(200,30));
        filtreTextField.setMaximumSize(new Dimension(400,30));
        add(filtreTextField, BorderLayout.NORTH);

        sortere = new TableRowSorter(this.tableModel);
        tableau.setRowSorter(sortere);
        
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
            // TODO Auto-generated method stub
            filtrer();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // TODO Auto-generated method stub
            filtrer();        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // TODO Auto-generated method stub
            filtrer();        }
    }
    class ActiveJuryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            fenetre.getJury().setEtat(true);
            fenetre.getBarreDeMenu().initComponent();
        }
        
    }
}
