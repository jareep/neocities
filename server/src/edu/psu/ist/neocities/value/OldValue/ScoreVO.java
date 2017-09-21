package edu.psu.ist.neocities.value.OldValue;

/**
 * @author bhellar
 * @date 2-16-09
 * @description ScoreVO is an object that contains the scoring data for a given incidentID. 
 * 				This class has a Remote Class in AS. 
 *
 */
public class ScoreVO {
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public int incidentID; //foreign key
	
	public boolean status = false; // fails or wins..
		
	public double rawScore = 0; //un-weighted score
	public double worstScore = 0; //un weighted worst possible action area;
	public double normalScore = 0; //relative weighted score	
	public String grade = "F"; // a semantic grade"A - F" based on the normalScore
	
	public double dispatchRatio = 0; // percentage of correct units divided by total actions. 
	
				
	/****************************************************************
	 * Constructors
	 ****************************************************************/
	public ScoreVO() {
			
	}
	
	public ScoreVO(int _incidentID, boolean _status, double _rawScore, double _worstScore, double _normalScore, String _grade, double _dispatchRatio)
	{
		incidentID = _incidentID;
		status = _status;
		rawScore = _rawScore;
		worstScore = _worstScore;
		normalScore = _normalScore;
		grade = _grade;
		dispatchRatio = _dispatchRatio;
	}
	
}
