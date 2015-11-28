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
public enum NatureInfraction {
    
    CR_FR ("crimes"),
    DE_FR ("délits"),
    CO_FR ("contraventions"),
    CR_EN ("felonies"),
    DE_EN ("misdemeanours"),
    CO_EN ("simple offences");
    
    private String type = "";

    private NatureInfraction(String type) {
        this.type = type;
    }
    
    @Override
    public String toString(){
        return type;
    }
    
    
}
