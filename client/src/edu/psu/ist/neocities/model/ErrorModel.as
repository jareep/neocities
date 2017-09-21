package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.oldValue.BriefingVO;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.messaging.events.MessageFaultEvent;
	
	import spark.components.VGroup;
	
	public class ErrorModel extends ModelFactory
	{
	
		[Bindable]
		public var errors : ArrayCollection;
		
		[Bindable]
		public var haveErrors :Boolean;
	
		
		public function ErrorModel()
		{
			haveErrors = false;	
			errors = new ArrayCollection();
		}
		
		
		
		public function toString() : String
		{
			var ts : String = ""
			
			for each(var error : String in errors)
			{
				ts += error + "\n\n\n\n";
			}
				
			return ts;
		}
	}
}