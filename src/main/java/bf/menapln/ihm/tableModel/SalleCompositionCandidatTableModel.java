/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class SalleCompositionCandidatTableModel extends ObjetModel{

    public SalleCompositionCandidatTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom(s)", "Sexe","Prescription hand.","Option","Jury"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 2:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 3:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 4:
                return null;//((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getVilleComposition().getNomLoclite();
            case 5:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSerie().getSerieLibelle();
            case 6:
                return (((CandidatJury)this.modelData.get(rowIndex)).getJury() != null )?((CandidatJury)this.modelData.get(rowIndex)).getJury().getJuryLibelle():null;
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
