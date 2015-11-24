/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.util.enums;

/**
 *
 * @author armel
 */
public enum Sexe {
    M ("Masculin"),
    F ("Feminin");
    
    private String sexe = "";
    
    Sexe(String sexe){
        this.sexe = sexe;
    }
    
    @Override
    public String toString(){
        return sexe;
    }
}
