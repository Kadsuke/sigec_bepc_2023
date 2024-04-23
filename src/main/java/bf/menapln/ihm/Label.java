/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import javax.swing.JLabel;

/**
 *
 * @author coulibaly
 */
public class Label extends JLabel{
    private String texte="";

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = this.texte.concat(texte);
        this.setText(texte);
    }
    
    
    
}
