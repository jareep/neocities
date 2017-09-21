package edu.psu.ist.neocities.oldModel
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.oldValue.BriefingVO;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	import spark.components.VGroup;

	public class BriefingModel extends ModelFactory
	{
		
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var curBriefing : int = -1;
		
		[Bindable]
		public var isInit : Boolean = false;
		
		public function BriefingModel()
		{
			super();
		}
		
		public function addBriefing(briefing : BriefingVO) : void
		{
			briefing.setBriefingView();
			data.addItem(briefing);
			this.isInit = true;
		}
		
		public function getBriefing(ID : int) : BriefingVO
		{
			for (var i : int = 0; i < data.length; i++)
			{
				if ((data.getItemAt(i) as BriefingVO).briefingID == ID)
				{
					return data.getItemAt(i) as BriefingVO;
				}
			}
			
			return null;
		}
		
		public function getBriefingAt(i : int) : BriefingVO
		{
			if (i < 0 || i > data.length)
			{
				
				return null
			}
			
			
			return data.getItemAt(i) as BriefingVO;
		}
		
		public function getNextBriefingView() : VGroup
		{
			curBriefing++;
			
			
			
			if (curBriefing != 0) {		
				curBriefing = curBriefing%data.length;
			}
			
			//Alert.show(curBriefing.toString());
			//Alert.show(this.getBriefing(curBriefing).briefing);
									
			return this.getBriefingAt(curBriefing).briefingGroup;
		}
				
		public function getPreviousBriefingView() : VGroup
		{
			curBriefing--;
			
			if (curBriefing != 0) {		
				curBriefing = curBriefing%data.length;
			}
			
			if (curBriefing == -1)
			{
				curBriefing = data.length-1;
			}
			
			return this.getBriefingAt(curBriefing).briefingGroup;
		}
		
		public function getMostCurrentBriefingAlert () : String
		{
			return this.getBriefingAt(data.length-1).alert;
		}
		
		public function getMostCurrentBriefingView() : VGroup
		{
			curBriefing = data.length - 1;
			
			return this.getBriefingAt(curBriefing).briefingGroup;
		}
	}
}