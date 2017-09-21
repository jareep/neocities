 package edu.psu.ist.neocities.simulator;

import edu.psu.ist.neocities.model.*;
import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.control.*;
import edu.psu.ist.neocities.value.HistoryValue.*;


import java.util.Timer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bhellar
 *	This class maintains the state of the world in neocities, 
 *  firing off events on a time-line.
 */
public class Simulator {

	/****************************************************************
	 * Variables
	 ****************************************************************/
//	IncidentModel iModel;
	//DifficultyModel dModel = DifficultyModel.instance();
	//EventModel eventModel = EventModel.instance();
	
	ScenarioModel scenarioModel = ScenarioModel.instance();
	EnvironmentModel environmentModel = EnvironmentModel.instance();
	
//	PauseModel pauseModel = PauseModel.instance();
//	BriefingModel briefingModel = BriefingModel.instance();
	HistoryModel historymodel = HistoryModel.instance();
	CommController comm = CommController.instance();
	
	Timer simTimer = new Timer();
	Timer clockTimer = new Timer();
	String lastEventLabel = "";
	
	
	/****************************************************************
	 * NON Singleton Constructor (We Want to be able to have multiple instances)
	 ****************************************************************/
	
	public Simulator() {
		comm.consoleMessage("Simulator online");
		//iModel = IncidentModel.instance();
	}			
				
	/****************************************************************
	 * Functions
	 ****************************************************************/
	public void start(){						
		//IncidentVO incident;
		//DifficultyVO difficulty;
		EventVO eventVO;
		
		//ArrayList<Integer> scenarioIncidents = scenarioModel.activeScenario.getIncidents();
		
		List<EventVO> scenarioEvents = scenarioModel.getAllEvents();// 
	 	
		//Establishes the Scenario Timeline based off of the selectedScenario in the scenarioModel;
	     
		
		for(int i=0; i < scenarioEvents.size(); i++){						
			
			eventVO = scenarioEvents.get(i);
			
			//prep the ScoringModel
			
			scenarioModel.resetMasterTimeStep(); // why? 
			
			
			simTimer.schedule(new EventDispatch(eventVO), eventVO.getDispatchTimeMS());
			System.out.println("Event # " + eventVO.getId() + " Scheduled at " + eventVO.getDispatchTimeMS());
			
			//initialize the data for Event History Model
			
			//historymodel.eventHistory.add(new EventHistoryVO(incident.getIncidentID(), difficulty.getDispatchTimeStep()));
			historymodel.eventHistory.add(new EventHistoryVO(eventVO.getId(), eventVO.getDispatchTime()));
			
			// the above requires historyModel revision..
			
			//initialize the data for the Order History Model (for interdepdent events only!)
			/*if(difficulty.isInterdependent()){
				historymodel.orderHistory.add(new OrderHistoryVO(incident.getIncidentID(), difficulty.getResourceReqNum()));
			}*/
			
		}// ending i loop				
		
		//Establishes when the scenario should "Pause", switch screens to prompt a temporary message for survey data collection
		comm.consoleMessage("Simulator - Loading Scenario Pauses");
		
		
		List<PauseVO> pauseList = scenarioModel.getAllPauses();
		
		if (scenarioModel.Pauses != null)
		{
			comm.consoleMessage("Scenario Pauses: " + pauseList.size());
		}
		if(!pauseList.isEmpty()){
			
			for(int i=0; i < pauseList.size(); i++){	
				//PauseVO pause = pauseModel.getPause(pauseList.get(i));				
				
				PauseVO pauseVO = pauseList.get(i);	
			
				simTimer.schedule(new PauseDispatch(pauseVO), pauseVO.getPauseTime().calcTotalMS());				    
			}
		}
		
				
		
		// Adding information-dispatch
		
		List<InformationVO> informationList = scenarioModel.getAllInformation();
		
		if(!informationList.isEmpty()){
			
			for(int i =0; i<informationList.size(); i++){
				
					InformationVO informationVO = informationList.get(i);
					simTimer.schedule(new InformationDispatch(informationVO), informationVO.getDispatchTime().calcTotalMS());
				
			}// ending i loop
			
		}// ending informationLsit check
		
		
		//stop the client timer if the game has a timelimit 
		comm.consoleMessage("Simulator - Setting Game Time Limit");
	 
	// The if condition below may not be necessary:
	//	if (scenarioModel.activeScenario.isGameTimeBound()){
			//simTimer.schedule(new TimerDispatch(this, "stop"), scenarioModel.activeScenario.getGameStopTime()); //stop the game clock after set duration
			
			simTimer.schedule(new TimerDispatch(this, "stop"), scenarioModel.getScenarioLengthMS());
			
//		}
		
		//start the client timer (gameclock)
		comm.consoleMessage("Simulator - Starting the Client Clock");
		simTimer.schedule(new TimerDispatch(this, "start"), 0); //start the clock after 1 second has passed
		
		
		//new recurring timer task that calculates and checks scores at the defined interval timestep.
		comm.consoleMessage("Simulator - Starting the EventController. 1 Timestep = " + scenarioModel.TimeStepLength + " Seconds");
		
		simTimer.scheduleAtFixedRate(new EventController(), scenarioModel.getTSLengthInMS(), scenarioModel.getTSLengthInMS()); //start at 3 seconds, repeat every 3 seconds
	
	}
	
	/*
	private void syncClocks(long timeSteps)
	{
		
	}*/
	
	public void stop(){
		//simTimer.schedule(new TimerDispatch(this, "stop"), 0); //stop the game asap!
		comm.consoleMessage("SIM - Simulator Offline");
		simTimer.cancel();	
	}
		
	public void pause(){
		try {
			simTimer.wait();
			comm.consoleMessage("SIM - Pausing Simulator");
		}
		catch (InterruptedException e){
			comm.consoleMessage("SIM - IE: " + e.toString());
			
		}
	}
	
	public void resume(){
		simTimer.notifyAll();
		comm.consoleMessage("SIM - Resuming Simulator");
	}
		
	//Created to Facilitate J-Unit Testing
	public String getLastEventLabel(){
		return lastEventLabel;
	}			

}
