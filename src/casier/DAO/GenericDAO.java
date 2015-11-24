/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.DAO;

import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author armel
 * @param <T> Entity Class
 * @param <ID> Id of Entity Class
 */
public interface GenericDAO<T, ID extends Serializable> {
    
    T findById(ID id);
    
    void create(T entity) throws PreexistingEntityException, Exception;
    
    void update(T entity) throws NonexistentEntityException, Exception;
    
    void update(ID id) throws NonexistentEntityException, Exception;
    
    void delete(T entity) throws NonexistentEntityException ;
    
    void delete(ID id) throws NonexistentEntityException ;
    
    List<T> findAll();
    
    List<T> findByCriteria(String property, Object propertyValue);
    
    List<T> findByMultiCriterias(Map propertiesNamesValues);
    
    int count();
    
}
