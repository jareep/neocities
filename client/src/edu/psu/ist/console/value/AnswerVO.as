package edu.psu.ist.console.value
{	
	import mx.collections.ArrayCollection;
				
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.AnswerVO")]
	public class AnswerVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
		
		public var difficultyID : int; //foreign key to difficulty Model
		public var resourceID : int; //foreign key to resourceID Model
		
		//Only used when EventVO.Difficulty.UnitCapBound = True;
		public var expected : int; //number of units of this resource that need to be sent.
		public var allocated : int; //number of units of this resource that are currently allocated.
				
	   /****************************************************************
	 	* Constructors
	 	****************************************************************/
		
		public function AnswerVO() {
			this.difficultyID = 1;
			this.resourceID = 1;
			this.expected = 1;
			this.allocated = 0;
		}
		
		/****************************************************************
	 	* Functions
	 	****************************************************************/
	 	
	 	public function getExpectedLabel() : String {
	 		
	 		if (expected == 0){
	 			return "Any";
	 		}
	 		else {
	 			return expected.toString() + " Max";  
	 		}
	 	}

	}
}