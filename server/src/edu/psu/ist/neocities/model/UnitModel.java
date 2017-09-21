package edu.psu.ist.neocities.model;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.control.CommController;

import edu.psu.ist.neocities.value.EventVO;
import edu.psu.ist.neocities.value.ResourceVO;
import edu.psu.ist.neocities.value.UnitVO;
import edu.psu.ist.neocities.value.dataTypes.*;
import edu.psu.ist.neocities.model.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description stores in a list the UnitVO Data
 */
public final class UnitModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static UnitModel instance = new UnitModel();

	private UnitModel() {
		// Required for Singleton Design Pattern
	}

	public static UnitModel instance() {
		return instance;
	}

	/****************************************************************
	 * Variables
	 ****************************************************************/
	CommController comm = CommController.instance();

	public List<UnitVO> data = new ArrayList<UnitVO>();
	
	

	/****************************************************************
	 * Dispatch & Recall & Reset Unit Functions
	 ****************************************************************/

	public void dispatchUnits(int _eventID, ArrayList<UnitVO> unitArray, int _locationID) { 

	
		ScenarioModel sModel = ScenarioModel.instance();
		EnvironmentModel eModel = EnvironmentModel.instance();
		String eventLabel;
		
		EventVO event ;
		if(_eventID==-9999){		
		
		 event = sModel.getEvent(_eventID);
		 eventLabel = event.label;
		
		}
		else 
		eventLabel = "-9999";
		
		
		ResourceVO resource;
		
		for (int i = 0; i <= unitArray.size() - 1; i++) {
			// dispatch each unit contained in the UnitArray to the matching
			// unit in the data Array;

			for (int j = 0; j <= data.size() - 1; j++) {
				resource = eModel.getResource(data.get(j).resourceID);
				if (data.get(j).badgeNo == unitArray.get(i).badgeNo) {
				
					
					if(data.get(j).locationID!=_locationID){
					 
						// This code section is redundant - could be removed after testing is complete
						
						data.get(j).locationID =  _locationID;
					    
						
					    data.get(j).message = "dispatched to "
							+ eventLabel;
						

						if (!resource.isAction()) {
							data.get(j).available = false;
						} else {
							data.get(j).available = true;
						}

						data.get(j).isPending = true;
						data.get(j).eventID = _eventID;

						data.get(j).setPendingDelay( resource.travelTimeSteps) ;
					}
					else{ 
						
						// Code always enters this condition  
						
						data.get(j).message = "dispatched to "+eventLabel;

						if (!resource.isAction()) {
							data.get(j).available = false;
						} else {
							data.get(j).available = true;
						}

						data.get(j).isPending = true;
						data.get(j).eventID = _eventID;

						data.get(j).setPendingDelay( resource.travelTimeSteps) ;
					}
						
						
						break; // stops the inner unit loop
				}
			}// end of unitLoop
			
		
				// update the resource totals
			eModel.dispatchResource(unitArray.get(i).resourceID);

		}// end of outer unitArray loop

		// iModel.setIncidentStatus(_eventID, "On Route");
		
		
	
	}// dispatchUnits()
	


	// used by NCServer when units are dispatch units are recalled
	public void recallUnits(ArrayList<UnitVO> unitList) {
		EnvironmentModel eModel = EnvironmentModel.instance();
		ResourceVO resource;
		for (UnitVO unit : data) {
			resource = eModel.getResource(unit.resourceID);
			for (UnitVO recall : unitList) {
				if (unit.badgeNo == recall.badgeNo) {
					
					if(unit!=null){
					unit.status = "Recall";
					unit.isPending = false;
					unit.isRecallPending=true;
					unit.setRecallPendingDelay(resource.returnTimeSteps);
					
					//unit.status = "Recall";
					//unit.message = "Unit is Returning to Station";
					//unit.isPending = false;
					//unit.isRecallPending = true;
					//unit.setRecallPendingDelay(resource.returnTimeSteps);
					}
					
				}

			}// recall loop
		}// unit Loop
	}// end of recall


	// used by EventController after events are completed or when units are
	// reallocated
	public ArrayList<UnitVO> resetEventUnits(int eventID) {

		ArrayList<UnitVO> recalledUnits = new ArrayList<UnitVO>();

		EnvironmentModel eModel = EnvironmentModel.instance();
		
		ScenarioModel sModel = ScenarioModel.instance();
		

		for (int j = 0; j <= data.size() - 1; j++) {
			// if the unit matches the incident,
			
			
			  if (data.get(j).eventID == eventID) {
				
				  // then reset the unit back to the initial state
				  
				  data.get(j).eventID = -1;
				  data.get(j).status= "idle";
				  data.get(j).message = "idle";
				  data.get(j).available = true;
				  data.get(j).isCorrectlyAssigned = false;
				  data.get(j).isPending = false;
				  data.get(j).isRecallPending = false;

				// update the resource totals
				eModel.returnResource(data.get(j).resourceID);
				recalledUnits.add(data.get(j));
				
			}// ending if
			  
		}// ending j

		return recalledUnits;
	}// end of resetEventUnits

	
	// used by EventController when recalled Units are returned home
	public void returnUnits(ArrayList<UnitVO> unitList) {

		EnvironmentModel eModel = EnvironmentModel.instance();

		for (UnitVO unit : data) {
			for (UnitVO reset : unitList) {

				if (unit.badgeNo == reset.badgeNo) {
								  
					
					unit.eventID = -1;
				    unit.status = "idle";
				    unit.message = "idle";
					unit.available = true;
					unit.isCorrectlyAssigned = false;
					unit.isRecallPending = false;
					unit.isPending = false;

					// update the resource totals
					eModel.returnResource(unit.resourceID);
					
				}
			}// unitList reset Loop
		}// unitLoop
	}// end of returnUnits

	
	// used by EventController in ManageEventResources Function
	public ArrayList<UnitVO> getDispatchEligibleUnits(int eventID, int locationID) {

		ArrayList<UnitVO> dispatchUnits = new ArrayList<UnitVO>();

		// Looping over all units
		for (UnitVO unit : data) {

			if(unit!=null)
			if (unit.eventID == eventID) 
			{
				if (eventID != -9999) { System.out.println("Dispatched unit to event "+eventID+" found."); }
				  
				   // Dispatch Check
						if (unit.isPending) 
						{
					
					    if (eventID != -9999) { System.out.println("Dispatched unit to event "+eventID+" is Pending."); }
					
					    	if (unit.getPendingDelay() < 1) 
					    	{
						
					    	if (eventID != -9999) { System.out.println("Dispatched unit to event "+eventID+" has low pending delay."); }
					    	
					    	dispatchUnits.add(unit);
					
					    	}	
					    	else 
					    	{
					    		unit.decrementPendingDelay();
					    	}
						}// end of dispatchCheck
			
			}// end of event Match
		
		}// end of unitLoop

		return dispatchUnits;
	}

	// used by EventController in ManageEventResources Function
	public ArrayList<UnitVO> getRecallEligibleUnits(int eventID, int locationID) {

		ArrayList<UnitVO> recallUnits = new ArrayList<UnitVO>();

		// Looping over all units
		for (UnitVO unit : data) {

			if(unit!=null)
			if (unit.eventID == eventID) {

				// Recall Check
				if (unit.isRecallPending) {
					if (unit.getRecallPendingDelay() < 1) {
						recallUnits.add(unit);
					} else {
						comm.consoleMessage("Answer - Unit isPending = "
								+ unit.badgeNo + " delay = "
								+ unit.getRecallPendingDelay());
						unit.decrementRecallPendingDelay();
					}
				}// end of recallCheck
			}// end of event Match
		}// end of unitLoop

		return recallUnits;
	}

	/****************************************************************
	 * Unit Manipulation Functions
	 ****************************************************************/

	// used by NCRO in the dispatchUnits function
	public UnitVO getUnit(int badgeNo) {

		UnitVO unit = new UnitVO(999, 999, "fail.jpg");

		for (int j = 0; j <= data.size() - 1; j++) {
			if (data.get(j).badgeNo == badgeNo) {
				unit = data.get(j);
				break;
			}
		}

		return unit;
	}

	// used by NCserver to get units that were most recently dispatched to the
	// given incident
	public ArrayList<UnitVO> getDispatchedUnits(int eventID) {
		
		ArrayList<UnitVO> dispatchedUnits = new ArrayList<UnitVO>();
		String traceUnits = "Answer - Dispatched Units Badge List: ";
		ScenarioModel sModel = ScenarioModel.instance();
		
		int thisLocation = sModel.Events.get(eventID).locationId;

		for (int j = 0; j <= data.size() - 1; j++) {
			
			if(data.get(j)!=null)
			if ((data.get(j).eventID == eventID) && (!data.get(j).isPending)) {
				dispatchedUnits.add(data.get(j));
				traceUnits = traceUnits + data.get(j).badgeNo + ", ";
			}
		}
		comm.consoleMessage(traceUnits);
		return dispatchedUnits;
	}

	
	// used by NCserver to get units that were most recently dispatched to the
	// given incident
	public ArrayList<UnitVO> getPendingUnits(int eventID) {
		ArrayList<UnitVO> dispatchedUnits = new ArrayList<UnitVO>();
		String traceUnits = "UM - Dispatched Units Badge List: ";
		
		ScenarioModel sModel = ScenarioModel.instance();

		for (int j = 0; j <= data.size() - 1; j++) {
			
			if(data.get(j)!=null)
			if ((data.get(j).eventID == eventID) && (data.get(j).isPending)) {

				dispatchedUnits.add(data.get(j));

				traceUnits = traceUnits + data.get(j).badgeNo + ", ";
			}
		}
		comm.consoleMessage(traceUnits);
		return dispatchedUnits;
	}

	
	public ArrayList<UnitVO> getRecallPendingUnits(int eventID) {
		ArrayList<UnitVO> recalledUnits = new ArrayList<UnitVO>();
		

		for (UnitVO unit : data) {
			
			if(unit!=null)
				if (((unit.eventID == eventID) && (unit.isRecallPending))) {

				recalledUnits.add(unit);
			}
		}

		return recalledUnits;
	}
	

	// used by NCServer to see if any units are dispatched to an incident
	public int getDispatchedTotal(int eventID) {
		int count = 0;

		ScenarioModel sModel = ScenarioModel.instance();
		
		for (int j = 0; j <= data.size() - 1; j++) {
			
			if(data.get(j)!=null)
				if (data.get(j).eventID == eventID) {
				count++;
				}
		}

		return count;
	}

	// Sets unit status to 'On Scene", used by the UnitArrival Simulator
	// Function
	public void setUnitsStatus(ArrayList<UnitVO> unitArray, String status) {

		for (int i = 0; i <= unitArray.size() - 1; i++) {
			unitLoop: for (int j = 0; j <= data.size() - 1; j++) {
				if (data.get(j).badgeNo == unitArray.get(i).badgeNo) {
					
				   if(data.get(j)!=null)
				   { 	    
					   data.get(j).status = status;
					   data.get(j).message = "Unit has arrived on scene and is assessing the situation";
					   data.get(j).isPending = false;
					   break unitLoop; // stops the inner unit loop
				   }
				
				}
			}// end of unitLoop
		}// end of outer unitArray loop
	}

	public void setUnitFeedback(UnitVO unit, String feedbackMessage) {
		for (int j = 0; j <= data.size() - 1; j++) {
			if (data.get(j).badgeNo == unit.badgeNo) {
			
				   if(data.get(j)!=null)
				   { 	    
					    data.get(j).message = feedbackMessage;
						break;
				   }			
			
			}
		}
	}// end of function

	
	public void addUnitFeedback(UnitVO unit, String feedbackMessage) {
		
		for (int j = 0; j <= data.size() - 1; j++) {
			
			if (data.get(j).badgeNo == unit.badgeNo) {
				
				 if(data.get(j)!=null)
				   { 	    
						data.get(j).message += feedbackMessage;
						break;
				   }	
				
			
			}
		
		}
	}// end of function
	
	

	public void setCorrectUnitArrival(int badgeNo) {
		EnvironmentModel eModel = EnvironmentModel.instance();
		
		for (UnitVO unit : data) {
			if (unit.badgeNo == badgeNo) {
				
				 if(unit!=null)
				   { 	    
					 	unit.message = eModel.getResource(unit.resourceID).correctFeedback;
						unit.isCorrectlyAssigned = true;
						break;
				   }	
				
				
			}
		}
	} // end of function
	
	public void setIncorrectUnitArrival(int badgeNo) {
		EnvironmentModel eModel = EnvironmentModel.instance();
		
		for (UnitVO unit : data) {
			if (unit.badgeNo == badgeNo) {
				
				if(unit!=null)
				{ 
				
					unit.message = eModel.getResource(unit.resourceID).incorrectFeedback;
					unit.isCorrectlyAssigned = false;
					unit.isPending = false;
					break;
					
				}			
			
			}
		}
	} // end of function
	
	public void resetModel () 
	{
		this.data = new ArrayList<UnitVO>();
	}
	
	public void terminateRejectedUnit(UnitVO unitVO){
		 // This should be called only for those units that were rejected   
		   	
		  int removeIndex = -1;
			
			for (int k = 0 ; k < data.size(); k++){
					
				if(unitVO.badgeNo==data.get(k).badgeNo){
					removeIndex = k;
					break;
				}
			}
			
			data.remove(removeIndex);
			
			//comm.consoleMessage("ERROR - deleting unit with badgeNo : "+unitVO.badgeNo);
				
	}// terminateRejectedUnit
	
	public void terminateUnitsAtEvent(EventVO event){
		
		ArrayList<Integer> removeList = new ArrayList<Integer>();
		
		
		for(int u = 0 ; u< this.data.size(); u ++){
				
				if(data.get(u).eventID==event.getId()){
					
							removeList.add(u);
					
				}
				
			} // u loop
		
		for(int k = 0 ; k < removeList.size() ; k++)
		data.remove(removeList.get(k));
			
	
		
	} // terminate

} // End of UnitModel