package edu.psu.ist.neocities.util;

import java.util.Date;

public final class TimeLogic
{
 /* 
    As of now, this implementation assumes that Game-Start Time can be independent of
    real-start-time. Game-Start Time will be arbitrary
    
    This assumes that getTime() in Date returns the number of milli-seconds 
    since 1970 00:00:00 EDT  - Not GMT
    
    realStartTime will have to be recorded when the game-starts with setRealStartTime()...
        
    gameStartTime will have to be entered by the user
    
    At present the game assumes that no simulation would go overnight
  
*/	
	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static TimeLogic instance = new TimeLogic();
	
	private TimeLogic() {
		// Required for Singleton Design Pattern
	}
			
	public static TimeLogic instance(){
		return instance;
	}
	
    
	/****************************************************************
	 * Variables
	 ****************************************************************/
	private Date t = new Date();	   
		
	private int ratio = 12; 
	// The seconds in real-time elapsed per seconds in game-time
	 
	private long gameStartTime, realStartTime, midnightReference; 
	// never used variables: private long simTime, gameTime, realTime,
	
	
			
	private boolean consistencyCheck = false;
	
	/****************************************************************
	 * Getter Functions
	 ****************************************************************/
	public Date getDate() {
		return t;
	}
		    
	// This returns the seconds elapsed in the game so far
    public long getGameTime() {     	    	
    	return (getRealTime()*ratio)/1000 + gameStartTime;        
 	}
    
    // Returns the number of milliseconds elapsed since game started
	public long getRealTime() {
    	
	
		if(consistencyCheck=false)
		{
			System.out.println("Consistency Check Failed: setRealStartTime not called!!!");
			System.exit(1);
		}
		
		return (System.currentTimeMillis() - realStartTime); 	 	
    }
    
   
    
    
	
	/****************************************************************
	 * Setter Functions
	 ****************************************************************/	
    public void setDate(Date date){
		t = date;
	}
    	 	
	/* Game Start Time needs to be in seconds since 00:00 AM of the start of the day in the game-world */
	public void setGameStartTime(int startTime) {			 
		gameStartTime = startTime; 
	}
	
	//realStartTime will have to be specified in terms of the number of milliseconds since 1970
	public void setRealStartTime() {
	    
		consistencyCheck = true;
		
		realStartTime = System.currentTimeMillis(); 
		
		//midnightReference = the number of millisecs between days-start and the game-start
		midnightReference = t.getHours()*3600000 + t.getMinutes()*60000 + t.getSeconds()*1000;			
	
	}// ending setRealStartTime()
  
	
		
	
	public void setRatio(int R)
	{
		ratio = R;
		
	}// ending setRatio
	
	
    
    /****************************************************************
	 * Display and Format Functions
	 ****************************************************************/    
    
	
	public String displayClock(long time) // time is in seconds.. 
    { 
		
      long hrs, mins, scs, x;
      
      time = time % (3600 * 24);
      
      hrs = time / 3600;
      x = time % 3600;
      
      mins = x / 60;
      
      scs = x % 60;
      
      String disp = ""+hrs+" : "+mins+" : "+scs;
       
      return disp;
         
    } // ending displayClock()
 	
    
	
 	public void testBox()
 	{   
 		int x = 0;
 		long startTestTimeReal = System.currentTimeMillis();;
 	    
 		setRealStartTime();
 		
 		System.out.println("Starting test now");
 		
 	    //long latency  = startTestTimeReal - realStartTime;  	 
 		while( System.currentTimeMillis() < startTestTimeReal + 5000) // Show 5 seconds progress
         {
            if(x%5000==0)     
 			System.out.println("Real-Time = "+displayClock((getRealTime()/1000)+(midnightReference/1000))+" || Game-Time = "+displayClock(getGameTime()) );
             
            //System.out.println(System.currentTimeMillis()+" || "+"Real-Time ="+getRealTime()+" || Game Time ="+getGameTime());    
            x++; 
            
         } // ending while()
 	
 	}// ending testBox()   
 	
 	
 	
 	public static void main(String args[])
 	{
 		TimeLogic TL = TimeLogic.instance();
 		
 		TL.testBox();
	
 		
 	}// ending void main 
 	
 
} // Ending TimeLogic Class