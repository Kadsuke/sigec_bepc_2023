/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Jury;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SalleTableModel extends ObjetModel{

    public SalleTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Centre","Capacité centre","Jury","Capacité Jury"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((Jury)this.modelData.get(rowIndex)).getCentre().getEtatblissement().getNomStructure();
            case 1:
                return ((Jury)this.modelData.get(rowIndex)).getCentre().getCapacite();
            case 2:
                return ((Jury)this.modelData.get(rowIndex)).getJuryLibelle();
            case 3:
                return ((Jury)this.modelData.get(rowIndex)).getJuryCapacite();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }
    
}
