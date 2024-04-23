/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm.tableModel;

import bf.menapln.entity.Objet;
import bf.menapln.entity.User;
import bf.menapln.ihm.Fenetre;
import java.util.List;

/**
 *
 * @author coulibaly
 */
public class UserTableModel extends ObjetModel{

    public UserTableModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Nom","Prénom","Profil","Identifiant"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return ((User)this.modelData.get(rowIndex)).getPersonnel().getNom();
            case 1:
                return ((User)this.modelData.get(rowIndex)).getPersonnel().getPrenom();
            case 2:
                return ((User)this.modelData.get(rowIndex)).getProfil().getProfilLibelle();
            case 3:
                return ((User)this.modelData.get(rowIndex)).getUsername();
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
    
}
