/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author sargula
 */
@Stateless
public class AssignmentService extends AbstractService<Assignment> {

    public AssignmentService() {
        super(Assignment.class);
    }

    public List<Assignment> readAll() {
        return super.readAll("Assignment.readAll");
    }

    public List<Assignment> getAssignmentsForTeacher(Teacher teacher) {
        Teacher managedTeacherRef = em.getReference(Teacher.class, teacher.getId());

        return em.createNamedQuery("Assignment.findAssignmentsForTeacher", Assignment.class).setParameter("teacherid", managedTeacherRef.getId()).getResultList();
    }
    
    public void submitAssignment(Assignment assignment) {
        Assignment newAssignment = new Assignment(assignment.getTitle(), assignment.getDate(), assignment.getTime(), assignment.getGrade());

        newAssignment.submitAssignment(
                em.getReference(Student.class, assignment.getStudent().getId()),
                em.getReference(Lesson.class, assignment.getLesson().getId())
        );

        em.persist(newAssignment);
    }

    public void editAssignment(Assignment assignment) {
        Assignment managedRef = em.getReference(Assignment.class, assignment.getId());

        managedRef.setTitle(assignment.getTitle());
        managedRef.setGrade(assignment.getGrade());
        managedRef.setDate(assignment.getDate());
        managedRef.setTime(assignment.getTime());
        managedRef.setStudent(assignment.getStudent());
        managedRef.setLesson(assignment.getLesson());

        em.merge(managedRef);
    }

    public void deleteAssignment(Assignment assignment) {
        Assignment managedAssignmentRef = em.getReference(Assignment.class, assignment.getId());
        managedAssignmentRef.deleteAssignment();
        em.remove(managedAssignmentRef);
    }
}
