/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CentreStateListener;
import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.Localite;
import bf.menapln.ihm.tableModel.RepartitionTableModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.LocaliteService;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class RepartitionCandidatPanel extends ContainerObject{
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;

    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;
    
    private JLabel centreExamenLabel;
    private JComboBox centreExamenField;
    private ContainerObject centreExamenPanel;
    
    private JLabel juryLabel;
    private JComboBox juryField;
    private ContainerObject juryPanel;

    private JLabel centreSecondaireLabel;
    private JComboBox centreSecondaireField;
    private ContainerObject centreSecondairePanel;
    
    private ContainerObject filtrePanel;
    private ContainerObject listePanel;
    private ContainerObject bodyPanel;
    
    private Panneau container;
    
    private RepartitionTableModel tableModel;
    public RepartitionCandidatPanel(Panneau container) throws SQLException {
        this.container = container;
        
        this.centreSecondaireLabel = new JLabel("Centre Secondaire");
        this.centreSecondaireField = new JComboBox();
        this.centreSecondairePanel = new ContainerObject();
        this.centreSecondairePanel.setLayout(new BoxLayout(this.centreSecondairePanel,BoxLayout.Y_AXIS));
        this.centreSecondairePanel.add(this.centreSecondaireLabel);
        this.centreSecondairePanel.add(this.centreSecondaireField);


        this.juryLabel = new JLabel("Jury");
        this.juryField = new JComboBox();
        this.juryPanel = new ContainerObject();
        this.juryField.addActionListener(CentreStateListener.centreSecondaireField(centreSecondaireField));
        this.juryPanel.setLayout(new BoxLayout(this.juryPanel,BoxLayout.Y_AXIS));
        this.juryPanel.add(this.juryLabel);
        this.juryPanel.add(this.juryField);
        
        this.centreExamenLabel = new JLabel("Centre d'examen");
        this.centreExamenField = new JComboBox();
        this.centreExamenPanel = new ContainerObject();
        this.centreExamenField.addActionListener(CentreStateListener.juryField(juryField));
        this.centreExamenPanel.setLayout(new BoxLayout(this.centreExamenPanel,BoxLayout.Y_AXIS));
        this.centreExamenPanel.add(this.centreExamenLabel);
        this.centreExamenPanel.add(this.centreExamenField);
        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communePanel = new ContainerObject();
        this.communeField.addActionListener(new CommuneStateListener(this.centreExamenField));
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.addActionListener(new RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);
        
        this.filtrePanel = new ContainerObject();
        this.filtrePanel.setLayout(new BoxLayout(this.filtrePanel,BoxLayout.X_AXIS));
        this.filtrePanel.add(this.regionPanel);
        this.filtrePanel.add(this.provincePanel);
        this.filtrePanel.add(this.communePanel);
        this.filtrePanel.add(this.centreExamenPanel);
        this.filtrePanel.add(this.juryPanel);
        this.filtrePanel.add(this.centreSecondairePanel);
        
        
        
        this.container.save.addActionListener(new RepartitionCandidatSaveListener());
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        this.bodyPanel = new ContainerObject();
        this.bodyPanel.setLayout(new BorderLayout());
        this.bodyPanel.add(this.filtrePanel,BorderLayout.NORTH);
        this.bodyPanel.add(this.listePanel);
        this.bodyPanel.add(this.container.formControlPanel,BorderLayout.SOUTH);
        
        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
        
        this.container.service = new CandidatJuryService();
        
        this.tableModel = new RepartitionTableModel(this.container.service.getAll(""),this.container.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(25);
        tableau.getColumnModel().removeColumn(tableau.getColumnModel().getColumn(1));
        this.listePanel.add(new JScrollPane(tableau));
        this.container.body.setLayout(new BorderLayout());
        this.container.body.add(this.bodyPanel);
    }
    
    /*@Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.initComposant();
        
        
    }*/
    
    class CandidatLoadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            /* Serie serie = (Serie)serieField.getSelectedItem();
            Localite villeComposition = (Localite)villeCompositionField.getSelectedItem();
            //System.out.println("vill+serie : "+(serie != null && villeComposition != null));
            if((serie != null && serie.getId() != null && villeComposition != null && villeComposition.getId() != null)){
                try {
                    tableModel.setModelData(((CandidatJuryService)container.service).getCandidatJury(serie, villeComposition));
                    tableModel.fireTableDataChanged();
                } catch (SQLException ex) {
                    Logger.getLogger(RepartitionCandidatPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } */
        }
        
    }
    
    class RepartitionCandidatSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           for(Object data : tableModel.getModelData()){
                if(((CandidatJury)data).getCandidat().isInJury()){
                    //CandidatJuryService cjService;
                    try {
                       // CandidatJuryService service = new CandidatJuryService();

                        container.service.setFormData(new HashMap());
                        if(centreSecondaireField.getSelectedItem() == null || centreSecondaireField.getSelectedItem().toString() == null){
                            container.service.getFormData().put("session", container.fenetre.getUserSession().getSession());
                            container.service.getFormData().put("jury", (Jury)juryField.getSelectedItem());
                            container.service.getFormData().put("candidat", ((CandidatJury)data).getCandidat());
                            container.service.save();

                        }else{
                            container.service.getFormData().put("session", container.fenetre.getUserSession().getSession());
                            container.service.getFormData().put("jury", (Jury)juryField.getSelectedItem());
                            container.service.getFormData().put("candidat", ((CandidatJury)data).getCandidat());
                            try {
                                container.service.getFormData().put("centreSecondaire", (CentreSecondaire)centreSecondaireField.getSelectedItem());
                            } catch (NullPointerException e) {
                                // TODO: handle exception
                            }
                            container.service.save();
                        }
                         tableModel.fireTableDataChanged();
                    } catch (SQLException | EmptyDataException | NullPointerException ex) {
                        Logger.getLogger(RepartitionCandidatPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }
    
}
