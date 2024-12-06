/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import edu.iit.sat.itmd4515.sargula.service.TeacherService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * TeacherConverter class for JSF converter for Teacher.
 *
 * @author sargula
 */
@FacesConverter(value = "teacherConverter", managed = true)
public class TeacherConverter implements Converter<Teacher> {

    @EJB
    TeacherService teacherSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Teacher getAsObject(FacesContext context, UIComponent component, String value) {
        // Here, we are taking a String, and returning an Object
        return teacherSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Teacher value) {
        // Here, we are taking an Object, and returning a String
        return String.valueOf(value.getId());
    }
}
