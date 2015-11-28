/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import casier.entities.Peine;
import casier.entities.Personne;
import java.util.List;

/**
 *
 * @author armel
 */
public interface CasierInfosProvider {
    
    // Personne concernée
    Personne getPersonne();
    
    // Liste des peines de la personne concernée
    List<Peine> getPeines();
    
}
