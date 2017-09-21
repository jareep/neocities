package edu.psu.ist.console.value
{	
	import mx.collections.ArrayCollection;
				
	[Bindable]	
	[RemoteClass(alias="edu.psu.ist.neocities.value.ScenarioVO")]
	public class ScenarioVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
				
		public var scenarioID : int; //primary key
		public var label : String;
		 
		public var incidents : Array;
		public var length : int = 0;
				
	   /****************************************************************
		 * Constructors
		 ****************************************************************/
		public function ScenarioVO() {
				
		}
		
			
		/****************************************************************
		 * Functions
		 ****************************************************************/
		
		public function addIncident(incident : int) : void {
			 incidents.push(incident);		
		}
		
		public function getIncidents() : Array{
			return incidents;
		}

	}
}