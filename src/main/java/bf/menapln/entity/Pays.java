/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.util.Map;

/**
 *
 * @author coulibaly
 */
public class Pays extends Objet{
    private String indicatif;
    private String nomPays;
    private String nationalite;
    private boolean paysToString = true;
    
    public Pays(){
        super();
    }
    
    public Pays(Map data){
        this.setIndicatif((String) data.get("indicatif"));
        this.setNationalite((String) data.get("nationalite"));
        this.setNomPays((String) data.get("nomPays"));
    }
    public static Pays id(Long id){
        Pays p = new Pays();
        p.setId(id);
        return p;
    }
    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public boolean isPaysToString() {
        return paysToString;
    }

    public void setPaysToString(boolean paysToString) {
        this.paysToString = paysToString;
    }
    
    @Override
    public String toString(){
        return (this.isPaysToString())?this.nomPays:this.nationalite;
    }
}
