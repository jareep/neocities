package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.PlayerVO;
	
	import mx.collections.ArrayCollection;
		
	public class PlayerModel extends ModelFactory
	{
		public function PlayerModel()
		{
			super();
			trace("load PlayerModel");
		}
		
		[Bindable]
		public var players : ArrayCollection = new ArrayCollection;
		

		
		public function addPlayer ( player : PlayerVO ) : void
		{
			players.addItem(player);
		}
		
		public function clearModel () : void
		{
			players = new ArrayCollection;
		}
		
		public function playerRoleExists(roleID : String) : Boolean
		{
			for each (var player : PlayerVO in players)
			{
				if (player.roleID.toString() == roleID)
				{
					return true;
				} 
			}
			
			return false;
		}
		
		
		
		public function getCurrentPlayer() : PlayerVO
		{
			for each (var player : PlayerVO in players)
			{
				if (player.currentPlayer)
				{
					return player;
				} 
			}
			
			return player;
		}
	}
}