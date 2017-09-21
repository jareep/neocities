package edu.psu.ist.neocities.control
{
	import com.pnwrain.easyCG.business.RemoteMediator;
	import com.pnwrain.easyCG.business.ServiceFactory;
	import com.pnwrain.easyCG.control.ControllerFactory;
	import com.pnwrain.easyCG.events.ControlEvent;
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.model.ChatModel;
	import edu.psu.ist.console.value.PlayerChat;
	import edu.psu.ist.console.value.QuestionHistoryVO;
	import edu.psu.ist.console.value.TimelineVO;
	import edu.psu.ist.neocities.model.*;
	import edu.psu.ist.neocities.oldValue.*;
	import edu.psu.ist.neocities.value.*;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class MainController extends ControllerFactory
	{
		public function MainController()
		{
			addEventHandler("evt_authenticate", doAuthenticate);
			addEventHandler("evt_dispatch", doDispatch);

			addEventHandler("evt_getSysState", doGetSystemState);
			addEventHandler("evt_sendQuestionAnswer", doSendQuestionAnswer);
			addEventHandler("evt_mc_removeInformation", doRemoveInformation);
			addEventHandler("evt_mc_clearSelectedInfo", doClearSelectedInfo);
			
			addEventHandler("alert", doAlert);
			addEventHandler("changeStateRequest", requestChangeState);				
			
			
			
		}
		
		
		/****************************************************************
		 * This method allows the main application to register a state change
		 * function with the main controller. When a state change event is caught,                 
		 * this function will be invoked, receiving the event object as its only parameter       	     
		 ****************************************************************/
		
		
		
		public function setResouceUpdateHandler(f : Function) : void {
			addEventHandler('evt_updateResources', f);
			
		}
		
		public function setPauseAlertHandler (f: Function) : void {
			addEventHandler('evt_pauseAlert', f);
		}
		
		
		public function setStartTimerHandler (f : Function) : void {
			addEventHandler('evt_startTimer', f);
		}
		
		public function setStopTimerHandler (f : Function) : void {
			addEventHandler('evt_stopTimer', f);
		}
		
		public function setLoggedInHandler (f : Function) : void{
			addEventHandler('evt_loggedIn', f);
		}
		
		public function setEnableLogInHandler (f : Function) : void{
			addEventHandler('evt_enableLogin', f);
		}
		
		public function setEndGameHandler (f : Function) : void {
			addEventHandler('evt_endGame', f);
		}
		
		public function setDispatchedHandler (f : Function) : void {
			addEventHandler('evt_dispatchComplete', f);
		}
		
		public function setUpdateInfoHandler (f : Function) : void {
			addEventHandler('evt_mc_updateinfo',f);
		}
		
		
		/****************************************************************
		 * Control Event handlers
		 ****************************************************************/
		
		private function doAuthenticate( event : ControlEvent ) : void {
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var player : RoleVO;
			var playerVO : PlayerVO = new PlayerVO;
			
			var userName :String = event.data[0] as String;
			var role : String = event.data[1] as String;
			trace("Role: " + role);
			playerVO.roleID =  event.data[2];
			playerVO.userName = userName;
			//Alert.show("PlayerVO Role: " + playerVO.roleID);
			trace ("PlayerVO Role: " + playerVO.roleID);
			//now assigning current player, team, and changing state
			for (var i : int = 0; i <= rModel.data.length - 1; i++){
				if (rModel.data.getItemAt(i).role.toLowerCase() == role.toLowerCase()){					
					rModel.data.getItemAt(i).userName = userName;
					rModel.assignPlayerTeam(rModel.data.getItemAt(i) as RoleVO);
					trace("MC - CurrentPlayer =" +rModel.currentPlayer.userName + ", Role =" + rModel.currentPlayer.role );
				}
			}
			
			var event : ControlEvent = new ControlEvent('evt_sendPlayer');
			event.data = playerVO;
			event.dispatch();
			trace("Autenticate - Player Sent");
			setPlayerResources();
			
			var event2 : ControlEvent = new ControlEvent('evt_loggedIn');
			event2.data = "Decision";
			event2.dispatch();
			
		}
		
		private function doSendQuestionAnswer (event:ControlEvent) : void {
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			
			var qh : QuestionHistoryVO = event.data as QuestionHistoryVO;		
			
			qh.roleID = rModel.currentPlayer.roleID;
			
			this.doSendAnswer(qh);
			
			
		}
		
		public function doClearSelectedInfo(event : ControlEvent) : void
		{
			var iModel : InformationModel = ModelFactory.getModel("InformationModel") as InformationModel;
			
			iModel.clearSelectedInformation();
		}
		
		public function doRemoveInformation( event : ControlEvent) : void
		{
			var info : InformationVO = event.data as InformationVO;
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var iModel : InformationModel = ModelFactory.getModel("InformationModel") as InformationModel;
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			
			// Notify backend that information was removed
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.informationTransfer(rModel.currentPlayer.roleID, info.id, -9999);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
			
			// Remove the information from the location to update the view
			lModel.removeInfoFromLocation(info.locationID);
			
			// Remove the information from the info array
			iModel.removeInformation(info.id);			
			
		}
		
		// tells the server to dispatch Units to an Incident
		public function doDispatch( event : ControlEvent) : void {
			trace("SC - Sending Dispatched units to Server...");
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var unitList : ArrayCollection = event.data as ArrayCollection;
			
			var tlVO :TimelineVO = new TimelineVO()
						
			
			
			doDispatchUnits(unitList, lModel.selectedLocation.id);
			doUpdatePlayerIncident(pModel.currentPlayer.roleID, lModel.selectedLocation.id);
			
			/*
			var event2 : ControlEvent = new ControlEvent('evt_updateResources');
			event2.dispatch();
			
			var event3 : ControlEvent = new ControlEvent('evt_dispatchComplete');
			event3.dispatch();*/
			
			
		}
		
		// tells the server to return Units from an Incident
		public function doReturn( event : ControlEvent) : void {
			trace("SC - Sending Returned units to Server...");
			var unitList : ArrayCollection = event.data as ArrayCollection;
			
			doReturnUnits(unitList);
		}
		
		// tells the server to return the selected unit from an incident
		public function doRecall ( event : ControlEvent ) : void {
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			var rModel :ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;
			
			var unit : UnitVO = event.data as UnitVO;
			
			unit.isPending = false;			
			
			var unitList : ArrayCollection = new ArrayCollection();
			unitList.addItem(unit);
			
			var tlVO :TimelineVO = new TimelineVO()
			tlVO.type = "action";
			tlVO.message = pModel.currentPlayer.role + " recalled " + rModel.getResource(unit.resourceID.toString()).label + " from the location " 
				+ lModel.getLocation(unit.locationID).name;
			
			var tlEvent : ControlEvent = new ControlEvent('evt_logTimeline');
			tlEvent.data = tlVO;
			tlEvent.dispatch();
			
			doReturnUnits(unitList);			
			
		}
		
		private function doAlert( event : ControlEvent ) : void {
			Alert.show(event.data as String);
		}
		
		private function doGetSystemState (event : ControlEvent) : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.getSystemState();
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		
		/****************************************************************
		 * Remote Object Functions
		 ****************************************************************/
		
		private function doSendAnswer( qh : QuestionHistoryVO) : void
		{
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;	
			var token :AsyncToken = ro.answerQuestion(qh.questionId, qh.answerValue, qh.roleID, qh.questionTime, 
				qh.answerTime, qh.correct, qh.totalUtterances, qh.totalCharacters,
				qh.totalWords, qh.roleUtterances, qh.roleCharacters, qh.roleWords);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
			//answerQuestion(int questionId, String answer, int roleId, int questionTime, int answerTime)
		}
		
		private function doDispatchUnits( unitList : ArrayCollection, locationID : int) : void {
			
			var CurrentDateTime:Date = new Date();
			var TimeString:String = CurrentDateTime.getHours().toString()+" : "+doubleDigitFormat(CurrentDateTime.getMinutes())+" : "+doubleDigitFormat(CurrentDateTime.getSeconds());
			
			var badgeList : Array = new Array();
			var priorityList : Array = new Array();
			var infoList : Array = new Array();
			var traceBadgeList : String = "SC - Unit Badges: ";
			var pModel : PlayerModel = ModelFactory.getModel("PlayerModel") as PlayerModel;
			var rModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var iModel :InformationModel = ModelFactory.getModel("InformationModel") as InformationModel;
			
			//create an array of BadgeNo's (the unique identifer for units), to be sent to the server
			for (var i : int = 0; i <= unitList.length - 1; i++){
				//Alert.show(unitList.getItemAt(i).badgeNo + " " + locationID);
				badgeList.push((unitList.getItemAt(i) as UnitVO).resourceID);
				priorityList.push(unitList.getItemAt(i).setPriority);
				traceBadgeList = traceBadgeList + unitList.getItemAt(i).badgeNo + ": priority " + unitList.getItemAt(i).setPriority + ", ";
			} 
			
			infoList = iModel.getSelectedInfoIDs();
			trace(traceBadgeList);	
			
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.dispatchUnits(rModel.currentPlayer.roleID, badgeList, locationID, priorityList, infoList, TimeString);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
			
			
			var event2 : ControlEvent = new ControlEvent("evt_mc_clearSelectedInfo");
			event2.dispatch();					
		}
		
		
			function doubleDigitFormat(num:uint):String 
			{
			
				if(num < 10) {
				return ("0" + num);
				}
			
				return num.toString();
			
			}
		
		
		private function doUpdatePlayerIncident ( roleID : int, incidentID : int ) : void {
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.setPlayerIncident(roleID, incidentID);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
		}
		
		private function doReturnUnits( unitList : ArrayCollection) : void {
			
			var badgeList : Array = new Array();
			var traceBadgeList : String = "SC - Unit Badges: ";
			
			//create an array of BadgeNo's (the unique identifer for units), to be sent to the server
			for (var i : int = 0; i <= unitList.length - 1; i++){
				badgeList.push(unitList.getItemAt(i).badgeNo);
				traceBadgeList = traceBadgeList + unitList.getItemAt(i).badgeNo + ", ";
			} 
			
			trace(traceBadgeList);	
			
			var ro : RemoteObject = ServiceFactory.getInstance().getService("neocitiesRO") as RemoteObject;			
			var token : AsyncToken = ro.returnUnits(badgeList);
			token.addResponder(new RemoteMediator (this, voidResponse, onFault));
			
			/*var event : ControlEvent = new ControlEvent('changeState');
			event.data = "Decision";
			event.dispatch();*/					
		}
		
		
		
		
		/****************************************************************
		 * Private Functions
		 ****************************************************************/
		
		private function setPlayerResources () : void {		
			trace("...Associating Player Resources & Units...");
			
			//obtain the application's Resource and Player models
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;						
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			//var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;
			
			PlayerLoop:			//loop over the current list of players
			for (var i : int = 0; i <= pModel.data.length - 1; i++){
				var playerResources : ArrayCollection = new ArrayCollection();
				var playerUnits : ArrayCollection = new ArrayCollection();
				
				ResourceLoop:	//loop over the resource list, assigning resources to each player.
				for (var j : int = 0; j <= rModel.data.length - 1; j++){
					
					if ((rModel.data.getItemAt(j).roleID == pModel.data.getItemAt(i).roleID) || rModel.data.getItemAt(j).roleID == -1 ){
						playerResources.addItem(rModel.data.getItemAt(j));
						
						/*
						UnitLoop:	//loop over the unit list, assigning units to each player.
						for (var k : int = 0; k <= uModel.data.length - 1; k++){
							
							if(uModel.data.getItemAt(k).resourceID == rModel.data.getItemAt(j).resourceID) {
								playerUnits.addItem(uModel.data.getItemAt(k));
							}
							
						}//end of unitLoop		*/						
					}																						
				}//end of ResourceLoop
				
				pModel.data.getItemAt(i).resources = playerResources;
				
				//pModel.data.getItemAt(i).units = playerUnits;
				
				//assigns the currentPlayers resourceList
				if (pModel.currentPlayer.roleID == pModel.data.getItemAt(i).roleID){
					pModel.currentPlayer.resources = playerResources;
					//pModel.currentPlayer.units = playerUnits;
				}				
				
			}//end of PlayerLoop
			
			pModel.assignPlayerTeam(pModel.currentPlayer);
			
		}
		
		/****************************************************************
		 * Traffic Request Functions
		 ****************************************************************/
		
		private function requestChangeState( event : ControlEvent ) : void {
			var newState : String = event.data.toString();
			
			var event2 : ControlEvent = new ControlEvent('changeState');
			event2.data = event.data;			
			trace ("MC - Trafficking ChangeState request");
			event2.dispatch();			
		}		
		
		
		/****************************************************************
		 * Message Event Handlers
		 ****************************************************************/
		
		public function onFault (event : FaultEvent ) : void {
			trace(event.fault.message.toString());
			//Alert.show(event.fault.message.toString()); 
			/*
			switch (event.fault.faultCode.toString())
			{
			case "Client.Error.Request.Timeout":
			Alert.show("This node has lost Internet Connection");
			break;
			default:
			Alert.show(event.fault.faultString);
			}*/
		}
		
		public function voidResponse ( event : ResultEvent ) : void {
			trace ("SC - Function Call Complete");
		}
		
		
		
		
	} 
}