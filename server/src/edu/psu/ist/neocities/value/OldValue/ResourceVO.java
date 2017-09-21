package edu.psu.ist.neocities.value.OldValue;

/**
 * @author bhellar
 * @date 9-11-08
 * @description ResourceVO defines the Resource Data Variables
 * @example squad car, fire truck, ambulance
 */
public class ResourceVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int resourceID; //primary key
	public String label;
	public String icon;
	public int available;
	public int dispatched;
	public int total;
	public int roleID; // foreign key to RoleVO
		
	/****************************************************************
	 * Constructors
	 ****************************************************************/	
	public ResourceVO() {
		// TODO Auto-generated constructor stub
	}
	
	//example = 1, 2, "Squad Car", "squadCar.gif", 5
	public ResourceVO (
		int _resourceID,
		int _roleID,
		String _label,
		String _icon,
		int _total		
	) {
		resourceID = _resourceID;
		label = _label;
		icon = _icon;
		total = _total;
		available = _total;
		dispatched = 0;
		roleID = _roleID;		
	}

}
