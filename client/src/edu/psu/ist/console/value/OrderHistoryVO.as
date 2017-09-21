package edu.psu.ist.console.value
{	
			
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.OrderHistoryVO")]
	public class OrderHistoryVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
		
		public var incidentID : int; //foreign key to incidentModel	
		
		public var hardOrder : Boolean;
		public var hardRoleOrder : Boolean;
		
		public var numCorrectUnits : int;
		public var numCorrectOrderUnits : int;
		public var numCorrectRoleOrderUnits : int;
		public var totalActions : int; 
		
		// Role Specific sequencing variables:
				
		
		public var numRequiredUnits : int;
		public var numTimelyArrival : int;
		
	   /****************************************************************
		 * Constructors
		 ****************************************************************/
		public function OrderHistoryVO() {
				
		}
		


	}
}// ActionScript file
