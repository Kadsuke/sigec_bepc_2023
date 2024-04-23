/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Objet;
import bf.menapln.ihm.tableModel.TypeTableModel;
import bf.menapln.service.Service;
import bf.menapln.service.TypeService;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author coulibaly
 */
public class TypePanel extends Panneau{
    
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    private JSplitPane bodyPanel;
    
    private JLabel typoLabel;
    private JComboBox typoField;
    private JLabel typeLibelleLabel;
    private JTextField typeLibelleField;
    
    private JComboBox filtreField;
    
    JTable tableau;
    
    DefaultComboBoxModel modelJcomboBox;
    TypeTableModel tableModel;
    
    public TableauModelListener tableauModelListener = new TableauModelListener();
    public TypePanel(Fenetre fenetre) {
        super(fenetre);
        
        modelJcomboBox = new DefaultComboBoxModel();
        this.modelJcomboBox.addElement("Localité");
        this.modelJcomboBox.addElement("Statut étab.");
        this.modelJcomboBox.addElement("Type struct.");
        this.modelJcomboBox.addElement("Type épreuve");
        this.modelJcomboBox.addElement("Type candidat");
        this.modelJcomboBox.addElement("Type insc.");
        this.modelJcomboBox.addElement("Offre ens.");
        this.modelJcomboBox.addElement("Poste jury");
        this.modelJcomboBox.addElement("Type handicap");
        this.modelJcomboBox.addElement("Prescription hand.");
        this.modelJcomboBox.addElement("Concours rata.");
        
        this.filtreField = new JComboBox();
        this.filtreField.setModel(modelJcomboBox);
        this.filtreField.addActionListener(new FiltreStateListener());
        //this.head.getAddNewEntity().addActionListener(new AddLocaliteListener());
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
        this.typoLabel = new JLabel("type de :");
        this.typoField = new JComboBox();
        this.typoField.setPreferredSize(new Dimension(200,30));
        this.typoField.setMaximumSize(new Dimension(400,30));
        this.typoField.setModel(modelJcomboBox);
        /*this.typoField.addItem("Localité");
        this.typoField.addItem("Statut étab.");
        this.typoField.addItem("Type struct.");
        this.typoField.addItem("Type épreuve");
        this.typoField.addItem("Offre ens.");*/
        
        this.typeLibelleLabel = new JLabel("Libellé");
        this.typeLibelleField = new JTextField();
        this.typeLibelleField.setPreferredSize(new Dimension(200,30));
        this.typeLibelleField.setMaximumSize(new Dimension(400,30));
        
        this.save.addActionListener(new AddTypeListener());
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.typoLabel);
        this.formPanel.add(this.typoField);
        this.formPanel.add(this.typeLibelleLabel);
        this.formPanel.add(this.typeLibelleField);
        this.formPanel.add(this.formControlPanel);
        
        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
       
        
        this.foot.add(new JLabel("foot"));
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des types de données");
        this.head.getAddNewEntity().setText("Ajouter une localité");
        
        this.head.getSearchPanel().add(this.filtreField,BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        
        tableModel = new TypeTableModel(new ArrayList<Objet>(), this.fenetre);
        tableau = new JTable(tableModel);
        this.listePanel.add(new JScrollPane(tableau));
        
        this.body.add(this.bodyPanel);
    }
    
    class AddTypeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try {
                Service service = new TypeService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("typoField", typoField.getSelectedItem());
                service.getFormData().put("typeLibelle", typeLibelleField.getText());
                service.save();
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class FiltreStateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try {
                Service service = new TypeService();
                List types = service.getAll(filtreField.getSelectedItem().toString());
                tableModel.setModelData(types);
                //tableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class TableauModelListener implements TableModelListener{

        @Override
        public void tableChanged(TableModelEvent tme) {
            System.out.println("change");
        }
        
    }
    
}
