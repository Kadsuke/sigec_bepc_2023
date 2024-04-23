/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class SaisiePanel extends Panneau{
    private Bouton anonymat;
    private Bouton repartition;
    private Bouton note;
    
    public NoteListener noteListener = new NoteListener();
    public RepartitionCopieListener repartitionCopieListener = new RepartitionCopieListener();
    public SaisiePanel(Fenetre fenetre) throws SQLException {
        super(fenetre);
        
        
        this.anonymat = new Bouton("Anonymat");
        this.anonymat.addActionListener(this.fenetre.getBarreDeMenu().saisieListener);
        
        this.note = new Bouton("Saisie de notes");
        this.note.addActionListener(this.noteListener);
        
        this.repartition = new Bouton("Repartition");
        this.repartition.addActionListener(this.repartitionCopieListener);
        
        
        
        AnonymatPanel anonymatPanel = new AnonymatPanel(this);
        this.body.setLayout(new BorderLayout());
        this.body.add(anonymatPanel);
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.ongletPanel = new JPanel();
        this.head.ongletPanel.add(this.anonymat);
        this.head.ongletPanel.add(this.repartition);
        this.head.ongletPanel.add(this.note);
        
        
        this.head.getTitle().setText(this.fenetre.getUserSession().getJury().getJuryLibelle()+" : Gestion des copies");
        this.head.getAddNewEntity().setText("Retour à la liste");
       // Object[][] data = { {"Lycée Professionel National Maurice YAMEOGO", "LPNMY", "Public","Professionel","1000","","",""}};
        //Les titres des colonnes
       // String title[] = {"N°PV", "Nom", "Prénom(s)","Sexe","Nationalité","Date naiss.","Lieu de naiss.","Prescription hand."};
       // JTable tableau = new JTable(data, title);
       // tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
    }
    
    class NoteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try {
                SaisiePanel p = new SaisiePanel(fenetre);
                NotePanel notePanel = new NotePanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(notePanel);
                ////p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(CompositionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class RepartitionCopieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                //CandidatOrganisationPanel p = new CandidatOrganisationPanel(fenetre);
                //RepartitionCandidatPanel pb = new RepartitionCandidatPanel(p);
                fenetre.setContainer(new SaisiePanel(fenetre));
                new RepartitionCopiePanel(fenetre.getContainer());
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
