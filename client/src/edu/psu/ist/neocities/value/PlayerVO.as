package edu.psu.ist.neocities.value
{
	// Update this class when you make significant updates to the code
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.PlayerVO")]
	public class PlayerVO
	{				
		public var playerID : int;
		public var roleID : int;
		public var userName : String;
		public var currentFlexVersion : String = "4.0-Alpha";
		public var selectedIncidentID : int = 0;
		public var currentPlayer : Boolean
		
		public function	PlayerVO()
		{
			super();
		}	
	}
}