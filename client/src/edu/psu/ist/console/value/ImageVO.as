package edu.psu.ist.console.value
{	
	import mx.collections.ArrayCollection;
				
	[Bindable]	
	public class ImageVO
	{		
	   /****************************************************************
	 	* Variables
	 	****************************************************************/
				
		public var label : String;
		public var source : String;
		public var filename : String;
		public var embed : Class; //unusable currently
				
	   /****************************************************************
	 	* Constructors
	 	****************************************************************/
		
		//example row = 1, 2, 4
		public function ImageVO( _label : String, _source : String, _filename : String)
		{
			label = _label;
			source = _source;
			filename = _filename;
		}

	}
}