package edu.psu.ist.neocities.value.OldValue;

/**
 * @author bhellar
 * @date 10-14-08
 * @description AnswerVO defines the answer for the ScoreEvent, defined and stored in the difficultyVO
 * 
 */
public class AnswerVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int difficultyID; //foreign key
	public int resourceID; //foreign key to resourceID Model
	
	//only used when eventVO.difficulty.unitCapBound = true;
	public int expected; //number of units of this resource that need to be sent.
	public int allocated; //number of units of this resource that are currently allocated
	
	public int rank = -1;  // rank of AnswerVO 
				
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public AnswerVO() {
			
	}
	
	//example row = 1, 2, 4
	public AnswerVO(int _difficultyID, int _resourceID, int _expected){
		difficultyID = _difficultyID;
		resourceID = _resourceID;
		expected = _expected;
		allocated = 0;
		
		
	
	} // AnswerVO
	
	
	/* The following constructor allows the possibility of adding a rank for sequence/order information */
	public AnswerVO(int _difficultyID, int _resourceID, int _expected, int _rank){
		difficultyID = _difficultyID;
		resourceID = _resourceID;
		expected = _expected;
		allocated = 0;
		rank = _rank;	
		
	
	} // AnswerVO
	
	
	
} 
