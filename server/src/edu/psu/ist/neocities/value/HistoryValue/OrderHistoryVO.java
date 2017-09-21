package edu.psu.ist.neocities.value.HistoryValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dxm401
 * This class contains variables concerning the Interdependency Scoring.
 * The following aspects of order/interdependency are maintained here  :- 
 *   (a) Hard-Order Score
 *   (b) Soft-Order Score
 *   (c) Timing/Pacing Score
 *   (d) Interdependency Score
 */

public class OrderHistoryVO {

	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int eventID; // foreign key
		
	// Sequencing variables :
	public boolean hardOrder = true; //did the event succeed in matching the hardOrder, assume true initially
	public int numCorrectOrderUnits = 0 ; // numerator information for sequencing
	public int totalActions = 0 ; // denominator information for sequencing
	
	// Role Specific sequencing variables:
	public boolean hardRoleOrder = true;
	public int numCorrectRoleOrderUnits = 0;
	
	// Interdependency variables:
	public int numRequiredUnits = 0; //this is the number if interdependent units required..
	public int numCorrectUnits = 0; //number of correct units, regardless of sequence info. 
	
	// Pacing variables:
	public int numTimelyArrival = 0; // The number of resources that arrived on time..
	public int uniqueResources = 0;
	
	public List<Integer> uniqueArrivedResource = new ArrayList<Integer>();     // storage for the resource IDS of the units that show up
	                                                                            // so the system doesn't tally multiple of the same units as arriving ontime
	
	
	/****************************************************************
	 * Constructors
	 ****************************************************************/	
	public OrderHistoryVO(){ 		

	}
	
	public OrderHistoryVO(int _eventID, int _numRequiredUnits){		
		eventID = _eventID;
		numRequiredUnits = _numRequiredUnits;
	} 
	
	public boolean checkUniqueArrived(int resourceID)
	{
	    for(int i = 0; i < this.uniqueArrivedResource.size(); i++)
	    {
	        if (this.uniqueArrivedResource.get(i) == resourceID)
	        {
	            return false;
	        }
	    }
	    
	    this.uniqueArrivedResource.add(resourceID);
	    
	    return true;
	    
	}
	
} // OrderHistoryVO
