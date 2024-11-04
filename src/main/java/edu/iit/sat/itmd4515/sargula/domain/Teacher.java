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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sargula
 */
@Entity
@NamedQuery(name = "Teacher.readAll", query = "select t from Teacher t")
public class Teacher extends AbstractPerson {

    /**
     * OneToOne uni-directional relationship
     *
     */
    @OneToOne
    @JoinColumn(name = "TEACHER_SUBJECT")
    private Subject subject;

    /**
     * ManyToMany bi-directional relationship Teacher is owning side Lesson is
     * inverse side
     *
     * This is owning side
    *
     */
    @ManyToMany
    @JoinTable(name = "TEACHER_LESSONS",
            joinColumns = @JoinColumn(name = "TEACHER_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID"))
    private List<Lesson> lessons = new ArrayList<>();

    /**
     * OneToMany bi-directional relationship Assignment is owning side Teacher
     * is inverse side
     *
     * This is inverse side
     *
     */
//    @OneToMany(mappedBy = "teacher")
//    private List<Assignment> assignments = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Default constructor
     */
    public Teacher() {
    }

    /**
     * Parameterized constructor
     *
     * @param name
     */
    public Teacher(String name) {
        this.name = name;
    }

    /**
     * Get the value of subject
     *
     * @return String
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Set the value of subject
     *
     * @param subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Get the value of lessons
     *
     * @return List<Lesson>
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
     * @return List<Assignment>
     */
//    public List<Assignment> getAssignments() {
//        return assignments;
//    }
//
//    /**
//     * Set the value of subject
//     *
//     * @param assignments
//     */
//    public void setAssignments(List<Assignment> assignments) {
//        this.assignments = assignments;
//    }
    /**
     * Writes Teacher fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", name=" + name + ", subject=" + subject + ", lessons=" + lessons + '}';
    }

    /**
     * Method to get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Teacher other = (Teacher) obj;

        //Since we are using GeneratedValue, one more condition
        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    /**
     * Helper method to add lesson for teacher
     *
     * @param l
     */
    public void addTeacherLesson(Lesson l) {
        if (!this.lessons.contains(l)) {
            this.lessons.add(l);
        }
        if (!l.getTeachers().contains(this)) {
            l.getTeachers().add(this);
        }
    }

    /**
     * Helper method to remove lesson for teacher
     *
     * @param l
     */
    public void removeTeacherLesson(Lesson l) {
        if (this.lessons.contains(l)) {
            this.lessons.remove(l);
        }
        if (l.getTeachers().contains(this)) {
            l.getTeachers().remove(this);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
