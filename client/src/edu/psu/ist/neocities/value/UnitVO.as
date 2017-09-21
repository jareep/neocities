package edu.psu.ist.neocities.value
{
		
	[Bindable]
	public class UnitVO
	{				
		//shown in view
		public var badgeNo : int;		
		public var status : String;
		public var message : String;
		public var available : Boolean;
		public var isPending : Boolean;
		public var eventID : int;
		public var locationID : int;
		public var resourceID : int;
		public var icon : String;
		public var setPriority : int;
		
		
		
		public var playerMessage : String;
				
		public function UnitVO
		(			
			_badgeNo : int,
			_resourceID : int,
			_icon : String
		)
		{
			badgeNo = _badgeNo;			
			resourceID = _resourceID;
			icon = _icon;
			
			status = "idle";
			message = "in stock";
			available = true;	
			isPending = false;
			eventID = 0; //setting this variable to zero enables the numerical sorting of eventIDs
			locationID = 0;
			playerMessage = message;
			setPriority = 3;
		}
		
		public function compareTo(_unit : UnitVO) : Boolean
		{
			return (_unit.badgeNo == this.badgeNo);
		}
		
		
	}
}