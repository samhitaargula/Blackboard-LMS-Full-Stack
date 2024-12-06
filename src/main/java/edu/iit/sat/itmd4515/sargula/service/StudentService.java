/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.security.Group;
import edu.iit.sat.itmd4515.sargula.security.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 * StudentService class for service methods of Student.
 *
 * @author sargula
 */
@Named
@Stateless
public class StudentService extends AbstractService<Student>{
    
    /**
     * Default no-args constructor
     */
    public StudentService() {
        super(Student.class);
    }
    
    /**
     * Method to read all Student records.
     *
     * @return
     */
    public List<Student> readAll(){
        return super.readAll("Student.readAll");
    }
    
    /**
     * Method to find Student record by Username
     *
     * @param uname
     * @return
     */
    public Student findByUsername(String uname){
        return em.createNamedQuery("Student.findByUsername", Student.class).setParameter("uname", uname).getSingleResult();
    }
    
    /**
     * Method to add users and groups on new Student on Signup
     *
     * @param s
     * @return
     */
    public boolean newStudentSignup(Student s) {
        Group studentGroup
                = em.createQuery("select g from Group g where g.groupName = 'STUDENT_GROUP'", Group.class).getSingleResult();
        User newStudentUser = s.getUser();
        newStudentUser.addGroup(studentGroup);
        em.persist(newStudentUser);
        
        s.setUser(newStudentUser);
        em.persist(s);
        
        return em.contains(s);
    }
}
