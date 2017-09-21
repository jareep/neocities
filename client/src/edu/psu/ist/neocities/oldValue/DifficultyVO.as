package edu.psu.ist.neocities.oldValue
{		
		
	import mx.collections.ArrayCollection;
					
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.DifficultyVO")]
	public class DifficultyVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
	 
		public var difficultyID : String;//primary key
		public var incidentID : String; // foreign key
		public var answers : ArrayCollection;
			
		public var magLimit : Number = 0; //the maximum value the magnitude can reach during a ScoringEvent,  default 0 means no limit. 
		public var magSuccess : Number = 0.5; //value that determines when an ScoreEvent reaches completion...
		public var magCap : Number = 0; //the maximum value the magnitude can reach, without ending the event
		public var resourceCap : int = 0;//Resource Limit for all resources, not just a specific type
				
		public var timeStepLimit : int = 0; //Let us assume that this is the number of imaginary time-steps... 
		public var dispatchTime : Number = 0;			
		
		public var timeBound : Boolean = false; //If TRUE, will use the stopTime variable of an incidentVO to complete a scoring event
		public var magBound : Boolean = false;
		public var resourceCapBound : Boolean = false; //If TRUE, Caps the total effective resources for an event
		public var unitCapBound : Boolean = false; // IF TRUE, Caps the resource untis for an event, each cap defined in the AnswerVO
		public var magCapBound : Boolean = false;  			
		
	    /****************************************************************
		* Constructors
		****************************************************************/
		
		//example 1, "Easy", 2 
		public function DifficultyVO () {
			this.difficultyID = "0";
			this.answers = new ArrayCollection();
		}
		
		/*
		public function DifficultyVO ( _difficultyID : String, _label : String, _severity : int )
		{
			difficultyID = _difficultyID;
			label = _label;
			severity = _severity;
		
			answers = new ArrayCollection();
		}
		*/
		
	   /****************************************************************
		* Label Functions
		****************************************************************/
		public function getMagBoundLabel() : String {
			var label : String = "none";
			
			if(magBound){
				label = magLimit.toString();
			}
			
			return label;			
		}
		
		public function getMagCapLabel() : String {
			var label : String = "none";
			
			if(magCapBound){
				label = magCap.toString();
			}
			
			return label;
		}
		
		public function getResCapLabel() : String {
			var label : String = "No Limit";
			
			if(resourceCapBound){
				label = (resourceCap.toString()) + " Resource Units";
			}
			else if(unitCapBound){
				label = "Individual Unit Cap";
			}
			
			return label;
		}
		
		public function getTimeBoundLabel() : String {
			var label : String = "none";
			var minLabel : String = this.getCompletionMinutes().toString();
			var secLabel : String = this.getCompletionSeconds().toString();
			var min : Number = this.getCompletionMinutes();
			var sec : Number = this.getCompletionSeconds();
			
			if(timeBound){
				
				if(min < 10){
					minLabel = "0" + minLabel;
				} 
				if(sec < 10){
					secLabel = "0" + secLabel;
				}
				
				label = minLabel + ":" + secLabel;
			}
			
			return label;
		}
		
		public function getDispatchTimeLabel() : String {
			
			var minLabel : String = this.getDispatchMinutes().toString();
			var secLabel : String = this.getDispatchSeconds().toString();
			var min : Number = this.getDispatchMinutes();
			var sec : Number = this.getDispatchSeconds();
			
			if(min < 10){
				minLabel = "0" + minLabel;
			} 
			if(sec < 10){
				secLabel = "0" + secLabel;
			}
			
			var label : String = minLabel + ":" + secLabel;
						
			return label;
		}
		
		
		/****************************************************************
		* Time Functions
		****************************************************************/
		
		public function getDispatchMinutes () : Number {
			var ms: Number = this.dispatchTime;
			
			var TotalSecs : Number = ms / 1000;
			var TotalMin : Number = TotalSecs / 60;			
			
			var min : Number = Math.floor(TotalMin);
			
			return Math.round(min);				
		}
		
		public function getDispatchSeconds () : Number {
			var ms: Number = this.dispatchTime;
			
			var TotalSecs : Number = ms / 1000;
			var TotalMin : Number = TotalSecs / 60;			
			
			var min : Number = Math.floor(TotalMin);			
			var second : Number = (TotalMin - min) * 60;
			
			return Math.round(second);					
		}
		
		public function getCompletionMinutes () : Number {
			var ms: Number = this.timeStepLimit * 3000;
			
			var TotalSecs : Number = ms / 1000;
			var TotalMin : Number = TotalSecs / 60;			
			
			var min : Number = Math.floor(TotalMin);
			
			return Math.round(min);			
		}
		
		public function getCompletionSeconds () : Number {
			var ms: Number = this.timeStepLimit * 3000;
			
			var TotalSecs : Number = ms / 1000;
			var TotalMin : Number = TotalSecs / 60;			
			
			var min : Number = Math.floor(TotalMin);			
			var second : Number = (TotalMin - min) * 60;
			
			return Math.round(second);					
		}
		
		public function setTimeLimit (min : Number, second : Number) : void {
			var TotalSecs : Number = ((min * 60) + second);
			var TotalMS : Number = TotalSecs * 1000;
			
			var durationMS : Number = Math.abs(TotalMS - dispatchTime);
			var durationSec : Number = durationMS / 1000;
			
			this.timeStepLimit = Math.round(Math.ceil( durationSec / 3)); 
		
		}
		
		public function setDispatchTime (min : Number, second : Number) : void {
			var TotalSecs : Number = ((min * 60) + second);
			var TotalMS : Number = TotalSecs * 1000;
			
			this.dispatchTime = TotalMS;			 		
		}		
		

	}
}