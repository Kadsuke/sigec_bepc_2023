/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Epreuve;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.EpreuveService;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class EpreuvePanel extends Panneau{
    
    private JSplitPane bodyPanel;
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    
    private JLabel epreuveLabel;
    private JTextField epreuveField;
    private JLabel epreuveAcronymeLabel;
    private JTextField epreuveAcronymeField;
    
    public SaveEpreuveListener saveEpreuveListener = new SaveEpreuveListener();
    
    
    public EpreuvePanel(Fenetre fenetre) {
        super(fenetre);
       
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
        this.epreuveLabel = new JLabel("Epreuve");
        this.epreuveField = new JTextField();
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuveField.setMaximumSize(new Dimension(300,30));
        
        this.epreuveAcronymeLabel = new JLabel("Acronyme");
        this.epreuveAcronymeField = new JTextField();
        this.epreuveAcronymeField.setPreferredSize(new Dimension(200,30));
        this.epreuveAcronymeField.setMaximumSize(new Dimension(300,30));
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(this.saveEpreuveListener);
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.epreuveLabel);
        this.formPanel.add(this.epreuveField);
        this.formPanel.add(this.epreuveAcronymeLabel);
        this.formPanel.add(this.epreuveAcronymeField);
        this.formPanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/3)*2);
        
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
        this.foot.add(new JLabel("foot"));
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des épreuves");
        this.head.getAddNewEntity().setText("Ajouter une option");
        //this.head.getAddNewEntity().addActionListener(new AddEtablissementListener());
        List epreuves = null;
        try {
            this.service = new EpreuveService();
            epreuves = this.service.getAll("");
            System.out.println(epreuves.size());
        } catch (SQLException ex) {
            Logger.getLogger(SessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[][] data = new String[epreuves.size()][5];
        for(int line = 0 ; line < epreuves.size() ; line++){
            data[line][0] = ((Epreuve)epreuves.get(line)).getId().toString();
            data[line][1] = ((Epreuve)epreuves.get(line)).getEpreuveLibelle();
            data[line][2] = ((Epreuve)epreuves.get(line)).getEpreuveAcronyme();
            data[line][3] = "";//((Serie)series.get(line)).getOffreEnseignement().getLibelle();
            //data[line][4] = "";//((Session)sessions.get(line)).getDateDebutSessNorm().toString();
            //data[line][5] = "";//((Session)sessions.get(line)).getDateFinSessNorm().toString();
            
        }
        
        
        //Les titres des colonnes
        String title[] = {"ID","Epreuve", "Acronyme","Option"};
        TableModel model = new TableModel(data,title);
        JTable tableau = new JTable(model);
        //tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
       
        this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        //this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        this.listePanel.add(new JScrollPane(tableau));
    }
    
    class SaveEpreuveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("event");
            try {
                service = new EpreuveService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("epreuveLibelle", epreuveField.getText());
                service.getFormData().put("epreuveAcronyme", epreuveAcronymeField.getText());
                //service.getFormData().put("definition",serieDefField.getText());
                service.save();
                fenetre.getBarreDeMenu().epreuveListener.actionPerformed(ae);
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
