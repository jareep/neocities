package edu.psu.ist.neocities.value
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.Interfaces.LocationInterface;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class LocationVO
	{
		/******JAVA VARS**********/
		public var id : int;
		public var name : String;
		public var type : String;
		public var data : Object;
		public var connections : ArrayCollection;
		public var feedback : ArrayCollection;
		public var activeEvents : int;
		public var numInformation : int;
		public var avgSeverity :Number;
		public var locationImage : String;
		
		public var label : String;
		
		
		public function LocationVO(
			_id : int,
			_name : String,
			_type : String,
			_data : Object,
			_locationImage : String,
			_connections : ArrayCollection
		)
		{
			
			this.id = _id;
			this.name = _name;
			this.type = _type;
			this.data = _data;
			this.locationImage = _locationImage;
			this.connections = _connections;
			this.feedback = new ArrayCollection();
			//this.feedback.addItem(this.name + " Has been loaded");
			this.activeEvents = 0;
			this.numInformation = 0;
			this.avgSeverity = 0.0;
		}
		
		public function addFeedBack(feedback : String) : void
		{
			this.feedback.addItem(feedback);
		}
		
		public function addEvent() : void
		{
			this.activeEvents++;
		}
		
		public function removeEvent() : void
		{
			if (this.activeEvents > 0) { this.activeEvents--; }
		}
		
		public function addInformation() : void
		{
			this.numInformation++;
		}
		
		public function removeInformation() : void
		{
			if (this.numInformation > 0) { this.numInformation--; }
		}
		
		public function getIcon():String
		{
			return this.locationImage;
		}
	}
}