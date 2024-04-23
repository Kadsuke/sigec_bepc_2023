/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Pays;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.PaysService;
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
public class PaysPanel extends Panneau{
    private JLabel indicatifLabel;
    private JTextField indicatifField;
    private ContainerObject indicatifPanel;
    
    private JLabel nomPaysLabel;
    private JTextField nomPaysField;
    private ContainerObject nomPaysPanel;
    
    private JLabel nationaliteLabel;
    private JTextField nationaliteField;
    private ContainerObject nationalitePanel;
    
    private ContainerObject formPanel;
    private ContainerObject listePanel;
    private JSplitPane bodyPanel;
    
    public PaysPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.indicatifLabel = new JLabel("Indicatif");
        this.indicatifField = new JTextField();
        this.indicatifField.setPreferredSize(new Dimension(200,30));
        this.indicatifField.setMinimumSize(new Dimension(200,30));
        this.indicatifField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.indicatifPanel = new ContainerObject();
        this.indicatifPanel.setLayout(new BoxLayout(this.indicatifPanel,BoxLayout.Y_AXIS));
        this.indicatifPanel.add(this.indicatifLabel);
        this.indicatifPanel.add(this.indicatifField);
        
        this.nomPaysLabel = new JLabel("Pays");
        this.nomPaysField = new JTextField();
        this.nomPaysField.setPreferredSize(new Dimension(200,30));
        this.nomPaysField.setMinimumSize(new Dimension(200,30));
        this.nomPaysField.setMaximumSize(new Dimension(400,30));
        this.nomPaysPanel = new ContainerObject();
        this.nomPaysPanel.setLayout(new BoxLayout(this.nomPaysPanel,BoxLayout.Y_AXIS));
        this.nomPaysPanel.add(this.nomPaysLabel);
        this.nomPaysPanel.add(this.nomPaysField);
        
        this.nationaliteLabel = new JLabel("Nationalité");
        this.nationaliteField = new JTextField();
        this.nationaliteField.setPreferredSize(new Dimension(200,30));
        this.nationaliteField.setMinimumSize(new Dimension(200,30));
        this.nationaliteField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.nationalitePanel = new ContainerObject();
        this.nationalitePanel.setLayout(new BoxLayout(this.nationalitePanel,BoxLayout.Y_AXIS));
        this.nationalitePanel.add(this.nationaliteLabel);
        this.nationalitePanel.add(this.nationaliteField);
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new PaysSaveListener());
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.indicatifPanel);
        this.formPanel.add(this.nomPaysPanel);
        this.formPanel.add(this.nationalitePanel);
        this.formPanel.add(this.formControlPanel);
        
        this.listePanel = new ContainerObject();
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/3)*2);
        this.body.setLayout(new BorderLayout());
       
        
        this.foot.add(new JLabel("foot"));
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        
        this.head.getTitle().setText("Gestion des types de données");
        this.head.getAddNewEntity().setText("Ajouter une localité");
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        if(this.service == null){
            this.service = new PaysService();
            
        }
        List listePays = this.service.getAll("");
        Object[][] data = new String[listePays.size()][6];
        for(int line = 0 ; line < listePays.size(); line++){
            //System.out.println(loc);
            data[line][0] = ((Pays)listePays.get(line)).getId().toString();
            data[line][1] = ((Pays)listePays.get(line)).getIndicatif();
            data[line][2] = ((Pays)listePays.get(line)).getNomPays();
            data[line][3] = ((Pays)listePays.get(line)).getNationalite();
            data[line][4] = "";
            data[line][5] = "";
        }
        String title[] = {"ID","Indicatif", "Pays", "Nationalité","",""};
        JTable tableau = new JTable(data, title);
        tableau.setRowHeight(25);
        this.listePanel.setLayout(new BorderLayout());
        this.listePanel.add(new JScrollPane(tableau));
        
        this.body.add(this.bodyPanel);
    }
    
    class PaysSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            service.setFormData(new HashMap<String,String>());
            service.getFormData().put("indicatif", indicatifField.getText());
                service.getFormData().put("nomPays", nomPaysField.getText());
                service.getFormData().put("nationalite", nationaliteField.getText());
            try {
                service.save();
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(PaysPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            fenetre.getBarreDeMenu().paysListener.actionPerformed(ae);
        }
        
    }
    
}
