/**
 * 
 */
package edu.psu.ist.neocities;

import edu.psu.ist.neocities.simulator.*;
import edu.psu.ist.neocities.control.*;
import edu.psu.ist.neocities.model.*;
import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.value.HistoryValue.*;
import edu.psu.ist.neocities.value.dataTypes.BuildingLocation;
import edu.psu.ist.neocities.value.dataTypes.CyberInfo;
import edu.psu.ist.neocities.value.dataTypes.CyberLocation;
import edu.psu.ist.neocities.value.dataTypes.TestInfoData;
import edu.psu.ist.neocities.util.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

/**
 * @author bhellar@ist.psu.edu
 * @date 9-4-08
 * 
 * This is the main function class in NeoCities
 * This class is the controller, the traffic cop 
 */
public class NeoCitiesServer {
	
	/****************************************************************
	 * Variables & Constructor
	 ****************************************************************/
	private ScenarioModel scenarioModel = ScenarioModel.instance();
	private EnvironmentModel environmentModel = EnvironmentModel.instance();
	private ScoreModel scoreModel = ScoreModel.instance();  
	private HistoryModel historyModel = HistoryModel.instance();
	private PlayerModel playerModel = PlayerModel.instance();
	private UnitModel uModel = UnitModel.instance();
	
		
	private String systemState = "Authenticating";
	
	
	private CommController comm = CommController.instance();
	private TimeLogic TL = TimeLogic.instance();
	
	private static Simulator sim;
		
	public NeoCitiesServer() {
	}
	
	/****************************************************************
	 * Simulator Functions
	 ****************************************************************/
	// Start the Simulator Thread
	public void start(){
		
		if (systemState == "Running")
		{
			comm.consoleMessage("ERROR - Simulation is already running");
			return;
		}
		else if (scenarioModel == null)
		{
			comm.consoleMessage("ERROR - Scenario Model is null");
		}
		else if (environmentModel == null)
		{
			comm.consoleMessage("ERROR - Environment Model is null");
		}
		else
		{
			TL.setRealStartTime();
			systemState = "Running";
			sim = new Simulator();
			sim.start();
		}
	}
	
	// Stop the Simulator Thread
	public void stop(){
		sim.stop();
		systemState = "Authenticating";
	}
	
	// Pause's the Simulator Thread
	public void pause(){
		sim.pause();		
	}
	
	// Resume's the Simulator Thread
	public void resume(){
		sim.resume();
	}
	
	// Clears the simulation's data
	public void clearSimData() {		
		
		playerModel.resetPlayerIncidents();		
		scoreModel.resetScores();
		uModel.resetModel();
		historyModel.resetHistory();
	
	}
	
	public void clearScenarioData() {
		
		scenarioModel.resetModel();
		comm.consoleMessage("Scenario Data Cleared");
		
	}
	
	public void clearEnvironmentData(){
		environmentModel.resetModel();
		comm.consoleMessage("Environment Data Cleared");
	}
	
	public void initClient(){
		comm.syncMessage("login");
		systemState = "loggingin";
	}
	
	/****************************************************************
	 * Simulator Timer Functions
	 ****************************************************************/
	//setters
	public void setTime(Date date) {
		TL.setDate(date);
	}
	
	public void setGameStartTime(int seconds) {		
    	TL.setGameStartTime(seconds);
	}
	
	public void setDate(Calendar cal){
		TL.setDate(cal.getTime());
	}
	
	public Date getTime(){
		return TL.getDate();
	}
	
	public int getCurrentTimeStep(){
		return scenarioModel.getCurrentTimeStep();
	}
	
	/****************************************************************
	 * Unit Manipulation Functions
	 ****************************************************************/
	public void loadUnits(){
		//for each resource
		
		comm.consoleMessage("===================Loading Units=======================");
		comm.consoleMessage("Creating Units for " + environmentModel.Resources.size() + " Resources" );
		for(int i = 0; i <= environmentModel.Resources.size() - 1; i++){
			//create a new unit until the total number for that resource is met
			comm.consoleMessage("Creating " + environmentModel.Resources.get(i).total + " units for " + environmentModel.Resources.get(i).resourceName);
			environmentModel.Resources.get(i).initAvailable();
			for(int j = 0; j <= Math.abs(environmentModel.Resources.get(i).total) - 1; j++){
				int unitBadge = (environmentModel.Resources.get(i).id * 10 )+ j;
				int rID = environmentModel.Resources.get(i).id;
				String rIcon = environmentModel.Resources.get(i).resourceImage;
				uModel.data.add(new UnitVO (unitBadge, rID, rIcon ));
			}
		}
		comm.consoleMessage("===================Finished Loading Units=======================");
	}
	
	public void calculateAssignedInformation() {
		comm.consoleMessage("===================Assigning Info To Events=======================");
		
		
		for (int i = 0; i < scenarioModel.Information.size(); i++)
		{
			scenarioModel.addAssignedInfo(scenarioModel.Information.get(i).eventID);
		}
		
		for (int j = 0; j < scenarioModel.Events.size(); j++)
		{
			comm.consoleMessage("Event " + scenarioModel.Events.get(j).eventID + " Assigend " + scenarioModel.Events.get(j).assignedInformation +
								" pieces of information");
		}
		
		comm.consoleMessage("===================Finished Info To Events=======================");
	}
	
	/*
	 * InformationHistoryVO(int roleID, int informationID, int locationID,
			int recipientID, String infoTime, String actionTime,
			String reactionTime, String action, Boolean returned
	 */
	public void informationTransfer(int roleID, int infoID, int recieverID)
	{
		InformationVO info = scenarioModel.getInformation(infoID);
		InformationHistoryVO ihVO;
		if (info == null)
		{
			comm.consoleMessage("ERROR - Action performed on invalid information ID");
			return;
		}
		
		if (recieverID == -9999)
		// means it was an information delete
		{	
			Boolean returned = false;
			Boolean correct = false;
			if (info.numReturns > 0)
			{
				if (info.eventID == -1) { returned = correct = true; }
				else if (!scenarioModel.getEvent(info.eventID).isActive()) { returned = correct = true; }
				else { returned = correct = false; }
				// if the info is a dud (not assigned to an event, allow them to delete)
				
				if (returned = true)
				{
					if (info.returnData != null) {info.data = info.returnData; }
					
					info.numReturns--;
				
					comm.sendInformation(info);
				}
			}
			
			ihVO = new InformationHistoryVO(roleID, info.id, info.locationID, -9999, info.dispatchTime.getTimeSteps(scenarioModel.TimeStepLength), 
					scenarioModel.masterTimeStep, "delete", returned, correct);		
		}
		else
		{
			//TODO : write transfer function
			ihVO = new InformationHistoryVO();
		}
		//comm.consoleMessage("ERROR - " + ihVO.action + " " + ihVO.actionTime);
		historyModel.addInformation(ihVO);
		comm.sendInformationHistory(ihVO);
		
	}

		
	
	//update the model
	public void dispatchUnits(int roleID, int locationID, ArrayList<UnitVO> unitArray) {
		
		List<EventVO> eventsByLocation;
		Boolean isEligible = false;
		
		eventsByLocation = scenarioModel.getActiveEventsByLocation(locationID);
		
		ArrayList<UnitVO> dispatchedUnits = new ArrayList<UnitVO>();
		ArrayList<UnitVO> TempArrayList = new ArrayList<UnitVO>(); 
		ArrayList<UnitVO> removeList = new ArrayList<UnitVO>();
		int numCorrectInfo = 0;
		int numIncorrectInfo = 0;
		    
		for(int j = 0 ; j<unitArray.size() ; j++){
		    
			isEligible = this.setDispatchFeedback(unitArray.get(j), roleID, locationID);
			numCorrectInfo = 0;
			numIncorrectInfo = 0;
			boolean found = false;
			if (isEligible)
		    {
				for(int k = 0; k<eventsByLocation.size(); k++){
					
					// which event applies
				    
						if(eventsByLocation.get(k).checkAnswer(unitArray.get(j).resourceID))
						{
							found = true;
							TempArrayList.add(unitArray.get(j));
						
							uModel.dispatchUnits(eventsByLocation.get(k).getId(),TempArrayList, locationID);
							
							for (int i = 0; i < unitArray.get(j).info.length; i++)
							{
								if (scenarioModel.getInformation(unitArray.get(j).info[i]).eventID == eventsByLocation.get(k).getId())
								{
									numCorrectInfo++;
								}
								else
								{
									numIncorrectInfo++;
								}
							}
							
							this.updateActionHistory(eventsByLocation.get(k).getId(), unitArray.get(j), "Dispatch", roleID, locationID, 
													unitArray.get(j).setPriority, numCorrectInfo, numIncorrectInfo);
							TempArrayList.clear();
							
							break;
						}
				    }// ending k loop
				   
				}
			 else
			    {
				 	removeList.add(unitArray.get(j));
				 	this.updateActionHistory(-2, unitArray.get(j), "Dispatch", roleID, locationID, unitArray.get(j).setPriority, -9999, -9999);
			    }
			
			if(!found && isEligible){
				comm.consoleMessage("DS - No events found or no answer");
				TempArrayList.add(unitArray.get(j));
				uModel.dispatchUnits(-9999, TempArrayList, locationID);
				
	     		this.updateActionHistory(-9999, unitArray.get(j), "Dispatch", roleID, locationID, unitArray.get(j).setPriority, 0, unitArray.get(j).info.length);
				TempArrayList.clear();
			}
			
		}// j loop
		
		for(int q = 0; q < removeList.size(); q++){
			
			uModel.terminateRejectedUnit(removeList.get(q));
		}
	
		//comm.updateClientUnits(dispatchedUnits);
		
		//send the latest changes to the dispatched units		
		
	} // dispatchUnits
	
	
	
		
	
	public void recallUnits(ArrayList<UnitVO> unitArray, int roleID, int locationID){		
		
		int eventID = unitArray.get(0).eventID; //assumes the units are all being recalled from the same place
						
		//update their status in the uModel
		uModel.recallUnits(unitArray);			
		
		//update the ActionHistory
	//	this.updateActionHistory(eventID, unitArray, "Recall", roleID);
		
		if(uModel.getDispatchedTotal(eventID) == 0){
			//iModel.setIncidentStatus(eventID, "New");
			comm.sendEvent(scenarioModel.getEvent(eventID));
		}
		
		//Obtain an ArrayList of only the units and resources that were recalled	
		ArrayList<UnitVO> recalledUnits = uModel.getRecallPendingUnits(eventID);
		
		//send the latest changes to the dispatched units
		//comm.updateClientUnits(recalledUnits);	
	}
	
	public void updateActionHistory (int eventID, UnitVO unitDispatched, String action, int roleID, int _locationID, int _setPriority, int numCorrectInfo, int numIncorrectInfo){
		
	    EventVO eVO = scenarioModel.getEvent(eventID);
		
		
		ActionHistoryVO actionHistory = new ActionHistoryVO();
	
				 actionHistory.eventID = eventID;
				 
			if(eventID!=-9999&&eventID!=-2){	 
				
				int dispatchTime = eVO.getDispatchTime();
				String eventTime = Integer.toString(dispatchTime);		
				String actionTime = Integer.toString(scenarioModel.getCurrentTimeStep());
				String reactionTime = Integer.toString(scenarioModel.getCurrentTimeStep() - dispatchTime);

				actionHistory.eligible = "Eligible";
				
				 actionHistory.roleID = roleID;
				 actionHistory.resourceID = unitDispatched.resourceID;
				 actionHistory.eventTime = eventTime;
				 actionHistory.reactionTime = reactionTime;
				 actionHistory.actionTime = actionTime;
				 actionHistory.correct =  scenarioModel.checkAnswer(eventID, unitDispatched.resourceID);  // eVO.checkAnswer(unitDispatched.resourceID);
				 actionHistory.locationID = _locationID;
				 actionHistory.setPriority = _setPriority;
				 actionHistory.currentMagnitude = Math.ceil(eVO.getCurrentMagnitude());
				 actionHistory.initialSeverity = eVO.initialSevarity;
				 actionHistory.numActiveEvents = environmentModel.getLocation(_locationID).activeEvents;
			}
			else
			{
								
				if(eventID==-2){
							
						actionHistory.eligible = "Not Eligible";
						
				}
				else 
				{
					actionHistory.eligible = "Eligible but Wrong";
				}
				
				String eventTime = "N/A";		
				String actionTime = Integer.toString(scenarioModel.getCurrentTimeStep());
				String reactionTime = "N/A";
 			
				actionHistory.roleID = roleID;
				 actionHistory.resourceID = unitDispatched.resourceID;
				 actionHistory.eventTime = eventTime;
				 actionHistory.reactionTime = reactionTime;
				 actionHistory.actionTime = actionTime;
				 
				 actionHistory.correct = false;// scenarioModel.checkAnswer(eventID, unitDispatched.resourceID);
				 actionHistory.locationID = _locationID;
				 actionHistory.setPriority = _setPriority;
				 actionHistory.currentMagnitude = 0.0;
				 actionHistory.numActiveEvents = environmentModel.getLocation(_locationID).activeEvents;
			
			}
		        historyModel.addAction(actionHistory);
			
		        comm.sendActionHistory(actionHistory);      
		        
	}//end of updateActionHistory
	
	
	
	public boolean setDispatchFeedback(UnitVO unit, int roleID, int locationID)
	{
		String feedback = ""; 
		Boolean isEligible = true;
			
		if (environmentModel.getLocation(locationID).dispatchEligilble())
		{
			feedback = playerModel.getPlayer(roleID).userName + " (" + environmentModel.getRole(roleID).roleName + 
								") categorized issue at " + environmentModel.getLocation(locationID).name + " as " + 
								environmentModel.getResource(unit.resourceID).resourceName + " with priority " + (unit.setPriority);
			isEligible = true;
			
			environmentModel.togglePendingUnits(locationID, true);
			comm.consoleMessage("ERROR - " + environmentModel.getLocation(locationID).activeEvents + " " + environmentModel.getLocation(locationID).pendingUnits);
		}
		else
		{
			isEligible = false;
			feedback = "This event has already been categorized, please wait for feedback before taking another action";

		}
		environmentModel.addFeedback(locationID, feedback);
		comm.sendLocations(environmentModel.getLocation(locationID));
		
		return isEligible;
	}
	
	
	public void updateQuestionHistory(QuestionHistoryVO record)
	{
	    historyModel.questionHistory.add(record);
	   
	    comm.sendQuestionHistory(record);
	}
	

	/****************************************************************
	 * Data Process Functions
	 ****************************************************************/
	
	public void loadSampleEnvironment()
	{
		environmentModel.setEnvironmentName("Example Environment");
				
		// Adding Locations
		environmentModel.addLocation(new LocationVO(1, "Location 1", new BuildingLocation("IST Building", "IST Building State College, PA 16801", "ist.gif"), 7));
		environmentModel.addLocation(new LocationVO(2, "Location 2", new CyberLocation("MINDS Server", "130.203.163.251", "server.gif"), 90));
		environmentModel.addLocation(new LocationVO(3, "Location 3", new CyberLocation("MINDS Server", "130.203.163.251", "server.gif"), 90));
		
		//Adding Roles
		environmentModel.addRole(new RoleVO(1, "Role 1", ""));
		//environmentModel.addRole(new RoleVO(2, "Role 2", "cyber.gif"));
		//environmentModel.addRole(new RoleVO(3, "Role 3", "help.gif"));
		
		//Adding Resources		
		environmentModel.addResource(new ResourceVO(1, "Resource 1", 4, 1, 4, 3, false, "", "", ""));
		environmentModel.addResource(new ResourceVO(2, "Action 1", -1, 1, 0, 0, true, "", "", ""));
		environmentModel.addResource(new ResourceVO(3, "Action 2", -1, 1, 0, 0, true, "", "", ""));
		environmentModel.addResource(new ResourceVO(4, "Resource 2", 4, 1, 2, 1, false, "", "", ""));
		
		
	}
	
	public void loadSampleScenario()
	{
		scenarioModel.setScenarioName("Example Scenario");
		
		//Set time information
		scenarioModel.setTimeLimit(new TimeSet(5,30));
		
		// Setting Events
		scenarioModel.addEvent(new EventVO(1, 1, 2, "Event Number 1", 8));
		scenarioModel.setEventTimeLimit(1, new TimeSet(1,30));
		scenarioModel.setEventDispatchTime(1, new TimeSet(0,30));
		scenarioModel.addEventAnswer(1, new AnswerVO(1, 1, -1, 1));

		
		scenarioModel.addEvent(new EventVO(2, 2, 3, "Event Number 2", 8));
		scenarioModel.setEventTimeLimit(2, new TimeSet(1,30));
		scenarioModel.setEventDispatchTime(2, new TimeSet(2,30));
		scenarioModel.addEventAnswer(2, new AnswerVO(2, .20, -1, 1));
		scenarioModel.addEventAnswer(2, new AnswerVO(3, .80, 2, 1));
		
		//Setting Information
		scenarioModel.addInformation(new InformationVO(1, "Info 1"));
		scenarioModel.setInfoData(1, new TestInfoData("Break in at IST Building","70.8","90.2"));
		scenarioModel.setInfoDispatchTime(1, new TimeSet(0, 50));
		
		scenarioModel.addInformation(new InformationVO(2, "Info 2"));
		scenarioModel.setInfoData(2, new CyberInfo("Trojan Virus", 7, "10:52 AM"));
		scenarioModel.setInfoDispatchTime(2, new TimeSet(2, 50));
		
		
		//Setting Pause
		scenarioModel.addPause(new PauseVO(1));
		scenarioModel.setPauseTime(1, new TimeSet(3,20));
		scenarioModel.setPauseTimeLimit(1, new TimeSet(1, 0));
		
		//Adding questions to pause
		scenarioModel.addPauseQuestion(1, new QuestionVO(1, "SA", "What is your name?"));
		
		scenarioModel.addPauseQuestion(1, new QuestionVO(2, "MC", "What is 5+5?"));
		scenarioModel.addQuestionAnswer(1, 2, new QuestionAnswerVO(1, "7", "7", 0));
		scenarioModel.addQuestionAnswer(1, 2, new QuestionAnswerVO(2, "9", "9", 0));
		scenarioModel.addQuestionAnswer(1, 2, new QuestionAnswerVO(3, "10", "10", 1));
		scenarioModel.addQuestionAnswer(1, 2, new QuestionAnswerVO(4, "12", "12", 0));
	}
	
	public void loadScenarioXML(String xml)
	{
		/* 	This function is called from NeoCITIES RO, which is passed an XML string from the Flex interface
			First the system checks XML to ensure it is valid, and then using the Simple XML framework (simple.sourceforge.net)
			Binds it to the scenario model
		*/
		Serializer serializer = new Persister();
		ScenarioModel s;
		
		try {
			if (!serializer.validate(ScenarioModel.class, xml))
			{
				comm.consoleMessage("ERROR - The Scenario Model file is invalid  " + xml);
				return;
			}
				
			comm.consoleMessage("Scenario Model has been validated");
			s = serializer.read(ScenarioModel.class, xml);
			
			scenarioModel.setScenarioName(s.ScenarioName);
			scenarioModel.setTimeLimit(s.ScenarioTime);
			scenarioModel.setEvents(s.Events);
			scenarioModel.setPauses(s.Pauses);
			scenarioModel.setInformation(s.Information);
			comm.consoleMessage("======= Finished Loading Scenario " + scenarioModel.ScenarioName + "========");
			
			this.calculateAssignedInformation();
			comm.sendScenaioName(scenarioModel.ScenarioName);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			comm.consoleMessage("There has been an error loading the Scenario XML");
			
			comm.consoleMessage(e.getLocalizedMessage());
			
		}
		
	}
	
	public void loadEnvironmentXML(String xml)
	/*
	 * This function serves the same purpose as the previous one, but loads the XML to the Environment model
	 */
	
	{
		Serializer serializer = new Persister();
		
		EnvironmentModel e;
		
		try {
			if (!serializer.validate(EnvironmentModel.class, xml))
			{
				comm.consoleMessage("ERROR - The Environmental Model file is invalid");
				return;
			}
			
			comm.consoleMessage("Environmental Model has been validated");
			
			e = serializer.read(EnvironmentModel.class, xml);
			
			environmentModel.setEnvironmentName(e.EnvironmentName);
			environmentModel.setLocations(e.Locations);
			environmentModel.setRoles(e.Roles);
			environmentModel.setResources(e.Resources);
			
						
			comm.consoleMessage("======= Finished Loading Environment " + environmentModel.EnvironmentName + "========");
		    //this.loadUnits();
			comm.sendEnvironmentName(environmentModel.EnvironmentName);
			
		}
		catch (Exception ev) {
			// TODO Auto-generated catch block
			ev.printStackTrace();
			
			comm.consoleMessage("ERROR - There has been an error loading the Environment XML");
			
			comm.consoleMessage(ev.getLocalizedMessage());
			
		}
		
		
	}
	
	public void addPlayer(PlayerVO player) {
		playerModel.data.add(player);
		System.out.println(player.currentFlexVersion + " " + player.roleID);
		comm.sendPlayerUpdate(player);	
	}

	public void addEvent(EventVO event)
	{
		scenarioModel.Events.add(event);
	}
	
	public void addEventAnswer(int eventID, AnswerVO answer)
	{
		scenarioModel.addEventAnswer(eventID, answer);
	}
	
	public void setScenarioName(String name)
	{
		scenarioModel.setScenarioName(name);
	}
	
	public void setScenarioTimeLimit(int min, int sec) {		
    	scenarioModel.setNewTimeLimit(min, sec);
	}
	
	/*public void addDifficulty (DifficultyVO difficulty){
		dModel.data.add(difficulty);
	}
	public void addDifficultyAnswer(AnswerVO answer){
		answerModel.data.add(answer);
			dModel.setDifficultyAnswer(answer.difficultyID, answer);
	}	
	
	public void setSuccessMSG(int eventID, String msg){
		iModel.setSuccessMSG(eventID, msg);
	}
	public void setDescription(int eventID, int permission, String description){
		iModel.addDescription(eventID, permission, description);
	}
	public void setFailureMSG(int eventID, String msg){
		iModel.setFailureMSG(eventID, msg);
	}
	*/
	
	public void setEventTimeLimit(int eventID, int min, int seconds){
		scenarioModel.setEventTimeLimit(eventID, new TimeSet(min, seconds));
	}	
	public void setEventDispatchTime(int eventID, int min, int seconds){
		scenarioModel.setEventDispatchTime(eventID, new TimeSet(min, seconds));
	}		
	
	public void addRole(RoleVO role){		
		environmentModel.Roles.add(role);
	}	
	public void addResource(ResourceVO resource){
		environmentModel.Resources.add(resource);
	}
		
	public void addScenarioPause(PauseVO pause){		
		scenarioModel.Pauses.add(pause);
	}
	
	
	public void addPauseQuestion(int pauseID, QuestionVO question)
	{
		scenarioModel.addPauseQuestion(pauseID, question);	    
	}
	public void addQuestionImage(int pauseID, int questionID, String image)
	{
	    scenarioModel.addQuestionImage(pauseID, questionID, image);
	}
	public int getNumberQuestions()
	{
	    return scenarioModel.Pauses.size();
	}
	public void addQuestionAnswer(int pauseID, int questionID, QuestionAnswerVO questionAnswer)
	{
	    //pauseModel.addAnswerQuestion(questionID, questionAnswer);
		scenarioModel.addQuestionAnswer(pauseID, questionID, questionAnswer);
	}
	public void setPauseDispatchTime(int pauseID, int minute, int seconds){
		scenarioModel.setPauseTime(pauseID, new TimeSet(minute, seconds));
		
	}	
	public void setPauseDuration(int pauseID, int minute, int seconds){
		scenarioModel.setPauseTimeLimit(pauseID, new TimeSet(minute, seconds));
	}
	
	public void setPlayerIncident(int eventID, int roleID) {
		playerModel.setPlayerIncident(eventID, roleID);
		comm.sendPlayerUpdate(playerModel.getPlayer(roleID));
	}	
					
	/****************************************************************
	 * Data Access Functions
	 ****************************************************************/
	public List<ResourceVO> getResourceData(){
		return environmentModel.Resources;
	}
	
	public List<EventVO> getEventData() {
		return scenarioModel.Events;
	}
	
	/*
	public List<EventVO> getEventData(){		
		return iModel.data;
	}
	
	public List<DifficultyVO> getDifficultyData(){
		return dModel.data;
	}*/
	
	public List<AnswerVO> getAnswerData(){
		List<AnswerVO> answerData = new ArrayList<AnswerVO>();
		
		for (int i = 0; i < scenarioModel.Events.size(); i++)
		{
			answerData.addAll(scenarioModel.Events.get(i).answers);
		}
		
		return answerData;
	}
	
	public List<RoleVO> getRoleData(){
		return environmentModel.Roles;
	}
		
	public List<UnitVO> getUnitData(){
		return uModel.data;
	}
	public List <LocationVO> getLocationData() {
		return environmentModel.Locations;
	}
	
	public String getLastEventLabel(){
		return sim.getLastEventLabel();
	}
	
	public EventVO getScoreEvent(int eventID){
		return scenarioModel.getEvent(eventID);
	}
	
	public ScenarioModel getScenarioData() {
		return scenarioModel;
	}
	
		
	public List<ScoreVO> getScoringData() {
		return scoreModel.data;
	}
	
	public double getTeamAverage() {
		return scoreModel.teamAverage;
	}
	
	public double getTeamScore() {
		return scoreModel.teamScore;
	}
	
	public double getScenarioScore() {
		return scoreModel.scenarioScore;
	}
	
	public double getScenarioTotal() {
		return scoreModel.scenarioTotal;
	}
	
	public List<ActionHistoryVO> getActionHistory(){
		return historyModel.actionHistory;
	}
	
	public List<EventHistoryVO> getEventHistory(){
		return historyModel.eventHistory;
	}

	
	public List<OrderHistoryVO> getOrderHistory(){
		
		return historyModel.orderHistory;
	}
	
	public void getSystemState()
	{
		comm.sendSystemState(systemState);
	}
	
	
	
	
}