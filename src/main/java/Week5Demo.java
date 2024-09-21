
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sargula
 */
public class Week5Demo {
    public static void main(String... args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        
        Lesson lesson = new Lesson("Intro to Java",LocalDate.of(2024,9,19), LessonType.INTRO);
        
        System.out.println("Before persist: " + lesson.toString());
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(lesson);
        tx.commit();
        
        System.out.println("After persist: " + lesson.toString());
    }
}
