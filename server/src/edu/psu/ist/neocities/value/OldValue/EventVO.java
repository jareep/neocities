package edu.psu.ist.neocities.value.OldValue;

import edu.psu.ist.neocities.model.OldModel.*;
import edu.psu.ist.neocities.value.OldValue.*;

import java.util.ArrayList;

public class EventVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	
	public IncidentVO incident;
	public DifficultyVO difficulty;
		
	private Boolean active = Boolean.FALSE; //by default, when this item is created, the event should not be active
	private int timeStep = 0;  /* The current-time-step of the event. 
	This should get incremented after the eventGrowth() is called/completed */
					
	// The following variables can be manipulated from eventGrowth(). 
	public ArrayList<Double> magnitudeArray = new ArrayList<Double>(); // series of magnitudes the event went through..
		 
	public double magCurrent = 0; // Current-magnitude of the event    
	public int finishingTimeStep; // The last time-step at which a magnitude was calculated. 
	public double finishingMagnitude; // magnitudeArray[finishingMagnitude] should give the same value
		
	public int goodResources = 0;
		
	public int condition = -1;  // Explained below	
	/* 	Condition has values indicating the status of the event: 
   
	     * -1 = event yet to be initialized
	     * 0 = failure 
	     * 1 = event still active
	     * 2 = success 
	*/
	
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public EventVO() {
		// TODO Auto-generated constructor stub
	}
	
	//example 1, "Easy", 2 
	public EventVO
	(		
		IncidentVO _incident, 
		DifficultyVO _difficulty 
	) 
	{
		incident = _incident;
		difficulty = _difficulty;
		
	//	pendingArrival = new ArrayList<UnitVO>();
		
	}
    
	/****************************************************************
	 * Functions
	 ****************************************************************/
	public int getTimeStep(){
		return timeStep;
	}
	
	public void incrementTimeStep(){
		timeStep++;
	}
	
	//used by EventModel to see if event is active
	public Boolean isActive(){
		
		if(condition == 1){
			active = true;
		} else {
			active = false;
		}
		
		return active;
	}
	
	public void setActive(){		
		condition = 1;
		active = true;
		incident.setStatus("New");
	}
	
	public void setEventComplete(){
		condition = 2;
		active = false;
		incident.setStatus("Complete");
	}
	
	public void setEventFailure(){
		condition = 0;
		active = false;		
		incident.setStatus("Failed");
	}
	
	/****************************************************************
	 * Check Answer Functions
	 ****************************************************************/
	//Checks to see if the passed in resource matches an answer for this event. 
	public Boolean checkAnswer(int resourceID) {
		//start by assuming this resource is wrong (does not match an answer)
		Boolean unitMatch = Boolean.FALSE; 
		
		//loop over the correct answers for the scoreEvent 
		AnswerLoop:
		for (int j = 0; j < difficulty.answers.size(); j++){
			//comm.consoleMessage("Answer ---- Unit Resource = " + unitArray.get(i).resourceID + " , Answer = " + eventAnswers.get(j).resourceID);
			if(difficulty.answers.get(j).resourceID == resourceID){
				unitMatch = Boolean.TRUE;
				break AnswerLoop;
			}			
		}//end of j loop 
		
		return unitMatch;
	}
	
	public Boolean checkRoleAnswer (int roleID) {
	    ResourceModel rModel = ResourceModel.instance();
	    Boolean unitMatch = Boolean.FALSE; 
	    
	    AnswerLoop:
	        for (int j = 0; j < difficulty.answers.size(); j++){
	            //comm.consoleMessage("Answer ---- Unit Resource = " + unitArray.get(i).resourceID + " , Answer = " + eventAnswers.get(j).resourceID);
	            if(rModel.getRoleOfResource(difficulty.answers.get(j).resourceID) == roleID){
	                unitMatch = Boolean.TRUE;
	                break AnswerLoop;
	            }           
	        }//end of j loop 
	        
	        return unitMatch;
	}
	
	//Function assumes that UnitMatch performed above = true
	public AnswerVO getAnswer(int resourceID) {
				
		AnswerVO currentAnswer = new AnswerVO();
		
		//loop over the correct answers for the scoreEvent 
		AnswerLoop:
		for (int j = 0; j < difficulty.answers.size(); j++){			
			if(difficulty.answers.get(j).resourceID == resourceID){
				currentAnswer = difficulty.answers.get(j);
				break AnswerLoop;
			}			
		}//end of j loop 
		
		return currentAnswer;
		
	}
	
	public AnswerVO getAnswerByRole(int roleID)
	{
	       ResourceModel rModel = ResourceModel.instance();
	       AnswerVO currentAnswer = new AnswerVO();
	        
	        AnswerLoop:
	            for (int j = 0; j < difficulty.answers.size(); j++){
	                //comm.consoleMessage("Answer ---- Unit Resource = " + unitArray.get(i).resourceID + " , Answer = " + eventAnswers.get(j).resourceID);
	                if(rModel.getRoleOfResource(difficulty.answers.get(j).resourceID) == roleID){
	                    currentAnswer = difficulty.answers.get(j);
	                    break AnswerLoop;
	                }           
	            }//end of j loop 
	            
	       return currentAnswer;
	}
	
	//Function decrements the current answer tally
	public void decrementAnswerTotal(int resourceID){
	
		AnswerLoop:
		for (int j = 0; j <= difficulty.answers.size() - 1; j++){
			if (difficulty.answers.get(j).resourceID == resourceID ){
				difficulty.answers.get(j).allocated--;
				break AnswerLoop;
			}//end of AnswerMatch
		}//end of answerloop
	}
	
	//Function decrements the current answer tally
	public void incrementAnswerTotal(int resourceID){
	
		AnswerLoop:
		for (int j = 0; j <= difficulty.answers.size() - 1; j++){
			if (difficulty.answers.get(j).resourceID == resourceID ){				
				difficulty.answers.get(j).allocated++;	
				break AnswerLoop;
			}//end of AnswerMatch
		}//end of answerloop
	}		
	   
} // ending scoreEventVO
