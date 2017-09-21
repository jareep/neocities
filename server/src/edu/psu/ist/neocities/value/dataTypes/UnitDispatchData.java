package edu.psu.ist.neocities.value.dataTypes;

public class UnitDispatchData{
	
	public String status = "";
	public String message = "";
	public int eventID = -1; // foreign key
	public int locationID = -1;
	public int arrivalRank= -1;
	public int dispatchDelay;
	public boolean isCorrectlyAssigned= false;
	public boolean isPending = false;
	public int setPriority = 3;
	public int recallDelay;
	public boolean isRecallPending = false;
	
	
	public UnitDispatchData(){
		
	
	}
	
	public UnitDispatchData(int _locationID){
		
		locationID = _locationID;
	}
	
	

	
} // unitDispatchData

