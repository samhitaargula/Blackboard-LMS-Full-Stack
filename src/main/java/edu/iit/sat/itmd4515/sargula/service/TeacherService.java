/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
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
    
    public Teacher findByUsername(String uname){
        return em.createNamedQuery("Teacher.findByUsername", Teacher.class).setParameter("uname", uname).getSingleResult();
    }
    
    public void createLessonForAuthenticatedTeacher(Lesson lesson, Teacher student) {
        em.persist(lesson);
        
        Teacher teacherRef = em.getReference(Teacher.class, student.getId());
        teacherRef.addTeacherLesson(lesson);
        em.merge(teacherRef);
    }
}
