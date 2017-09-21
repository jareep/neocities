/**
 * 
 */
package edu.psu.ist.neocities.control;

import java.util.List;

import edu.psu.ist.neocities.NeoCitiesDS;
import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.value.HistoryValue.*;

/**
 * @author bhellar
 *
 */
public class CommController {
	
	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static CommController instance = new CommController();
	
	private CommController() {
		// Required for Singleton Design Pattern
	}
			
	public static CommController instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/	
	
	NeoCitiesDS blazeDS = NeoCitiesDS.instance();
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public void sendEvent(EventVO event){
		blazeDS.sendEvent(event);
	}
	
	public void sendTime(String gameTime){
		blazeDS.sendTime(gameTime);
	}
	
	public void sendSyncTime (int timeStep)
	{
		blazeDS.sendSyncTime(timeStep);
	}
	
	public void sendPlayerUpdate(PlayerVO player){
		blazeDS.sendPlayerUpdate(player);
	}
	
	public void sendInformation(InformationVO information)
	{
		
		blazeDS.sendInformation(information);
	}
		
	public void updateClientUnits(List<UnitVO> unitList){				
		blazeDS.sendUnits(unitList);
		//System.out.println("CC - update client unit list");
	}
	
	public void sendLocations(LocationVO location)
	{
		blazeDS.sendLocations(location);
	}
	
	public void sendLocationFeedback(int locationID, String feedback)
	{
		blazeDS.sendLocationFeedback(locationID, feedback);
	}
	
	public void consoleMessage(String message){
		blazeDS.consoleMessage(message);
	}
	
	public void sendPause(PauseVO pause){
		blazeDS.sendPause(pause);
	}
	
	/*
	public void sendBriefing(BriefingVO briefing)
	{
	    blazeDS.sendBriefing(briefing);
	}*/
	
	/*
	public void sendQuestions(List<QuestionVO> questions)
	{
	    blazeDS.sendQuestions(questions);
	}*/
	
	public void sendChatMSG( String message){
		blazeDS.sendChatMSG(message);
	}
	
	public void sendScore (ScoreVO score){
		blazeDS.sendScore(score);
	}
	
	public void sendActionHistory (ActionHistoryVO actionHistoryVO){
		blazeDS.sendActionHistory(actionHistoryVO);
	}
	
	public void sendInformationHistory(InformationHistoryVO infoHistoryVO) {
		blazeDS.sendInformationHistory(infoHistoryVO);
	}
	
	public void sendEventHistory(EventHistoryVO record){		
		blazeDS.sendEventHistory(record);		
	}
	
	public void sendQuestionHistory(QuestionHistoryVO record)
	{
	    blazeDS.sendQuestionHistory(record);
	}
	
	public void sendOrderHistory(OrderHistoryVO record){		
		blazeDS.sendOrderHistory(record);		
	}
	
	public void syncMessage(String message){
		blazeDS.syncMessage(message);
	}
	
	public void sendSystemState (String systemState) {
		blazeDS.sendSystemState(systemState);
	}
	
	public void sendScenaioName (String scenarioName) {
		blazeDS.sendScenarioName(scenarioName);
	}
	
	public void sendEnvironmentName (String environmentName) {
		blazeDS.sendEnvironmentName(environmentName);
	}
		
}
