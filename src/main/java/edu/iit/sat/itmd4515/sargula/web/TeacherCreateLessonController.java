/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.service.TeacherService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author sargula
 */
@Named
@RequestScoped
public class TeacherCreateLessonController {

    //@EJB LessonService lessonSvc;
    @EJB TeacherService teacherSvc;
    @Inject TeacherWelcomeController twc;
    
    private static final Logger LOG = Logger.getLogger(TeacherCreateLessonController.class.getName());

    private Lesson lesson;

    public TeacherCreateLessonController() {
    }

    @PostConstruct
    private void postContruct() {
        LOG.info("Inside LessonController.postContruct");
        lesson = new Lesson();
    }
    
    public String saveLesson(){
        LOG.info("Inside LessonController.saveLesson with " + lesson.toString());
        //lessonSvc.create(lesson);
        teacherSvc.createLessonForAuthenticatedTeacher(lesson, twc.getTeacher());
        LOG.info("Inside LessonController.saveLesson after call to service " + lesson.toString());
        
        return "createLessonConfirmation.xhtml";
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
