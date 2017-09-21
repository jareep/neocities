package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;
import java.util.List;

public class QuestionVO {
    
    
    /****************************************************************
     * Variables
     ****************************************************************/
    public int questionID;      // primary key for the question
        
    public String label;        // actual text of the question
    public String type;         // Question type, either MC (multiple choice), SA (short answer)
    public String image;        // location of an image to be used for the question
    
    public List<QuestionAnswerVO> answers = new ArrayList<QuestionAnswerVO>();
    
    
    
    /****************************************************************
     * Constructors
     ****************************************************************/
    
    public QuestionVO()
    {
        
    }
    
    public QuestionVO(int _questionID, String _label, String _type)
    {
        this.questionID = _questionID;
        
        this.label = _label;
        this.type = _type;
        this.image = "";
    }


    public int getQuestionID() {
        return questionID;
    }


    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }



    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
    
    public void addAnswer(QuestionAnswerVO questionAnswer)
    {
        this.answers.add(questionAnswer);
    }
    
}
