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
 *
 * @author sargula
 */
@Stateless
public class SubjectService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public SubjectService() {
    }

    public void create(Subject s) {
        em.persist(s);
    }

    public Subject read(Long id) {
        return em.find(Subject.class, id);
    }

    public void update(Subject s) {
        em.merge(s);
    }

    public void delete(Subject s) {
        em.remove(em.merge(s));
    }
    
    public List<Subject> readAll(){
        //return em.createQuery("select s from Subject s", Subject.class).getResultList();
        return em.createNamedQuery("Subject.readAll", Subject.class).getResultList();
    }
}
