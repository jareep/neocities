package edu.psu.ist.console.value
{
	public class TimelineVO
	{
		public var time : int; // in seconds
		public var type : String; // what type of message it is, i.e. a chat, pause, brief, etc.
		public var message : String;	// what actually happened
								 
		
		public function TimelineVO()
		{
		}
	}
}