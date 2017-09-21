package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.value.OldValue.ScenarioVO;

/**
 * @author bhellar
 * @date 1-11-09
 * @description stores in a list the ScenarioVO Data
 */
public final class ScenarioModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static ScenarioModel instance = new ScenarioModel();
	
	private ScenarioModel() {
		// Required for Singleton Design Pattern
	}
			
	public static ScenarioModel instance(){
		return instance;
	}
	
	public static void setInstance(ScenarioModel _instance)
	{
		instance = _instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	CommController comm = CommController.instance();
	
	public List<ScenarioVO> data = new ArrayList<ScenarioVO>();	
	
	public ScenarioVO activeScenario; 
	
	/****************************************************************
	 * Functions
	 ****************************************************************/
	
	public void setActiveScenario(int scenarioID) {
		
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).scenarioID == scenarioID){
				activeScenario = data.get(j);
				comm.consoleMessage("=== Active Scenario: " + data.get(j).label + " ===");
			}
		}		
	}
	
	public void setScenarioTimeLimit(int scenarioID, int min, int sec) {
		
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).scenarioID == scenarioID){
				data.get(j).setGameStopTime(min, sec);
			}
		}		
	}
	
	public void addScenarioPause(int scenarioID, int pauseID){
		for (int j = 0; j <= data.size() - 1; j++){
			if (data.get(j).scenarioID == scenarioID){
				data.get(j).addPause(pauseID);
				//comm.consoleMessage("ScenarioModel - Adding Pause #" + pauseID + " to Scenario #" + scenarioID);
			}
		}
	}
	
	public void addScenarioBriefing(int scenarioID, int briefingID) {
	       for (int j = 0; j <= data.size() - 1; j++){
	            if (data.get(j).scenarioID == scenarioID){
	                data.get(j).addBriefing(briefingID);
	                //comm.consoleMessage("ScenarioModel - Adding Briefing #" + briefingID + " to Scenario #" + scenarioID);
	            }
	        }
	}
	
	
	
}
