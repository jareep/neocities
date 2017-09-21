package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
				
	public class TimeModel extends ModelFactory
	{
		public function TimeModel()
		{
			super();
		}
		
		/****************************************************************
		 * Variables
		 ****************************************************************/				
		private const MIN_MASK:String = "00";
        private const SEC_MASK:String = "00";
        private const MS_MASK:String = "000";
        private const TIMER_INTERVAL:int = 1000;
       
        
        public var masSec : int = 0;
        private var min : String;
        private var sec : String;
        private var ms : String;  
        private var ampm : String;
        
        [Bindable]
		public var simTime : String = "08:00 AM";
		
		/****************************************************************
		 * Functions
		 ****************************************************************/
		
		public function updateSimTime( d : Date ) : void {
			masSec++;
			trace("Mas Sec: " + masSec.toString());
			var minS : Number =  (Math.floor(masSec/60) + 8) % 24; // Simulations start at 8 am...
			trace("minS : " + minS.toString());
			var secS : Number = masSec % 60;			
									
			if (minS >= 12)
			{	
				ampm = "PM"						
			}
			else
			{
				ampm = "AM"
			}
			
			minS = minS % 12;
			
			if (minS == 0) { minS = 12; }
						
			min = (MIN_MASK + minS.toString()).substr(-MIN_MASK.length);
			trace("min: " + min.toString());
			sec = (SEC_MASK + secS.toString()).substr(-SEC_MASK.length);
			
			simTime = String(min + ":" + sec + " " + ampm);		
		}
		
		public function resetTime() : void
		{
			min = new String;
			sec = new String;
			ms = new String;
			simTime = "00:00";
		}
		
		public function syncTime(timeStep : int) : void
		{
			var totalSeconds : int = timeStep * 3;
			masSec = totalSeconds - 1;

			updateSimTime( new Date());
		
		}
		
		public function getMinutes() : String {
			return min;
		}
		
		public function getSeconds() : String {
			return sec;
		}
		
		public function getMilliSeconds() : String {
			return ms;
		}
		
		
		
	}//end of Class
        
}//end of Package