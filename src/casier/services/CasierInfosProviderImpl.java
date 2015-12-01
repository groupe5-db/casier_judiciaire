/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import casier.entities.Peine;
import casier.entities.Personne;
import java.util.List;


public class CasierInfosProviderImpl implements CasierInfosProvider {
    
    private final Resource personneRs = new PersonneResource();
    
    private final PeineResource peineRs = new PeineResource();
    
    private Personne personne = null;
    
    private List<Peine> peines = null;

    public CasierInfosProviderImpl(String numeroActe) {
        personne = (Personne) personneRs.get(numeroActe);
        peines = personne != null ? peineRs.getByPerson(personne) : null;
    }

    @Override
    public Personne getPersonne() {
        return personne;
    }

    @Override
    public List<Peine> getPeines() {
        return peines;
    }
    
}
