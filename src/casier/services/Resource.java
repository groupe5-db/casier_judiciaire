/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.services;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author armel
 * @param <T> Entity class
 * @param <ID> type of Id of entity class
 */
public interface Resource<T, ID extends Serializable> {
    
    void add(T entity);
    
    void update(T entity);
    
    T get(ID id);
    
    List<T> getAll();
    
    List<T> getAll(int maxResut);
    
    List<T> getAll(int firstResult, int maxResult);
    
    void remove(ID id);
    
    void remove(T entity);
}
