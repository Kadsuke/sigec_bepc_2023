/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.tableModel.CandidatPosteJuryTableModel;
import bf.menapln.service.CandidatPosteJuryService;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class CandidatPosteJuryPanel extends Panneau{
    private ContainerObject listePanel;
    public CandidatPosteJuryPanel(Fenetre fenetre) {
        super(fenetre);
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Liste des candidats aux postes de jury");
        this.head.getAddNewEntity().setText("Ajouter un acteur");
        this.head.getAddNewEntity().addActionListener(new AddCandidatPosteJuryListener());
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        this.service = new CandidatPosteJuryService();
        //this.service.setImplementation("Candidat");
        
        this.tableModel = new CandidatPosteJuryTableModel(this.service.getAll(""),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        tableau.removeColumn(tableau.getColumnModel().getColumn(0));
        this.listePanel = new ContainerObject();
        //this.setLayout(new BorderLayout());
        this.add(new JScrollPane(tableau));
        
        this.body.add(this.listePanel);
    }
    
    class AddCandidatPosteJuryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CandidatPosteJuryForm p = new CandidatPosteJuryForm(fenetre);
           fenetre.setContainer(p);
            /* try {
               // fenetre.getContainer().initComposant();
                
            } catch (SQLException ex) {
                Logger.getLogger(EtablissementPanel.class.getName()).log(Level.SEVERE, null, ex);
            } */
            try {
                fenetre.initComponent();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
}
