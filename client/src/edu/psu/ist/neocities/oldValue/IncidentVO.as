package edu.psu.ist.neocities.oldValue
{
	import mx.collections.ArrayCollection;
			
	[Bindable]
	public class IncidentVO
	{
		public var incidentID : String;
		public var label : String;
		public var description : String;
		public var descriptions : ArrayCollection = new ArrayCollection;
		public var status : String;		
		public var icon : String;
		public var severity : Number;
		
		public var lat : String;
		public var long : String;
		public var feedback : String;									
		public var role : String;
		public var dispatchTime : String;
		public var completionTime : String;
				
		public function IncidentVO
		( 
			_ID : String,
			_icon : String,
			_label : String,
			_status : String,
			_description : String,
			_severity : Number,
			_lat : String,
			_long : String
			
		)
		{
			incidentID = _ID;
			icon = _icon;
			label = _label;
			status = _status;
			description = _description;
			severity = _severity;			
			
			/*lat = _lat;
			long = _long;*/
			
			//until scenarios represent them it just generates a random area in state college area... 
			lat = (Math.random() * (40.801968 - 40.786438) + 40.786438).toString(); 
			long = (Math.random() * (-77.849722+77.870922) - 77.870922).toString();
			
			
			feedback = "N / A"
			
		}
		
		public function getDescription(roleID : int) : String
		{
			var description : String = "";
			
			for each (var des : DescriptionVO in this.descriptions)
			{
				if (des.permission == 0 || des.permission == roleID)
				{
					if (des.description != null)
					{
						description+= des.description + " ";
					}
				}
			}
			
			return description;
		}					

	}
}