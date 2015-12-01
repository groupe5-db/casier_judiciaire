/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import casier.DAO.PersonneDAO;
import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import casier.entities.Personne;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// On pourrait ajouter des messages de dialogue à chaque exception

/**
 *
 * @author armel
 */
public class PersonneResource implements Resource<Personne, String> {
    
    private final PersonneDAO dao = new PersonneDAO();

    @Override
    public void add(Personne entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Personne entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Personne get(String numeroActe) {
        Personne pers = dao.findById(numeroActe);
        if(pers == null){
            // Dialog Box
            System.out.println("Désolé cette personne n'existe pas dans le système");
        }
        return pers;
    }

    @Override
    public List<Personne> getAll() {
        List<Personne> pers = dao.findAll();
        if(pers.isEmpty()){
            // Dialog Box
            System.out.println("Aucune personne n'a encore été enregistrée dans le système");
        }
            
        return pers;
    }

    @Override
    public void remove(String numeroActe) {
        try {
            dao.delete(numeroActe);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Personne entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PersonneResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Personne> getAll(int maxResut){
        List<Personne> pers = dao.findAll(maxResut);
        if(pers.isEmpty()){
            // Dialog Box
            System.out.println("Aucune personne n'a encore été enregistrée dans le système");
        }
            
        return pers;
    }
    
    @Override
    public List<Personne> getAll(int firstResult, int maxResult){
        List<Personne> pers = dao.findAll(firstResult, maxResult);
        if(pers.isEmpty()){
            // Dialog Box
            System.out.println("Aucune personne n'a encore été enregistrée dans le système");
        }
            
        return pers;
    }


}
