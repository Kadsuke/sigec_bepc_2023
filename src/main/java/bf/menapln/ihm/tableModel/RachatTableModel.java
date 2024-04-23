/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Candidat;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class RachatTableModel extends ObjetModel{
    public RachatTableModel(List<Objet> data, Fenetre f) throws SQLException {
        super(data, f);
        this.modelTitle = new String[]{};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { 
        Candidat candidat = ((Candidat)this.modelData.get(rowIndex));
            return this.matchValueAt(candidat, columnIndex);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            FeuilleComposition feuille = (FeuilleComposition)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 6 :
                    if(feuille.isPresent()){feuille.setNote((Double) value);}else{feuille.setNote(0.0);}
                break;
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            default:
                return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
    }
    
    public Object matchValueAt(Candidat candidat,int columnIndex){
        Object objet = null;
        int feuilleIndice = 0;
            if(columnIndex == this.getColumnCount()-1){
                objet =  ((candidat.getTotalCoef()*10)-candidat.getTotalPondere());
            }else if(columnIndex == this.getColumnCount()-2){
                objet = candidat.getTotalPondere();
            }else if(columnIndex%2 == 0){
                feuilleIndice = columnIndex/2;
                objet = (feuilleIndice < candidat.getCopies().size())? (((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getAnonymat() != null)?((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getAnonymat() : candidat.getNumeroPv() :null;
            }else if(columnIndex%2 == 1){
                feuilleIndice = (columnIndex-1)/2;
                objet = (feuilleIndice < candidat.getCopies().size())? ((FeuilleComposition)candidat.getCopies().get(feuilleIndice)).getNotePondere():null;
            }
            return objet;
    }
}
