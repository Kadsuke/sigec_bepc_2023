/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.CandidatSalle;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SalleCandidatTableModel extends ObjetModel{

    public SalleCandidatTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom(s)", "Sexe","Option","Salle"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 2:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 3:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 4:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getCandidat().getSerie().getSerieLibelle();
            case 5:
                return ((CandidatSalle)this.modelData.get(rowIndex)).getSalleComposition().getLibelleSalle();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
