/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.ObjetModel;
import bf.menapln.service.Service;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.sql.SQLException;
import javax.swing.BoxLayout;

/**
 *
 * @author coulibaly
 */
public class Panneau extends ContainerObject{
    
    protected Color fond;
    protected LayoutManager panLayout;
    protected HeaderPanel head;
    protected ContainerObject foot;
    protected ContainerObject body;
    protected ContainerObject formControlPanel;
    protected Fenetre fenetre;
    
    protected Bouton save;
    protected Bouton reset;
    
    protected Service service;
    protected ObjetModel tableModel;
    
    public Panneau(Fenetre fenetre){
        
        this.fenetre = fenetre;
        this.setFond(Color.WHITE);
        this.panLayout = new BorderLayout();
        
        
        this.save = new Bouton("Enregistrer");
        //this.save.addActionListener(new SaveSessionListener());
        this.reset = new Bouton("Annuler");
        //this.reset.addActionListener(new ResetSessionListener());
        
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.setLayout(new BoxLayout(this.formControlPanel,BoxLayout.X_AXIS));
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.body = new ContainerObject();
        this.body.setBackground(this.fond);
        
        this.foot = new ContainerObject();
        this.foot.setBackground(Color.LIGHT_GRAY);
        this.setBackground(this.fond);
    }
    /*public void paintComponent(Graphics g){
        this.setBackground(this.fond);
        //g.fillRect(12, 12, 12, 12);
        -int x[] = {20, 30, 50, 60, 60, 50, 30, 20};
        int y[] = {30, 20, 20, 30, 50, 60, 60, 50};
        g.fillPolygon(x,y, 8);
    }*/
    public void initComposant() throws SQLException{
        this.head = new HeaderPanel(this);
        this.setLayout(this.panLayout);
        this.add(this.head,BorderLayout.NORTH);
        this.add(this.body,BorderLayout.CENTER);
        this.add(this.foot,BorderLayout.SOUTH);
    }
    public Color getFond() {
        return fond;
    }

    public void setFond(Color fond) {
        this.fond = fond;
    }

    public Fenetre getFenetre() {
        return fenetre;
    }

    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    public ObjetModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(ObjetModel tableModel) {
        this.tableModel = tableModel;
    }

    public ContainerObject getFormControlPanel() {
        return formControlPanel;
    }

    public void setFormControlPanel(ContainerObject formControlPanel) {
        this.formControlPanel = formControlPanel;
    }

    public ContainerObject getBody() {
        return body;
    }

    public void setBody(ContainerObject body) {
        this.body = body;
    }
    
    
    
}
