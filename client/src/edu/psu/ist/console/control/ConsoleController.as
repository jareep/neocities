package edu.psu.ist.console.control
{
	import com.pnwrain.easyCG.business.RemoteMediator;
	import com.pnwrain.easyCG.business.ServiceFactory;
	import com.pnwrain.easyCG.control.ControllerFactory;
	import com.pnwrain.easyCG.events.ControlEvent;
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.model.*;
	import edu.psu.ist.console.value.*;
	import edu.psu.ist.neocities.model.*;
	import edu.psu.ist.neocities.value.PlayerVO;
	import edu.psu.ist.neocities.value.ScoreVO;
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.messaging.Consumer;
	import mx.messaging.events.MessageEvent;
	import mx.messaging.events.MessageFaultEvent;
	import mx.messaging.messages.AsyncMessage;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	
	public class ConsoleController extends ControllerFactory
	{
		public var consumers : ArrayCollection;  
		
		public function ConsoleController()
		{
			addEventHandler("evt_serverCall", getServerData);
			addEventHandler("evt_scoreEvent", getScoreEvent);
			addEventHandler("evt_subscribeConsole", subscribeData);
			addEventHandler("evt_unsubscribeConsole", unsubscribeData);
			addEventHandler("evt_activeScenario", setActiveScenario);
			addEventHandler("evt_initialize", doInitialize);
			addEventHandler("evt_initClient", doInitClient);
			
			addEventHandler("evt_cc_loadEnvironment",loadEnvironment);
			addEventHandler("evt_cc_loadScenario", loadScenario);
			
			this.consumers = new ArrayCollection();
		}
		
		/****************************************************************
	     * Service Event Handlers
	     ****************************************************************/
		
		// obtains the Simulation Data from the server
		public function getServerData ( event : ControlEvent ) : void {
			trace("CC - Contacting Server...");
			
			switch (event.data as String){
				case "serverStart":
					this.doStartServer();
					//Alert.show("Working");
					//2 seconds pause to give the server time to load, the call a function to DL the scenario data
					
					var startupTimer : Timer = new Timer(2000, 1); 
						startupTimer.addEventListener(TimerEvent.TIMER_COMPLETE, timerComplete);
						startupTimer.start();	
									
					break;
				case "serverStop":  
					this.doStopServer();
					this.resetConsoleData();
					break;
				case "simStart":
					this.doStartSim();
					break;
				case "simPause":
					this.doPauseSim();					
					break;
				case "simResume":
					this.doResumeSim();					
					break;				
			}//end of switch
										
		} 
		
		public function timerComplete( event : TimerEvent) : void {	
			var scenarioModel : ScenarioModel = ModelFactory.getModel("ScenarioModel") as ScenarioModel;
			
			if (scenarioModel.data == null){
				scenarioModel.data = new ArrayCollection();			
			}
			else {
				scenarioModel.data.removeAll();
			}
			
			//doGetScenarioData();		
			//doGetActiveScenario();	
			
			var eventSub : ControlEvent = new ControlEvent('evt_subscribeConsole');
			eventSub.dispatch();
			
						
		}
		
		
		//called by the Score Event View
		public function getScoreEvent ( event : ControlEvent ) : void {
			//var incident : IncidentVO = event.data as IncidentVO;
			
			//doGetScoreEvent(parseInt(incident.incidentID));
			
		}
		
		// subscribe to the server's event feed
		public function subscribeData ( event : ControlEvent ) : void {
			trace("CC - Subscribing to Simulation Feeds...");
			subscribeConsole();
			subscribePlayers();
			
			
			subscribeActionHistory();
			subscribeEventHistory();
			subscribeInformationHistory();
			subscribeScenario();
			subscribeEnvironment();
			
			
			/*subscribeOrderHistory();
			subscribeQuestionHistory();
			
			subscribeTimeSync();	*/		
		}
		
		
		public function unsubscribeData ( event : ControlEvent ) : void {
			for each (var c : Consumer in this.consumers)
			{
				c.unsubscribe();
			}
			
			//Alert.show("Unsubscribed from console");
		}
		
		
		//called by the scenario manager panel, when the user changes the active scenario
		public function setActiveScenario ( event : ControlEvent ) : void {
			var scenario : ScenarioVO = event.data as ScenarioVO;
			
			doSetActiveScenario( scenario.scenarioID );			
		}
		
		public function loadEnvironment (event : ControlEvent) : void {
			var environment : String = event.data as String;
			
			this.doLoadEnvironment(environment);
		}
		
		public function loadScenario (event : ControlEvent) : void {
			var scenario : String = event.data as String;
			
			this.doLoadScenario(scenario);
		}
		
		public function doInitClient(event : ControlEvent ) : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.initClient();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function doInitialize( event : ControlEvent ) : void {
				
			//var event : ControlEvent = new ControlEvent('evt_initComplete');
			var event : ControlEvent = new ControlEvent('evt_sessionInfo');
					event.dispatch();	
		}		
		
		/****************************************************************
	     * Remote Object Functions
	     ****************************************************************/
		
		private function doGetScoreEvent( incidentID : int ): void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getScoreEvent( incidentID );
				token.addResponder(new RemoteMediator (this, handleScoreEvent, onFault));
		}
		
		private function doGetScenarioData(): void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getScenarioData( );
				token.addResponder(new RemoteMediator (this, handleScenarioData, onFault));
		}
		
		private function doLoadEnvironment(environment : String): void {
			
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.loadEnvironmentDataXML(environment);
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function doLoadScenario(scenario : String): void {
			
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.loadScenarioDataXML(scenario);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function doSetActiveScenario( scenarioID : int ): void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.setActiveScenario( scenarioID );
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function doStartServer() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.startServer();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
				
			//after starting the server, fire new event to pull down its data	
			/*	var serverEvent : ControlEvent = new ControlEvent('evt_serverData');
				serverEvent.dispatch();*/				
		}
		
		private function doStopServer() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.stopServer();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));			
		}
		
		private function doStartSim() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.startSim();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
				
			var tlVO :TimelineVO = new TimelineVO()
			tlVO.type = "sim";
			tlVO.message = "scenario start";
			
			var tlEvent : ControlEvent = new ControlEvent('evt_logTimeline');
			tlEvent.data = tlVO;
			tlEvent.dispatch();	
		}
		
		private function doPauseSim() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.pauseSim();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));			
		}
		
		private function doResumeSim() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.resumeSim();
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));			
		}
		
		/*
		private function subscribeTime() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("time") as Consumer;			
			consumer.addEventListener (MessageEvent.MESSAGE, timeManager);
			consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
			consumer.subscribe();
		}*/
		
		
		/****************************************************************
	     * Consumer - Producer Subscription Functions
	     ****************************************************************/
		
		private function subscribeConsole() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("consoleConsumer") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, consoleManager);
				
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				
				consumer.subscribe();
				
			this.consumers.addItem(consumer);
		}
		
		private function onMessageResult(m : MessageEvent) : void
		{
			Alert.show("Gotcha");
		}
		
		private function subscribePlayers() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("player") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, playerManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();
				
				this.consumers.addItem(consumer);
		}
	
		
		public function subscribeActionHistory() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("actionHistory") as Consumer;
				consumer.addEventListener ( MessageEvent.MESSAGE, actionHistoryManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();
				
				this.consumers.addItem(consumer);
		}
		
		public function subscribeEventHistory() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("eventHistory") as Consumer;
				consumer.addEventListener ( MessageEvent.MESSAGE, eventHistoryManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();
				
				this.consumers.addItem(consumer);
		}						
		
		public function subscribeInformationHistory() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("informationHistory") as Consumer;
				consumer.addEventListener ( MessageEvent.MESSAGE, infoHistoryManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();	
				
				this.consumers.addItem(consumer);
		}
		
		public function subscribeScenario() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("scenario") as Consumer;
			consumer.addEventListener ( MessageEvent.MESSAGE, scenarioManager);
			consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
			consumer.subscribe();	
			
			this.consumers.addItem(consumer);
		}
		
		public function subscribeEnvironment() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("environment") as Consumer;
			consumer.addEventListener ( MessageEvent.MESSAGE, environmentManager);
			consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
			consumer.subscribe();	
			
			this.consumers.addItem(consumer);
		}
		
		public function subscribeQuestionHistory() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("questionHistory") as Consumer;
				consumer.addEventListener ( MessageEvent.MESSAGE, questionHistoryManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();	
				
				this.consumers.addItem(consumer);
		}
		
		public function subscribeTimeSync () : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("timeSync") as Consumer;
				consumer.addEventListener (MessageEvent.MESSAGE, timeSyncManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, onMessageFault);
				consumer.subscribe();
				
				this.consumers.addItem(consumer);
		}		
		
		/****************************************************************
	     * Remote Object Result Handlers
	     ****************************************************************/
				
		public function handleScenarioData ( event : ResultEvent ) : void {
			var scenarioModel : ScenarioModel = ModelFactory.getModel("ScenarioModel") as ScenarioModel;
			var scenario : ScenarioVO;
			var scenarioCollection : ArrayCollection;
						
			trace("Event Message: " + event.message);
			scenarioCollection = event.message.body as ArrayCollection;					
								
			for ( var i : int = 0; i < scenarioCollection.length; i++){				
				scenario = scenarioCollection.getItemAt(i) as ScenarioVO;
				//ONR FILTER
				
				
					scenarioModel.data.addItem( scenarioCollection.getItemAt(i) as ScenarioVO );
									
			}// i loop
		}//end of function
		
		public function handleActiveScenario ( event : ResultEvent ) : void {
			var scenarioModel : ScenarioModel = ModelFactory.getModel("ScenarioModel") as ScenarioModel;
			var scenario : ScenarioVO;
									
			trace("Event Message: " + event.message);
			scenario = event.message.body as ScenarioVO;					
			
			scenarioModel.activeScenario = scenario;
			
		}//end of function
		
		public function handleScoreEvent ( event : ResultEvent ) : void {
			var eventModel : EventModel = ModelFactory.getModel("EventModel") as EventModel;
			var eventVO : EventVO;
			
			trace("Event Message: " + event.message);
			
			eventVO = event.message.body as EventVO;
			eventModel.selectedEvent = eventVO;
			
		}
		
		public function voidResponse ( event : ResultEvent ) : void {
			trace ("CC - Void Function Call Complete");
		}
		
		/****************************************************************
	     * Subscription Message Result Handlers
	     ****************************************************************/
		
		//assumes the feed is sending a single incident of type IncidentVO
		public function consoleManager (event : MessageEvent ) : void {			
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var serverString : String = serverMessage.body.toString()
			var mModel : MessageModel = ModelFactory.getModel("MessageModel") as MessageModel;			
			
			if(serverString.substr(0, 2) == "DS"){
				mModel.blazeDS += serverString + "\n";
				mModel.addMessage("BlazeDS", serverString);
			}
			else if (serverString.substr(0,3) == "MSG") {
				mModel.addMessage("Confirmation", serverString);
			}
			else if (serverString.substr(0, 8) == "Resource") {
				mModel.resource += serverString + "\n";
				mModel.addMessage("Resource", serverString);
			}
			else if (serverString.substr(0, 5) == "Score") {
				mModel.score += serverString + "\n";
				mModel.addMessage("Score", serverString);
			}
			else if (serverString.substr(0, 6) == "Answer") {
				mModel.answer += serverString + "\n";
				mModel.addMessage("Answer", serverString);
			}
			else if (serverString.substr(0, 7) == "Current") {
				mModel.timeStep = serverString;
			}
			else if (serverString.substr(0,5) == "ERROR") {
				mModel.error += serverString + "\n";
				mModel.addMessage("ERROR", serverString);
			}
			else {
				
				mModel.addMessage("Misc", serverString);
				mModel.console += serverString + "\n";
			}
					
		}
		
		public function playerManager ( event : MessageEvent) : void
		
		{
			var pModel : PlayerModel = ModelFactory.getModel("PlayerModel") as PlayerModel;
			
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var messageBody : PlayerVO = serverMessage.body as PlayerVO;
			
			pModel.addPlayer(messageBody);
			
			
		}
		
		public function eventHistoryManager ( event : MessageEvent ) : void
		{
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			
			trace ("CC - New Event Messages...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			trace ("serverMessage");
			
			var messageBody : EventHistoryVO = new EventHistoryVO();
			
			messageBody.eventID = event.message.body.eventID;
			messageBody.dispatchCorrect = event.message.body.dispatchCorrect;
			messageBody.dispatchWrong = event.message.body.dispatchWrong;
			messageBody.eventDuration = event.message.body.eventDuration;
			messageBody.eventTimeLimit = event.message.body.eventTimeLimit;
			messageBody.rawDispatch = event.message.body.rawDispatch;
			messageBody.recallCorrect = event.message.body.recallCorrect;
			messageBody.timeBegin = event.message.body.timeBegin;
			messageBody.timeOver = event.message.body.timeOver;
			messageBody.recallWrong = event.message.body.recallWrong;
			
			messageBody.initialSeverity = event.message.body.initialSeverity;
			messageBody.failComplete = event.message.body.failComplete;
			messageBody.locationID = event.message.body.locationID;
			messageBody.completeType = event.message.body.completeType;
			
			messageBody.score = new ScoreVO();
			
			/*messageBody.score.eventID =  event.message.body.score.eventID;
			messageBody.score.dispatchRatio = event.message.body.score.dispatchRatio;
			messageBody.score.eventLabel = event.message.body.score.eventLabel;
			messageBody.score.grade = event.message.body.score.grade;
			messageBody.score.normalScore = event.message.body.score.normalScore;
			messageBody.score.rawScore = event.message.body.score.rawScore;
			messageBody.score.worstScore = event.message.body.score.worstScore;
			*/
	//		messageBody = event.message.body as EventHistoryVO;
			
			messageBody.score = event.message.body.score as ScoreVO;
			
			
			hModel.eventHistory.addItem(messageBody);
		}
		
		public function infoHistoryManager ( event : MessageEvent ) : void
		{
			trace ("CC - New Order Messages...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			trace ("serverMessage");
			var messageBody : InformationHistoryVO = serverMessage.body as InformationHistoryVO;
			
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			hModel.informationHistory.addItem(messageBody);
			
		}
		
		public function questionHistoryManager (event : MessageEvent) : void
		{
			trace ("CC - New Question Messages...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var messageBody :QuestionHistoryVO = serverMessage.body as QuestionHistoryVO;
			
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			var rModel :RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var cModel :ChatModel = ModelFactory.getModel("ChatModel") as ChatModel;
			
			messageBody.totalUtterances = cModel.totalUtterances;
			messageBody.totalWords = cModel.totalWords;
			messageBody.totalCharacters = cModel.totalCharacters;
			
			var pc :PlayerChat = cModel.getPlayerChat(rModel.getPlayer(messageBody.roleID).role);
			
			if (pc != null)
			{
				messageBody.roleCharacters = pc.characterCount;
				messageBody.roleUtterances = pc.utterances;
				messageBody.roleWords = pc.wordCount;
			}
			hModel.questionHistory.addItem(messageBody);
			
			
		}
		
		
		public function actionHistoryManagerOld ( event : MessageEvent ) : void
		{
			trace("CC - New Action Messages...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			trace (serverMessage);
			var messageBody : ArrayCollection = serverMessage.body as ArrayCollection;
			
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			
			var rModel :RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var reModel :ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			//var iModel :IncidentModel = ModelFactory.getModel("IncidentModel") as IncidentModel;
			var cModel :ChatModel = ModelFactory.getModel("ChatModel") as ChatModel;
			
			var tlEvent : ControlEvent;
			var tlVO : TimelineVO;

			for each ( var ah : ActionHistoryVO in messageBody)
			{
				
				//ah.numActiveEvents = iModel.openIncidents.length;
				
			//	ah.totalUtterances = cModel.totalUtterances;
			//	ah.totalWords = cModel.totalWords;
			//	ah.totalCharacters = cModel.totalCharacters;
				
				var pc :PlayerChat = cModel.getPlayerChat(rModel.getPlayer(ah.roleID).role);
						
				if (pc != null)
				{
			//		ah.roleCharacters = pc.characterCount;
		//			ah.roleUtterances = pc.utterances;
		//			ah.roleWords = pc.wordCount;
				}
								
				hModel.actionHistory.addItem(ah);
				
				tlEvent = new ControlEvent('evt_logTimeline');
				
				tlVO = new TimelineVO();
				tlVO.type = ah.action;
				/*
				tlVO.message = (rModel.getPlayer(ah.roleID.toString()).role + " " + ah.action + "ed " + 
							   reModel.getResource(ah.resourceID.toString()).label + " for the event " + 
							   iModel.getIncident(ah.incidentID.toString()).label).replace(",","");
				*/
				tlEvent.data = tlVO;
				tlEvent.dispatch();
				//Alert.show("I am here");
			}
			
		}
		
		
		public function actionHistoryManager ( event : MessageEvent ) : void
		{
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			
			trace ("CC - New Event Messages...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			trace ("serverMessage");
			
			var messageBody : ActionHistoryVO = new ActionHistoryVO();
			
			messageBody.eventID = event.message.body.eventID;
		//	messageBody.eventID =  event.message.body.eventID;
			messageBody.roleID =  event.message.body.roleID;
			messageBody.badgeNo =  event.message.body.badgeNo;
			messageBody.resourceID =  event.message.body.resourceID;
			messageBody.eventTime =  event.message.body.eventTime;
			messageBody.reactionTime =  event.message.body.reactionTime;
			messageBody.actionTime =  event.message.body.actionTime;
			messageBody.correct =  event.message.body.correct;
			messageBody.locationID = event.message.body.locationID;
			messageBody.setPriority =  event.message.body.setPriority;	
			messageBody.action =  event.message.body.action;
			messageBody.currentMagnitude = event.message.body.currentMagnitude;
			messageBody.numActiveEvents = event.message.body.numActiveEvents;
			messageBody.initialSeverity = event.message.body.initialSeverity;
			messageBody.eligible = event.message.body.eligible;
			
			hModel.actionHistory.addItem(messageBody);
		}
		
		public function scenarioManager ( event : MessageEvent ) : void {
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var scenarioName : String = serverMessage.body as String;
			//var changeEvent : ControlEvent;
			
			var sModel :ScenarioModel = ModelFactory.getModel("ScenarioModel") as ScenarioModel;
			sModel.activeScenarioName = scenarioName;
			
			//timeModel.syncTime(int(timeStep));
		} 
		
		public function environmentManager ( event : MessageEvent ) : void {
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var environmentName : String = serverMessage.body as String;
			//var changeEvent : ControlEvent;
			
			var sModel :ScenarioModel = ModelFactory.getModel("ScenarioModel") as ScenarioModel;
			sModel.activeEnvironmentName = environmentName;
			
			//timeModel.syncTime(int(timeStep));
		} 
		
		
		public function timeSyncManager ( event : MessageEvent ) : void {
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var timeStep : Number = serverMessage.body as Number;
			//var changeEvent : ControlEvent;
			
	    	var timeModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
	    	
	    	timeModel.syncTime(int(timeStep));
	    } 
						
		
		/****************************************************************
	     * Fault Event Handlers
	     ****************************************************************/
	     
		public function onFault (event : FaultEvent ) : void {
			trace(event.fault.message.toString());
			
			/*switch (event.fault.faultCode.toString())
			{
				case "Client.Error.Request.Timeout":
					Alert.show("This node has lost Internet Connection");
					break;
				default:
					Alert.show(event.fault.faultString);
			}*/
		}
		
		public function onMessageFault (event : MessageFaultEvent):void	{
			trace(event.faultString);
			
			//Alert.show(event.message.toString());
						
		}
		
		/****************************************************************
	     * Private Data Reset Function
	     ****************************************************************/
				
		private function resetConsoleData () : void {
			var scoreModel : EventModel = ModelFactory.getModel("EventModel") as EventModel;
			
			if(scoreModel.data != null){
				scoreModel.data.removeAll();	
			}
						
			var event : ControlEvent = new ControlEvent('evt_resetData');
				event.dispatch();
		}
		
		
				
	}
}