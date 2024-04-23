/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import bf.menapln.entity.FeuilleComposition;
import bf.menapln.ihm.Fenetre;
import com.itextpdf.layout.element.Table;

/**
 *
 * @author coulibaly
 */
public class NotePDF extends Anonymat{

    public NotePDF(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void setTableBody(Table table) {
        for(Object copie : this.listeCandidat){
            table.addCell(((FeuilleComposition)copie).getAnonymat());
            table.addCell((((FeuilleComposition)copie).getNote() != null)?((FeuilleComposition)copie).getNote().toString():"");
        }
    }
    
}