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

public class RepartitionCentreSecondairePanel extends ContainerObject{
    private Panneau container;
    
    private ContainerObject listePanel;
    private ContainerObject formControlPanel;
    
    private Bouton save;
    private Bouton annuler;
    
    private ObjetModel tableModel;
    public RepartitionCentreSecondairePanel(Panneau container) throws SQLException{
        super();
        this.container = container;
        
        //this.container.save.addActionListener(new RepartitionCandidatSaveListener());
        this.container.service = new CandidatJuryService();
        
        this.tableModel = new RepartitionTableModel(((CandidatJuryService)this.container.service).getCandidatOrdered(),this.container.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(25);
        
        this.save = new Bouton("Répartir");
        this.save.addActionListener(new SaveRepartitionListener());

        this.annuler = new Bouton("Annuler");
        this.annuler.addActionListener(new AnnulerPvListener());
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.annuler);
        
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(tableau));
        this.add(this.formControlPanel,BorderLayout.SOUTH);
        
        
        this.container.body.setLayout(new BorderLayout());
        this.container.body.add(this);
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
    
    class SaveRepartitionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                CandidatJuryService cService = new CandidatJuryService();
                for(Objet objet : tableModel.getModelData()){
                    CandidatJury cj = (CandidatJury)objet;
                    if(cj.getCentreSecondaire() != null){
                        cService.updateCentreSecond(cj);
                    }
                }
                tableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(GenerationPvPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
