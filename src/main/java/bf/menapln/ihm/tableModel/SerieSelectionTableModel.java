/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SerieSelectionTableModel extends ObjetModel{

    public SerieSelectionTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"","Option"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((Serie)this.modelData.get(rowIndex)).isChecked();
            case 1:
                return ((Serie)this.modelData.get(rowIndex)).getSerieLibelle();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            Serie serie = (Serie)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 0 :
                    serie.setChecked((boolean) value);
                break;
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Boolean.class;
            default:
                return Object.class;
        }
    }
    
}
