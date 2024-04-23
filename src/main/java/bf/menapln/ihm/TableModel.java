/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author coulibaly
 */
public class TableModel extends AbstractTableModel{
    private Object[][] data;
    private String[] title;
    
    public TableModel(Object[][] data,String title[]){
        this.data = data;
        this.title = title;
    }
    
    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public int getColumnCount() {
        return this.title.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    @Override
    public String getColumnName(int col) {
        return this.title[col];
    }
    
    @Override
    public Class getColumnClass(int col){
        return (this.data[0][col].getClass() == JCheckBox.class)? Boolean.class :this.data[0][col].getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        /*if(getValueAt(0, col) instanceof JCheckBox)
            return false;*/
        return true;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
      if (aValue instanceof Boolean && column == 2) {
        System.out.println((boolean)aValue);
        //Vector rowData = (Vector)getDataVector().get(row);
        //rowData.set(2, (boolean)aValue);
        //fireTableCellUpdated(row, column);
      }
    }
    
}
