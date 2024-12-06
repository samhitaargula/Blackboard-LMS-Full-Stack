/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import jakarta.annotation.Resource;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;

/**
 * SuccessTimer class to handle timer logic after sign up to display
 * student user created success message.
 *
 * @author sargula
 */
@Stateless
public class SuccessTimer {

    @Resource
    private SessionContext context;

    /**
     * Method to create timer
     */
    public void createTimer() {
        context.getTimerService().createTimer(5000, "Student user created!");
    }

    /**
     * Method to handle the timeOut
     *
     * @param timer
     */
    @Timeout
    public void timeOutHandler(Timer timer) {
        System.out.println("TimeoutHandler : " + timer.getInfo());
        timer.cancel();
    }

}
