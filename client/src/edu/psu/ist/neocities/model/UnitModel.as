package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.model.*;
	import edu.psu.ist.neocities.value.*;
	
	import mx.collections.ArrayCollection;
	
	public class UnitModel extends ModelFactory
	{
		/****************************************************************
		 * Variables
		 ****************************************************************/
		[Bindable]
		public var data : ArrayCollection; 
		
		public function UnitModel()
		{
			super();
			trace("load UnitModel");
		}
		
		
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
		public function getUnits(eventID : String) : ArrayCollection {
			var unitCollection : ArrayCollection = new ArrayCollection();
			//for each unit
			for (var i : int = 0; i <= data.length - 1; i++){
				//find the units that match the passed in incident
				if (data.getItemAt(i).eventID == eventID){
					var unit : UnitVO = data.getItemAt(i) as UnitVO;
					unitCollection.addItem(unit);
					//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
				}
			}
			return unitCollection;
		}
		
		
		//formely used by the Incident Popup to discover which units have been dispatched to an incident
		public function getUnitsOfPlayer(eventID : String) : ArrayCollection {
			var unitCollection : ArrayCollection = new ArrayCollection();
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;	
			
			//for each unit
			for (var i : int = 0; i <= data.length - 1; i++){
				//find the units that match the passed in incident of resources belonging to the current player
				if (data.getItemAt(i).eventID == eventID && 
					rModel.isPlayerResource(data.getItemAt(i).resourceID) ){
					var unit : UnitVO = data.getItemAt(i) as UnitVO;
					unitCollection.addItem(unit);
					//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
				}
			}
			return unitCollection;
		}
		
		//formely used by the Incident Popup to discover which units have been dispatched to an incident
		public function getUnitsofPlayer(eventID : String, status : String) : ArrayCollection {
			var unitCollection : ArrayCollection = new ArrayCollection();
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			//for each unit
			for (var i : int = 0; i <= data.length - 1; i++){
				//find the units that match the passed in incident
				if (data.getItemAt(i).eventID == eventID && 
					rModel.isPlayerResource(data.getItemAt(i).eventID) &&
					data.getItemAt(i).status.toLowerCase() == status.toLowerCase() ) 
				{
					var unit : UnitVO = data.getItemAt(i) as UnitVO;
					unitCollection.addItem(unit);
					//trace("unit found: Badge = " + unit.badgeNo + " incidentID = " + unit.incidentID); 
				}
			}
			return unitCollection;
		}
		
		public function getResourceUnits(resourceID : int) : ArrayCollection {
			
			var unitCollection :ArrayCollection = new ArrayCollection();
			
			for(var i : int = 0; i<data.length; i++){
				
				if(data.getItemAt(i).resourceID==resourceID){
					
					var unit : UnitVO = data.getItemAt(i) as UnitVO;
					unitCollection.addItem(unit);
					
				}// ending if
				
			}// ending i loop
			
			return unitCollection;
			
		}//getResourceUnits()
		
		
		//used by the Decision.Dispatch View
		public function getAvailableUnits() : ArrayCollection {
			var unitCollection : ArrayCollection = new ArrayCollection();
			//for each unit
			for (var i : int = 0; i <= data.length - 1; i++){
				//find the units that match the passed in incident
				if (data.getItemAt(i).available == true){
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
		
		public function getAllDispatchedUnits() : ArrayCollection {
			var unitCollection : ArrayCollection = new ArrayCollection();
			//for each unit
			for (var i : int = 0; i <= data.length - 1; i++){
				//find the units that match the passed in incident
				if (data.getItemAt(i).available == false){
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
		public function updateUnit(badgeNo : uint, eventID : int, locationID : int, newStatus : String, newMessage : String, available : Boolean) : void {
			
			//changing incidentID's to eventID's
			
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			//	var iModel : IncidentModel = ModelFactory.getModel("IncidentModel") as IncidentModel;
			//var eModel : EventModel = ModelFactory.getModel("EventModel") as EventModel;
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			var canViewResource : Boolean = false;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).badgeNo == badgeNo) {
					//updates the resource count based upon comparing the old availbility with the new availability
					rModel.updateResource(data.getItemAt(i).resourceID, data.getItemAt(i).available, available); 
					
					data.getItemAt(i).locationID = locationID;
					data.getItemAt(i).eventID = eventID;
					data.getItemAt(i).status = newStatus;
					data.getItemAt(i).message = newMessage;
					data.getItemAt(i).available = available;
					
					//eModel.setEventStatus(eventID, newStatus);
					
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
		
		public function printAvailableUnits() : String {
			
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			var unitsAvailable : String;
			unitsAvailable = "";
			
			var availableUnits : ArrayCollection;
			availableUnits = this.getAvailableUnits();
			
			for each (var unit: UnitVO in availableUnits){
				
				var unitType : String;
				
				for each (var res: ResourceVO in rModel.data){
					
					if(res.resourceID==unit.resourceID)
					{unitType = res.label; break;}
					
				}
				
				//unitsAvailable = unitsAvailable + unit.badgeNo;
				
				unitsAvailable = unitsAvailable + "[Badge No. = "+unit.badgeNo+"; Unit Type = "+unitType+"]\n";
			
			}// ending for
			
			return unitsAvailable;
			
		} // printA  vailableUnits..
		
		
		
		public function printDispatchedUnits() : String {
			
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			var dispatchedUnits : String;
			dispatchedUnits = "";
			
			var units : ArrayCollection;
			units = this.getAllDispatchedUnits();
			
			
			
			for each (var unit: UnitVO in units){
				
				var unitType : String;
				
				for each (var res: ResourceVO in rModel.data){
					
					if(res.resourceID==unit.resourceID)
					{unitType = res.label; break;}
						
				}
				
				dispatchedUnits = dispatchedUnits + "[Badge No. = "+unit.badgeNo+"; Unit Type = "+unitType+"]\n";
				
			}// ending for
			
			return dispatchedUnits;
			
		} // printDispatchedUnits..
		
		
		
	}// closing class
}//ending package