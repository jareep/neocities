package edu.psu.ist.neocities.value.OldValue;

public class UnitVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int badgeNo; //primary key
	public String icon; 
	public String status;
	public String message;
	public boolean available;
	public int resourceID; // foreign key to ResourceVO, indicates resource type
	public int incidentID; // foreign key to IncidentVO, indicates dispatch location
	
	//dispatch variables
	private int arrivalRank= -1;
	private int dispatchDelay =-1;
	public boolean isCorrectlyAssigned= false;
	public boolean isPending = false;
	
	//recall variables
	private int recallDelay = 0;
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
		
		incidentID = 0;		
		status = "idle";
		message = "idle";
		available = true;
	}
	
	/****************************************************************
	 * Dispatch Functions
	 ****************************************************************/
	
	public int getArrivalRank(){
		return arrivalRank;
	}
	
	public void setArrivalRank(int rank){		
		arrivalRank = rank;		
	}
	
	public void resetArrivalRank(){		
		arrivalRank = -1;
	}
	
	public int getPendingDelay(){	
		return this.dispatchDelay;
	}	
	
	public void setPendingDelay(int _pendingDelay){		
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
	
	public void setRecallPendingDelay( int _recallDelay){
		this.recallDelay = _recallDelay;
	}
	
	public void decrementRecallPendingDelay(){
		this.recallDelay--;
	}

	
}// UnitVO
