package edu.psu.ist.neocities.test;

import edu.psu.ist.neocities.value.*;
import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.util.*;
import edu.psu.ist.neocities.model.*;
import java.util.ArrayList;
import java.util.List;

 
import java.io.*;

/**
 * 
 * @author dxm401
 * This is a class to test the event progression implemented from EventModel and the GrowthEquation classes  
 *  
 */
public class TestEventGrowth {
	
	//variables
	
	//private EventModel instance1 ;//=  EventModel.instance(); // not-sure about this!!
	private GrowthEquation instance1;
	
	private EventVO testEvent; //= new EventVO();
    
//	private boolean isTimeBound = true;// does it have time-restrictions ? 
//    private boolean isMagBound = true; // is it magnitude-restricted ?
    
    private int timeLimit = 100; // !!! This is assumed to be the limiting time-step (imaginary, not real) 
   
    private double magCap = 8.00;  // It cannot go beyond this magnitude to something unrealistically large
 //   private double magLimit =  7.0;  // Magnitude level that would result to failure... 
    
    private double severity = 3.5;
   // private int maxResources = 10;
    
    private int goodResources = 0; // Lets not consider inapplicable resources at the moment.. 
//   private int timeLatestModification = -1;
    
    private double magSuccess = 0;
    
    public double magnitudeTestEvt = 0.0;
    
    ArrayList<Integer> responseTimeSteps = new ArrayList<Integer>();
    ArrayList<Integer> responseNumbers = new ArrayList<Integer>();
   
    
    ScenarioModel scenarioModel = ScenarioModel.instance();
    
    CommController comm = CommController.instance();
    //functions
	
    private void setUserEventSettings(){
    	
    } // ending setUserEventSettings...
    
    
    
    TestEventGrowth()
    { 
    	instance1 = new GrowthEquation();
         instance1.setCoeffs(.995, .0075, .04995);
         //  instance1.setChaos(0.0);
         testEvent = new EventVO();
               
    }
    
  
    
    
    public void answerFeed(data d){
    		
    	     // answers need to be added here...
    	     
            if (d.u1 != -9999)
            {
        	     responseTimeSteps.add(d.u1);// First.
        	     responseNumbers.add(d.u1n); // First.
            }
            
            if (d.u2 != -9999)
            {
                responseTimeSteps.add(d.u2);// First.
                responseNumbers.add(d.u2n); // First.
            }
            
            if (d.u3 != -9999)
            {
                responseTimeSteps.add(d.u3);// First.
                responseNumbers.add(d.u3n); // First.
            } 
    		 responseTimeSteps.add(-1);
    		 responseNumbers.add(-1); 

    	
    }// Ending answerFeed() function
	
    
    
	public void runEvent(data d)
	{ 
		answerFeed(d);
		
		printString("Running Event");
		
		testEvent.activate();
		testEvent.goodResources = 0;
			
		while(true)
		{ 
			
			int timeStep = testEvent.getCurrentTimeStep();
			
			
			// calculate magnitude
			double tempMag = 0.0;
					
			
			for(int search = 0;  ; search++){
				
				if(responseTimeSteps.get(search)==-1)// the last default entry in the ArrayList
				break;
				
				else if(timeStep==responseTimeSteps.get(search))
				{ 
					goodResources = responseNumbers.get(search) + goodResources;
					testEvent.goodResources = goodResources;
					//System.out.println("Added Resource Number =  "+responseNumbers.get(search));
					//timeLatestModification = timeStep;
					
				    break;
				}
				
			} // Ending Search Loop
			
			 				
				tempMag = instance1.calculateCurrentMagnitude(testEvent, timeStep);	
				boolean timeOver = false; // has the event failed due to time-bound considerations ?
				
		
				if(testEvent.getCurrentTimeStep()>= testEvent.getTimeStepLimit()-1) 
					timeOver= true;
				
				if(timeOver && (tempMag > scenarioModel.getMagSuccess())) { 				  					  
				handleTestEventCompletion(testEvent, false);
				} 
				else if (tempMag <= scenarioModel.getMagSuccess()) {
					handleTestEventCompletion(testEvent, true);
				} 
				
				if((testEvent.getCurrentTimeStep() > testEvent.getTimeStepLimit())) // error-check
				{
					// If this portion of the code is reached, there is a problem with the coding.
					printString("ERROR - EC: Event still on even after Time-Over.");
					System.exit(1);
				}				 
		
			// Managed Conditions
						
			// Check for event-conditions 
			boolean flag_break = false;
			
			if(testEvent.condition==2)
			{
				//testEvent.setEventComplete();
				testEvent.finishingTimeStep = testEvent.getCurrentTimeStep();
				testEvent.finishingMagnitude = tempMag;
				System.out.println("Current Magnitude: "+tempMag);
				System.out.println("SUCCESS");
				System.out.println("Number of resources: "+testEvent.goodResources);
				System.out.println("Unit Cap = "+testEvent.unitCap);
				//System.out.println("Res. Cap = "+testEvent.resourceCap);
				flag_break = true;
			}
			
			else if(testEvent.condition==0)
			{   
				//testEvent.setEventFailure();
				testEvent.finishingTimeStep = testEvent.getCurrentTimeStep();
				testEvent.finishingMagnitude = tempMag;
				System.out.println("Current Magnitude: "+tempMag);
				System.out.println("FAILURE");
				System.out.println("Number of resources: "+testEvent.goodResources);
				System.out.println("Unit Cap = "+testEvent.unitCap);
			//System.out.println("Res. Cap = "+testEvent.resourceCap);
				flag_break = true;
			}
			
			else{ 
				   
				printString("R: "+goodResources);
				printString("M: "+testEvent.getCurrentMagnitude());
				testEvent.incrementTimeStep();
				  
				// Get user input to go to next-time-step, or to add more good-resources
				printString("# of resources to be added for next time-step ? Enter 0 for none: ");
					
			}

			
			testEvent.magnitudeArray.add(timeStep, (double)tempMag);
				
		//	System.out.println("Post Equation Magnitude "+testEvent.magnitudeArray.get(timeStep));
			System.out.println(testEvent.magnitudeArray.get(timeStep));
			   
			
			if(flag_break==true)
				break;
		
			
		} // ending while loop (timeSteps)
	    
		for(int i = 0; i <= testEvent.finishingTimeStep; i++)
			printString(""+testEvent.magnitudeArray.get(i));
		
		instance1.setTestMode(false);
		System.out.println("Completed on time-step number: "+testEvent.finishingTimeStep);
		System.out.println("Events normalized score : "+instance1.calculateNormalizedScore(testEvent));
		System.out.println("Events grade : "+instance1.scoreGrader(testEvent));
		
	//	appendToFile(testEvent.condition + ","  + instance1.calculateNormalizedScore(testEvent) + ","  + 
	//	instance1.calculateEventArea(testEvent) + ",243,183," + (testEvent.finishingTimeStep*3) + "," + (183+(testEvent.finishingTimeStep*3)));
		
	} // ending runEvent() 
	
	
	
	 public void appendToFile (String append) {

	    BufferedWriter bw = null;

	      try {
	         bw = new BufferedWriter(new FileWriter("test.csv", true));
	     bw.write(append);
	     bw.newLine();
	     bw.flush();
	      } catch (IOException ioe) {
	     ioe.printStackTrace();
	      } finally {                       // always close the file
	     if (bw != null) try {
	        bw.close();
	     } catch (IOException ioe2) {
	        // just ignore it
	     }
	      } // end try/catch/finally

	   } // end test()

	
	
 private void handleTestEventCompletion(EventVO eventVO, Boolean complete){
		
		if(complete)
		{
				eventVO.setEventComplete();

				//comm.consoleMessage("Event Success: #" +eventVO.incident.getIncidentID());
		} 
		else 
		{
		
				eventVO.setEventFailure();
						
			//comm.consoleMessage("Event Failed: #" +eventVO.incident.getIncidentID());
		}
				
		//record the final timeStep and Magnitude for the events
		
		
 } // ending handleTestEventCompletion 
	
	
	private int readInteger()
	{
		int x = 0;
		
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader input = new BufferedReader(isr);
	          
			String a;
			a = input.readLine();
			x = Integer.parseInt(a);
			
			}
		
		catch (IOException e)
			{
			printString("TestEventGrowth.readInteger:  Problem with reading inputs...");
			}
		
		return x;
		
			
	} // getUserInputs
	
	public void printString(String s){
		
		//comm.consoleMessage(s);
		
	}
	
	

    
    private void eventVOSettings()
    {
        testEvent.activate();
    	
        testEvent.setInitialSevarity(severity);
        
       // testEvent.difficulty.setResourceLimit(maxResources);
        testEvent.setMagCap(magCap);
        
    	
      //  if(isTimeBound)
       // {    
        	//testEvent.setTimeStepLimit(timeLimit);
        	
        TimeSet timeLimit = new TimeSet();
        	
        timeLimit.setTimeSteps(this.timeLimit, scenarioModel.TimeStepLength);
        	
        testEvent.setTimeLimit(timeLimit);
       
        //}
        
        scenarioModel.setMagSuccess(0.0);
      //  testEvent.magnitudeArray.ensureCapacity(10);
      
    	
    }// ending eventVOSettings()
    
	
	
	public void NC4Test(){
		
		//TestEventGrowth test = new TestEventGrowth();
		
		eventVOSettings();
		boolean success = false;
		data apply3 = new data(65,10,68,0,70,0);
		
		this.runEvent(apply3);
		
		/*for(int t = 0; t < timeLimit; t++){
			
			// calculate magnitude
			
			double currMag = 0.0; 
			currMag = instance1.calculateCurrentMagnitude(testEvent, t);
			
			// check for termination
			
			if(currMag <= scenarioModel.getMagSuccess()){
				
				success = true;
				finishingTimeStep = t;
				break;
			}
			
			testEvent.incrementTimeStep();
			
		}// ending t loop
		
		if(success)
		{
			System.out.println("Success!!");
			
		}	
		else 
		{
			System.out.println("Failure!");
		}
		
		System.out.println("Event completed in : "+finishingTimeStep+" time-steps.");
		
		// report success or failure
		
		// report scores
		 * 
		 * 
		 */
			
	}// ending NC4Test
	
	
	public void ONRTestingFunction(){
		
		List<data> dataList = new ArrayList<data>();
        
	    dataList.add(new data(8,1,13,1,29,1));
	    dataList.add(new data(16,1,24,1,-9999,1));
	    dataList.add(new data(3,1,8,1,24,1));
	    dataList.add(new data(5,1,13,1,19,1));
	    dataList.add(new data(2,1,6,1,7,1));
	    dataList.add(new data(3,1,5,1,5,1));
	    dataList.add(new data(4,1,11,1,20,1));
	    dataList.add(new data(9,1,9,1,15,1));
	    dataList.add(new data(4,1,11,1,79,1));
	    dataList.add(new data(6,1,13,1,-9999,1));
	    dataList.add(new data(5,1,6,1,15,1));
	    dataList.add(new data(4,1,8,1,14,1));
	    dataList.add(new data(6,1,8,1,-9999,1));
	    dataList.add(new data(8,1,9,1,11,1));
	    dataList.add(new data(2,1,5,1,10,1));
	    dataList.add(new data(5,1,17,1,32,1));
	    dataList.add(new data(3,1,7,1,9,1));
	    dataList.add(new data(5,1,11,1,12,1));
	    dataList.add(new data(7,1,7,1,38,1));
	    dataList.add(new data(5,1,21,1,35,1));
	    dataList.add(new data(7,1,22,1,32,1));
	    dataList.add(new data(12,1,18,1,27,1));
	    dataList.add(new data(16,1,25,1,-9999,1));
	    dataList.add(new data(2,1,7,1,31,1));
	    dataList.add(new data(6,1,7,1,9,1));
	    dataList.add(new data(2,1,9,1,11,1));
	    dataList.add(new data(32,1,35,1,38,1));
	    dataList.add(new data(3,1,12,1,20,1));
	    dataList.add(new data(24,1,37,1,37,1));
	    dataList.add(new data(10,1,15,1,-9999,1));
	    dataList.add(new data(6,1,12,1,16,1));
	    dataList.add(new data(6,1,15,1,45,1));
	    dataList.add(new data(5,1,19,1,23,1));
	    dataList.add(new data(13,1,19,1,62,1));
	    dataList.add(new data(2,1,-9999,1,-9999,1));
	    dataList.add(new data(9,1,-9999,1,-9999,1));
	    dataList.add(new data(6,1,9,1,11,1));
	    dataList.add(new data(12,1,12,1,18,1));
	    dataList.add(new data(11,1,13,1,26,1));
	    dataList.add(new data(4,1,13,1,-9999,1));
	    dataList.add(new data(4,1,11,1,17,1));
	    dataList.add(new data(15,1,16,1,-9999,1));
	    dataList.add(new data(5,1,17,1,20,1));
	    dataList.add(new data(3,1,13,1,-9999,1));
	    dataList.add(new data(13,1,16,1,17,1));
	    dataList.add(new data(5,1,6,1,14,1));
	    dataList.add(new data(18,1,19,1,23,1)); 
	    
		TestEventGrowth T1;
    	    
		for (int i = 0; i < dataList.size(); i++)
		{
		    
		    T1 = new TestEventGrowth();
		    T1.eventVOSettings();
    	    T1.runEvent(dataList.get(i));
		} 
		
	}
	

	public static void main(String args[])
	{
		TestEventGrowth test = new TestEventGrowth();
		
		test.NC4Test();	
		     
	} // ending main

	
} //  TestEventGrowth



class data
{
    int u1;
    int u1n;
    
    int u2;
    int u2n;
    
    int u3;
    int u3n;
    
    public data(int u1, int u1n, int u2, int u2n, int u3, int u3n) {
        super();
        this.u1 = u1;
        this.u1n = u1n;
        this.u2 = u2;
        this.u2n = u2n;
        this.u3 = u3;
        this.u3n = u3n;
        
        if (this.u1 == this.u2)
        {
            this.u1n ++;
            this.u2 = -9999;
        }
        
        if (this.u2 == this.u3)
        {
            this.u2n++;
            this.u3 = -9999;
        }
        
        if (this.u1 == this.u3)
        {
            this.u1n ++;
            this.u3 = -9999;
        }
    }
    
    public String toString()
    {
        return (u1+ " "+ u1n+ " "+ u2+ " "+ u2n+ " "+ u3+ " "+ u3n);
    }
    
}
