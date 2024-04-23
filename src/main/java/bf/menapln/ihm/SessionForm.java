/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.SessionService;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class SessionForm extends Panneau{
    private ContainerObject anneePanel;
    private ContainerObject datePanel;
    private ContainerObject tourPanel;
    private ContainerObject controlPanel;
    private ContainerObject formPanel;
    
    private JTextField anneeField;
    private DatePicker dateDebutInscField;
    private DatePicker dateFinInscField;
    private DatePicker dateDebutSessNormField;
    private DatePicker dateFinSessNormField;
    private JTextField moyAdmissionField;
    private JTextField moyRachatField;
    private JTextField moyAdmissibleField;
    
    public SaveSessionListener saveSessionListener = new SaveSessionListener();
    
    public SessionForm(Fenetre fenetre) {
        super(fenetre);
       
        
        JLabel anneeLabel = new JLabel("Année");
        this.anneeField = new JTextField();
        this.anneeField.setPreferredSize(new Dimension(500,30));
        
        this.anneePanel = new ContainerObject();
        this.anneePanel.setLayout(new BoxLayout(this.anneePanel,BoxLayout.Y_AXIS));
        this.anneePanel.add(anneeLabel);
        this.anneePanel.add(anneeField);
        
        JLabel dateDebutInscLabel = new JLabel("Date de début");
        dateDebutInscField = new DatePicker();
        dateDebutInscField.setPreferredSize(new Dimension(150,30));
        
        ContainerObject dateDebutInscPanel = new ContainerObject();
        dateDebutInscPanel.setLayout(new BoxLayout(dateDebutInscPanel,BoxLayout.Y_AXIS));
        dateDebutInscPanel.add(dateDebutInscLabel);
        dateDebutInscPanel.add(dateDebutInscField);
        
        JLabel dateFinInscLabel = new JLabel("Date de fin");
        dateFinInscField = new DatePicker();
        dateFinInscField.setPreferredSize(new Dimension(150,30));
        
        ContainerObject dateFinInscPanel = new ContainerObject();
        dateFinInscPanel.setLayout(new BoxLayout(dateFinInscPanel,BoxLayout.Y_AXIS));
        dateFinInscPanel.add(dateFinInscLabel);
        dateFinInscPanel.add(dateFinInscField);
        
        ContainerObject dateInscPanel = new ContainerObject();
        dateInscPanel.setLayout(new BoxLayout(dateInscPanel,BoxLayout.Y_AXIS));
        dateInscPanel.setBorder(BorderFactory.createTitledBorder("Inscription"));
        dateInscPanel.add(dateDebutInscPanel);
        dateInscPanel.add(dateFinInscPanel);
        
        JLabel dateDebutSessNormLabel = new JLabel("Date début");
        dateDebutSessNormField = new DatePicker();
        dateDebutSessNormField.setPreferredSize(new Dimension(150,30));
        
        ContainerObject dateDebutSessNormPanel = new ContainerObject();
        dateDebutSessNormPanel.setLayout(new BoxLayout(dateDebutSessNormPanel,BoxLayout.Y_AXIS));
        dateDebutSessNormPanel.add(dateDebutSessNormLabel);
        dateDebutSessNormPanel.add(dateDebutSessNormField);
        
        JLabel dateFinSessNormLabel = new JLabel("Date fin");
        dateFinSessNormField = new DatePicker();
        dateFinSessNormField.setPreferredSize(new Dimension(150,30));
        
        ContainerObject dateFinSessNormPanel = new ContainerObject();
        dateFinSessNormPanel.setLayout(new BoxLayout(dateFinSessNormPanel,BoxLayout.Y_AXIS));
        dateFinSessNormPanel.add(dateFinSessNormLabel);
        dateFinSessNormPanel.add(dateFinSessNormField);
        
        ContainerObject dateSessNormPanel = new ContainerObject();
        dateSessNormPanel.setLayout(new BoxLayout(dateSessNormPanel,BoxLayout.Y_AXIS));
        dateSessNormPanel.setBorder(BorderFactory.createTitledBorder("Session"));
        dateSessNormPanel.add(dateDebutSessNormPanel);
        dateSessNormPanel.add(dateFinSessNormPanel);
        
        JLabel moyAdmissionLabel = new JLabel("Moyenne d'admission");
        moyAdmissionField = new JTextField();
        moyAdmissionField.setPreferredSize(new Dimension(100,30));
        
        ContainerObject moyAdmissionPanel = new ContainerObject();
        moyAdmissionPanel.setLayout(new BoxLayout(moyAdmissionPanel,BoxLayout.Y_AXIS));
        moyAdmissionPanel.add(moyAdmissionLabel);
        moyAdmissionPanel.add(moyAdmissionField);


        JLabel moyRachatLabel = new JLabel("Moyenne de rachat");
        moyRachatField = new JTextField();
        moyRachatField.setPreferredSize(new Dimension(100,30));
        
        ContainerObject moyRachatPanel = new ContainerObject();
        moyRachatPanel.setLayout(new BoxLayout(moyRachatPanel,BoxLayout.Y_AXIS));
        moyRachatPanel.add(moyRachatLabel);
        moyRachatPanel.add(moyRachatField);


        JLabel moyAdmissibiliteLabel = new JLabel("Moyenne d' Admmissibilite");
        moyAdmissibleField = new JTextField();
        moyAdmissibleField.setPreferredSize(new Dimension(100,30));
        
        ContainerObject moyAdmissiblePanel = new ContainerObject();
        moyAdmissiblePanel.setLayout(new BoxLayout(moyAdmissiblePanel,BoxLayout.Y_AXIS));
        moyAdmissiblePanel.add(moyAdmissibiliteLabel);
        moyAdmissiblePanel.add(moyAdmissibleField);
        
        ContainerObject premierTourPanel = new ContainerObject();
        premierTourPanel.setLayout(new BoxLayout(premierTourPanel,BoxLayout.Y_AXIS));
        premierTourPanel.setBorder(BorderFactory.createTitledBorder("Premier et Second tour"));
        premierTourPanel.add(moyAdmissionPanel);
        premierTourPanel.add(moyRachatPanel);
        premierTourPanel.add(moyAdmissiblePanel);

        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(this.saveSessionListener);
        this.reset = new Bouton("Annuler");
        this.reset.addActionListener(new ResetSessionListener());
        
        this.datePanel = new ContainerObject();
        this.datePanel.setLayout(new BoxLayout(this.datePanel,BoxLayout.X_AXIS));
        this.datePanel.add(dateInscPanel);
        this.datePanel.add(dateSessNormPanel);
        this.tourPanel = new ContainerObject();
        this.tourPanel.add(premierTourPanel);        
        
        this.controlPanel = new ContainerObject();
        this.controlPanel.add(this.save);
        this.controlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(anneePanel);
        this.formPanel.add(this.datePanel);
        this.formPanel.add(this.tourPanel);
        this.formPanel.add(this.controlPanel); 
        this.body.add(this.formPanel);
    }
    
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Gestion des sessions");
        this.head.getAddNewEntity().setText("Retour à la liste");
        this.head.getAddNewEntity().addActionListener(new SessionListener());
        this.head.getSearchPanel().add(this.head.getAddNewEntity(),BorderLayout.EAST);
        this.head.initComposant();    
    }
    
    class SessionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           SessionPanel p = new SessionPanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(SessionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    class SaveSessionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae){
            try {
                SessionService service = new SessionService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("annee", anneeField.getText());
                service.getFormData().put("dateDebutInsc", dateDebutInscField.getDate());
                service.getFormData().put("dateFinInsc", dateFinInscField.getDate());
                service.getFormData().put("dateDebutSessNorm", dateDebutSessNormField.getDate());
                service.getFormData().put("dateFinSessNorm", dateFinSessNormField.getDate());
                service.getFormData().put("moyAdmission", moyAdmissionField.getText());
                service.getFormData().put("moyRachat", moyRachatField.getText());
                service.getFormData().put("moyAdmissible", moyAdmissibleField.getText());
                service.save();
                //fenetre.getBarreDeMenu().sessionListener.actionPerformed(ae);
                //service.getAll();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                //Logger.getLogger(SessionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class ResetSessionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
        }
        
    }
    
}
