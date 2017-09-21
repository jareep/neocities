package edu.psu.ist.neocities.model;
import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description stores in a list the PlayerVO Data
 */
public final class PlayerModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static PlayerModel instance = new PlayerModel();
	
	private PlayerModel() {
		// Required for Singleton Design Pattern
	}
			
	public static PlayerModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<PlayerVO> data = new ArrayList<PlayerVO>();
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public void updatePlayerIncident(int incidentID, ArrayList<UnitVO> unitArray){
		
		EnvironmentModel eModel = EnvironmentModel.instance();
		ResourceVO resource = eModel.getResource(unitArray.get(0).resourceID);
		
		for (int j = 0; j <= data.size() - 1; j++){
			if ( data.get(j).roleID == resource.roleID){
				data.get(j).selectedIncidentID = incidentID;
			}
		}		
	}
	
	public void setPlayerIncident(int incidentID, int roleID) {
		for(PlayerVO player : data){
			if( player.roleID == roleID ) {
				player.selectedIncidentID = incidentID;
				break;
			}
		}
	}
	
	public PlayerVO getPlayer(int roleID){
		PlayerVO rolePlayer = new PlayerVO();
		
		for(PlayerVO player : data){
			if( player.roleID == roleID ) {				
				rolePlayer = player;;
			}
		}
		
		return rolePlayer;
	}
	
	
	public void resetPlayerIncidents(){
			
		for (int j = 0; j <= data.size() - 1; j++){
			data.get(j).selectedIncidentID = 0;				
		}
			
	}
	
}
