/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Objet;
import bf.menapln.entity.Resultat;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class ResultatTableModel extends ObjetModel{

    public ResultatTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom", "Sexe","Etablissement","Déc. jury"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((Resultat)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                return ((Resultat)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 2:
                return ((Resultat)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 3:
                return ((Resultat)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 4:
                return ((Resultat)this.modelData.get(rowIndex)).getCandidat().getEtablissementCandidat().getAcronymeStructure();
            case 5:
                return ((Resultat)this.modelData.get(rowIndex)).getDecisionJury().getLibelle();
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
        return Object.class;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
