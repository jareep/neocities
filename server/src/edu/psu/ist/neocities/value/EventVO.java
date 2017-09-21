
package edu.psu.ist.neocities.value;
import java.util.List;
import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.model.EnvironmentModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.util.TimeSet;

@Root
@Default (DefaultType.FIELD)
public class EventVO {
    
	/*****XML SET VARIABLES******/
	
	
    @Attribute
    public int eventID;/*[***] name change for convenience and access modifier*/
    
    @Element (required=false)
    public String label;
    
    public int locationId; /*[***] access modifier */
        
    public double initialSevarity;	
    
    @ElementList (required=false)
    public List<AnswerVO> answers;      // List of answers
	    
    @Element (required=false)
    public String eventMessage;
    
    @Element (required=false)
    public String completeType = "unit"; 	// Two types of completion: 'unit' says complete it all necessary units show up, 'mag' says complete if
    										//	magnitude reaches 0
    
    @Element (required=false)
    public String successMessage = "Success!";
    
    @Element (required=false)
    public String failureMessage = "Fail!";
        
    @Element (required=false)
    public double magCap = -1;          // Maximum magnitude that the event can reach, -1 if infinite
    
    @Element (required=false)			// If false allows multiple correct dispatches, if true, disables dispatching to this event after correct 
    public Boolean lockable = false;	// resource is applied

	//Time Variables
	public TimeSet dispatchTime; //Start time from the beginning of the simulation till this event dispatched from the simulator
	
	public TimeSet timeLimit; // Number of TimeSteps that determines the length of this event.			
	
	@Element (required = false)
	public TimeSet pacingTimeLimit; // The time-step before which all resources should arrive.
	
	@Element (required=false)
	public int unitCap = 0; // Unit Limitations
	// if no unit limit exists set as -1
  //  @Element (required=false)
   // public int resourceCap = 0; //Resource Limitations
	
    /*****SYSTEM MANIPULATION VARIABLES******/
    @Transient
	public ArrayList<Double> magnitudeArray = new ArrayList<Double>();  // series of magnitudes the event went through..
	
	@Transient
	public int currentTimeStep = 0;
    
	@Transient
	public int goodResources; // The total resources present at this event, this incorporates the priority if applicable
	
	@Transient
	public int individualUnits; // the total number of individual units at this event (does not include priority)

    @Transient
	public int condition = -1;  // Explained below	
	/* 	Condition has values indicating the status of the event: 
   
	     * -1 = event yet to be initialized
	     * 0 = failure 
	     * 1 = event still active
	     * 2 = success 
	*/
    
   // public int resourceCap = 0; //Resource Limit for all resources, not just a specific type
	// if no resource limit exists set as -1

	@Transient
	String status = "false"; // Are units on scene? Taken from the old IncidentVO

	@Transient
	public Boolean resourceCapBound = Boolean.FALSE; //If TRUE, Caps the total effective resources for an event
	
	@Transient
	public Boolean unitCapBound = Boolean.FALSE;  //IF TRUE, Caps the resource units for an event, each cap defined in the AnswerVO

	@Transient
	public int finishingTimeStep = -1;

	@Transient
	public double finishingMagnitude = -1;
	
	@Transient
	public int assignedInformation = 0;
	
	
	@Transient
	ScenarioModel scenModel =  ScenarioModel.instance();
    /***********************************************
     *                 Constructors                *  
     ***********************************************/ 
		
	public EventVO() {
        super();
    }
	
    public EventVO(int eventID, int locationId, double initialSevarity,
			String eventMessage, double magCap) {
		super();
		this.eventID = eventID;
		this.locationId = locationId;
		this.initialSevarity = initialSevarity;
		this.eventMessage = eventMessage;
		this.magCap = magCap;
		
		this.answers = new ArrayList<AnswerVO>();
	}

	/***********************************************
     *                 Getters                     *  
     ***********************************************/

    public int getId() {
        return this.eventID;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public double getSevarity() {
        return initialSevarity;
    }

    public int getTimeStepLimit() {
        return timeLimit.getTimeSteps(scenModel.TimeStepLength);
    }

    public double getMagCap() {
        return magCap;
    }
    

    public double getUnitCap(){
    	
    	return this.unitCap;
    }
    
    public boolean isUnitCapBound() {
    	if (this.unitCap > 0) {  return true; }
    	
    	return false;
    }
    
    public boolean isResourceCapBound() {
    	for (AnswerVO answer : this.answers)
    	{
    		if (answer.resourceCap > 0) { return true; }
    	}
    	
    	return false;
    }
	
	//[***]
	public int getCurrentTimeStep(){
		return currentTimeStep;	
	}
	
	//[***]
	public double getCurrentMagnitude(){
		
		CommController commController = CommController.instance();

		
		if(magnitudeArray.size()==0){
			
			//commController.consoleMessage("ERROR - magnitudeArray.size() = 0");
			return this.initialSevarity;
			
		}
			
			
		return 	magnitudeArray.get(magnitudeArray.size()-1);
	}
	
	public int getDispatchTime(){
		
		return this.dispatchTime.getTimeSteps(scenModel.TimeStepLength);
	}
	
	public long getDispatchTimeMS() {
		return this.dispatchTime.calcTotalMS();
	}
	
	//[***]
	public double getPreviousMagnitude(){
		System.out.println("Previous time step " + (currentTimeStep-1));
		return 	magnitudeArray.get(currentTimeStep-1);
	}
	
	//[***]
	public int getResourcesAtEvent(){
			return goodResources;
	}
	
	public String getLabel() {
		return label;
	}
	

	//used by EventModel to see if event is active
	
	 public boolean isActive(){
		 
		 if(this.condition==1)
			 return true;
		 else
			 return false;
	 }
	 
	 public String getStatus(){
		 
		 return this.status;
		 
	 }
	 
	 public Boolean isLocked()
	 {
		 if (this.lockable = false) { return false; }
		 
		 for (AnswerVO answer : this.answers)
		 {
			 if (answer.numApplied == 0) { return false; }
		 }
		 
		 return true;
	 }
	 
    
	 
	//Function assumes that UnitMatch performed above = true
	public AnswerVO getAnswer(int _resourceID) {
					
			AnswerVO currentAnswer = new AnswerVO();
			
			//loop over the correct answers for the scoreEvent 
			AnswerLoop:
			for (AnswerVO answerVO : this.answers){			
				if(answerVO.resourceID == _resourceID){
					currentAnswer = answerVO;
					break AnswerLoop;
					}			
			}//end of j loop 
			
	return currentAnswer;
	
	}

	 
    /***********************************************
     *                 Setters                     *  
     ***********************************************/
    
		
    public void setId(int id) {
        this.eventID = id;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public void setInitialSevarity(double sevarity) {
        this.initialSevarity = sevarity;
    }

    public void setTimeLimit(TimeSet timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public void setDispatchTime(TimeSet dispatchTime)
    {
    	this.dispatchTime = dispatchTime;
    }

    public void setMagCap(double magCap) {
        this.magCap = magCap;
    }
	

    public void setUnitCap(int t){
    	
    	this.unitCap = t;
    }
    
    public void setLabel (String _label) {
    	this.label = _label;
    }

        
	//[***]
	public void incrementTimeStep(){
			currentTimeStep++;
	}
	
	
	//[***]
	public void addResources(int k){
			goodResources = goodResources + k;
	
	}
	
	//[***]	
	public void recallResources(int k){
			goodResources = goodResources - k;

	}

	  
	  
	  public void activate()
	  {
	        this.condition = 1;
	  }
	    
	  
	  public void setStatus(String stat){
		  
		  this.status = stat;
	  }
	  
	  public void setEventComplete(){
			condition = 2;
			
			this.setStatus("Complete");
	  }
		
		public void setEventFailure(){
			condition = 0;
				
			this.setStatus("Failed");
		}
   
	  public void decrementAnswerTotal(int resourceID){
			
		
			for (int j = 0; j < this.answers.size(); j++){
				if (this.answers.get(j).resourceID == resourceID){
					this.answers.get(j).numApplied = this.answers.get(j).numApplied;
					break;
				}//end of AnswerMatch
			}//end of answerloop
		}
		
		
		//Function decrements the current answer tally
	  public void incrementAnswerTotal(int resourceID, int priorityEffect, boolean overwrite){
		
			
			for (int j = 0; j < this.answers.size(); j++){
				if (this.answers.get(j).resourceID == resourceID ){				
					
					if(!overwrite)
					this.answers.get(j).numApplied = this.answers.get(j).numApplied + priorityEffect;
					else
						this.answers.get(j).numApplied = priorityEffect;
						
					break;
				}//end of AnswerMatch
			}//end of answerloop
		}		
		   
	  
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
     
    public boolean isMagCap()
    {
        return (this.magCap >= 0);
    }
    
    public boolean isTimeLimit()
    {
        return (this.timeLimit.getTimeSteps(3) >= 0);
    }
    
   
    
    public void addAnswer(AnswerVO answer)
    {
    	if (answers == null)
    	{
    		answers = new ArrayList<AnswerVO>();
    	}
        answers.add(answer);
    }
    
    public boolean checkAnswer(int unitResourceID){
    	
    	
    	if(this.eventID==-9999) return false;
    	
    	for(AnswerVO answerVO : this.answers){
    			
    			if(answerVO.resourceID == unitResourceID){
    				//answerVO.numApplied++;
    				return true;
    			}
    		 }
    		
    		 return false;   	
      }
    
    public boolean checkEventComplete(double magSuccess)
    {
    	if (this.completeType.equals("unit"))
    	{
    		return this.checkUnitComplete();
    	}
    	else
    	{
    		return this.checkMagComplete(magSuccess);
    	}
    }
    
     private boolean checkUnitComplete() 
     // This checks if the scenario was completed based on if all necessary units arrived on scene
     {
    	for (AnswerVO answerVO : this.answers)
    	{
    		if (answerVO.numApplied < answerVO.expected)
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }
     
     public boolean checkMagComplete(double magSuccess)
     {
    	 if (this.getCurrentMagnitude() > magSuccess) { return false; }
    	 
    	 return true;
     }
    
	public boolean checkRoleAnswer (int roleID) {
	    EnvironmentModel eModel = EnvironmentModel.instance();
	    Boolean unitMatch = Boolean.FALSE; 
	    ResourceVO resource;
	    AnswerLoop:
	        for (int j = 0; j < answers.size(); j++){
	            //comm.consoleMessage("Answer ---- Unit Resource = " + unitArray.get(i).resourceID + " , Answer = " + eventAnswers.get(j).resourceID);
	            resource = eModel.getResource(answers.get(j).resourceID);
	        	if(resource.roleID == roleID || resource.roleID == -1){
	                unitMatch = Boolean.TRUE;
	                break AnswerLoop;
	            }           
	        }//end of j loop 
	        
	        return unitMatch;
	}

}
