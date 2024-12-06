/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Subject;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * SubjectService class for service methods of Subject.
 *
 * @author sargula
 */
@Stateless
public class SubjectService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    /**
     * Default no-args constructor
     */
    public SubjectService() {
    }

    /**
     * Method to create Subject records.
     *
     * @param s
     */
    public void create(Subject s) {
        em.persist(s);
    }

    /**
     * Method to read Subject records.
     *
     * @param id
     * @return
     */
    public Subject read(Long id) {
        return em.find(Subject.class, id);
    }

    /**
     * Method to update Subject records.
     *
     * @param s
     */
    public void update(Subject s) {
        em.merge(s);
    }

    /**
     * Method to delete Subject records.
     *
     * @param s
     */
    public void delete(Subject s) {
        em.remove(em.merge(s));
    }
    
    /**
     * Method to read all Subject records.
     *
     * @return
     */
    public List<Subject> readAll(){
        //return em.createQuery("select s from Subject s", Subject.class).getResultList();
        return em.createNamedQuery("Subject.readAll", Subject.class).getResultList();
    }
}
