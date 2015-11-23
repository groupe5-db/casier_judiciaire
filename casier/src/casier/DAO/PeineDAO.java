/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.DAO;

import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import casier.entities.Peine;
import java.io.Serializable;

/**
 *
 * @author armel
 */
public class PeineDAO extends AbstractDAO<Peine, Integer>implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void create(Peine peine) throws PreexistingEntityException, Exception {
        try {
            super.create(peine);
        } catch (Exception ex) {
            if (findById(peine.getId()) != null) {
                throw new PreexistingEntityException("Désolé cette peine existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(Peine peine) throws NonexistentEntityException, Exception {
        try {
            super.update(peine);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = peine.getId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé cette peine n'existe pas");
                }
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    @Override
     public void update(Integer id) throws NonexistentEntityException, Exception {
        try {
            super.update(id);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé la peine ayant cet identifient n'existe pas");
                }
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void delete(Integer id) throws NonexistentEntityException {
        try {
            super.delete(id);
            
            } catch (Exception ex) {
                if(findById(id) == null){
                    throw new NonexistentEntityException("Désolé cette peine n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
}
