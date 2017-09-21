package edu.psu.ist.neocities.value
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.Interfaces.InfoInterface;
	
	[Bindable]
	public class InformationVO
	{
		
		/*****JAVA VARS*****/
		public var id : int;
		public var type : String;
		public var roleID : int;
		public var data : Object;
		
		public var eventID: int;
		public var locationID : int;
		
		public var selected :Boolean;
		
		public function InformationVO(
			_id : int,
			_type : String,
			_roleID : int,
			_data : Object
		)
		{
			this.id = _id;
			this.type = _type;
			this.roleID = _roleID;
			this.data = _data;
			this.eventID = -1;
			this.locationID = -1;
			this.selected = false;
		}
		
		public function setEventID(_eventID: int): void{
			
			this.eventID = _eventID;
			
		}
		
		public function setLocationID(_locationID : int) : void{
			this.locationID = _locationID;
		}
		
		public function getEventID() : int{
			
			return this.eventID;
			
		}
		
		
	}// class InformationVO
}