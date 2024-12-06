/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import edu.iit.sat.itmd4515.sargula.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * LoginController is a lightweight JSF controller which manages
 * the logic of login to the LMS.
 *
 * @author sargula
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    FacesContext facesContext;
    @Inject
    SecurityContext securityContext;

    private User user;

    /**
     * Default no-args constructor
     */
    public LoginController() {
    }

    /**
     * Method to initialize the model
     */
    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside LoginController.postConstruct()");
        user = new User();
    }
    
    /**
     * Method to get Authenticated Username
     *
     * @return
     */
    public String getAuthenticatedUsername(){
        return securityContext.getCallerPrincipal().getName();
    }
    
    /**
     * Method to check if user role is Teacher
     *
     * @return
     */
    public boolean isTeacher(){
        return securityContext.isCallerInRole("TEACHER_ROLE");
    }

    /**
     * Method to check if user role is Student
     *
     * @return
     */
    public boolean isStudent(){
        return securityContext.isCallerInRole("STUDENT_ROLE");
    }

    /**
     * Method to check if user role is Admin
     *
     * @return
     */
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    /**
     * Method to login to the LMS
     *
     * @return
     */
    public String doLogin() {

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        Credential cred = new UsernamePasswordCredential(this.user.getUsername(), new Password(this.user.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(cred));

        switch (status) {
            case SUCCESS:
                LOG.info(status.toString());
                break;
            case SEND_FAILURE:
                LOG.info("FAILURE! " + status.toString());
                return "/error.xhtml";
            case NOT_DONE:
                LOG.info("NOT DONE! " + status.toString());
                return "/error.xhtml";
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;
        }

        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     * Method to logout of the LMS
     *
     * @return
     */
    public String doLogout() {
        LOG.info("LoginController.doLogout()");

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return "/login.xhtml";
    }

    /**
     * Method to get User
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Method to set User
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
