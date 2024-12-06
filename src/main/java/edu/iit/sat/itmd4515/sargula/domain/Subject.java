/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import java.util.Objects;

/**
 * Subject entity stores the specialized subjects taught by teachers.
 *
 * @author sargula
 */
@Entity
@NamedQuery(name = "Subject.readAll", query = "select s from Subject s")
public class Subject extends AbstractEntity {
    
    private String name;

    /**
     * Default constructor
     */
    public Subject() {
    }

    /**
     * Parameterized constructor
     *
     * @param name
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * Get the value of name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

     /**
     * Set the value of name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Subject other = (Subject) obj;

        //Since we are using GeneratedValue, one more condition
        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    /**
     * Writes Subject fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + '}';
    }

}
