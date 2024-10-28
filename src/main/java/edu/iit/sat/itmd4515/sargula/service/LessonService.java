/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author sargula
 */
@Named
@Stateless
public class LessonService extends AbstractService<Lesson>{

    /**
     *
     */
    public LessonService() {
        super(Lesson.class);
    }
    
    /**
     *
     * @return
     */
    public List<Lesson> readAll(){
        return super.readAll("Lesson.readAll");
    }

    /**
     * service to expose all possible lesson types for drop-down use
     * @return
     */
    public LessonType[] getAllLessonTypes() {
        return LessonType.values();
    }
}
