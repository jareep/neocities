package edu.psu.ist.neocities;

import flex.messaging.MessageBroker;
import flex.messaging.endpoints.Endpoint;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.util.UUIDUtils;


import java.util.Arrays;
import java.util.List;

import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.value.HistoryValue.*;

public class NeoCitiesDS {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static NeoCitiesDS instance = new NeoCitiesDS();
	
	public boolean debugMode = false;						// If this is set to true, all messages will be routed to System.out
	
	public int numReturns = 0;								// Used to avoid infinte error loops in console message
	
	private NeoCitiesDS() {
		// Required for Singleton Design Pattern
	}
			
	public static NeoCitiesDS instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
		
	MessageBroker msgBroker = MessageBroker.getMessageBroker(null);
	String clientID = UUIDUtils.createUUID();
	AsyncMessage msg;
		
	/****************************************************************
	 * NeoCITIES Client Messages
	 ****************************************************************/
		
	public void sendEvent(EventVO event) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("neocitiesDS");
			msg.setHeader("DSSubtopic", "events");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(event);
			this.routeMessageToService(msg, null, event.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending Event #" + event.getId());
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending event");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendTime(String gameTime) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("time-stream");
			//msg.setHeader("DSSubtopic", "time");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(gameTime);
			this.routeMessageToService(msg, null, gameTime); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - " + gameTime);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending time");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendSystemState(String systemState){
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("sync-stream");
			//msg.setHeader("DSSubtopic", "time");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(systemState);
			this.routeMessageToService(msg, null, systemState); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - " + systemState);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending time");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendSyncTime(int timeStep) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("timeSync-stream");
			//msg.setHeader("DSSubtopic", "time");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(timeStep);
			this.routeMessageToService(msg, null, Integer.toString(timeStep)); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Current Synced TimeStep" + timeStep);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending sync time message");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendInformation(InformationVO information) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("info-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(information);
			this.routeMessageToService(msg, null, information.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending Information ID: " + information.id);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending Information");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendLocations(LocationVO location)
	{
		try
		{
			
			msg = new AsyncMessage();
			msg.setDestination("location-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(location);
			
			this.routeMessageToService(msg, null, location.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending Location " + location.name);
		}
		catch (Exception e)
		{
			
		
			e.printStackTrace();
			consoleMessage("ERROR - Error Sending locations ");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendUnits(List<UnitVO> unitList) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("unit-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(unitList);
			this.routeMessageToService(msg, null, unitList.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - unitList");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending units");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendPlayerUpdate(PlayerVO player) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("player-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(player);
			this.routeMessageToService(msg, null, player.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - update player " + player.userName + " roleID = " + player.roleID + " incident = " + player.selectedIncidentID);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending console message");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendPause(PauseVO pause) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("pause-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(pause);
			this.routeMessageToService(msg, null, pause.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Pausing Simulation MSG Start Time: " + pause.pauseTime.toString()
							+ " Duration: " + pause.timeLimit.toString());
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending pause");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	


	
	public void sendScore(ScoreVO score) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("score-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(score);
			this.routeMessageToService(msg, null, score.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Score");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending score");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}

	
	/****************************************************************
	 * NeoCITIES Console Messages
	 ****************************************************************/
	
	public void consoleMessage(String message) {
		
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("console-stream");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(message);
			this.routeMessageToService(msg, null, message); //msgBroker.routeMessageToService(msg, null);
			if (!debugMode) { System.out.println(message); }
			this.numReturns = 0;
			
		}
		catch (Exception e)
		{
			if (this.numReturns == 0)
			// This check is to ensure that an infinite loop of errors does not occur...
			{
				consoleMessage("ERROR - Error Sending console message: " + message);
				consoleMessage("ERROR - " + e.getLocalizedMessage());
				numReturns++;
			}
			else
			{
				e.printStackTrace();
				numReturns = 0;
			}
		}
	}
	
	public void sendLocationFeedback(int locationID, String feedback)
	{
		try
		{
			msg = new AsyncMessage();
			msg.setHeader("locationID", locationID);
			msg.setDestination("feedback-stream");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(feedback);
			this.routeMessageToService(msg, null, feedback); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending location feed back for " + locationID);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending location feedback");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendChatMSG(String message) {
		try
		{
			msg = new AsyncMessage();
			msg.setHeader("userName", "Admin");
			msg.setHeader("sender", "Commander");
			msg.setDestination("chat");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(message);
			this.routeMessageToService(msg, null, message); //msgBroker.routeMessageToService(msg, null);
			System.out.println(message);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending chat message");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}

	public void sendActionHistory(ActionHistoryVO actionHistoryVO) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("ah-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(actionHistoryVO);
			this.routeMessageToService(msg, null, actionHistoryVO.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - ActionHistory");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending action history");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public  void sendInformationHistory(InformationHistoryVO infoHistoryVO) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("ih-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(infoHistoryVO);
			this.routeMessageToService(msg, null, infoHistoryVO.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - InformationHistory");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending information history");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	
	public void sendEventHistory(EventHistoryVO record) {
		try
		{
			
			/*msg = new AsyncMessage();
			msg.setDestination("location-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(location);
			System.out.println(msg.toString());
			this.routeMessageToService(msg, null, location.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending Location " + location.name);*/
			
			/*msg = new AsyncMessage();
			msg.setDestination("info-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(information);
			this.routeMessageToService(msg, null, information.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sending Information ID: " + information.id);*/
			
			msg = new AsyncMessage();
			msg.setDestination("eh-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(record);
			this.routeMessageToService(msg, null, record.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Event History Record");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending event history");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendQuestionHistory (QuestionHistoryVO record)
	{
	    try
	    {
	        msg = new AsyncMessage();
	        msg.setDestination("qh-stream");
	        msg.setClientId(clientID);
	        msg.setMessageId(UUIDUtils.createUUID());
	        msg.setTimestamp(System.currentTimeMillis());
	        msg.setBody(record);
	        this.routeMessageToService(msg, null, record.toString()); //msgBroker.routeMessageToService(msg, null);
	        consoleMessage("DS - Question History Record");
	        
	    }
	    catch (Exception e)
	    {
	        consoleMessage("ERROR - Error Sending question history");
	        consoleMessage("ERROR - " + e.getLocalizedMessage());
	    }
	}
	
	public void sendOrderHistory(OrderHistoryVO record) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("oh-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(record);
			this.routeMessageToService(msg, null, record.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Order History Record");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending order history");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void sendScenarioName(String scenarioName) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("scenario-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(scenarioName);
			this.routeMessageToService(msg, null, scenarioName.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sent Scenario Name");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending Scenario Name");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
		
	}
	
	public void sendEnvironmentName(String environmentName) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("environment-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(environmentName);
			this.routeMessageToService(msg, null, environmentName.toString()); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sent Environment Name");
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending Environment Name");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	public void syncMessage(String message) {
		try
		{
			msg = new AsyncMessage();
			msg.setDestination("sync-stream");
			//msg.setHeader("DSSubtopic", "units");
			msg.setClientId(clientID);
			msg.setMessageId(UUIDUtils.createUUID());
			msg.setTimestamp(System.currentTimeMillis());
			msg.setBody(message);
			this.routeMessageToService(msg, null, message); //msgBroker.routeMessageToService(msg, null);
			consoleMessage("DS - Sync Message: " + message);
		}
		catch (Exception e)
		{
			consoleMessage("ERROR - Error Sending sync message");
			consoleMessage("ERROR - " + e.getLocalizedMessage());
		}
	}
	
	/*	This function will test to see if the server is in debug mode, and then if it isn't will send the message (msg) through the blazeDS
	 * 	channel, and if it is in debug mode, will Print the debug message to the console
	 */
	public void routeMessageToService(AsyncMessage msg, Endpoint end, String debug) throws Exception
	{
		if (!this.debugMode)
		{
			msgBroker.routeMessageToService(msg, end);
		}
		else
		{
			System.out.println(debug);
		}
	}

	

	
}