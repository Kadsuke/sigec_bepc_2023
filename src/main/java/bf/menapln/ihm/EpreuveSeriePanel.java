/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Type;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.service.EpreuveSerieService;
import bf.menapln.service.EpreuveService;
import bf.menapln.service.SerieService;
import bf.menapln.service.SessionService;
import bf.menapln.service.TypeService;
import com.github.lgooddatepicker.components.TimePicker;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author coulibaly
 */
public class EpreuveSeriePanel extends Panneau{
    private ContainerObject formPanel;
    
    private JLabel sessionLabel;
    private JComboBox sessionField;
    
    private JLabel serieLabel;
    private JComboBox serieField;
    
    private JLabel epreuveLabel;
    private JComboBox epreuveField;
    
    private JLabel typeEpreuveLabel;
    private JComboBox typeEpreuveField;
    
    private JLabel coefficientLabel;
    private JTextField coefficientField;
    
    private JLabel dureeLabel;
    private TimePicker dureeField;
    
    private JLabel isComposeSecTourLabel;
    private JComboBox isComposeSecTourField;

    private JLabel isComposePreTourLabel;
    private JComboBox isComposePreTourField;

    private JLabel isRachetableLabel;
    private JComboBox isRachetableField;
    
    private JLabel isTypeFrancaisLabel;
    private JComboBox isTypeFrancaisField;
    

    private ContainerObject line1;
    private ContainerObject line2;
    
    public EpreuveSerieListener epreuveSerieListener = new EpreuveSerieListener();
    public EpreuveSeriePanel(Fenetre fenetre) {
        super(fenetre);
        
        this.sessionLabel = new JLabel("Session");
        this.sessionField = new JComboBox();
        this.sessionField.setPreferredSize(new Dimension(200,30));
        this.sessionField.setMaximumSize(new Dimension(300,30));
        ContainerObject sessionPanel = new ContainerObject();
        sessionPanel.setLayout(new BoxLayout(sessionPanel,BoxLayout.Y_AXIS));
        sessionPanel.add(this.sessionLabel);
        sessionPanel.add(this.sessionField);
        
        this.serieLabel = new JLabel("Option");
        this.serieField = new JComboBox();
        this.serieField.setPreferredSize(new Dimension(200,30));
        this.serieField.setMaximumSize(new Dimension(300,30));
        ContainerObject seriePanel = new ContainerObject();
        seriePanel.setLayout(new BoxLayout(seriePanel,BoxLayout.Y_AXIS));
        seriePanel.add(this.serieLabel);
        seriePanel.add(this.serieField);
        
        this.epreuveLabel = new JLabel("Epreuve");
        this.epreuveField = new JComboBox();
        this.epreuveField.setPreferredSize(new Dimension(200,30));
        this.epreuveField.setMaximumSize(new Dimension(300,30));
        ContainerObject epreuvePanel = new ContainerObject();
        epreuvePanel.setLayout(new BoxLayout(epreuvePanel,BoxLayout.Y_AXIS));
        epreuvePanel.add(this.epreuveLabel);
        epreuvePanel.add(this.epreuveField);
        
        this.typeEpreuveLabel = new JLabel("Type épreuve");
        this.typeEpreuveField = new JComboBox();
        this.typeEpreuveField.setPreferredSize(new Dimension(200,30));
        this.typeEpreuveField.setMaximumSize(new Dimension(300,30));
        ContainerObject typeEpreuvePanel = new ContainerObject();
        typeEpreuvePanel.setLayout(new BoxLayout(typeEpreuvePanel,BoxLayout.Y_AXIS));
        typeEpreuvePanel.add(this.typeEpreuveLabel);
        typeEpreuvePanel.add(this.typeEpreuveField);
        
        this.line1 = new ContainerObject();
        this.setLayout(new BoxLayout(this.line1,BoxLayout.X_AXIS));
        this.line1.add(sessionPanel);
        this.line1.add(seriePanel);
        this.line1.add(epreuvePanel);
        this.line1.add(typeEpreuvePanel);
        
        this.coefficientLabel = new JLabel("Coéfficient");
        this.coefficientField = new JTextField();
        this.coefficientField.setPreferredSize(new Dimension(200,30));
        this.coefficientField.setMaximumSize(new Dimension(300,30));
        ContainerObject coefficientPanel = new ContainerObject();
        coefficientPanel.setLayout(new BoxLayout(coefficientPanel,BoxLayout.Y_AXIS));
        coefficientPanel.add(this.coefficientLabel);
        coefficientPanel.add(this.coefficientField);
        
        this.dureeLabel = new JLabel("Durée");
        this.dureeField = new TimePicker();
        this.dureeField.setPreferredSize(new Dimension(200,30));
        this.dureeField.setMaximumSize(new Dimension(300,30));
        ContainerObject dureePanel = new ContainerObject();
        dureePanel.setLayout(new BoxLayout(dureePanel,BoxLayout.Y_AXIS));
        dureePanel.add(this.dureeLabel);
        dureePanel.add(this.dureeField);
        
        this.isComposeSecTourLabel = new JLabel("Comp. au sec. tour");
        this.isComposeSecTourField = new JComboBox();
        this.isComposeSecTourField.addItem("Non");
        this.isComposeSecTourField.addItem("Oui");
        this.isComposeSecTourField.setPreferredSize(new Dimension(200,30));
        this.isComposeSecTourField.setMaximumSize(new Dimension(300,30));
        ContainerObject secondPanel = new ContainerObject();
        secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.Y_AXIS));
        secondPanel.add(this.isComposeSecTourLabel);
        secondPanel.add(this.isComposeSecTourField);

        this.isComposePreTourLabel = new JLabel("Comp. au pre. tour");
        this.isComposePreTourField = new JComboBox();
        this.isComposePreTourField.addItem("Non");
        this.isComposePreTourField.addItem("Oui");
        this.isComposePreTourField.setPreferredSize(new Dimension(200,30));
        this.isComposePreTourField.setMaximumSize(new Dimension(300,30));
        ContainerObject premierPanel = new ContainerObject();
        premierPanel.setLayout(new BoxLayout(premierPanel,BoxLayout.Y_AXIS));
        premierPanel.add(this.isComposePreTourLabel);
        premierPanel.add(this.isComposePreTourField);

        this.isRachetableLabel = new JLabel("Comp . rache");
        this.isRachetableField = new JComboBox();
        this.isRachetableField.addItem("Non");
        this.isRachetableField.addItem("Oui");
        this.isRachetableField.setPreferredSize(new Dimension(200,30));
        this.isRachetableField.setMaximumSize(new Dimension(300,30));
        ContainerObject rachetablePanel = new ContainerObject();
        rachetablePanel.setLayout(new BoxLayout(rachetablePanel,BoxLayout.Y_AXIS));
        rachetablePanel.add(this.isRachetableLabel);
        rachetablePanel.add(this.isRachetableField);

        this.isTypeFrancaisLabel = new JLabel("Tyoe . Franc");
        this.isTypeFrancaisField = new JComboBox();
        this.isTypeFrancaisField.addItem("Non");
        this.isTypeFrancaisField.addItem("Oui");
        this.isTypeFrancaisField.setPreferredSize(new Dimension(200,30));
        this.isTypeFrancaisField.setMaximumSize(new Dimension(300,30));
        ContainerObject typeFrancaisPanel = new ContainerObject();
        typeFrancaisPanel.setLayout(new BoxLayout(typeFrancaisPanel,BoxLayout.Y_AXIS));
        typeFrancaisPanel.add(this.isTypeFrancaisLabel);
        typeFrancaisPanel.add(this.isTypeFrancaisField);
        
        this.line2 = new ContainerObject();
        this.line2.setLayout(new BoxLayout(this.line2,BoxLayout.X_AXIS));
        this.line2.add(coefficientPanel);
        this.line2.add(dureePanel);
        this.line2.add(secondPanel);
        this.line2.add(premierPanel);
        this.line2.add(rachetablePanel);
        this.line2.add(typeFrancaisPanel);
        
        this.save = new Bouton("Enregistrer");
        this.save.addActionListener(this.epreuveSerieListener);
        this.reset = new Bouton("Annuler");
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);
        this.formControlPanel.add(this.reset);
        
        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.add(this.line1);
        this.formPanel.add(this.line2);
        this.formPanel.add(this.formControlPanel);
    }
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.getTitle().setText("Association option épreuves");
        this.head.initComposant();
        
        SessionService sessionService = new SessionService();
        List sessions = sessionService.getAll();
        for(Object session : sessions){
            this.sessionField.addItem(session);
        }
        
        SerieService serieService = new SerieService();
        List series = serieService.getAll("");
        for(Object serie : series){
            this.serieField.addItem(serie);
        }
        
        EpreuveService epreuveService = new EpreuveService();
        List epreuves = epreuveService.getAll("");
        for(Object epreuve : epreuves){
            this.epreuveField.addItem(epreuve);
        }
        
        TypeService typeService = new TypeService();
        List typesEpreuves = typeService.getAll("Type épreuve");
        for(Object tepreuve : typesEpreuves){
            this.typeEpreuveField.addItem(tepreuve);
        }
        
        this.body.add(this.formPanel);
        
    }
    
    class EpreuveSerieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean estAuSecTour = (isComposeSecTourField.getSelectedItem() == "Oui")?true:false;
            boolean estAuPreTour = (isComposePreTourField.getSelectedItem() == "Oui")?true:false;
            boolean estRachetable = (isRachetableField.getSelectedItem() == "Oui")?true:false;
            boolean estTypeFrancais = (isTypeFrancaisField.getSelectedItem() == "Oui")?true:false;
            try {
                EpreuveSerieService service = new EpreuveSerieService();
                service.setFormData(new HashMap<String,String>());
                service.getFormData().put("session_id", ((Session)sessionField.getSelectedItem()).getId().toString());
                service.getFormData().put("serie_id", ((Serie)serieField.getSelectedItem()).getId().toString());
                service.getFormData().put("epreuve_id", ((Epreuve)epreuveField.getSelectedItem()).getId().toString());
                service.getFormData().put("typeEpreuve_id", ((Type)typeEpreuveField.getSelectedItem()).getId().toString());
                service.getFormData().put("coefficient", coefficientField.getText().toString());
                service.getFormData().put("estComposeSecTour", estAuSecTour);
                service.getFormData().put("estComposePreTour", estAuPreTour);
                service.getFormData().put("estRachetable", estRachetable);
                service.getFormData().put("estTypeFrancais", estTypeFrancais);
                service.getFormData().put("duree", dureeField.getText());
                service.save();
            } catch (SQLException | EmptyDataException ex) {
                Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
