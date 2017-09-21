package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bhellar
 * @date 9-11-08
 * @description ThreatVO defines the Threat Data Variables
 * 
 */
public class PauseVO {
	
	/****************************************************************
	 * Variables (Should be private, Public for purposes of DS-RemoteClass
	 ****************************************************************/
	public int pauseID; //primary key
	
	public String msg; //this is the information message sent  
	public String link;//this is the hyperlink (optional) that can be sent to clients
	
	public long dispatchTime = 0; //Start time from the beginning of the SIM from when to launch the Pause 
	public long pauseDuration = 0; // How long the pause should last
	
	public int introSec = 0;   // How long the intro should last
	
	public List<QuestionVO> questionList = new ArrayList<QuestionVO>();
	
	
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public PauseVO() {
	   
	}
	
	//example row = 1, "Take the SAGAT Survey", "http://link.com"
	public PauseVO(int _pauseID, String _msg, String _link)
	{
		pauseID = _pauseID;
		msg = _msg;
		link = _link;
		

	}
	
	public PauseVO(int _pauseID, String _msg, String _link, int _introSec)
	{
	    pauseID = _pauseID;
	    msg = _msg;
	    link = _link;
	    introSec = _introSec;

	}
	
	//example row = 1, "Take the SAGAT Survey", "http://link.com", 10000, 30000
	public PauseVO(int _pauseID, String _msg, String _link, long _dispatchTime, long _pauseDuration)
	{
		pauseID = _pauseID;
		msg = _msg;
		link = _link;
		dispatchTime = _dispatchTime;
		pauseDuration = _pauseDuration;
		
	}
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	//--------------------   Getters   -----------------------------//
	public long getPauseID() {
		return pauseID;
	}
	public String getMSG() {
		return msg;
	}
	public String getLink() {
		return link;
	}
	public long getDispatchTime() {
		return dispatchTime;
	}
	public long getPauseDuration() {
		return pauseDuration;
	}
	
	//--------------------   Setters   -----------------------------//
	public void setPauseDispatchTime( long ms ){
		dispatchTime = ms;
	}
	
	public void setPauseDuration ( long ms ) {
		pauseDuration = ms;
	}
	
	//--------------------- Modifiers --------------------------------//
	
	public void addQuestion (QuestionVO question)
	{
	    this.questionList.add(question);
	}
	
	public boolean addQuestionAnswer(int questionID, QuestionAnswerVO questionAnswer)
	{
	    for (int i = 0; i < questionList.size(); i++)
	    {
	        if (questionList.get(i).questionID == questionID)
	        {
	            questionList.get(i).addAnswer(questionAnswer);
	            return true;
	        }
	    }
	    
	    return false;
	}
	
	   public boolean addQuestionImage(int questionID, String image)
	    {
	        for (int i = 0; i < questionList.size(); i++)
	        {
	            if (questionList.get(i).questionID == questionID)
	            {
	                questionList.get(i).setImage(image);
	                return true;
	            }
	        }
	        
	        return false;
	    }
}
