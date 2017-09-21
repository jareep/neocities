package edu.psu.ist.neocities.value
{				
	import mx.collections.ArrayCollection;

	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.PauseVO")]
	public class PauseVO
	{
	
		/****************************************************************
		 * Variables
		 ****************************************************************/
		public var pauseID : int; //primary key
		
		public var msg : String; //this is the information message sent  
		public var link : String; //this is the hyperlink (optional) that can be sent to clients
		
		public var dispatchTime : Number = 0; //Start time from the beginning of the SIM from when to launch the Pause 
		public var pauseDuration : Number = 0; // How long the pause should last
		
		public var introSec : int = 0; // How long the intro should last
		
		public var questionList : ArrayCollection;
		
		public var questionNumber : int = 0;	// which question the person is currently on...
		
		public var isIntro :Boolean = true;
		
		/****************************************************************
		 * Constructors
		 ****************************************************************/
		public function PauseVO() {
				
		}
		
		public function getNextQuestion() : QuestionVO
		{
			if (this.questionList == null || this.questionNumber >= this.questionList.length)
			{
				return null;
			}
			
			var returnQuestion : QuestionVO = this.questionList.getItemAt(this.questionNumber) as QuestionVO;
			questionNumber++;
			return returnQuestion;
			
		}

	}
}