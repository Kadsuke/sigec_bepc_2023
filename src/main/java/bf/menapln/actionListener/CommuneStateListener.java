/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.actionListener;

import bf.menapln.entity.Centre;
import bf.menapln.entity.Etablissement;
import bf.menapln.entity.Localite;
import bf.menapln.entity.Serie;
import bf.menapln.entity.Session;
import bf.menapln.entity.Structure;
import bf.menapln.service.CentreService;
import bf.menapln.service.LocaliteService;
import bf.menapln.service.SerieService;
import bf.menapln.service.StructureService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author coulibaly
 */
public class CommuneStateListener implements ActionListener{
    private JComboBox villageField = null;
    private JComboBox etablissementField = null;
    private JComboBox centreField = null;
    private JComboBox serieField = null;
    private JComboBox centreExamenField = null;
    private Session session;
    
    public CommuneStateListener(){
        super();
    }
    public CommuneStateListener(JComboBox villageField,JComboBox etablissementField, JComboBox centreExamenField){
        this.villageField = villageField;
        this.etablissementField = etablissementField;
        this.centreExamenField = centreExamenField;
    }

    public CommuneStateListener(JComboBox centreExamenField){
        this.centreExamenField = centreExamenField;
    }
    public static CommuneStateListener villageField(JComboBox villageField){
        CommuneStateListener communeState = new CommuneStateListener();
        communeState.villageField = villageField;
        return communeState;
    }
    

    public static CommuneStateListener centreExamenField(JComboBox centreExamenField){
        CommuneStateListener communeState = new CommuneStateListener();
        communeState.centreExamenField = centreExamenField;
        return communeState;
    }
    
    
    public static CommuneStateListener etablissementField(JComboBox etabField){
        CommuneStateListener communeState = new CommuneStateListener();
        communeState.etablissementField = etabField;
        return communeState;
    }
    
    public static CommuneStateListener centreField(JComboBox etabField){
        CommuneStateListener communeState = new CommuneStateListener();
        communeState.centreField = etabField;
        return communeState;
    }
    
    public static CommuneStateListener serieField(JComboBox serieField,Session session){
        CommuneStateListener communeState = new CommuneStateListener();
        communeState.serieField = serieField;
        communeState.session = session;
        return communeState;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(this.villageField != null){
            LocaliteService localiteService;
            try {
                localiteService = new LocaliteService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List communes = localiteService.getByParentId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.villageField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Localite());
                    for(Object commune : communes){
                        comboModel.addElement(commune);
                    }
                    this.villageField.setModel(comboModel);
                }
            } catch (SQLException ex) {
            }
        }
        
        if(this.centreExamenField != null){
            LocaliteService localiteService;
            try {
                localiteService = new LocaliteService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List communes = localiteService.getByTypeLocalite(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString(),"5");
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.centreExamenField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Localite());
                    for(Object commune : communes){
                        comboModel.addElement(commune);
                    }
                    this.centreExamenField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(EtablissementForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(this.etablissementField != null){
            StructureService structureService;
            try {
                structureService = new StructureService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    //List etablissements = structureService.getByLocaliteId(((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId().toString());
                    List etablissements = structureService.getAll("test");
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.etablissementField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Structure());
                    for(Object etablissement : etablissements){
                        comboModel.addElement(etablissement);
                    }
                    this.etablissementField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                System.out.println("Echec : "+ex.getMessage());
            }
        }
        
        if(this.centreField != null){
            CentreService centreService;
            try {
                centreService = new CentreService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List centres = centreService.getCentres((Localite)((JComboBox)ae.getSource()).getSelectedItem());
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.centreField.getModel();
                    comboModel.removeAllElements();
                    Centre emptyCentre= new Centre();
                    emptyCentre.setEtatblissement(new Etablissement());
                    comboModel.addElement(emptyCentre);
                    for(Object centre : centres){
                        comboModel.addElement(centre);
                        //provinceField.addItem((Localite)province);
                    }
                    this.centreField.setModel(comboModel);
                }
            } catch (SQLException ex) {
                System.out.println("Echec : "+ex.getMessage());
            }
        }
        
        if(this.serieField != null){
            SerieService serieService;
            try {
                serieService = new SerieService();
                if(((Localite)((JComboBox)ae.getSource()).getSelectedItem()) != null && ((Localite)((JComboBox)ae.getSource()).getSelectedItem()).getId() != null){
                    List series = serieService.getSerie(this.session, (Localite)((JComboBox)ae.getSource()).getSelectedItem());
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel)this.serieField.getModel();
                    comboModel.removeAllElements();
                    comboModel.addElement(new Serie());
                    for(Object serie : series){
                        System.out.println(serie.getClass());
                        
                        comboModel.addElement(serie);
                        //provinceField.addItem((Localite)province);
                    }
                    this.serieField.setModel(comboModel);
                    System.out.println("yes");
                }
            } catch (SQLException ex) {
                System.out.println("Echec : "+ex.getMessage());
            }
        }
    } 
    public JComboBox getVillageField() {
        return villageField;
    }

    public void setVillageField(JComboBox villageField) {
        this.villageField = villageField;
    }

    public JComboBox getEtablissementField() {
        return etablissementField;
    }

    public void setEtablissementField(JComboBox etablissementField) {
        this.etablissementField = etablissementField;
    }

    public JComboBox getCentreExamenField() {
        return centreExamenField;
    }

    public void setCentreExamenField(JComboBox centreExamenField) {
        this.centreExamenField = centreExamenField;
    }


    public JComboBox getSerieField() {
        return serieField;
    }

    public void setSerieField(JComboBox serieField) {
        this.serieField = serieField;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
