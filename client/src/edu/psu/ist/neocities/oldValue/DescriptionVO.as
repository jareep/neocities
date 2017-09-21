package edu.psu.ist.neocities.oldValue
{
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.DescriptionVO")]
	public class DescriptionVO
	{			
		/****************************************************************
	 	* Variables
	 	****************************************************************/
		
		public var incidentID : int; //foreign key to incident Model
		public var permission: int;
		public var description : String;
		
				
	   /****************************************************************
	 	* Constructors
	 	****************************************************************/
		
		public function DescriptionVO() {
			
		}
		
	}
}