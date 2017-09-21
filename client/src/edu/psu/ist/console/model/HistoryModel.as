package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.ActionHistoryVO;
	import edu.psu.ist.console.value.EventHistoryVO;
	import edu.psu.ist.console.value.EventVO;
	import edu.psu.ist.console.value.OrderHistoryVO;
	import edu.psu.ist.console.value.QuestionHistoryVO;
	import edu.psu.ist.neocities.model.RoleModel;
	import edu.psu.ist.neocities.oldModel.IncidentModel;
	import edu.psu.ist.neocities.oldModel.ResourceModel;
	import edu.psu.ist.neocities.oldValue.IncidentVO;
	import edu.psu.ist.neocities.value.ResourceVO;
	import edu.psu.ist.neocities.value.RoleVO;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.NumberBase;
	
	public class HistoryModel extends ModelFactory
	{
		[Bindable]
		public var eventHistory : ArrayCollection;
		
		[Bindable]
		public var actionHistory : ArrayCollection;
		
		[Bindable]
		public var informationHistory : ArrayCollection;
		
		[Bindable]
		public var questionHistory : ArrayCollection;
		
		
		
		
		public function HistoryModel()
		{
			super();
			eventHistory = new ArrayCollection();
			actionHistory = new ArrayCollection();
			informationHistory = new ArrayCollection();
			questionHistory = new ArrayCollection()
			trace("load history model");
		}
		
		public function resetModel() : void
		{
			eventHistory = new ArrayCollection();
			actionHistory = new ArrayCollection();
			informationHistory = new ArrayCollection();
			questionHistory = new ArrayCollection()
		}
		
		public function getEventHistoryVO(incidentID : int) : EventHistoryVO
		{
			
			
			for each(var ehVO : EventHistoryVO in this.eventHistory)
			{
				if (ehVO.eventID == incidentID)
				{
					return ehVO;
				}
			}
			
			return null;
		}
		
		private function getBooleanCode (bool :Boolean) : int
		{
			if (bool) { return 1; }
			
			return 0;
		}
		
		
		public function getActionHistoryVO() : String
		{
			if (actionHistory.length > 0)
			{
				var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
				
				
				var copy : String = "";
				
				copy += sModel.getSessionHeader() + ",";
				copy += "Event" + ",";
				copy += "Eligible" + ",";
				copy += "Location" + ",";
				copy += "Set Priority" + ",";
				copy += "Initial Severity" + ",";
				copy += "Expected Priority" + ",";
				copy += "Role" + ",";
				copy += "Resource" + ",";
				copy += "Number Dispatched" + ",";
				copy += "Number Role Dispatched" + ",";
				copy += "Action" + ",";
				copy += "Event Time" + ",";
				copy += "Action Time" + ",";
				copy += "Reaction Time" + ",";
				copy += "Correct Unit" + ",";
				copy += "Active Events" + "\n";
				
				
				for each (var item : ActionHistoryVO in this.actionHistory)
				{
					copy += sModel.getSessionInfo() + ",";	
					copy += item.eventID + ",";
					copy += item.eligible + ",";
					copy += item.locationID  + ",";
					copy += item.setPriority + ",";
					copy += item.initialSeverity + ",";
					copy += item.currentMagnitude + ",";
					copy += item.roleID + ",";
					copy += item.resourceID + ",";
					copy += item.numDispatch + ",";
					copy += item.numRoleDispatch + ",";
					copy += item.action + ",";
					copy += getTime(item.eventTime) + ",";
					copy += getTime(item.actionTime) + ",";
					copy += getTime(item.reactionTime) + ",";
					copy += item.correct + ",";
					copy += item.numActiveEvents + "\n";
				} 
				
				return copy;	
			}
			
			return "";
		}
		
		public function getTime(time :String) : String
		{
			if (isNaN(Number(time)))
			{
				return time;
			}
			
			return (int(time) * 3).toString();
		}
		
		
		
		public function getEventHistory() : String
		{
			if (this.eventHistory.length > 0)
			{
				var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
				var copy : String = "";
				
				copy += sModel.getSessionHeader() + ",";
				copy += "Event" + ",";
				copy += "Location" + ",";
				copy += "Status"  + ",";
				copy += "Complete Type" + ",";
				copy += "Normal Score"  + ",";
				copy += "Raw Score"  + ",";
				copy += "Worst Score"  + ",";
				copy += "Initial Severity" + ",";
				copy += "Start Time"  + ",";
				copy += "Duration"  + ",";
				copy += "End Time"  + ",";
				copy += "Time Limit"  + ",";
				copy += "Raw Dispatched" + ",";
				copy += "Dispatched Wrong"  + ",";
				copy += "Dispatched Correct"  + "\n";
				
				for each (var item : EventHistoryVO in this.eventHistory)
				{
					copy += sModel.getSessionInfo() + ",";
					copy += item.eventID  + ",";
					copy += item.locationID  + ",";
					copy += item.failComplete + ",";
					copy += item.completeType + ",";
					copy += item.score.normalScore + ",";
					copy += item.score.rawScore + ",";
					copy += item.score.worstScore + ",";
					copy += item.initialSeverity + ",";
					copy += getStringFromNumber(int(item.timeBegin) * 3) + ",";
					copy += getStringFromNumber(int(item.eventDuration) * 3) + ",";
					copy += getStringFromNumber(int(item.timeOver) * 3) + ",";
					copy += getStringFromNumber(int(item.eventTimeLimit) * 3) + ",";
					copy += item.rawDispatch + ",";
					copy += item.dispatchWrong + ",";
					copy += item.dispatchCorrect +"\n";
				}
				
				return copy;
			}
			
			return "";
		}
		
		public function getQuestionHistory() : String
		{
			if (this.questionHistory.length > 0)
			{
				var copy : String = "";
				var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
				
				
				copy += sModel.getSessionHeader() + ",";
				copy += "Question ID,";
				copy += "Answer Value,";
				copy += "Correct,";
				copy += "Role,";
				copy += "Question Time,";
				copy += "Answer Time,";
				copy += "Reaction Time,";
				copy += "Total Utterances" + ",";
				copy += "Total Characters" + ",";
				copy += "Total Words" + ",";
				copy += "Role Utterances" + ",";
				copy += "Role Characters" + ",";
				copy += "Role Words" + "\n";
				
				for each (var item : QuestionHistoryVO in this.questionHistory)
				{
					copy += sModel.getSessionInfo() + ",";
					copy += item.questionId.toString() + ",";
					copy += item.answerValue  + ",";
					copy += item.correct + ",";
					copy += this.getRoleName(item.roleID) + ",";
					copy += item.questionTime.toString() + ",";
					copy += item.answerTime.toString() + ",";
					
					if (item.answerTime != 0){copy += (item.answerTime - item.questionTime).toString() + ",";}
					else {copy+= "-1,"; }
					copy += item.totalUtterances + ",";
					copy += item.totalCharacters + ",";
					copy += item.totalWords + ",";
					copy += item.roleUtterances + ",";
					copy += item.roleCharacters + ",";
					copy += item.roleWords + "\n";		
				}
				
				return copy;
			}
			
			return "";
		}
		
		
		
		/********************************************************
		 * Helper functions for getting history print outs
		 * ******************************************************/
		
		
		
		private function getRoleName (id : int) : String
		{
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var role : RoleVO = pModel.getPlayer(id);
			
			return role.role
		}
		
		private function getResourceName (id : int) : String
		{
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			var resource : ResourceVO = rModel.getResource(id.toString());
			
			return resource.label;
		}
		
		private function getEventName (id : int) : String
		{
			var iModel : IncidentModel = ModelFactory.getModel("IncidentModel") as IncidentModel;
			var incident : IncidentVO = iModel.getIncident(id.toString());
			
			return incident.label
		}
		
		public function getStringFromNumber(num : Number) : String
		{
			return num.toString()
		}
		
		/*public function getAverageSecondsLate() : String
		{
		var nb :NumberBase = new NumberBase();
		var totalSeconds : int = 0;
		var totalNum : int = 0;
		for each(var ah :ActionHistoryVO in this.actionHistory)
		{
		if (ah.secondsLate != "-9999")
		{
		totalSeconds += Number(ah.secondsLate);
		totalNum++;
		}
		}
		
		if (totalNum == 0)
		{
		return "{n/a}";
		}*/
		
		
	}//end of class
}//end of package