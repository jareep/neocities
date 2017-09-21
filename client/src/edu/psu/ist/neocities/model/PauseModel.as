package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.PauseVO;
		
	public class PauseModel extends ModelFactory
	{
		public function PauseModel()
		{
			super();
			trace("load PauseModel");
		}
		
		[Bindable]
		public var pause : PauseVO;
		
		public function initializeME () : void {
			pause = new PauseVO();
		}		
		
	}
}