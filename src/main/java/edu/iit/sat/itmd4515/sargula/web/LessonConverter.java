/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.service.LessonService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author sargula
 */
@FacesConverter(value = "lessonConverter", managed = true)
public class LessonConverter implements Converter<Lesson> {

    @EJB LessonService lessonService;
    
    @Override
    public Lesson getAsObject(FacesContext fc, UIComponent uic, String value) {
        // Here, we are taking a String, and returning an Object
        return lessonService.read(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Lesson value) {
        // Here, we are taking an Object, and returning a String
        return String.valueOf(value.getId());
    }
    
}
