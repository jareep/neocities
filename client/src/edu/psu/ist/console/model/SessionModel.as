package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.ScenarioVO;
	import edu.psu.ist.console.value.SessionVO;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.formatters.DateFormatter;
	
	public class SessionModel extends ModelFactory
	{
		public function SessionModel()
		{
			super();
			trace("load SessionModel");
		}
		
			
		[Bindable]
		public var sessionInfo : SessionVO = new SessionVO();
		
		public function clearModel() : void
		{
			sessionInfo = new SessionVO();
		}
		
		public function getSessionHeader() : String
		{
			return "Participant ID,Researcher,Date,Session Time,Scenario,Environment"
		}
		
		public function getSessionInfo() : String
		{
			/*return sessionInfo.teamID + "," + sessionInfo.researcher + "," + sessionInfo.date + "," + sessionInfo.startTime
				+ "," + sessionInfo.location + "," + sessionInfo.division + "," + sessionInfo.scenario + "," + sessionInfo.condition;*/
			
			return sessionInfo.teamID + "," + sessionInfo.researcher + "," + sessionInfo.date + "," + sessionInfo.startTime
			+ "," + sessionInfo.scenario + "," + sessionInfo.environment;
		}
		
		public function setAsDebug() : void
		{
			
			var df :DateFormatter = new DateFormatter();
			df.formatString = "MM-DD-YYYY";
			
			sessionInfo.debug = true;
			sessionInfo.researcher = "DEBUG";
			sessionInfo.location = "DBG";
			sessionInfo.division = "99";
			sessionInfo.startTime = "10:55 PM";
			sessionInfo.date = df.format(new Date());
			sessionInfo.teamID = "27";
			sessionInfo.condition = "DEBUG";
		}
		
		public function populateSessionInfo(r : String, l : String, d : String, st : String, t : String, sc :String, c :String, e:String)
		{
			
			var df :DateFormatter = new DateFormatter();
			
			df.formatString = "MM-DD-YYYY";
			
			this.sessionInfo.date = df.format(new Date());
			this.sessionInfo.researcher = r;
			this.sessionInfo.location = l;
			this.sessionInfo.division = d;
			this.sessionInfo.startTime = st;
			this.sessionInfo.teamID = t;
			this.sessionInfo.scenario = sc;
			this.sessionInfo.environment = e;
			this.sessionInfo.condition = c;
			
			
		}
		
		
		
		
	}//end of class
}//end of package