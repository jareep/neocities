package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.OldValue.AnswerVO;

/**
 * @author bhellar
 * @date 9-11-08
 * @description Redundant Data Store used for sending answers to Flex via BlazeDS
 */
public final class AnswerModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static AnswerModel instance = new AnswerModel();
	
	private AnswerModel() {
		// Required for Singleton Design Pattern
	}
			
	public static AnswerModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<AnswerVO> data = new ArrayList<AnswerVO>();	
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	// used to check the answer to log in the action history
	public Boolean checkAnswer(int difficultyID, int resourceID)
	{
	    
	    for (int i=0; i <= data.size() - 1; i++)
	    {
	        if (data.get(i).difficultyID == difficultyID)
	        {
	            if (data.get(i).resourceID == resourceID)
	            {
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}
	
	//used by NCServer to answers for a given difficulty
	public List<AnswerVO> getDifficultyAnswers ( int difficultyID) {
		List<AnswerVO> answers = new ArrayList<AnswerVO>();
				
		for (int i = 0; i <= data.size() - 1; i++){
			if (data.get(i).difficultyID == difficultyID){
				answers.add(data.get(i));
			}
		}
		
		return answers;
	}
	
}
