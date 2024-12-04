/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author sargula
 */
@Entity
@NamedQuery(name = "Assignment.readAll", query = "select a from Assignment a")
@NamedQuery(name = "Assignment.findAssignmentsForLesson", query="select a from Assignment a where a.lesson.id = :lessonid")
@NamedQuery(name = "Assignment.findAssignmentsForTeacher", query="select a from Assignment a where a.lesson.teacher.id = :teacherid")
public class Assignment extends AbstractEntity {

    @PastOrPresent
    private LocalDate date;
    private LocalTime time;

    /**
     * ManyToOne bi-directional relationship Assignment is owning side Student
     * is inverse side
     *
     * This is owning side
     *
     */
    @ManyToOne
    private Student student;

    /**
     * ManyToOne bi-directional relationship Assignment is owning side Teacher
     * is inverse side
     *
     * This is owning side
     *
     */
    //Removed because Teacher Assignments have mapping with Lesson in common
//    @ManyToOne
//    private Teacher teacher;

    /**
     * OneToOne uni-directional relationship
     * Assignment is owning side
     * Lesson is inverse side
     *
     * This is owning side
     *
     */
    @OneToOne
    private Lesson lesson;

    private String title;
    private char grade;

    /**
     * Default constructor
     */
    public Assignment() {
    }

    /**
     * Parameterized constructor
     *
     * @param title
     * @param date
     * @param time
     * @param grade
     */
    public Assignment(String title, LocalDate date, LocalTime time, char grade) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.grade = grade;
    }
    

    public void submitAssignment(Student s, Lesson l) { //, Teacher t
        this.student = s;
//        this.teacher = t;
        this.lesson = l;

        if (!s.getAssignments().contains(this)) {
            s.getAssignments().add(this);
        }
//        if (!t.getAssignments().contains(this)) {
//            t.getAssignments().add(this);
//        }
    }

    public void deleteAssignment() {
        if (this.student.getAssignments().contains(this)) {
            this.student.getAssignments().remove(this);
        }
//        if (this.teacher.getAssignments().contains(this)) {
//            this.teacher.getAssignments().remove(this);
//        }

        this.student = null;
//        this.teacher = null;
        this.lesson = null;
    }

    /**
     * Get the value of date
     *
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the value of time
     *
     * @return LocalTime
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Set the value of time
     *
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Get the value of student
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the value of student
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Get the value of lesson
     *
     * @return Lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Set the value of lesson
     *
     * @param lesson
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Writes Assignment fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Assignment{" + "date=" + date + ", time=" + time + ", student=" + student + ", lesson=" + lesson + ", grade=" + grade + '}'; //", teacher=" + teacher +
    }

    /**
     * Method to get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Equals method to compare objects
     *
     * @param obj
     * @return Boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Assignment other = (Assignment) obj;

        //Since we are using GeneratedValue, one more condition
        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
