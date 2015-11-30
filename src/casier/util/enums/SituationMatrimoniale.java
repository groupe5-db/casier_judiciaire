/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.util.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author armel
 */
public enum SituationMatrimoniale {
    MARIE ("Marié"),
    MARIEE ("Mariée"),
    CELIBATAIRE ("Célibataire"),
    DIVORCE ("Divorcé"),
    DIVORCEE ("Divorcée"),
    VEUF ("Veuf"),
    VEUVE ("Veuve");
    
    private String situation = "";

    private SituationMatrimoniale(String situation) {
        this.situation = situation;
    }
    
    @Override
    public String toString(){
        return situation;
    }
    
}
