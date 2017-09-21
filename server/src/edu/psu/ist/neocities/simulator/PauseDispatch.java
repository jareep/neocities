/**
 * 
 */
package edu.psu.ist.neocities.simulator;

import java.util.TimerTask;

import edu.psu.ist.neocities.value.PauseVO;
import edu.psu.ist.neocities.control.CommController;

/**
 * @author bhellar
 * @date 1-06-09
 * 
 * This class sends out the message to the client to temporarily pause the simulation, switching it to a different screen
 * NOTE: This is not a true pause, Events will still fire if scheduled during the "pause" event
 */
public class PauseDispatch extends TimerTask {

	/****************************************************************
	 * Variables & Simple Constructor
	 ****************************************************************/	
	CommController comm = CommController.instance();
	
	PauseVO pause;
	
	public PauseDispatch(PauseVO _pause) {			
		pause = _pause;
	}
	
	/****************************************************************
	 * Main Function
	 ****************************************************************/
	@Override
	public void run() {									
		comm.sendChatMSG("DS - Now Pausing the Simulation");
		comm.sendPause(pause);		
	}

}
