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
import edu.iit.sat.itmd4515.sargula.security.Group;
import edu.iit.sat.itmd4515.sargula.security.GroupService;
import edu.iit.sat.itmd4515.sargula.security.User;
import edu.iit.sat.itmd4515.sargula.security.UserService;
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

    @EJB
    SubjectService subSvc;
    @EJB
    LessonService lessonSvc;
    @EJB
    TeacherService teacherSvc;
    @EJB
    StudentService studentSvc;
    @EJB
    AssignmentService assnSvc;

    //security services
    @EJB
    UserService userSvc;
    @EJB
    GroupService groupSvc;

    public StartupServiceInitDB() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside StartupServiceInitDB.postConstruct()");

        //security initialization
        Group studentGroup = new Group("STUDENT_GROUP", "Group of students");
        Group teacherGroup = new Group("TEACHER_GROUP", "Group of teachers");
        Group adminGroup = new Group("ADMIN_GROUP", "Group of admins");
        groupSvc.create(studentGroup);
        groupSvc.create(teacherGroup);
        groupSvc.create(adminGroup);
        
        User teacher1 = new User("teacher1", "teacher1");
        teacher1.addGroup(teacherGroup);
        teacher1.addGroup(adminGroup);
        
        User teacher2 = new User("teacher2", "teacher2");
        teacher2.addGroup(teacherGroup);
        teacher2.addGroup(studentGroup);

        User student1 = new User("student1", "student1");
        student1.addGroup(studentGroup);
        
        User student2 = new User("student2", "student2");
        student2.addGroup(studentGroup);
        
        User admin = new User("admin", "admin");
        admin.addGroup(adminGroup);
        
        userSvc.create(teacher1);
        userSvc.create(teacher2);
        userSvc.create(student1);
        userSvc.create(student2);
        userSvc.create(admin);
        
        
        Subject s1 = new Subject("Java");
        Subject s2 = new Subject("Algorithms");
        Subject s3 = new Subject("Design");

        subSvc.create(s1);
        subSvc.create(s2);
        subSvc.create(s3);

        Lesson l1 = new Lesson("Intro to Java", LocalDate.of(2024, 10, 1), LessonType.INTRO);
        Lesson l2 = new Lesson("Java History", LocalDate.of(2024, 10, 10), LessonType.INTRO);
        Lesson l3 = new Lesson("OOPs concepts", LocalDate.of(2024, 10, 15), LessonType.INTRO);
        Lesson l4 = new Lesson("Analysis of Algorithms", LocalDate.of(2024, 10, 1), LessonType.MEDIUM);
        Lesson l5 = new Lesson("Design Principles", LocalDate.of(2024, 10, 10), LessonType.MEDIUM);

        lessonSvc.create(l1);
        lessonSvc.create(l2);
        lessonSvc.create(l3);
        lessonSvc.create(l4);
        lessonSvc.create(l5);

        Teacher t1 = new Teacher("Java Teacher");
        t1.setSubject(s1);
        t1.setUser(teacher1);
        Teacher t2 = new Teacher("Algorithms Teacher");
        t2.setSubject(s2);
        t2.setUser(teacher2);
        Teacher t3 = new Teacher("Design Teacher");
        t3.setSubject(s3);

        t1.addTeacherLesson(l1);
        t1.addTeacherLesson(l2);
        t1.addTeacherLesson(l3);
        t2.addTeacherLesson(l4);
        t3.addTeacherLesson(l5);

        teacherSvc.create(t1);
        teacherSvc.create(t2);
        teacherSvc.create(t3);

        Student stu1 = new Student("Java Student", "stu1@mail.com");
        stu1.addStudentLesson(l1);
        stu1.addStudentLesson(l2);
        stu1.addStudentLesson(l3);
        stu1.setUser(student1);

        Student stu2 = new Student("Algorithms Student", "stu2@mail.com");
        stu2.addStudentLesson(l4);

        Student stu3 = new Student("Design Student", "stu3@mail.com");
        stu3.addStudentLesson(l5);
        stu3.setUser(student2);
        
        //Design student who is also TA for Algo
        Student stu4 = new Student("Teaching Assistant Student", "stu4@mail.com");
        stu4.addStudentLesson(l5);
        stu4.setUser(teacher2);

        studentSvc.create(stu1);
        studentSvc.create(stu2);
        studentSvc.create(stu3);
        studentSvc.create(stu4);

        Assignment a1 = new Assignment("Java Assignment", LocalDate.of(2024, 11, 15), LocalTime.of(9, 30), 'A');
        a1.submitAssignment(stu1, l1);
        Assignment a2 = new Assignment("Java History Assignment", LocalDate.of(2024, 11, 20), LocalTime.of(9, 30), 'B');
        a2.submitAssignment(stu1, l2);
        Assignment a3 = new Assignment("OOPs Assignment", LocalDate.of(2024, 11, 30), LocalTime.of(9, 30), 'C');
        a3.submitAssignment(stu1, l3);
        Assignment a4 = new Assignment("Algorithms Assignment", LocalDate.of(2024, 11, 30), LocalTime.of(9, 30), 'D');
        a4.submitAssignment(stu2, l4);
        Assignment a5 = new Assignment("Design Assignment", LocalDate.of(2024, 11, 20), LocalTime.of(9, 30), 'F');
        a5.submitAssignment(stu3, l5);

        assnSvc.create(a1);
        assnSvc.create(a2);
        assnSvc.create(a3);
        assnSvc.create(a4);
        assnSvc.create(a5);

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Output Sample data");
        LOG.info("-----------------------------------------------------------------------------\n");

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Subjects");
        LOG.info("-----------------------------------------------------------------------------");
        for (Subject s : subSvc.readAll()) {
            LOG.info(s.toString() + "\n");
        }

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Lessons");
        for (Lesson l : lessonSvc.readAll()) {
            LOG.info("-----------------------------------------------------------------------------");
            LOG.info(l.toString());

            LOG.info("\tBi-directional relationship from Lesson (inverse) to Student >>>>>>>>>");
            for (Student s : l.getStudents()) {
                LOG.info("\t" + s.toString() + "\n");
            }

            LOG.info("\tBi-directional relationship from Lesson (inverse) to Teacher >>>>>>>>>");
//            for (Teacher t : l.getTeacher()) {
                LOG.info("\t" + l.getTeacher().toString() + "\n");
//            }
        }

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Teachers");
        for (Teacher t : teacherSvc.readAll()) {
            LOG.info("-----------------------------------------------------------------------------");
            LOG.info(t.toString());

            LOG.info("\tUnidirectional relationship from Teacher (owner) to Subject >>>>>>>>>");
            LOG.info("\t" + t.getSubject().toString() + "\n");

            LOG.info("\tBi-directional relationship from Teacher (inverse) to Lesson >>>>>>>>>");
            for (Lesson l : t.getLessons()) {
                LOG.info("\t" + l.toString());
            }
            LOG.info("\n");
        }

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Students");
        for (Student s : studentSvc.readAll()) {
            LOG.info("-----------------------------------------------------------------------------");
            LOG.info(s.toString());

            LOG.info("\tBi-directional relationship from Student (owner) to Lesson >>>>>>>>>");
            for (Lesson l : s.getLessons()) {
                LOG.info("\t" + l.toString());
            }

            LOG.info("\n");

            LOG.info("\tBi-directional relationship from Student (inverse) to Assignment >>>>>>>>>");
            for (Assignment a : s.getAssignments()) {
                LOG.info("\t" + a.toString());
            }

            LOG.info("\n");

        }

        LOG.info("-----------------------------------------------------------------------------");
        LOG.info("Assignments");
        for (Assignment a : assnSvc.readAll()) {
            LOG.info("-----------------------------------------------------------------------------");
            LOG.info(a.toString());

            LOG.info("\tUnidirectional relationship from Assignment (owner) to Lesson >>>>>>>>>");
            LOG.info("\t" + a.getLesson().toString() + "\n");

            LOG.info("\tBi-directional relationship from Assignment (owner) to Student >>>>>>>>>");
            LOG.info("\t" + a.getStudent().toString() + "\n");
        }
        LOG.info("-----------------------------------------------------------------------------");
    }
}
