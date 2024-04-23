/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class HeaderPanel extends ContainerObject{
    private JLabel title;
    private ContainerObject titlePanel;
    private ContainerObject searchPanel;
    protected JPanel ongletPanel;
    private Panneau containerparent;
    private JTextField search;
    private Bouton addNewEntity;

    public HeaderPanel(Panneau container) {
        this.containerparent = container;
        this.title = new JLabel(this.containerparent.getFenetre().getUserSession().getJury().getJuryLibelle());
        this.title.setForeground(Color.red);
        this.titlePanel = new ContainerObject();
        this.titlePanel.setPreferredSize(new Dimension(this.containerparent.getFenetre().getWidth(),30));
        this.titlePanel.setBackground(this.containerparent.getFond());
        
        this.search = new JTextField("Rechercher");
        this.search.setPreferredSize(new Dimension(200,20));
        this.addNewEntity = new Bouton("");
        
        this.searchPanel = new ContainerObject();
        //this.searchPanel.setPreferredSize(new Dimension(this.containerparent.getFenetre().getWidth(),40));
        this.searchPanel.setLayout(new BorderLayout());
        this.searchPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setLayout(new BorderLayout());
    }
    
    public void initComposant(){
        this.titlePanel.add(this.title);
        this.add(this.titlePanel,BorderLayout.NORTH);
        this.add(this.searchPanel);
        this.setBackground(this.containerparent.getFond());
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }
    
    public ContainerObject getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(ContainerObject titlePanel) {
        this.titlePanel = titlePanel;
    }

    public ContainerObject getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(ContainerObject searchPanel) {
        this.searchPanel = searchPanel;
    }

    public JTextField getSearch() {
        return search;
    }

    public void setSearch(JTextField search) {
        this.search = search;
    }

    public Bouton getAddNewEntity() {
        return addNewEntity;
    }

    public void setAddNewEntity(Bouton addNewEntity) {
        this.addNewEntity = addNewEntity;
    }

    public JPanel getOngletPanel() {
        return ongletPanel;
    }

    public void setOngletPanel(JPanel ongletPanel) {
        this.ongletPanel = ongletPanel;
    }
    
}
