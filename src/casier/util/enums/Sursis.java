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
public enum Sursis {
    
    Avec ("Avec sursis"),
    Sans ("Sans sursis");
    
    private String type = "";

    private Sursis(String type) {
        this.type = type;
    }
    
    @Override
    public String toString(){
        return type;
    }
}
