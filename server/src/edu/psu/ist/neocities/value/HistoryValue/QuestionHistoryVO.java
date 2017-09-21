package edu.psu.ist.neocities.value.HistoryValue;

public class QuestionHistoryVO {
    public int questionId;
    public String answerValue;
    
    public int roleID; //foreign key to roleModel
    
    public int questionTime; // time when the question was first presented
    public int answerTime; // time when the user answered
    
    public int correct; //1 if correct 0 if no answer -1 if incorrect 
    
    public int totalUtterances;
    public int totalCharacters;
    public int totalWords;
    
    public int roleUtterances;
    public int roleCharacters;
    public int roleWords;
   
   
    public QuestionHistoryVO(int questionID, String answerValue, int roleID,
                                int questionTime, int answerTime, int correct,
                                int totalUtterances, int totalCharacters, int totalWords,
                                int roleUtterances, int roleCharacters, int roleWords) {
        super();
        this.questionId = questionID;
        this.answerValue = answerValue;
        this.roleID = roleID;
        this.questionTime = questionTime;
        this.answerTime = answerTime;
        this.correct = correct;
        this.totalUtterances = totalUtterances;
        this.totalCharacters = totalCharacters;
        this.totalWords = totalWords;
        this.roleUtterances = roleUtterances;
        this.roleCharacters = roleCharacters;
        this.roleWords = roleWords;
        
        
    }
    
    
    
    
}
