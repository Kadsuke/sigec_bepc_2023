/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Etablissement;
import bf.menapln.service.StructureService;
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
public class EtablissementPanel extends Panneau{
    public EtablissementPanel(Fenetre fenetre){
        super(fenetre);
        this.body.setLayout(new BorderLayout());
        this.foot.add(new JLabel("foot"));
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        List structures = null;
        try {
            this.service = new StructureService();
            structures = this.service.getAll("");
            System.out.println(structures.size());
        } catch (SQLException ex) {
            Logger.getLogger(SessionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[][] data = new String[structures.size()][8];
        for(int line = 0 ; line < structures.size() ; line++){
            data[line][0] = ((Etablissement)structures.get(line)).getId().toString();
            data[line][1] = ((Etablissement)structures.get(line)).getNomStructure();
            data[line][2] = ((Etablissement)structures.get(line)).getAcronymeStructure();
            data[line][3] = "";//((Etablissement)Etablissement.get(line)).getDateFindInscrition().toString();
            data[line][4] = "";//((Session)sessions.get(line)).getDateDebutSessNorm().toString();
            data[line][5] = "";//((Session)sessions.get(line)).getDateFinSessNorm().toString();
            data[line][6] = "";//((Session)sessions.get(line)).getDateDebutSessRemp().toString();
            data[line][7] = "";//((Session)sessions.get(line)).getDateFinSessRemp().toString();
            //data[line][8] = ((Session)sessions.get(line)).getMoyAdmission().toString();
            //data[line][9] = ((Session)sessions.get(line)).getMoyRachat().toString();
        }
        
        
        //Object[][] data = { {"Lycée Professionel National Maurice YAMEOGO", "LPNMY", "Public","Professionel","1000","",""}};
        //Les titres des colonnes
        String title[] = {"ID","Etablissement", "Acronyme", "Type étab.","Offre ens.","Capacité","",""};
        JTable tableau = new JTable(data, title);
        tableau.setRowHeight(25);
        tableau.setPreferredSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
        this.head.getTitle().setText("Gestion des établissements");
        this.head.getAddNewEntity().setText("Ajouter établissement");
        this.head.getAddNewEntity().addActionListener(new AddEtablissementListener());
        this.head.getSearchPanel().add(this.head.getSearch(),BorderLayout.WEST);
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();
        this.body.add(new JScrollPane(tableau));
    }
    
    class AddEtablissementListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           EtablissementForm p = new EtablissementForm(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(EtablissementPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
