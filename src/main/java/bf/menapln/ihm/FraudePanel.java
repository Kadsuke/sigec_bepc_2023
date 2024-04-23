/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

/**
 *
 * @author coulibaly
 */
public class FraudePanel extends ContainerObject{
    private ContainerObject listePanel;
    private ContainerObject formPanel;
    public FraudePanel(){
        //super(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        Object[][] data = { {"01","Traoré","Lamine","Jury 1", "D", "Maths","12/05/2023","7h:00","10h:00",new Boolean(false)}
        ,{"02","Traoré","Lamine","Jury 1", "D", "Maths","12/05/2023","7h:00","10h:00",new Boolean(false)},
        {"03","Traoré","Lamine","Jury 1", "D", "Maths","12/05/2023","7h:00","10h:00",new Boolean(false)},
        {"04","Traoré","Lamine","Jury 1", "D", "Maths","12/05/2023","7h:00","10h:00",new Boolean(false)},
        {"05","Traoré","Lamine","Jury 1", "D", "Maths","12/05/2023","7h:00","10h:00",new Boolean(false)}};
        //Les titres des colonnes
        String title[] = {"N°PV","Nom","Prénom","Jury", "Série", "Epreuve","Date compo","Début","Fin","Candidats impliqués"};
        
        TableModel model = new TableModel(data,title);
        JTable tableau = new JTable(model);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        this.listePanel.add(new JScrollPane(tableau));
        this.formPanel = new ContainerObject();
        
    }

    public ContainerObject getListePanel() {
        return listePanel;
    }

    public void setListePanel(ContainerObject listePanel) {
        this.listePanel = listePanel;
    }

    public ContainerObject getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(ContainerObject formPanel) {
        this.formPanel = formPanel;
    }
    
    
}
