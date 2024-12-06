/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 * StudentAssignmentController is a JSF lightweight controller which manages
 * the logic of Student welcome page.
 *
 * @author sargula
 */
@Named
@RequestScoped
public class StudentWelcomeController {
    private static final Logger LOG = Logger.getLogger(StudentWelcomeController.class.getName());

    private Student student;

    @Inject LoginController loginController;
    @EJB StudentService studentSvc;
    
    /**
     * Default no-args constructor
     */
    public StudentWelcomeController() {
    }

    /**
     * Method to initialize the model
     */
    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside StudentWelcomeController.postConstruct with " + loginController.getAuthenticatedUsername() );
        student = studentSvc.findByUsername(loginController.getAuthenticatedUsername());
        LOG.info("Inside StudentWelcomeController.postConstruct with " + student.toString() );
    }
    
    /**
     * Get the value of Student
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the value of Student
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
}
