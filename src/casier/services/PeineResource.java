/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import casier.DAO.PeineDAO;
import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import casier.entities.Peine;
import casier.entities.Personne;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PeineResource implements Resource<Peine, Integer> {

    private final PeineDAO dao = new PeineDAO();

    @Override
    public void add(Peine entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Peine entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Peine get(Integer id) {
        Peine peine = dao.findById(id);
        if(peine == null){
            // Dialog Box
            System.out.println("Désolé cette peine n'existe pas dans le système");
        }
        return peine;
    }

    @Override
    public List<Peine> getAll() {
        List<Peine> peines = dao.findAll();
        if(peines.isEmpty()){
            // Dialog Box
            System.out.println("Aucune peine n'a encore été enregistrée dans le système");
        }
            
        return peines;
    }

    @Override
    public List<Peine> getAll(int maxResut) {
        List<Peine> peines = dao.findAll(maxResut);
        if(peines.isEmpty()){
            // Dialog Box
            System.out.println("Aucune peine n'a encore été enregistrée dans le système");
        }
            
        return peines;
    }

    @Override
    public List<Peine> getAll(int firstResult, int maxResult) {
        List<Peine> peines = dao.findAll(firstResult, maxResult);
        if(peines.isEmpty()){
            // Dialog Box
            System.out.println("Aucune peine n'a encore été enregistrée dans le système");
        }
            
        return peines;
    }

    @Override
    public void remove(Integer id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Peine entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeineResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Peine> getByPerson(Personne personne){
        List<Peine> peines = dao.findByCriteria("nummeroActe", personne.getNumeroActe());
        if(peines == null)
            System.out.println("Cette personne n'a aucune peine");
        return peines;
    }
    
}
