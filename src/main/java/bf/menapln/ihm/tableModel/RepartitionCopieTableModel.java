/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class RepartitionCopieTableModel extends ObjetModel{
    public RepartitionCopieTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Anonymat","Epreuve","Correcteur","Etat repartition"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getAnonymat();
            case 2:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getEpreuve().getEpreuveLibelle();
            case 3:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCorrecteur().getMatricule();
            case 4:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).etatReparti();
            
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            FeuilleComposition feuille = (FeuilleComposition)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 3 :
                    feuille.setCorrecteur((String) value);
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
}
