/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author coulibaly
 */
public abstract class Objet {
    protected Long id;  
    protected boolean etat;
    protected LocalDate addDate;
    protected boolean actif;
    protected boolean checked = false;
    protected boolean valide;
    
    public Long getId(){return this.id;}
    public void setId(Long id){this.id = id;}
    
    public Objet(){
        
    }
    
    public Objet(Map data){
        if(data.containsKey("id")){
            this.setId((Long) data.get("id"));
        }
    }
    public boolean isActive(){
        return this.etat;
    }
    
    public void setEtat(boolean etat){
        this.etat = etat;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    
    
    public LocalDate getAddDate() {
        return (addDate == null)? LocalDate.now():addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
    
}
