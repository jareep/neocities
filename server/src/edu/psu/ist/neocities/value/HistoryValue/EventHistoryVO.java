package edu.psu.ist.neocities.value.HistoryValue;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.psu.ist.neocities.value.ScoreVO;

/**
 * @author bhellar
 * @date 7-1-09
 * @description ActionHistoryVO stores the history of user actions (dispatch and recalls) for the simulation
 * 				This class has a Remote Class in AS. 
 *
 */
public class EventHistoryVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int eventID; //foreign key to incidentModel	
	
	public ScoreVO score; //stores the scoring information for this event;
	
	public int timeBegin; // when the event was dispatched (recorded in TimeSteps)
	public int timeOver; // when the event ended (recorded in TimeSteps)
	public int eventDuration; // how long the event was active (recorded in TimeSteps)
	public int eventTimeLimit; // timeLimit set for the event (recorded in TimeSteps)
	public String failComplete;
	public int dispatchCorrect = 0; // the number of correct units sent to this event (based only on answer)
	public int dispatchWrong = 0; // the number of incorrect or "wrong" units sent to this event;
	public int recallCorrect = 0; // the number of correct units recalled from this event
	public int recallWrong = 0; // the number of incorrect or "wrong" units recalled from this event 
	public int rawDispatch = 0; // Raw number of dispatched, includes if they recall before it gets there
	public int locationID = 0;
	public String completeType = "";
	public double initialSeverity = 0.0;
	
//   public Hashtable<Integer, Integer> roleDispatch = new Hashtable<Integer, Integer>();
					
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public EventHistoryVO() {
		
		
	} // ending constructor
	
	public EventHistoryVO(int _eventID, int _timeBegin) {
		
		eventID = _eventID;
		timeBegin = _timeBegin;
	} // ending constructor
	

}
