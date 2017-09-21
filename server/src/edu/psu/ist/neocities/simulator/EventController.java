package edu.psu.ist.neocities.simulator;

import java.util.ArrayList;
import java.util.TimerTask;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import edu.psu.ist.neocities.model.EnvironmentModel;
//import edu.psu.ist.neocities.model.EventModel;
//import edu.psu.ist.neocities.model.ResourceModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.model.ScoreModel;
import edu.psu.ist.neocities.model.UnitModel;
//import edu.psu.ist.neocities.model.OldModel.ResourceModel;
//import edu.psu.ist.neocities.model.IncidentModel;
import edu.psu.ist.neocities.model.HistoryModel;
import edu.psu.ist.neocities.test.TestResourceAllocation;
import edu.psu.ist.neocities.util.GrowthEquation;
import edu.psu.ist.neocities.value.EventVO;
import edu.psu.ist.neocities.value.ResourceVO;
import edu.psu.ist.neocities.value.ScoreVO;
import edu.psu.ist.neocities.value.UnitVO;
import edu.psu.ist.neocities.value.AnswerVO;

import edu.psu.ist.neocities.control.*;

/**
 * @author bhellar
 * @date 10-09-08
 *  
 * This purpose of this class is to evaluate the status of active events in the Score Event Model
 *  
 *  1. Event Resource Management
 *     - Dispatching
 *     - Answering Checking
 *     - Answer Feedback
 *  2. Score Management
 *     - Calculation of Current Magnitude
 *     - Implementation of Score Model Boundaries 
 *  3. TimeStep Management
 *     - Completes the Event Controller functions, moving the simulation forward 1 timestep.
 *  4. Event Completion Management
 *     - Checks for Success and Failure Conditions in Active Events
 */
public class EventController extends TimerTask {

	/****************************************************************
	 * Variables & Simple Constructor
	 ****************************************************************/
		
	//EventModel eventModel = EventModel.instance();//scenario-model
	ScenarioModel scenarioModel = ScenarioModel.instance();
	EnvironmentModel environmentModel = EnvironmentModel.instance();
	ScoreModel scoreModel = ScoreModel.instance();//same
	HistoryModel historyModel = HistoryModel.instance();//
	UnitModel uModel = UnitModel.instance();// need to replace unit model
	//ResourceModel rModel = ResourceModel.instance();// environment model
	
	//Dummy Event VO
	EventVO noEvent = new EventVO(-9999, -9999, 0.0, "", 0.0);
	
	
	CommController comm = CommController.instance();
	boolean newResourcesHandled = true;
	
	//defines the growthEquation and the constructor sets the three variable coefficients
	public GrowthEquation  eventGrowth = new GrowthEquation(.995, 0.0075, 0.04995); 
		
	public EventController() {			
		//null
	}
	
	
	/****************************************************************
	 * Main Function
	 ****************************************************************/
		
	@Override
	public void run() {
		
		//1. Event Resource Management
		try {
			manageEventResources();
					
		}
		catch(Exception e)
		{
			
			
			comm.consoleMessage("ERROR -  Error found in manageEventResources() function " + getStackTrace(e));
			comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
		//manageEventResources();
		
		try {
		
		//2. Score Calculations for ACTIVE Events
		manageScoring();
		}
		catch(Exception e)
		{
			comm.consoleMessage("ERROR - Error found in manageScoring() function " + getStackTrace(e));
			comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
		
		try{
		//3. Handle Completed (Success or Failure) Events
		manageEventCompletion();
		}
		catch(Exception e)
		{
			comm.consoleMessage("ERROR - Error found in manageEventCompletion() function " + getStackTrace(e));
			comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
		try{
		//4. TimeStep Management
		manageTime(); 
		}
		catch(Exception e)
		{
			comm.consoleMessage("ERROR - Error found in manageTime() function " + getStackTrace(e));
			comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
		try{
			//5. Sending Location Health Information
			
			locationHealth(); 
			
			}
		catch(Exception e)
		{
			comm.consoleMessage("ERROR - Error found in LocationHealth() function " + getStackTrace(e));
			comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
		
	} // run()..
	

		
	/****************************************************************
	 * 1. Event Resource Management
	 ****************************************************************/
     
	public void manageEventResources(){
		
		/*int activeEvents = 0;
		for(int k = 0; k< scenarioModel.Events.size(); k++) if(scenarioModel.Events.get(k).isActive()) activeEvents++;
		System.out.println("EventController:  of events active = "+activeEvents);*/
		//eventVO.checkMagComplete(scenarioModel.getMagSuccess())
		//processes units in the pendingArrival queue
		
		EventVO eventVO;
		
		for (int i = -1; i < scenarioModel.Events.size(); i++)
		{
			if (i == -1)
			{
				eventVO = this.noEvent;
				eventVO.activate();
			}
			else
			{
				eventVO = scenarioModel.Events.get(i);
			}
			 
			 if (eventVO.condition==1){// checking if the event is active								
				 	  
				 if (eventVO.eventID != -9999) { System.out.println("manageEventResources(): "+eventVO.eventID+" is active!"); }
				
				 int eventID = eventVO.getId(); 
				 
				 // Build a list of Units to be either Dispatched or Recalled				
				ArrayList <UnitVO> dispatchUnits = uModel.getDispatchEligibleUnits(eventID, eventVO.locationId);
		//		ArrayList <UnitVO> recallUnits = uModel.getRecallEligibleUnits(eventID, eventVO.locationId);
				
				// checking if there were units found..
				if(dispatchUnits.size() > 0){ 
					
					// Set the incident and unit status to On Scene and makes the unit.isPending false
					System.out.println("EventController: On Scene");
					uModel.setUnitsStatus(dispatchUnits, "On Scene");
					eventVO.setStatus("On Scene");
								
					//check to see if the allocated units were the correct answer and provide feedback
					this.checkAddition(dispatchUnits, eventVO);
					
					//send the updated units to the client
					//comm.updateClientUnits(dispatchUnits);
					if (eventVO.eventID != -9999) { comm.consoleMessage("EventController:  of good-resources for event, "+eventVO.eventID+"  , is  "+eventVO.goodResources); }
				}
				
				if (eventVO.eventID == -9999) { eventVO.condition = -1; }
				
	/*			if(recallUnits.size() > 0){
					
					System.out.println("Units were recalled ");
					//check to see if the units were to be recalled.
					this.checkRemoval(recallUnits, eventVO);
					
					//send the updated units to the client
					comm.updateClientUnits(recallUnits);
					comm.consoleMessage("EventController:  of good-resources for event, "+eventVO.eventID+"  , is  "+eventVO.goodResources);
				}*/
							   
				//comm.consoleMessage("Score:  of good-resources for event, "+eventVO.getLabel()+"  , is  "+eventVO.goodResources);
				
			   
			}//end of Active Pending Arrival Check
			
		}//end of event loop / i loop
		
	}//end of manageEventResources()
	

	
	
	
	/****************************************************************
	 * 1A. Check Answer Function
	 * 
	 * - Called By:  ManageEventResources Function
	 * - Purpose: To check the answer of of the passed in units & provide feedback
	 * 
	 * - Notes: unitArray is the list of allocated units..
	 *          eventAnswers is stored in DifficultyVO
	 *  
	 ****************************************************************/	
	private void checkAddition(ArrayList<UnitVO> arrivedUnits, EventVO eventVO){							
		
		List<AnswerVO> eventAnswers = eventVO.answers;
		
		
		comm.consoleMessage("Answer -- Checking Answer for event: " + eventVO.eventID);
		comm.consoleMessage("Answer -- Provided Units: " + arrivedUnits.size());
		comm.consoleMessage("Answer -- Valid Resources:"  + eventAnswers.size());
		String feedback = "";
		
		//loop over the answers provided by the user 					
		for (int i = 0; i < arrivedUnits.size(); i++){

			AnswerVO currentAnswer = new AnswerVO();
			
			feedback = "";
						
			Boolean unitMatch = true;
			
			if(eventVO.checkAnswer(arrivedUnits.get(i).resourceID)){
				
				unitMatch = true;
			}
			else unitMatch = false;
			
			
			//***Boolean roleMatch = eventVO.checkRoleAnswer(rModel.getRoleOfResource(arrivedUnits.get(i).resourceID));
			ResourceVO resource = environmentModel.getResource(arrivedUnits.get(i).resourceID);
			//Boolean roleMatch = eventVO.checkRoleAnswer(resource.roleID);
			
			Boolean resCap = true;  
			Boolean unitCap = true;
			
			//environmentModel.togglePendingUnits(arrivedUnits.get(i).locationID, false);
			environmentModel.togglePendingUnits( arrivedUnits.get(i).locationID, false);
			
			try{
		    
			if (unitMatch){ //(&& roleMatch?)
			   
				System.out.println("\nUnit match at event - "+eventVO.eventID);
				
				currentAnswer = eventVO.getAnswer(arrivedUnits.get(i).resourceID);
			    
				resCap = currentAnswer.hasResouceCap() && (currentAnswer.numApplied >= currentAnswer.resourceCap);
				
			    unitCap = eventVO.isUnitCapBound() && (eventVO.individualUnits >= eventVO.unitCap);			
			    
			 
				//if one of the answers provided matched the answer list and was not ResCapped or UnitCapped, then the unit was correct
				if(!resCap && !unitCap){							
					
					double roundedCurrentMagnitude = Math.ceil(eventVO.getCurrentMagnitude());
					
					int priorityDifference =  (int)Math.abs(roundedCurrentMagnitude - (double)arrivedUnits.get(i).setPriority);
					
					//comm.consoleMessage("ERROR - Cur Mag" + roundedCurrentMagnitude + " priority difference" + priorityDifference);
					int priorityEffect = 0;
					
					boolean isAction = environmentModel.getResource(arrivedUnits.get(i).resourceID).isAction();
					boolean overwrite = false;
					
					int numAnswers = eventVO.answers.size();
					
					if(isAction){
						overwrite = true;
						
												
						if(roundedCurrentMagnitude<(double)arrivedUnits.get(i).setPriority){
							priorityEffect = ((int) (roundedCurrentMagnitude - priorityDifference))/numAnswers;
							//comm.consoleMessage("ERROR - priority effect " + priorityEffect);
							if((priorityEffect<=0)&&(roundedCurrentMagnitude>0)) { 
								priorityEffect = 1; }
							
						}
						else if (roundedCurrentMagnitude>=arrivedUnits.get(i).setPriority){
							priorityEffect = arrivedUnits.get(i).setPriority/numAnswers;
						}
					}// ending if
					else{
						priorityEffect = 1;
					}
				
					//Set Unit CorrectAssigned
					uModel.setCorrectUnitArrival(arrivedUnits.get(i).badgeNo);
					feedback = resource.correctFeedback;
					//Add GoodResources to the corresponding EventVO and its answer									
					//eventModel.addGoodResources(eventVO.incident.getIncidentID(), currentAnswer.resourceID);
					scenarioModel.addGoodResources(eventVO.eventID, currentAnswer.resourceID, priorityEffect, overwrite);
					
					
				} // ending if(!resCap && !unitCap)
				else
				{
					
					feedback =   resource.incorrectFeedback;
					uModel.setIncorrectUnitArrival(arrivedUnits.get(i).badgeNo);
				}
				
			} // unitMatch
			else 
				// if unit is incorrect
			{
				feedback = resource.incorrectFeedback;
				uModel.setIncorrectUnitArrival(arrivedUnits.get(i).badgeNo);
					
			}//incorrect
			
			}// ending try
			catch(Exception e)
			{
				comm.consoleMessage("ERROR- Unit match code");
				comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
			}
					
			
			try{
			  
			
			feedback+=this.answerFeedback(unitMatch, resCap, unitCap, resource.isAction());					
						
			if (unitMatch && !unitCap & !resCap ) {
				feedback += this.severityFeedback(eventVO, environmentModel.getResource(arrivedUnits.get(i).resourceID).isAction);
			}
			//comm.consoleMessage("ERROR - " + eventVO.locationId);
			this.provideFeedback(arrivedUnits.get(i), unitMatch, feedback, arrivedUnits.get(i).locationID);
			
			
			} // ending try block
			catch(Exception e)
			{
				comm.consoleMessage("ERROR- Feedback Message Code " + getStackTrace(e));
				comm.consoleMessage("ERROR - " + e.getLocalizedMessage());
				
			}
					
		}//end of i loop
				
		
	}//end of checkAddition function
	
	
	
	
	private void checkRemoval (ArrayList<UnitVO> recallUnits, EventVO eventVO) {
		comm.consoleMessage("MISC - Checking Recalled Units for Removal");
        
		List<AnswerVO> answers = eventVO.answers;
		
		for (UnitVO unit : recallUnits){
				
			//Check to see if the resource was a valid answer for that event
			Boolean unitMatch = eventVO.checkAnswer(unit.resourceID);
			
			if(unitMatch){	
				//Remove the GoodResourcs from the event
				scenarioModel.removeGoodResources(eventVO.getId(), unit.resourceID);
				eventVO.individualUnits--;
				//update the Allocated Answer Tally in Event History
				eventVO.decrementAnswerTotal(unit.resourceID);
			}
							
			//update the RecallTally in Event History
			historyModel.updateRecallTally(eventVO.getId(), unitMatch);
									
		}//end of j loop
		
		//return the unit in the UnitModel & update Resource Totals
		uModel.returnUnits(recallUnits);
				
	}//end of checkRemoval
	
	/****************************************************************
	 * 1C. Feedback Functions
	 * 
	 * - Called By:  Check Answer Function
	 * - Purpose: Handles the assignment of feedback to an event
	 * 
	 * - Notes:    
	 ****************************************************************/	
		
	private String answerFeedback (Boolean unitMatch, Boolean resCap, Boolean unitCap, Boolean isAction) {
		String feedback = " ";
		
		//if one of the answers provided matched the answer list, then the unit was correct
		if(unitMatch){										
			if(resCap){
				if (isAction)
				{
					feedback += "This location has already recieved this categorization";
				}
				else
				{
					feedback += "Too many resources are already on scene. This unit is unable to help.";
				}
			}
			else if (unitCap) {
				if (isAction)
				{
					feedback += "This location has already been categorized and somebody is working to resolve the situation";
				}
				else
				{
					feedback += "Too many units of this type are already on scene. This unit is unable to help.";
				}
			}	
		} 
		
		return feedback;
	}
		
	private String severityFeedback (EventVO eventVO, boolean isAction){
		String feedback = " ";
		if (eventVO.getId() == -9999) { return feedback; }
		if (!isAction)
		{
			if(eventVO.goodResources < Math.floor(eventVO.getCurrentMagnitude()) || 
					environmentModel.getLocation(eventVO.locationId).activeEvents > 1){
				feedback += "However there are more issues that need to be resolved at this location. ";
			}
		}
		
		return feedback;		
	}
	
	private void provideFeedback(UnitVO unit, Boolean correct, String feedback, int _locationID){
		
		
		uModel.setUnitFeedback(unit, feedback);
		environmentModel.addFeedback(_locationID, feedback);
		comm.sendLocations(environmentModel.getLocation(_locationID));
		
		if(!correct&&environmentModel.getResource(unit.resourceID).isAction){
				uModel.terminateRejectedUnit(unit);
		}
		
		comm.consoleMessage("Answer ------ Unit #" + unit.badgeNo + " = " + correct);
	}
	
	
	/****************************************************************
	 * 1B. Check Removal Function
	 * 
	 * - Called By:  ManageEventResources Function
	 * - Purpose: Handles the Removal of Resources from the Event
	 * 
	 * - Notes:  Removal List is built by the NCServer, during a unit recall. 
	 *           Function also decrements the goodResources variable of an eventVO
	 * @param recallUnits 
	 *  
	 ****************************************************************/	
	

	
	/****************************************************************
	 * 2. Score Calculations for ACTIVE Events
	 * 
	 * An '(I)' notation would indicate Interdependency. The rest of the NeoCITIES-code should consider this.
	 * 
	 * Explaining variables: 
	 * 
	 * R_reachTime - The time at which the inputed R_Good value became true  
	 * R_Good      - The  of correct resources applied.
	 *
	 * Specifics about eventGrowth(): 
	 * 
	 * +(I) The assignment of a value to magCap is assumed as not skipped. 
	 * +(I) R_reachTime should have a default value of the last possible time-step, such as episode completion. 	  
	 * 
	 * Other important results that this function should give is:
	 * 
	 * 1) finishingMagnitude: The magnitude at which the event was completed.  
	 * 2) finishingTimeStep =  The time-step at which the event was completed.
	 * 3) magnitudeArray[] is filled here 
	 *
	 ****************************************************************/
		
	private void manageScoring(){		
		
		if(this.newResourcesHandled==false)
		{
			System.out.println("Scoring mechanism invoked without resource management");
			System.exit(1);
			
		}
		
		//for all active events, calculate the Magnitude for this timeStep
		for(int k = 0 ;  k < scenarioModel.Events.size() ; k++) {		
			
			if(scenarioModel.Events.get(k).isActive()){		
				
				int effectiveResources = scenarioModel.Events.get(k).getResourcesAtEvent();
												
				// If this is the first time step for an event
								
				if(scenarioModel.Events.get(k).getCurrentTimeStep()==0)//***if( eventModel.data.get(k).getTimeStep() == 0 ) 
				{ 			    
					//then set the current magnitude to the defined initial severity
					//***eventModel.data.get(k).magCurrent = eventModel.data.get(k).incident.getSeverity(); // Input from severity					
				   scenarioModel.Events.get(k).magnitudeArray.add(scenarioModel.Events.get(k).getSevarity()); // Input from severity					
				} 
				else {	
				    //else calculate the current magnitude given the event growth formula
					//***scenarioModel..get(k).magCurrent = eventGrowth.getCurrentMagnitude(eventModel.data.get(k), effectiveResources, eventModel.data.get(k).getTimeStep());					
				    
					double currentMag = eventGrowth.calculateCurrentMagnitude(scenarioModel.Events.get(k), scenarioModel.Events.get(k).getCurrentTimeStep());
					System.out.println("Current Mag " + currentMag +  " Current Time Step: " + scenarioModel.Events.get(k).getCurrentTimeStep());
					scenarioModel.Events.get(k).magnitudeArray.add(currentMag); 
					
				}				
				
				comm.consoleMessage(
					"Score: Current Magnitude for Incident " 
					+ scenarioModel.Events.get(k).eventID + ": " 
					+ scenarioModel.Events.get(k).getCurrentMagnitude()
					);
				
				//this.compareMagnitudes(scenarioModel.Events.get(k));				
			}
		}//end of k loop
		
		}//end of manageScoring
	   
/*
	
	//used to determine if the incident should be updated for the client, based on magnitude comparison
	private void compareMagnitudes(EventVO eventVO){		
		
		int previousMag = (int) Math.floor(eventVO.incident.getMagCurrent());
		int currentMag = (int) Math.floor(eventVO.magCurrent);
	
		eventVO.incident.setMagCurrent(currentMag); //update MagCurrent to reflect current mag.
		
		//if new mag is different from previous mag, then the value has changed (for better or worse) and the client needs to be informed.
		if(currentMag != previousMag){
			comm.sendIncident(eventVO.incident);
		}
		
	}//compareMagnitudes()
	
	*/
	     
	/****************************************************************
	 * 3. TimeStep Management
	 ****************************************************************/
	private void manageTime(){
		//increment the Master TimeStep for the Simulation
	    
		scenarioModel.incrementTimeStep(); //eventModel.incrementTimeStep();
		
		//scenarioModel
		//output current Master TimeStep to console
		
		comm.consoleMessage("Current TimeStep: " + scenarioModel.getCurrentTimeStep());
		
		/*if (eventModel.getCurrentTimeStep() % 20 == 0 )
		{
			comm.sendSyncTime(eventModel.getCurrentTimeStep());
		}*/
		
		if (scenarioModel.getCurrentTimeStep() % 20 == 0 ){
			comm.sendSyncTime(scenarioModel.getCurrentTimeStep());
		}
		
		//increment the timeStep for all active scoreEvents
		scenarioModel.incrementActiveEvents();
		
		/*
		//decrement the timeStep counter for all active interdependent events
		eventModel.decrementEventPacing();
		*/
		
	} // manageTime()
		
	/****************************************************************
	 * 4. Event Completion Management
	 * 
	 * This function assumes that magCurrent has been calculated for this timeStep
	 ****************************************************************/	
		
	public void manageEventCompletion(){
	
		for(int k = 0 ;  k < scenarioModel.Events.size() ; k++) {
			
			if(scenarioModel.Events.get(k).isActive()) 
			{									
				//boolean magFail = false; // has the event failed due to magnitude-considerations ?
				boolean timeOver = false; // has the event failed due to time-bound considerations ?
				
				
				EventVO eventVO = scenarioModel.Events.get(k);
										      
			//	timeOver  = (eventVO.difficulty.isTimeBound() && (eventVO.getTimeStep()>= eventVO.difficulty.getTimeStepLimit()));
			//	magFail = ((eventVO.difficulty.isMagBound()) && (eventVO.magCurrent >= eventVO.difficulty.getMagLimit()));
				
				if(eventVO.getCurrentTimeStep()>=eventVO.getTimeStepLimit()-1)
				{ timeOver = true; } 
				
			
				//if (timeOver||magFail) { comm.consoleMessage("Event Controller - Time Limit End: " + timeOver + ", Mag Limit End: " + magFail); }
				
				if (timeOver) { comm.consoleMessage("Event Controller - Time Limit End: " + timeOver); }
				
				/* if the magnitude has not become MagSuccess by the time the time-bound time-step is over, 
				 * or if magnitude-condition fails, a failure condition is reported
				 * 
				 * OLD comparisons:
				 * if(timeOver && eventVO.getCurrentMagnitude() > scenarioModel.getMagSuccess()){
				 * else if (eventVO.getCurrentMagnitude() <= scenarioModel.getMagSuccess())
				 */
				if(timeOver || eventVO.checkMagComplete(scenarioModel.getMagSuccess()))
				// if time ends or the severity has dropped to 0, the simulation will end the event
				{ 				  					  
					this.handleEventCompletion(eventVO, eventVO.checkEventComplete(scenarioModel.getMagSuccess()));
				} 
				
				
				if(eventVO.getCurrentTimeStep() >= eventVO.getTimeStepLimit()) // error-check
				{
					// If this portion of the code is reached, there is a problem with the coding.
					  comm.consoleMessage("ERROR - EC: Event still on even after Time-Over.");
					  System.exit(1);
				}				 
			
			}// ending if	
		}//end of k loop
	}//end of manageEventCompletion
	
	
	
	private void handleEventCompletion(EventVO eventVO, Boolean status){
		
		//IncidentModel iModel = IncidentModel.instance();
		String feedback;
		environmentModel.togglePendingUnits(eventVO.locationId, false);
		if(status){
			eventVO.setEventComplete();
			feedback = eventVO.successMessage;
			// removed on redundancy - iModel.setIncidentStatus(eventVO.incident.getIncidentID(), "Complete");
			comm.consoleMessage("Event Success: #" +eventVO.getId());
		} 
		else {
			eventVO.setEventFailure();
			feedback = eventVO.failureMessage;
			//removed on redundancy -  iModel.setIncidentStatus(eventVO.incident.getIncidentID(), "Failed");
			comm.consoleMessage("Event Failed: #" +eventVO.getId());
		}
				
		//record the final timeStep and Magnitude for the events
		eventVO.finishingTimeStep = eventVO.getCurrentTimeStep();
		
		eventVO.finishingMagnitude = eventVO.getCurrentMagnitude();
								
		//reset dispatched units to an idle state, update the resource counter
	//	ArrayList<UnitVO> recalledUnits = uModel.resetEventUnits(eventVO.getId());
		
		//calculateScores
		ScoreVO score = this.calculateScores(eventVO, status);
		
		// Update eventRecords.
		this.recordEventScores(score);
	//	comm.consoleMessage("ERROR - handle event completion " + score.eventID);
		this.recordEventHistory(score, eventVO.finishingTimeStep, eventVO.getTimeStepLimit());
		
		// Send the updated event and unit information
	//	comm.updateClientUnits(recalledUnits);
		
		// Update events location status and send to client
		environmentModel.toggleLocationEvent(eventVO.locationId, false);
		environmentModel.addFeedback(eventVO.locationId, feedback);
		
		environmentModel.getLocation(eventVO.locationId).avgSeverity = 0;	
		comm.sendLocations(environmentModel.getLocation(eventVO.locationId));
		
		uModel.terminateUnitsAtEvent(eventVO);
		
	} // handleEventCompletion()
	
	
	
	//Calculate Scores
	public ScoreVO calculateScores (EventVO eventVO, Boolean status){
		int eventID = eventVO.getId();
		int locationID = eventVO.getLocationId();
		String eventLabel = eventVO.label;
		
		double rawScore = eventGrowth.calculateEventArea(eventVO);
		double worstScore = eventGrowth.worstActionArea(eventVO);
		double normalScore = eventGrowth.calculateNormalizedScore(eventVO);
		String grade = eventGrowth.scoreGrader(eventVO);
		
		double numerator = 0;
		double total = 0;
		double dispatchRatio = 0;
				
		/*if(eventVO.difficulty.isInterdependent()){
			numerator = historyModel.getOrderRecord(incidentID).numCorrectOrderUnits;
			total = historyModel.getOrderRecord(incidentID).numRequiredUnits;
		}*/
		
		//else 
		{
	//		numerator = historyModel.getIncidentRecord(incidentID).dispatchCorrect;
	//		total = historyModel.getIncidentRecord(incidentID).dispatchCorrect + historyModel.getIncidentRecord(incidentID).dispatchWrong;
		}
		
		if (total != 0){			
			dispatchRatio = numerator / total;
		}
		
		comm.consoleMessage("Score - Numerator: " + numerator);
		comm.consoleMessage("Score - total: " + total);
		comm.consoleMessage("Score - DispatchRatio: " + dispatchRatio);
				
		//in case of a miscalculation due to timeStep inaccuracy.
		if(rawScore > worstScore){
			rawScore = worstScore;  
		}		
		//comm.consoleMessage("ERROR - calculate scores " + eventID);
		ScoreVO score = new ScoreVO(eventID, locationID, status, rawScore, worstScore, normalScore, grade, eventLabel);
		//comm.consoleMessage("ERROR - Event ID " + score.eventID);
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
		//comm.consoleMessage("ERROR - record event history " + eventID);
		historyModel.storeEventHistory(eventID, score, finishingTimeStep, timeStepLimit);
		
		comm.sendEventHistory(historyModel.getEventRecord(eventID));					
	}
	
    private static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString().replaceAll("[\r\n]+", "");
    }
    
    public void locationHealth(){
    	    	
    	for(int k = 0; k < environmentModel.Locations.size() ; k++){
    		
    		double avgSev = 0.0;
    		int activeEventNo = 0;
    		
    		if(environmentModel.Locations.get(k).activeEvents==0)
    		continue;
    		
    		for(int e = 0 ; e< scenarioModel.Events.size(); e++){
    			
    			if(scenarioModel.Events.get(e).isActive()&&scenarioModel.Events.get(e).locationId==environmentModel.Locations.get(k).id){
    				
    				activeEventNo++;
    				avgSev += scenarioModel.Events.get(e).getCurrentMagnitude();
    				
    			} // ending if
    			
    			
    	   	}// ending e loop
    		
    		
    		avgSev = avgSev/(double)activeEventNo;
    			
    		
    		environmentModel.Locations.get(k).avgSeverity = avgSev;
    		comm.sendLocations(environmentModel.Locations.get(k));	
    	
    	}// ending k loop
    	
    	
    	
    }// ending locationHealth()
	
	

}//end of EventController Class
