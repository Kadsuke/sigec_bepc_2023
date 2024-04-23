/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.entity;

/**
 *
 * @author coulibaly
 */
public class NatureHandicap extends Objet{
    private Type typeHandicap;
    private String natureHandicapLibelle;
    
    
    public NatureHandicap id(Long id){
        NatureHandicap h = new NatureHandicap();
        h.setId(id);
        return h;
    }
    
    public Type getTypeHandicap() {
        return typeHandicap;
    }

    public void setTypeHandicap(Type typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public String getNatureHandicapLibelle() {
        return natureHandicapLibelle;
    }

    public void setNatureHandicapLibelle(String natureHandicapLibelle) {
        this.natureHandicapLibelle = natureHandicapLibelle;
    }
    
    
}
