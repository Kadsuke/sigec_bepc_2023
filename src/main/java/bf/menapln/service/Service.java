/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.service;

import bf.menapln.dto.Factory;
import bf.menapln.dto.InterfaceDAO;
import bf.menapln.entity.Correcteur;
import bf.menapln.entity.Epreuve;
import bf.menapln.entity.EpreuveSerie;
import bf.menapln.entity.FeuilleComposition;
import bf.menapln.entity.Objet;
import bf.menapln.ihm.tableModel.ObjetModel;
import exception.EmptyDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author coulibaly
 */
public abstract class Service {
    protected Factory factory;
    protected InterfaceDAO implementationDAO;
    protected Map formData;
    public Service() throws SQLException{
        this.factory = Factory.getInstance("sigec_bac");
        this.factory.connect();
        this.factory.getDatabase().initDatabase();
    }
    
    public abstract Objet save()throws SQLException,EmptyDataException;
    public abstract List<Objet> getByParentId(String str) throws SQLException;
    public abstract List<Objet> getAll(String str) throws SQLException;
    public abstract Objet getById(Long id) throws SQLException;
    public abstract Objet update(Objet objet) throws SQLException;
    public abstract void validate() throws EmptyDataException;
    public abstract void setImplementation(String str);
    public Map getFormData() {
        return formData;
    }

    public void setFormData(Map formData) {
        this.formData = formData;
    }
    
    public List<String> random(int size){
        Random rand = new Random();
        List<String> chaines = new ArrayList<String>();
        while(chaines.size() != size){
            char c = (char)(rand.nextInt(26) + 65);
            int d = 1+rand.nextInt(21);
            String str = c+""+d;
            if(!chaines.contains(str)){
                chaines.add(str);
            }
        }
        return chaines;
    }
    
    public void setNbCopieParCorrecteur(ObjetModel model,JComboBox correcteur){
        int nombreCandidat = model.getRowCount();
        int nombreCorrecteur = correcteur.getItemCount();
        int nombreMinCopie = nombreCandidat/nombreCorrecteur;
        int resteCopie = nombreCandidat%nombreCorrecteur;
        for(int j = 0 ; j < nombreCorrecteur ; j++){
            if(resteCopie > 0){
                ((Correcteur)correcteur.getItemAt(j)).setNbCopie(nombreMinCopie+1);
                resteCopie--;
            }else{
                ((Correcteur)correcteur.getItemAt(j)).setNbCopie(nombreMinCopie);
            }
        }
    }
    
    public void repartirCopie(ObjetModel model,JComboBox correcteur){
        this.setNbCopieParCorrecteur(model, correcteur);
        int indiceCorrecteur = 0;
        int partCorrecteur = 0;
        for(Objet objet : model.getModelData()){
            if(partCorrecteur ==  ((Correcteur)correcteur.getItemAt(indiceCorrecteur)).getNbCopie()){
                indiceCorrecteur++;
                partCorrecteur = 0;
            }
            FeuilleComposition copie = (FeuilleComposition)objet;
            copie.setCorrecteur(((Correcteur)correcteur.getItemAt(indiceCorrecteur)).getPersonnel());
            copie.setReparti(true);
            partCorrecteur++;
        }
        model.fireTableDataChanged();
    }
    
    public String[] setColonnePVProvisoire(JTable table,List epreuves){
        String[] colPV = new String[epreuves.size()*2+3];
        int indice = 0;
        while (table.getColumnModel().getColumnCount() != 0) {                
            table.getColumnModel().removeColumn(table.getColumnModel().getColumn(0));
        }
        for(Object objet : epreuves){
            TableColumn an = new TableColumn(indice);
            an.setHeaderValue("A.");
            colPV[indice] = "A.";
            indice++;
            TableColumn eplib = new TableColumn(indice);
            eplib.setHeaderValue(((Epreuve)objet).getEpreuveAcronyme()+" ("+((Epreuve)objet).getCoefficient()+")");
            colPV[indice] = ((Epreuve)objet).getEpreuveAcronyme()+" ("+((Epreuve)objet).getCoefficient()+")";
            table.getColumnModel().addColumn(an);
            table.getColumnModel().addColumn(eplib);
            indice++;
        }
        TableColumn total = new TableColumn(indice);
        total.setHeaderValue("Total");
        colPV[indice] = "Total";
        
        TableColumn moy = new TableColumn(indice+1);
        moy.setHeaderValue("Moy.");        
        colPV[indice+1] = "Moy.";
        
        TableColumn decision = new TableColumn(indice+2);
        decision.setHeaderValue("Dec. Jury");        
        colPV[indice+2] = "Dec. Jury";

         /* TableColumn mention = new TableColumn(indice+3);
        mention.setHeaderValue("Mention");        
        colPV[indice+3] = "Mention"; */ 


        
        
        table.getColumnModel().addColumn(total);
        table.getColumnModel().addColumn(moy);
        table.getColumnModel().addColumn(decision);
       // table.getColumnModel().addColumn(mention);
        return colPV;
    }

    public String[] setColonnePV(JTable table,List epreuves){
        String[] colPV = new String[epreuves.size()*2+2];
        int indice = 0;
        while (table.getColumnModel().getColumnCount() != 0) {                
            table.getColumnModel().removeColumn(table.getColumnModel().getColumn(0));
        }
        for(Object objet : epreuves){
            TableColumn an = new TableColumn(indice);
            an.setHeaderValue("A."/*+((Epreuve)objet).getEpreuveAcronyme()*/);
            colPV[indice] = "A.";
            indice++;
            TableColumn eplib = new TableColumn(indice);
            eplib.setHeaderValue(((Epreuve)objet).getEpreuveAcronyme()+" ("+((Epreuve)objet).getCoefficient()+")");
            colPV[indice] = ((Epreuve)objet).getEpreuveAcronyme()+" ("+((Epreuve)objet).getCoefficient()+")";
            table.getColumnModel().addColumn(an);
            table.getColumnModel().addColumn(eplib);
            indice++;
        }
        TableColumn total = new TableColumn(indice);
        total.setHeaderValue("Total");
        colPV[indice] = "Total";
        
        TableColumn moy = new TableColumn(indice+1);
        moy.setHeaderValue("Point Manquant.");        
        colPV[indice+1] = "Point Manquant.";
        
       /*  TableColumn decision = new TableColumn(indice+2);
        decision.setHeaderValue("Dec. Jury");        
        colPV[indice+2] = "Dec. Jury";

        TableColumn mention = new TableColumn(indice+3);
        mention.setHeaderValue("Mention");        
        colPV[indice+3] = "Mention"; */


        
        
        table.getColumnModel().addColumn(total);
        table.getColumnModel().addColumn(moy);
        //table.getColumnModel().addColumn(decision);
        //table.getColumnModel().addColumn(mention);
        return colPV;
    }
    
    public static void setPVPColWidth(JTable table){
        int colCount = table.getColumnCount();
        for(int i = 0 ; i < colCount-4 ; i++){
            if(i%2 == 0){
                table.getColumnModel().getColumn(i).setMaxWidth(50);
            }
        }
    }
    
}
