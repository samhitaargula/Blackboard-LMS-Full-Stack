/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

/**
 *
 * @author sargula
 */
public enum LessonType {
    INTRO("Intro"),
    MEDIUM("Medium"),
    ADVANCED("Advanced");
    
    private String label;

    private LessonType(String label) {
        this.label = label;
    }
    
    private String getLabel(){
        return label;
    }
}
