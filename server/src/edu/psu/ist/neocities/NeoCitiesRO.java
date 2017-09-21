package edu.psu.ist.neocities;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sun.security.util.Resources;
import edu.psu.ist.neocities.NeoCitiesServer;
import edu.psu.ist.neocities.control.CommController;

import edu.psu.ist.neocities.model.UnitModel;
import edu.psu.ist.neocities.model.EnvironmentModel;
//import edu.psu.ist.neocities.model.OldModel.ResourceModel;
import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.value.HistoryValue.*;
import edu.psu.ist.neocities.value.dataTypes.*;


public class NeoCitiesRO {
	
	/****************************************************************
	 * Variables & Constructor
	 ****************************************************************/
	EnvironmentModel eModel = EnvironmentModel.instance;
	
	public static NeoCitiesServer server;
	CommController comm = CommController.instance();
	static Boolean runOnce = false;
	static Boolean scenarioLoaded = false;
	static Boolean environmentLoaded = false;
	private String version = "4.0-Alpha";
	
	public NeoCitiesRO() {
		//loadTestData();
	}
	
	/****************************************************************
	 * Data Functions
	 ****************************************************************/
	
	
	/****************************************************************
	 * Data Accessor Functions
	 ****************************************************************/
	/*
	public List<IncidentVO> getIncidentData(){
		return server.getIncidentData();
	}
	public List<DifficultyVO> getDifficultyData(){
		return server.getDifficultyData();
	}
	public List<ScenarioVO> getScenarioData(){
		return server.getScenarioData();
	}*/
	public List<AnswerVO> getAnswerData(){
		return server.getAnswerData();
	}
	public List<RoleVO> getRoles(){
		return server.getRoleData();	
	}	
	public List<ResourceVO> getResources(){
		return server.getResourceData();
	}	
	public List<UnitVO> getUnits(){
		return server.getUnitData();
	}
	public List <LocationVO> getLocations() {
		return server.getLocationData();
	}
	public Date getSimTime(){
		return server.getTime();
	}
	public int getCurrentTimeStep(){
		return server.getCurrentTimeStep();
	}
	public EventVO getScoreEvent(int eventID) {
		return server.getScoreEvent(eventID);
	}
	
	
	public List<ScoreVO> getScoreData(){
		return server.getScoringData();
	}
	public double getTeamScore(){
		return server.getTeamScore();
	}
	public double getTeamAverage(){
		return server.getTeamAverage();
	}
	public double getScenarioScore(){
		return server.getScenarioScore();
	}
	public double getScenarioTotal(){
		return server.getScenarioTotal();
	}
	
	
	
	/****************************************************************
	 * Simulator Functions
	 ****************************************************************/
	//used to Create a New Server Instance
	public void startServer(){			
		comm.consoleMessage("=== Starting Server Version: " + version + " ===");
		server = new NeoCitiesServer();		
		
		if(runOnce)
		{
			comm.consoleMessage("=== Clearing Previous Simulation Data ===");
			// clear prior Simulation data
			server.clearSimData();
			if (environmentLoaded) { server.clearEnvironmentData(); } else { comm.consoleMessage("No Environment Data to clear"); }
			if (scenarioLoaded) { server.clearScenarioData(); } else { comm.consoleMessage("No Scenario Data to clear"); }
		}
		
		/*
		comm.consoleMessage("=== Loading Default Environment Data ===");
		this.loadEnvironmentData();
		comm.consoleMessage("=== Loading Default Scenario Data ===");
		this.loadScenarioData();
		*/
		
		comm.consoleMessage("=== Server Online ===");
		NeoCitiesRO.runOnce = Boolean.TRUE;
	}
	
	public void loadEnvironmentDataXML(String xml)
	{
		server.loadEnvironmentXML(xml);
		NeoCitiesRO.environmentLoaded = Boolean.TRUE;
	}
	
	public void loadScenarioDataXML(String xml)
	{
		server.loadScenarioXML(xml);
		NeoCitiesRO.scenarioLoaded = Boolean.TRUE;
	}
	
	
	//used to Start the Simulator on the Server
	public void startSim(){
		boolean errors = false;
		
		if (scenarioLoaded == false)
		{
			comm.consoleMessage("ERROR - No Scenario File has been loaded");
			errors = true;
		}
		
		if (environmentLoaded == false)
		{
			comm.consoleMessage("ERROR - No Environment File has been loaded");
			errors = true;
		}
		
		if (!errors) { server.start(); }
	}
	
	public void pauseSim(){
		server.pause();
	}
	
	public void resumeSim(){
		server.resume();
	}
	
	public void stopServer(){
		comm.consoleMessage("=== Clearing Previous Simulation Data ===");
		server.clearScenarioData();
		server.clearEnvironmentData();
		server.clearSimData();
		scenarioLoaded = Boolean.FALSE;
		environmentLoaded = Boolean.FALSE;
		comm.consoleMessage("=== Stopping Server Version: " + version + " ===");
		server.stop();
		comm.consoleMessage("=== Server Offline ===");
	}
	
	/****************************************************************
	 * Remote Object Functions
	 ****************************************************************/

	public void submitEvent(int locationId, double initialSevarity, String eventMessage, double magCap){
		int eventID = server.getEventData().size() + 1; 
		
		EventVO event = new EventVO(eventID, locationId, initialSevarity, eventMessage, magCap);
		
		server.addEvent(event);
	}
	
	public void informationTransfer(int senderID, int informationID, int recieverID)
	{
		server.informationTransfer(senderID, informationID, recieverID);
	}
	
	public void dispatchUnits(int roleID, int[] clientResources, int locationID, int[] priorities, int[] information, String actionTimeSeconds){		
	
		ArrayList<UnitVO> unitList = new ArrayList<UnitVO>();
		
		UnitModel uModel = UnitModel.instance();
		//ResourceModel rModel = ResourceModel.instance();
		
		
		for(int i = 0; i <=clientResources.length - 1; i++){
			
			UnitVO tempUnit = new UnitVO();
			
			tempUnit.resourceID = clientResources[i];
			tempUnit.locationID = locationID;
			tempUnit.setPriority = 3;
			tempUnit.info = information;
		//	 eModel.Resources.get((clientResources[i])).incrementBadgeCount();
			
			int largest = -1;
			 		
	 		for(int k = 0; k < uModel.data.size() ; k++){
	 					
	 				if(uModel.data.get(k).badgeNo>largest)
	 					largest = uModel.data.get(k).badgeNo;
	 			  
	  		}
		 		
			tempUnit.badgeNo = largest + 1;
			
			comm.consoleMessage("ERROR - Badge Number Added "+tempUnit.badgeNo);
			
			uModel.data.add(tempUnit);
			unitList.add(tempUnit);

		} // ending i loop
		
		server.dispatchUnits(roleID, locationID, unitList);
		
		comm.consoleMessage("Dispatch Units - Total " + unitList.size() + " to incident " + locationID + " by RoleID " + roleID );
	}
	
	public void returnUnits(int roleID, int[] clientUnits, int locationID ){
		
		// Assume all client units come from the same location
		
				
		ArrayList<UnitVO> unitList = new ArrayList<UnitVO>();
		
		
		UnitModel uModel = UnitModel.instance();
		// Assuming all units are recalled from same location
		
		
	
		
		for(int i = 0; i <= clientUnits.length - 1; i++){
			unitList.add(uModel.getUnit(clientUnits[i]));
		}
				
		server.recallUnits(unitList, roleID, locationID);
		comm.consoleMessage("Return Units - Total " + unitList.size());
	}
	
	public void messageRecieved(String role, String msg)
	{
		if (role != "Admin")
		{
			comm.consoleMessage("MSG - " + role + ":" + msg);
		}
	}
	
	public void answerQuestion(int questionId, String answer, int roleId, int questionTime, int answerTime, int correct,
                               int totalUtterances, int totalCharacters, int totalWords, int roleUtterances, 
                               int roleCharacters, int roleWords)
	{
	    QuestionHistoryVO qh = new QuestionHistoryVO(questionId, answer, roleId, questionTime, answerTime, correct,
	                                                 totalUtterances, totalCharacters, totalWords, roleUtterances,
	                                                 roleCharacters, roleWords);
	    comm.consoleMessage("User " + Integer.toString(roleId) + " answered " + answer + "for question " + Integer.toString(questionId)
	            + " at " + Integer.toString(questionTime));
	    
	    server.updateQuestionHistory(qh);
	}
	
	
	public void setPlayerIncident (int roleID, int eventID){		
		server.setPlayerIncident(eventID, roleID);	
	}
	
		
	public void initClient () {
		server.initClient();
	}
	
	public void addPlayer (PlayerVO player){
		server.addPlayer(player);
	}
	
	public void getSystemState(){
		server.getSystemState();
	}
	

		
	
	
}