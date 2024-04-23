/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Jury;
import bf.menapln.entity.UserSession;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.CandidatJuryTableModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CentreSecondaireService;
import bf.menapln.service.JuryService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author coulibaly
 */
public class CandidatPanelCentre extends Panneau{
    private JLabel centreSecondaireLabel;
    private JComboBox centreSecondaireField;
    private ContainerObject centreSecondairePanel;

    private JLabel juryLabel;
    private JComboBox juryField;
    private ContainerObject juryPanel;

    private ContainerObject westPanel;
    private ContainerObject listePanel;

    private JTextField filtreTextField;
    private TableRowSorter<DefaultTableModel> sortere;
    private CandidatJuryTableModel tableModel;
    public CandidatPanelCentre(Fenetre fenetre){
        super(fenetre);

        this.centreSecondaireLabel = new JLabel("Centre Secondaire");
        this.centreSecondaireField = new JComboBox();
        this.centreSecondaireField.addActionListener(new FiltreCentreListener());
        
        this.centreSecondairePanel = new ContainerObject();
        this.centreSecondairePanel.setLayout(new BoxLayout(this.centreSecondairePanel,BoxLayout.Y_AXIS));
        this.centreSecondaireField.setPreferredSize(new Dimension(200,30));
        this.centreSecondaireField.setMaximumSize(new Dimension(400,30));
        this.centreSecondairePanel.add(this.centreSecondaireLabel);
        this.centreSecondairePanel.add(this.centreSecondaireField);

        this.juryLabel = new JLabel("Jury");
        this.juryField = new JComboBox();

        this.juryPanel = new ContainerObject();
        this.juryField.addActionListener(new FiltreJuryListener());
        this.juryPanel.setLayout(new BoxLayout(this.juryPanel,BoxLayout.Y_AXIS));
        this.juryField.setPreferredSize(new Dimension(200,30));
        this.juryField.setMaximumSize(new Dimension(400,30));
        this.juryPanel.add(this.juryLabel);
        this.juryPanel.add(this.juryField);
        
        this.westPanel = new ContainerObject();
        this.westPanel.setLayout(new BoxLayout(this.westPanel,BoxLayout.X_AXIS));
        this.westPanel.add(this.juryPanel);
        this.westPanel.add(this.centreSecondairePanel);
        
        this.head.getSearchPanel().add(this.westPanel,BorderLayout.WEST);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());
    }
    
     
    public void initComposant() throws SQLException{
        super.initComposant();
        CentreSecondaireService centreSecondaireService = new CentreSecondaireService();

        List list = centreSecondaireService.getAll();
        this.centreSecondaireField.addItem(new CentreSecondaire());
        for(Object epreuve : list){
            this.centreSecondaireField.addItem(epreuve);
        }

        JuryService juryService = new JuryService();
        List lists = juryService.getAll("trdt");
       this.juryField.addItem(new Jury());
       for(Object jury : lists){
           this.juryField.addItem(jury);
       }
        this.service = new CandidatJuryService();
        tableModel = new CandidatJuryTableModel(((CandidatJuryService)this.service).getCandidatJury(this.fenetre.getUserSession().getJury()),this.fenetre);
      //  this.tableModel = new CandidatJuryTableModel(new ArrayList(),this.fenetre);
        JTable tableau = new JTable(tableModel);
        tableau.setRowHeight(30);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        this.head.initComposant();
        this.listePanel.add(new JScrollPane(tableau));
        this.body.setLayout(new BorderLayout());
        this.body.add(this.listePanel);
    }


    class FiltreCentreListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
                List data;
                CentreSecondaire s = (CentreSecondaire)centreSecondaireField.getSelectedItem();
                if(s.getId() != null){
                try {
                        data = ((CandidatJuryService)service).getCandidatCentre((CentreSecondaire)centreSecondaireField.getSelectedItem());
                        tableModel.setModelData(data);
                        tableModel.fireTableDataChanged();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch(NullPointerException e){

                }
            }
        }
    }

    class FiltreJuryListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
                List data;
                Jury s = (Jury)juryField.getSelectedItem();
                if(juryField.getSelectedItem() != null){
                try {
                    System.out.println("Avant Taille ");
                    data = ((CandidatJuryService)service).getCandidatJury((Jury)juryField.getSelectedItem());
                    System.out.println("Taille "+data.size());

                    tableModel.setModelData(data);
                    tableModel.fireTableDataChanged();
                   
                } catch (SQLException ex) {
                    Logger.getLogger(AnonymatPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch(NullPointerException e){

                }
            }
        }
    }
}

