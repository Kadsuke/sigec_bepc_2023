/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Centre;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.CentreService;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class CentreTableModel extends ObjetModel{

    public CentreTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Etablissement", "Acronyme", "Dédifinir Comme centre","Capacité"};
    }

    

    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((Centre)this.modelData.get(rowIndex)).getEtatblissement().getNomStructure();
            case 1:
                return ((Centre)this.modelData.get(rowIndex)).getEtatblissement().getAcronymeStructure();
            case 2:
                return ((Centre)this.modelData.get(rowIndex)).getEtatblissement().isDefinedAsCentre();
            case 3:
                return ((Centre)this.modelData.get(rowIndex)).getCapacite();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            Centre centre = (Centre)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 1 :
                    centre.getEtatblissement().setAcronymeStructure((String) value);
                break;
                case 2 :
                    centre.getEtatblissement().setDefinedAsCentre((boolean) value);
                break;
                case 3 :
                    centre.setCapacite((Long) value);
                break;
            }
            if(centre.getEtatblissement().isDefinedAsCentre() && centre.getCapacite() != null){
                try {
                    CentreService service = new CentreService();
                    Map data = new HashMap();
                    data.put("etablissement", centre.getEtatblissement());
                    data.put("session", this.fenetre.getUserSession().getSession());
                    data.put("capacite", centre.getCapacite());
                    service.setFormData(data);
                    service.save();
                } catch (SQLException | EmptyDataException ex) {
                    Logger.getLogger(CentreTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2:
                return Boolean.class;
            case 3:
                return Long.class;
            default:
                return Object.class;
        }
    }

   /*@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
        switch(columnIndex){
            case 2:
                return true;
            case 3:
                return true;
            default:
                return true;
        }
    }*/
    
}
