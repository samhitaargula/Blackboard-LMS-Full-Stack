/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.security;

import edu.iit.sat.itmd4515.sargula.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * UserService class for User functions.
 *
 * @author sargula
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     * Default no-args constructor
     */
    public UserService() {
        super(User.class);
    }
    
    /**
     * Method to read all users
     *
     * @return
     */
    public List<User> readAll(){
        return super.readAll("User.findAll");
    }
}
