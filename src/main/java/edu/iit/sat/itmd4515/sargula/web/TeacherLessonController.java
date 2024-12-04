/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.service.LessonService;
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
public class TeacherLessonController {

    @EJB LessonService lessonSvc;
//    @EJB TeacherService teacherSvc;
    @Inject TeacherWelcomeController twc;
    
    private static final Logger LOG = Logger.getLogger(TeacherLessonController.class.getName());

    private Lesson lesson;

    public TeacherLessonController() {
    }

    @PostConstruct
    private void postContruct() {
        LOG.info("Inside TeacherLessonController.postContruct");
        lesson = new Lesson();
    }
    
    public String displayLessonDetailsPage(Lesson l){
        this.lesson = l;
        LOG.info("inside displayLessonDetailsPage with Lesson " + lesson.toString());
        return "/teacher/lessonDetails.xhtml";
    }

    public String displayEditLessonPage(Lesson l){
        this.lesson = l;
        LOG.info("inside displayEditLessonPage with Lesson " + lesson.toString());
        return "/teacher/editLesson.xhtml";
    }

    public String displayDeleteLessonPage(Lesson l){
        this.lesson = l;
        LOG.info("inside displayDeleteLessonPage with Lesson " + lesson.toString());
        return "/teacher/deleteLesson.xhtml";
    }

    public String displayAddLessonPage(){
        LOG.info("inside displayAddLessonPage");
        return "/teacher/createLesson.xhtml";
    }
    
    public String saveLesson(){
        LOG.info("Inside TeacherLessonController.saveLesson with " + lesson.toString());
        //lessonSvc.create(lesson);
        lessonSvc.createLessonForAuthenticatedTeacher(lesson, twc.getTeacher());
        LOG.info("Inside TeacherLessonController.saveLesson after call to service " + lesson.toString());
        
        return "/teacher/welcome.xhtml?faces-redirect=true";
    }
    
    public String editLesson(){
        LOG.info("Inside TeacherLessonController.editLesson() before call to service: " + lesson.toString());
        lessonSvc.editLessonForAuthenticatedTeacher(lesson);
        
        return "/teacher/welcome.xhtml?faces-redirect=true";
    }

    public String deleteLesson(){
        LOG.info("Inside TeacherLessonController.deleteLesson() before call to service: " + lesson.toString());
        lessonSvc.deleteLessonForAuthenticatedTeacher(lesson, twc.getTeacher());
        
        return "/teacher/welcome.xhtml?faces-redirect=true";
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
