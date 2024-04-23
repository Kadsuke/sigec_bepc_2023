/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.CandidatPosteJury;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CandidatPosteJuryService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class CandidatPosteJuryTableModel extends ObjetModel{
    private CandidatPosteJuryService service;
    public CandidatPosteJuryTableModel(List<Objet> data, Fenetre f) throws SQLException {
        super(data, f);
        this.modelTitle = new String[]{"","Session","Poste","Nom", "Prénom(s)", "Sexe","Matricule/CNIB","NIP","Tél.","Epreuve","Etat cand."};
        this.service = new CandidatPosteJuryService();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: 
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).isChecked();
            case 1:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getSession().getAnnee();
            case 2:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getPoste().getLibelle();
            case 3:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getNom();
            case 4:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getPrenom();
            case 5:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getSexe();
            case 6:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getMatricule();
            case 7:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getNip();
            case 8:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getCandidat().getTelephone();
            case 9:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getEpreuve().getEpreuveAcronyme();
            case 10:
                return ((CandidatPosteJury)this.modelData.get(rowIndex)).getEtatCandidature();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(value != null){
            CandidatPosteJury candidat = (CandidatPosteJury)this.modelData.get(rowIndex);
            switch(columnIndex){
                case 0 :
                    candidat.setChecked((boolean) value);
                break;
                case 3 :
                    candidat.getCandidat().setNom((String)value);
                    try {
                        this.updateNom(candidat);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                case 4 :
                    candidat.getCandidat().setPrenom((String)value);
                    try {
                        this.updatePrenom(candidat);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                case 6 :
                    candidat.getCandidat().setMatricule((String)value);
                    try {
                        this.updateMatricule(candidat);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                case 7 :
                    candidat.getCandidat().setNip((String)value);
                    try {
                        this.updateNip(candidat);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                case 8 :
                    candidat.getCandidat().setTelephone((String) value);
                    try {
                        this.updateTelephone(candidat);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                case 9 :
                if(value.equals("Math")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("1")));
                }else if(value.equals("Fr")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("2")));
                }else if(value.equals("SVT")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("3")));
                }else if(value.equals("HG")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("4")));
                }else if(value.equals("PC")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("8")));
                }else if(value.equals("All")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("9")));
                }else if(value.equals("Ar")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("10")));
                }else if(value.equals("Esp")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("11")));
                }else if(value.equals("L")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("12")));
                }else if(value.equals("Ang")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("14")));
                }else if(value.equals("Ital")){
                    candidat.setEpreuve(Epreuve.id(Long.parseLong("16")));
                }
                try {
                    this.updateEpreuve(candidat);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        CandidatPosteJury candidatPosteJury = (CandidatPosteJury)this.modelData.get(rowIndex);
        switch(columnIndex){
            case 0 :
                return true;
            case 3 :
                return true;
            case 4 :
                return true;
            case 6 :
                return true;
            case 7 :
                return true;
            case 8 :
                return true;
            case 9 :
                return true;
            default:
                return false;
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

    public void updateNom(CandidatPosteJury candidatju) throws SQLException{
        service.updateNom(candidatju);
    }
    public void updatePrenom(CandidatPosteJury candidatju) throws SQLException{
        service.updatePrenom(candidatju);
    }
    public void updateMatricule(CandidatPosteJury candidatju) throws SQLException{
        service.updateMatricule(candidatju);
    }
    public void updateNip(CandidatPosteJury candidatju) throws SQLException{
        service.updateNip(candidatju);
    }
    public void updateTelephone(CandidatPosteJury candidatju) throws SQLException{
        service.updateTelephone(candidatju);
    }
    public void updateEpreuve(CandidatPosteJury candidatju) throws SQLException{
        service.updateTelephone(candidatju);
    }
    
}
