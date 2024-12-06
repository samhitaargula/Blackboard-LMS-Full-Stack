/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * AbstractService class for storing abstract methods that can be reused by  
 * any class.
 *
 * @author sargula
 * @param <T>
 */
public class AbstractService<T> {
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    protected Class<T> entityClass;
    
    /**
     * Parameterized constructor
     * 
     * @param entityClass
     */
    protected AbstractService(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    /**
     * Method to create an object record.
     *
     * @param e
     */
    public void create(T e) {
        em.persist(e);
    }

    /**
     * Method to read an object record.
     *
     * @param id
     * @return
     */
    public T read(Long id) {
        return em.find(entityClass, id);
    }

    /**
     * Method to update an object record.
     *
     * @param e
     */
    public void update(T e) {
        em.merge(e);
    }

    /**
     * Method to delete an object record.
     *
     * @param e
     */
    public void delete(T e) {
        em.remove(em.merge(e));
    }
    
    /**
     * Method to read all object records.
     *
     * @param namedQueryName
     * @return
     */
    protected List<T> readAll(String namedQueryName){
        return em.createNamedQuery(namedQueryName, entityClass).getResultList();
    }
}
