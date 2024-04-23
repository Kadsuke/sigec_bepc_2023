/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Jury;
import bf.menapln.entity.Session;
//import bf.menapln.entity.Type;
import bf.menapln.entity.UserSession;
import bf.menapln.service.JuryService;
import bf.menapln.service.SessionService;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author coulibaly
 */
public class Fenetre extends ObjetFenetre{
    private int width;
    private int height;
    private String title;
    private Panneau container;
    private BarreDeMenu barreDeMenu;
    
    private Jury jury;
    private UserSession userSession;
    
    public Fenetre() throws SQLException{
        //this.jury = new Jury();
        //this.jury.setJuryLibelle("Jury 1");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setWidth(screenSize.width-100);
        this.setHeight(screenSize.height-100);
        this.setContainer(new Panneau(this));
    }
    
    public void initComponent() throws SQLException{
        
        
        this.getContainer().initComposant();
        this.setBarreDeMenu(new BarreDeMenu(this));
        this.getBarreDeMenu().initComponent();
        this.setSize(this.width, this.height);
        this.setTitle(this.title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.container);
        this.setJMenuBar(this.barreDeMenu);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Panneau getContainer() {
        return this.container;
    }

    public void setContainer(Panneau container) {
        this.container = container;
    }

    public BarreDeMenu getBarreDeMenu() {
        return barreDeMenu;
    }

    public void setBarreDeMenu(BarreDeMenu barreDeMenu) {
        this.barreDeMenu = barreDeMenu;
    }

    public Jury getJury() {
        return jury;
    }

    public void setJury(Jury jury) {
        this.jury = jury;
    }

    public UserSession getUserSession() {
        return this.userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
