package edu.psu.ist.neocities.util;

import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.value.*;
import java.util.ArrayList;


public class GrowthEquation{

private double coeffA; 
private double coeffB;
private double coeffC;
	
private double thisMagnitude = 0.0; // This stores the current magnitude being calculated

public boolean testModeViewer = false;

ScenarioModel scenarioModel = ScenarioModel.instance();

public GrowthEquation()
{
		
} // GrowthEquation()
	
	
public GrowthEquation(double cffA, double cffB, double cffC){  
	
		coeffA = cffA;
		coeffB = cffB;
		coeffC = cffC;
		
} // ending constructor


public double calculateCurrentMagnitude(EventVO e, int currentTimeStep){

// this should be called after any changes to resource-numbers have been made (using the addResources() and recallResources() functions in EventVO)
// currentTimeStep should specify the current timeStep from which this function is being called

	double currentMag = 0.0;
	double previousMag = 0.0;

	int resourcesOnEvent = e.getResourcesAtEvent();

	
	if(currentTimeStep==0){
    	
    	currentMag = e.initialSevarity;
    	return currentMag;
    }
	else
	{
		previousMag = e.getPreviousMagnitude();
		currentMag = coeffA*previousMag + coeffB*previousMag*previousMag - coeffC*(double)resourcesOnEvent;
		
		if(currentMag>e.magCap)
		{
			currentMag = e.magCap;
		}
		
		if(currentMag<scenarioModel.getMagSuccess()){
			
			currentMag = scenarioModel.getMagSuccess();
			
		}
		
		return currentMag;
	}

}// ending calculateCurrentMagnitude



public double calculateEventArea(EventVO eventVO){

    // The area under the curve of the event is calculated. 
	 
	 double summation = 0.0;
   	//comm.consoleMessage("Finishing Timestep: " + eventVO.getTimeStep());
   	for(int t =0; t <= eventVO.finishingTimeStep ; t++){
   	 	 	        
   		summation = summation + eventVO.magnitudeArray.get(t);		
   		
   	} // ending t loop
    
   	System.out.println("Event Area : "+summation);
   	
   	return summation;
}


public double bestActionArea(EventVO eventVO){

	 // The area of the curve representing best possible action.. 
	 // This means , appropriate resources are applied on time-step 1 (after 0).
	
	//System.out.println("Best Action Magnitudes: - ");
	
	 double bas = 0.0;
	// double magnitudeArray[] = new double[200];
	 
	 ArrayList<Double> magnitudeArray = new ArrayList<Double>(); 
	 
	 int resources = getAnswer(eventVO.getSevarity());
	 
	 int tLimit = 0;
	 int last_time_step = -1;
	 int t = 0;
	 
	 //System.out.println("*Severity: "+eventVO.getSevarity());
	 if(testModeViewer)
		 System.out.println("*Magnitudes in best action: ");
	 
	 //if(eventVO.difficulty.isTimeBound())
	 tLimit = (int)eventVO.getTimeStepLimit();
	 //else tLimit = 1; // see tLimit++;
	 
	 
	 for(t = 0; t<tLimit; t++)
	 {
		 double currentMag = 0.0;
		 
		 if(t==0)
		 magnitudeArray.add(eventVO.getSevarity());
		
		 else{ 
			 double previousMag = magnitudeArray.get(t-1);
			 currentMag = (coeffA*previousMag) + (coeffB*previousMag*previousMag) - (coeffC*(double)resources);
			 
			 if(currentMag<scenarioModel.getMagSuccess())
			 {
				 currentMag = scenarioModel.getMagSuccess();
				 magnitudeArray.add(currentMag);
				 last_time_step = t;
				 break;
			 }
			 else			 
			 magnitudeArray.add(currentMag);
		  }// ending else
		
			 
	 }// ending t loop
	 
	 if(last_time_step == -1)
		 last_time_step = tLimit-1;
	 
	 // finally calculating the best-action-score
	 
	 for(int s = 0; s<= last_time_step; s++)
		 {
		 
		  if(testModeViewer)
			  System.out.println(""+magnitudeArray.get(s));
		  			  
		  bas = bas + magnitudeArray.get(s);
		  //System.out.println(""+magnitudeArray.get(s));
		 }// ending s loop
	 
	 
	 return bas;
	 
}//bestActionArea()



public double worstActionArea(EventVO eventVO){
	

	//System.out.println("Worst Action Magnitudes : ");
	
	double magnitudeArray[] = new double[5000];
	double magCap = eventVO.getMagCap();
	int timeLimit = eventVO.getTimeStepLimit();

	//Calculate magnitudes for every time-step :-
	
	for(int t = 0; t<timeLimit; t++){
	
	double previousMag = 0.0;
	double currentMag = 0.0;
		
	if(t == 0){
		
		currentMag = eventVO.getSevarity();
	    magnitudeArray[t] = currentMag;
        continue;			  
	}
	
	else {
		previousMag = magnitudeArray[t-1];
	}
		
	currentMag = this.coeffA*previousMag + this.coeffB*previousMag*previousMag;
	magnitudeArray[t] = currentMag;
	
	if (currentMag>magCap){
	
		currentMag = magCap;
		magnitudeArray[t] = currentMag;
	}
	
	//System.out.println(magnitudeArray[t]);
	
	}// ending t loop

	// Calculating summation -> 
  
	double was = 0.0;
  
	for(int s = 0; s < timeLimit; s++)
 	{
		if(testModeViewer)
		{
			
			System.out.println(""+magnitudeArray[s]);
		
		}
		  
		was = was + magnitudeArray[s];		  
 	}// ending s loop
	  
	return was;
	
}// ending worstActionArea



public double calculateNormalizedScore(EventVO eventVO)
{
	/* double normalizedScore = 100.0 - ((100.0*calculateEventArea(eventVO))/(worstActionArea(eventVO)+1) );
	 
	 
	 normalizedScore += (100.0 * (this.bestActionArea(eventVO) / (this.worstActionArea(eventVO) + 1)) );
	 
	 */
	
	 double normalizedScore = 0.0;
	 
	 normalizedScore = 100.00*((this.worstActionArea(eventVO) - this.calculateEventArea(eventVO))/(this.worstActionArea(eventVO)-this.bestActionArea(eventVO)+1.00));
	 	 
	 return normalizedScore;
	 
	
} // calNormalizedScore


public String scoreGrader(EventVO eventVO){
	
	// grades : A+, A, B+, B, C+, C, D+, D...
	// normalized-score for worst action is 0..
	// normalized-score for best action needs to be calculated..
	
    double norScoreBestAct;//= 100.0 - (100.0*bestActionArea(eventVO)/(worstActionArea(eventVO)+1.00));
    
    norScoreBestAct = ((this.worstActionArea(eventVO) - this.bestActionArea(eventVO))/(this.worstActionArea(eventVO) - this.bestActionArea(eventVO)+1.00))*100.0;
    
    double slotSize = norScoreBestAct/8.0;
    
    double eventScore = calculateNormalizedScore(eventVO);
    
    int mod = (int)eventScore/((int)(slotSize+1.0));
    
    String grade = "";
    
    if(true)
    {
    	
    	System.out.println("Best Action Area: "+bestActionArea(eventVO));
    	System.out.println("Worst Action Area: "+worstActionArea(eventVO));
    }
     
    switch (mod)
    {   
     	    	
        case 6: grade = "A";  
                break;
    	
    	case 5: grade = "B+";
    	break;
    	case 4: grade = "B";
    	break;
    	case 3: grade = "C+";
    	break;	    		
    	case 2: grade = "C";
    	break;
    	case 1: grade = "D+";
    	break;
    	case 0: grade = "D";
    	break;
    	default: grade = "A+";
    	break;
    }
	
   return grade;
   
}


public int getAnswer(double magnitude)
{  
	
	double _resources = 0.0;
	int resources = 0;
	
	_resources = (1.0/this.coeffC)*(this.coeffA*magnitude + this.coeffB*magnitude*magnitude - .95*magnitude);
	resources = (int)Math.ceil(_resources);
	
	return resources;
	
	/*
	// This returns an appropriate number of resources needed to quell the event, when a magnitude is given.
	if ((magnitude >=0.0) && (magnitude <1.0))
		return 1;
	 
	else if((magnitude >= 1.0) && (magnitude<=6.5))
		return (int)magnitude;
	
	
	else if((magnitude >6.5) && (magnitude<=8.0))
    	return 10;
	
	else {
		return 10;
				}
	*/

}// ending getAnswer


public void setCoeffs(double _a, double _b, double _c)
{ 
	coeffA = _a;
	coeffB = _b;
	coeffC = _c;
      				
}// ending setCoeffs



public void setTestMode(boolean mode)
{
	testModeViewer = mode;
	
}
}// ending GrowthEquation class