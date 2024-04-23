/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Session;
import bf.menapln.service.SessionService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class SessionPanel extends Panneau{
    public SessionPanel(Fenetre fenetre){
        super(fenetre);
        
        this.body.setLayout(new BorderLayout());
        this.foot.add(new JLabel("foot"));
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des sessions");
        this.head.getAddNewEntity().setText("Ajouter une session");
        this.head.getAddNewEntity().addActionListener(new AddSessionListener());
        List sessions = null;
        try {
            //Les titres des colonnes
            this.service = new SessionService();
            sessions = this.service.getAll("");
        } catch (SQLException ex) {
            Logger.getLogger(SessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[][] data = new String[sessions.size()][10];
        for(int line = 0 ; line < sessions.size() ; line++){
            data[line][0] = ((Session)sessions.get(line)).getId().toString();
            data[line][1] = ((Session)sessions.get(line)).getAnnee().toString();
            data[line][2] = ((Session)sessions.get(line)).getDateDebutInscription().toString();
            data[line][3] = ((Session)sessions.get(line)).getDateFindInscrition().toString();
            data[line][4] = ((Session)sessions.get(line)).getDateDebutSessNorm().toString();
            data[line][5] = ((Session)sessions.get(line)).getDateFinSessNorm().toString();
            data[line][6] = ((Session)sessions.get(line)).getMoyAdmission().toString();
            data[line][7] = ((Session)sessions.get(line)).getMoyRachat().toString();
            data[line][8] = ((Session)sessions.get(line)).getMoyAdmissible().toString();
        }
        String title[] = {"ID", "Année", "déb. insc.","fin insc.","Deb. sess","Fin sess","Moy adm.","Moy rachat", "Moy admissi"};
        JTable tableau = new JTable(data, title);
        tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
        this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        this.body.add(new JScrollPane(tableau));
    }
    
    class AddSessionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           SessionForm p = new SessionForm(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(SessionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
}
