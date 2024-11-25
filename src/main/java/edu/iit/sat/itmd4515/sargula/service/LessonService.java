/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author sargula
 */
@Named
@Stateless
public class LessonService extends AbstractService<Lesson>{

    /**
     *
     */
    public LessonService() {
        super(Lesson.class);
    }
    
    /**
     *
     * @return
     */
    public List<Lesson> readAll(){
        return super.readAll("Lesson.readAll");
    }

    /**
     * service to expose all possible lesson types for drop-down use
     * @return
     */
    public LessonType[] getAllLessonTypes() {
        return LessonType.values();
    }
    
    public void editLessonForAuthenticatedTeacher(Lesson lesson, Teacher student) {
        Lesson managedRef = em.getReference(Lesson.class, lesson.getId());
        
        managedRef.setTitle(lesson.getTitle());
        managedRef.setLessonDate(lesson.getLessonDate());
        managedRef.setType(lesson.getType());
        
        em.merge(managedRef);
    }
    
    public void deleteLessonForAuthenticatedTeacher(Lesson lesson, Teacher teacher) {
        Teacher managedTeacherRef = em.getReference(Teacher.class, teacher.getId());
        Lesson managedLessonRef = em.getReference(Lesson.class, lesson.getId());
        
        managedTeacherRef.removeTeacherLesson(managedLessonRef);
        em.merge(managedTeacherRef);
        
        for( Assignment a : em.createNamedQuery("Assignment.findAssignmentsForLesson", Assignment.class).setParameter("lessonid", managedLessonRef.getId()).getResultList() ){
            a.deleteAssignment();
            em.remove(a);
        }
        
        em.remove(managedLessonRef);
    }
}
