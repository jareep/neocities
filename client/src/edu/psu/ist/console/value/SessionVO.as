package edu.psu.ist.console.value
{
	[Bindable]
	public class SessionVO
	{
		/****************************************************************
		 * Variables
		 ****************************************************************/
		public var researcher : String;
		public var startTime : String;
		public var location : String;
		public var teamID : String;
		public var division : String;
		public var date : String;
		public var scenario : String;
		public var environment : String;
		public var condition : String;
		
		public var debug : Boolean = false;
		
		public function SessionVO()
		{
		}
	}
}