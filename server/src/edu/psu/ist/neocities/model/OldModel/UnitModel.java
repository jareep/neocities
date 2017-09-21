package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.value.OldValue.UnitVO;

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
			
	public static UnitModel instance(){
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
	
	public void dispatchUnits(int incidentID, ArrayList<UnitVO> unitArray, int trafficDelay) {			
		
		IncidentModel iModel = IncidentModel.instance();
		ResourceModel rModel = ResourceModel.instance();		
				
		for(int i = 0; i <= unitArray.size() - 1; i++){
			//dispatch each unit contained in the UnitArray to the matching unit in the data Array;
			unitLoop:
			for (int j = 0; j <= data.size() - 1; j++){
				
				if (data.get(j).badgeNo == unitArray.get(i).badgeNo){				
					data.get(j).status = "On Route";
					data.get(j).message = "dispatched to " + iModel.getLabelOf(incidentID);
					data.get(j).available = false;
					data.get(j).incidentID = incidentID;					
					data.get(j).isPending = true;
					data.get(j).setPendingDelay(trafficDelay); 
					
					break unitLoop; //stops the inner unit loop
				}				
			}//end of unitLoop
		//update the resource totals
		rModel.dispatchResource(unitArray.get(i).resourceID);
					
		}//end of outer unitArray loop
		
		iModel.setIncidentStatus(incidentID, "On Route");
	}
	
	//used by NCServer when units are dispatch units are recalled 
	public void recallUnits(ArrayList<UnitVO> unitList, int recallDelay){
		
		for (UnitVO unit : data){
			for (UnitVO recall : unitList){
				if (unit.badgeNo == recall.badgeNo){
					//unit.status = "Recall";
					unit.message = "Unit is Returning to Station";
					unit.isPending = false;
					unit.isRecallPending = true;
					unit.setRecallPendingDelay(recallDelay);
				}
				
			}//recall loop
		}//unit Loop					
	}//end of recall
	
	//used by EventController after events are completed or when units are reallocated
	public ArrayList<UnitVO> resetIncidentUnits(int incidentID){
		
		ArrayList<UnitVO> recalledUnits = new ArrayList<UnitVO>();
		
		ResourceModel rModel = ResourceModel.instance();		
		
		for (int j = 0; j <= data.size() - 1; j++){
			//if the unit matches the incident, 
			if (data.get(j).incidentID == incidentID){								
				//then reset the unit back to the initial state
				data.get(j).incidentID = 0; 
				data.get(j).status = "idle";
				data.get(j).message = "idle";
				data.get(j).available = true;	
				data.get(j).isCorrectlyAssigned = false;
				data.get(j).isPending = false;
				data.get(j).isRecallPending = false;
						
				//update the resource totals
				rModel.returnResource(data.get(j).resourceID); 				
				recalledUnits.add(data.get(j));
			}
		}
		
		return recalledUnits;
	}//end of returnUnits
	
	//used by EventController when recalled Units are returned home
	public void returnUnits(ArrayList<UnitVO> unitList){
						
		ResourceModel rModel = ResourceModel.instance();		
				
		for (UnitVO unit : data){
			for (UnitVO reset : unitList){
			
				if (unit.badgeNo == reset.badgeNo){				
					unit.incidentID = 0; 
					unit.status = "idle";
					unit.message = "idle";
					unit.available = true;	
					unit.isCorrectlyAssigned = false;
					unit.isRecallPending = false;
					unit.isPending = false;
							
					//update the resource totals
					rModel.returnResource(unit.resourceID); 													
				}				
			}//unitList reset Loop			
		}//unitLoop			
	}//end of returnUnits
	
	//used by EventController in ManageEventResources Function
	public ArrayList<UnitVO> getDispatchEligibleUnits (int incidentID){
		
		ArrayList<UnitVO> dispatchUnits = new ArrayList<UnitVO>();
		
		// Looping over all units 
		for(UnitVO unit : data){
			
			if(unit.incidentID == incidentID){
				
				//Dispatch Check
				if (unit.isPending){
					if (unit.getPendingDelay() < 1){
						dispatchUnits.add(unit);
					}
					else{
						unit.decrementPendingDelay();
					}								
				}//end of dispatchCheck
			}//end of event Match
		}//end of unitLoop
						
		return dispatchUnits;		
	}
	
	//used by EventController in ManageEventResources Function
	public ArrayList<UnitVO> getRecallEligibleUnits (int incidentID){
		
		ArrayList<UnitVO> recallUnits = new ArrayList<UnitVO>();
		
		// Looping over all units 
		for(UnitVO unit : data){
			
			if(unit.incidentID == incidentID){
				
				//Recall Check
				if (unit.isRecallPending){
					if (unit.getRecallPendingDelay() < 1){
						recallUnits.add(unit);
					}
					else{
						comm.consoleMessage("Answer - Unit isPending = " + unit.badgeNo + " delay = " +unit.getRecallPendingDelay());
						unit.decrementRecallPendingDelay();
					}								
				}//end of recallCheck
			}//end of event Match
		}//end of unitLoop
						
		return recallUnits;		
	}
	
	
	
	/****************************************************************
	 * Unit Manipulation Functions
	 ****************************************************************/
	
	//used by NCRO in the dispatchUnits function
	public UnitVO getUnit(int badgeNo){
		
		UnitVO unit = new UnitVO(999, 999, "fail.jpg");
	 		
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).badgeNo == badgeNo){
				unit = data.get(j);
				break;
			}
		}
		
		return unit;
	}
	
	
	//used by NCserver to get units that were most recently dispatched to the given incident
	public ArrayList<UnitVO> getDispatchedUnits(int incidentID){
		ArrayList<UnitVO> dispatchedUnits = new ArrayList<UnitVO>(); 
		String traceUnits = "Answer - Dispatched Units Badge List: ";
		
		for (int j = 0; j <= data.size() - 1; j++){
			if ((data.get(j).incidentID == incidentID)&&(!data.get(j).isPending)){				
				dispatchedUnits.add(data.get(j));
				traceUnits = traceUnits + data.get(j).badgeNo + ", ";
			}				
		}
		comm.consoleMessage(traceUnits);
		return dispatchedUnits;
	}
	
	//used by NCserver to get units that were most recently dispatched to the given incident
	public ArrayList<UnitVO> getPendingUnits(int incidentID){
		ArrayList<UnitVO> dispatchedUnits = new ArrayList<UnitVO>(); 
		String traceUnits = "UM - Dispatched Units Badge List: ";
		
		for (int j = 0; j <= data.size() - 1; j++){
			if ((data.get(j).incidentID == incidentID) && (data.get(j).isPending)){				
				
				dispatchedUnits.add(data.get(j));
				
				traceUnits = traceUnits + data.get(j).badgeNo + ", ";
			}				
		}
		comm.consoleMessage(traceUnits);
		return dispatchedUnits;
	}
	
	public ArrayList<UnitVO> getRecallPendingUnits(int incidentID) {
		ArrayList<UnitVO> recalledUnits = new ArrayList<UnitVO>(); 
				
		for (UnitVO unit : data){
			if ((unit.incidentID == incidentID) && (unit.isRecallPending)){				
				
				recalledUnits.add(unit);
			}				
		}
		
		return recalledUnits;
	}
		
	//used by NCServer to see if any units are dispatched to an incident
	public int getDispatchedTotal(int incidentID){
		int count = 0;
		
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).incidentID == incidentID){				
				count++;
			}				
		}
		
		return count;
	}
		
	//Sets unit status to 'On Scene", used by the UnitArrival Simulator Function
	public void setUnitsStatus(ArrayList<UnitVO> unitArray, String status) {			
				
		for(int i = 0; i <= unitArray.size() - 1; i++){
			unitLoop:
			for (int j = 0; j <= data.size() - 1; j++){
				if (data.get(j).badgeNo == unitArray.get(i).badgeNo){				
					data.get(j).status = status;
					data.get(j).message = "Unit has arrived on scene and is assessing the situation";
					data.get(j).isPending = false;
					break unitLoop; //stops the inner unit loop
				}				
			}//end of unitLoop				
		}//end of outer unitArray loop				
	}
	
	public void setUnitFeedback(UnitVO unit, String feedbackMessage){
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).badgeNo == unit.badgeNo){
				data.get(j).message = feedbackMessage;
				break;
			}
		}
	}//end of function
	
	public void addUnitFeedback(UnitVO unit, String feedbackMessage){
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).badgeNo == unit.badgeNo){
				data.get(j).message += feedbackMessage;
				break;
			}
		}
	}//end of function

	public void setCorrectUnitArrival(int badgeNo){
		for (UnitVO unit : data ){			
			if( unit.badgeNo == badgeNo){
				unit.isCorrectlyAssigned = true;			
				break;
			}			
		}						
	} // end of function
	
	
	
	
} //End of UnitModel
