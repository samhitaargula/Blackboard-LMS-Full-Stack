/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.service;

import edu.iit.sat.itmd4515.sargula.domain.Assignment;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author sargula
 */
@Stateless
public class AssignmentService extends AbstractService<Assignment>{
    
    public AssignmentService() {
        super(Assignment.class);
    }
    
    public List<Assignment> readAll(){
        return super.readAll("Assignment.readAll");
    }
}
