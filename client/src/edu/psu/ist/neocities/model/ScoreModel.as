package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.model.HistoryModel;
	import edu.psu.ist.console.model.SessionModel;
	import edu.psu.ist.neocities.value.ScoreVO;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.NumberBase;

	public class ScoreModel extends ModelFactory
	{
		public function ScoreModel()
		{
			super();
			trace("load ScoreModel");
		}
		
		/****************************************************************
		 * Variables
		 ****************************************************************/
		[Bindable]
		public var data : ArrayCollection = new ArrayCollection();	
		
		[Bindable]
		public var scenarioScore : Number = 0; //sum of the rawScores
		
		[Bindable]
		public var scenarioTotal : Number = 0; //sum of the worstScores
				
		[Bindable]
		public var teamScore : Number = 0; //sum of the normalScores
		
		[Bindable]
		public var teamAverage : Number = 0; //avg of the teamScore
		
		
		/****************************************************************
		 * Functions
		 ****************************************************************/
		
		public function resetScores() : void {
			if(data == null){
				data = new ArrayCollection();
			} else {
				data.removeAll();	
			}
			
			scenarioTotal = 0
			scenarioScore = 0;
			teamScore = 0;
			teamAverage = 0;
		}
		
		
		public function getScoreModel () : String
		{
			var copy : String = "";
			
			var nb : NumberBase = new NumberBase();	
			var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
			
						
			copy += sModel.getSessionHeader() + ",";
			copy += "Team Score" + ",";
			copy += "Total Damage done"  + ",";
			copy += "Total Damage possible"  + ",";
			copy += "Total Events Completed"  + ",";
			copy += "Total Events Failed" + ",";
			copy += "Total Events" + ",";
			copy += "Percent Completed"  + "\n";
			
			
			copy += sModel.getSessionInfo()  + ",";
			copy += nb.formatPrecision(this.teamAverage.toString(), 4) + ",";
			copy += nb.formatPrecision(this.scenarioScore.toString(), 4) + ",";
			copy += nb.formatPrecision(this.scenarioTotal.toString(), 4) + ",";
			copy += this.getEventCount(true) + ",";
			copy += this.getEventCount(false) + ",";
			copy += this.data.length + ",";
			copy += nb.formatPrecision((this.getEventCount(true)/this.data.length).toString(),4) + "\n";
		//	copy += this.getAvgSecondsLate() + "\n";
			
			return copy;
		}
		
		/******************************************************
		 * Helper Functions
		 * ****************************************************/
		
		private function getEventCount(status : Boolean) : int
		{
			var count :int = 0;
			
			for each (var score :ScoreVO in this.data)
			{
				if (score.status == status)
				{
					count++;
				}
			}
			
			return count;
		}
		
		/*private function getAvgSecondsLate() : String
		{
			var hModel :HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel
				
			return hModel.getAverageSecondsLate();
				
		}*/
	}
}