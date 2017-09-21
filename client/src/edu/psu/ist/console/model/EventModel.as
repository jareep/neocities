package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.*;
	
	import mx.collections.ArrayCollection;
	
	public class EventModel extends ModelFactory
	{
		public function EventModel()
		{
			super();
			trace("load EventModel");
		}
		
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var selectedEvent : EventVO = new EventVO();
		
		
	   /****************************************************************
	 	* Functions
	 	****************************************************************/
		
		//used by EventController to return Answers based on provided incident
		public function getAnswersOfEvent(eventID : int) : ArrayCollection {
			
			var eventAnswers : ArrayCollection = new ArrayCollection; //array of type answers
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).eventID == eventID){
					eventAnswers = data.getItemAt(i).answers;
					break;
				}
			}
			
			return eventAnswers;
		}
	/*
		//used by EventController to return Answers based on provided difficulty
		public function getAnswersOfDifficulty(difficultyID : int) : ArrayCollection {
			
			var eventAnswers : ArrayCollection = new ArrayCollection; //array of type answers
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).difficulty.difficultyID == difficultyID){
					eventAnswers = data.getItemAt(i).difficulty.answers;
				}
			}
			
			return eventAnswers;
		}
		*/
		//used by NCServer to return the appropriate EventVO
		public function getEvent( eventID : int) : EventVO {
			var eventVO : EventVO;
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).eventID == eventID){
					eventVO = data.getItemAt(i) as EventVO;
					break;
				}
			}
			
			return eventVO;
		}
		
		//used by NCServer to return the appropriate EventVO
		public function replaceEvent(eventVO : EventVO) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).eventID == eventVO.eventID){
					data.setItemAt(eventVO, i);
					break;
				}
			}			
		}
		
		public function addEvent(event : EventVO)
		{
			if (data == null)
			{
				data = new ArrayCollection();
			}
			
			data.addItem(event);
		}
		
		public function addEvents(events : ArrayCollection)
		{
			for each (var e :EventVO in events)
			{
				this.addEvent(e);
			}
		}
		
		
				
	}//end of class
}//end of package