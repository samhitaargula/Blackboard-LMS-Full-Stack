/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sargula
 */
public class LessonJPATest extends AbstractJPATest {
    
    /**
     *
     * @throws SQLException
     */
    @Test
    public void createTest() throws SQLException {
        Lesson lesson = new Lesson("Lesson 1 - Introduction", LocalDate.of(2024, 9, 19), LessonType.INTRO);
        tx.begin();
        em.persist(lesson);
        tx.commit();
        
        System.out.println("Create lesson \t" + lesson.toString());
        
        Lesson readBackFromDatabase = em.find(Lesson.class, lesson.getId());
        assertNotNull(readBackFromDatabase);
        assertTrue(readBackFromDatabase.getId() > 0);
        assertEquals("Lesson 1 - Introduction", readBackFromDatabase.getTitle());
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void readTest() throws SQLException {
        Lesson lesson = new Lesson("Lesson 2 - OOPs concepts", LocalDate.of(2024, 9, 21), LessonType.INTRO);
        tx.begin();
        em.persist(lesson);
        tx.commit();
        
        System.out.println("Read lesson \t" + lesson.toString());
        
        Lesson readBackFromDatabase = em.find(Lesson.class, lesson.getId());
        assertEquals(lesson.getTitle(), readBackFromDatabase.getTitle());
        assertEquals(lesson.getType(), readBackFromDatabase.getType());
        assertEquals(LocalDate.of(2024,9,21), readBackFromDatabase.getLessonDate());
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void updateTest() throws SQLException {
        Lesson lesson = em.createQuery("select l from Lesson l where l.title = 'Test Data'", Lesson.class).getSingleResult();
        assertTrue(lesson.getId() > 0);
        
        System.out.println("Update lesson \t" + lesson.toString());
        
        tx.begin();
        lesson.setLessonDate(LocalDate.of(2024,9,21));
        tx.commit();
        
        Lesson readBackFromDatabase = em.createQuery("select l from Lesson l where l.title = 'Test Data'", Lesson.class).getSingleResult();

        assertEquals(lesson.getId(), readBackFromDatabase.getId());
        assertEquals(LocalDate.of(2024,9,21),readBackFromDatabase.getLessonDate());
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void deleteTest() throws SQLException {
        Lesson lesson = em.createQuery("select l from Lesson l where l.title = 'Lesson 2 - OOPs concepts'", Lesson.class).getSingleResult();
        
        System.out.println("Delete lesson \t" + lesson.toString());
        
        tx.begin();
        em.remove(lesson);
        tx.commit();
    }
    
}
