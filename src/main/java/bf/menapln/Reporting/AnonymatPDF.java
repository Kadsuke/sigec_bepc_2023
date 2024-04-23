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
public class AnonymatPDF extends Anonymat{

    public AnonymatPDF(Fenetre fenetre) {
        super(fenetre);
    }

    @Override
    public void setTableBody(Table table) {
        for(Object copie : this.listeCandidat){
            if(((FeuilleComposition)copie).getAnonymat() != null){
                table.addCell(((FeuilleComposition)copie).getAnonymat());
            }else{
                table.addCell(((FeuilleComposition)copie).getCandidat().getNumeroPv().toString());
            }
            table.addCell("");
        }
    }
    
}
