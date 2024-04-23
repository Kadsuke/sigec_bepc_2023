/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Menu;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.MenuTableModel;
import bf.menapln.service.MenuService;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
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
public class MenuPanel extends Panneau{
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    private JSplitPane bodyPanel;
    
    private JLabel menuLabel;
    private JTextField menuField;
    private ContainerObject menuPanel;
    
    private JLabel menuParentLabel;
    private JComboBox menuParentField;
    private ContainerObject menuParentPanel;
    
    public MenuPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.menuParentLabel = new JLabel("Menu parent");
        this.menuParentField = new JComboBox();
        this.menuParentField.setPreferredSize(new Dimension(200,30));
        this.menuParentField.setMinimumSize(new Dimension(200,30));
        this.menuParentField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.menuParentPanel = new ContainerObject();
        this.menuParentPanel.setLayout(new BoxLayout(this.menuParentPanel,BoxLayout.Y_AXIS));
        this.menuParentPanel.add(this.menuParentLabel);
        this.menuParentPanel.add(this.menuParentField);
        
        this.menuLabel = new JLabel("Menu");
        this.menuField = new JTextField();
        this.menuField.setPreferredSize(new Dimension(200,30));
        this.menuField.setMinimumSize(new Dimension(200,30));
        this.menuField.setMaximumSize(new Dimension(400,30));
        this.menuPanel = new ContainerObject();
        this.menuPanel.setLayout(new BoxLayout(this.menuPanel,BoxLayout.Y_AXIS));
        this.menuPanel.add(this.menuLabel);
        this.menuPanel.add(this.menuField);
        
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new MenuSaveListener());
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.menuParentPanel);
        this.formPanel.add(this.menuPanel);
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
        this.head.getTitle().setText("Gestion des menus");
        
        service = new MenuService();
        this.tableModel = new MenuTableModel(service.getAll(""),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        this.listePanel.setLayout(new BorderLayout());
        this.listePanel.add(new JScrollPane(tableau));
        this.body.add(this.bodyPanel);
    }
    
    public void resetFormData(){
        this.menuField.setText(null);
    }
    
    class MenuSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                service.setFormData(new HashMap());
                service.getFormData().put("menuLibelle", menuField.getText());
                service.getFormData().put("menuParent_id", menuParentField.getSelectedItem());
                Menu menu = (Menu) service.save();
                tableModel.addRow(menu);
                tableModel.fireTableDataChanged();
                resetFormData();
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
