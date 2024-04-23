/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CentreStateListener;
import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Session;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CandidatPosteJuryTableModel;
import bf.menapln.service.CandidatPosteJuryService;
import bf.menapln.service.EpreuveService;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.MembreService;
import exception.EmptyDataException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

/**
 *
 * @author coulibaly
 */
public class ActeurAffectationBodyPanel extends ContainerObject{
    private Panneau container;
    
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel villeCompositionLabel;
    private JComboBox villeCompositionField;
    private ContainerObject villeCompositionPanel;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    private ContainerObject seriePanel;
    
    private JLabel centreLabel;
    private JComboBox centreField;
    private ContainerObject centrePanel;
    
    private JLabel juryLabel;
    private JComboBox juryField;
    private ContainerObject juryPanel;
    
    private JLabel disciplineLabel;
    private JComboBox disciplineField;
    private ContainerObject disciplinePanel;
    
    private JLabel posteLabel;
    private JComboBox posteField;
    private ContainerObject postePanel;
    
    private Bouton save;
    
    private ContainerObject filtrePanel;
    private ContainerObject listePanel;
    
    CandidatPosteJuryTableModel tableModel;
    
    public ActeurAffectationBodyPanel(Panneau container) throws SQLException{
        this.container = container;
        
        this.posteLabel = new JLabel("Poste");
        this.posteField = new JComboBox();
        this.postePanel = new ContainerObject();
        this.postePanel.setLayout(new BoxLayout(this.postePanel,BoxLayout.Y_AXIS));
        this.postePanel.add(this.posteLabel);
        this.postePanel.add(this.posteField);
        
        this.disciplineLabel = new JLabel("Matière");
        this.disciplineField = new JComboBox();
        this.disciplinePanel = new ContainerObject();
        this.disciplinePanel.setLayout(new BoxLayout(this.disciplinePanel,BoxLayout.Y_AXIS));
        this.disciplinePanel.add(this.disciplineLabel);
        this.disciplinePanel.add(this.disciplineField);
        
        
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        //this.filtrePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
       // this.filtrePanel.add(this.seriePanel);
      //  this.filtrePanel.add(this.postePanel);
      //  this.filtrePanel.add(this.disciplinePanel);
        
        
        EpreuveService epreuveService = new EpreuveService();
        List epreuves = epreuveService.getAll("epreuve");
        for(Object epreuve : epreuves){
            this.disciplineField.addItem((Epreuve)epreuve);
        }
        
        this.container.service = new CandidatPosteJuryService();
        //this.service.setImplementation("Candidat");
        
        tableModel = new CandidatPosteJuryTableModel(((CandidatPosteJuryService) this.container.service).getAllWithoutRetenu(),this.container.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(25);
        
        this.save = new Bouton("Affecter");
        this.save.addActionListener(new AffecterListener());
                
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //this.add(this.filtrePanel);
        this.add(new JScrollPane(tableau));
        this.add(this.save);
    }
    class AffecterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                MembreService service = new MembreService();
                for(Objet membre : tableModel.getModelData()){
                    if(membre.isChecked()){
                        service.setFormData(new HashMap());
                        service.getFormData().put("session", container.fenetre.getUserSession().getSession());
                        service.getFormData().put("jury", container.fenetre.getUserSession().getJury());
                        service.getFormData().put("poste", ((CandidatPosteJury)membre).getPoste());
                        service.getFormData().put("personnel", ((CandidatPosteJury)membre).getCandidat());
                        service.getFormData().put("epreuve", ((CandidatPosteJury)membre).getEpreuve());
                        service.getFormData().put("membreDeliberant", true);
                        service.save();
                       
                    }

                }
                Timer timer = new Timer(1000, e -> {
                            // Code exécuté après 10 secondes
                            JOptionPane.showMessageDialog(container,"Candidat Affecté avec succès");
                        });
                        timer.setRepeats(false); // Ne pas répéter le Timer
                
                        timer.start(); // Démarrer le Timer
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(ActeurAffectationBodyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
