/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Jury;
import bf.menapln.entity.Session;
import bf.menapln.entity.User;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.JuryService;
import bf.menapln.service.SessionService;
import bf.menapln.service.UserService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class ConnexionForm extends ObjetFenetre implements ActionListener{
    private JLabel usernameLabel;
    private JTextField usernameField;
    private ContainerObject usernamePanel;
    
    private JLabel pwdLabel;
    private JPasswordField pwdField;
    private ContainerObject pwdPanel;
    
    private Bouton save;
    private ContainerObject formControlPanel;
    
    public ConnexionForm() {
        this.usernameLabel = new JLabel("Identifiant");
        this.usernameLabel.setForeground(Color.RED);
        this.usernameField = new JTextField();
        this.usernameField.setMinimumSize(new Dimension(200,30));
        this.usernameField.setPreferredSize(new Dimension(300,30));
        this.usernameField.setMaximumSize(new Dimension(500,30));
        this.usernamePanel = new ContainerObject();
        this.usernamePanel.setLayout(new BoxLayout(this.usernamePanel,BoxLayout.Y_AXIS));
        this.usernamePanel.setBackground(Color.WHITE);
        this.usernamePanel.add(this.usernameLabel);
        this.usernamePanel.add(this.usernameField);
        
        this.pwdLabel = new JLabel("Mot de passe");
        this.pwdLabel.setForeground(Color.RED);
        this.pwdField = new JPasswordField();
        this.pwdField.setMinimumSize(new Dimension(200,30));
        this.pwdField.setPreferredSize(new Dimension(300,30));
        this.pwdField.setMaximumSize(new Dimension(500,30));
        this.pwdPanel = new ContainerObject();
        this.pwdPanel.setLayout(new BoxLayout(this.pwdPanel,BoxLayout.Y_AXIS));
        this.pwdPanel.setBackground(Color.WHITE);
        this.pwdPanel.add(this.pwdLabel);
        this.pwdPanel.add(this.pwdField);
        
        this.save = new Bouton("Se connecter");
        this.save.addActionListener(this);
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.setBackground(Color.WHITE);
        this.formControlPanel.add(this.save);
        
        ContainerObject bodyPanel = new ContainerObject();
        bodyPanel.setLayout(new BoxLayout(bodyPanel,BoxLayout.Y_AXIS));
        bodyPanel.setBorder(BorderFactory.createTitledBorder("Authentification"));
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.add(this.usernamePanel);
        bodyPanel.add(this.pwdPanel);
        bodyPanel.add(this.formControlPanel);
        
        ContainerObject body = new ContainerObject();
        body.add(bodyPanel);
        body.setBackground(Color.WHITE);
        this.setContentPane(body);
        
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(600, 400);
        
    }
    
    
    public void initComposant() throws SQLException{
        //this.setTitle(this.title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setContentPane(this.container);
        //this.setJMenuBar(this.barreDeMenu);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getRootPane().setDefaultButton(save);
        this.setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            UserService service = new UserService();
            service.setFormData(new HashMap());
            service.getFormData().put("username", this.usernameField.getText());
            service.getFormData().put("pwd", this.encrypte(this.charArrToSTring(this.pwdField.getPassword())));
            User user = (User)((UserService)service).connect();
            if(user != null){
                SessionService sService = new SessionService();
                JuryService jService = new JuryService();
                
                Fenetre fenetre = new Fenetre();
                fenetre.setUserSession(new UserSession());
                fenetre.getUserSession().setSession((Session) sService.getActiveSession());
                fenetre.getUserSession().setJury((Jury) jService.getById(user.getJury().getId()));
                fenetre.getUserSession().setTourComposition(fenetre.getUserSession().getJury().getEtape());
                fenetre.getUserSession().setUser(user);
                fenetre.initComponent();
                this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                
            }
            System.out.println(user);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
