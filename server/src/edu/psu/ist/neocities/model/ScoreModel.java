package edu.psu.ist.neocities.model;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.ScoreVO;

/**
 * @author bhellar
 * @date 2-16-09
 * @description stores the Team Score as well as the individual event scores
 */
public final class ScoreModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static ScoreModel instance = new ScoreModel();
	
	private ScoreModel() {
		// Required for Singleton Design Pattern
	}
			
	public static ScoreModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<ScoreVO> data = new ArrayList<ScoreVO>();	
	
	public double scenarioScore = 0; //sum of the rawScores
	
	public double scenarioTotal = 0; // sum of the worstScores
	
	public double teamScore = 0; //sum of the normalScores
	
	public double teamAverage = 0; //AVG of the normalScores
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public void resetScores() {
		data.clear();
		scenarioScore = 0;
		teamScore = 0;
		scenarioTotal = 0;
		teamAverage = 0;
	}
	
	public void setTeamScore ( double normalScore ) {
		
		teamScore += normalScore;
		
		if( data.size() == 0){
			teamAverage = teamScore;
		}
		else {
			teamAverage = teamScore / data.size();
		}
		
	}
}
