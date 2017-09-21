package edu.psu.ist.neocities.value
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.model.UnitModel;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class ResourceVO
	{	
		//shown in view
		public var label : String;
		public var available : int;
		public var dispatched : int;
		public var total : int;
		
		//hidden from view
		public var resourceID : int;
		public var roleID : int;
		public var icon : String;	
		public var isAction : Boolean;
		
		
		
		public function ResourceVO
			(
				_resourceID : int,
				_roleID : int,
				_label : String,			
				_total : int,
				_icon : String,
				_isAction :Boolean
			)
		{
			resourceID = _resourceID;
			roleID = _roleID;
			label = _label;			
			total = _total;
			available = total;
			dispatched = 0;
			icon = _icon;
			isAction = _isAction;
			//assignIcon(_roleID, _label);
			
		}
		
		public function getFirstAvailable() :UnitVO
		{
			
			var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;
			var units:ArrayCollection = new ArrayCollection();
			
			units = uModel.getResourceUnits(this.resourceID);
			
			for each (var unit:UnitVO in units)
			{
				if (unit.available == true)
				{
					return unit;
				}
			}
			
			return null;
		}
		
		private function assignIcon(_roleID : int, _label : String) : void {
			
			switch (_roleID)
			{
				case 1:					
					if(_label.toLowerCase() == "investigator"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/P_InU.jpg"						
					}
					else if(_label.toLowerCase() == "squad car"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/P_Sq_Car.jpg"
					}
					else if(_label.toLowerCase() == "swat"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/P_SWAT.jpg"
					}										
					break;
				
				case 2:
					if(_label.toLowerCase() == "investigator"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/F_InU.jpg"
					}
					else if(_label.toLowerCase() == "ambulance"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/F_Amb.jpg"
					}
					else if(_label.toLowerCase() == "fire truck"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/F_Truck.jpg"
					}	
					break;
				
				case 3:
					if(_label.toLowerCase() == "investigator"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/H_InU.jpg"
					}
					else if(_label.toLowerCase() == "bomb squad"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/H_BSq.jpg"
					}
					else if(_label.toLowerCase() == "chemical truck"){
						icon = "edu/psu/ist/neocities/assets/resource_icons/H_CTruck.jpg"
					}	
					break;
			}
			
		}		
		
	}
}