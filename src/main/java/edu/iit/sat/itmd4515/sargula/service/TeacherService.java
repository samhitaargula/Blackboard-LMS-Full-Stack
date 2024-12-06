/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 * TeacherService class for service methods of Teacher.
 *
 * @author sargula
 */
@Named
@Stateless
public class TeacherService extends AbstractService<Teacher>{
    
    /**
     * Default no-args constructor
     */
    public TeacherService() {
        super(Teacher.class);
    }
    
    /**
     * Method to read all Teacher records.
     *
     * @return
     */
    public List<Teacher> readAll(){
        return super.readAll("Teacher.readAll");
    }
    
    /**
     * Method to find Teacher record by Username
     *
     * @param uname
     * @return
     */
    public Teacher findByUsername(String uname){
        return em.createNamedQuery("Teacher.findByUsername", Teacher.class).setParameter("uname", uname).getSingleResult();
    }
    
}
