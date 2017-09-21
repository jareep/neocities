package edu.psu.ist.neocities.control
{
	import com.pnwrain.easyCG.business.RemoteMediator;
	import com.pnwrain.easyCG.business.ServiceFactory;
	import com.pnwrain.easyCG.control.ControllerFactory;
	import com.pnwrain.easyCG.events.ControlEvent;
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.TimelineVO;
	import edu.psu.ist.neocities.model.*;
	import edu.psu.ist.neocities.value.*;
	import edu.psu.ist.neocities.value.dataTypes.BuildingLocation;
	
	import flash.net.FileReference;
	import flash.utils.*;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import mx.messaging.Consumer;
	import mx.messaging.errors.ChannelError;
	import mx.messaging.events.ChannelEvent;
	import mx.messaging.events.MessageEvent;
	import mx.messaging.events.MessageFaultEvent;
	import mx.messaging.messages.AsyncMessage;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;
	
	public class ServiceController extends ControllerFactory
	{
		
		public var numSaves : int = 0; 
		public var consumers :ArrayCollection;
						
		public function ServiceController()
		{			
			addEventHandler("evt_serverData", getServerData);
			addEventHandler("evt_subscribe", subscribeData);
			addEventHandler("evt_unsubscribe", unsubscribeData);
			addEventHandler("evt_resetData", doResetData);
			addEventHandler("evt_scoreData", getScoringData);
			addEventHandler("evt_sendPlayer", doSendPlayer);
			addEventHandler("evt_sendConfMessage", sendConfMessage);
			
			consumers = new ArrayCollection;
		}
		
		/****************************************************************
	     * Service Event Handlers
	     ****************************************************************/
		
		// obtains the Simulation Data from the server
		public function sendConfMessage(event : ControlEvent) : void
		{
			this.confirmMessage(event.data.toString());
		}
		
		public function getServerData ( event : ControlEvent ) : void {
			trace("SC - Contacting Server...");
			
			getPlayerRoles();
			getResources();
			
			getLocations();
			
			/*
			getUnits();
			
			var changeEvent : ControlEvent = new ControlEvent('evt_resetData');
			changeEvent.dispatch();*/		
			
			/*
			*/								
		}
		
		// subscribe to the server's event feed
		public function subscribeData ( event : ControlEvent ) : void {
			trace("SC - Subscribing to Simulation Feeds...");
			var eModel :ErrorModel = ModelFactory.getModel("ErrorModel") as ErrorModel;
			
			//subscribeUnits();
			subscribeTime();
			subscribePlayer();	
			subscribeInformation();
			subscribeLocations();
			subscribePause();
			subscribeTimeSync();
			subscribeSync();
			subscribeScore();
		
			
		}
		
		public function unsubscribeData ( event : ControlEvent ) : void {
			for each (var c : Consumer in this.consumers)
			{
				c.unsubscribe();
			}
			
			//Alert.show("Unsubscribed from NC");
		}
		
		// obtains the Scoring Data from the Server
		public function getScoringData ( event : ControlEvent ) : void {
			trace("SC - Downloading Scoring Data...");
			//getScoreData();	this was screwing things up, so i deleted it...		
			getTeamScore();
			getTeamAverage();
			getScenarioScore();	
			getScenarioTotal();		
		}		
		
		public function doSendPlayer (event : ControlEvent ) : void {
			var player : PlayerVO = event.data as PlayerVO;
			
			sendPlayer(player);
		}
				
		/****************************************************************
	     * Remote Object Functions
	     ****************************************************************/		
		
		private function getPlayerRoles() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getRoles();
				token.addResponder(new RemoteMediator (this, doLoadPlayers, onFault));			
		}
		
		private function getResources() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getResources();
				token.addResponder(new RemoteMediator (this, doLoadResources, onFault));			
		}
		
		private function getLocations() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getLocations();
			token.addResponder(new RemoteMediator (this, doLoadLocations, onFault));	
		}
				
		
		private function getUnits() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getUnits();
				token.addResponder(new RemoteMediator (this, doLoadUnits, onFault));			
		}
		
		private function getScoreData() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getScoreData();
				token.addResponder(new RemoteMediator (this, setScoreData, onFault));	
		}
		
		private function getTeamScore() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getTeamScore();
				token.addResponder(new RemoteMediator (this, setTeamScore, onFault));	
		}
		
		private function getTeamAverage() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getTeamAverage();
				token.addResponder(new RemoteMediator (this, setTeamAverage, onFault));	
		}
		
		private function getScenarioScore() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getScenarioScore();
				token.addResponder(new RemoteMediator (this, setScenarioScore, onFault));	
		}
		
		private function getScenarioTotal() : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getScenarioTotal();
				token.addResponder(new RemoteMediator (this, setScenarioTotal, onFault));	
		}
		
		private function sendPlayer(player : PlayerVO) : void {
			trace("send Player");
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;	
			//Alert.show(player.roleID.toString());
			var token : AsyncToken = ro.addPlayer(player);
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function checkPlayer(player : PlayerVO) : void {
			trace("check Player");
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;	
			var token : AsyncToken = ro.checkPlayer(player);
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}			
				
		/****************************************************************
	     * Consumer - Producer Subscription Functions
	     ****************************************************************/
		
	
		
		private function subscribeLocations() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("locations") as Consumer;			
			consumer.addEventListener (MessageEvent.MESSAGE, locationManager);
			
			consumer.addEventListener (MessageFaultEvent.FAULT, 
				function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
			consumer.subscribe();
			
			consumers.addItem(consumer);
		}
		
		private function subscribeInformation() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("info") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, infoManager); //informationManager
		
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
			
		private function subscribeUnits() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("unit") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, unitManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
		private function subscribeTime() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("time") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, timeManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
		private function subscribePlayer() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("player") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, playerManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
		private function subscribePause() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("pause") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, pauseManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
		
		private function subscribeScore() : void {
			var consumer : Consumer = ServiceFactory.getInstance().getService("score") as Consumer;			
				consumer.addEventListener (MessageEvent.MESSAGE, scoreManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
				
		}
		
		private function subscribeSync() : void {
			trace ("sync subscribe");
			var consumer : Consumer = ServiceFactory.getInstance().getService("syncConsumer") as Consumer;
				consumer.addEventListener (MessageEvent.MESSAGE, syncManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}
		
		private function subscribeTimeSync() : void {
			
			var consumer : Consumer = ServiceFactory.getInstance().getService("timeSync") as Consumer;
				consumer.addEventListener (MessageEvent.MESSAGE, timeSyncManager);
				consumer.addEventListener (MessageFaultEvent.FAULT, 
					function (e : MessageFaultEvent) : void { onMessageFault(e, consumer.destination + " " + consumer.id); });
				consumer.subscribe();
				
				consumers.addItem(consumer);
		}				
		
		
		/****************************************************************
	     * Remote Object Result Handlers
	     ****************************************************************/
	     
	     private function doLoadPlayers( event : ResultEvent ) : void {
			trace("SC - Loading players roles...");						
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;			
				pModel.data = new ArrayCollection();
				
			var serverRoles : ArrayCollection = event.message.body as ArrayCollection;
				
			for (var i : int = 0; i <= serverRoles.length - 1; i++){
				pModel.data.addItem(
					new RoleVO(
						serverRoles.getItemAt(i).roleID,
						serverRoles.getItemAt(i).roleName, 
						serverRoles.getItemAt(i).roleImage)
				);
				trace("Role Added - " +  serverRoles.getItemAt(i).roleID + " " + serverRoles.getItemAt(i).roleName);
			}
			
		}
			
		private function doLoadResources ( event : ResultEvent ) : void {		
			trace("SC - Loading resources...");
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
				rModel.data = new ArrayCollection();
				
			
			
			var serverResources : ArrayCollection = event.message.body as ArrayCollection;
			
			for (var i : int = 0; i <= serverResources.length - 1; i++){
				rModel.data.addItem(
					new ResourceVO(
						serverResources.getItemAt(i).resourceID, 
						serverResources.getItemAt(i).roleID, 
						serverResources.getItemAt(i).resourceName, 
						serverResources.getItemAt(i).total,
						serverResources.getItemAt(i).resourceImage,
						serverResources.getItemAt(i).isAction
					)
					
				);
				
				trace("Resource Added - " +  serverResources.getItemAt(i).resourceID + " " + serverResources.getItemAt(i).resourceName);
			}
			
			var roModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			if (roModel.currentPlayer != null && roModel.currentPlayer.roleID == 0)
			{
				roModel.currentPlayer.resources = rModel.data;
			}
		}
		
		public function doLoadLocations (event : ResultEvent) : void {
			trace("SC - Loading Locations...");
			
			var lModel :LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
				lModel.data = new ArrayCollection();
				
			var serverLocations : ArrayCollection = event.message.body as ArrayCollection;
			var location : LocationVO;
			for (var i :int = 0 ; i < serverLocations.length; i++)
			{
				location = new LocationVO(
					serverLocations.getItemAt(i).id,
					serverLocations.getItemAt(i).name,
					serverLocations.getItemAt(i).type,
					serverLocations.getItemAt(i).data,
					serverLocations.getItemAt(i).locationImage,
					serverLocations.getItemAt(i).connections);
					
				location.feedback = serverLocations.getItemAt(i).feedback;
				lModel.addLocation(location);
					
				trace("Location Added - " + serverLocations.getItemAt(i).id + " " + serverLocations.getItemAt(i).name);
				trace(serverLocations.getItemAt(i).data.buildingAddress + " OR " + serverLocations.getItemAt(i).data.cyberAddress);
			}
			
		}

		public function doLoadUnits( event : ResultEvent ): void {
			trace ("SC - Loading Units....");	
			/*
			var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;
			uModel.data = new ArrayCollection();
			
			var serverUnits : ArrayCollection = event.message.body as ArrayCollection;
			
			for (var i : int = 0; i <= serverUnits.length - 1; i++){
				uModel.data.addItem(
					new UnitVO( 
						serverUnits.getItemAt(i).badgeNo, 
						serverUnits.getItemAt(i).resourceID, 
						serverUnits.getItemAt(i).icon
					) 					
				);
				trace("Unit Added - " +  serverUnits.getItemAt(i).badgeNo);
			}	
			
			var roModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			if (roModel.currentPlayer != null && roModel.currentPlayer.roleID == 0)
			{
				roModel.currentPlayer.units = uModel.data;
			}*/
		}
		
		public function setScoreData ( event : ResultEvent ) : void {
			trace ("SC - Setting Scoring Data");			
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
				scoringModel.data = new ArrayCollection();
				
				scoringModel.data = event.message.body as ArrayCollection;
		}
		
		public function setTeamScore ( event : ResultEvent ) : void {
			trace ("SC - Setting Team Score");
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
			
				scoringModel.teamScore = event.message.body as Number;
		}
		
		public function setTeamAverage ( event : ResultEvent ) : void {
			trace ("SC - Setting Team Average");
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
			
				scoringModel.teamAverage = event.message.body as Number;
		}
		
		public function setScenarioScore ( event : ResultEvent ) : void {
			trace ("SC - Setting Scenario Score");
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
			
				scoringModel.scenarioScore = event.message.body as Number;			
		}
		public function setScenarioTotal ( event : ResultEvent ) : void {
			trace ("SC - Setting Scenario Score");
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
			
				scoringModel.scenarioTotal = event.message.body as Number;
				
				//as the last to be called, you should now be safe to switch to the summary view
				var changeEvent : ControlEvent = new ControlEvent('evt_endGame');
				//changeEvent.data = "Summary";
				changeEvent.dispatch();				
		}
				
		public function voidResponse ( event : ResultEvent ) : void {
			trace ("SC - Function Call Complete");
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
		}
		
		/****************************************************************
	     * Subscription Message Result Handlers
	     ****************************************************************/
		
		
		public function infoManager(event : MessageEvent) : void
		{
			
			confirmMessage("Processing Information");
			
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			//Alert.show(serverMessage.body.type);
			
			var lModel :LocationModel = ModelFactory.getModel("LocationModel") as LocationModel
			var iModel :InformationModel = ModelFactory.getModel("InformationModel") as InformationModel;
			var pModel : PlayerModel = ModelFactory.getModel("PlayerModel") as PlayerModel;
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			if (iModel.data == null){
				iModel.data = new ArrayCollection();
			}
			
			// check permission of information
			
			if (rModel.currentPlayer != null && (rModel.currentPlayer.roleID == serverMessage.body.roleID || serverMessage.body.roleID == -1))
				{
					var information : InformationVO = new InformationVO(serverMessage.body.id, serverMessage.body.type, serverMessage.body.roleID, serverMessage.body.data);
					
					information.setEventID(serverMessage.body.eventID);
					information.setLocationID(serverMessage.body.locationID);
					
					iModel.addInformation(information);
					lModel.addInfoToLocation(information.locationID);
					
					var cEvent :ControlEvent = new ControlEvent("evt_mc_updateinfo");
					cEvent.dispatch();
					
					confirmMessage("New Information Recieved: " + information.id + " Permission: " + information.roleID + " at "+ tModel.simTime);
				}
		}
		
		public function locationManager(event : MessageEvent) : void
		{
			
			trace("SC - Managing Location Feed...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			
			if (lModel.data == null) {
				lModel.data = new ArrayCollection();
			}
			
			//var tlVO : TimelineVO = new TimelineVO();
			confirmMessage("Location message recieved");
			// if location already exists, then update it with new information
			
			if (lModel.isLocation(serverMessage.body.id))
			{
				
				lModel.setLocationEvents(serverMessage.body.id, serverMessage.body.activeEvents);
				lModel.setLocationFeedback(serverMessage.body.id, serverMessage.body.feedback);
				lModel.setLocationSeverity(serverMessage.body.id, serverMessage.body.avgSeverity);
								
				confirmMessage("Updated Location Message Recieved... (location: " + serverMessage.body.name + 
					", Active Events " + serverMessage.body.activeEvents + ", feedback: " + serverMessage.body.feedback.length + 
					") at " + tModel.simTime); //+ 
			}
			else
			{
				var location : LocationVO = serverMessage.body as LocationVO;
				
				lModel.addLocation(location);
				
				confirmMessage("New Location Message Recieved... (location: " + serverMessage.body.name + 
					", Active Events " + serverMessage.body.activeEvents + ", feedback: " + serverMessage.body.feedback.length + 
					") at "+ tModel.simTime);
			}
		}
		
		//assumes the feed is sending a single incident of type IncidentVO
		
		//assumes the Feed is sending an array of Units of type UnitVO	
		public function unitManager (event : MessageEvent ) : void {
			
			/*
			trace("SC - Managing Unit Feed...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;			
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			
			//just in case, uModel is empty, create a new one
			if (uModel.data == null){
				uModel.data = new ArrayCollection();
			}
			
			var serverUnits : ArrayCollection = serverMessage.body as ArrayCollection;
			var updateUnits : Boolean = false;
			//loop over the array of units
			for (var i : int = 0; i <= serverUnits.length - 1; i++){
						
				if (uModel.isUnit(serverUnits.getItemAt(i).badgeNo)) {
				// if unit already exists, then update it with the new information			
					trace("    True - Unit " + serverUnits.getItemAt(i).badgeNo);
					uModel.updateUnit(
						serverUnits.getItemAt(i).badgeNo as uint, 
						serverUnits.getItemAt(i).eventID,
						serverUnits.getItemAt(i).locationID,
						serverUnits.getItemAt(i).status, 
						serverUnits.getItemAt(i).message, 
						serverUnits.getItemAt(i).available
					);
					
					confirmMessage("Update Unit Message Recieved... (Unit No:" + serverUnits.getItemAt(i).badgeNo  
									+ ", Event ID: " + serverUnits.getItemAt(i).eventID + ", " 
									+ serverUnits.getItemAt(i).status + ") at " + tModel.simTime);														
				} else {	
				//else, add the resource to rModel data array												
					trace("    False - Unit " + serverUnits.getItemAt(i).badgeNo);
					uModel.data.addItem(
						new UnitVO( 
						serverUnits.getItemAt(i).badgeNo, 
						serverUnits.getItemAt(i).resourceID, 
						serverUnits.getItemAt(i).icon)
					);
					
					confirmMessage("New Unit Message Recieved... (Unit No:" + serverUnits.getItemAt(i).badgeNo + 
									") at " + tModel.simTime);
				}
				
				if (uModel.isUnitOfPlayer(serverUnits.getItemAt(i).badgeNo))
				{			
					updateUnits = true;
				}
			}//end of loop
			
			if (updateUnits)
			{
				var event2 : ControlEvent = new ControlEvent('evt_updateResources');
					
					event2.dispatch();
			}
			*/		
		}//end of unitManager		
		
		public function timeManager (event : MessageEvent ) : void {
			trace("SC - Managing Time Feed...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			
			if (serverMessage.body as String == "start"){
				trace("Start the Simulation Clock!");
				var timerEvent : ControlEvent = new ControlEvent('evt_startTimer');
					timerEvent.data = "Start the Simulation Clock!";
					timerEvent.dispatch();		
			}
			else if (serverMessage.body as String == "stop"){
				trace("Stop the Simulation Clock!");
				var stopEvent : ControlEvent = new ControlEvent('evt_stopTimer');
					stopEvent.data = "Stop the Simulation Clock!";
					stopEvent.dispatch();
				
				var scoreEvent : ControlEvent = new ControlEvent('evt_scoreData');
					scoreEvent.data = "Downloading the Scoring Data!";
					scoreEvent.dispatch();			
			}
			
					
			confirmMessage("Time Message Recieved... (Time: " + serverMessage.body.toString() + ")");
		}//end of timeManager
		
		//assumes the Feed is sending an array of Resources of type ResourceVO
		public function playerManager (event : MessageEvent ) : void {
			trace("SC - Managing Player Feed...");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;			
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			
			//just in case, resourceModel is empty, create a new one
			if (rModel.data == null){
				rModel.data = new ArrayCollection();
			}
												
			//loop over the array of resources
			for (var i : int = 0; i <= rModel.data.length - 1; i++){
			
				if (rModel.data.getItemAt(i).roleID == serverMessage.body.roleID){
					rModel.data.getItemAt(i).incidentID = serverMessage.body.selectedIncidentID;
					
					if ((rModel.data.getItemAt(i) as RoleVO).userName == "No User")
					{
						rModel.data.getItemAt(i).userName = serverMessage.body.userName;
						confirmMessage("Player Username set: " + serverMessage.body.userName);
					}
				}
			
			}//end of loop
		
			confirmMessage("Player Message Recieved... (Role ID: " + serverMessage.body.roleID + 
							", Incident ID: " +  serverMessage.body.selectedIncidentID + ") at "
							+ tModel.simTime);
		}//end of playerManager
		
		//assumes the Feed is sending an item of type PauseVO
		public function pauseManager (event : MessageEvent ) : void {			
			trace("SC - Managing Pause Feed...");
			
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var pauseModel : PauseModel = ModelFactory.getModel("PauseModel") as PauseModel;
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			var pause : PauseVO = serverMessage.body as PauseVO;	
				pauseModel.pause = pause;
				
			//on receipt of the pause, the simulation will switch to the paused view state
			var changeEvent : ControlEvent = new ControlEvent('evt_pauseAlert');
				//changeEvent.data = "Pause";
				changeEvent.dispatch();																
			
			confirmMessage("Pause Message Recieved... (Pause At:" + pause.dispatchTime + ", Duration: " 
							+ pause.pauseDuration + ") at " + tModel.simTime);
		}//end of pauseManager
		

		
		public function scoreManager (event : MessageEvent ) : void {
			trace ("SC - Adding Scoring Data");			
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			var score : ScoreVO = serverMessage.body as ScoreVO
			
			var scoringModel : ScoreModel = ModelFactory.getModel("ScoreModel") as ScoreModel;
				
			if (scoringModel.data == null){
				scoringModel.data = new ArrayCollection();
			}	
				scoringModel.data.addItem(score);
			
			confirmMessage("Score Message Recieved... (Event ID: " + score.eventID+ ", Raw Score: " + 
							score.rawScore + ")");
		}
		
		public function syncManager ( event : MessageEvent ) : void {
			trace("Sync Manager Is here");
			var serverMessage : AsyncMessage = event.message as AsyncMessage;
			var message : String = serverMessage.body.toString().toLowerCase();
			var changeEvent : ControlEvent;
			var eventSet : Boolean = false;
			
			
			switch (message)
			{
				case "login":
					changeEvent = new ControlEvent('evt_enableLogin');
					changeEvent.data = "force";
					eventSet = true;
					break;
				case "loggingin":
					// this is so if they get a login message due to somebody else logging in late they are not redirected to the login screen...
					changeEvent = new ControlEvent('evt_enableLogin');
					changeEvent.data = "no-force";
					eventSet = true;
					break;
				case "running":
					changeEvent = new ControlEvent('alert');
					changeEvent.data = "A NeoCities Instance may already be Running, please notify your researcher";
					eventSet = true;
					break;
			}
			
			if (eventSet == true)
			{
				changeEvent.dispatch();
				confirmMessage("Sync Message Recieved.... (" + message + ")");
			}
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
			
			switch (event.fault.faultCode.toString())
			{
				case "Client.Error.Request.Timeout":
					Alert.show("This node has lost Internet Connection");
					break;
				default:
					Alert.show("Error: " + event.fault.faultString);
			}
			
		}
		
		public function onMessageFault (event : MessageFaultEvent, msgHome :String):void	{
			trace(event.faultString);
			
			var eModel : ErrorModel = ModelFactory.getModel("ErrorModel") as ErrorModel;
			eModel.errors.addItem(msgHome + ":\n" + event.message.toString() + "\nEvent Details:" + event.toString());
			
			Alert.show(msgHome + ": There has been an error detected on your computer... Please notify your researcher....","Save", Alert.OK,null,this.saveData);
			//Alert.show(event.message.toString());
		}
		/*
		public function onMessageFault (event : MessageFaultEvent):void	{
			trace(event.faultString);
			var eModel : ErrorModel = ModelFactory.getModel("ErrorModel") as ErrorModel;
			eModel.errors.addItem(event.message.toString());
			
			Alert.show("There has been an error detected on your computer... Please notify your researcher....","Save", Alert.OK,null,this.saveData);
			//Alert.show(event.message.toString());
					
		}*/
		
		public function saveData (event:CloseEvent) : void
		{
			this.numSaves ++;
			var eModel : ErrorModel = ModelFactory.getModel("ErrorModel") as ErrorModel;
			var name : String = "error" + this.numSaves + ".txt";
			var fr :FileReference = new FileReference();
			fr.save(eModel.toString(), name);
			
				
		}
		
		private function doResetData ( event : ControlEvent) : void {
			// get all of the models
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			var rolModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			//var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;
			var pModel : PlayerModel = ModelFactory.getModel("PlayerModel") as PlayerModel;
				
			trace("...Clearing Data");	
			
			// clear the data
			if(rModel.data != null) { rModel.data.removeAll(); }										
			if (rolModel.data != null) { rolModel.data.removeAll(); }
			//if (uModel.data != null) { uModel.data.removeAll(); }	
			if (pModel.players != null) { pModel.clearModel(); }					

			//get new data from the server
			//this.getServerData(event);
		}	
				
		private function confirmMessage(msg : String) : void
		{
			var rolModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;
			var token : AsyncToken;
			
			if (rolModel.currentPlayer == null)
			{				
				token = ro.messageRecieved("NoUSR", msg.toString());
				token.addResponder(new RemoteMediator (this, voidResponse, onFault));
			}
			else
			{
					if (rolModel.currentPlayer.role.toString() != "Admin")
					{
						token = ro.messageRecieved(rolModel.currentPlayer.role.toString(), msg.toString());
						token.addResponder(new RemoteMediator (this, voidResponse, onFault));
					}
				
			}
			
			
		}
	}
}