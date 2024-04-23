/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import java.util.List;

import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;

/**
 *
 * @author coulibaly
 */
public class AnonymatTableModel extends ObjetModel{

    public AnonymatTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom(s)", "Sexe","Jury","Epreuve","Date compo.","Début","Fin","Anonymat","Etat anonymat"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 2:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 3:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 4:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getJury().getJuryLibelle();
            case 5:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getEpreuve().getEpreuveLibelle();
            case 6:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getDateComposition();
            case 7:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getHeureDebutComposition();
            case 8:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getComposition().getHeureFinComposition();
            case 9:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).getAnonymat();
            case 10:
                return ((FeuilleComposition)this.modelData.get(rowIndex)).etatAnonymat();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            FeuilleComposition feuille = (FeuilleComposition)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 10 :
                    feuille.setAnonymat((String) value);
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
