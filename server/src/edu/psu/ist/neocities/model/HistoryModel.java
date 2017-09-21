package edu.psu.ist.neocities.model;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.HistoryValue.*;
import edu.psu.ist.neocities.value.ScoreVO;
/**
 * @author bhellar
 * @date 2-16-09
 * @description stores the Team Score as well as the individual event scores
 */
public final class HistoryModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static HistoryModel instance = new HistoryModel();
	
	private HistoryModel() {
		// Required for Singleton Design Pattern
	}
			
	public static HistoryModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<ActionHistoryVO> actionHistory = new ArrayList<ActionHistoryVO>();
	
	public List<EventHistoryVO> eventHistory = new ArrayList<EventHistoryVO>();
	
	public List<OrderHistoryVO> orderHistory = new ArrayList<OrderHistoryVO>();
	
	public List<QuestionHistoryVO> questionHistory = new ArrayList<QuestionHistoryVO>(); 
	
	public List<InformationHistoryVO> informationHistory = new ArrayList<InformationHistoryVO> ();
	
	/****************************************************************
	 * Common Functions
	 ****************************************************************/
	
	public void resetHistory() {
		actionHistory.clear();
		eventHistory.clear();
		orderHistory.clear();
		questionHistory.clear();
		informationHistory.clear();
	}
	
	/****************************************************************
	 * Action History Functions
	 ****************************************************************/
	
	public void addAction(int eventID, int roleID, int resourceID, int badgeNo, String actionString, String eventTime, String actionTime, String reactionTime) {
		
		ActionHistoryVO action = new ActionHistoryVO();
		
		action.eventID = eventID;
		/*action.roleID = roleID;
		action.resourceID = resourceID;
		action.badgeNo = badgeNo;
		action.action = actionString;
		action.eventTime = eventTime;			
		action.actionTime = actionTime;
		action.reactionTime = reactionTime;*/
		
		actionHistory.add(action);		
	}
	
	
	public void addAction (ActionHistoryVO action)
	{
	    actionHistory.add(action);
	}
	
	public void addInformation(InformationHistoryVO info)
	{
		informationHistory.add(info);
	}
	
	
	/****************************************************************
	 * Event History Functions
	 ****************************************************************/
	
	public void updateDispatchTally(int eventID, Boolean unitMatch){
		//loop over the events
		for (EventHistoryVO history : eventHistory){
			//for the selected incident
			if(history.eventID == eventID){
				
				if(unitMatch){
					history.dispatchCorrect++;					
				}
				else {
					history.dispatchWrong++;
				}							
			}			
		}//end of eventHistory Loop
		
	}// ending DispatchTally
	
	public void updateRecallTally(int eventID, Boolean unitMatch){
		//loop over the events
		for (EventHistoryVO history : eventHistory){
			//for the selected incident
			if(history.eventID == eventID){
				
				if(unitMatch){
					history.recallCorrect++;
				}
				else {
					history.recallWrong++;
				}							
			}			
		}//end of eventHistory Loop
		
	}// ending DispatchTally
	
	/****************************************************************
	 * Order History Functions
	 ****************************************************************/
	// Interdependency numerator
/*
	
	public void incrementSoftOrderNumerator(int eventID)
	{
		for (OrderHistoryVO order : orderHistory){
			
			//for the selected incident
			if(order.eventID == eventID){
				
				order.numCorrectOrderUnits++;
				
			}
			
		}//end of orderHistory Loop
		
	} // updateSoftOrderNumerator
	
	 
	public void incrementSoftRoleOrderNumerator(int eventID)
	    {
	        for (OrderHistoryVO order : orderHistory){
	            
	            //for the selected incident
	            if(order.eventID == eventID){
	                
	                order.numCorrectRoleOrderUnits++;
	                
	            }
	            
	        }//end of orderHistory Loop
	        
	    } // updateSoftOrderNumerator
			
	
	// Interdependency denominator..
	public void incrementSoftOrderDenominator(int eventID)
	{
		for (OrderHistoryVO order : orderHistory){
			
			//for the selected incident
			if(order.eventID == eventID){		
				order.totalActions++; 		
				break;	
			} 
			
		}//end of orderHistory Loop
	} // updateSoftOrderNumerator
	
	// function for correct pacing information
	public void incrementCorrectTimedUnits(int eventID)
	{
		for (OrderHistoryVO order : orderHistory){
			
			//for the selected incident
			if(order.eventID == eventID){
				order.numTimelyArrival++;	
				break;	
			} 
						
		}//end of orderHistory Loop	
	} // updateSoftOrderNumerator
	
	public void incrementCorrectUnits(int eventID)
	{
		for (OrderHistoryVO order : orderHistory){
			
			//for the selected incident
			if(order.eventID == eventID){		
				order.numCorrectUnits++; 		
				break;	
			} 
			
		}//end of orderHistory Loop
	} // updateSoftOrderNumerator
	
	*/
	
	public int incrementRawDispatch (int eventID)
	{
	    for (EventHistoryVO event : eventHistory) {
	        if (event.eventID == eventID)
	        {
	            //event.rawDispatch++;
	            //return event.rawDispatch;
	        	return 0;
	        }
	    }
	    return -1;
	}
	
	public int incrementRoleDispatch (int roleID, int eventID)
	{
	    for (EventHistoryVO event : eventHistory) {
            if (event.eventID == eventID)
            {               
                int newRoleDispatch = 0;
                
              /*  if (event.roleDispatch.containsKey(new Integer(roleID)))
                {
                    newRoleDispatch = ((Integer)event.roleDispatch.get(new Integer(roleID))) + 1;
                }
                else
                {
                    newRoleDispatch = 1;
                }
                
                event.roleDispatch.put(new Integer(roleID), new Integer (newRoleDispatch));
                return newRoleDispatch;*/
               
            }
        }
	    
	    return -1;
	}
	
	public boolean checkUniqueResource(int eventID, int resourceID)
	{
	    for (OrderHistoryVO order : orderHistory)
	    {
	        if (order.eventID == eventID)
	        {
	            return order.checkUniqueArrived(resourceID);
	        }
	    }
	    
	    return false;
	}
	
	
	public void storeEventHistory(int eventID, ScoreVO score, int durration, int timeStepLimit){
	
		ScenarioModel scenarioModel = ScenarioModel.instance();
		for (EventHistoryVO history : eventHistory){
		
			if (history.eventID == eventID){
				history.score = score;
				System.out.println("XXXXXXX " + eventID + " " + scenarioModel + " " + scenarioModel.Events.size() + " " + scenarioModel.getEvent(eventID));
				history.failComplete = Integer.toString(scenarioModel.getEvent(eventID).condition);
				history.locationID = scenarioModel.getEvent(eventID).locationId;
				history.eventTimeLimit = timeStepLimit;
				history.eventDuration = durration;
				history.initialSeverity = scenarioModel.getEvent(eventID).initialSevarity;
				history.timeOver = history.timeBegin + durration;
				history.completeType = scenarioModel.getEvent(eventID).completeType;
				break;					
			}			
		} // end loop			
	} // storeEventScores
	
	
	public EventHistoryVO getEventRecord(int eventID){
		
		for(int g = 0; g < eventHistory.size(); g++){
				
				if(eventHistory.get(g).eventID == eventID){
					
					return eventHistory.get(g);
				}
			
		} // g loop
		return null;
	}
	
	public OrderHistoryVO getOrderRecord(int eventID){
		
		for(int g = 0; g < orderHistory.size(); g++){
			
			if(orderHistory.get(g).eventID == eventID){
				
				return orderHistory.get(g);
			} // if
		
		} // g loop
	
		return null;
		
	} // getOrderRecord ...
	 
	
	/*
	public void hardOrderFail(int eventID) {
		
		for (OrderHistoryVO order : orderHistory){
			
			if (order.eventID == eventID){
				order.hardOrder = false;
				break;					
			}			
		} // end loop	
	}
	
	public void hardRoleOrderFail(int eventID) {
        
        for (OrderHistoryVO order : orderHistory){
            
            if (order.eventID == eventID){
                order.hardRoleOrder = false;
                break;                  
            }           
        } // end loop   
    }
    */
	
	
	
	
	
} // HistoryModel..
