package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.OldValue.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description Stores the types of difficulty used by the EventModel
 */
public final class DifficultyModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static DifficultyModel instance = new DifficultyModel();
	
	private DifficultyModel() {
		// Required for Singleton Design Pattern
	}
			
	public static DifficultyModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<DifficultyVO> data = new ArrayList<DifficultyVO>();	
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	//previously used by Simulator to obtain difficulty for an event
	public DifficultyVO getDifficulty ( int difficultyID ) {
		DifficultyVO dif = data.get(0);
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getDifficultyID() == difficultyID){
				dif = data.get(i);
				break;
			}
		}
		return dif;
	}
	
	//currently used by Simulator to obtain difficulty for an event
	public DifficultyVO getDifficultyOf ( int incidentID ) {
		DifficultyVO dif = data.get(0);
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				dif = data.get(i);
				break;
			}
		}
		return dif;
	}
	
	//used by NCServer to add an answer to a difficulty
	public void setDifficultyAnswer ( int difficultyID, AnswerVO answer ) {
				
		for (DifficultyVO eventD : data){
			if (eventD.getDifficultyID() == difficultyID){
				eventD.setNewAnswer(answer);
				
				if(answer.expected > 0){
					eventD.setUnitCapBound(true);
					eventD.setResourceReqNum(eventD.getResourceReqNum() + answer.expected);
				}
			
				break;	
			}//end diff check			
		}//end loop	
	}
		
	
	//used by NCServer to set timeLimit of the event
	public void setEventTimeLimit( int difficultyID, int minute, int seconds){
		
		long timeLimit = ((minute * 60) + seconds) * 1000;
		int timeStepLimit = (int) timeLimit / 3000;
				
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getDifficultyID() == difficultyID){				
				
				long dispatchTime = data.get(i).getDispatchTime();
				int dispatchTimeStep = (int) dispatchTime / 3000;
				int timeStepDuration = timeStepLimit - dispatchTimeStep;
												
				data.get(i).setTimeStepLimit( timeStepDuration );
				//System.out.println("TimeStep Limit: " + timeStepLimit + " - " + dispatchTimeStep + " = " + timeStepDuration );
				break;
			}
		}
	}
	
	//used by NCServer to set dispatchTime of the event
	public void setEventDispatchTime( int difficultyID, int minute, int seconds){
		
		long ms = ((minute * 60) + seconds) * 1000;
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getDifficultyID() == difficultyID){				
				data.get(i).setDispatchTime(ms);
				break;
			}
		}
	}
}
