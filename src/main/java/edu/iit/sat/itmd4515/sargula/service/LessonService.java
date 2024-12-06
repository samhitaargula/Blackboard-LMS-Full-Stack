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
 * LessonService class for service functions of Lesson.
 *
 * @author sargula
 */
@Named
@Stateless
public class LessonService extends AbstractService<Lesson>{

    /**
     * Default no-args constructor
     */
    public LessonService() {
        super(Lesson.class);
    }
    
    /**
     * Method to read All Lesson records
     *
     * @return
     */
    public List<Lesson> readAll(){
        return super.readAll("Lesson.readAll");
    }

    /**
     * service to expose all possible lesson types for drop-down use
     * 
     * @return
     */
    public LessonType[] getAllLessonTypes() {
        return LessonType.values();
    }
    
    /**
     * Method to create Lesson For Authenticated Teacher
     *
     * @param lesson
     * @param student
     */
    public void createLessonForAuthenticatedTeacher(Lesson lesson, Teacher student) {
        em.persist(lesson);
        
        Teacher teacherRef = em.getReference(Teacher.class, student.getId());
        teacherRef.addTeacherLesson(lesson);
        em.merge(teacherRef);
    }
    
    /**
     * Method to edit Lesson For Authenticated Teacher
     *
     * @param lesson
     */
    public void editLessonForAuthenticatedTeacher(Lesson lesson) {
        Lesson managedRef = em.getReference(Lesson.class, lesson.getId());
        
        managedRef.setTitle(lesson.getTitle());
        managedRef.setLessonDate(lesson.getLessonDate());
        managedRef.setType(lesson.getType());
        
        em.merge(managedRef);
    }
    
    /**
     * Method to delete Lesson For Authenticated Teacher
     *
     * @param lesson
     * @param teacher
     */
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
