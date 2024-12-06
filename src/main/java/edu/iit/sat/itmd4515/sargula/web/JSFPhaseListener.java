/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.web;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import java.util.logging.Logger;

/**
 * JSFPhaseListener class to add logs at beginning and end of life cycle phases.
 *
 * @author sargula
 */
public class JSFPhaseListener implements PhaseListener {
    private static final Logger LOG = Logger.getLogger(JSFPhaseListener.class.getName());

    /**
     * Get the phase id
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
    /**
     * Method for before phase
     *
     * @param event
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        
        if( event.getPhaseId() == PhaseId.RESTORE_VIEW ) {
            LOG.info("===================== NEW JSF REQUEST STARTING ======================");
        }
        
        LOG.info("Before JSF Phase =================> " + event.getPhaseId());
    }
    
    /**
     * Method for after phase
     *
     * @param event
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("After JSF Phase =================> " + event.getPhaseId());
        
        if( event.getPhaseId() == PhaseId.RENDER_RESPONSE ) {
            LOG.info("===================== JSF REQUEST ENDING ======================");
        }
        
    }
}
