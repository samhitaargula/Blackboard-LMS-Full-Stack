/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.domain.Student;
import edu.iit.sat.itmd4515.sargula.security.User;
import edu.iit.sat.itmd4515.sargula.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author sargula
 */
@Named
@RequestScoped
public class NewStudentSignupController {

    private static final Logger LOG = Logger.getLogger(NewStudentSignupController.class.getName());

    private Student student;
    @EJB
    StudentService studentSvc;
    @EJB
    SuccessTimer successTimer;

    /**
     *
     */
    public NewStudentSignupController() {
    }

    @PostConstruct
    private void postConstruct() {
        student = new Student();
        student.setUser(new User());
    }

    // action method
    /**
     *
     * @return
     */
    public String doStudentSignup() {
        LOG.info("Before new student signup with " + this.student.toString());

        Boolean success = studentSvc.newStudentSignup(student);
        LOG.info("Signup success: " + success);

        //Extra Credit 1: Create an EJB timer
        //context.getTimerService().createTimer(5000, "Hello World!");
        if (success) {
            successTimer.createTimer();
        }

        return "/login.xhtml?faces-redirect=true";
    }

//    @Timeout
//    public void timeOutHandler(Timer timer) {
//        System.out.println("timeoutHandler : " + timer.getInfo());
//        timer.cancel();
//    }
    /**
     * Extra Credit 2: Tried to send Mail with JavaMail Resource, didn't work
     * Referred from cloudmailin docs and other Google sources
     */
//    public void sendMail() {
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", 587);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        Authenticator auth = new Authenticator() {
//            //override the getPasswordAuthentication method
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("samstudent122024@gmail.com", "password");
//            }
//        };
//        Session session = Session.getInstance(properties, auth);
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("samstudent122024@gmail.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("samstudent@yopmail.com"));
//            message.setSubject("Blackboard Student Registration Successful!");
//            message.setText("Welcome to Blackboard! You are registered and can access your lessons now.");
//
//            Transport.send(message);
//
//            System.out.println("Email sent successfully.");
//        } catch (MessagingException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    /**
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
}
