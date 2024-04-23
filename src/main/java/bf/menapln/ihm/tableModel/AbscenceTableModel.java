/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.FeuilleCompositionService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class AbscenceTableModel extends ObjetModel{
    private boolean saved;
    public AbscenceTableModel(List<Objet> data, Fenetre f) throws SQLException {
        super(data, f);
        this.service = new FeuilleCompositionService();
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom(s)", "Sexe","Jury","Epreuve","Date compo.","Début","Fin","Etat copies","Absent(e)"};
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
                return (((FeuilleComposition)this.modelData.get(rowIndex)).getCycle() >= 1)?"Crée" : "Non crée" ;
            case 10:
                return !((FeuilleComposition)this.modelData.get(rowIndex)).isPresent();
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
                    feuille.setPresent(!((boolean) value));
                break;
            }
            if(this.saved){
                try {
                    this.updatePresence(feuille);
                } catch (SQLException ex) {
                    Logger.getLogger(AbscenceTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 10:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    
    public void updatePresence(FeuilleComposition feuille) throws SQLException{
        ((FeuilleCompositionService)this.service).updatePresence(feuille);
    }
    
}
