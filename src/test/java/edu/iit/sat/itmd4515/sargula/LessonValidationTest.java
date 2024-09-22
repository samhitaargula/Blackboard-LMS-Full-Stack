/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sargula
 */
public class LessonValidationTest {
    private static Validator validator;
    private Lesson l = new Lesson("Test", LocalDate.of(2022,12,20), LessonType.INTRO);

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    public void lessonTitleValid() {
        System.out.println("Lesson title valid: " + l.toString());
        
        Set<ConstraintViolation<Lesson>> violations = validator.validate(l);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void lessonTitleInvalid() {
        l.setTitle(" ");
        System.out.println("Lesson title invalid: " + l.toString());
        
        Set<ConstraintViolation<Lesson>> violations = validator.validate(l);
        assertEquals(1, violations.size());
        assertEquals( "must not be blank",violations.iterator().next().getMessage());
    }
    
    @Test
    public void lessonDateValid() {
        System.out.println("Lesson date valid: " + l.toString());
        
        Set<ConstraintViolation<Lesson>> violations = validator.validate(l);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void lessonDateInvalid() {
        l.setLessonDate(LocalDate.of(2026,12,20));
        System.out.println("Lesson date invalid: " + l.toString());
        
        Set<ConstraintViolation<Lesson>> violations = validator.validate(l);
        assertEquals(1, violations.size());
        assertEquals( "must be a date in the past or in the present",violations.iterator().next().getMessage());
    }

    @AfterEach
    public void afterEach() {
    }

    @AfterAll
    public static void afterAll() {
    }
}
