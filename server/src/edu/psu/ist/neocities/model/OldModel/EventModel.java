package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.control.*;
import edu.psu.ist.neocities.value.OldValue.*;
/**
 * @author bhellar
 * @date 9-11-08
 * @description stores the active incidents that are being scored,
 *   will also contain the functions for scoring calculations
 */
public final class EventModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static EventModel instance = new EventModel();	
	
	private EventModel() {
		// Required for Singleton Design Pattern
	}
			
	public static EventModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	CommController comm = CommController.instance();
	UnitModel uModel = UnitModel.instance();
	
	public List<EventVO> data = new ArrayList<EventVO>();	
		
	private int masterTimeStep = 0; //timeStep counter for the entire simulation
	
	/****************************************************************
	 * TimeStep Management Functions
	 ****************************************************************/
	//Used by DispatchEvent and CloseEvent, indicates the matching incident is now Active 	
	public void setActiveIncident(IncidentVO incident){
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).incident.getIncidentID() == incident.getIncidentID()){							
				data.get(i).setActive();
				comm.consoleMessage("Event - " + data.get(i).incident.getLabel() + " is now ACTIVE");								
				break;
			}
		}		
	}
	
	//used by EventController to Increment the timeStep of all active events
	public void incrementActiveEvents(){
		
		for (EventVO eventVO : data){
			if (eventVO.isActive()){
				//increment TimeStep for Active Events
				eventVO.incrementTimeStep();
				
			} //active check			
		}//event loop
		
	}
	
	//decrement the timeStep counter for all active interdependent events with pacing
	public void decrementEventPacing(){
		for (EventVO eventVO : data){
			if (eventVO.isActive() && eventVO.difficulty.isInterdependent() && eventVO.difficulty.isPacing()){
				eventVO.difficulty.decrementPacing();					
			}
		}//event loop
	}
	
	
	public void incrementTimeStep(){
		masterTimeStep++;
	}
	
	public int getCurrentTimeStep(){
		return masterTimeStep;
	}
	
	public void resetMasterTimeStep(){
		masterTimeStep = 0;
	}
	
	public int getEventTimeStep(int incidentID){		
		int timeStep = 0;
		
		for (EventVO event : data){
			if (event.incident.getIncidentID() == incidentID){
				timeStep = event.getTimeStep();
			}
		}
		
		return timeStep;	
	}
	
	/****************************************************************
	 * Event Resource Functions
	 ****************************************************************/
	
	//used by EventController to return Answers based on provided incident
	public ArrayList<AnswerVO> getAnswersOfIncident(int incidentID){
		
		ArrayList<AnswerVO> eventAnswers = new ArrayList<AnswerVO>();
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).incident.getIncidentID() == incidentID){
				eventAnswers = data.get(i).difficulty.getAnswers();
			}
		}
		
		return eventAnswers;
	}
	
	//used by EventController to return Answers based on provided difficulty
	public ArrayList<AnswerVO> getAnswersOfDifficulty(int difficultyID){
		ArrayList<AnswerVO> eventAnswers = new ArrayList<AnswerVO>();
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).difficulty.getDifficultyID() == difficultyID){
				eventAnswers = data.get(i).difficulty.getAnswers();
			}
		}
		
		return eventAnswers;
	}
	
	//used by EventController to add a Good Resource to a ScoreEvent
	@SuppressWarnings("unused")
	public void addGoodResources(int incidentID, int resourceID){
			
		EventLoop:
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).incident.getIncidentID() == incidentID){
				data.get(i).goodResources++;				
				data.get(i).incrementAnswerTotal(resourceID);
							
			}//end of eventMatch
	
		}//end of eventLoop
		
	} // addGoodResources..
	
	
	@SuppressWarnings("unused")
	public void removeGoodResources(int incidentID, int resourceID){
		
		EventLoop:
		for (int i = 0; i <= data.size() - 1; i++){	
			if (data.get(i).incident.getIncidentID() == incidentID){
				data.get(i).goodResources--;				
			}			
		}//end of eventloop	
	} // removeGoodResources
		
	//used by NCServer to return the appropriate EventVO
	public EventVO getScoreEvent(int incidentID){
		EventVO eventVO = new EventVO();
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).incident.getIncidentID() == incidentID){
				eventVO = data.get(i);
				break;
			}
		}
		
		return eventVO;
	}
				
	/****************************************************************
	 * Event Growth Functions
	 ****************************************************************/   
   
    public double calculateEventScore(EventVO eventVO)
    {
    	double summation = 0.0;
    	
    	for(int t =0; t <= eventVO.finishingTimeStep ; t++){
    	 	
    	        summation = summation + eventVO.magnitudeArray.get(t);		
    		
    	} // ending t loop
        
    	return summation;
    	
    } // ending calculateEventScore()
    
	
	
}// ending EventModel
