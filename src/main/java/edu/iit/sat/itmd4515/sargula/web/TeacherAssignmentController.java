/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.service.AssignmentService;
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
public class TeacherAssignmentController {

    private static final Logger LOG = Logger.getLogger(TeacherAssignmentController.class.getName());
    private Assignment assignment;

    @Inject
    TeacherWelcomeController twc;
    @EJB
    AssignmentService assignmentSvc;

    public TeacherAssignmentController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside TeacherAssignmentController");

        assignment = new Assignment();
        assignment.setLesson(new Lesson());
        assignment.setStudent(new Student());
        assignment.getLesson().setTeacher(twc.getTeacher());
        //assignment.setTeacher(twc.getTeacher());
        LOG.info("Inside TeacherAssignmentController postConstruct() with " + assignment.toString());
    }

    public String displayGradeAssignmentPage() {
        return "/teacher/gradeAssignment.xhtml";
    }

    public String displayGradeAssignmentPage(Lesson l) {
        assignment.setLesson(l);
        LOG.info("Inside displayGradeAssignmentPage with " + assignment.toString());

        return "/teacher/gradeAssignment.xhtml";
    }

    public String displayAssignmentDetailsPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayAssignmentDetailsPage with " + assignment.toString());

        return "/teacher/assignmentDetails.xhtml";
    }

    public String displayEditAssignmentPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayEditAssignmentPage with " + assignment.toString());

        return "/teacher/editAssignment.xhtml";
    }

    public String displayDeleteAssignmentPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayDeleteAssignmentPage with " + assignment.toString());

        return "/teacher/deleteAssignment.xhtml";
    }

    public String gradeAssignment() {
        LOG.info("Inside uploadAssignment with " + assignment.getLesson().getTeacher());
        LOG.info(assignment.toString());

        assignmentSvc.submitAssignment(assignment);

        return "/teacher/welcome.xhtml?faces-redirect=true";
    }

    public String editAssignment() {
        LOG.info("Inside TeacherAssignmentController.editAssignment() before call to service: " + assignment.toString());
        assignmentSvc.editAssignment(assignment);

        return "/teacher/welcome.xhtml?faces-redirect=true";
    }

    public String deleteAssignment() {
        LOG.info("Inside TeacherAssignmentController.deleteAssignment() before call to service: " + assignment.toString());
        assignmentSvc.deleteAssignment(assignment);

        return "/teacher/welcome.xhtml?faces-redirect=true";
    }

    /**
     * Get the value of assignment
     *
     * @return the value of assignment
     */
    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * Set the value of assignment
     *
     * @param assignment new value of assignment
     */
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

}
