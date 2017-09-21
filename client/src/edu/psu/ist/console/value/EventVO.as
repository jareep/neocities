package edu.psu.ist.console.value
{	
	//import edu.psu.ist.neocities.oldValue.IncidentVO;
	
	import mx.collections.ArrayCollection;;
	import edu.psu.ist.neocities.model.InformationModel;
	import com.pnwrain.easyCG.model.ModelFactory;
	import edu.psu.ist.neocities.value.InformationVO;
				
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.ScoreEventVO")]
	public class EventVO
	{		
	/****************************************************************
	 * Variables
	 ****************************************************************/
	 	
	 	//public var incident : IncidentVO;
	 	//public var difficulty : DifficultyVO;
	 	
		public var eventID : int; // eventID 
	 	public var pendingArrival : ArrayCollection; //list of units pending arrival on scene, processed by status check
	 	public var pendingDelay : int = 0 ; //timeStep delay before units arrive on scene. 
	 	//public var active : Boolean = false; //by default, when this item is created, the event should not be active
	 	public var timeStep : int = 0; /* The current-time-step of the event. 
										  This should get incremented after the eventGrowth() is called/completed */
		public var magnitudeArray : Array = new Array(1000); // series of magnitudes the event went through..
		public var magCurrent : Number = 0; // Current-magnitude of the event
		public var finishingTimeStep : int; // The last time-step at which a magnitude was calculated.
		public var finishingMagnitude : Number; // magnitudeArray[finishingMagnitude] should give the same value
		public var goodResources : int;
		public var condition : int = -1;		// Explained below	
		
		public var label : String; // event label 
		public var status : String; // status label
		
		public var answers : ArrayCollection; //list of answers (answerVO)
		
		public var locationID : int; // ID of location associated to event
		
	/****************************************************************
	 * Constructors
	 ****************************************************************/		
		
		public function EventVO(){
			goodResources = 0;
			finishingMagnitude = 0;
			finishingTimeStep = 0;		
			pendingArrival = new ArrayCollection();
			//difficulty = new DifficultyVO();
		}
				
		//example 1, "Easy", 2 
		/*public function ScoreEventVO ( _incident : IncidentVO ,	_difficulty : DifficultyVO ) 
		{
			incident = _incident;
			difficulty = _difficulty;
			simEventScore = 0;	
			
			pendingArrival = new ArrayCollection();
			
		}
		*/
		
		/************************************************************	
		 * Condition has values indicating the status of the event:
		 * 
		 *  -1 = event yet to be initialized
		 *   0 = failure
		 *   1 = event still active
		 *   2 = success
		 * 
		 ****************************************************************/		
		public function getConditionLabel() : String {
			var conditionLabel : String = "";
			
			switch (this.condition){
				
				case -1:
					conditionLabel = "Event is not initialized";
					break;
				case 0:
					conditionLabel = "Event Failed!";
					break;
				case 1:
					conditionLabel = "Event is Active";
					break;
				case 2:
					conditionLabel = "Event Completed!";
					break;
			}	
			
			return conditionLabel;				
		}
		
		public function incrementTimeStep() : void{
		
			this.timeStep++;
			
		}
		
		public function isActive() : Boolean {
			
			if(this.condition==1)
				return true;
			else
				return false;
				
		}
		
		public function activate() : void
		{
			this.condition = 1;
		}
		
		
		public function setStatus(stat : String) : void{
			
			this.status = stat;
			
		}
		
		public function setEventComplete() : void{
			
			condition = 2;
			
			this.setStatus("Complete");
		}
		
		public function setEventFailure() : void{
			
			condition = 0;
			
			this.setStatus("Failed");
		}
		
		public function getEventInformation(): ArrayCollection{
			
			var iModel : InformationModel = ModelFactory.getModel("InformationModel") as InformationModel;
			
			var eventInfo:ArrayCollection = new ArrayCollection();
			
			
			for each (var info:InformationVO in iModel.data){
				
				if(info.eventID==this.eventID) 
					eventInfo.addItem(info);
				
			}// ending for
			
			return eventInfo;
			
		}// getEventInformation
		
		

	}//end of class
}//end of package