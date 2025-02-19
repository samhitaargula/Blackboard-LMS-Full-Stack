/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import edu.iit.sat.itmd4515.sargula.security.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Student is a person entity, who is the learner in our LMS.
 *
 * @author sargula
 */
@Entity
@NamedQuery(name = "Student.readAll", query = "select s from Student s")
@NamedQuery(name = "Student.findByUsername", query = "select s from Student s where s.user.username = :uname")
public class Student extends AbstractPerson {

    @NotBlank
    private String email;

    /**
     * ManyToMany bi-directional relationship Student is owning side Lesson is
     * inverse side
     *
     * This is owning side
     *
     */
    @ManyToMany
    @JoinTable(name = "STUDENT_LESSONS",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID"))
    private List<Lesson> lessons = new ArrayList<>();

    /**
     * OneToMany bi-directional relationship Assignment is owning side Student
     * is inverse side
     *
     * This is inverse side
     *
     */
    @OneToMany(mappedBy = "student")
    private List<Assignment> assignments = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * Parameterized constructor
     *
     * @param name
     * @param email
     */
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Helper method to add lesson for student
     *
     * @param l
     */
    public void addStudentLesson(Lesson l) {
        if (!this.lessons.contains(l)) {
            this.lessons.add(l);
        }
        if (!l.getStudents().contains(this)) {
            l.getStudents().add(this);
        }
    }

    /**
     * Helper method to 
     * lesson for student
     *
     * @param l
     */
    public void removeStudentLesson(Lesson l) {
        if (this.lessons.contains(l)) {
            this.lessons.remove(l);
        }
        if (l.getStudents().contains(this)) {
            l.getStudents().remove(this);
        }
    }

    /**
     * Get the value of email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of lessons
     *
     * @return List of lessons
     */
    public List<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Set the value of lessons
     *
     * @param lessons
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Get the value of assignments
     *
     * @return List of assignments
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Set the value of assignments
     *
     * @param assignments
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Writes Student fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", email=" + email + ", lessons=" + lessons + '}';
    }

    /**
     * Method to get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Student other = (Student) obj;

        //Since we are using GeneratedValue, one more condition
        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    /**
     * Get the value of User
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of User
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
