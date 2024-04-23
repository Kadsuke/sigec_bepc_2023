/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.actionListener.CommuneStateListener;
import bf.menapln.actionListener.ProvinceStateListener;
import bf.menapln.actionListener.RegionStateListener;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.tableModel.CentreTableModel;
import bf.menapln.service.CentreService;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.StructureService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class CentrePanel extends Panneau{
    
    private JLabel regionLabel;
    private JComboBox regionField;
    private ContainerObject regionPanel;
    
    private JLabel provinceLabel;
    private JComboBox provinceField;
    private ContainerObject provincePanel;
    
    private JLabel communeLabel;
    private JComboBox communeField;
    private ContainerObject communePanel;
    
    private ContainerObject westPanel;
    private ContainerObject listePanel;
    
    public CentrePanel(Fenetre fenetre) {
        super(fenetre);
        
        this.communeLabel = new JLabel("Commune");
        this.communeField = new JComboBox();
        this.communePanel = new ContainerObject();
        //this.communeField.addActionListener(new CommuneStateListener(this.secteurCandidatField,this.etablissementField));
        this.communePanel.setLayout(new BoxLayout(this.communePanel,BoxLayout.Y_AXIS));
        this.communePanel.add(this.communeLabel);
        this.communePanel.add(this.communeField);
        
        this.provinceLabel = new JLabel("Province");
        this.provinceField = new JComboBox();
        this.provinceField.addActionListener(new ProvinceStateListener(this.communeField));
        this.provincePanel = new ContainerObject();
        this.provincePanel.setLayout(new BoxLayout(this.provincePanel,BoxLayout.Y_AXIS));
        this.provincePanel.add(this.provinceLabel);
        this.provincePanel.add(this.provinceField);
        
        this.regionLabel = new JLabel("Région");
        this.regionField = new JComboBox();
        this.regionField.addActionListener(new RegionStateListener(this.provinceField));
        this.regionPanel = new ContainerObject();
        this.regionPanel.setLayout(new BoxLayout(this.regionPanel,BoxLayout.Y_AXIS));
        this.regionPanel.add(this.regionLabel);
        this.regionPanel.add(this.regionField);
        
        this.westPanel = new ContainerObject();
        this.westPanel.setLayout(new BoxLayout(this.westPanel,BoxLayout.X_AXIS));
        this.westPanel.add(this.regionPanel);
        this.westPanel.add(this.provincePanel);
        this.westPanel.add(this.communePanel);
        
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
        
        
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getSearchPanel().add(this.westPanel,BorderLayout.WEST);

        LocaliteService localiteService = new LocaliteService();
        List regions = localiteService.getAll("region");
        for(Object region : regions){
            this.regionField.addItem((Localite)region);
        }
        
        CentreService centreService = new CentreService();
        List centres = centreService.getAll("");
    
        tableModel = new CentreTableModel(centres, this.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(30);
        this.head.initComposant();
        this.listePanel.add(new JScrollPane(tableau));
        this.body.setLayout(new BorderLayout());
        this.body.add(this.listePanel);
    }
    
}
