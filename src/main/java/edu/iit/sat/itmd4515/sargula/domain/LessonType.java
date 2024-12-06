/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

/**
 * LessonType is the type of a Lesson.
 * It is used with @Enumerated in Lesson class.
 *
 * @author sargula
 */
public enum LessonType {
    INTRO("Intro"),
    MEDIUM("Medium"),
    ADVANCED("Advanced");
    
    private String label;

    /**
     * Method to get Lesson type
     */
    private LessonType(String label) {
        this.label = label;
    }
    
    /**
     * Method to get Lesson label
     */
    private String getLabel(){
        return label;
    }
}
