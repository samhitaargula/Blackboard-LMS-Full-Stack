/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author sargula
 */
@Stateless
public class LessonService extends AbstractService<Lesson>{

    public LessonService() {
        super(Lesson.class);
    }
    
    public List<Lesson> readAll(){
        return super.readAll("Lesson.readAll");
    }
}
