package edu.psu.ist.neocities.test;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import edu.psu.ist.neocities.NeoCitiesDS;
import edu.psu.ist.neocities.model.EnvironmentModel;
import edu.psu.ist.neocities.model.ScenarioModel;
import edu.psu.ist.neocities.util.TimeSet;
import edu.psu.ist.neocities.value.AnswerVO;
import edu.psu.ist.neocities.value.EventVO;
import edu.psu.ist.neocities.value.InformationVO;
import edu.psu.ist.neocities.value.LocationVO;
import edu.psu.ist.neocities.value.PauseVO;
import edu.psu.ist.neocities.value.QuestionAnswerVO;
import edu.psu.ist.neocities.value.QuestionVO;
import edu.psu.ist.neocities.value.ResourceVO;
import edu.psu.ist.neocities.value.RoleVO;
import edu.psu.ist.neocities.value.dataTypes.AlertData;
import edu.psu.ist.neocities.value.dataTypes.BuildingLocation;
import edu.psu.ist.neocities.value.dataTypes.CyberInfo;
import edu.psu.ist.neocities.value.dataTypes.CyberLocation;
import edu.psu.ist.neocities.value.dataTypes.TestInfoData;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.interfaces.*;

public class TestBuildScenario {
	
	
	public static void main(String[] args) throws Exception {
		
		Serializer serializer = new Persister();

		

		
		/*
		public EventVO(int eventID, int locationId, double initialSevarity,
				String eventMessage, double magCap)*/
		
		ScenarioModel s = buildScenario();
		//EnvironmentModel e = buildEnvironment();
		
		serializer.write(s, System.out);
		//serializer.write(e, System.out);
		
        //System.out.println(s.Events.toString());
		
		
		File result = new File("C:\\Users\\vfm105\\Desktop\\test.xml");
		
		System.out.println(serializer.validate(EnvironmentModel.class, result));
		//ScenarioModel s = serializer.read(ScenarioModel.class, result);
		
		/*
		serializer.write(s, System.out);
        System.out.println("");*/
			
	}
	
	public static ScenarioModel buildScenario()
	{
		ScenarioModel s = new ScenarioModel();
		s.setScenarioName("Example Scenario");
		
		//Set time information
		s.setTimeLimit(new TimeSet(10,30));
		
		// Setting Events
		s.addEvent(new EventVO(1, 1, 1.1, "Event Number 1", 8));
		s.setEventTimeLimit(1, new TimeSet(1,30));
		s.setEventDispatchTime(1, new TimeSet(0,30));
		s.addEventAnswer(1, new AnswerVO(1, .25, -1, 1));
		s.addEventAnswer(1, new AnswerVO(2, .75, 1, 1));
		
		s.addEvent(new EventVO(2, 2, 3.1, "Event Number 2", 8));
		s.setEventTimeLimit(2, new TimeSet(1,30));
		s.setEventDispatchTime(2, new TimeSet(2,30));
		s.addEventAnswer(2, new AnswerVO(2, .20, -1, 1));
		s.addEventAnswer(2, new AnswerVO(5, .80, 2, 1));
		

		//Setting Information
		s.addInformation(new InformationVO(1, "Alert"));
		s.setInfoData(2, new CyberInfo("Trojan Virus", 7, "10:52 AM"));
		//s.setInfoData(1, new AlertData("SnortID", "SnortDescr", "Date", "TimeStamp","SourceIP","SourcePort","DestIP","DestPort", null));
		s.setInfoDispatchTime(1, new TimeSet(0, 50));
		
		s.addInformation(new InformationVO(2, "CyberInfo"));
		s.setInfoData(2, new CyberInfo("Trojan Virus", 7, "10:52 AM"));
		s.setInfoDispatchTime(2, new TimeSet(2, 50));
		
		
		//Setting Pause
		s.addPause(new PauseVO(1));
		s.setPauseTime(1, new TimeSet(3,20));
		s.setPauseTimeLimit(1, new TimeSet(1, 0));
		
		//Adding questions to pause
		s.addPauseQuestion(1, new QuestionVO(1, "SA", "What is your name?"));
		
		s.addPauseQuestion(1, new QuestionVO(2, "MC", "What is 5+5?"));
		s.addQuestionAnswer(1, 2, new QuestionAnswerVO(1, "7", "7", 0));
		s.addQuestionAnswer(1, 2, new QuestionAnswerVO(2, "9", "9", 0));
		s.addQuestionAnswer(1, 2, new QuestionAnswerVO(3, "10", "10", 1));
		s.addQuestionAnswer(1, 2, new QuestionAnswerVO(4, "12", "12", 0));
		
		return s;
	}
	
	public static EnvironmentModel buildEnvironment()
	{
		EnvironmentModel e = new EnvironmentModel();
		
		e.setEnvironmentName("Example Environment");
		
		/* public LocationVO(int id, String type, LocationInterface data,
			double importance)*/
		
		// Adding Locations
		e.addLocation(new LocationVO(1, "Building", new BuildingLocation("IST Building", "IST Building State College, PA 16801", "ist.gif"), 7));
		e.addLocation(new LocationVO(2, "Cyber", new CyberLocation("MINDS Server", "130.203.163.251", "server.gif"), 90));
		
		//Adding Roles
		e.addRole(new RoleVO(1, "Police Dispatcher", "police.gif"));
		e.addRole(new RoleVO(2, "Cyber Security Analyst", "cyber.gif"));
		e.addRole(new RoleVO(3, "Help Desk", "help.gif"));
		
		//Adding Resources		
		e.addResource(new ResourceVO(1, "Police Investigator", 4, 1, 4, 3, false, "investigator.gif", "Nothing to Investigage", "Investigation in progress"));
		e.addResource(new ResourceVO(2, "Port Block", -1, 2, 0, 0, true, "port_block.gif", "Invalid Port Black", "Port has been blocked"));
		e.addResource(new ResourceVO(3, "Update Virus Definitions", -1, 3, 0, 0, true, "update.gif", "Nothing to update", "Virus Definitions updated"));
		
		return e;
	}
	
	
	
}
