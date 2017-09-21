package edu.psu.ist.neocities.value;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root
@Default (DefaultType.FIELD)
public class QuestionVO 
{
    @Attribute
    public int questionID;
    
    @Attribute
    public String questionType;         // this should be SA or MC
    
    public String questionText;
    
    @ElementList (required = false)
    public List<QuestionAnswerVO> answers;
    
    public QuestionVO(int questionID, String questionType, String questionText) {
		super();
		this.questionID = questionID;
		this.questionType = questionType;
		this.questionText = questionText;
	}

	@Element (required = false)
    public String questionImage;
    
    public QuestionVO()
    {
        super();
    }
    
    /***********************************************
     *                 Getters                     *  
     ***********************************************/
    
    public int getQuestionID() {
        return questionID;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionText() {
        return questionText;
    }
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
    
    public void addAnswer(QuestionAnswerVO answer)
    {
        if (answers == null)
        {
        	answers = new ArrayList<QuestionAnswerVO>();
        }
    	this.answers.add(answer);
    }
    
}
