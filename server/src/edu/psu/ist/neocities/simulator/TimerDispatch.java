/**
 * 
 */
package edu.psu.ist.neocities.simulator;

import java.util.ArrayList;
import java.util.TimerTask;

import edu.psu.ist.neocities.control.CommController;

import edu.psu.ist.neocities.model.EnvironmentModel;
import edu.psu.ist.neocities.model.HistoryModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.model.ScoreModel;
import edu.psu.ist.neocities.model.UnitModel;
import edu.psu.ist.neocities.util.GrowthEquation;
import edu.psu.ist.neocities.value.EventVO;
import edu.psu.ist.neocities.value.ScoreVO;
import edu.psu.ist.neocities.value.UnitVO;

/**
 * @author bhellar
 * @date 2-26-09
 * 
 * This class sends out the message to the client to start or stop the timer
 */
public class TimerDispatch extends TimerTask {

	/****************************************************************
	 * Variables & Simple Constructor
	 ****************************************************************/	
	CommController comm = CommController.instance();
	ScenarioModel scenarioModel = ScenarioModel.instance();
	EnvironmentModel environmentModel = EnvironmentModel.instance();
	ScoreModel scoreModel = ScoreModel.instance();
	UnitModel uModel = UnitModel.instance();
	
	HistoryModel historyModel = HistoryModel.instance();
	
	GrowthEquation  eventGrowth = new GrowthEquation(.995, 0.0075, 0.04995); 
	
	private Simulator simParent;
	String timerMessage;
	
	
	public TimerDispatch(Simulator _simParent, String _timerMessage) {			
		simParent = _simParent;
		timerMessage = _timerMessage;
	}
	
	/****************************************************************
	 * Main Function
	 ****************************************************************/
	@Override
	public void run() {									
				
		if(timerMessage == "stop"){
			simParent.simTimer.cancel();
			this.finishScenario();
			comm.consoleMessage("!Time Limit Reached -- Simulator Finished!");
		}
		
		
		comm.sendTime(timerMessage);
	}
	
	public void finishScenario(){
		
		for(EventVO eventVO : scenarioModel.Events) {
						
			if(eventVO.isActive()) 
			{																					
				//fails all currently active events
				eventVO.setEventFailure();
				//scenarioModel.setEventStatus(eventVO.eventID, 0);
				//iModel.setIncidentStatus(eventVO.incident.geteventID(), "Failed");
				comm.consoleMessage("Event Failed: #" +eventVO.eventID);
				
				//record the final timeStep and Magnitude for the events
				eventVO.finishingTimeStep = eventVO.currentTimeStep-1;
				eventVO.finishingMagnitude = eventVO.getCurrentMagnitude();
																
				//reset dispatched units to an idle state, update the resource counter
				ArrayList<UnitVO> recalledUnits = uModel.resetEventUnits(eventVO.eventID);
				
				//calculateScores
				ScoreVO score = this.calculateScores(eventVO, false);
				
				// Update eventRecords.
				this.recordEventScores(score);
				this.recordEventHistory(score, eventVO.finishingTimeStep, eventVO.timeLimit.getTimeSteps(scenarioModel.TimeStepLength));
				
				// Send the updated event and unit information
				//comm.updateClientUnits(recalledUnits);
				comm.sendEvent(eventVO);
				
				
				
				/*
				 * 
				 * IF INTERDEPENDENT
				if(eventVO.difficulty.isInterdependent()){
					
					
					// Checking if the sufficient number of units were sent for hard order..
					
					if(historyModel.getOrderRecord(eventVO.incident.geteventID()).hardOrder==true)
					{
					
						if(historyModel.getOrderRecord(eventVO.incident.geteventID()).numCorrectOrderUnits != eventVO.difficulty.getResourceReqNum()){
											
							historyModel.hardOrderFail(eventVO.incident.geteventID());
						}
					}
					
					
					
					comm.sendOrderHistory(historyModel.getOrderRecord(eventVO.incident.geteventID()));
				}*/
			}
		}
	}
			
	//Calculate Scores
	public ScoreVO calculateScores (EventVO eventVO, Boolean status){
		int eventID = eventVO.eventID;
		int locationID = eventVO.getLocationId();
		String eventLabel = eventVO.label;
		
		
		double rawScore = eventGrowth.calculateEventArea(eventVO);
		double worstScore = eventGrowth.worstActionArea(eventVO);
		double normalScore = eventGrowth.calculateNormalizedScore(eventVO);
		String grade = eventGrowth.scoreGrader(eventVO);
		
		int numerator = 0;
		int total = 0;
		double dispatchRatio = 0;
			
		/*
		if(eventVO.difficulty.isInterdependent()){
			numerator = historyModel.getOrderRecord(eventID).numCorrectOrderUnits;
			total = historyModel.getOrderRecord(eventID).totalActions;
		}
		else {
			numerator = historyModel.getIncidentRecord(eventID).dispatchCorrect;
			total = historyModel.getIncidentRecord(eventID).dispatchCorrect + historyModel.getIncidentRecord(eventID).dispatchWrong;
		}*/
		
		if (total != 0){			
			dispatchRatio = numerator / total;
		}
				
		//in case of a miscalculation due to timeStep inaccuracy.
		if(rawScore > worstScore){
			rawScore = worstScore;  
		}		
		
		ScoreVO score = new ScoreVO(eventID, locationID, status, rawScore, worstScore, normalScore, grade, eventLabel);
		
		return score;
	}
		
	//Record the Scores in the ScoreModels
	public void recordEventScores (ScoreVO score){		
		scoreModel.data.add(score);
		scoreModel.scenarioScore += score.rawScore;
		scoreModel.scenarioTotal += score.worstScore;
		scoreModel.setTeamScore(score.normalScore);
				
		comm.sendScore(score);
	}
	
	public void recordEventHistory (ScoreVO score, int finishingTimeStep, int timeStepLimit){		
		int eventID = score.eventID;
				
		historyModel.storeEventHistory(eventID, score, finishingTimeStep, timeStepLimit);
		
		//comm.sendEventHistory(historyModel.getIncidentRecord(eventID));					
	}

}
