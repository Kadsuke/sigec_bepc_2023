/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Composition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CompositionTableModel extends ObjetModel{

    public CompositionTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Session", "Jury", "Etape","Epreuve","Date Compo.","H. déb.","H. fin","Fraude","Observation"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((Composition)this.modelData.get(rowIndex)).getSession().getAnnee();
            case 1:
                return ((Composition)this.modelData.get(rowIndex)).getJury().getJuryLibelle();
            case 2:
                return ((Composition)this.modelData.get(rowIndex)).getTourComposition().getLibelle();
            case 3:
                return ((Composition)this.modelData.get(rowIndex)).getEpreuve().getEpreuveLibelle();
            case 4:
                return ((Composition)this.modelData.get(rowIndex)).getDateComposition().toString();
            case 5:
                return ((Composition)this.modelData.get(rowIndex)).getHeureDebutComposition().toString();
            case 6:
                return ((Composition)this.modelData.get(rowIndex)).getHeureFinComposition().toString();
            case 7:
                return ((Composition)this.modelData.get(rowIndex)).isFraude();
            case 8:
                return ((Composition)this.modelData.get(rowIndex)).getObservation();
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
        switch(columnIndex){
            case 7:
                return Boolean.class;
            default:
                return Object.class;
        }
    }
    
}
