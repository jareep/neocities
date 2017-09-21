package edu.psu.ist.neocities.value;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;


@Root
@Default (DefaultType.FIELD)
public class ResourceVO {
    
    @Attribute
    public int id;
    
    public String resourceName;
    
    public int total;           // If infinite set as -1
    
    public int roleID;              // If available to all, set as -1
    
    public int travelTimeSteps;          // If inapplicable, set to 0
    
    public int returnTimeSteps;          // If inapplicable, set to 0
    
    @Element (required = false)
    public boolean isAction = false; // Set to true if it is an action such as a port-block
    
    @Element (required = false)
    public String resourceImage = "";
    
    @Element (required = false)
    public String incorrectFeedback = "";
    
    @Element (required = false)
    public String correctFeedback = "";
    
    @Transient
    public int available = 0;
    
    @Transient
    public int dispatched = 0;
    
    @Transient
    public int badgeCount = 0; // This would dictate the badge number
    
    
    public ResourceVO(int id, String resourceName, int available, int roleID,
			int travelTimeSteps, int returnTimeSteps, boolean isAction,
			String resourceImage, String incorrectFeedback,
			String correctFeedback) {
		super();
		this.id = id;
		this.resourceName = resourceName;
		this.available = available;
		this.roleID = roleID;
		this.travelTimeSteps = travelTimeSteps;
		this.returnTimeSteps = returnTimeSteps;
		this.isAction = isAction;
		this.resourceImage = resourceImage;
		this.incorrectFeedback = incorrectFeedback;
		this.correctFeedback = correctFeedback;
	}

	public ResourceVO()
    {
        super();
    }

    // Getters
    
    public int getResourceID() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceImage() {
        return resourceImage;
    }

    public int getAvailable() {
        return available;
    }

    public int getRoleID() {
        return roleID;
    }

    public int getTravelTime() {
        return travelTimeSteps;
    }

    public int getReturnTime() {
        return returnTimeSteps;
    }

    public String getIncorrectFeedback() {
        return incorrectFeedback;
    }

    public String getCorrectFeedback() {
        return correctFeedback;
    }
    
    
    /*[***]*/
    public boolean isAction(){
    	
    	return this.isAction;
    }

    
    // Setters
    
    /*[***]*/
    public void setAsAction(){
    	
    	this.isAction = true; 
    }
    
    public void setAsResource(){
    	
    	this.isAction = false;
    }
    
    public void setResourceID(int id) {
        this.id = id;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setRoldID(int roldID) {
        this.roleID = roleID;
    }

    public void setTravelTimeSteps(int travelTime) {
        this.travelTimeSteps = travelTime;
    }

    public void setReturnTimeSteps(int returnTime) {
        this.returnTimeSteps = returnTime;
    }

    public void setIncorrectFeedback(String incorrectFeedback) {
        this.incorrectFeedback = incorrectFeedback;
    }

    public void setCorrectFeedback(String correctFeedback) {
        this.correctFeedback = correctFeedback;
    }
    
    public void initAvailable() {
    	this.available = this.total;
    }
    
    public void incrementBadgeCount(){
		
    	this.badgeCount++;
    	
    }
    
    public void decrementBadgeCount(){
    	 
    	this.badgeCount--;
    	
    }
    
    public int getBadgeCount(){
    	return this.badgeCount;
    }
    

    

}
