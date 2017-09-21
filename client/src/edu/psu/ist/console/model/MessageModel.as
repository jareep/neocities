package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.MessageVO;
	
	import mx.collections.ArrayCollection;
				
	public class MessageModel extends ModelFactory
	{
		public function MessageModel()
		{
			super();
			trace("load MessageModel");
		}
		
		[Bindable]
		public var console : String = '';
		
		[Bindable]
		public var blazeDS : String = '';
		
		[Bindable]
		public var resource : String = '';
		
		[Bindable]
		public var score : String = '';
		
		[Bindable]
		public var answer : String = '';
		
		[Bindable]
		public var timeStep : String = '';
		
		[Bindable]
		public var error : String = '';
		
		[Bindable]
		public var errors : Boolean = false;
		
		private var typeFilter : ArrayCollection = new ArrayCollection();
		
		public var messageArray : ArrayCollection = new ArrayCollection();
		
		public var unilteredArray : ArrayCollection = new ArrayCollection();
		
		public function addMessage(_msgType : String, _msgText : String) : void
		{
			var date : Date = new Date();
 
			var message : MessageVO = new MessageVO();
			
			message.setMessage(date, _msgType, _msgText);
					
			messageArray.addItem(message);
			
			if (_msgType == "ERROR")
			{
				this.errors = true;
			}
									
		}
		
		
		public function toggleFilter(_typeFilter : ArrayCollection) : void
		{
			typeFilter = _typeFilter;
			//filteredArray = messageArray;
	
			messageArray.filterFunction = null;
			messageArray.refresh();
			
			messageArray.filterFunction = filterTypes;
			messageArray.refresh();
				 
		}
		
		private function filterTypes(item:Object) : Boolean
		{
			var message : MessageVO = item as MessageVO;
						
			
			for each( var filterItem:Object in typeFilter)
			{
			
				if (message.getMsgType().toLowerCase() == filterItem.toString().toLowerCase())
				{
					return true;
				}
			}
			
			return false;
		}
		
		public function clear() : void {
			console = '';
			blazeDS = '';
			resource = '';
			score = '';
			answer = '';
			timeStep = "Current TimeStep: 0";		
		}
		
		public function toCSVString() : String
		{
			messageArray.filterFunction = null;
			messageArray.refresh();
			
			var csv : String = new String();
			
			csv+= "time,msgType,message\n";
			
			for each(var msg : MessageVO in messageArray)
			{
				csv+= msg.toCSVString();
			}
			
			return csv;
			
		}
		
		public function toString() : String
		{
			messageArray.filterFunction = null;
			messageArray.refresh();
			
			var output : String = new String();
			
			for each(var msg : MessageVO in messageArray)
			{
				output+= msg.toCSVString();
			}
			
			return output;
		}
		
		
	}//end of class
}//end of package