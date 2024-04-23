/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Menu;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class MenuTableModel extends ObjetModel{

    public MenuTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Menu"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Menu menu = ((Menu)this.modelData.get(rowIndex));
        return menu.getMenuLibelle();
        
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }
    
}
