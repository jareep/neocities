package edu.psu.ist.neocities.value.HistoryValue;

/**
 * @author bhellar
 * @date 7-1-09
 * @description ActionHistoryVO stores the history of user actions (dispatch and recalls) for the simulation
 * 				This class has a Remote Class in AS. 
 *
 */
public class ActionHistoryVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int eventID; //foreign key to incidentModel	
	public int roleID; //foreign key to roleModel
	public int resourceID; //foreign key to resourceModel
	public int badgeNo; //foreign key to unitModel
	public int numDispatch; // number of total dispatches to the event in question
	public int numRoleDispatch; // number of total dispatches to the event by this role
	public String action = "Dispatch"; // should be either Recall or Dispatch
	
	public String eventTime; // time when the event was started
	public String actionTime; // time when the action occurred
	public String actionTimeSeconds; // action time in seconds
	public String reactionTime; // difference of (actionTime - event dispatch time)
//	public String secondsLate = "-9999"; // number of seconds they are late for the dispatch.

	
	public Boolean correct;     // whether the unit was correct or not
	
	public int numActiveEvents;
	public int numDispatchedUnits; // total number of dispatched units for the particular player
	
	public int setPriority;
	public int locationID; //location to which the specific action/unit was applied
	public boolean isAction = true;
	public double currentMagnitude = 0.0;
	public double initialSeverity = 0.0;
	
	public int attempt = -2;
	
	public String eligible = "";
	
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public ActionHistoryVO() {
			
	}
	
	
	public ActionHistoryVO(boolean isAction, int locationID, int setPriority, int attempt, int eventID, int roleID, int resourceID, String actionString, String eventTime, String actionTime, String reactionTime){
		this.eventID = eventID;
		this.roleID = roleID;
		this.resourceID = resourceID;
		this.action = actionString;
		this.eventTime = eventTime;			
		this.actionTime = actionTime;
		this.reactionTime = reactionTime;
		
		
		this.isAction = isAction;
		this.locationID = locationID;
		this.setPriority = setPriority;
		this.attempt = attempt;
	}
	
	
	public ActionHistoryVO(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, String reactionTime){
		this.eventID = eventID;
		this.roleID = roleID;
		this.resourceID = resourceID;
		this.badgeNo = badgeNo;
		this.action = actionString;
		this.eventTime = eventTime;			
		this.actionTime = actionTime;
		this.reactionTime = reactionTime;
	}
	
   public ActionHistoryVO(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, String reactionTime, Boolean correct){
       this.eventID = eventID;
        this.roleID = roleID;
        this.resourceID = resourceID;
        this.badgeNo = badgeNo;
        this.action = actionString;
        this.eventTime = eventTime;         
        this.actionTime = actionTime;
        this.reactionTime = reactionTime;
        this.correct = correct;
      
    }
   
   public ActionHistoryVO(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, String reactionTime, Boolean correct, int numDispatchedUnits){
       this.eventID = eventID;
       this.roleID = roleID;
       this.resourceID = resourceID;
       this.badgeNo = badgeNo;
       this.action = actionString;
       this.eventTime = eventTime;         
       this.actionTime = actionTime;
       this.reactionTime = reactionTime;
       this.correct = correct;
       this.numDispatchedUnits = numDispatchedUnits;
   }
   
   public ActionHistoryVO(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, 
           String reactionTime, Boolean correct, int numDispatchedUnits, int numDispatched, int numRoleDispatch){
       this.eventID = eventID;
       this.roleID = roleID;
       this.resourceID = resourceID;
       this.badgeNo = badgeNo;
       this.action = actionString;
       this.eventTime = eventTime;         
       this.actionTime = actionTime;
       this.reactionTime = reactionTime;
       this.correct = correct;
       this.numDispatchedUnits = numDispatchedUnits;
       this.numDispatch = numDispatched;
       this.numRoleDispatch = numRoleDispatch;
   }
   
   public ActionHistoryVO(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, 
           String reactionTime, Boolean correct, int numDispatchedUnits, int numDispatched, int numRoleDispatch, int timeLate){
       this.eventID = eventID;
       this.roleID = roleID;
       this.resourceID = resourceID;
       this.badgeNo = badgeNo;
       this.action = actionString;
       this.eventTime = eventTime;         
       this.actionTime = actionTime;
       this.reactionTime = reactionTime;
       this.correct = correct;
       this.numDispatchedUnits = numDispatchedUnits;
       this.numDispatch = numDispatched;
       this.numRoleDispatch = numRoleDispatch;
       //this.secondsLate = Integer.toString(timeLate);
   }

}
