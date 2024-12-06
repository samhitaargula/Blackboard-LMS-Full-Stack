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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author sargula
 */
public class AbstractJPATest {

    private static EntityManagerFactory emf;

    /**
     *
     */
    protected EntityManager em;

    /**
     *
     */
    protected EntityTransaction tx;

    /**
     *
     */
    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    /**
     *
     * @throws SQLException
     */
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

    /**
     *
     * @throws SQLException
     */
    @AfterEach
    public void afterEach() throws SQLException {
        Lesson lesson = em.createQuery("select l from Lesson l where l.title = 'Test Data'", Lesson.class).getSingleResult();

        System.out.println("After each - delete lesson\t" + lesson.getTitle());

        tx.begin();
        em.remove(lesson);
        tx.commit();

        em.close();
    }

    /**
     *
     */
    @AfterAll
    public static void afterAll() {
        emf.close();
    }
}
