package edu.psu.ist.console.value
{	
	import mx.collections.ArrayCollection;
				
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.MessageVO")]
	public class MessageVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
				
		public var date : Date;
		public var msgType : String;
		public var msgText : String;
		
				
	   /****************************************************************
		 * Constructors
		 ****************************************************************/
		public function MessageVO() {
				
		}
		
			
		/****************************************************************
		 * Functions
		 ****************************************************************/
		
		public function setMessage (_date : Date, _msgType : String, _msgText : String) : void
		{
			this.date = _date;
			this.msgText = _msgText;
			this.msgType = _msgType;
		}

		public function getDate() : Date
		{
			return this.date;
		}	
		
		public function getMsgType() : String
		{
			return this.msgType;
		}
		
		public function toCSVString() : String
		{
			var time : String = this.date.toLocaleTimeString();
			
			return time + "," + msgType + "," + msgText + "\n";
		}
		
		public function toString() : String
		{
			var time : String = this.date.toLocaleTimeString();
			
			return time + " [" + msgType + "]: " + msgText + "\n";
		}
			

	}
}