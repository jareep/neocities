package edu.psu.ist.neocities.value.OldValue;

import java.util.List;
import edu.psu.ist.neocities.value.OldValue.DescriptionVO;

/**
 * @author bhellar
 * @date 9-11-08
 * @description IncidentVO defines the Incident Data Variables and access functions
 */
public class IncidentVO {

	/****************************************************************
	 * Variables
	 ****************************************************************/
	private int incidentID;
    private String label;
    private String description;
    private String icon;
    private String status;
    
    private String success;
    private String failure;
    
    private double severity;//initial Magnitude of the Event
    private double magCurrent; //current Magnitude of the Event
    
    private double latitude; 
    private double longitude; 
    

    /****************************************************************
	 * Constructors
	 ****************************************************************/
    public IncidentVO() {
    	
    }
    
    //@example = 1, "Trash Can Fire", "Smoke", "icon.gif", "on scene", 5000
    public IncidentVO(int _incidentID, String _label, String _description, String _icon, double _severity) {
		this.incidentID = _incidentID;
		this.label = _label;
		this.description = _description;
		this.icon = _icon;
		this.severity = _severity;
				
		
		//random loc in state college
		magCurrent = _severity;
		latitude = -77.85791792449951;
		longitude = 40.79645552436291;
		
		status = "new";
	}
    
    public IncidentVO(int _incidentID, String _label, String _icon, double _severity) {
		this.incidentID = _incidentID;
		this.label = _label;
		this.icon = _icon;
		this.severity = _severity;		
		
		//random loc in state college
		magCurrent = _severity;
		latitude = -77.85791792449951;
		longitude = 40.79645552436291;
		
		status = "new";
	}
    
    
    //@example = 1, "Trash Can Fire", "Smoke", "icon.gif", "on scene", 5000
    public IncidentVO(int _incidentID, String _label, String _description, String _icon, double _severity, double _lat, double _long) {
		this.incidentID = _incidentID;
		this.label = _label;
		this.description = _description;
		this.icon = _icon;
		this.severity = _severity;
		this.latitude = _lat;
		this.longitude = _long;
					
		magCurrent = _severity;
		status = "new";
	}
    
    public IncidentVO(int _incidentID, String _label, String _icon, double _severity, double _lat, double _long) {
		this.incidentID = _incidentID;
		this.label = _label;
		this.icon = _icon;
		this.severity = _severity;
		this.latitude = _lat;
		this.longitude = _long;
		
		magCurrent = _severity;
		status = "new";
	}
          
    /****************************************************************
	 * Getters
	 ****************************************************************/
    public String getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
	public String getIcon() {
		return icon;
	}	
	public String getLabel() {
		return label;
	}		
	public int getIncidentID() {
		return incidentID;
	}
	public double getSeverity() {
		return severity;
	}
	public double getMagCurrent() {
		return magCurrent;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getSuccessMSG() {
		return success;
	}
	public String getFailureMSG() {
		return failure;
	}
	
	/****************************************************************
	 * Setters
	 ****************************************************************/
	public void setIncidentID(int incidentID) {
		this.incidentID = incidentID;
	}
	public void setSeverity(double severity){
		this.severity = severity;
	}
	public void setMagCurrent(double magCurrent){
		this.magCurrent = magCurrent;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setLatitude(double coord) {
		this.latitude = coord;
	}
	public void setLongitude(double coord) {
		this.longitude = coord;
	}
	public void setSuccessMSG(String msg){
		this.success = msg;
	}
	public void setFailureMSG(String msg){
		this.failure = msg;
	}

}
