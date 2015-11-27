/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import casier.DAO.GenericDAO;
import casier.DAO.PeineDAO;
import casier.DAO.PersonneDAO;
import casier.entities.Peine;
import casier.entities.Personne;
import java.util.List;


public class CasierInfosProviderImpl implements CasierInfosProvider {
    
    private final GenericDAO personneDao = new PersonneDAO();
    
    private final GenericDAO peineDao = new PeineDAO();
    
    private Personne personne = null;
    
    private List<Peine> peines = null;

    public CasierInfosProviderImpl(String numeroActe) {
        personne = (Personne) personneDao.findById(numeroActe);
        peines = peineDao.findByCriteria("numeroActe", personne.getNumeroActe());
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
