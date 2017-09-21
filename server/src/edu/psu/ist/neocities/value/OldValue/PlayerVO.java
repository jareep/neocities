package edu.psu.ist.neocities.value.OldValue;

/**
 * @author bhellar
 * @date 7-11-08
 * @description PlayerVO defines variables of the player's information
 * @example police, fire, hazmat
 */
public class PlayerVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public String userName; // primary key
	
	public int roleID; //foreign key
	
	public String currentFlexVersion; //information on their flex client
	
	public int selectedIncidentID;
	
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public PlayerVO() {
			
	}
	
	//example row = "bdh161", 2, "3.1.234"
	public PlayerVO(String _userName, int _roleID, String _version)
	{
		roleID = _roleID;
		userName = _userName;
		currentFlexVersion = _version;		
	}
	
}
