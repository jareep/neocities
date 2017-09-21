package edu.psu.ist.neocities.oldModel
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.neocities.oldValue.DescriptionVO;
	import edu.psu.ist.neocities.oldValue.IncidentVO;
	
	import mx.collections.ArrayCollection;

	
	public class IncidentModel extends ModelFactory
	{
		public function IncidentModel()
		{
			super();
		}
		
		
		/****************************************************************
	     * Variables
	     ****************************************************************/
		[Bindable]
		public var data : ArrayCollection;
		
		[Bindable]
		public var openIncidents : ArrayCollection;
		
		[Bindable]
		public var closedIncidents : ArrayCollection;
				
		[Bindable]
		public var selectedIncident : IncidentVO;			
		
		/****************************************************************
	     * Open & Closed Incident Functions
	     ****************************************************************/

		
		public function isOpenIncident ( ID : String ) : Boolean {
			
			for (var i : int = 0; i <= openIncidents.length - 1; i++){
				if (openIncidents.getItemAt(i).incidentID == ID){
					return true;
				}
			}
			return false;			
			
		}
		
		public function isClosedIncident ( ID : String ) : Boolean {
			
			for (var i : int = 0; i <= closedIncidents.length - 1; i++){
				if (closedIncidents.getItemAt(i).incidentID == ID){
					return true;
				}
			}
			return false;			
			
		}
		
		public function updateOpenIncidents () : void {
			
			for (var i : int = 0; i <= openIncidents.length - 1; i++){
				for (var j : int = 0; j <= data.length - 1; j++){	
					if( openIncidents.getItemAt(i).incidentID == data.getItemAt(j).incidentID){
						openIncidents.removeItemAt(i);
						openIncidents.addItem(data.getItemAt(j));
					}					
				}//end of data loop
			}//end of openIncident Loop			
		}//end of function
		
		public function updateClosedIncidents () : void {
			
			for (var i : int = 0; i <= closedIncidents.length - 1; i++){
				for (var j : int = 0; j <= data.length - 1; j++){	
					if( closedIncidents.getItemAt(i).incidentID == data.getItemAt(j).incidentID){
						closedIncidents.removeItemAt(i);
						closedIncidents.addItem(data.getItemAt(j));
					}					
				}//end of data loop
			}//end of closedIncidents Loop			
		}//end of function
		
		public function updateAllIncidents () : void {
			openIncidents.removeAll();
			closedIncidents.removeAll();
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).status.toLowerCase() == "complete" || data.getItemAt(i).status.toLowerCase() == "failed"){
					closedIncidents.addItem(data.getItemAt(i));									
				}
				else {
					openIncidents.addItem(data.getItemAt(i));	
				}
			}
			
			return void;
		}
		
										
		/****************************************************************
	     * Incident Value Object Functions
	     ****************************************************************/
		public function getIncident ( ID : String ) : IncidentVO {
			var incident : IncidentVO
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == ID){
					incident = data.getItemAt(i) as IncidentVO;
					//trace ('incident found: ID = ' + incident.incidentID + ' Label = ' + incident.label);
					break;
				} else {
			
					incident = new IncidentVO("99", "ignore", "idle", "New",											  
											  "You fail at flex programming" , 2, "-77.87452697753906", "40.800811097987103"
											   );	
				}											
			}
			return incident;
		}
		
		
		public function isIncident ( ID : String ) : Boolean {
			
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == ID){
					return true;
				}
			}
			return false;			
			
		}		
		
		public function setIncidentStatus ( incidentID : String, status : String ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID && data.getItemAt(i).status != status){
					data.getItemAt(i).status = status;								
					break;
				}
			}
							
			return void;
		}
		
		public function setIncidentSeverity( incidentID : String, severity : Number ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID){
					data.getItemAt(i).severity = severity;								
					break;
				}
			}
							
			return void;
		}
		
		public function setIncidentDescription ( incidentID : String, description : String ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID ){
					data.getItemAt(i).description = description;								
					break;
				}
			}
							
			return void;
		}
		
		public function setIncidentFeedback ( incidentID : String, feedback : String ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID ){
					data.getItemAt(i).feedback = feedback;								
					break;
				}
			}
							
			return void;
		}
				
		public function setIncidentCompletionTime( incidentID : String, time : String ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID){
					data.getItemAt(i).completionTime = time;
					break;
				}
			}	
			return void;
		}
		public function setIncidentDispatchTime( incidentID : String, time : String ) : void {
						
			for (var i : int = 0; i <= data.length - 1; i++){
				if (data.getItemAt(i).incidentID == incidentID){
					data.getItemAt(i).dispatchTime = time;
					break;
				}
			}	
			return void;
		}
		
				
		public function resetModel () : void
		{
			data.removeAll();
			openIncidents.removeAll();
			closedIncidents.removeAll();
			
			
				selectedIncident = null;
		}

	}
}