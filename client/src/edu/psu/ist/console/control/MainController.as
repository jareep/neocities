package edu.psu.ist.console.control
{
	import com.pnwrain.easyCG.control.ControllerFactory;
	import com.pnwrain.easyCG.events.ControlEvent;
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.model.ChatModel;
	import edu.psu.ist.console.model.EventModel;
	import edu.psu.ist.console.model.HistoryModel;
	import edu.psu.ist.console.model.TimelineModel;
	import edu.psu.ist.console.value.TimelineVO;
	import edu.psu.ist.neocities.model.*;
	import edu.psu.ist.neocities.value.*;
	
	import mx.controls.Alert;
	
	public class MainController extends ControllerFactory
	{
		public function MainController()
		{
			addEventHandler("evt_alert", doAlert);
			addEventHandler("evt_PlayerAdmin", setPlayerAdmin);
			addEventHandler("evt_selectLocation", doSelectLocation);
			addEventHandler("evt_clearData", doClearData);
			addEventHandler("evt_logTimeline", doLogTimeline);
		}
		
		
		/****************************************************************
		 * This method allows the main application to register a state change
		 * function with the main controller. When a state change event is caught,                 
		 * this function will be invoked, receiving the event object as its only parameter       	     
		 ****************************************************************/
		
		public function setStateChangeHandler (f : Function) : void {
			addEventHandler('evt_StateChange', f);
		}
		public function setStartTimerHandler (f : Function) : void {
			addEventHandler('evt_startTimer', f);
		}		
		public function setStopTimerHandler (f : Function) : void {
			addEventHandler('evt_stopTimer', f);
		}
		public function setLaunchInspectorHandler (f : Function) : void {
			addEventHandler('evt_inspector', f);
		}
		public function setTabSelectHandler (f : Function) : void {
			addEventHandler('evt_TabSelect', f);
		}
		public function setInitCompleteHandler (f : Function) : void {
			addEventHandler('evt_initComplete', f);
		}
		
		public function setScenarioStartHandler (f : Function ) : void {
			addEventHandler('evt_scenarioStart', f);
		}
		
		public function setSessionInfoHandler (f : Function) :void {
			addEventHandler('evt_sessionInfo', f);
		}
		
		public function setResetSecenarioHandler (f: Function ) : void {
			addEventHandler('evt_resetScenario', f);
		}
		
		public function setPauseAlertHandler (f: Function) : void {
			addEventHandler('evt_pauseAlert', f);
		}
		
		public function setSubscribeAllHandler (f : Function) : void {
			addEventHandler('evt_subscribeAll', f);
		}
		
		
		/****************************************************************
		 * Control Event handlers
		 ****************************************************************/
		
		private function doAlert( event : ControlEvent ) : void {
			Alert.show(event.data as String);
		}
		
		private function setPlayerAdmin ( event : ControlEvent ) : void {
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			var rModel : ResourceModel = ModelFactory.getModel("ResourceModel") as ResourceModel;	
			//var uModel : UnitModel = ModelFactory.getModel("UnitModel") as UnitModel;		
			var player : RoleVO = new RoleVO(0, "Admin", "edu/psu/ist/neocities/assets/polteam.gif");
			
			pModel.assignPlayerTeam(player);
			pModel.currentPlayer = player;
			
			//pModel.currentPlayer.resources = rModel.data;
			//pModel.currentPlayer.units = uModel.data;			
		}
		
		private function doSelectLocation ( event : ControlEvent):void {           	          	
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			lModel.selectedLocation = event.data as LocationVO;           	
			var pModel : RoleModel = ModelFactory.getModel("RoleModel") as RoleModel;
			pModel.setPlayerLocation(lModel.selectedLocation.id);
			
			trace ("MC - selected Incident updated   ");
			var scoreEvent : ControlEvent = new ControlEvent('evt_scoreEvent');
			scoreEvent.data = event.data;
			scoreEvent.dispatch();	           	
		}
		
		private function doClearData ( event : ControlEvent ) : void {
			var lModel : LocationModel = ModelFactory.getModel("LocationModel") as LocationModel;
			var eModel : EventModel = ModelFactory.getModel("EventModel") as EventModel;
			var hModel : HistoryModel = ModelFactory.getModel("HistoryModel") as HistoryModel;
			var tlModel :TimelineModel = ModelFactory.getModel("TimelineModel") as TimelineModel;
			var cModel : ChatModel = ModelFactory.getModel("ChatModel") as ChatModel;
			
			//lModel.resetModel()
			tlModel.resetModel();
			hModel.resetModel();
			cModel.resetModel();			
			
			if (eModel.data != null){
				eModel.data.removeAll();
			}        		
		}
		
		private function doLogTimeline( event : ControlEvent ) : void {
			var tlModel : TimelineModel = ModelFactory.getModel("TimelineModel") as TimelineModel;
			var tModel :TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
			var log : TimelineVO = event.data as TimelineVO;
			
			if (log.message != null && log.message != "" && log.type != null && log.type != "")
			{
				tlModel.addTimelineEntry(tModel.masSec, log.type, log.message);
			}
			
		}
		
		
	}//end of MainController Class 
}//end of package file