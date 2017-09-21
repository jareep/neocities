package edu.psu.ist.neocities.value;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

@Root
@Default (DefaultType.FIELD)
public class QuestionAnswerVO {

    @Attribute
    public int answerID;
    
    public String answerText;
    
    public String answerValue;
    
    public int correct;             // -1 for needs checked, 0 for incorrect, 1 for correct
    
    public QuestionAnswerVO()
    {
        super ();
    }
    
    public QuestionAnswerVO(int answerID, String answerText,
			String answerValue, int correct) {
		super();
		this.answerID = answerID;
		this.answerText = answerText;
		this.answerValue = answerValue;
		this.correct = correct;
	}

	/***********************************************
     *                 Getters                     *  
     ***********************************************/

    public int getAnswerID() {
        return answerID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public int getCorrect() {
        return correct;
    }
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    
    
}
