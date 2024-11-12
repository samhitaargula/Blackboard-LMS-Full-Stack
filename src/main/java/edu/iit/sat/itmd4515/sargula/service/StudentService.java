/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author sargula
 */
@Named
@Stateless
public class StudentService extends AbstractService<Student>{
    
    public StudentService() {
        super(Student.class);
    }
    
    public List<Student> readAll(){
        return super.readAll("Student.readAll");
    }
    
    public Student findByUsername(String uname){
        return em.createNamedQuery("Student.findByUsername", Student.class).setParameter("uname", uname).getSingleResult();
    }
    
}
