/**
 * 
 */
package edu.psu.ist.neocities.model.OldModel;

import java.util.TimerTask;

import edu.psu.ist.neocities.value.OldValue.BriefingVO;
import edu.psu.ist.neocities.control.CommController;

/**
 * @author vfm
 * @date 7-21-10
 * 
 * This class sends a briefing or news clip to the participants
 */
public class BriefingDispatch extends TimerTask {

    /****************************************************************
     * Variables & Simple Constructor
     ****************************************************************/  
    CommController comm = CommController.instance();
    
    BriefingVO briefing;
    
    public BriefingDispatch(BriefingVO _briefing) {          
        briefing = _briefing;
    }
    
    /****************************************************************
     * Main Function
     ****************************************************************/
    @Override
    public void run() {                                 
        comm.sendChatMSG("DS - Sending Briefing");
        //comm.sendBriefing(briefing);      
    }

}