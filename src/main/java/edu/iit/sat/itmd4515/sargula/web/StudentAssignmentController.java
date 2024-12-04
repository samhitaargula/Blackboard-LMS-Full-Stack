/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
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
public class StudentAssignmentController {

    private static final Logger LOG = Logger.getLogger(StudentAssignmentController.class.getName());

    private Assignment assignment;

    @Inject
    StudentWelcomeController swc;
    @EJB
    AssignmentService assignmentSvc;

    public StudentAssignmentController() {
    }

    @PostConstruct
    private void postConstruct() {
        assignment = new Assignment();
        assignment.setLesson(new Lesson());
        assignment.setStudent(swc.getStudent());
        assignment.getLesson().setTeacher(new Teacher());
        LOG.info("Inside StudentAssignmentController postConstruct() with " + assignment.toString());

    }

    // action methods
    public String displaySubmitAssignmentPage() {
        return "/student/submitAssignment.xhtml";
    }

    public String displayAssignmentDetailsPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayAssignmentDetailsPage with " + assignment.toString());

        return "/student/assignmentDetails.xhtml";
    }

    public String displayEditAssignmentPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayEditAssignmentPage with " + assignment.toString());

        return "/student/editAssignment.xhtml";
    }

    public String displayDeleteAssignmentPage(Assignment a) {
        this.assignment = a;
        LOG.info("Inside displayDeleteAssignmentPage with " + assignment.toString());

        return "/student/deleteAssignment.xhtml";
    }

    // action methods called in the final step
    public String submitAssignment() {
        LOG.info("Inside submitAssignment with " + assignment.toString());

        assignmentSvc.submitAssignment(assignment);

        return "/student/welcome.xhtml?faces-redirect=true";
    }
    
    public String editAssignment() {
        LOG.info("Inside editAssignment() before call to service: " + assignment.toString());
        assignmentSvc.editAssignment(assignment);

        return "/student/welcome.xhtml?faces-redirect=true";
    }

    public String deleteAssignment() {
        LOG.info("Inside deleteAssignment() before call to service: " + assignment.toString());
        assignmentSvc.deleteAssignment(assignment);

        return "/student/welcome.xhtml?faces-redirect=true";
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
