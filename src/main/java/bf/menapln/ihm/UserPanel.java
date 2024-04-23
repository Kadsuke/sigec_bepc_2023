/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.UserTableModel;
import bf.menapln.service.ProfilService;
import bf.menapln.service.UserService;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class UserPanel extends Panneau{
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    private JSplitPane bodyPanel;
    
    private JLabel profilLabel;
    private JComboBox profilField;
    private ContainerObject profilPanel;
    
    private JLabel personnelLabel;
    private JComboBox personnelField;
    private ContainerObject personnelPanel;
    
    private JLabel userNameLabel;
    private JTextField userNameField;
    private ContainerObject userNamePanel;
    
    private JLabel pwdLabel;
    private JPasswordField pwdField;
    private ContainerObject pwdPanel;
    
    
    public UserPanel(Fenetre fenetre) {
        super(fenetre);
        
        this.profilLabel = new JLabel("Profil");
        this.profilField = new JComboBox();
        this.profilField.setPreferredSize(new Dimension(200,30));
        this.profilField.setMinimumSize(new Dimension(200,30));
        this.profilField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.profilPanel = new ContainerObject();
        this.profilPanel.setLayout(new BoxLayout(this.profilPanel,BoxLayout.Y_AXIS));
        this.profilPanel.add(this.profilLabel);
        this.profilPanel.add(this.profilField);
        
        this.personnelLabel = new JLabel("Personnel");
        this.personnelField = new JComboBox();
        this.personnelField.setPreferredSize(new Dimension(200,30));
        this.personnelField.setMinimumSize(new Dimension(200,30));
        this.personnelField.setMaximumSize(new Dimension(this.fenetre.getWidth()/3,30));
        this.personnelPanel = new ContainerObject();
        this.personnelPanel.setLayout(new BoxLayout(this.personnelPanel,BoxLayout.Y_AXIS));
        this.personnelPanel.add(this.personnelLabel);
        this.personnelPanel.add(this.personnelField);
        
        this.userNameLabel = new JLabel("Pseudonyme");
        this.userNameField = new JTextField();
        this.userNameField.setPreferredSize(new Dimension(200,30));
        this.userNameField.setMinimumSize(new Dimension(200,30));
        this.userNameField.setMaximumSize(new Dimension(400,30));
        this.userNamePanel = new ContainerObject();
        this.userNamePanel.setLayout(new BoxLayout(this.userNamePanel,BoxLayout.Y_AXIS));
        this.userNamePanel.add(this.userNameLabel);
        this.userNamePanel.add(this.userNameField);
        
        this.pwdLabel = new JLabel("Mot de passe");
        this.pwdField = new JPasswordField();
        this.pwdField.setPreferredSize(new Dimension(200,30));
        this.pwdField.setMinimumSize(new Dimension(200,30));
        this.pwdField.setMaximumSize(new Dimension(400,30));
        this.pwdPanel = new ContainerObject();
        this.pwdPanel.setLayout(new BoxLayout(this.pwdPanel,BoxLayout.Y_AXIS));
        this.pwdPanel.add(this.pwdLabel);
        this.pwdPanel.add(this.pwdField);
        
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(new UserSaveListener());
        this.reset = new Bouton("Annuler");
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.profilPanel);
        this.formPanel.add(this.personnelPanel);
        this.formPanel.add(this.userNamePanel);
        this.formPanel.add(this.pwdPanel);
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
        
        ProfilService pService = new ProfilService();
        List profils = pService.getAll("");
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.profilField.getModel();
        model.addAll(profils);
        
        this.service = new UserService();
        this.tableModel = new UserTableModel(this.service.getAll(""),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        this.listePanel.setLayout(new BorderLayout());
        //this.listePanel.add(this.filterPanel);
        this.listePanel.add(new JScrollPane(tableau));
        //this.body.add(this.filterPanel,BorderLayout.NORTH);
        this.body.add(this.bodyPanel);
    }
    
    class UserSaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            service.setFormData(new HashMap());
            service.getFormData().put("profil", profilField.getSelectedItem());
            service.getFormData().put("username", userNameField.getText());
            service.getFormData().put("pwd", fenetre.encrypte(fenetre.charArrToSTring(pwdField.getPassword())));
            try {
                service.save();
            } catch (SQLException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EmptyDataException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
