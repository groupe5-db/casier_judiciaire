/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.DAO;

import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import casier.entities.Utilisateur;
import java.io.Serializable;

/**
 *
 * @author armel
 */
public class UtilisateurDAO extends AbstractDAO<Utilisateur, String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void create(Utilisateur utilisateur) throws PreexistingEntityException, Exception {
        try {
            super.create(utilisateur);
        } catch (Exception ex) {
            if (findById(utilisateur.getMatricule()) != null) {
                throw new PreexistingEntityException("Désolé cet utilisateur existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(Utilisateur utilisateur) throws NonexistentEntityException, Exception {
        try {
            super.update(utilisateur);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = utilisateur.getMatricule();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé cette utilisateur n'existe pas");
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
     public void update(String matricule) throws NonexistentEntityException, Exception {
        try {
            super.update(matricule);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(matricule) == null) {
                    throw new NonexistentEntityException("Désolé l'utilisateur ayant ce matricule n'existe pas");
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
    public void delete(String matricule) throws NonexistentEntityException {
        try {
            super.delete(matricule);
            
            } catch (Exception ex) {
                if(findById(matricule) == null){
                    throw new NonexistentEntityException("Désolé cet utilisateur n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }

    public static UtilisateurDAO builder(){
        return new UtilisateurDAO();
    }

    
}
