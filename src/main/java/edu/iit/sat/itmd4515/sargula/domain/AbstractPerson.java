/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

/**
 * AbstractPerson class to store specific fields that can be reused by people
 * entities in the domain.
 *
 * @author sargula
 */
@MappedSuperclass
public class AbstractPerson extends AbstractEntity {

    @NotBlank
    protected String name;

    /**
     * Default no-args constructor
     */
    public AbstractPerson() {
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
    
}
