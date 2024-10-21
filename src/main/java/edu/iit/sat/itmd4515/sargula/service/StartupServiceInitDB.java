/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import edu.iit.sat.itmd4515.sargula.domain.Lesson;
import edu.iit.sat.itmd4515.sargula.domain.LessonType;
import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.domain.Subject;
import edu.iit.sat.itmd4515.sargula.domain.Teacher;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

/**
 *
 * @author sargula
 */
@Startup
@Singleton
public class StartupServiceInitDB {

    private static final Logger LOG = Logger.getLogger(StartupServiceInitDB.class.getName());
    
    @EJB SubjectService subSvc;
    @EJB LessonService lessonSvc;
    @EJB TeacherService teacherSvc;
    @EJB StudentService studentSvc;
    @EJB AssignmentService assnSvc;

    public StartupServiceInitDB() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside StartupServiceInitDB.postConstruct()");
        
        Subject s1 = new Subject("Java");
        Subject s2 = new Subject("Algorithms");
        Subject s3 = new Subject("Design");
        
        subSvc.create(s1);
        subSvc.create(s2);
        subSvc.create(s3);
        
        Lesson l1 = new Lesson("Intro to Java",LocalDate.of(2024, 10, 1),LessonType.INTRO);
        Lesson l2 = new Lesson("Java History",LocalDate.of(2024, 10, 10),LessonType.INTRO);
        Lesson l3 = new Lesson("OOPs concepts",LocalDate.of(2024, 10, 15),LessonType.INTRO);
        Lesson l4 = new Lesson("Analysis of Algorithms",LocalDate.of(2024, 10, 1),LessonType.MEDIUM);
        Lesson l5 = new Lesson("Design Principles",LocalDate.of(2024, 10, 10),LessonType.MEDIUM);
        
        lessonSvc.create(l1);
        lessonSvc.create(l2);
        lessonSvc.create(l3);
        lessonSvc.create(l4);
        lessonSvc.create(l5);
        
        Teacher t1 = new Teacher("Java Teacher");
        t1.setSubject(s1);
        Teacher t2 = new Teacher("Algorithms Teacher");
        t2.setSubject(s2);
        Teacher t3 = new Teacher("Design Teacher");
        t3.setSubject(s3);
        
        teacherSvc.create(t1);
        teacherSvc.create(t2);
        teacherSvc.create(t3);
        
        Student stu1 = new Student("Java Student", "stu1@mail.com");
        stu1.addStudentLesson(l1);
        stu1.addStudentLesson(l2);
        stu1.addStudentLesson(l3);
        
        Student stu2 = new Student("Algorithms Student", "stu2@mail.com");
        stu2.addStudentLesson(l4);
        
        Student stu3 = new Student("Design Student", "stu3@mail.com");
        stu3.addStudentLesson(l5);
        
        studentSvc.create(stu1);
        studentSvc.create(stu2);
        studentSvc.create(stu3);
        
        Assignment a1 = new Assignment(LocalDate.of(2024, 12, 20), LocalTime.of(9, 30), true, "A");
        a1.uploadAssignment(stu1, l1, t1);
        Assignment a2 = new Assignment(LocalDate.of(2024, 12, 30), LocalTime.of(9, 30), true, "B");
        a2.uploadAssignment(stu2, l4, t2);
        Assignment a3 = new Assignment(LocalDate.of(2024, 12, 20), LocalTime.of(9, 30), false, "F");
        a3.uploadAssignment(stu3, l5, t3);
        
        assnSvc.create(a1);
        assnSvc.create(a2);
        assnSvc.create(a3);
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Output Sample data");
        LOG.info("-----------------------------------------------------------------------------\n");
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Subjects");
        LOG.info("-----------------------------------------------------------------------------");
        for(Subject s : subSvc.readAll()){
            LOG.info(s.toString() + "\n");
        }
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Lessons");
        LOG.info("-----------------------------------------------------------------------------");
        for(Lesson l : lessonSvc.readAll()){
            LOG.info(l.toString());
            
            LOG.info("\tBi-directional relationship from Lesson (inverse) to Student >>>>>>>>>");
            for( Student s : l.getStudents()){
                LOG.info("\t" + s.toString()+ "\n");
            }
        }
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Teachers");
        LOG.info("-----------------------------------------------------------------------------");
        for(Teacher t : teacherSvc.readAll()){
            LOG.info(t.toString());
            
            LOG.info("\tUnidirectional relationship from Teacher (owner) to Subject >>>>>>>>>");
            LOG.info("\t" + t.getSubject().toString() + "\n");
            
            LOG.info("\tBi-directional relationship from Teacher (inverse) to Assignment >>>>>>>>>");
            for( Assignment a : t.getAssignments()){
                LOG.info("\t" + a.toString() + "\n");
            }

            LOG.info("-----------------------------------------------------------------------------");
        }
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Students");
        LOG.info("-----------------------------------------------------------------------------");
        for(Student s : studentSvc.readAll()){
            LOG.info(s.toString());
            
            LOG.info("\tBi-directional relationship from Student (owner) to Lesson >>>>>>>>>");
            for( Lesson l : s.getLessons()){
                LOG.info("\t" + l.toString() + "\n");
            }
            
            LOG.info("\tBi-directional relationship from Student (inverse) to Assignment >>>>>>>>>");
            for( Assignment a : s.getAssignments()){
                LOG.info("\t" + a.toString() + "\n");
            }
            
            LOG.info("-----------------------------------------------------------------------------");
        }
        
        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Assignments");
        LOG.info("-----------------------------------------------------------------------------");
        for(Assignment a : assnSvc.readAll()){
            LOG.info(a.toString());
            
            LOG.info("\tUnidirectional relationship from Assignment (owner) to Lesson >>>>>>>>>");
            LOG.info("\t" + a.getLesson().toString() + "\n");
            
            LOG.info("\tBi-directional relationship from Assignment (owner) to Teacher >>>>>>>>>");
            LOG.info("\t" + a.getTeacher().toString() + "\n");
            
            LOG.info("\tBi-directional relationship from Assignment (owner) to Student >>>>>>>>>");
            LOG.info("\t" + a.getStudent().toString() + "\n");

            LOG.info("-----------------------------------------------------------------------------");
        }
    }
}
