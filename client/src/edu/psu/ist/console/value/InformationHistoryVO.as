package edu.psu.ist.console.value
{
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.HistoryValue.InformationHistoryVO")]
	public class InformationHistoryVO
	{
		
		public var roleID :int;				// the role id of the person performing the action
		public var informationID : int;		// the id of the information vo that the action was performed on
		public var locationID : int;			// the location of the information
		public var recipientID : int;			// if the action was a transfer, who it was sent to
		
		public var infoTime : String;			// the time the information was dispatched
		public var actionTime : String;		// the time the action was taken on the information
		public var reactionTime : String;		// the reaction time
		
		public var action : String;			// the action that has been taken on the information
		public var returned : Boolean;		// true if the information was returned;
		
		public var correctAction : Boolean;	// if transfered to correct Role, or deleted dud/finished event information
		
		public function InformationHistoryVO()
		{
		}
	}
}