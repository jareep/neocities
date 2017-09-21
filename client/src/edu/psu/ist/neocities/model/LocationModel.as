package edu.psu.ist.neocities.model
{
	
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.*;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	
	public class LocationModel extends ModelFactory
	{
		
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var selectedLocation : LocationVO;				// the location the user is currently working on
		
		public function LocationModel()
		{
			super();
			selectedLocation = new LocationVO(-1, "No Location Selected","",null,"",null);
			trace("load LocationModel");
		}
		
		public function getLocation(id : int) : LocationVO
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					return l;
				}
			}
			
			return null;
		}
		
		
		
		public function addLocation(local : LocationVO) : void
		{
			if (data == null)
			{
				data = new ArrayCollection();
			}
			
			data.addItem(local);
		}
		
		public function addLocations(locations : ArrayCollection) : void
		{
			for each (var l :LocationVO in locations)
			{
				this.addLocation(l);
			}
		}
		
		public function addLocationFeedback(id : int, feedback : String) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.addFeedBack(feedback);
				}
			}
		}
		
		public function setLocationEvents (id : int, events : int) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.activeEvents = events;
				}
			}
		}
		
		public function setLocationSeverity (id : int, avgSeverity :Number) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.avgSeverity = avgSeverity;
				}
			}
		}
		
		public function setLocationFeedback (id : int, feedback : ArrayCollection) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.feedback = feedback;
				}
			}
		}
		
		public function addEventToLocation(id : int) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.addEvent();
				}
			}
		}
		
		public function removeEventFromLocation(id : int) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.removeEvent();
				}
			}
		}
		
		public function addInfoToLocation(id : int) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.addInformation();
				}
			}
		}
		
		public function removeInfoFromLocation(id : int) : void
		{
			for each (var l : LocationVO in data)
			{
				if (l.id == id)
				{
					l.removeInformation();
				}
			}
		}
		
		public function isLocation(id : int) : Boolean
		{
				for each (var l : LocationVO in data)
				{
					if (l != null)
					{
						if (l.id == id)
						{
							return true;
						}
					}
				}
			
			return false;
		}
		
		public function resetModel() : void
		{
			this.data = new ArrayCollection();
			this.selectedLocation = new LocationVO(-1, "","",null,"",null);
		}
		
		public function printLocations() : String{
			
			var locationString : String  = "";
			
			for each (var location : LocationVO in this.data){
				
				locationString = location.name + locationString;
				locationString += location.id + " " + location.name + " " + location.activeEvents.toString() + " \n";
				
			} // ending for
			
			return locationString; 
			
		}// printLocations()
		
	}
}