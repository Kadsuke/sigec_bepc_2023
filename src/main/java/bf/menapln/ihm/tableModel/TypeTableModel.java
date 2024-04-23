/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Type;
import bf.menapln.ihm.Fenetre;
import bf.menapln.ihm.Panneau;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class TypeTableModel extends ObjetModel{

    public TypeTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Id","Modalités","",""};
    }


    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex){
            case 0:
                return ((Type)this.modelData.get(rowIndex)).getId();
            case 1:
                return ((Type)this.modelData.get(rowIndex)).getLibelle();
            case 2:
                return this.modelData.get(rowIndex).isActif();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            Type type = (Type)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 0 :
                    type.setId(Long.parseLong((String) value));
                    type.setLibelle("");
                break;
                case 1 :
                    type.setLibelle((String) value);
                break;
                case 2 :
                    if(type.isActif()){
                        type.setActif(false);
                    }else{
                        type.setActif(true);
                    }
                break;
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    /*@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }*/
    
}
