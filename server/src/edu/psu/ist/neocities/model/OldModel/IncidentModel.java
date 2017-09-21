/**
 * 
 */
package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.OldValue.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description stores in a list the IncidentVO Data
 */
public final class IncidentModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static IncidentModel instance = new IncidentModel();
	
	private IncidentModel() {
		// Required for Singleton Design Pattern
	}
			
	public static IncidentModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	
	// List of incidents
	public List<IncidentVO> data = new ArrayList<IncidentVO>();
	
	// List of descriptions
	public List<DescriptionVO> dData = new ArrayList<DescriptionVO>();

	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public IncidentVO getIncident( int incidentID){
		IncidentVO incident = new IncidentVO();
				
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				incident = data.get(i);
				break;
			}
		}
		
		return incident;
	}

		
	public void setIncidentStatus ( int incidentID, String status ) {
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				data.get(i).setStatus(status);
				break;
			}
		}
	}
	
	public String getLabelOf ( int incidentID) {
		
		String label = new String();
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				label = data.get(i).getLabel();
			}
		}
		
		return label;
	}
	
	public void setSuccessMSG ( int incidentID, String msg ) {
				
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				data.get(i).setSuccessMSG(msg);
			}
		}
	}
	
	public void addDescription(int incidentID, int permission, String description)
	{
		dData.add(new DescriptionVO(incidentID, permission, description));
	}
	
	public List<DescriptionVO> getDescriptions(int incidentID)
	{
		List<DescriptionVO> descripts = new ArrayList<DescriptionVO>();
		
		for (int i = 0; i <= dData.size() - 1; i++){
			if (dData.get(i).getIncidentID() == incidentID){
				descripts.add(dData.get(i));
			}
		}
		
		return descripts;
	}
	
	
	public void setFailureMSG ( int incidentID, String msg) {
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getIncidentID() == incidentID){
				data.get(i).setFailureMSG(msg);
			}
		}
	}
}