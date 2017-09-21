package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.*;
	import edu.psu.ist.neocities.value.*;
	
	import mx.collections.ArrayCollection;
	
	public class ResourceModel extends ModelFactory
	{
		
		[Bindable]
		public var data : ArrayCollection; 
		
		public function ResourceModel()
		{
			super();
			trace("Loading ResourceModel");
		}
		
		
		
		/****************************************************************
		 * Resource Icons
		 * 	                
		 * These variables embed the resource icons for the available  	                
		 * resource units
		 *
		 ****************************************************************/
		
		/*
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_InU.jpg")]
		private var p_investigator : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_Sq_Car.jpg")]
		private var p_squadcar : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_SWAT.jpg")]
		private var p_swat : Class;	
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_InU.jpg")]
		private var f_investigator : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_Amb.jpg")]
		private var f_ambulance : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_Truck.jpg")]
		private var f_firetruck : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_InU.jpg")]
		private var h_investigator : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_BSq.jpg")]
		private var h_bombsquad : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_CTruck.jpg")]
		private var h_chemicaltruck : Class;
		
		/****************************************************************
		 * Resource Icons (Faded)
		 * 	                
		 * These variables embed the resource icons for the dispatched  	                
		 * resource units, same as the icons above but faded, and grayed out
		 *
		 ****************************************************************/
		/*
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_InU_gray.jpg")]
		private var p_investigator_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_Sq_Car_gray.jpg")]
		private var p_squadcar_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/P_SWAT_gray.jpg")]
		private var p_swat_gray : Class;	
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_InU_gray.jpg")]
		private var f_investigator_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_Amb_gray.jpg")]
		private var f_ambulance_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/F_Truck_gray.jpg")]
		private var f_firetruck_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_InU_gray.jpg")]
		private var h_investigator_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_BSq_gray.jpg")]
		private var h_bombsquad_gray : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/resource_icons/H_CTruck_gray.jpg")]
		private var h_chemicaltruck_gray : Class;*/
		
		/****************************************************************
		 * Functions
		 ****************************************************************/
		
		public function getIcon ( resourceID : String, available : Boolean ) : Class {
			var iconClass : Class
			/*
			switch ( resourceID )
			{
				case "1":
					if(available) {
						iconClass = p_investigator;
					} else {
						iconClass = p_investigator_gray;
					}
					break;
				case "2":
					if(available) {
						iconClass = p_squadcar;
					} else {
						iconClass = p_squadcar_gray;
					}
					break;
				case "3":
					if(available) {
						iconClass = p_swat;
					} else {
						iconClass = p_swat_gray;
					}
					break;
				case "4":
					if(available) {
						iconClass = f_investigator;
					} else {
						iconClass = f_investigator_gray;
					}
					break;
				case "5":
					if(available) {
						iconClass = f_ambulance;
					} else {
						iconClass = f_ambulance_gray;
					}
					break;
				case "6":
					if(available) {
						iconClass = f_firetruck;
					} else {
						iconClass = f_firetruck_gray;
					}
					break
				case "7":
					if(available) {
						iconClass = h_investigator;
					} else {
						iconClass = h_investigator_gray;
					}
					break;
				case "8":
					if(available) {
						iconClass = h_bombsquad;
					} else {
						iconClass = h_bombsquad_gray;
					}
					break;
				case "9":
					if(available) {
						iconClass = h_chemicaltruck;
					} else {
						iconClass = h_chemicaltruck_gray;
					}
					break
				default:
					if(available) {
						iconClass = p_investigator;
					} else {
						iconClass = p_investigator_gray;
					}
			}*/
			return iconClass;
			
		}
		
		public function getResource (ID : String) : ResourceVO {
			var resource : ResourceVO;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == ID){
					resource = data.getItemAt(i) as ResourceVO;
					//trace ('resource found: ID = ' + resource.resourceID + ' Label = ' + resource.label);
					break;
				} else {
					resource = new ResourceVO (99, 1, "FAIL", 10, "XXXX", false);	
				}											
			}
			return resource;
		}
		
		public function getResourceLabel ( resourceID : String ) : String {
			var label : String = "";
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID){
					label = data.getItemAt(i).label;
					break;
				} 										
			}
			return label;
		}
		
		
		//used by the Team Panel to determine the total number of units available for a given resource
		public function getResourceTotal ( resourceID : String ) : int {
			var total : int = 10;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID){		
					total = data.getItemAt(i).total;
				}
			}
			
			return total;
		}
		
		//used by the Team Panel to determine the number of units available for a given resource
		public function getResourceAvailable ( resourceID : String ) : int {
			var available : int = 10;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID){		
					available = data.getItemAt(i).available;
				}
			}
			
			return available;
		}
		
		//used by SC to determine if the resource passed in by the server already exists or not
		public function isResource ( resourceID : int ) : Boolean {
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID){		
					return true;
				}
			}
			return false;
		}
		
		//used by SC to update the resource totals for a given resource (matching the rID)
		public function updateResource (resourceID : String, oldAvailable : Boolean, newAvailable : Boolean) : void {
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID){
					trace("Before - " + data.getItemAt(i).label + " : " + data.getItemAt(i).available);				
					
					if(!oldAvailable && newAvailable){
						data.getItemAt(i).available++; //unit was not available and now has returned
					} 
					else if (oldAvailable && !newAvailable) {
						data.getItemAt(i).available--; //unit was available and is now dispatched
					}
					trace("After - " + data.getItemAt(i).label + " : " + data.getItemAt(i).available);
					data.getItemAt(i).dispatched = data.getItemAt(i).total - data.getItemAt(i).available;
					break;
				}
			}
			return void;
			
		}
		
		//used by unitModel to determine if the unit belongs to the current player's role
		public function isPlayerResource ( resourceID : String ) : Boolean {
			var playerResource : Boolean = false; //assume false
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			if (pModel.currentPlayer.roleID == 0)
			{
				return true;
			}
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).resourceID == resourceID && pModel.currentPlayer.roleID == data.getItemAt(i).roleID){
					playerResource = true;
					break;
				}
			}
			
			return playerResource;		
		}
		
		
	}// end of class
}// end of package