/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Teacher extends AbstractPerson {

    /**
     * OneToOne uni-directional relationship
     *
     */
    @OneToOne
    @JoinColumn(name = "TEACHER_SUBJECT")
    private Subject subject;

    /**
     * OneToMany bi-directional relationship
     * Assignment is owning side
     * Teacher is inverse side
     *
     * This is inverse side
     *
     */
    @OneToMany(mappedBy = "teacher")
    private List<Assignment> assignments = new ArrayList<>();

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
     * Get the value of assignments
     *
     * @return List<Assignment>
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Set the value of subject
     *
     * @param assignments
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Writes Teacher fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", name=" + name + ", subject=" + subject + '}';
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
}
