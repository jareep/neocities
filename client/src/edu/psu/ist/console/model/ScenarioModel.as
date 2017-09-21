package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.ScenarioVO;
	import edu.psu.ist.console.value.SessionVO;
	
	import mx.collections.ArrayCollection;
	
	public class ScenarioModel extends ModelFactory
	{
		public function ScenarioModel()
		{
			super();
			trace("load ScenarioModel");
		}
		
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var activeScenario : ScenarioVO;
		
		[Bindable]
		public var activeScenarioName : String = "";
		
		[Bindable]
		public var activeEnvironmentName : String = "";
		
		[Bindable]
		public var sessionInfo : SessionVO;
	
		
	}//end of class
}//end of package