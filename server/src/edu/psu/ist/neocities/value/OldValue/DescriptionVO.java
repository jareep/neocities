package edu.psu.ist.neocities.value.OldValue;

public class DescriptionVO {
	
	private int incidentID;
	private int permission;
	private String description;
	
	public DescriptionVO(int incidentID, int permission, String description) {
		super();
		this.incidentID = incidentID;
		this.permission = permission;
		this.description = description;
	}
	public int getIncidentID() {
		return incidentID;
	}
	public void setIncidentID(int incidentID) {
		this.incidentID = incidentID;
	}
	
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	
}// UnitVO