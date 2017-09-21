package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;

/**
 * @author bhellar
 * @date 9-11-08
 * @description RoleVO defines the Role Data Variables
 * @example police, fire, hazmat
 */
public class RoleVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int roleID; //primary key
	public String label; // DevNotes: Police / Fire / Hazmat
	public String icon; 
	public ArrayList<ResourceVO> resources;
		
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public RoleVO() {
			
	}
	
	//example row = 1, "Police", "edu/psu/ist/neocities/assets/polteam.gif"
	public RoleVO(int _roleID, String _label, String _icon)
	{
		roleID = _roleID;
		label = _label;
		icon = _icon;		
	}
	
}
