/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.DAO;

import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import casier.entities.Personne;
import java.io.Serializable;

/**
 *
 * @author armel
 */
public class PersonneDAO extends AbstractDAO<Personne, String>implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param personne
     * @throws PreexistingEntityException
     * @throws Exception
     */
    @Override
    public void create(Personne personne) throws PreexistingEntityException, Exception {
        try {
            super.create(personne);
        } catch (Exception ex) {
            if (findById(personne.getNumeroActe()) != null) {
                throw new PreexistingEntityException("Désolé cette personne existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(Personne personne) throws NonexistentEntityException, Exception {
        try {
            super.update(personne);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personne.getNumeroActe();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé cette personne n'existe pas");
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
     public void update(String numeroActe) throws NonexistentEntityException, Exception {
        try {
            super.update(numeroActe);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(numeroActe) == null) {
                    throw new NonexistentEntityException("Désolé la personne ayant ce numero d'acte de naissance n'existe pas");
                }
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    /**
     *
     * @param numeroActe
     * @throws NonexistentEntityException
     */
    @Override
    public void delete(String numeroActe) throws NonexistentEntityException {
        try {
            super.delete(numeroActe);
            
            } catch (Exception ex) {
                if(findById(numeroActe) == null){
                    throw new NonexistentEntityException("Désolé cette personne n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    @Override
    public void delete(Personne personne) throws NonexistentEntityException {
        try {
            super.delete(personne);
            
            } catch (Exception ex) {
                String id = personne.getNumeroActe();
                if(findById(id) == null)
                    throw new NonexistentEntityException("Désolé cette personne n'existe pas: impossible de la supprimer");
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }

    
}
