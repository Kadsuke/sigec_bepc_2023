/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.CandidatJury;
import bf.menapln.entity.Objet;
import bf.menapln.entity.Serie;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.CandidatJuryService;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class CandidatJuryTableModel extends ObjetModel{
    private CandidatJuryService service;
    public CandidatJuryTableModel(List<Objet> data, Fenetre f) throws SQLException {
        super(data, f);
        this.modelTitle = new String[]{"N°PV","Nom", "Prénom(s)", "Sexe","Sport","Etab","Option"};
        this.service = new CandidatJuryService();

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
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSport();
            case 5:
                return (((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getEtablissementCandidat() != null )?((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getEtablissementCandidat().getAcronymeStructure():null;
            case 6:
                return ((CandidatJury)this.modelData.get(rowIndex)).getCandidat().getSerie().getSerieLibelle();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            CandidatJury candidat = (CandidatJury)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 4 :
                    candidat.getCandidat().setSport((String) value);
                    try {
                        this.updateCandidatAptitude(candidat);
                     } catch (SQLException ex) {
                         Logger.getLogger(NoteTableModel.class.getName()).log(Level.SEVERE, null, ex);
                     }
                break;
                case 6 :
                if(value != null){
                     if(value.equals("PC")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("1")));
                     }else if(value.equals("PCAL")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("2")));
                     }else if(value.equals("PCAR")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("3")));
                     }else if(value.equals("PCESP")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("4")));
                     }else if(value.equals("PCITA")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("6")));
                     }else if(value.equals("PCLAT")){
                        candidat.getCandidat().setSerie(Serie.id(Long.parseLong("5")));
                     }
                    
                    try {
                        this.updateCandidatSerie(candidat);
                     } catch (SQLException ex) {
                         Logger.getLogger(NoteTableModel.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
                break;
            }
            
        }
        
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 4:
                return true;
            case 6:
                return true;
            default:
                return false;
        }
    }

    public void updateCandidatAptitude(CandidatJury candidatju) throws SQLException{
        service.updateAptitude(candidatju);
    }

    public void updateCandidatSerie(CandidatJury candidatju) throws SQLException{
        service.updateSerie(candidatju);
    }
    
}
