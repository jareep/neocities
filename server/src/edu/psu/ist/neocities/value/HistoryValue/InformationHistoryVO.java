package edu.psu.ist.neocities.value.HistoryValue;

public class InformationHistoryVO {

	public int roleID;				// the role id of the person performing the action
	public int informationID;		// the id of the information vo that the action was performed on
	public int locationID;			// the location of the information
	public int recipientID;			// if the action was a transfer, who it was sent to
	
	public String infoTime;			// the time the information was dispatched
	public String actionTime;		// the time the action was taken on the information
	public String reactionTime;		// the reaction time
	
	public String action;			// the action that has been taken on the information
	public Boolean returned;		// true if the information was returned;
	
	public Boolean correctAction;	// if transfered to correct Role, or deleted dud/finished event information
	
	public InformationHistoryVO() {
		
	}

	public InformationHistoryVO(int roleID, int informationID, int locationID,
			int recipientID, int infoTime, int actionTime,
			 String action, Boolean returned, Boolean correctAction) {
		super();
		this.roleID = roleID;
		this.informationID = informationID;
		this.locationID = locationID;
		this.recipientID = recipientID;
		this.infoTime = Integer.toString(infoTime);
		this.actionTime = Integer.toString(actionTime);
		this.reactionTime = Integer.toString(actionTime-infoTime);
		this.action = action;
		this.returned = returned;
		this.correctAction = correctAction;
	}
	
	

}
