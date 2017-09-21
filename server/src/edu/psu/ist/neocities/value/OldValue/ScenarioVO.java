package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;
import edu.psu.ist.neocities.value.PauseVO;

/**
 * @author bhellar
 * @date 1-21-09
 * @description ScenarioVO contains an array of incidents used for the scenario 
 *              as well as variables that override the difficulty settings. 
 */

public class ScenarioVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int scenarioID; //primary key
	public String label;
	 
	private ArrayList<Integer> eventList;	
	private ArrayList<Integer> pauseList = new ArrayList<Integer>(); //list of the pauseID to be dispatched for the scenario
	private ArrayList<Integer> briefingList = new ArrayList<Integer>(); // list of the breifingID to be dispatched for the scenario
	
	private long gameStopTime = 0;
	private boolean gameTimeBound = false;
	
	private long timeStep; // Millisecond measure of the timeStep used to calculate scoring 		
	private int dispatchDelay; // TimeStep measure of the time delay from when units are allocated to when they arrive on scene
	private int recallDelay; // TimeStep measure of the time delay from when units are recalled to when they return to the station
	
		 
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public ScenarioVO() {
			
	}
	
	//example row = "1, "Test Profile 1"
	public ScenarioVO(int _scenarioID, String _label)
	{
		scenarioID = _scenarioID;
		label = _label;		
	}
	
	public ScenarioVO(int _scenarioID, String _label, ArrayList<Integer> _events)
	{
		scenarioID = _scenarioID;
		label = _label;		
		this.eventList = _events;
	}
	
	public ScenarioVO(int _scenarioID, String _label, ArrayList<Integer> _incidents, int _timeStep, int _trafficDelay)
	{
		scenarioID = _scenarioID;
		label = _label;		
		this.eventList = _incidents;
		timeStep = _timeStep * 1000;
		dispatchDelay = _trafficDelay;
	}
	
	public ScenarioVO(int _scenarioID, String _label, ArrayList<Integer> _events, int _timeStep, int _trafficDelay, int _recallDelay)
	{
		scenarioID = _scenarioID;
		label = _label;		
		this.eventList = _events;
		timeStep = _timeStep * 1000;
		dispatchDelay = _trafficDelay;
		recallDelay = _recallDelay;
	}
		
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public void addIncident(int eventID) {
		this.eventList.add(eventID);		
	}
	
	public ArrayList<Integer> getEvents(){
		return this.eventList;
	}
	
	public void addPause(int pauseID) {
		pauseList.add(pauseID);		
	}
	
	public void addBriefing (int briefingID) {
	    briefingList.add(briefingID);
	}
	
	public ArrayList<Integer> getPauseList(){
		return pauseList;
	}
	
	public ArrayList<Integer> getBriefingList(){
	    return briefingList;
	}
	
	 public long getTimeStep() {
		return timeStep;
	}
    
    public int getTrafficDelay() {
    	return dispatchDelay;
    }
    
    public int getRecallDelay() {
    	return recallDelay;
    }
    
    public void setTimeStep(long ms){		
		timeStep = ms;		
	}
	public void setTimeStep(int sec){
		timeStep = (sec * 1000);		
	}	
	
	public void setTrafficDelay(int timeStep){
		dispatchDelay = timeStep;		
	}
	public void setRecallDelay(int timeStep){
		recallDelay = timeStep;
	}
	
	public long getGameStopTime() {
    	return gameStopTime;
    }
    
    public boolean isGameTimeBound() {
    	return gameTimeBound;
    }
    
    public void setGameStopTime(long ms){
		gameStopTime = ms;
		
		if (gameStopTime > 0){
			gameTimeBound = true;
		}
	}	
	public void setGameStopTime(int sec){
		gameStopTime = (sec * 1000);
		
		if (gameStopTime > 0){
			gameTimeBound = true;
		}
	}	
	public void setGameStopTime(int min, int sec){
		gameStopTime = (((min * 60) + sec) * 1000);
		
		if (gameStopTime > 0){
			gameTimeBound = true;
		}
	}
	
	public void setGameTimeBound(boolean bool){
		gameTimeBound = bool;
	}
		
}
