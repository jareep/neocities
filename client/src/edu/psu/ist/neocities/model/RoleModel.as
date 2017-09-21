package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.oldValue.IncidentVO;
	import edu.psu.ist.neocities.value.RoleVO;
	import edu.psu.ist.neocities.value.UnitVO;
	
	import mx.collections.ArrayCollection;
	
	public class RoleModel extends ModelFactory
	{
		public function RoleModel()
		{
			super();
			trace("load PlayerModel");	
		} 
		
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var team : ArrayCollection;
		
		
		[Bindable]
		public var currentPlayer : RoleVO; 
		
		[Bindable]
		public var chat : String = new String;
		
		
		public function isPlayer( name : String ) : Boolean {
			
			if (data != null){
				//if not null, then loop through the arrayCollection
				for (var i : int = 0; i <= data.length - 1; i++){
					if (data.getItemAt(i).userName.toLowerCase() == name.toLowerCase()){
						trace("Name Found in Player List: " + name);
						return true;						
					}
				}
				trace("Name NOT Found in Player List: " + name);
				return false;
			}
			else {
				trace("Player List is Null");
				return false;	
			}
			
		}
		
		public function assignPlayerTeam ( newPlayer : RoleVO) : void {
			var playerTeam : ArrayCollection = new ArrayCollection();
			
			if (data == null){
				trace("you screwed up somewhere, load players first");
			}
			else
			{
				for (var i : int = 0; i <= data.length - 1; i++){
					if (data.getItemAt(i).role.toLowerCase() != newPlayer.role.toLowerCase()){
						playerTeam.addItem(data.getItemAt(i));
					}							
				}
				
				currentPlayer = newPlayer;
				team = playerTeam;
			}
		}
		
		public function setPlayerLocation ( location : int ) : void {
			
			for (var i : int = 0; i <= data.length - 1; i++){
				data.getItemAt(i).locationID = location;
			}
			return void;
		}
		
		public function getPlayer( playerID : int ) : RoleVO {
			for each (var player : RoleVO in data)
			{
				if (player.roleID == playerID)
				{
					return player;
				}
			}
			
			return null;
			
			
		}
		
		public function getRole (id : int) : RoleVO
		{
			for each (var role : RoleVO in data)
			{
				if (role.roleID == id)
				{
					return role;
				}
			}
			
			return null;
		}
		
		
		
		public function checkRole ( roleCheck : String ) : Boolean {
			for each (var role : RoleVO in data)
			{
				trace("ROLE CHECK " + role.role + " " + role.userName);
				if (role.role.toLowerCase() == roleCheck.toLowerCase())
				{
					if (role.userName != "No User")
					{
						return true;
					}
				}
			}
			
			return false;
		}
		
		
		public function printAvailablePlayerUnits() : String {
			
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			var unitsAvailable : String;
			unitsAvailable = "";
			
			var availableUnits : ArrayCollection;
			
			
			for each (var unit: UnitVO in this.currentPlayer.units){
				
				var unitType : String;
				
				unitType = rModel.getResource(unit.resourceID.toString()).label;
				
				unitsAvailable = unitsAvailable + "[Badge No. = "+unit.badgeNo+"; Unit Type = "+unitType+"]\n";
				
			}// ending for
			
			return unitsAvailable;
			
		} // printA  vailableUnits..
		
		
		public function printDispatchedPlayerUnits() : String {
			
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			var unitsAvailable : String;
			unitsAvailable = "";
			
			var availableUnits : ArrayCollection;
			
			
			for each (var unit: UnitVO in this.currentPlayer.units){
				
				var unitType : String;
				
				if(!unit.available){
				
				unitType = rModel.getResource(unit.resourceID.toString()).label;
				
				unitsAvailable = unitsAvailable + "[Badge No. = "+unit.badgeNo+"; Unit Type = "+unitType+"]\n";
				
				}				
				
			}// ending for
			
			return unitsAvailable;
			
		} // printA  vailableUnits..
		
		
	}
}