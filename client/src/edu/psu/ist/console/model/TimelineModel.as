package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.TimelineVO;
	
	import mx.collections.ArrayCollection;

	public class TimelineModel extends ModelFactory
	{
		public function TimelineModel()
		{
			super();
			trace("load EventModel");
		}
		
		[Bindable]
		public var timeline : ArrayCollection = new ArrayCollection();
		
		
		public function resetModel () : void
		{
			this.timeline = new ArrayCollection();
		}
		
		public function addTimelineEntry(time : int, type : String, message : String) : void
		{
			var timelineVO : TimelineVO = new TimelineVO()
			timelineVO.time = time;
			timelineVO.type = type;
			timelineVO.message = message;
			
			timeline.addItem(timelineVO);
		}
		
		public function getTimelineModel () : String
		{
			var rs : String = "";
			var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
						
			rs += sModel.getSessionHeader() + ",";
			rs += "time,type,message\n";
			
			for each (var t : TimelineVO in this.timeline)
			{
				rs += sModel.getSessionInfo() + ",";
				rs += t.time + ",";
				rs += t.type + ",";
				rs += t.message.replace(",", " ") + "\n";
			}
			
			return rs;
		}
	}
}