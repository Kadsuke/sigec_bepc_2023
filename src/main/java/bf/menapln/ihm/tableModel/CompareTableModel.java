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
import bf.menapln.service.FeuilleCompositionService;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class CompareTableModel extends ObjetModel{
    private FeuilleCompositionService service;
    public CompareTableModel(List<Objet> data, Fenetre f) throws SQLException {
        super(data, f);
        this.modelTitle = new String[]{"Anonymat","1er Tour","2nd Tour"};
        this.service = new FeuilleCompositionService();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FeuilleComposition feuille = ((FeuilleComposition)this.modelData.get(rowIndex));
        Epreuve epreuve = feuille.getComposition().getEpreuve();
        Candidat candidat = feuille.getCandidat();
        if(!feuille.isPresent() && feuille.getNote() == null){
            feuille.setNote(0.0);
            try {
                this.updateNote(feuille);
            } catch (SQLException ex) {
                Logger.getLogger(NoteTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        switch(columnIndex){
            case 0:
                return (feuille.getAnonymat() != null)?((FeuilleComposition)this.modelData.get(rowIndex)).getAnonymat():((FeuilleComposition)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 1:
                if(feuille.getComposition().getTourComposition().getId() == 1){
                    return feuille.getNote();
                }
            /*case 2:
                return feuille.getComposition().getSerie().getSerieLibelle();
            case 3:
                return feuille.getComposition().getJury().getJuryLibelle();
            case 4:
                return feuille.getComposition().getEpreuve().getEpreuveLibelle()+""+((candidat.getInscription().getDispenses().containsKey(epreuve.getId()))?" (Dispensée)":"");
            case 5:
                return feuille.getCorrecteur().getMatricule();*/
            case 2:
                if(feuille.getComposition().getTourComposition().getId() == 2){
                    return feuille.getNote();
                }
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            FeuilleComposition feuille = (FeuilleComposition)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 0 :
                    feuille.setPresent((boolean)value);
                case 2 :
                    if(feuille.isPresent())
                        {
                            String ep = feuille.getComposition().getEpreuve().getEpreuveLibelle();
                            Double note;
                            if(ep.equals("Dictée")||ep.equals("Langue")||ep.equals("Expression Ecrite")||ep.equals("Français"))
                            {   
                               note = ((Double.parseDouble((String) value)))/(feuille.getComposition().getEpreuve().getCoefficient());
                            }else{
                                note = ((Double.parseDouble((String) value)));

                            }
                            feuille.setNote((note));
                    }else{
                        feuille.setNote(0.0);
                    }
                break;
            }
            try {
               // this.updatePresence(feuille);
                this.updateNote(feuille);
            } catch (SQLException ex) {
                Logger.getLogger(NoteTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Boolean.class;
            default:
                return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        FeuilleComposition feuille = (FeuilleComposition)this.modelData.get(rowIndex);
        Epreuve epreuve = feuille.getComposition().getEpreuve();
        Candidat candidat = feuille.getCandidat();
        switch(columnIndex){
            case 0:
                return feuille.isPresent();
            case 2 :
                return (feuille.isVerouille() || candidat.getInscription().getDispenses().containsKey(epreuve.getId()))?false : true;
            default:
                return false;
        }
    }
    
    public void updatePresence(FeuilleComposition feuille) throws SQLException{
        service.upadtePresence(feuille);
    }
    public void updateNote(FeuilleComposition feuille) throws SQLException{
        service.upadteNote(feuille);
    }
}