package edu.psu.ist.neocities.value
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class RoleVO
	{		
		
		/****************************************************************
		 * Variables
		 ****************************************************************/
		public var userName : String = "No User";
		public var roleID : int;
		public var role : String;
		public var icon : String; 
		public var banner : String;		
		public var incidentID : int;
		public var resources : ArrayCollection; //of type ResourceVO
		public var units : ArrayCollection; //of type UnitVO available
		public var dispatchedUnits : ArrayCollection; //of type UnitVO
		
		
		// might not use
		public var totalUnits : ArrayCollection; // of type UnitVO
		public var pendingUnits : ArrayCollection // of type UnitVO
		
		/****************************************************************
		 * Constructor
		 ****************************************************************/
		public function RoleVO ( _roleID : int, _role : String, _icon : String	)
		{
			roleID = _roleID;
			role = _role;
			icon = _icon;
			
			
			resources = new ArrayCollection();
			units = new ArrayCollection();
			
			totalUnits = new ArrayCollection();
			pendingUnits = new ArrayCollection();
			
			dispatchedUnits = new ArrayCollection();			
		}
		
		
		/****************************************************************
		 * Functions
		 ****************************************************************/		
		//updates the playerUnit AC... contains the up-to-date information on all of the player's units
		public function updatePlayerUnit ( unit : UnitVO ) : void {
			
			for (var i : int = 0; i <= units.length - 1; i++){
				
				if( units.getItemAt(i).badgeNo == unit.badgeNo ) {
					units.getItemAt(i).locationID = unit.locationID,
						units.getItemAt(i).eventID = unit.eventID,
						units.getItemAt(i).status = unit.status,
						units.getItemAt(i).message = unit.message,
						units.getItemAt(i).playerMessage = unit.playerMessage,
						units.getItemAt(i).available = unit.available
				}//end of if			
			}//end of loop
			
			return void;		
		}//end of function
		
		//checks the DispatchedUnit AC to see if Unit matching the BadgeNo exists
		public function isDispatchedUnit ( badgeNo : uint ) : Boolean {
			var isUnit : Boolean = false;
			
			for (var i : int = 0; i <= dispatchedUnits.length - 1; i++){				
				if( dispatchedUnits.getItemAt(i).badgeNo == badgeNo ) {
					isUnit = true;
					break;		
				}//end of if			
			}//end of loop
			
			return isUnit;		
		}//end of function
		
		//updates the dispatchedUnit AC... contains the up-to-date information on all of the player's dispatched units
		public function updateDispatchedUnit ( unit : UnitVO ) : void {
			
			for (var i : int = 0; i <= dispatchedUnits.length - 1; i++){
				
				if( dispatchedUnits.getItemAt(i).badgeNo == unit.badgeNo ) {
					dispatchedUnits.getItemAt(i).locationID = unit.locationID,
						dispatchedUnits.getItemAt(i).eventID = unit.eventID,
						dispatchedUnits.getItemAt(i).status = unit.status,
						dispatchedUnits.getItemAt(i).message = unit.message,
						dispatchedUnits.getItemAt(i).playerMessage = unit.playerMessage,
						dispatchedUnits.getItemAt(i).available = unit.available
				}//end of if			
			}//end of loop
			
			return void;		
		}//end of function
		
		//removes the passed in badgeNO from the Dispatched Unit AC
		public function removeDispatchedUnit (  badgeNo : uint ) : void {
			
			for (var i : int = 0; i <= dispatchedUnits.length - 1; i++){
				
				if( dispatchedUnits.getItemAt(i).badgeNo == badgeNo ) {
					dispatchedUnits.removeItemAt(i);
					break;
				}//end of if			
			}//end of loop
			
			return void;		
		}//end of function				
		
	}
}