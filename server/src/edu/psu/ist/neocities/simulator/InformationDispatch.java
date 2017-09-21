package edu.psu.ist.neocities.simulator;

import java.util.TimerTask;

import edu.psu.ist.neocities.value.InformationVO;
import edu.psu.ist.neocities.control.CommController;

/**
 * @author vfm
 * @date 6-9-11
 * 
 * This class sends a briefing or news clip to the participants
 */
public class InformationDispatch extends TimerTask {

    /****************************************************************
     * Variables & Simple Constructor
     ****************************************************************/  
    CommController comm = CommController.instance();
    
    InformationVO information;
    
    public InformationDispatch(InformationVO _information) {          
        information = _information;
    }
    
    /****************************************************************
     * Main Function
     ****************************************************************/
    @Override
    public void run() {                                 
        comm.sendInformation(information);      
    }

}