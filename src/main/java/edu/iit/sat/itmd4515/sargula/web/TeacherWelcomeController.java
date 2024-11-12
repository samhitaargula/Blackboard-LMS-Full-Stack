/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Teacher;
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
public class TeacherWelcomeController {

    private static final Logger LOG = Logger.getLogger(TeacherWelcomeController.class.getName());

    private Teacher teacher;

    @Inject
    LoginController loginController;
    @EJB
    TeacherService teacherSvc;

    public TeacherWelcomeController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside TeacherWelcomeController.postConstruct with " + loginController.getAuthenticatedUsername());
        teacher = teacherSvc.findByUsername(loginController.getAuthenticatedUsername());
        LOG.info("Inside TeacherWelcomeController.postConstruct with " + teacher.toString());
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
