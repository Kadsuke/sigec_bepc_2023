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
public class RepartitionTableModel extends ObjetModel{

    public RepartitionTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"","N°PV","Nom", "Prénom", "Sexe","Option","Ville compo.","Jury"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().isInJury();
            case 1:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getNumeroPv();
            case 2:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 3:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 4:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 5:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSerie().getSerieLibelle();
            case 6:
               // return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getVilleComposition().getNomLoclite();
            case 7:
                return (((CandidatJury)this.modelData.get(rowIndex)).getJury() != null )?((CandidatJury)this.modelData.get(rowIndex)).getJury().getJuryLibelle():null;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            CandidatJury candidat = (CandidatJury)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 0 :
                    candidat.getCandidat().setInJury((boolean) value);
                break;
            }
            /*if(centre.getEtatblissement().isDefinedAsCentre() && centre.getCapacite() != null){
                try {
                    CentreService service = new CentreService();
                    Map data = new HashMap();
                    data.put("etablissement", centre.getEtatblissement());
                    data.put("session", Session.id(Long.parseLong("1")));
                    data.put("capacite", centre.getCapacite());
                    service.setFormData(data);
                    service.save();
                } catch (SQLException | EmptyDataException ex) {
                    Logger.getLogger(CentreTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
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
    
}
