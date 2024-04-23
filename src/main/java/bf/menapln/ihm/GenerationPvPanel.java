/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.ihm.tableModel.RepartitionTableModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CandidatService;
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
public class GenerationPvPanel extends ContainerObject{
    private Panneau container;
    
    private ContainerObject listePanel;
    private ContainerObject formControlPanel;
    
    private Bouton save;
    private Bouton simuler;
    private Bouton annuler;
    
    private ObjetModel tableModel;
    public GenerationPvPanel(Panneau container) throws SQLException{
        super();
        this.container = container;
        
        //this.container.save.addActionListener(new RepartitionCandidatSaveListener());
        this.container.service = new CandidatJuryService();
        
        this.tableModel = new RepartitionTableModel(((CandidatJuryService)this.container.service).getCandidatOrdered(),this.container.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(25);
        
        this.save = new Bouton("Enregister");
        this.save.addActionListener(new SavePvListener());
        this.simuler = new Bouton("Simuler");
        this.simuler.addActionListener(new SimulerPvListener());
        this.annuler = new Bouton("Annuler");
        this.annuler.addActionListener(new AnnulerPvListener());
        
        this.formControlPanel = new ContainerObject();
        //this.formControlPanel.setLayout(new BoxLayout(this.formControlPanel,BoxLayout.X_AXIS));
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.simuler);
        this.formControlPanel.add(this.annuler);
        
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(tableau));
        this.add(this.formControlPanel,BorderLayout.SOUTH);
        
        
        this.container.body.setLayout(new BorderLayout());
        this.container.body.add(this);
    }
    
    class SimulerPvListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            int i = 0;
            for(Objet objet : tableModel.getModelData()){
                CandidatJury cj = (CandidatJury)objet;
                if(cj.getCandidat().isInJury()){
                    i++;
                    cj.getCandidat().setNumeroPv(i);
                }
            }
            tableModel.fireTableDataChanged();
        }
        
    }
    
    class AnnulerPvListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            for(Objet objet : tableModel.getModelData()){
                CandidatJury cj = (CandidatJury)objet;
                if(cj.getCandidat().isInJury()){
                    cj.getCandidat().setNumeroPv(null);
                }
            }
            tableModel.fireTableDataChanged();
        }
        
    }
    
    class SavePvListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                CandidatService cService = new CandidatService();
                for(Objet objet : tableModel.getModelData()){
                    CandidatJury cj = (CandidatJury)objet;
                    if(cj.getCandidat().isInJury() && cj.getCandidat().getNumeroPv() != null){
                        cService.updatePV(cj);
                    }
                }
                tableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(GenerationPvPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
