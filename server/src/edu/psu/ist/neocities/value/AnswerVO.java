   package edu.psu.ist.neocities.value;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

@Root
@Default (DefaultType.FIELD)
public class AnswerVO {
    
    public int resourceID;                  // the id of the correct resource
    
    public double weight;                      // The weight which this resource has on the scoring model
    
    @Element (required=false)
    public int predecessorID = -1;         // The id of the unit which must come before (-1 if none)
    
    @Element (required=false)
    public int resourceCap = 0;                 // Maximum number of units permitted (If infinite, set at 0)
    
    @Transient
    public int numApplied = 0;                 // The number of units which have been applied to this event
    
    public int expected; //number of units of this resource that need to be sent.
	
    
    public AnswerVO() {
        super();
    }
    
    
    public AnswerVO(int resourceID, double weight, int predecessorID,
			int unitCap) {
		super();
		this.resourceID = resourceID;
		this.weight = weight;
		this.predecessorID = predecessorID;
		this.resourceCap = unitCap;
	}



	/***********************************************
     *                 Getters                     *  
     ***********************************************/
    public int getResourceID() {
        return resourceID;
    }

    public Double getWeight() {
        return (weight);
    }

    public int getPredecessorID() {
        return predecessorID;
    }
    
    public int getResourceCap () 
    {
        return (this.resourceCap);
    }
    
    public int getNumApplied ()
    {
        return this.numApplied;
    }
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setPredecessorID(int predecessorID) {
        this.predecessorID = predecessorID;
    }
    
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
    
    public boolean hasResouceCap()
    {
        return (this.getResourceCap() > 0);
    }
    
    public boolean isCorrectAnswer(int resourceID)
    {
        return this.resourceID == (resourceID);
    }
    
    public boolean hasPredacessor()
    {
        return (this.predecessorID != -1);
    }
    
    public boolean isCorrectPredacessor(int resourceID)
    {
        return (this.predecessorID == resourceID); 
    }
    
    /*
    public boolean applyResource (ResourceVO resource)
    {
        if (this.isCorrectAnswer(resource.resourceID))
        {
            // If there is no unit cap, or the number applied is less than the unit cap
            if (this.getUnitCap() == 0 || this.numApplied < this.getUnitCap())
            {
                this.numApplied++;
                return true;
            }
        }
    
        return false;
    }*/
    

}
