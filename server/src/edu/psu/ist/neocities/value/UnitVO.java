package edu.psu.ist.neocities.value;

import edu.psu.ist.neocities.util.*;
import java.util.ArrayList;
import edu.psu.ist.neocities.value.dataTypes.*;

public class UnitVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int badgeNo = -1; //primary key
	public String icon = ""; 
	public String status = "";
	public String message = "";
	public boolean available = true;
	public int resourceID = -1; // foreign key to ResourceVO, indicates resource type
    public int eventID = -1; // foreign key 
	
//	public ArrayList<UnitDispatchData>  dispatchData = new ArrayList<UnitDispatchData>(); 
	
	public int locationID = -1;
	//public ArrayList<Integer> locationID = new ArrayList<Integer>() ;
	
	//dispatch variables
	private int arrivalRank= -1;
	private int dispatchDelay;
	public boolean isCorrectlyAssigned= false;
	public boolean isPending = false;
	public int setPriority = 3;
	
	public int[] info = {};
	
	//recall variables
	private int recallDelay;
	public boolean isRecallPending = false;
		
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public UnitVO() {
		// TODO Auto-generated constructor stub
	}
	
	//example 54, 1, "assets/image.gif", 
	public UnitVO(int _badgeNo, int _resourceID, String _icon) {
		badgeNo = _badgeNo;
		icon = _icon;
		resourceID = _resourceID;
		
		//eventID = 0;		
		//status = "idle";
		//message = "idle";
		available = true;
	}
	
	/*public UnitDispatchData getDispatchData(int locationID){
		
			for(int j = 0; j<this.dispatchData.size(); j++){
				
					if(dispatchData.get(j).locationID==locationID){
						return dispatchData.get(j);
					}
				
			}// j loop
		
			return null;
	}// ending function*/
	
	
	/*public int getIndexDispatchData(int locationID){
		
		for(int j = 0; j<this.dispatchData.size(); j++){
			
			if(dispatchData.get(j).locationID==locationID){
				return j;
			}
		
	}// j loop

	return -2;
		
	}*/
	
	/*public boolean isLocationCovered(int locationID){
		
		for(int k = 0 ; k < this.dispatchData.size(); k++){
			
				if(dispatchData.get(k).locationID==locationID){
					return true;
				}
			
		}// ending for
		
		return false;
		
	}*/
	
	
	/****************************************************************
	 * Dispatch Functions
	 ****************************************************************/
	
	public int getArrivalRank(int locationID){
		return arrivalRank;
	}
	
	public void setArrivalRank(int rank){		
		
		this.arrivalRank = rank;
		
	}
	
	public void resetArrivalRank(){		
		this.arrivalRank = -1;
	}
	
	public int getPendingDelay(){	
		
		return this.dispatchDelay;
	
	}	
	
	public void setPendingDelay( int _pendingDelay){		
		this.dispatchDelay = _pendingDelay; 
	}
	
	public void decrementPendingDelay(){
		
		this.dispatchDelay--;
	}
	
	/****************************************************************
	 * Recall Functions
	 ****************************************************************/
	
	public int getRecallPendingDelay(){
		
		return this.recallDelay;
	
	}
	
	public void setRecallPendingDelay(int _recallDelay){
		this.recallDelay = _recallDelay;
	}
	
	public void decrementRecallPendingDelay(){
		this.recallDelay--;
	}
	
	

	
}// UnitVO