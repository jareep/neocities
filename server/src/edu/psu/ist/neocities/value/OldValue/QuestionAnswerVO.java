package edu.psu.ist.neocities.value.OldValue;

public class QuestionAnswerVO {
    
    /****************************************************************
     * Variables
     ****************************************************************/
       
    public String label;        // the label that is shown for the question
    public String value;        // the reported value if selected
    public int correct;         // -1 if incorrect, 0 if there is no correct answer, 1 if correct
    
    
    /****************************************************************
     * Constructors
     ****************************************************************/
    public QuestionAnswerVO()
    {
        
    }
    
    public QuestionAnswerVO(String _label, String _value, int _correct)
    {
        this.label = _label;
        this.value = _value;
        this.correct = _correct;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the correct
     */
    public int getCorrect() {
        return correct;
    }  

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    
    
    
}
