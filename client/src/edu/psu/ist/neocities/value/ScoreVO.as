package edu.psu.ist.neocities.value
{
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.ScoreVO")]
	public class ScoreVO
	{			
		/****************************************************************
	 	* Variables
	 	****************************************************************/
		
		public var eventID : int; //foreign key to incident Model
		public var locationID : int;
		public var eventLabel : String;
		
		public var status : Boolean;
		
		public var rawScore : Number = 0; //raw un-weighted score
		public var worstScore : Number = 0; //un weighted worst possible action area;
		public var normalScore : Number = 0; //relative normalized weighted score		
		public var grade : String = "F"; // a semantic grade"A - F" based on the normalScore
		
		public var dispatchRatio : Number = 0;
				
	   /****************************************************************
	 	* Constructors
	 	****************************************************************/
		
		public function ScoreVO() {
			
		}
		
	}
}