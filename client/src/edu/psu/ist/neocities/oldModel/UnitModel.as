package edu.psu.ist.neocities.oldModel
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.UnitVO;
	
	import mx.collections.ArrayCollection;
	import edu.psu.ist.neocities.model.RoleModel;

	public class UnitModel extends ModelFactory
	{
		public function UnitModel()
		{
			super();
		}
		
		
		/****************************************************************
	     * Variables
	     ****************************************************************/
		[Bindable]
		public var data : ArrayCollection; 
		
		
		/****************************************************************
	     * Data Access Functions
	     ****************************************************************/
		
		public function getUnit( badgeNo : uint ) : UnitVO {
			var unit : UnitVO = new UnitVO(0, 0, "ignore.gif");
			
        	for (var i : int = 0; i <= data.length - 1; i++){
        		if (data.getItemAt(i).badgeNo == badgeNo){
        			unit = data.getItemAt(i) as UnitVO; 
        			break;
        		}
        	}
        	return unit;
		}
		
		
		
		//formely used by the Incident Popup to discover which units have been dispatched to an incident
		public function getUnits(incidentID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).incidentID == incidentID){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
        		}
        	}
        	return unitCollection;
        }
        
        //formely used by the Incident Popup to discover which units have been dispatched to an incident
		public function getUnitsOfPlayer(incidentID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;	
        	
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident of resources belonging to the current player
        		if (data.getItemAt(i).incidentID == incidentID && 
        			rModel.isPlayerResource(data.getItemAt(i).resourceID) ){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
        		}
        	}
        	return unitCollection;
        }
        
        //formely used by the Incident Popup to discover which units have been dispatched to an incident
		public function getUnitsofPlayer(incidentID : String, status : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
        	
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).incidentID == incidentID && 
        			rModel.isPlayerResource(data.getItemAt(i).resourceID) &&
        		    data.getItemAt(i).status.toLowerCase() == status.toLowerCase() ) 
	        		{
	        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
	        			unitCollection.addItem(unit);
	        			//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
	        		}
        	}
        	return unitCollection;
        }
        
        //used by the Resource Tracker Display to display units of a specific resource
        public function getResourceUnits(resourceID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).resourceID == resourceID){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " resourceID = " + unit.resourceID); 
        		}
        	}
        	return unitCollection;
        }
        
        //used by the Decision.Dispatch View
        public function getAvailableUnits(resourceID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).resourceID == resourceID
        		    && data.getItemAt(i).available == true){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " resourceID = " + unit.resourceID); 
        		}
        	}
        	return unitCollection;
        }
        
       public function getDispatchableUnits(resourceID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).resourceID == resourceID
        		    && data.getItemAt(i).available == true && data.getItemAt(i).isPending == false){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " resourceID = " + unit.resourceID); 
        		}
        	}
        	return unitCollection;
        }
        
        
        
        
        //used by the Decision.Recall View
        public function getDispatchedUnits(resourceID : String) : ArrayCollection {
        	var unitCollection : ArrayCollection = new ArrayCollection();
        	//for each unit
        	for (var i : int = 0; i <= data.length - 1; i++){
        		//find the units that match the passed in incident
        		if (data.getItemAt(i).resourceID == resourceID
        		    && data.getItemAt(i).available == false){
        			var unit : UnitVO = data.getItemAt(i) as UnitVO;
        			unitCollection.addItem(unit);
        			//trace("unit found: Badge = " + unit.badgeNo + " resourceID = " + unit.resourceID); 
        		}
        	}
        	return unitCollection;
        }		
        
        public function resetUnits() : void
        {
        	for each (var a : UnitVO in data)
        	{
        		a.isPending = false;
        	}
        }
		
		
		/****************************************************************
	     * Controller Functions
	     ****************************************************************/
		//used by the SC to determine if the serverUnit already exists
		public function isUnit ( badgeNo : String ) : Boolean {
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).badgeNo == badgeNo){		
					return true;
				}
			}
			return false;
		}
		
		public function isUnitOfPlayer ( badgeNo : String) : Boolean
		{
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			var isUnitOfPlayer : Boolean = false;
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).badgeNo == badgeNo){		
					isUnitOfPlayer = (rModel.isPlayerResource(data.getItemAt(i).resourceID));
				}
			}
			
			return isUnitOfPlayer;
		}
		
		//used by the SC to update a Unit, changing its properties
		public function updateUnit(badgeNo : uint, incidentID : String, newStatus : String, newMessage : String, available : Boolean) : void {
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			var iModel : IncidentModel = ModelFactory.getModel("IncidentModel") as IncidentModel;
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			var canViewResource : Boolean = false;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).badgeNo == badgeNo) {
				    //updates the resource count based upon comparing the old availbility with the new availability
				    rModel.updateResource(data.getItemAt(i).resourceID, data.getItemAt(i).available, available); 
				    
				    data.getItemAt(i).incidentID = incidentID;
					data.getItemAt(i).status = newStatus;
					data.getItemAt(i).message = newMessage;
					data.getItemAt(i).available = available;
					
					iModel.setIncidentStatus(incidentID, newStatus);
					
					if (rModel.isPlayerResource(data.getItemAt(i).resourceID) ) {
						data.getItemAt(i).playerMessage = data.getItemAt(i).message;	
						manageDispatchedUnits(data.getItemAt(i) as UnitVO);												
					}
					else {
						data.getItemAt(i).playerMessage = "You are not authorized to view this report";
					}
					
					pModel.currentPlayer.updatePlayerUnit(data.getItemAt(i) as UnitVO);					
									
					//trace("         -  badgeNo:" + data.getItemAt(i).badgeNo + " function:" + badgeNo);
					//trace("         -  incidentID:" + data.getItemAt(i).incidentID + " function:" + incidentID);
					//trace("         -  available:" + data.getItemAt(i).available + " function:" + available);
					break;					
				}
			}
			
			return void;			
		}
		
		public function manageDispatchedUnits( unit : UnitVO ) : void {
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			if (pModel.currentPlayer.isDispatchedUnit(unit.badgeNo) && !unit.available){
				pModel.currentPlayer.updateDispatchedUnit(unit);						
			}
			else if (pModel.currentPlayer.isDispatchedUnit(unit.badgeNo) && unit.available) {
				pModel.currentPlayer.removeDispatchedUnit(unit.badgeNo);
			}
			else if (!pModel.currentPlayer.isDispatchedUnit(unit.badgeNo) && !unit.available) {
				pModel.currentPlayer.dispatchedUnits.addItem(unit);
			}
			else {
				trace(" The Unthinkable has happened ! ");
			}	
			
		}
						
	
						
	}//end of class
}