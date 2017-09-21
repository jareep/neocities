package edu.psu.ist.console.value
{
	import edu.psu.ist.neocities.value.ScoreVO;
		
					
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.HistoryValue.EventHistoryVO")]
	public class EventHistoryVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
		
		public var eventID : int; //foreign key to incidentModel	
		
		public var score : ScoreVO;
				
		public var timeBegin : int; //when the event started
		public var timeOver : int; // when the event ended
		public var eventDuration : int; // how long the event was active
		public var eventTimeLimit : int; // timeLimit set for the event
		public var failComplete : String;
		
		public var dispatchCorrect : int = 0; // the number of correct units sent to this event (based only on answer)
		public var dispatchWrong : int = 0; // the number of incorrect or "wrong" units sent to this event;
		public var recallCorrect : int= 0; // the number of correct units recalled from this event
		public var recallWrong  : int = 0; // the number of incorrect or "wrong" units recalled from this event 
		public var rawDispatch : int = 0; // Raw number of dispatched, includes if they recall before it gets there
		public var locationID : int = 0;
		public var completeType : String;
		public var initialSeverity : Number; 
		
		
	   /****************************************************************
		 * Constructors
		 ****************************************************************/
		public function EventHistoryVO() {
				
		}
		


	}
}
