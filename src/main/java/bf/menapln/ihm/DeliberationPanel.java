/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.JuryService;
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
public class DeliberationPanel extends Panneau{
    private Bouton validationnote;
    private Bouton pondereCompare;
    private Bouton pvdeliberation;
    private Bouton resultat;
    private Bouton rachat;
    
    private Bouton valideEtapeComposition;
    
    public PvdeliberationListener pvdeliberationListener = new PvdeliberationListener();
    public ResultatListener resultatListener = new ResultatListener();
    public RachatListener rachatListener = new RachatListener();
 
    
    public DeliberationPanel(Fenetre fenetre) throws SQLException{
        super(fenetre);
        this.validationnote = new Bouton("vérification note");
        this.validationnote.addActionListener(this.fenetre.getBarreDeMenu().deliberationListener);
    
        this.pvdeliberation = new Bouton("Pv de délibération");
        this.pvdeliberation.addActionListener(this.pvdeliberationListener);
        
        this.resultat = new Bouton("Résultat");
        this.resultat.addActionListener(this.resultatListener);

        this.rachat = new Bouton("Rachat");
        this.rachat.addActionListener(this.rachatListener);
        
        this.valideEtapeComposition = new Bouton("Valider le "+this.fenetre.getUserSession().getJury().getEtape().getLibelle());
        this.valideEtapeComposition.addActionListener(new ValiderEtapeListener());
        
        NotePanel notePanel = new NotePanel(this);
        this.body.setLayout(new BorderLayout());
        this.body.add(notePanel);
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
       
        this.head.ongletPanel = new JPanel();
        this.head.ongletPanel.add(this.validationnote);
        this.head.ongletPanel.add(this.rachat);
        this.head.ongletPanel.add(this.pvdeliberation);
        this.head.ongletPanel.add(this.resultat);
        String texte = this.fenetre.getUserSession().getJury().getJuryLibelle();
        this.head.getTitle().setText(texte +" : Délibération ( Vérification des notes)");
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.WEST);
        this.head.getSearchPanel().add(this.valideEtapeComposition,BorderLayout.EAST);
        this.head.initComposant();
        
    }
    
    class PvdeliberationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                
                DeliberationPanel p = new DeliberationPanel(fenetre);
                PVPanel bodyPanel = new PVPanel(p);
                p.body.setLayout(new BorderLayout());
                p.body.add(bodyPanel);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(DeliberationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class ResultatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
               
                DeliberationPanel p = new DeliberationPanel(fenetre);
                 ResultatPanel bodyPanel = new ResultatPanel(p);
                //p.body = new ContainerObject();
                p.body.setLayout(new BorderLayout());
                p.body.add(bodyPanel);
                //p.initComposant();
                fenetre.setContainer(p);
            fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(DeliberationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    class RachatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
               
                DeliberationPanel p = new DeliberationPanel(fenetre);
                 RachatPanel bodyPanel = new RachatPanel(p);
                //p.body = new ContainerObject();
                p.body.setLayout(new BorderLayout());
                p.body.add(bodyPanel);
                //p.initComposant();
                fenetre.setContainer(p);
            fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(DeliberationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class ValiderEtapeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                JuryService jService = new JuryService();
                jService.valideEtape(fenetre.getUserSession().getJury());
                fenetre.getBarreDeMenu().getTour().setText(fenetre.getUserSession().getJury().getEtape().getLibelle());
                valideEtapeComposition.setText("Valider le "+fenetre.getUserSession().getJury().getEtape().getLibelle());
                valideEtapeComposition.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(DeliberationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException e){
                
            }
        }
        
    }
}
