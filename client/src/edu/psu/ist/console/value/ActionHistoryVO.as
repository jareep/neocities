package edu.psu.ist.console.value
{	
			
	[Bindable]	
	public class ActionHistoryVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
		 
		public var eventID : int; //foreign key to incidentModel	
		public var roleID : int; //foreign key to roleModel
		public var resourceID : int; //foreign key to resourceModel
		public var badgeNo : int; //foreign key to unitModel
		public var numDispatch : int = 0; // number of total dispatches to the event in question
		public var numRoleDispatch : int = 0; // number of total dispatches to the event by this role
		public var action : String; // should be either Recall or Dispatch
		
		public var eventTime : String; // time when the event was started
		public var actionTime : String; // time when the action occurred
		public var reactionTime : String; // difference of actionTime - eventTime
	//	public var secondsLate : String;
			
		public var correct : Boolean; // whether the resource is correct
		
		public var numActiveEvents : int;	// number of active events at the time of the action		
		public var numDispatchedUnits : int; // number of units the user currently had dispatched

		public var setPriority : int = 3;
	    public var locationID : int;
		public var isAction : Boolean = true;

		public var attempt : int; // First, Second, Third etc..
		
		public var currentMagnitude : Number; // The magnitude at action-time 
		public var initialSeverity : Number; 
		
		public var eligible : String; 
		
		
		
	/*	public var totalUtterances : int = 0;	// number of utterances at the time of the action
		public var totalCharacters : int = 0;	// total characters at the time of the action
		public var totalWords : int = 0;		// total words at the tmie of the action
		
		public var roleUtterances : int = 0;	// number of utterances by that player at the time of the action
		public var roleCharacters : int = 0;	// number of characters by that player at the time of the action
		public var roleWords : int = 0;			// number of words by that player at the time of the action
	*/	
		
		public var prevGood : int; //previous number of Good Resources, prior to action
		public var currentGood : int; // current number of Good Resources, after the action
		
		
		
	   /****************************************************************
		 * Constructors
		 ****************************************************************/
		public function ActionHistoryVO() {
				
		}
		


	}
}