package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.value.OldValue.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description stores in a list the ResourceVO Data
 */
public final class ResourceModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	CommController comm = CommController.instance();
	
	private static ResourceModel instance = new ResourceModel();
	
	private ResourceModel() {
		// Required for Singleton Design Pattern
	}
			
	public static ResourceModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/	
	public List<ResourceVO> data = new ArrayList<ResourceVO>();
	
	/****************************************************************
	 * Resource Functions
	 ****************************************************************/
	
	//returns the Resource Value object for the passed in resourceID
	public ResourceVO getResource ( int resourceID ) {
		ResourceVO resource = new ResourceVO(999, 999, "EPIC FAIL","Fail.jpg", 1);
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).resourceID == resourceID){
				resource = data.get(i);				
				break;
			}
		}
		return resource;
	}
	
	//returns the role of the passed in resourceID
	public int getRoleOfResource ( int resourceID ) {
		int roleID = 0;
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).resourceID == resourceID){
				roleID = data.get(i).roleID;				
				break;
			}
		}
		return roleID;
	}
	
	public void resetResourceTotals (){
		for (int i = 0; i <= data.size() - 1; i++){
			data.get(i).available = data.get(i).total;
			data.get(i).dispatched = 0;
		}
	}
	
	// returns the number of dispatched resources for a specific role
	public int getRoleDispatched(int roleID)
	{
	    int dispatched = 0;
	    for (int i=0; i < data.size(); i++)
	    {
	        if (data.get(i).roleID == roleID)
	        {
	            dispatched+=data.get(i).dispatched;
	        }
	    }
	    
	    return dispatched;
	}
	
	public void dispatchResource (int resourceID ){
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).resourceID == resourceID){
				data.get(i).dispatched++;
				data.get(i).available = data.get(i).total - data.get(i).dispatched;
				comm.consoleMessage("Resource: Dispatch " + data.get(i).label + " - " 
						+ data.get(i).available + " / " + data.get(i).dispatched + " / " + data.get(i).total);
				break;
			}//end of if
		}//end of loop
	}
	
	public void returnResource (int resourceID ){
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).resourceID == resourceID){
				data.get(i).dispatched--;
				data.get(i).available = data.get(i).total - data.get(i).dispatched;	
				comm.consoleMessage("Resource: Recall " + data.get(i).label + " - " 
						+ data.get(i).available + " / " + data.get(i).dispatched + " / " + data.get(i).total);
				break;
			}//end of if
		}//end of loop
	}

	//unused currently, but leaving this function just in case its needed later
	public ArrayList<ResourceVO> getResourcesOf(ArrayList<UnitVO> dispatchedUnits) {
		ArrayList<ResourceVO> resourceList = new ArrayList<ResourceVO>();
		
		for (int i = 0; i <= dispatchedUnits.size() - 1; i++){
			ResourceVO resource = this.getResource(dispatchedUnits.get(i).resourceID);
			comm.consoleMessage("Resource: resourceList " + resource.label + " - " 
					+ resource.available + " / " + resource.dispatched + " / " + resource.total);
			resourceList.add(resource);
		}
		
		return resourceList;
				
	}

}
