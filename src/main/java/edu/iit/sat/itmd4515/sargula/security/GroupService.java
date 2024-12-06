/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.security;

import edu.iit.sat.itmd4515.sargula.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * GroupService class for Group functions.
 *
 * @author sargula
 */
@Stateless
public class GroupService extends AbstractService<Group> {

    /**
     * Default no-args constructor
     */
    public GroupService() {
        super(Group.class);
    }
    
    /**
     * Method to read all groups
     *
     * @return
     */
    public List<Group> readAll(){
        return super.readAll("Group.findAll");
    }
}
