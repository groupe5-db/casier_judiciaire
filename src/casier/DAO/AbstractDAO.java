/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.DAO;

import casier.DAO.exceptions.NonexistentEntityException;
import casier.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public abstract class AbstractDAO<T, ID extends Serializable> implements GenericDAO<T,ID> {
    
    private Class<T> entityClass;
    private Session session = null;
    
    public AbstractDAO(){
        this.entityClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void setSession(Session session){
        this.session = session;
    }
    
    protected Session getSession(){
        if(session == null)
            session = Factory.getSessionFactory().getCurrentSession();
        return session;
    }
    
    protected Session openSession(){
            session = Factory.getSessionFactory().openSession();
        return session;
    }
    
    protected void start(){

        Transaction transaction = openSession().beginTransaction();
        transaction.toString();


    }
    
    protected void end(){
        getSession().getTransaction().commit();
        Connection connection = getSession().close();

    }
    
    private Class<T> getEntityClass(){
        return entityClass;
    }

    @Override
    public T findById(ID id) {
        T entity;
        String query = "from Personne where id = "+id;
        start();
        Query q = getSession().createQuery(query);
        entity = (T)q.uniqueResult();
        end();
        return entity;
    }

    @Override
    public void create(T entity) throws PreexistingEntityException, Exception {
        start();
        getSession().save(entity);
        end();
    }

    @Override
    public void update(T entity) throws NonexistentEntityException, Exception{
        start();
        getSession().update(entity);
        end();
    }

    @Override
    public void update(ID id) throws NonexistentEntityException, Exception{
        T entity = findById(id);
        update(entity);
    }

    @Override
    public void delete(T entity) throws NonexistentEntityException {
        start();
        getSession().delete(entity);
        end();
    }

    @Override
    public void delete(ID id) throws NonexistentEntityException {
        T entity = findById(id);
        delete(entity);
    }

    @Override
    public List<T> findAll() {
        List<T> entities;
        start();
        entities = getSession().createCriteria(getEntityClass()).list();
        end();
        
        return entities;
    }
    
    @Override
    public List<T> findByCriteria(String property, Object propertyValue){
        List<T> entities;
            start();
            Criteria critere = getSession().createCriteria(getEntityClass());
            critere.add(Restrictions.eq(property, propertyValue));
            entities = critere.list();
            end();
        return entities;
    }
    
    @Override
    public List<T> findByMultiCriterias(Map propertiesNamesValues){
        List<T> entities;
        start();
            Criteria critere = getSession().createCriteria(getEntityClass());
            critere.add(Restrictions.allEq(propertiesNamesValues));
            entities = critere.list();
            end();
        return entities;
    };

    @Override
    public int count() {
        int i = 0;
        String query = "select count(*) from "+getEntityClass();
        start();
        Query q = getSession().createQuery(query);
        i = ((Long)q.uniqueResult()).intValue();
        end();
        return i;
    }
    
    public List findProperty(String propertyName){
        List property;
        start();
            Criteria critere = getSession().createCriteria(getEntityClass());
            critere.setProjection(Projections.property(propertyName));
            property = critere.list();
            end();
            return property;
    }
    
    public List<T> findAll(int maxResult){
        List<T> entities;
        start();
        entities = getSession().createCriteria(getEntityClass()).setMaxResults(maxResult).list();
        end();
        
        return entities;
    }
    
    public List<T> findAll(int firstResult, int maxResult){
        List<T> entities;
        start();
        entities = getSession().createCriteria(getEntityClass()).setFirstResult(firstResult).setMaxResults(maxResult).list();
        end();
        
        return entities;
    }
    
}
