package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;
import edu.psu.ist.neocities.value.OldValue.QuestionVO;
import edu.psu.ist.neocities.value.OldValue.QuestionAnswerVO;

import edu.psu.ist.neocities.value.OldValue.PauseVO;

/**
 * @author bhellar
 * @date 2-16-09
 * @description This model contains the pause messages that are scheduled to pause
 *              or interrupt the simulation 
 */
public final class PauseModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static PauseModel instance = new PauseModel();
	
	private PauseModel() {
		// Required for Singleton Design Pattern
	}
			
	public static PauseModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<PauseVO> data = new ArrayList<PauseVO>();

	
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	public PauseVO getPause(int pauseID){
		PauseVO pause = new PauseVO();
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getPauseID() == pauseID){				
				pause = data.get(i);
				break;
			}
		}
		
		return pause;		
	}
	
	//used by NCServer to set dispatchTime of the pause event
	public void setPauseDispatchTime( int pauseID, int minute, int seconds){
		
		long ms = ((minute * 60) + seconds) * 1000;
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getPauseID() == pauseID){				
				data.get(i).setPauseDispatchTime(ms);
				break;
			}
		}
	}
	
	//used by NCServer to set duration of the pause event
	public void setPauseDuration( int pauseID, int minute, int seconds){
		
		long ms = ((minute * 60) + seconds) * 1000;
		
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).getPauseID() == pauseID){				
				data.get(i).setPauseDuration(ms);
				break;
			}
		}
	}
	
	public void addPauseQuestion(int pauseID, QuestionVO question)
	{
       for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).getPauseID() == pauseID){               
                data.get(i).addQuestion(question);
                break;
            }
        }
	}
	
	public void addQuestionImage (int questionID, String image)
	{
       for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).addQuestionImage(questionID, image)) {          
                break;
            }
        }
	}
	
	public void addAnswerQuestion (int questionID, QuestionAnswerVO questionAnswer)
	{
       for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).addQuestionAnswer(questionID, questionAnswer)){               
                break;
            }
        }
	}
	
	public void clearModel()
	{
	    this.data = new ArrayList<PauseVO>();
	}
	
   public int getNumQuestions()
    {
       int x = 0;
       for (int i = 0; i <= data.size() - 1; i++){
           x+=data.get(i).questionList.size();
            
        }
       
       return x;
    }
	
}
