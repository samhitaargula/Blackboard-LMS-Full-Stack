/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.service.LessonService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author sargula
 */
@Named
@RequestScoped
public class LessonController {

    @EJB LessonService lessonSvc;
    
    private static final Logger LOG = Logger.getLogger(LessonController.class.getName());

    private Lesson lesson;

    public LessonController() {
    }

    @PostConstruct
    private void postContruct() {
        LOG.info("Inside LessonController.postContruct");
        lesson = new Lesson();
    }
    
    public String saveLesson(){
        LOG.info("Inside LessonController.saveLesson with " + lesson.toString());
        lessonSvc.create(lesson);
        LOG.info("Inside LessonController.saveLesson after call to service " + lesson.toString());
        
        return "confirmation.xhtml";
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
