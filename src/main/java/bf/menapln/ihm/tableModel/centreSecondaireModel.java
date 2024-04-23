package bf.menapln.ihm.tableModel;

import java.util.List;

import bf.menapln.entity.CentreSecondaire;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.Fenetre;

public class centreSecondaireModel extends ObjetModel{

    public centreSecondaireModel(List<Objet> data, Fenetre f) {
        super(data, f);
        this.modelTitle = new String[]{"Centre Secondaire","Jury"};
        //TODO Auto-generated constructor stub
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        switch(columnIndex){
            case 0:
                return ((CentreSecondaire)this.modelData.get(rowIndex)).getLibelle();
            case 1:
            //return ((CentreSecondaire)this.modelData.get(rowIndex)).getJury().getJuryLibelle();
            case 2:
              //  return ((CentreSecondaire)this.modelData.get(rowIndex)).getJuryLibelle();
            case 3:
               // return ((CentreSecondaire)this.modelData.get(rowIndex)).getJury().getJuryLibelle()
            default:
                return null;
        }    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValueAt'");
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        // TODO Auto-generated method stube
        return Object.class;    
    }
    
}
