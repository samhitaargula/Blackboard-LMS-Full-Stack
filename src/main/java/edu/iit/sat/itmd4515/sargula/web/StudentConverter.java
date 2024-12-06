/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.service.StudentService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * StudentConverter class for JSF converter for Student.
 *
 * @author sargula
 */
@FacesConverter(value = "studentConverter", managed = true)
public class StudentConverter implements Converter<Student> {

    @EJB
    StudentService studentSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Student getAsObject(FacesContext context, UIComponent component, String value) {
        // Here, we are taking a String, and returning an Object
        return studentSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Student value) {
        // Here, we are taking an Object, and returning a String
        return String.valueOf(value.getId());
    }
}
