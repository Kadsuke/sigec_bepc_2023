/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Localite;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.TypeService;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class LocalitePanel extends Panneau{
    private JSplitPane bodyPanel;
    
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    private ContainerObject formControlPanel;
    
    private JLabel typeLocaliteLabel;
    private JComboBox typeLocaliteField;
    private JLabel parentLocaliteLabel;
    private JComboBox parentLocaliteField;
    private JLabel codeLocaliteLabel;
    private JTextField codeLocaliteField;
    private JLabel nomLocaliteLabel;
    private JTextField nomLocaliteField;
    
    public LocalitePanel(Fenetre fenetre) throws SQLException {
        super(fenetre);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
        TypeService type = new TypeService();
        List typesLocalite = type.getAll("Localité");
        
        this.typeLocaliteLabel = new JLabel("Type de localité");
        this.typeLocaliteLabel.setPreferredSize(new Dimension(200,30));
        this.typeLocaliteField = new JComboBox();
        this.typeLocaliteField.setPreferredSize(new Dimension(200,30));
        this.typeLocaliteField.setMaximumSize(new Dimension(400,30));
        this.typeLocaliteField.addItem("");
        if(!typesLocalite.isEmpty()){
            for(int i = 0 ; i < typesLocalite.size() ; i++){
                this.typeLocaliteField.addItem((Type)typesLocalite.get(i));
            }
        }
        
        this.parentLocaliteLabel = new JLabel("Localite parent");
        this.parentLocaliteLabel.setPreferredSize(new Dimension(200,30));
        this.parentLocaliteField = new JComboBox();
        this.parentLocaliteField.setPreferredSize(new Dimension(200,30));
        this.parentLocaliteField.setMaximumSize(new Dimension(400,30));
        
        this.codeLocaliteLabel = new JLabel("Code localité");
        this.codeLocaliteLabel.setPreferredSize(new Dimension(200,30));
        this.codeLocaliteField = new JTextField();
        this.codeLocaliteField.setPreferredSize(new Dimension(200,30));
        this.codeLocaliteField.setMaximumSize(new Dimension(400,30));
        //this.codeLocaliteField.setMinimumSize(new Dimension(200,30));
        
        this.nomLocaliteLabel = new JLabel("Nom localité");
        this.nomLocaliteLabel.setPreferredSize(new Dimension(200,30));
        this.nomLocaliteField = new JTextField();
        this.nomLocaliteField.setPreferredSize(new Dimension(200,30));
        this.nomLocaliteField.setMaximumSize(new Dimension(400,30));
        
        this.save = new Bouton("Enregistrer");
        this.save.setPreferredSize(new Dimension(100,30));
        this.save.setMaximumSize(new Dimension(200,30));
        this.save.addActionListener(new AddLocaliteListener());
        this.reset = new Bouton("Annuler");
        this.reset.setPreferredSize(new Dimension(100,30));
        this.reset.setMaximumSize(new Dimension(200,30));
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.setLayout(new BoxLayout(this.formControlPanel,BoxLayout.X_AXIS));
        this.formControlPanel.setMaximumSize(new Dimension(400,40));
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.typeLocaliteLabel);
        this.formPanel.add(this.typeLocaliteField);
        this.formPanel.add(this.parentLocaliteLabel);
        this.formPanel.add(this.parentLocaliteField);
        this.formPanel.add(this.codeLocaliteLabel);
        this.formPanel.add(this.codeLocaliteField);
        this.formPanel.add(this.nomLocaliteLabel);
        this.formPanel.add(this.nomLocaliteField);
        this.formPanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        
        
        this.body.setLayout(new BorderLayout());
        
        
        this.foot.add(new JLabel("foot"));
    }
    
    @Override
    public void initComposant() throws SQLException {
        super.initComposant();
        this.head.getTitle().setText("Gestion des Localités");
        this.head.getAddNewEntity().setText("Ajouter une localité");
        //this.head.getAddNewEntity().addActionListener(new AddLocaliteListener());
        
        List localites = null;
        if(this.service == null){
            try {
                this.service = new LocaliteService();
            } catch (SQLException ex) {
                Logger.getLogger(LocalitePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            localites = this.service.getAll("");
        } catch (SQLException ex) {
            Logger.getLogger(LocalitePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.parentLocaliteField.addItem(new Localite());
        Object[][] data = new String[localites.size()][7];
        for(int line = 0 ; line < localites.size(); line++){
            //System.out.println(loc);
            String locparent = (((Localite)localites.get(line)).getParentLocalite().getId() == null)? "" : ((Localite)localites.get(line)).getParentLocalite().getId().toString();
            data[line][0] = ((Localite)localites.get(line)).getId().toString();
            data[line][1] = ((Localite)localites.get(line)).getTypeLocalite().getId().toString();
            data[line][2] = locparent;
            data[line][3] = ((Localite)localites.get(line)).getCodeLocalite();
            data[line][4] = ((Localite)localites.get(line)).getNomLoclite();
            data[line][5] = "";
            data[line][6] = "";
            
            this.parentLocaliteField.addItem(localites.get(line));
        }
        String title[] = {"ID","Type localité", "Localite parent", "Code localité","Libellé localité","",""};
        JTable tableau = new JTable(data, title);
        tableau.setRowHeight(25);
        tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
        this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        this.listePanel.add(new JScrollPane(tableau));
        
        this.body.add(this.bodyPanel);
    }
    
    class AddLocaliteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           try {
                //System.out.println(((Localite)parentLocaliteField.getSelectedItem()).getId() == null);
                String parentloc = (((Localite)parentLocaliteField.getSelectedItem()).getId() != null)?((Localite)parentLocaliteField.getSelectedItem()).getId().toString():"0";
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("typeLocalite", ((Type)typeLocaliteField.getSelectedItem()).getId().toString());
                service.getFormData().put("parentLocalite", parentloc);
                service.getFormData().put("codeLocalite", codeLocaliteField.getText());
                service.getFormData().put("nomLocalite", nomLocaliteField.getText());
                service.save();
                fenetre.getBarreDeMenu().localiteListener.actionPerformed(ae);
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
