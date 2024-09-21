/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula;

import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sargula
 */
public class LessonJPATest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Lesson lesson = new Lesson("Test Data", LocalDate.of(2024, 9, 19), LessonType.INTRO);
        tx.begin();
        em.persist(lesson);
        tx.commit();
        
        System.out.println("Before each - create lesson\t" + lesson.getTitle());
    }

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

    @Test
    public void deleteTest() throws SQLException {
        Lesson lesson = em.createQuery("select l from Lesson l where l.title = 'Lesson 2 - OOPs concepts'", Lesson.class).getSingleResult();
        
        System.out.println("Delete lesson \t" + lesson.toString());
        
        tx.begin();
        em.remove(lesson);
        tx.commit();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        Lesson lesson = em.createQuery("select l from Lesson l where l.title = 'Test Data'", Lesson.class).getSingleResult();
        
        System.out.println("Before each - delete lesson\t" + lesson.getTitle());
        
        tx.begin();
        em.remove(lesson);
        tx.commit();
        
        em.close();
    }

    @AfterAll
    public static void afterAll() {
        emf.close();
    }
}
