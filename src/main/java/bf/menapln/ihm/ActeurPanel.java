/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CandidatPosteJuryTableModel;
import bf.menapln.service.CandidatPosteJuryService;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class ActeurPanel extends Panneau{
    private Bouton selectActeur;
    private Bouton affecterActeur;
    private Bouton convoquerActeur;
    
    private ContainerObject listePanel;
    
    public ActeurPanel(Fenetre fenetre) {
        super(fenetre);
        this.selectActeur = new Bouton("Selection");
        this.selectActeur.addActionListener(fenetre.getBarreDeMenu().getActeurListener());
        this.affecterActeur = new Bouton("Affecter à votre jury");
        this.affecterActeur.addActionListener(new AffectationListener());
        this.convoquerActeur = new Bouton("Convocation");
        
       
        this.save = new Bouton("Retenir");
        this.save.addActionListener(new RetenirCandidatListener());
        
        this.listePanel = new ContainerObject();
        this.body.setLayout(new BorderLayout());
        this.body.add(this.listePanel);
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des acteurs");
        this.head.ongletPanel = new ContainerObject();
        this.head.ongletPanel.add(this.selectActeur);
        this.head.ongletPanel.add(this.affecterActeur);
       // this.head.ongletPanel.add(this.convoquerActeur);
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.WEST);
        this.head.initComposant();
        
        this.service = new CandidatPosteJuryService();
        //this.service.setImplementation("Candidat");
        
        this.tableModel = new CandidatPosteJuryTableModel(this.service.getAll(""),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(25);
        //this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BoxLayout(this.listePanel,BoxLayout.Y_AXIS));
        this.listePanel.add(new JScrollPane(tableau));
        this.listePanel.add(this.save);
        
        
        
    }
    
    class RetenirCandidatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            for(Objet candidat : tableModel.getModelData()){
                if(((CandidatPosteJury)candidat).isChecked()){
                    try {
                        service.update(candidat);
                        tableModel.fireTableDataChanged();
                    } catch (SQLException ex) {
                        Logger.getLogger(ActeurPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
        }
        
    }
    
    class AffectationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
                 
            try {
                
                fenetre.setContainer(new ActeurPanel(fenetre));
                fenetre.getContainer().body.setLayout(new BorderLayout());
                fenetre.getContainer().body.add(new ActeurAffectationBodyPanel(fenetre.getContainer()));
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
                
                /*ActeurPanel p = new ActeurPanel(fenetre);
                p.body.setLayout(new BorderLayout());
                p.body.add(new ActeurAffectationBodyPanel(p));
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();*/
            } catch (SQLException ex) {
                Logger.getLogger(ActeurPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
