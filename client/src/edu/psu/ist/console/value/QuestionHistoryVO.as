package edu.psu.ist.console.value
{
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.QuestionHistoryVO")]
	public class QuestionHistoryVO
	{
		
		/****************************************************************
		 * Variables
		 ****************************************************************/
		
		public var questionId:int;
		public var answerValue:String = "{n/a}";
		
		public var roleID:int;
		
		public var questionTime:int;
		public var answerTime:int;
		
		public var correct:int; 
		
		public var totalUtterances : int = 0;	// number of utterances at the time of the action
		public var totalCharacters : int = 0;	// total characters at the time of the action
		public var totalWords : int = 0;		// total words at the tmie of the action
		
		public var roleUtterances : int = 0;	// number of utterances by that player at the time of the action
		public var roleCharacters : int = 0;	// number of characters by that player at the time of the action
		public var roleWords : int = 0;			// number of words by that player at the time of the action
		
		/****************************************************************
		 * Constructors
		 ****************************************************************/
		public function QuestionHistoryVO()
		{
		}
	}
}