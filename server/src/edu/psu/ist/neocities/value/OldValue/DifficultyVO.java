package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;

import edu.psu.ist.neocities.control.CommController;

public class DifficultyVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	CommController comm = CommController.instance();
	
	public int difficultyID; //primary key
	public int incidentID; //foreign key to IncidentModel.data IncidentVO
	
	//Answer Variables
	public ArrayList<AnswerVO> answers;
	private int resourceCap = 0; //Resource Limit for all resources, not just a specific type
	private int resourceRequirementNum = 0;  // This is necessary for the interdependency
	private Boolean resourceCapBound = Boolean.FALSE; //If TRUE, Caps the total effective resources for an event
	private Boolean unitCapBound = Boolean.FALSE;  //IF TRUE, Caps the resource units for an event, each cap defined in the AnswerVO
	private Boolean interdepedentBound = Boolean.FALSE; //IF TRUE, then interdependent logic applies. 
		
	//Magnitude Variables
	private double magLimit = 0; //the maximum value the magnitude can reach during a ScoringEvent,  default 0 means no limit. 
	private double magSuccess = 0.0; //value that determines when an ScoreEvent reaches completion... 
	private double magCap = 8; 	//the maximum value the magnitude can reach, Cap beyond 8 will cause scoring model to fail. 
	private Boolean magBound = Boolean.FALSE;
	private Boolean magCapBound = Boolean.TRUE; //default TRUE as magCAP is default of 8;
	
	//Time Variables
	private long dispatchTime = 0; //Start time from the beginning of the simulation till this event dispatched from the simulator
	private int timeStepLimit = 0; // Number of TimeSteps that determines the length of this event.			
	private int pacingTimeLimit = 0; // The time-step before which all resources should arrive.
	private int staticPacing = 0;  // A static representation of the pacing time, used for calculating the seconds late...
	private Boolean timeBound = Boolean.FALSE; //If TRUE, will use the stopTime variable of an incidentVO to complete a scoring event
	private Boolean pacingBound = Boolean.FALSE; //Set TRUE when there is a pacing TimeStep Limit, set False when pacingTimeLimit is reached
	private Boolean staticPacingBound = Boolean.FALSE;
		
		
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public DifficultyVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	//example 1, "Easy", 2 
	public DifficultyVO ( int _difficultyID, int _incidentID, int MagCap ) 
	{
		this.difficultyID = _difficultyID;
		this.incidentID = _incidentID;
		this.setMagCap(MagCap);
		
		answers = new ArrayList<AnswerVO>();
	}
	
	//example 1, "Easy", 2 
	public DifficultyVO ( int _difficultyID, int _incidentID ) 
	{
		this.difficultyID = _difficultyID;
		this.incidentID = _incidentID;
		
		answers = new ArrayList<AnswerVO>();
	}
	
	//example 1, "Easy", 2 
	public DifficultyVO ( int _difficultyID, int _incidentID, int MagCap, int _pacingTimeLimit ) 
	{
		this.difficultyID = _difficultyID;
		this.incidentID = _incidentID;
		this.setMagCap(MagCap);
		this.setPacingTimeLimit(_pacingTimeLimit);
		
		answers = new ArrayList<AnswerVO>();
		this.interdepedentBound = true;
	}
	

	
	
	/****************************************************************
	 * Boolean Accessor Functions
	 ****************************************************************/
	public Boolean isTimeBound(){
		return timeBound;
	}
	
	public Boolean isMagBound()	{
	  return magBound;	
	}
	
	public Boolean isResourceBound()	{
		return resourceCapBound;
	}
	
	//Use this as the primary accessor until the Above function is deprecated
	public Boolean isResourceCapBound() {
		return resourceCapBound;
	}
	
	public Boolean isMagCapBound() {
		return magCapBound;
	}
	
	public Boolean isUnitCapBound() {
		return unitCapBound;
	}
	
	public Boolean isUnitBound() {
		return unitCapBound;
	}
	
	public Boolean isInterdependent(){
		return interdepedentBound;
	}
	
	public Boolean isPacing(){
		return pacingBound;
	}
	
	public Boolean isStaticPacingBound() 
	{
	    return this.staticPacingBound;
	}
	
	
	
	
	/****************************************************************
	 * Getters
	 ****************************************************************/
	public ArrayList<AnswerVO> getAnswers(){
		return answers;
	}
	
	public int getDifficultyID(){
		return difficultyID;
	}	
	
	public double getIncidentID(){
		return incidentID;
	}
	
	public double getMagSuccess(){
		return magSuccess;
	}
	
	public double getMagCap(){
		return magCap;
	}
		
	public int getTimeStepLimit(){
		return timeStepLimit;
	}
	
	public long getTimeLimit(){
		return (long)timeStepLimit * 3000;
	}
		
	public long getDispatchTime(){
		return dispatchTime;
	}
	
	public int getDispatchTimeStep() {		
		return (int) dispatchTime / 3000;
	}	
		
	public double getMagLimit(){
		return magLimit;
	}
	
	public int getResourceLimit(){
		return resourceCap;
	}
	
	public int getResourceCap(){
		return resourceCap;
	}
	
	public int getResourceReqNum(){		
		return resourceRequirementNum;
	}
	
	public int getPacingLimit () {
		return pacingTimeLimit;
	}
	
	public int getStaticPacingLimit() {
	    return staticPacing;
	}
	
		
	/****************************************************************
	 * Setters
	 ****************************************************************/
	public void setDifficultyID(int _difficultyID){
		this.difficultyID = _difficultyID;
	}
	
	public void setNewAnswer(AnswerVO answer){
		answers.add(answer);
	}
	
	public void setMagBound(Boolean value)
	{
		magBound = value;
	}
	
	public void setTimeBound(Boolean value){
		timeBound = value;
	} 
		
	public void setMagSuccess(double _magSuccess)
	{
		magSuccess = _magSuccess;
	}
		
	public void setTimeStepLimit(int _timeStepLimit)
	{
		timeStepLimit = _timeStepLimit;
		
		if (timeStepLimit <= 1){
		    timeBound = Boolean.FALSE;
		} else {
		    timeBound = Boolean.TRUE;
		}
	}
		
	public void setMagLimit(double _magLimit)
	{
	   magLimit = _magLimit;
	   
	   if (magLimit <= 1){
		   magBound = Boolean.FALSE;
	   } else {
		   magBound = Boolean.TRUE;
	   }
	   
	   System.out.println("Mag Bound: " + magBound.toString() + " = " + magLimit);
	}
	
	public void setResourceLimit(int _resourceLimit)
	{
		resourceCap = _resourceLimit;
		   
		   if (resourceCap <= 1){
			   resourceCapBound = Boolean.FALSE;
		   } else {
			   resourceCapBound = Boolean.TRUE;
		   }
		   
		   System.out.println("ResCap: " + resourceCapBound.toString() + " = " + resourceCap);   
	}
		
	public void setMagCap(double _magCap){
	   	magCap = _magCap;
	   	
	   	if (magCap <= 1){
	   		magCapBound = Boolean.FALSE;
	   	}
	   	else {
	   		magCapBound = Boolean.TRUE;
	   	}
	   	
	   	System.out.println("magCap: " + magCapBound.toString() + " = " + magCap);
	}
	
	public void setUnitCapBound(boolean value){
		unitCapBound = value;
	}
		
	public void setDispatchTime(long ms){
		dispatchTime = ms;
	}
					
	public void setSeverity(int _incidentID){
	     incidentID = _incidentID; 	
	}

	public void setResourceReqNum(int _requirement){		
		this.resourceRequirementNum  = _requirement;				
	}
	
	public void setPacingTimeLimit(int _timeStepLimit){		 
		this.pacingTimeLimit = _timeStepLimit;
		this.pacingBound = true;
		this.staticPacingBound = true;
		this.staticPacing = _timeStepLimit;
		
	} // ending setPactingTimeLimit
	
	public void decrementPacing (){
		this.pacingTimeLimit--;
		
		if (pacingTimeLimit < 1){
			pacingBound = false;
		}
	}

}
