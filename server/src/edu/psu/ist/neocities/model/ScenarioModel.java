package edu.psu.ist.neocities.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.Element;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.interfaces.InfoInterface;
import edu.psu.ist.neocities.util.TimeSet;
import edu.psu.ist.neocities.value.AnswerVO;
import edu.psu.ist.neocities.value.EventVO;
import edu.psu.ist.neocities.value.InformationVO;
import edu.psu.ist.neocities.value.PauseVO;
import edu.psu.ist.neocities.value.QuestionAnswerVO;
import edu.psu.ist.neocities.value.QuestionVO;


@Root
@Default (DefaultType.FIELD)
public final class ScenarioModel {
	
	@Attribute
	public String ScenarioName;
	
	@Transient
	public static ScenarioModel instance = new ScenarioModel();
	
	public TimeSet ScenarioTime;
	
	@Element (required=false)
	public int TimeStepLength = 3;
	
	@ElementList
	public List <EventVO> Events = new ArrayList<EventVO>();
	
	@ElementList (required = false)
	public List <PauseVO> Pauses = new ArrayList<PauseVO>();
	
	@ElementList
	public List <InformationVO> Information = new ArrayList<InformationVO>();
	
	@Transient
	CommController comm = CommController.instance();
	
	@Transient
	public int masterTimeStep = 0;		// master timestep for the entire simulation
	
	@Transient
	public double magSuccess = 0.0; // Magnitude required to let events succeed
	
	
	
	
	public static ScenarioModel instance(){
		return instance;
	}
	
	public ScenarioModel()
	{
		super();
		
	}

	
	public ScenarioModel(String scenarioName) {
		super();
		ScenarioName = scenarioName;
	}

	/**********************************
	 *        SETTERS                 * 
	 **********************************/
	
	public void setScenarioName(String ScenarioName)
	{
		this.ScenarioName = ScenarioName;
		comm.consoleMessage("======== Loading Scenario: " + ScenarioName + " ========");
	}
	
	public void setTimeLimit (TimeSet time)
	{
		this.ScenarioTime = time;
		
		comm.consoleMessage("Scenario, " + this.ScenarioName + " Time Limit set at " + this.ScenarioTime.min + " min " + this.ScenarioTime.sec + 
				"(" + this.ScenarioTime.getTimeSteps(3) + " timesteps)");
	}
	
	public void setEvents (List<EventVO> events)
	{
		this.Events = events;
		
		comm.consoleMessage("Loaded " + this.Events.size() + " events into scenario " + this.ScenarioName);
	}
	
	public void setInformation (List <InformationVO> information)
	{
		this.Information = information;
		
		comm.consoleMessage("Loaded " + this.Information.size() + " information pieces into scenario " + this.ScenarioName);
	}
	
	public void setPauses (List <PauseVO> pauses)
	{
		this.Pauses = pauses;
		
		if (pauses != null)
		{
			comm.consoleMessage("Loaded " + this.Pauses.size() + " Pauses into scenario " + this.ScenarioName);
		}
	}
	
	
	@SuppressWarnings("unused")
	public void removeGoodResources(int eventID, int resourceID){
		
		EventLoop:
		for (int i = 0; i < Events.size() ; i++){	
			if (Events.get(i).getId() == eventID){
				Events.get(i).recallResources(1);	
	
			}			
		}//end of eventloop	
	} // removeGoodResources
	
	
	//used by EventController to add a Good Resource to a ScoreEvent
	@SuppressWarnings("unused")
	public void addGoodResources(int eventID, int resourceID, int priorityEffect, boolean overwrite){
			
		EventLoop:
		for (int i = 0; i < Events.size(); i++){
			if (Events.get(i).getId() == eventID){
				
				if (!overwrite) { Events.get(i).addResources(priorityEffect); }
				else
				{
					for(AnswerVO answerVO : Events.get(i).answers){
							
						if(answerVO.resourceID==resourceID){
								
									if(answerVO.numApplied==0)
									{
										// This means a different resource among the answers was applied
										Events.get(i).goodResources+= priorityEffect; 
									}
									else{
										//This means the same resource was applied and it is being overwritten
										
										Events.get(i).goodResources = Events.get(i).goodResources - answerVO.numApplied;
										Events.get(i).goodResources += priorityEffect;
									}
								
						}
						
					}// ending answers loop
										
				}
				
				Events.get(i).individualUnits++;
				Events.get(i).incrementAnswerTotal(resourceID, priorityEffect, overwrite);
				
			}//end of eventMatch
	
		}//end of eventLoop
		
	} // addGoodResources..
	
	public void addAssignedInfo(int eventID)
	{
		if (eventID == -1) { return; }
		
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).eventID == eventID)
			{
				Events.get(i).assignedInformation++;
				return;
			}
		}
	}

	/**********************************
	 *        GETTERS                 * 
	 **********************************/
	
	public int getCurrentTimeStep()
	{
		return this.masterTimeStep;
	}
	
	// returns scenario length in seconds
	public int getScenarioLength()
	{
		return this.ScenarioTime.getTimeSteps(this.TimeStepLength);
	}
	
	// returns scenario length in milliseconds
	public long getScenarioLengthMS()
	{
		return this.ScenarioTime.getTSinMS(this.TimeStepLength);
	}
	
	public EventVO getEvent(int eventID)
	{
		if ( eventID == -9999)
		{
			return new EventVO(-9999, -9999, 0.0, "", 0.0);
		}
		
		
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).eventID == eventID)
			{
				return Events.get(i);
			}
		}
		
		return null;
	}
	
	public List<EventVO> getEventsByLocation(int locationID)
	{
		List <EventVO> events = new ArrayList<EventVO>();
		
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).locationId == locationID)
			{
				events.add(Events.get(i));
			}
		}
		
		return events;
	}
	
	
	public List<EventVO> getActiveEventsByLocation(int locationID)
	{
		List <EventVO> events = new ArrayList<EventVO>();
		
		for (int i = 0; i < Events.size(); i++)
		{
			if ((Events.get(i).locationId == locationID)&&(Events.get(i).isActive()))
			{
				events.add(Events.get(i));
			}
		}
		
		return events;
	}

	
	public PauseVO getPause(int pauseID)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				return Pauses.get(i);
			}
		}
		
		return null;
	}
	
	public List<EventVO> getAllEvents()
	{
		return this.Events;
	}
	
	public List<PauseVO> getAllPauses(){
		
		return this.Pauses;
	}
	
	public List<InformationVO> getAllInformation(){
		
		return this.Information;
	}
	
	
	public InformationVO getInformation(int infoID)
	{
		for (int i = 0; i < Information.size(); i++)
		{
			if (Information.get(i).id == infoID)
			{
				return Information.get(i);
			}
		}
		
		return null;
	}
	
	/**********************************
	 *        Helpers                 * 
	 **********************************/
	
	public void resetModel()
	{
		this.TimeStepLength = 3;
		this.ScenarioName = null;
		this.ScenarioTime = null;
		if (this.Events != null) { this.Events.clear(); }
		if (this.Pauses != null) { this.Pauses.clear(); }
		if (this.Information != null) { this.Information.clear(); }
		
		instance = new ScenarioModel();
	}
	
	
	// used to check the answer to log in the action history
	public Boolean checkAnswer(int eventID, int resourceID)
	{
	    List<AnswerVO> answers = this.getEvent(eventID).answers;     //Events.get(eventID).answers;
		
	    for (int i=0; i < answers.size(); i++)
	    {
	        if (answers.get(i).getResourceID() == resourceID)
	        {
	              return true;
	        }
	    }
	    
	    return false;
	
	}// checkAnswer()
	
	
	// taken from EventModel - increments master time-step
	public void incrementTimeStep(){
		masterTimeStep++;
	}
	
	
	//used by EventController to Increment the timeStep of all active events
	public void incrementActiveEvents(){
		
		for (EventVO eventVO : Events){
			if (eventVO.isActive()){
				//increment TimeStep for Active Events
				eventVO.incrementTimeStep();
				
			} //active check			
		}//event loop
		
	}
	
	public void setEventStatus(int eventID, int status) 
	{
		for (EventVO eventVO : Events)
		{
			if (eventVO.eventID == eventID)
			{
				eventVO.setStatus(Integer.toString(status));
			}
		}
	}
	
	public void activateEvent(int id)
	{
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).eventID == id)
			{
				Events.get(i).activate();
			}
		}
	}
	
	
	public double getMagSuccess(){
		
		return this.magSuccess;
	}
	
	public long getTSLengthInMS()
	// returns timestep length in miliseconds
	{
		return this.TimeStepLength * 1000;
	}
	
	
	
	/**********************************
	 *        TESTING                 * 
	 **********************************/
	
	// These functions will be used for quick adding of test scenarios... They will not be used by the final program
	public void addEvent (EventVO event)
	{
		if (Events == null)
		{
			Events = new ArrayList<EventVO>();
		}
		Events.add(event);
	}
	
	public void setEventTimeLimit (int eventID, TimeSet time)
	{
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).getId() == eventID)
			{
				Events.get(i).setTimeLimit(time);
			}
		}
	}
	
	public void setEventDispatchTime(int eventID, TimeSet time)
	{
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).getId() == eventID)
			{
				Events.get(i).setDispatchTime(time);
			}
		}
	}
	
	public void addEventAnswer (int eventID, AnswerVO answer)
	{
		for (int i = 0; i < Events.size(); i++)
		{
			if (Events.get(i).getId() == eventID)
			{
				Events.get(i).addAnswer(answer);
			}
		}
	}
	
	public void addInformation (InformationVO info)
	{
		if (Information == null)
		{
			Information = new ArrayList<InformationVO>();
		}
		Information.add(info);
	}
	
	public void addPause(PauseVO pause)
	{
		if (Pauses == null)
		{
			Pauses = new ArrayList<PauseVO>();
		}
		
		Pauses.add(pause);
	}
	
	public void addPauseQuestion(int pauseID, QuestionVO question)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				Pauses.get(i).addQuestion(question);
			}
		}
	}
	
	public void addQuestionImage (int pauseID, int questionID, String image)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				Pauses.get(i).addQuestionImage(questionID, image);
			}
		}
	}
	
	public void addQuestionAnswer(int pauseID, int questionID, QuestionAnswerVO qa)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				Pauses.get(i).addQuestionAnswer(questionID, qa);
			}
		}
	}
	
	public void resetMasterTimeStep(){
		masterTimeStep = 0;
	}
	
	public void setInfoDispatchTime(int infoID, TimeSet time)
	{
		for (int i = 0; i < Information.size(); i++)
		{
			if (Information.get(i).getInformationId() == infoID)
			{
				Information.get(i).setDispatchTime(time);
			}
		}
	}
	
	public void setInfoData(int infoID, InfoInterface data)
	{
		for (int i = 0; i < Information.size(); i++)
		{
			if (Information.get(i).getInformationId() == infoID)
			{
				Information.get(i).setData(data);
			}
		}
	}
	
	public void setMagSuccess(double _magSuccess){
		
		this.magSuccess = _magSuccess;
	}
	public void setPauseTime(int pauseID, TimeSet time)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				Pauses.get(i).setPauseTime(time);
			}
		}
	}
	
	public void setPauseTimeLimit(int pauseID, TimeSet time)
	{
		for (int i = 0; i < Pauses.size(); i++)
		{
			if (Pauses.get(i).id == pauseID)
			{
				Pauses.get(i).setTimeLimit(time);
			}
		}
	}	
	
	public void setNewTimeLimit(int min, int sec)
	{
		ScenarioTime = new TimeSet(min, sec);
	}
}
