/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.domain.Subject;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sargula
 */
public class LessonJPARelationshipTest extends AbstractJPATest {

    @Test
    public void uniDirectionalTestCase() {
        Subject subject = new Subject("Math");
        Teacher teacher = new Teacher("Math Teacher");
        teacher.setSubject(subject);

        tx.begin();
        em.persist(subject);
        em.persist(teacher);
        tx.commit();

        System.out.println(subject.toString());
        System.out.println(teacher.toString());

        Teacher readBackFromDatabase = em.find(Teacher.class, teacher.getId());
        assertNotNull(readBackFromDatabase.getSubject());
        assertEquals("Math", readBackFromDatabase.getSubject().getName());

        Subject remove1 = em.createQuery("select s from Subject s where s.name = 'Math'", Subject.class).getSingleResult();
        Teacher remove2 = em.createQuery("select t from Teacher t where t.name = 'Math Teacher'", Teacher.class).getSingleResult();

        tx.begin();
        em.remove(remove1);
        em.remove(remove2);
        tx.commit();

    }

    @Test
    public void biDirectionalTestCase() {
        Student s = new Student("Sam Student", "samstu@iit.edu");
        Lesson l = new Lesson("Test Lesson", LocalDate.of(2024, 9, 30), LessonType.INTRO);

        s.addStudentLesson(l);

        tx.begin();
        em.persist(l);
        em.persist(s);
        tx.commit();

        System.out.println("Navigating M:M bidirectional relationship from owning side:\t" + l.getStudents().toString());
        System.out.println("Navigating M:M bidirectional relationship from inverse side:\t" + s.getLessons().toString());

        Student readBackFromDatabase = em.find(Student.class, s.getId());
        assertTrue(s.getLessons().size() == 1);
        assertTrue(l.getStudents().size() == 1);

        assertEquals("Sam Student", readBackFromDatabase.getName());
        assertTrue(readBackFromDatabase.getLessons().contains(l));

        tx.begin();
        // first unset any relationships before removing data 
        s.removeStudentLesson(l);
        // remove non-owning entity first
        em.remove(l);
        // and then owning entity
        em.remove(s);

        tx.commit();
    }
}
