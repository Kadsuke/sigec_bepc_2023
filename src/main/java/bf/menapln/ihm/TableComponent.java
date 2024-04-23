/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author coulibaly
 */
public class TableComponent extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table,
        Object value, boolean isSelected, boolean hasFocus, int row,
        int column) {
        if (value instanceof JCheckBox){
        return (JCheckBox) value;
        }else if (value instanceof JTextField){
            return (JTextField)value;
        }else{
            return this;
        }
        //return this;
    }
}
