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
public enum Mandat {
    
    DETENTION ("Mandat de détention provisoire"),
    INCARCERATION ("Mandat d'incarcération"),
    ARRET ("Mandat d'arret");
    
    private String type = "";

    private Mandat(String type) {
        this.type = type;
    }
    
    @Override
    public String toString(){
        return type;
    }
}
