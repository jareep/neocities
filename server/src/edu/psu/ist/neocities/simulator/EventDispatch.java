/**
 * 
 */
package edu.psu.ist.neocities.simulator;

import java.util.TimerTask;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.model.EnvironmentModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.value.EventVO;

/**
 * @author bhellar
 * @date 9-20-08
 * 
 * This class is instantiated for every incident(event)
 */
public class EventDispatch extends TimerTask {

	/****************************************************************
	 * Variables & Simple Constructor
	 ****************************************************************/
	EventVO event;	
	ScenarioModel scenarioModel = ScenarioModel.instance;
	EnvironmentModel environmentModel = EnvironmentModel.instance;
	/*EventModel eventModel = EventModel.instance();
	IncidentModel incidentModel = IncidentModel.instance();*/
	CommController commController = CommController.instance();
	
	public EventDispatch(EventVO _event) {			
		event = _event;
	}
	
	/****************************************************************
	 * Main Function
	 ****************************************************************/
	@Override
	public void run() {			
		//eventModel.setActiveIncident(incident);		
		/*[***] Have to write an activate event thingy in scenario model*/
		
		scenarioModel.activateEvent(event.eventID);
		environmentModel.toggleLocationEvent(event.locationId, true);
		
		commController.sendLocations(environmentModel.getLocation(event.locationId));
		//commController.sendEvent(event);
		
	}

}
