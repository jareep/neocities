package edu.psu.ist.neocities.model
{
	
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.value.*;
	
	import mx.collections.ArrayCollection;
	
	
	public class InformationModel extends ModelFactory
	{
		
		[Bindable]
		public var data : ArrayCollection = new ArrayCollection();
		
		[Bindable]
		public var numInfo : int = 0;
		
		public function InformationModel()
		{
			super();
			trace("load InformationModel");
		}
		
		public function addInformation(info : InformationVO) : void
		{
			if (data == null)
			{
				data = new ArrayCollection();
			}
			this.numInfo++;
			this.data.addItem(info);
		}
		
		public function removeInformation(id : int) : void
		{
			var info : InformationVO;
			for (var i : int = 0; i < data.length; i++)
			{
				info = data.getItemAt(i) as InformationVO;
				
				if (info.id == id)
				{
					this.numInfo--;
					data.removeItemAt(i);	
				}
			}
		}
		
		public function getInformation(id : int) : InformationVO
		{
			for each (var info : InformationVO in data)
			{
				if (info.id==id)
				{
					return info;
				}
				
			}
			return null;
		}
		
		public function printCurrentInformation() : String{
			
			var informationString : String  = "";
			
			for each (var info : InformationVO in this.data){
				
				informationString += info.id + " " + info.type + "\n";
			
			} // ending for
			
			return informationString;
			
		}// printCurrentInformation()
		
		public function getSelectedInformation() : ArrayCollection
		{
			var selectedInfo :ArrayCollection = new ArrayCollection();
			
			for each (var info : InformationVO in data)
			{
				if (info.selected)
				{
					selectedInfo.addItem(info)
				}
			}
			
			return selectedInfo;
		}
		
		public function getSelectedInfoIDs() : Array
		{
			var infoList : Array = new Array();
			
			for each (var info :InformationVO in data)
			{
				if (info.selected == true)
				{
					infoList.push(info.id);
				}
			}
			
			return infoList;
		}
		
		public function clearSelectedInformation() : void {
			for each (var info : InformationVO in data)
			{
				info.selected = false;
			}
		}
	}
}