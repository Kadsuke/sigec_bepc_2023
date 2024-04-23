/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.Service;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author coulibaly
 */
public abstract class ObjetModel extends AbstractTableModel{
    protected String[] modelTitle;
    protected List<Objet> modelData;
    
    protected Fenetre fenetre;
    protected Service service;
    
    public ObjetModel(List<Objet> data,Fenetre f){
        this.modelData = data;
        this.fenetre = f;
    }
    
    @Override
    public int getRowCount() {
        return this.modelData.size();
    }

    @Override
    public int getColumnCount() {
        return this.modelTitle.length;
    }
    
    
    @Override
    public String getColumnName(int colIndex) {
        return this.modelTitle[colIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }
    
    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
    
    @Override
    public abstract void setValueAt(Object value, int rowIndex, int columnIndex);
    
    @Override
    public abstract Class getColumnClass(int columnIndex);
    
    public void addRow(Objet o){
        this.modelData.add(o);
        fireTableRowsInserted(this.modelData.size()-1,this.modelData.size()-1);
    }
    
    public void removeRow(int rowIndex){
        this.modelData.remove(rowIndex);
        fireTableRowsDeleted(rowIndex,rowIndex);
    }

    public String[] getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String[] modelTitle) {
        this.modelTitle = modelTitle;
    }

    public List<Objet> getModelData() {
        return modelData;
    }

    public void setModelData(List<Objet> modelData) {
        this.modelData = modelData;
        this.fireTableDataChanged();
    }
    
    
}
