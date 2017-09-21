package edu.psu.ist.neocities.value;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import edu.psu.ist.neocities.util.TimeSet;


@Root
@Default (DefaultType.FIELD)
public class PauseVO {
    
    @Attribute
    public int id;
    
    public TimeSet pauseTime;           // in timesteps
    
    public TimeSet timeLimit;           // in timesteps
    
    @ElementList (required = false)
    public List <QuestionVO> questions;
    
    public PauseVO(int id) {
		super();
		this.id = id;
	}

	public PauseVO()
    {
        super();
    }
    
    /***********************************************
     *                 Getters                     *  
     ***********************************************/
    
    public int getId() {
        return id;
    }

    public TimeSet getPauseTime() {
        return pauseTime;
    }

    public TimeSet getTimeLimit() {
        return timeLimit;
    }
    
    public int getNumQuestions()
    {
    	return this.questions.size();
    }
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/

    public void setId(int id) {
        this.id = id;
    }

    public void setPauseTime(TimeSet pauseTime) {
        this.pauseTime = pauseTime;
    }

    public void setTimeLimit(TimeSet timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
    public void addQuestion (QuestionVO question)
    {
        if (questions == null)
        {
        	questions = new ArrayList<QuestionVO>();
        }
    	
        this.questions.add(question);
    }
    
    public void addQuestionAnswer(int questionID, QuestionAnswerVO qa)
    {
    	for (int i = 0; i < questions.size(); i++)
    	{
    		if (questions.get(i).questionID == questionID)
    		{
    			questions.get(i).addAnswer(qa);
    		}
    	}
    }
    
    public void addQuestionImage(int questionID, String image)
    {
    	for (int i = 0; i < questions.size(); i++)
    	{
    		if (questions.get(i).questionID == questionID)
    		{
    			questions.get(i).questionImage = image;
    		}
    	}
    }
    

}
