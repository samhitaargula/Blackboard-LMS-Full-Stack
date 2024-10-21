/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author sargula
 */
@Stateless
public class StudentService extends AbstractService<Student>{
    
    public StudentService() {
        super(Student.class);
    }
    
    public List<Student> readAll(){
        return super.readAll("Student.readAll");
    }
}
