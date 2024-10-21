/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author sargula
 */
@Stateless
public class TeacherService extends AbstractService<Teacher>{
    
    public TeacherService() {
        super(Teacher.class);
    }
    
    public List<Teacher> readAll(){
        return super.readAll("Teacher.readAll");
    }
}
