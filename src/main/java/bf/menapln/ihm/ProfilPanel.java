/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Menu;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Profil;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.MenuSelectionTableModel;
import bf.menapln.ihm.tableModel.MenuTableModel;
import bf.menapln.service.MenuService;
import bf.menapln.service.ProfilService;
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

/**
 *
 * @author coulibaly
 */
public class ProfilPanel extends Panneau{
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    private JSplitPane bodyPanel;
    
    private JComboBox profilFilter;
    private ContainerObject filterPanel;
    
    private JLabel profilLabel;
    private JTextField profilField;
    private ContainerObject profilPanel;
    
    private MenuSelectionTableModel menuModel;
    private JTable menuTableau;
    private ContainerObject menuPanel;
    
    private MenuService menuService;
    
    public ProfilPanel(Fenetre fenetre) throws SQLException {
        super(fenetre);
       
        this.profilLabel = new JLabel("Profil");
        this.profilField = new JTextField();
        this.profilField.setPreferredSize(new Dimension(200,30));
        this.profilField.setMinimumSize(new Dimension(200,30));
        this.profilField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.profilPanel = new ContainerObject();
        this.profilPanel.setLayout(new BoxLayout(this.profilPanel,BoxLayout.Y_AXIS));
        this.profilPanel.add(this.profilLabel);
        this.profilPanel.add(this.profilField);
        
        menuService = new MenuService();
        this.menuModel = new MenuSelectionTableModel(menuService.getAll(""),this.fenetre);
        this.menuTableau = new JTable(this.menuModel);
        this.menuTableau.getColumnModel().getColumn(0).setMaxWidth(20);
        this.menuTableau.setRowHeight(25);
        //serieTableau.setEnabled(false);
        this.menuPanel = new ContainerObject();
        this.menuPanel.setLayout(new BorderLayout());
        this.menuPanel.add(new JScrollPane(this.menuTableau));
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new ProfilSaveListener());
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.profilPanel);
        this.formPanel.add(this.menuPanel);
        this.formPanel.add(this.formControlPanel);
        
        this.profilFilter = new JComboBox();
        this.profilFilter.setPreferredSize(new Dimension(300,30));
        this.profilFilter.addActionListener(new ProfilFilterListener());
        this.filterPanel = new ContainerObject();
        this.filterPanel.add(this.profilFilter);
        
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
        this.head.getSearchPanel().add(this.filterPanel,BorderLayout.WEST);
        this.head.initComposant();
        this.service = new ProfilService();
        List profils = this.service.getAll("");
        DefaultComboBoxModel model = (DefaultComboBoxModel) profilFilter.getModel();
        model.addAll(profils);
        this.tableModel = new MenuTableModel(new ArrayList(),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        this.listePanel.setLayout(new BorderLayout());
        this.listePanel.add(new JScrollPane(tableau));
        this.body.add(this.bodyPanel);
    }
    
    public void resetFormData(){
        this.profilField.setText(null);
        for(Objet menu : this.menuModel.getModelData()){
            menu.setChecked(false);
        }
        this.menuModel.fireTableDataChanged();
    }
    
    class ProfilFilterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("filtre");
            try {
                tableModel.setModelData(((Profil)menuService.getByProfil((Objet) profilFilter.getSelectedItem())).getMenus());
                tableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                Logger.getLogger(ProfilPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class ProfilSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Profil p = new Profil();
            p.setProfilLibelle(profilField.getText());
            p.setMenus(new ArrayList());
            for(Objet objet : menuModel.getModelData()){
                Menu menu = (Menu)objet;
                if(menu.isChecked()){
                    p.addMenu(menu);
                }
            }
            service.setFormData(new HashMap());
            service.getFormData().put("profil", p);
            try {
                service.save();
                resetFormData();
                DefaultComboBoxModel model = (DefaultComboBoxModel) profilFilter.getModel();
                model.addElement(p);
            } catch (SQLException ex) {
                Logger.getLogger(ProfilPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EmptyDataException ex) {
                Logger.getLogger(ProfilPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
