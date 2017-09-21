package edu.psu.ist.neocities.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import edu.emory.mathcs.backport.java.util.Arrays;
import edu.psu.ist.neocities.NeoCitiesDS;
import edu.psu.ist.neocities.model.EnvironmentModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.model.UnitModel;
import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.simulator.*;
import edu.psu.ist.neocities.util.*;

public class TestResourceAllocation {
	
//////// INITIAL CONDITIONS //////////////////
	
	private static TestResourceAllocation instance = new TestResourceAllocation();
	
	public int noOfEvents = 4;
	public int noOfResources = 4;
	public int scenarioLength = 100;
	public int scenarioTimeStep = 3;
	public int travelTimeStepsGeneral = 2;
	public int returnTimeStepsGeneral = 2;
	
	// arrays for resources
	//public int resourceCapList[] = {4,4,5,5}; // capping resource limits per answerVO
	public int unitCapList[] = {4,4,5,5}; // capping maximum units per event
	public int unitsExpectedList[] = {3,3,3,3}; //number of expected/required units per event
	
	public int unitsPerResourceList[] = {5,6,6,6}; // list of no. of units per resource
	public char[] resourceLabelList = {'A','B','C','D'}; // list of resource labels per resource
	public int[] answersByEventsList = {3,2,1,0}; // list of resourceID's per event 
	
	public double generalMagCap = 8.0; 
	public int generalTimeLimit = 5; // the time limit for all events
	public double initialSeverityList[] = {2.0,2.0,4.0,4.0};
	public int dispatchTimeList[] = {10, 20, 30, 40};	
	
	public int  t;// timeStep
	
	public int dispatch_count = 0;
	
////////////////////////////////////////////////////

	
List<ActionInstance> actions;
List<EventVO> eventList;
List<ResourceVO> resList;    
    
UnitModel unitModel = UnitModel.instance();
ScenarioModel scenarioModel = ScenarioModel.instance();
EnvironmentModel environmentModel = EnvironmentModel.instance();
EventController eventController = new EventController();
NeoCitiesDS neocitiesDS = NeoCitiesDS.instance();
   
public static TestResourceAllocation instance()
{
	return instance;
}
	
	TestResourceAllocation(){
		
		eventList = new ArrayList<EventVO>();
		actions = new ArrayList<ActionInstance>();
		resList = new ArrayList<ResourceVO>();
		t = 0;
		
	}// ending constructor
	

	public void dispatch(int _resourceID, int _eventID, int _time){
		
		this.actions.add(new ActionInstance(true,_resourceID, _eventID, _time));
		
	}//ending dispatch
	
	
	public void recall(int _resourceID, int _eventID, int _time){
		
		this.actions.add(new ActionInstance(false,_resourceID, _eventID, _time));
		
	}// ending recall
	
//////////////////OTHER INITIAL CONDITIONS ////////////////// 

	public int getTimeStep(){
		return this.t;		
	}
	
	public void incrementTimeStep(){
		this.t++;
	}

	
	public void specifyResponses(){
		
		//specify dispatches and recalls with their respective times here
		
		// Order:- _resourceID, eventID, timeOfDispatch
		
	//	dispatch(3, 0, 12); // correct answer 
	//	dispatch(2,1,32); // correct answer
		dispatch(0, 2, 23); // wrong answer
	//	recall(3, 0, 20); // correct recall
	//	recall(2, 1, 45); // correct recall .... what about wrong recalls?
		
	}// specifyResponses()..

	////////////////////////////////////////////////////////////////////////
	
	public void testInput(){ // this provides the input to the test
		
		 // specify scenario-length and scenario timeStep length
		TimeSet ts = new TimeSet();
		
		scenarioModel.TimeStepLength =  this.scenarioTimeStep;
		
		ts.setTimeSteps(this.scenarioLength, scenarioModel.TimeStepLength);
		
		scenarioModel.setTimeLimit(ts);
		
		//Event Definition
			
		//specify unitCaps and resourceCaps for each event
		
		for(int events = 0 ; events < this.noOfEvents ; events++){
			
			EventVO eventVO = new EventVO();
			
			eventVO.eventID = events;
			eventVO.initialSevarity = this.initialSeverityList[events];
			
			TimeSet ts2 = new TimeSet();
			ts2.setTimeSteps(this.generalTimeLimit, scenarioModel.TimeStepLength);
			eventVO.setTimeLimit(ts2);
			
			TimeSet ts3 = new TimeSet();
			ts3.setTimeSteps(dispatchTimeList[events], scenarioModel.TimeStepLength);
			eventVO.setDispatchTime(ts3);
			
			eventVO.magCap = this.generalMagCap;
			eventVO.unitCap = this.unitCapList[events];
			
			eventList.add(eventVO);
			//scenarioModel.Events.add(eventVO);
			//scenarioModel.Events.add(events, eventVO);
						
		}// events loop
		
		scenarioModel.setEvents(eventList);
		
		// set four types of resources
		
		int unitCount = 0;
		
		for(int resources = 0; resources < this.noOfResources ; resources++){
			
			ResourceVO resourceVO = new ResourceVO();
			
			resourceVO.id = resources;
			resourceVO.resourceName = ""+this.resourceLabelList[resources];
			resourceVO.total = this.unitsPerResourceList[resources];
			resourceVO.travelTimeSteps = this.travelTimeStepsGeneral; // simplification
			resourceVO.returnTimeSteps =this.returnTimeStepsGeneral; // simplification
	
			resList.add(resourceVO);
			
			
			// for each resource - specify the units
			
			for(int units = 0; units < this.unitsPerResourceList[resources]; units++){
				
				UnitVO unitVO = new UnitVO();
							
				unitVO.badgeNo = unitCount + units;
				unitVO.resourceID = resources;
				//unitVO.setPendingDelay(0);
				//unitVO.setRecallPendingDelay(0);
				unitVO.available = true;
				unitModel.data.add(unitVO);
				
			}// units loop
			
			environmentModel.Resources = resList;
			
			unitCount = unitCount + this.unitsPerResourceList[resources];
			
		   }// resources loop
		
	  // now, specify all answers applicable
		
	  for(int events = 0; events < scenarioModel.Events.size(); events++){
		  
		  AnswerVO answerVO = new AnswerVO();
		  answerVO.resourceID = this.answersByEventsList[events];
		  answerVO.expected = this.unitsExpectedList[events];
		  
		  List<AnswerVO> answerList = new ArrayList<AnswerVO>();
		  answerList.add(answerVO);
		  scenarioModel.Events.get(events).answers = answerList; // one answer applicable per event
		  
	  }// ending events loop
	  
	  environmentModel.Locations = new ArrayList<LocationVO>();
	  environmentModel.Locations.add(new LocationVO());
		
	  this.specifyResponses();
		
	}//testInput()
	
	
	
	public void executeCodeBody(){
		
		this.neocitiesDS.debugMode = true;
		
		
		while(true){
	
			//timeStep begins - 
		    System.out.println("TimeStep: "+this.getTimeStep());
			
			// Activate and DeActivate Events here - 
			
			for(int e = 0; e < scenarioModel.Events.size(); e++){
				
				scenarioModel.Events.get(e).magnitudeArray.add(t, this.initialSeverityList[e]);
				
				if(scenarioModel.Events.get(e).getDispatchTime()==t){
						
						if(scenarioModel.Events.get(e).isActive()){
							System.out.println("Error in event activation sequence!");
							System.exit(1);}
						
						scenarioModel.Events.get(e).activate();
						
					}//ending if for dispatch time
					
					if(scenarioModel.Events.get(e).getDispatchTime()+scenarioModel.Events.get(e).timeLimit.getTimeSteps(3)==t){
						
						if(!scenarioModel.Events.get(e).isActive()){
							System.out.println("Error in event activation sequence!");
							System.exit(1);}
						
						scenarioModel.Events.get(e).condition = 2;// define it as successful by default
				
						
					}
				
			}// e loop
			
		
		   	// Answer Checking and Dispatch/Recall
			
			for(int a=0 ; a<actions.size(); a++){
				
				ActionInstance actionInstance = new ActionInstance();
					
					if(actions.get(a).timeOfAction==t){
							actionInstance = actions.get(a);
					
					
					if(actionInstance.dispatch){
						//write code for dispatch						
						
				
						ArrayList<UnitVO> unitArray = new ArrayList<UnitVO>();
						UnitVO unitVO = new UnitVO();
						unitVO.resourceID = actionInstance.resourceID;
						
						int badgeNo = -2;
						
						
						for(int u = 0; u<unitModel.data.size(); u++){ // scan all units
							
								if(unitModel.data.get(u).resourceID==actionInstance.resourceID){
									
									if(unitModel.data.get(u).available)
									{
										badgeNo = unitModel.data.get(u).badgeNo;
										break; // just find out which is the next unit available (by getting the badge number)
									}
								}
							
						}// ending u loop
						
						unitVO.badgeNo = badgeNo;
						unitArray.add(unitVO);
						//unitModel.dispatchUnits(actionInstance.eventID, unitArray);
						this.dispatch_count++;
 					}//ending if
					else{
						//write code for recall
						
						ArrayList<UnitVO> unitArray = new ArrayList<UnitVO>();
						UnitVO unitVO = new UnitVO();
						unitVO.resourceID = actionInstance.resourceID;
						
						int badgeNo = -2;
						
						
						for(int u = 0; u<unitModel.data.size(); u++){ // scan all units
							
								if(unitModel.data.get(u).resourceID==actionInstance.resourceID){
									
									if(!unitModel.data.get(u).available)
									{
										badgeNo = unitModel.data.get(u).badgeNo;
										break; // just find out which is the next unit available (by getting the badge number)
									}
								}
							
						}// ending u loop
						
						unitVO.badgeNo = badgeNo;
						
						unitArray.add(unitVO);
						//unitModel.recallUnits(unitArray, 1);
						this.dispatch_count--;
 					
					}//ending else
				}
				
			} // ending a loop
			
			
			eventController.manageEventResources();
		//	eventController.manageEventCompletion();
			
			if(t==this.scenarioModel.getScenarioLength()-1)
				break;
			else
			scenarioModel.incrementActiveEvents();
			
			this.consistencyCheck();
			
			this.incrementTimeStep();
			
			
		}// ending while loop
		
	}// executeCodeBody()
	
	
public void consistencyCheck(){ // this prints the test result

	
	// check if total dispatched + total available = total altogether
	
	int available_count = 0;
	int eventAllocated = 0;
	int totalUnits = 0;
	boolean consistent = true;
	
	for(int k = 0; k < this.unitModel.data.size(); k++){
		
				if(unitModel.data.get(k).available) available_count++;
				
				//if(unitModel.data.get(k).dispatchData.get(0).eventID !=-1) eventAllocated++;
		
	}// ending k loop
	
	totalUnits = this.unitModel.data.size();
	
	if(available_count+eventAllocated!=totalUnits) consistent = false;
	
	System.out.println("TimeStep: "+this.getTimeStep());
	System.out.println("--------------------------------------Consistency Check Table--------------------------------------------");
	System.out.println("Total Available: "+available_count);
	System.out.println("Total Allocated to events: "+eventAllocated);
	System.out.println("Total number of units in unit-model: "+totalUnits);
	if(!consistent)System.out.println("INCONSISTENT");
	System.out.println("-------------------------------------------------------------------------------------------------------------");
	System.out.println();
	
}//consistencyCheck()
	
	public static void main(String args[]){
		
			TestResourceAllocation test40 = new TestResourceAllocation();
			
			test40.testInput();
			test40.executeCodeBody();
			test40.consistencyCheck();
						
			
	}// ending main()
	
	
	class ActionInstance{
		
		boolean dispatch = true;// false for recall
		
		int eventID;
		int resourceID;
		int timeOfAction;
		
		ActionInstance(){
			
		}
		
		ActionInstance(boolean isDispatch,  int _resourceID, int _eventID, int _timeOfAction){
			
			dispatch = isDispatch;
			eventID = _eventID;
			resourceID = _resourceID;
			timeOfAction = _timeOfAction;
			
		}// ending constructor
		
	}// ending ActionInstance
	
	

}//TestResourceAllocation
