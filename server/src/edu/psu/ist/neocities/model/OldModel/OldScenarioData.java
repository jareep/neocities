package edu.psu.ist.neocities.model.OldModel;
/*
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.psu.ist.neocities.BriefingVO;
import edu.psu.ist.neocities.DifficultyVO;
import edu.psu.ist.neocities.IncidentVO;
import edu.psu.ist.neocities.value.AnswerVO;
import edu.psu.ist.neocities.value.PauseVO;
import edu.psu.ist.neocities.value.QuestionAnswerVO;
import edu.psu.ist.neocities.value.QuestionVO;
import edu.psu.ist.neocities.value.ResourceVO;
import edu.psu.ist.neocities.value.RoleVO;
import edu.psu.ist.neocities.value.OldValue.ScenarioVO;

public class OldScenarioData {
	public void loadEnvironmentData(){				
		//load Roles
			server.addRole(new RoleVO(1, "Police", "edu/psu/ist/neocities/assets/polteam.gif"));
			server.addRole(new RoleVO(2, "Fire", "edu/psu/ist/neocities/assets/fireteam.gif"));
			server.addRole(new RoleVO(3, "Hazmat", "edu/psu/ist/neocities/assets/hazteam.gif"));
			comm.consoleMessage("RO - Roles Loaded");
		//load Resources
			server.addResource(new ResourceVO(1, 1, "Investigator","edu/psu/ist/neocities/assets/resource_icons/P_InU.jpg", 3));
			server.addResource(new ResourceVO(2, 1, "Squad Car","edu/psu/ist/neocities/assets/resource_icons/P_Sq_Car.jpg", 5));
			server.addResource(new ResourceVO(3, 1, "SWAT","edu/psu/ist/neocities/assets/resource_icons/P_SWAT.jpg", 2));
			server.addResource(new ResourceVO(4, 2, "Investigator","edu/psu/ist/neocities/assets/resource_icons/F_InU.jpg", 3));
			server.addResource(new ResourceVO(5, 2, "Ambulance","edu/psu/ist/neocities/assets/resource_icons/F_Amb.jpg", 4));
			server.addResource(new ResourceVO(6, 2, "Fire Truck","edu/psu/ist/neocities/assets/resource_icons/F_Truck.jpg", 3));
			server.addResource(new ResourceVO(7, 3, "Investigator","edu/psu/ist/neocities/assets/resource_icons/H_InU.jpg", 3));
			server.addResource(new ResourceVO(8, 3, "Bomb Squad","edu/psu/ist/neocities/assets/resource_icons/H_BSq.jpg", 3));
			server.addResource(new ResourceVO(9, 3, "Chemical Truck","edu/psu/ist/neocities/assets/resource_icons/H_CTruck.jpg", 4));
			comm.consoleMessage("RO - Resources Loaded");
		//set Simulation Time & Date Constraints
			Calendar simulationDate = new GregorianCalendar(2000, 2, 17, 10, 15);
			server.setDate(simulationDate);
			server.setGameStartTime(7200);		
			comm.consoleMessage("RO - Server Variables Set");
		// create new Units
			server.loadUnits();
		}
		
		public void loadScenarioData(){		
		    
		    
		    // Time Limit Test
			comm.consoleMessage("Starting Load");
			//comm.consoleMessage("1");
			server.addIncident(new IncidentVO (1, "Fun with Lawn Darts", "Alumni tailgators near the south of the Beaver Stadium have injured each other playing a friendly game of lawn darts. Apparently the tailgators thought it would be a good idea to throw the darts as hard as possible. Please send units to treat the various reported puncture wounds. ", "i_medic.gif", 1));		
			//comm.consoleMessage("2");
			server.setSuccessMSG(1, "The alumni are in stable condition at the Mt Nittany Medical Center and have learned there lesson - at least till next year");
			server.setFailureMSG(1, "One of the alumni is in critical condition at the Mt Nittany Medical Center. You should have sent an ambulance sooner");
			server.addDifficulty(new DifficultyVO(1, 1, 4));		
			server.addDifficultyAnswer(new AnswerVO(1, 5, 0) );
			server.setEventDispatchTime(1, 0, 11);
			server.setEventTimeLimit(1, 2, 11);
			
			server.addIncident(new IncidentVO (2, "Flag Disapperance", "Reports are coming in from several sources indicating that Flags supporting Penn State are disappearing from tailgates across campus. No suspects have been identified. Please send units to investigate", "i_checkpoint.gif", 1));		
			server.setSuccessMSG(2, "The investigators on the scene discovered footprints leading to a large gathering of Ohio State supporters, after questioning the flags were quicly returned in good faith, no charges will be filed");
			server.setFailureMSG(2, "The tailgators have grown more angry and drunk with each passing minute their flags are gone. You should have sent a police investigator sooner");
			server.addDifficulty(new DifficultyVO(2, 2, 4));			
			server.addDifficultyAnswer(new AnswerVO(2, 1, 0) );
			server.setEventDispatchTime(2, 0, 31);
			server.setEventTimeLimit(2, 2, 31);
			
			server.addIncident(new IncidentVO (3, "Mystery Meat", "Several people at a tailgate near the Ag Arena report being served a 6 legged animal, that while delicious has made several people nauseous. While nausea went away, the question remains, What are they serving?", "i_death.gif", 1));		
			server.setSuccessMSG(3, "The investigators on the scene discovered the 6 legged animal to be none other than the legendary Triduckeon. None have surpassed its glory as the ultimate tailgating food");
			server.setFailureMSG(3, "Not enough hazmat investigators were on scene to uncover the mystery of the 6 legged animal.");
			server.addDifficulty(new DifficultyVO(3, 3, 4));		
			server.addDifficultyAnswer(new AnswerVO(3, 7, 0) );
			server.setEventDispatchTime(3, 1, 01);
			server.setEventTimeLimit(3, 3, 01);
			
			server.addIncident(new IncidentVO (4, "Blue Man Strikes Again", " Witness claim seeing a caped man dressed entirely in blue spandex running across the roofs of the RV parked down below the baseball stadium. The individual is most likely inebriated, the use of non-lethal force is authorized. ", "i_shoot.gif", 2));		
			server.setSuccessMSG(4, "The would-be 'blue man' was tripping off some acid given to him by a stranger claiming it was 'free candy'. Regardless of his excuse, atleast you acted before he could hurt anyone or himself.");
			server.setFailureMSG(4, "More police officers were needed to arrest mysterious blue man who was last scene hopping across the trailer rooftops.");
			server.addDifficulty(new DifficultyVO(4, 4, 4));	
			server.addDifficultyAnswer(new AnswerVO(4, 2, 0) );
			server.setEventDispatchTime(4, 1, 31);
			server.setEventTimeLimit(4, 3, 31);
			
			server.addIncident(new IncidentVO (5, "Charcoal Mishap", "Tailgators were attempting to clean up when they accidentally spilled their charcoal grill onto their ground catching fire to the grass and their paper plates and supplies. Please send units to assist.", "i_smoke.gif", 2));		
			server.setSuccessMSG(5, "Aside from a ruined grill and supplies, everyone will live to see another day, and more importantly be ready to see the game!");
			server.setFailureMSG(5, "Without assistance from a fire truck, the tailgators were unsuccessful in putting out their own fire, causing the fire to spread to a nearby tailgate before it could be put out. ");
			server.addDifficulty(new DifficultyVO(5, 5, 4));		
			server.addDifficultyAnswer(new AnswerVO(5, 6, 0) );
			server.setEventDispatchTime(5, 2, 01);
			server.setEventTimeLimit(5, 4, 01);
			
			server.addIncident(new IncidentVO (6, "Propane Disposal", "University Police have caught a drunk man tailgating with propane instead of charcoal. One of the propane tanks is unstable and needs to be disposed of before causing a potentially explosive situation in today's hot sun", "i_bombexp.gif", 2));		
			server.setSuccessMSG(6, "The Bomb Squad was successful in removing the propane thanks to your quick response!");
			server.setFailureMSG(6, "Without the assistance of the Bomb Squad, authorities were unable to dispose of the propane, causing several tanks to explode in containment.");
			server.addDifficulty(new DifficultyVO(6, 6, 4));		
			server.addDifficultyAnswer(new AnswerVO(6, 8, 0) );
			server.setEventDispatchTime(6, 2, 31);
			server.setEventTimeLimit(6, 4, 31);
			
			server.addIncident(new IncidentVO (7, "JoePA Security", "Please send units to escort JoePA from his house to the stadium. Although no specific threats have been issued by leering Ohio State Fans, its better to be safe than sorry. ", "i_escort.gif", 1));		
			server.setSuccessMSG(7, "The local SWAT team were effective in providing security for JoePA, assuring than no one could lay harm to our legendary coach");
			server.setFailureMSG(7, "Without the presense of the SWAT team the local security force was unaffective in providing security, allowing one rebel OSU fan to throw a pie at our couch. This clip will air on SportsCenters for a month - an embarassment to us all! ");
			server.addDifficulty(new DifficultyVO(7, 7, 4));		
			server.addDifficultyAnswer(new AnswerVO(7, 3, 0) );
			server.setEventDispatchTime(7, 3, 01);
			server.setEventTimeLimit(7, 5, 01);
			
			server.addIncident(new IncidentVO (8, "Soilant Green", " The prodigies in our Turf Grass Management department have spilled the containers of a new prototype fertilizer that was used on football fields prior to today's game. Please send units to assist the clean-up ", "i_chemical.gif", 1));		
			server.setSuccessMSG(8, "The Soilant green was cleaned up and then properly spread across the turf of Beaver Stadium providing a nice green shimmer to the field");
			server.setFailureMSG(8, "Soilant green is people! If only you would have sent a Chemical Truck to clean up the mess than maybe we could have prevented the public from finding out the truth! ");
			server.addDifficulty(new DifficultyVO(8, 8, 4));		
			server.addDifficultyAnswer(new AnswerVO(8, 9, 0) );
			server.setEventDispatchTime(8, 3, 31);
			server.setEventTimeLimit(8, 5, 31);
			
			server.addIncident(new IncidentVO (9, "Zombie Nation", "Concerns over the stability of the north end of beaver stadium have arisen due to the popularity of the song Zombi Nation. Please send a unit to investigate", "i_civriot.gif", 1));		
			server.setSuccessMSG(9, "Too much dancing could have caused strain on the girders, due to your proper investigation, announces will limit the use of the song during the game.");
			server.setFailureMSG(9, "You should have sent a fire investigator to discover that the incessant dancing has caused stress line fractures in North end of the stadium. Repairs will cost millions of doll-hairs.");
			server.addDifficulty(new DifficultyVO(9, 9, 4));	
			server.addDifficultyAnswer(new AnswerVO(9, 4, 0) );
			server.setEventDispatchTime(9, 3, 31);
			server.setEventTimeLimit(9, 5, 31);
			
			// Arts Fest Complex Events			
			
			server.addIncident(new IncidentVO (21, "Street Fighting Man", "The Rolling Stones would not be proud. A drunken brawl has consumed the alleyway between the brewery and Tony's Big Easy. Please send units to quell the fight and take care of the injured", "i_crimact.gif", 3));		
			server.setSuccessMSG(21, "The street fighters were arrested, several of whom are in stable condition in Mt Nittany Medical Center and have learned there lesson - at least till next year");
			server.setFailureMSG(21, "One of the street fighters is in critical condition in Mt Nittany Medical Center. The others are running amok across Beaver Canyon.");
			server.addDifficulty(new DifficultyVO(21, 21, 6));		
			server.addDifficultyAnswer(new AnswerVO(21, 5, 0) );
			server.addDifficultyAnswer(new AnswerVO(21, 2, 0) );
			server.setEventDispatchTime(21, 0, 16);
			server.setEventTimeLimit(21, 3, 16);
			
			server.addIncident(new IncidentVO (22, "Fireworks go Boom in the Night", "Fireworks also go Boom on people's rooftops. Fortunately while no one was hurt, authorities are concerned about the safety of the building after the explsion and are also requesting removal of the fireworks", "i_bombthr.gif", 3));		
			server.setSuccessMSG(22, "The fireworks were removed and the building was found to be safe. ");
			server.setFailureMSG(22, "The building was condemned and several of the fireworks have gone missing from the inventory");
			server.addDifficulty(new DifficultyVO(22, 22, 6));		
			server.addDifficultyAnswer(new AnswerVO(22, 4, 0) );
			server.addDifficultyAnswer(new AnswerVO(22, 8, 0) );
			server.setEventDispatchTime(22, 0, 36);
			server.setEventTimeLimit(22, 3, 36);
			
			server.addIncident(new IncidentVO (23, "Crime of Science", "Several beakers of EthylMethylDeath were stolen from the Chemistry Building tonight. The theives also knocked over several barrels of hazardous chemicals which also happend to be in the lab. Please send units to investigate the theft and clean up the mess. ", "i_chemical.gif", 3));		
			server.setSuccessMSG(23, "The investigators on the scene discovered the chemicals were stolen by students looking to paint themselves blue and white. The idiots apologized for knocking over the barrels, but the jokes on them as exposure to the chemicals have given them explosive diahhrea.");
			server.setFailureMSG(23, "Who knows why someone would commit such a dastardly crime, perhaps one day we can know the truth behind this great caper.");
			server.addDifficulty(new DifficultyVO(23, 23, 6));		
			server.addDifficultyAnswer(new AnswerVO(23, 1, 0) );
			server.addDifficultyAnswer(new AnswerVO(23, 9, 0) );
			server.setEventDispatchTime(23, 1, 01);
			server.setEventTimeLimit(23, 4, 01);
			
			server.addIncident(new IncidentVO (24, "Oh Noes -- Riots!", " Town has gone insane! People are everywhere tearing down street signs, turning over cars, burning couches, and throwing molotov cocktails! Get your butts down here and solve this problem! ", "i_civriot.gif", 5));		
			server.setSuccessMSG(24, "Hurray for Response, You all responded so well that people miraculously stopped rioting and started holding hands and singing Kum-Bi-Yah!");
			server.setFailureMSG(24, "Oh the Humanity! This is going to be on the national news again, a major embarrassment to the university1");
			server.addDifficulty(new DifficultyVO(24, 24, 8));	
			server.addDifficultyAnswer(new AnswerVO(24, 2, 0) );
			server.addDifficultyAnswer(new AnswerVO(24, 3, 0) );
			server.addDifficultyAnswer(new AnswerVO(24, 5, 0) );
			server.addDifficultyAnswer(new AnswerVO(24, 6, 0) );
			server.addDifficultyAnswer(new AnswerVO(24, 8, 0) );
			server.addDifficultyAnswer(new AnswerVO(24, 9, 0) );
			server.setEventDispatchTime(24, 1, 31);
			server.setEventTimeLimit(24, 4, 31);
					
			// Quick and Dirty Police Only Test (For Development Purposes)
			
			server.addIncident(new IncidentVO (50, "Theft Reported at Nittany Plaza", "That was such a silly show from the 80s. Investigate This!", "i_checkpoint.gif", 4 ));		
			server.setSuccessMSG(50, "You win at life!");
			server.setFailureMSG(50, "You fail at life!");
			server.addDifficulty(new DifficultyVO(50, 50, 4, 10));				
			server.addDifficultyAnswer(new AnswerVO(50, 1, 1, 1) );
			server.addDifficultyAnswer(new AnswerVO(50, 2, 1, 2) );
			server.addDifficultyAnswer(new AnswerVO(50, 3, 1, 3) );		
			server.setEventDispatchTime(50, 0, 6);
			server.setEventTimeLimit(50, 1, 56);
				
			server.addIncident(new IncidentVO (51, "Traffic Accident", "I love watching this show, does that make me old? Send a squad Car", "i_deadbody.gif", 3));		
			server.setSuccessMSG(51, "You win at life!");
			server.setFailureMSG(51, "You fail at life!");
			server.addDifficulty(new DifficultyVO(51, 51, 5));		
			server.addDifficultyAnswer(new AnswerVO(51, 2, 0) );
			server.setEventDispatchTime(51, 0, 16);
			server.setEventTimeLimit(51, 1, 51);
			
			server.addIncident(new IncidentVO (52, "Security Detail Request", "I love it when a plan comes together. SWAT This!", "i_shoot.gif", 1));		
			server.setSuccessMSG(52, "You win at life!");
			server.setFailureMSG(52, "You fail at life!");
			server.addDifficulty(new DifficultyVO(52, 52, 4));	
			server.addDifficultyAnswer(new AnswerVO(52, 3, 0) );
			server.setEventDispatchTime(52, 0, 26);
			server.setEventTimeLimit(52, 1, 51);
			
			server.addIncident(new IncidentVO (53, "Bank Robbery in Progress", "With our powers combined we form Captain Planet!.. oh wait I mean Voltron!", "i_smoke.gif", 4));		
			server.setSuccessMSG(53, "You win at life!");
			server.setFailureMSG(53, "You fail at life!");
			server.addDifficulty(new DifficultyVO(53, 53, 6));	
			server.addDifficultyAnswer(new AnswerVO(53, 1, 0) );
			server.addDifficultyAnswer(new AnswerVO(53, 2, 0) );
			server.addDifficultyAnswer(new AnswerVO(53, 3, 0) );		
			server.setEventDispatchTime(53, 0, 46);
			server.setEventTimeLimit(53, 1, 56);
			
			// ONR Scenario 1 v8 9.24.09

	        server.addIncident(new IncidentVO (101, "Football Weekend Briefing", "Unit(s) needed to deliver a talk for the upcoming football weekend on how to identify potential bombs and bomb-making materials.", "i_civriot.gif", 2));     
	        server.setSuccessMSG(101, "Everyone is prepared and ready for the football weekend festivities!");
	        server.setFailureMSG(101, "Not enough units showed up to the briefing, you wasted everyone's time."); 
	        server.addDifficulty(new DifficultyVO(101, 101, 2));        
	        server.addDifficultyAnswer(new AnswerVO(101, 7, 0));
	        server.setEventDispatchTime(101, 0, 11);
	        server.setEventTimeLimit(101, 4, 11);   
	        
	        server.addIncident(new IncidentVO(113,"Tanker Collision", " A tanker carrying aqueous ammonia has collided with a large truck. The driver thinks the tanker may explode within 60 minutes. Units are needed in the following order: FIRST to clear the area of on-lookers, SECOND to control the flames, and THIRD to cleanup the chemical material.", "i_vehinc.gif", 3));
	        server.setSuccessMSG(113, "The spilled chemicals from the tanker were cleaned up!");
	        server.setFailureMSG(113, "The chemicals have sunk into the water table, which cause a massive, expensive clean-up!");
	        server.addDifficulty(new DifficultyVO(113, 113, 3, 20));
	        server.addDifficultyAnswer(new AnswerVO(113, 2, 1, 1));
	        server.addDifficultyAnswer(new AnswerVO(113, 6, 1, 2));
	        server.addDifficultyAnswer(new AnswerVO(113, 9, 1, 3));
	        server.setEventDispatchTime(113, 0, 41);
	        server.setEventTimeLimit(113, 4, 41);
	        
	        server.addIncident(new IncidentVO(102,"Escort a Senator", " Pennsylvania Senator Bob Casey is in town for the football game. Unit[s] requested to escort him to a luncheon being held in his honor.", "i_escort.gif", 1));
	        server.setSuccessMSG(102, "Thank you for providing security for the senator-we all feel much safer now.");
	        server.setFailureMSG(102, "No one showed up to escort the senator.");
	        server.addDifficulty(new DifficultyVO(102, 102, 1));
	        server.addDifficultyAnswer(new AnswerVO(102, 3, 0));
	        server.setEventDispatchTime(102, 1, 26);
	        server.setEventTimeLimit(102, 5, 26);
	        
	        server.addIncident(new IncidentVO (103, "Smoking Kills", "Caller reports falling asleep with a lit cigarette has led to a fire in his apartment.  Resident is unable to contain the fire in the room and needs assistance.","i_resfire.gif", 2));       
	        server.setSuccessMSG(103, "The fire has been put out successfully before it could spread to the rest of the house.");
	        server.setFailureMSG(103, "Unfortunately the fire has spread, destroying the entire residence.");
	        server.addDifficulty(new DifficultyVO(103, 103, 2));        
	        server.addDifficultyAnswer(new AnswerVO(103, 6, 0));
	        server.setEventDispatchTime(103, 1, 56);
	        server.setEventTimeLimit(103, 5, 56);   
	        
	        server.addIncident(new IncidentVO (104, "Field Chemical Removal", "Director requests disposal of a large number of barrels containing expired lawn treatment chemicals found in the basement of Beaver Stadium. Some containers may be volatile.  Units are advised to proceed with caution. ","i_toxinfect.gif", 3));      
	        server.setSuccessMSG(104, "The chemicals were successfully removed from the stadium. ");
	        server.setFailureMSG(104, "A few of the barrels leaked their chemicals into the ground water preventing the use of university drinking fountains for a week");
	        server.addDifficulty(new DifficultyVO(104, 104, 3));    
	        server.addDifficultyAnswer(new AnswerVO(104, 9, 3));
	        server.setEventDispatchTime(104, 2, 26);
	        server.setEventTimeLimit(104, 6, 26);
	        
	        server.addIncident(new IncidentVO (107, "Luncheon Nausea", "People at the senator's luncheon report feeling nauseous. Units requested for the treatment of multiple victims and to collect and test samples of the food.", "i_medic.gif", 2));      
	        server.setSuccessMSG(107, "The Senator and his staff are recovering at the hospital.");
	        server.setFailureMSG(107, "A deadly strain of bacteria has spread to cafeterias across campus, and numerous people are becoming sicker and sicker.");
	        server.addDifficulty(new DifficultyVO(107, 107, 2));        
	        server.addDifficultyAnswer(new AnswerVO(107, 5, 1));
	        server.addDifficultyAnswer(new AnswerVO(107, 7, 1));
	        server.setEventDispatchTime(107, 2, 56);
	        server.setEventTimeLimit(107, 6, 56);
	        
	        server.addIncident(new IncidentVO (105, "Possible Student Rave", "Private citizen reports 'very loud music' coming from West Halls dormitory. Party involving underage drinking is a possibility. Units needed to investigate the situation and make possible arrests.", "i_civdemo.gif", 2));      
	        server.setSuccessMSG(104, "The students have been dispersed from the party.");
	        server.setFailureMSG(104, "The students have run amok and completely trashed the building.");
	        server.addDifficulty(new DifficultyVO(105, 105, 2));    
	        server.addDifficultyAnswer(new AnswerVO(105, 1, 0));
	        server.addDifficultyAnswer(new AnswerVO(105, 2, 0));
	        server.setEventDispatchTime(105, 3, 29);
	        server.setEventTimeLimit(105, 7, 29);
	        
	        server.addIncident(new IncidentVO (106, "Old Main Frame Shoppe Fire","Employees report that an apartment has caught fire and spread to nearby store below. Units needed to suppress fire and evaluate the building's sprinkler system. ", "i_nresfire.gif", 2));        
	        server.setSuccessMSG(106, "The fire has been put out successfully before it could spread further.");
	        server.setFailureMSG(106, "Unfortunately the fire has spread to the nearby store destroying both the apartment and the store below");
	        server.addDifficulty(new DifficultyVO(106, 106, 2));
	        server.addDifficultyAnswer(new AnswerVO(106, 4, 0));        
	        server.addDifficultyAnswer(new AnswerVO(106, 6, 0));
	        server.setEventDispatchTime(106, 3, 59);
	        server.setEventTimeLimit(106, 7, 59);
	        
	        server.addIncident(new IncidentVO (119, "No Go on the Logo", "Small group of students seen pouring strange blue and white chemicals on the Old Main lawn to try and create the Nittany Lion logo. Units need to arrive in the following order: FIRST to collect samples of the material and SECOND to interview possible witnesses.", "i_chemical.gif", 2));        
	        server.setSuccessMSG(119, "The students have seen the error in their ways and have stuck to dying themselves rather than Old Main Lawn");
	        server.setFailureMSG(119, "Looks like the logo will be stained in the lawn for several months, President Spanier and the alumni association are NOT happy.");
	        server.addDifficulty(new DifficultyVO(119, 119, 2, 0));     
	        server.addDifficultyAnswer(new AnswerVO(119, 1, 1, 2));
	        server.addDifficultyAnswer(new AnswerVO(119, 7, 1, 1));
	        server.setEventDispatchTime(119, 4, 30);
	        server.setEventTimeLimit(119, 8, 30);
	        
	        server.addIncident(new IncidentVO (108, "Rush the Field", "Caller reports that students have rushed the field at Beaver Stadium due to their anger over Penn State's losing the football game.  Units needed to control the mob.","i_civdemo.gif", 2));     
	        server.setSuccessMSG(108, "The mob is under control.");
	        server.setFailureMSG(108, "The mob went out of control, pulling down the goal post.");
	        server.addDifficulty(new DifficultyVO(108, 108, 2));        
	        server.addDifficultyAnswer(new AnswerVO(108, 3, 0));
	        server.setEventDispatchTime(108, 5, 00);
	        server.setEventTimeLimit(108, 9, 00);
	        
	        server.addIncident(new IncidentVO (118, "Arson Anyone?", "Units[s] needed to put out a small fire and investigate suspected arson at small store downtown.", "i_fireinc.gif", 1));  
	        server.setSuccessMSG(118, "The investigation was successfully completed- the fire was deemed an accident.");
	        server.setFailureMSG(118, "No one ever showed up to put out the fire or investigate the arson.");
	        server.addDifficulty(new DifficultyVO(118, 118, 1));        
	        server.addDifficultyAnswer(new AnswerVO(118, 4, 0));
	        server.setEventDispatchTime(118, 5, 30);
	        server.setEventTimeLimit(118, 9, 30);
	        
	        server.addIncident(new IncidentVO (109, "You'll Be Sorry!", "An irate caller yells 'I told you there'd be consequences if we lost the game. I’m going to blow this place! You'll be sorry!'  The call has been traced to the locker room in Beaver Stadium.","i_civriot.gif", 2));
	        server.setSuccessMSG(109, "The bomb the caller left in the locker room was discovered and dismantled.");
	        server.setFailureMSG(109, "A bomb detonated in the locker room, killing the entire football team and ensuring they'd never lose another game again.");
	        server.addDifficulty(new DifficultyVO(109, 109, 2));        
	        server.addDifficultyAnswer(new AnswerVO(109, 8, 0));
	        server.setEventDispatchTime(109, 6, 00);
	        server.setEventTimeLimit(109, 10, 00);
	        
	        server.addIncident(new IncidentVO(110, "Loitering Vehicle", "A suspicious van with a large Ohio State logo on the side is parked outside Citizens Bank on South Atherton. Inside the bank seems very crowded.", "i_vehhij.gif", 3));
	        server.setSuccessMSG(110, "The van was filled with a group of angry Buckeye fans. Fortunately you prevented their intentions of vandalizing the bank.");
	        server.setFailureMSG(110, "The van contained a group of angry Buckeye fans who proceeded to steal some 'fat loot' and get away!");
	        server.addDifficulty(new DifficultyVO(110, 110, 3, 15));
	        server.addDifficultyAnswer(new AnswerVO(110, 5, 1, 1));
	        server.addDifficultyAnswer(new AnswerVO(110, 1, 1, 2));
	        server.addDifficultyAnswer(new AnswerVO(110, 9, 1, 3));
	        server.setEventDispatchTime(110, 6, 30);
	        server.setEventTimeLimit(110, 10, 30);
	        
	        server.addIncident(new IncidentVO (117, "Bar tour gone bad", "A student called to report her friend vomiting and collapsing in the restroom at a local bar. Unit requested for treatment.", "i_medic.gif", 1));     
	        server.setSuccessMSG(117, "The student was taken to the hospital and had her stomach pumped. Thank you for your quick response.");
	        server.setFailureMSG(117, "The student was found face-down in a puddle outside the bar. She's alive, but just barely.");
	        server.addDifficulty(new DifficultyVO(117, 117, 1));    
	        server.addDifficultyAnswer(new AnswerVO(117, 5, 0));
	        server.setEventDispatchTime(117, 7, 15);
	        server.setEventTimeLimit(117, 11, 15);      
	        
	        server.addIncident(new IncidentVO (111, "Alcohol-Related Disturbance", "The front desk manager has requested that a small crowd of intoxicated people lingering outside the Nittany Lion Inn be dispersed. One individual seems to be passed out.", "i_medic.gif", 2));     
	        server.setSuccessMSG(111, "The student was taken to the hospital and had her stomach pumped. Thank you for your quick response.");
	        server.setFailureMSG(111, "The student was found faced down in a puddle outside the Inn. She's alive, but just barely.");
	        server.addDifficulty(new DifficultyVO(111, 111, 2));    
	        server.addDifficultyAnswer(new AnswerVO(111, 5, 1));
	        server.addDifficultyAnswer(new AnswerVO(111, 3, 1));
	        server.setEventDispatchTime(111, 7, 45);
	        server.setEventTimeLimit(111, 11, 45);
	        
	        server.addIncident(new IncidentVO(112,"Chemical Investigation", "Units requested to assist researchers with an inspection of a large chemical inventory.  Facility coordinator reports that approximately two to three rooms of volatile materials need to be catalogued. One of the barrels also seems to be leaking.", "i_chemical.gif", 2));
	        server.setSuccessMSG(112, "The chemicals have been transported to a safe location and catalogued for further investigation.");
	        server.setFailureMSG(112, "The chemicals were not fully catalogued, and some toxic waste has leaked all over the floor.");
	        server.addDifficulty(new DifficultyVO(112, 112, 2));
	        server.addDifficultyAnswer(new AnswerVO(112, 7, 0));
	        server.addDifficultyAnswer(new AnswerVO(112, 9, 0));
	        server.setEventDispatchTime(112, 8, 15);
	        server.setEventTimeLimit(112, 12, 15);
	        
	        server.addIncident(new IncidentVO (114, "University Fight Club", "Several intoxicated males seen fighting outside the University Club on College Ave and Atherton. No weapons are reported involved but a very large crowd has gathered around them that is obstructing traffic.  Disperse crowd and arrest suspects.","i_crimact.gif", 2));        
	        server.setSuccessMSG(114, "The students were arrested and crowd was successfully dispersed");
	        server.setFailureMSG(114, "The crowd has gone into a blood frenzy after witnessing a fight, dozens of people are now injured, one reported missing.");
	        server.addDifficulty(new DifficultyVO(114, 114, 2));    
	        server.addDifficultyAnswer(new AnswerVO(114, 2, 0));
	        server.addDifficultyAnswer(new AnswerVO(114, 3, 0));
	        server.setEventDispatchTime(114, 8, 45);
	        server.setEventTimeLimit(114, 12, 45);
	        
	        server.addIncident(new IncidentVO (115, "Tick Tock", "A bomb threat has been called in for Paterno Library.  Units needed to search the building and to also investigate the capacity of the emergency fire walls.", "i_bombthr.gif", 2));      
	        server.setSuccessMSG(115, "The bomb was successfully neutralized.");
	        server.setFailureMSG(115, "The bomb exploded and the fire walls didn't hold, injuring several people nearby.");
	        server.addDifficulty(new DifficultyVO(115, 115, 2));    
	        server.addDifficultyAnswer(new AnswerVO(115, 8, 1));
	        server.addDifficultyAnswer(new AnswerVO(115, 4, 1));
	        server.setEventDispatchTime(115, 10, 30);
	        server.setEventTimeLimit(115, 13, 30);
	        
	        server.addIncident(new IncidentVO (116, "Traffic Build up", "Units needed to direct traffic at the intersection of College Ave and Atherton St. ","i_vehhij.gif", 1));      
	        server.setSuccessMSG(116, "Traffic was alleviated thanks to the presence of the officers.");
	        server.setFailureMSG(116, "Traffic is completely gridlocked and some of the drivers are starting to unpack their tailgating supplies right on the street!");
	        server.addDifficulty(new DifficultyVO(116, 116, 1));    
	        server.addDifficultyAnswer(new AnswerVO(116, 2, 0));
	        server.setEventDispatchTime(116, 10, 50);
	        server.setEventTimeLimit(116, 12, 50);  
	        
	        //ONR Scenario2 v8 9.24.09 
	        
	        server.addIncident(new IncidentVO (201, "Finals Briefing", "Units needed to attend a talk on criminal activity for the upcoming final exams week.", "i_civriot.gif", 1));      
	        server.setSuccessMSG(201, "All teams are prepared and ready for the upcoming finals week.");
	        server.setFailureMSG(201, "Not enough units showed up to the briefing. You wasted everyone's time."); 
	        server.addDifficulty(new DifficultyVO(201, 201, 1));    
	        server.addDifficultyAnswer(new AnswerVO(201, 1, 0));
	        server.setEventDispatchTime(201, 0, 05);
	        server.setEventTimeLimit(201, 4, 05);
	        
	        server.addIncident(new IncidentVO (206, "Flying Laptops", "An unstable student is reportedly throwing stolen laptops off the roof of Paterno Library. The items are injuring passersby and denting gas pipes below. Units needed to to evaluate the dented gas pipes for leaks, treat the victims, and make an arrest.  All units are needed on scene within 40 minutes.", "i_civriot.gif", 3));
	        server.setSuccessMSG(206, "The student was apprehended in a timely fashion; his victims were treated, the gas pipes repaired, and the laptops (both damaged and otherwise) taken in as evidence.");
	        server.setFailureMSG(206, "After exhausting his supply of silicon ammunition, the student proceeded to flee. His whereabouts are unknown; meanwhile, the air has taken on the unmistakable scent of leaking gas.");
	        server.addDifficulty(new DifficultyVO(206, 206, 3, 14));    
	        server.addDifficultyAnswer(new AnswerVO(206, 2, 1));
	        server.addDifficultyAnswer(new AnswerVO(206, 5, 1));
	        server.addDifficultyAnswer(new AnswerVO(206, 7, 1));
	        server.setEventDispatchTime(206, 0,35);
	        server.setEventTimeLimit(206, 4, 35);
	        
	        server.addIncident(new IncidentVO (205, "Collapsed Student", "Units needed to revive a collapsed student outside Willard Building. Student seems to be suffering from extreme exhaustion and dehydration.", "i_medic.gif", 2));
	        server.setSuccessMSG(205, "Medical staff arrived in time to assist the student before he could get any worse.");
	        server.setFailureMSG(205, "Without medical aid, the student's condition worsened. He is alive, but in critical condition.");
	        server.addDifficulty(new DifficultyVO(205, 205, 2));    
	        server.addDifficultyAnswer(new AnswerVO(205, 5, 0));
	        server.setEventDispatchTime(205, 1, 15);
	        server.setEventTimeLimit(205, 5, 15);
	        
	        server.addIncident(new IncidentVO (203, "Missing Belongings", "Units requested at Paterno Library. A student went to the bathroom and is now missing her laptop and backpack. She has found the culprit and is detaining him for possible arrest.", "i_crimact.gif", 2));
	        server.setSuccessMSG(203, "The police arrived and took the thief into custody. The victim's belongings were located and returned to her.");
	        server.setFailureMSG(203, "A momentary diversion of the victim's attention allowed the thief to escape. The whereabouts of both the thief and the stolen goods are unknown.");
	        server.addDifficulty(new DifficultyVO(203, 203, 2));    
	        server.addDifficultyAnswer(new AnswerVO(203, 2, 0));
	        server.setEventDispatchTime(203, 1, 45);
	        server.setEventTimeLimit(203, 5, 45);
	        
	        server.addIncident(new IncidentVO (204, "Overflowing Toilet", "A bathroom toilet was stuffed with Calculus notes and has overflowed on the second floor of Pollock Library. A male member of the campus prankster group, Pi Rho, most easily identified by their Greek letter tattoos, was seen exiting the bathroom when the event occurred..  Units needed to clear the biological waste.", "i_pirho.gif", 2));
	        server.setSuccessMSG(204, "The waste was cleaned up and the toilet repaired before any major damage could occur.");
	        server.setFailureMSG(204, "The toilet continued to overflow, until the entire floor had to be shut down for decontamination and repair.");
	        server.addDifficulty(new DifficultyVO(204, 204, 2));    
	        server.addDifficultyAnswer(new AnswerVO(204, 9, 0));
	        server.setEventDispatchTime(204, 2, 15);    
	        server.setEventTimeLimit(204, 6, 15);
	        
	        server.addIncident(new IncidentVO(218,"How NOT to cook", " Kitchen fire reported at local eatery. Cooks have been unable to extinguish flames.", "i_nresfire.gif", 2));
	        server.setSuccessMSG(218, "The fire was put out thanks to your help");
	        server.setFailureMSG(218, "The fire got out of control, and burned the kitchen to the ground.");
	        server.addDifficulty(new DifficultyVO(218, 218, 2));
	        server.addDifficultyAnswer(new AnswerVO(218, 6, 2));
	        server.setEventDispatchTime(218, 2, 45);
	        server.setEventTimeLimit(218, 6, 45);
	        
	        server.addIncident(new IncidentVO (222, "Stampede at Forum", "A bomb threat was received by an instructor in the Forum Building, who panicked and pulled the fire alarm. Units are needed in the following order: FIRST to control the crowd of students running out of the building, SECOND to reset the fire alarm, and THIRD to sweep the area for bombs.", "i_civriot.gif", 3));
	        server.setSuccessMSG(222, "The crowd was brought under control with minimal injury. A bomb was found inside a utility closet; however, it was successfully defused before it could cause any injury or damage.");
	        server.setFailureMSG(222, "A bomb went off inside the building, causing extensive damage. Meanwhile, the stampede at the forum resulted in several broken limbs and seven students in critical condition.");
	        server.addDifficulty(new DifficultyVO(222, 222, 3, 0)); 
	        server.addDifficultyAnswer(new AnswerVO(222, 3, 1, 1));
	        server.addDifficultyAnswer(new AnswerVO(222, 4, 1, 2));
	        server.addDifficultyAnswer(new AnswerVO(222, 8, 1, 3));
	        server.setEventDispatchTime(222, 3, 05); // 3 05
	        server.setEventTimeLimit(222, 7, 05); // 7 05
	        
	        server.addIncident(new IncidentVO (212, "Plotting at Irvings", "An Irving’s employee has observed a group of individuals with Greek tattoos in the basement with suspicious bomb-like material. Units needed on scene to collect the explosive material.", "i_pirho.gif", 2));
	        server.setSuccessMSG(212, "The bomb-like material turned out to be the innards of an old laserdisc player. No need for concern.");
	        server.setFailureMSG(212, "The suspects disappeared shortly afterwards, leaving no trace of the suspicious materials. They could be anywhere now.");
	        server.addDifficulty(new DifficultyVO(212, 212, 2));    
	        server.addDifficultyAnswer(new AnswerVO(212, 8, 0));
	        server.setEventDispatchTime(212, 3, 45);
	        server.setEventTimeLimit(212, 7, 45);
	        
	        server.addIncident(new IncidentVO (215, "Sensitive Material", "A professor, transporting highly sensitive examination material, has requested an escort from the copy center to Thomas Building.", "i_escort.gif", 2));
	        server.setSuccessMSG(215, "Under the protection of an official escort, the professor was able to go about his business without incident.");
	        server.setFailureMSG(215, "The professor was forced to make the trip all by himself. He was fortunate enough to avoid misadventure; however, he is angry and has lodged an official complaint.");
	        server.addDifficulty(new DifficultyVO(215, 215, 2));    
	        server.addDifficultyAnswer(new AnswerVO(215, 3, 0));
	        server.setEventDispatchTime(215, 4, 15);
	        server.setEventTimeLimit(215, 8, 15);
	        
	        server.addIncident(new IncidentVO (209, "Naughty Snow Angels", "After losing a bet, a fraternity member has been spotted running around naked making snow angels.  However, the effects of the cold are beginning to severely affect him.  To avoid severe frostbite, units must be dispatched within 30 minutes to treat him and to make an arrest for public indecency.", "i_civriot.gif", 2));
	        server.setSuccessMSG(209, "Medical staff arrived in time to assist the student before he could get any worse... He was prompty arrested after being treated");
	        server.setFailureMSG(209, "Without medical aid, the student's condition worsened and he developed severe frostbite. He is alive, but surgeons had to remove his foot");
	        server.addDifficulty(new DifficultyVO(209, 209, 3, 10));    
	        server.addDifficultyAnswer(new AnswerVO(209, 2, 1));
	        server.addDifficultyAnswer(new AnswerVO(209, 5, 1));
	        server.setEventDispatchTime(209, 4, 45);
	        server.setEventTimeLimit(209, 8, 45);
	        
	        server.addIncident(new IncidentVO (216, "Buzzed Student", "A student has overdosed on a unknown substance rumored to boost wakefulness. She was last seen cartwheeling down Park Ave. Unit(s) needed to sedate the student.", "i_medic.gif", 2));
	        server.setSuccessMSG(216, "The student has received medical treatment and calmed down.");
	        server.setFailureMSG(216, "After knocking down an onlooker and tipping over several trashcans, the student disappeared. Her whereabouts are unknown.");
	        server.addDifficulty(new DifficultyVO(216, 216, 2));    
	        server.addDifficultyAnswer(new AnswerVO(216, 5, 0));
	        server.setEventDispatchTime(216, 5, 30);
	        server.setEventTimeLimit(216, 9, 25);
	        
	        server.addIncident(new IncidentVO (214, "A Stinky Situation", "Angry over his final exam grade, a student has thrown a homemade stink bomb into his professor’s office.  While another faculty member is detaining the student until authorities arrive, the professor is having a severe allergic reaction.  Therefore, resources are needed in the following order: FIRST to determine the chemical components of the stink bomb, SECOND to treat the professor, and THIRD to arrest the student.", "i_smoke.gif", 3));
	        server.setSuccessMSG(216, "The professor has received treatment and the student was arrested");
	        server.setFailureMSG(216, "The professor had to be taken to the hospital and the student was able to sneak away during the commotion.");
	        server.addDifficulty(new DifficultyVO(214, 214, 3, 0));    
	        server.addDifficultyAnswer(new AnswerVO(214, 2, 1, 3));
	        server.addDifficultyAnswer(new AnswerVO(214, 5, 1, 2));
	        server.addDifficultyAnswer(new AnswerVO(214, 7, 1, 1));
	        server.setEventDispatchTime(214, 6, 30);
	        server.setEventTimeLimit(214, 10, 15);
	        
	        server.addIncident(new IncidentVO (208, "Swine Flu Scare", "A concerned student has reported that his roomate is coughing and vomiting uncontrollably. Possible H1N1 infection may be present. Units needed for biological decontamination/quarantine in West Halls.", "i_toxinfect.gif", 2));
	        server.setSuccessMSG(208, "The presence of H1N1 was confirmed. The sick student was taken in for immediate treatment; furthermore, the other residents were quarantined, and the building shut down for decontamination.");
	        server.setFailureMSG(208, "The sick student's friends brought him to a hospital, where the presence of H1N1 was confirmed. Meanwhile, several more students at West Halls have started growing sick.");
	        server.addDifficulty(new DifficultyVO(208, 208, 2));    
	        server.addDifficultyAnswer(new AnswerVO(208, 9, 0));
	        server.setEventDispatchTime(208, 7, 10);
	        server.setEventTimeLimit(208, 10, 55);
	        
	        server.addIncident(new IncidentVO (219, "Shrine Redesign", "The Nittany lion shrine has been vandalized. Someone has painted a handlebar moustache and Greek symbols on the statue.  Units are needed for cleanup and to search for fingerprints.", "i_pirho.gif", 2));
	        server.setSuccessMSG(219, "The shrine has been restored to its original condition.");
	        server.setFailureMSG(219, "The shrine remains vandalized; meanwhile, the people waiting to be photographed with the lion are starting to look annoyed.");
	        server.addDifficulty(new DifficultyVO(219, 219, 3));    
	        server.addDifficultyAnswer(new AnswerVO(219, 1, 0));
	        server.addDifficultyAnswer(new AnswerVO(219, 9, 0));
	        server.setEventDispatchTime(219, 7, 50);
	        server.setEventTimeLimit(219, 11, 35);
	        
	        server.addIncident(new IncidentVO (210, "Wrestling Match", "A large group of students in the forum are attempting to wrestle the answer key for the final exam away from a proctor. Unit/s needed to control riot.", "i_civriot.gif", 2));
	        server.setSuccessMSG(210, "The riot was dispersed with minimal injury, and the ringleaders apprehended.");
	        server.setFailureMSG(210, "The riot grew to uncontrollable levels, resulting in several broken limbs and one student in critical condition. Those responsible for starting the riot were never found.");
	        server.addDifficulty(new DifficultyVO(210, 210, 2));
	        server.addDifficultyAnswer(new AnswerVO(210, 3, 0));
	        server.setEventDispatchTime(210, 8, 30);
	        server.setEventTimeLimit(210, 12, 15);
	        
	        server.addIncident(new IncidentVO (220, "Suspicious Luggage", "A small suitcase was left unattended at the departure gate at the University Park Airport.", "i_civriot.gif", 3));
	        server.setSuccessMSG(220, "The suitcase turned out to contain a homemade bomb. The area was cordoned off, and the bomb successfully defused.");
	        server.setFailureMSG(220, "The suitcase exploded, critically injuring three individuals who were entering the airport at the time.");
	        server.addDifficulty(new DifficultyVO(220, 220, 3, 20));    
	        server.addDifficultyAnswer(new AnswerVO(220, 3, 1, 1));
	        server.addDifficultyAnswer(new AnswerVO(220, 5, 1, 3));
	        server.addDifficultyAnswer(new AnswerVO(220, 8, 1, 2));
	        server.setEventDispatchTime(220, 10, 45);
	        server.setEventTimeLimit(220, 14, 30);
	        
	        server.addIncident(new IncidentVO (217, "Toxic Gas", "Students have been seen running out of the nutrition lab. The lab technician claims someone made a huge batch of chlorine gas. Unit(s) needed for cleanup.", "i_toxicgas.gif", 2)); 
	        server.setSuccessMSG(217, "The lab was cleaned up, and the matchbooks collected as evidence.");
	        server.setFailureMSG(217, "The nutrition lab remains unusable; meanwhile, further break-ins have been reported at several locations on campus.");
	        server.addDifficulty(new DifficultyVO(217, 217, 2));    
	        server.addDifficultyAnswer(new AnswerVO(217, 9, 0));
	        server.setEventDispatchTime(217, 11, 05);
	        server.setEventTimeLimit(217, 13, 50);
	        
	        server.addIncident(new IncidentVO (211, "Textbook Bonfire", "A bonfire filled with old textbooks has been sighted on the Old Main Lawn. Units needed to put out the blaze.", "i_fireinc.gif", 2));
	        server.setSuccessMSG(211, "The bonfire was put out before it could cause any injury or damage. Several copies of 'Advanced Yodeling' were rescued from the conflagration.");
	        server.setFailureMSG(211, "The bonfire was allowed to spread to the surrounding buildings, causing extensive damage. Thankfully, there were no major injuries.");
	        server.addDifficulty(new DifficultyVO(211, 211, 2));
	        server.addDifficultyAnswer(new AnswerVO(211, 6, 0));
	        server.setEventDispatchTime(211, 11, 15);   
	        server.setEventTimeLimit(211, 14, 00);
			
			//Ben Scenario 1
			
			server.addIncident(new IncidentVO (301, "Team Briefing", "Units needed for briefing on upcoming football weekend. Officials expect possible student rioting, wide-spread injuries, and severe intoxication if we lose the game.", "i_civriot.gif", 2));		
			server.setSuccessMSG(301, "All teams are prepared and ready for the football weekend festivities!");
			server.setFailureMSG(301, "An investigator from each team was required to attend the briefing. Someone didn't show up, therefore you fail at life.");
			server.addDifficulty(new DifficultyVO(301, 301, 3));		
			server.addDifficultyAnswer(new AnswerVO(301, 1, 1) );
			server.addDifficultyAnswer(new AnswerVO(301, 4, 1) );
			server.addDifficultyAnswer(new AnswerVO(301, 7, 1) );
			server.setEventDispatchTime(301, 0, 11);
			server.setEventTimeLimit(301, 2, 11);		
			
			server.addIncident(new IncidentVO (302, "Secret of the Ooze", "Frantic parents called 911 to report that their son was playing near an abandoned warehouse and cut himself on a rusty nail.  The child also noticed a neon green puddle of ooze nearby.  Units needed for treatment and investigation of the substance.", "i_radioact.gif", 2));		
			server.setSuccessMSG(302, "The child has been given a tetanus shot and the ooze has been collected. His parents thank you for the quick assistance");
			server.setFailureMSG(302, "The child has contracted lockjaw and the ooze has seeped into the ground.");
			server.addDifficulty(new DifficultyVO(302, 302, 3));	
			server.addDifficultyAnswer(new AnswerVO(302, 5, 0) );
			server.addDifficultyAnswer(new AnswerVO(302, 7, 0) );
			server.setEventDispatchTime(302, 0, 26);
			server.setEventTimeLimit(302, 2, 26);	
			
			server.addIncident(new IncidentVO(303,"Escort a Senator", "Unit[s] requested to escort Pennsylvania Senator Bob Casey and provide security during his campus tour", "i_escort.gif", 1));
			server.setSuccessMSG(303, "Thank you for providing security for the senator, we all feel much safer now.");
			server.setFailureMSG(303, "No one showed up to escort the senator");
			server.addDifficulty(new DifficultyVO(303, 303, 2));
			server.addDifficultyAnswer(new AnswerVO(303, 3, 0));
			server.setEventDispatchTime(303, 0, 31);
			server.setEventTimeLimit(303, 2, 31);
			
			server.addIncident(new IncidentVO (304, "Dormitory Fire Alarm", "Student Caller reports falling asleep with a lit cigarette has led to a fire in his residence.  Resident is unable to contain the fire in the room and needs assistance.", "i_schfire.gif", 2));		
			server.setSuccessMSG(304, "The fire has been put out successfully before it could spread to the rest of the dormitory");
			server.setFailureMSG(304, "Unfortunately the fire has spread to the nearby dorms, destroying the entire floor of the dormitory");
			server.addDifficulty(new DifficultyVO(304, 304, 3));		
			server.addDifficultyAnswer(new AnswerVO(304, 6, 0) );
			server.setEventDispatchTime(304, 0, 36);
			server.setEventTimeLimit(304, 2, 36);
						
			server.addIncident(new IncidentVO (305, "Possible Student Rave", "Private citizen reports 'very loud music' coming from an abandoned factory. Party involving underage drinking is a possibility. Units needed to investigate the situation and make possible arrests.", "i_escort.gif", 2));		
			server.setSuccessMSG(305, "The students have been dispersed from the party. ");
			server.setFailureMSG(305, "The students have run amok and completely trashed the building. There is now vomit stains on the carpet thanks to your inaction! ");
			server.addDifficulty(new DifficultyVO(305, 305, 4));	
			server.addDifficultyAnswer(new AnswerVO(305, 1, 0) );
			server.addDifficultyAnswer(new AnswerVO(305, 2, 0) );
			server.setEventDispatchTime(305, 1, 06);
			server.setEventTimeLimit(305, 3, 06);
			
			server.addIncident(new IncidentVO (306, "Luncheon Nausea", "The people at the Senator's luncheon reported feeling nauseous after trying the pork roast", "i_medic.gif", 2));		
			server.setSuccessMSG(306, "The pork was found to be undercooked, the sick people were treated and sent home");
			server.setFailureMSG(306, "The cafeteria has been renamed the 'Vomitorium', and a few the sick people want to sue for negligence");
			server.addDifficulty(new DifficultyVO(306, 306, 4));		
			server.addDifficultyAnswer(new AnswerVO(306, 5, 2) );
			server.addDifficultyAnswer(new AnswerVO(306, 7, 1) );		
			server.setEventDispatchTime(306, 1, 31);
			server.setEventTimeLimit(306, 3, 31);
			
			server.addIncident(new IncidentVO (307, "Old Main Frame Shoppe Fire", "Employees report that an apartment has caught fire and spread to nearby store below. Units needed to suppress fire. ", "i_nresfire.gif", 2));		
			server.setSuccessMSG(307, "The fire has been put out successfully before it could spread to the store, the store owners are eternally thankful");
			server.setFailureMSG(307, "Unfortunately the fire has spread to the nearby store destroying both the apartment and the store below");
			server.addDifficulty(new DifficultyVO(307, 307, 4));		
			server.addDifficultyAnswer(new AnswerVO(307, 6, 0) );
			server.setEventDispatchTime(307, 1, 55);
			server.setEventTimeLimit(307, 3, 55);
			
			server.addIncident(new IncidentVO (308, "University Fight Club", "Several intoxicated males seen fighting outside the University Club on College Ave and Atherton. No weapons are reported involved but a very large crowd has gathered around them that is obstructing traffic.  Disperse crowd and arrest suspects.", "i_crimact.gif", 2));		
			server.setSuccessMSG(308, "The students were arrested and crowds successfully dispersed");
			server.setFailureMSG(308, "The crowd has gone into a blood frenzy after witnessing a fight, dozens of people are now injured, one reported missing.");
			server.addDifficulty(new DifficultyVO(308, 308, 4));	
			server.addDifficultyAnswer(new AnswerVO(308, 2, 0) );
			server.addDifficultyAnswer(new AnswerVO(308, 3, 0) );
			server.setEventDispatchTime(308, 2, 20);
			server.setEventTimeLimit(308, 4, 20);
				
			server.addIncident(new IncidentVO (309, "Field Chemical Removal", "Director requests disposal of a large number of barrels containing expired lawn treatment chemicals found in the basement of Beaver Stadium. Some containers may be volatile.  Units are advised to proceed with caution. ", "i_toxinfect.gif", 3));		
			server.setSuccessMSG(309, "The chemicals were successfully removed from the stadium. ");
			server.setFailureMSG(309, "A few of the barrels leaked their chemicals into the ground water preventing the use of university drinking fountains for a week");
			server.addDifficulty(new DifficultyVO(309, 309, 5));	
			server.addDifficultyAnswer(new AnswerVO(309, 6, 0) );
			server.addDifficultyAnswer(new AnswerVO(309, 7, 0) );
			server.addDifficultyAnswer(new AnswerVO(309, 9, 0) );
			server.setEventDispatchTime(309, 2, 45);
			server.setEventTimeLimit(309, 4, 45);
			
			server.addIncident(new IncidentVO (310, "No Go on the Logo", "Group of students seen pouring strange blue and white chemicals on the Old Main lawn to try and create the Nittany Lion logo. Units needed to apprehend suspects and collect samples of the material.", "i_chemical.gif", 3));		
			server.setSuccessMSG(310, "The students have seen the error in their ways and have stuck to dying themselves rather than Old Main Lawn");
			server.setFailureMSG(310, "Looks like the logo will be stained in the lawn for several months, President Spanier and the alumni assocation are NOT happy.");
			server.addDifficulty(new DifficultyVO(310, 310, 6));		
			server.addDifficultyAnswer(new AnswerVO(310, 1, 0) );
			server.addDifficultyAnswer(new AnswerVO(310, 2, 0) );
			server.addDifficultyAnswer(new AnswerVO(310, 7, 0) );
			server.addDifficultyAnswer(new AnswerVO(310, 9, 0) );
			server.setEventDispatchTime(310, 3, 10);
			server.setEventTimeLimit(310, 5, 10);
			
			server.addIncident(new IncidentVO (311, "Smoking Kills", "Caller reports falling asleep with a lit cigarette has led to a fire in his residence. Resident is unable to contain the fire in the room and needs assistance.","i_resfire.gif", 1));		
			server.setSuccessMSG(311, "The fire has been put out successfully before it could spread to the rest of the house.");
			server.setFailureMSG(311, "Unfortunately the fire has spread, destroying the entire residence.");
			server.addDifficulty(new DifficultyVO(311, 311, 3));		
			server.addDifficultyAnswer(new AnswerVO(311, 6, 0) );
			server.setEventDispatchTime(311, 3, 33);
			server.setEventTimeLimit(311, 5, 33);
			
			server.addIncident(new IncidentVO (312, "Traffic Build up", "Units needed to direct traffic at the intersection of College Ave and Atherton St. ", "i_vehhij.gif", 2));		
			server.setSuccessMSG(312, "Traffic was alleviated thanks to the presence of the officers");
			server.setFailureMSG(312, "Traffic is completely Grid Locked and some of the drivers are starting to unpack their tailgating supplies right on the street! ");
			server.addDifficulty(new DifficultyVO(312, 312, 4));	
			server.addDifficultyAnswer(new AnswerVO(312, 2, 0) );
			server.setEventDispatchTime(312, 3, 41);
			server.setEventTimeLimit(312, 5, 41);		
			
			server.addIncident(new IncidentVO (313, "Bad News Bar Tour", "A student called to report her friend vomiting and collapsing in the restroom at a local bar. Unit requested for treatment.", "i_medic.gif", 2));		
			server.setSuccessMSG(313, "The student was taken to the hospital and had her stomach pumped. Thank you for your quick response.");
			server.setFailureMSG(313, "The student was found faced down in a puddle outside the bar, she's alive, but just barely.");
			server.addDifficulty(new DifficultyVO(313, 313,3));	
			server.addDifficultyAnswer(new AnswerVO(313, 5, 0) );
			server.setEventDispatchTime(313, 4, 01);
			server.setEventTimeLimit(313, 6, 01);
			
			server.addIncident(new IncidentVO (314, "Rush the Field", "Caller reports that students have rushed the field at Beaver Stadium due to their anger over Penn State's losing the football game.  Units needed to control the mob.","i_civdemo.gif", 2));		
			server.setSuccessMSG(314, "The mob is under control");
			server.setFailureMSG(314, "The mob went out of control, pulling down the goal post.");
			server.addDifficulty(new DifficultyVO(314, 314, 3));		
			server.addDifficultyAnswer(new AnswerVO(314, 3, 0) );
			server.setEventDispatchTime(314, 4, 31);
			server.setEventTimeLimit(314, 6, 31);
			
			server.addIncident(new IncidentVO (315, "Car Fire", "Several reports have come in reportedly seeing a car overturned and on fire at the intersecation of South Allen and Beaver Ave. Units Requested on scene Immeadiately.", "i_vehinc.gif", 2));		
			server.setSuccessMSG(315, "The fire has been put out successfully and the wreckage has been removed while investigators continue to discover the cause of the fire");
			server.setFailureMSG(315, "Unfortunately the fire has spread to a nearby car, causing massive vehicle damage and blocking the intersection of traffic");
			server.addDifficulty(new DifficultyVO(315, 315, 4));			
			server.addDifficultyAnswer(new AnswerVO(315, 6, 0) );
			server.setEventDispatchTime(315, 4, 55);
			server.setEventTimeLimit(315, 6, 55);
						
			server.addIncident(new IncidentVO (316, "Tick Tock", "A bomb threat has been called in for Paterno Library.  Units needed to search the building and to also investigate the capacity of the emergency fire walls.", "i_bombthr.gif", 3));		
			server.setSuccessMSG(316, "The bomb was successfully neutralized.");
			server.setFailureMSG(316, "The bomb exploded and the fire walls didn't hold, injuring several people nearby.");
			server.addDifficulty(new DifficultyVO(316, 316, 5));	
			server.addDifficultyAnswer(new AnswerVO(316, 8, 0) );
			server.addDifficultyAnswer(new AnswerVO(316, 4, 0) );
			server.setEventDispatchTime(316, 5, 15);
			server.setEventTimeLimit(316, 7, 15);
			
			server.addIncident(new IncidentVO (317, "Alcohol Related Disturbance", "The front desk manager called to report a small group of intoxicated people lingering outside the Nittany Lion Inn. One individual seems to be passed out. Units requested on scene.", "i_medic.gif", 2));		
			server.setSuccessMSG(317, "The student was taken to the hospital and had her stomach pumped. Thank you for your quick response.");
			server.setFailureMSG(317, "The student was found faced down in a puddle outside the Inn, she's alive, but just barely.");
			server.addDifficulty(new DifficultyVO(317, 317, 3));	
			server.addDifficultyAnswer(new AnswerVO(317, 5, 0) );
			server.setEventDispatchTime(317, 5, 40);
			server.setEventTimeLimit(317, 7, 40);
			
			server.addIncident(new IncidentVO (318, "Statements Required", "After the fight at the University Club, the local district attorney is pressing charges and statements are required in order to make an arrest", "i_crimact.gif", 2));		
			server.setSuccessMSG(318, "Enough statements were gathered that should allow the district attorney to press charges and move forward in this case");
			server.setFailureMSG(318, "No evidence was gathered, and it is doubtful whether or not the district attorney will be able to press charges");
			server.addDifficulty(new DifficultyVO(318, 318, 3));		
			server.addDifficultyAnswer(new AnswerVO(318, 1, 0) );
			server.addDifficultyAnswer(new AnswerVO(318, 2, 0) );
			server.setEventDispatchTime(318, 5, 55);
			server.setEventTimeLimit(318, 7, 55);
			
			server.addIncident(new IncidentVO(319,"Chemical Investigation", "Units requested to assist researchers with an inspection of a large chemical inventory.  Facility coordinator reports that approximately two to three rooms of volatile materials need to be catalogued. One of the barrels also seems to be leaking.", "i_chemical.gif", 2));
			server.setSuccessMSG(319, "The chemicals have been transported to a safe location and catalogued for further investigation");
			server.setFailureMSG(319, "The chemicals were lost during transport");
			server.addDifficulty(new DifficultyVO(319, 319, 3));
			server.addDifficultyAnswer(new AnswerVO(319, 7, 0));
			server.addDifficultyAnswer(new AnswerVO(319, 9, 0));
			server.setEventDispatchTime(319, 6, 10);
			server.setEventTimeLimit(319, 8, 10);
			
			server.addIncident(new IncidentVO(320,"All Along the Watchtower", "A disgruntled employee has taken the President hostage at the top of the bell tower in Old Main. If the employee does not receive a million doll-hairs by the time the clock reaches 8:30 he will detonate a stolen cache of illegal fireworks. Effectively kill himself, the hostage, and destroying the Old Main Bell Tower. Please send units to help either diffuse the situation, take out the terrorists, and investigate the structural integrity of Old Main.", "i_bombexp.gif", 4));
			server.setSuccessMSG(320, "The fireworks were rather beautiful, and rather than ruin their fun, your units assisted in making their launch safer. Everyone enjoyed the resulting show!");
			server.setFailureMSG(320, "The individuals were amateurs and nearly killed themselves lighting their fireworks by hand. One unnamed individual can now only count to 9 on his fingers.");
			server.addDifficulty(new DifficultyVO(320, 320, 7));
			server.addDifficultyAnswer(new AnswerVO(320, 3, 0));
			server.addDifficultyAnswer(new AnswerVO(320, 4, 0));
			server.addDifficultyAnswer(new AnswerVO(320, 8, 0));
			server.setEventDispatchTime(320, 6, 30);
			server.setEventTimeLimit(320, 8, 30);
			
			server.addIncident(new IncidentVO(321,"The Dukes of Hazzard", "A stolen truck containing suspicious material crashed into the river after being driven offroad by the General Lee. The truck is leaking its contents and the driver seems to be unconscious.", "i_hazinc.gif", 4));
			server.setSuccessMSG(321, "The good ole Duke Boys showed up to help resolve the matter, its hard to stay angry with them after getting lost in their dreamy eyes");
			server.setFailureMSG(321, "We've screwed up yet again. We'll get those Duke boys next time");
			server.addDifficulty(new DifficultyVO(321, 321, 7));
			server.addDifficultyAnswer(new AnswerVO(321, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(321, 5, 0));
			server.addDifficultyAnswer(new AnswerVO(321, 9, 0));
			server.setEventDispatchTime(321, 6, 46);
			server.setEventTimeLimit(321, 8, 46);
			
			//Ben Scenario 2
			
			server.addIncident(new IncidentVO(401,"Strange Flashes", "Resident reported seeing 'flashes' or 'flickering' in neighbor's window at White Course Apartments.  No smoke reported but units are advised to proceed with caution.", "i_resfire.gif", 1));
			server.setSuccessMSG(401, "The flickering was caused by a fire in the resident's kitchen, apparently she left the stove on.");
			server.setFailureMSG(401, "We've screwed up yet again. We'll get those Duke boys next time.");
			server.addDifficulty(new DifficultyVO(401, 401, 1));
			server.addDifficultyAnswer(new AnswerVO(401, 6, 0));
			server.setEventDispatchTime(401, 0, 05);
			server.setEventTimeLimit(401, 2, 05);
			
			server.addIncident(new IncidentVO(402,"Search and Seizure", "Search and seizure of material evidence needed at campus dormitories for narcotics.  Informants report dealer located in one of the buildings", "i_crimact.gif", 2));
			server.setSuccessMSG(402, "The narcotic's dealer was apprehended thanks to your quick response!");
			server.setFailureMSG(402, "The dealer caught wind that we were looking for him and ran, current location is unknown");
			server.addDifficulty(new DifficultyVO(402, 402, 3));
			server.addDifficultyAnswer(new AnswerVO(402, 1, 0));
			server.addDifficultyAnswer(new AnswerVO(402, 2, 0));
			server.setEventDispatchTime(402, 0, 30);
			server.setEventTimeLimit(402, 2, 30);
			
			server.addIncident(new IncidentVO(403,"Learning to Swim", "Student reports pulling a body from in the outdoor pool next to the Natatorium.  Caller has attempted CPR but has been unable to revive the victim.  ", "i_medic.gif", 1));
			server.setSuccessMSG(403, "The student swimmer was revived thanks to the heroic efforts of the local EMS ");
			server.setFailureMSG(403, "Unfortunately the student swimmer passed away without help from the local EMS");
			server.addDifficulty(new DifficultyVO(403, 403, 3));
			server.addDifficultyAnswer(new AnswerVO(403, 5, 0));
			server.setEventDispatchTime(403, 0, 49);
			server.setEventTimeLimit(403, 2, 49);
			
			server.addIncident(new IncidentVO(404,"Waste Container Removal", "Units requested at campus research laboratory to remove biohazardous waste containers across multiple floors.  ", "i_toxinfect.gif", 1));
			server.setSuccessMSG(404, "The chemical barrels were removed from the facility");
			server.setFailureMSG(404, "The chemicals were not removed and owner the research laboratory is going to sue for negligence");
			server.addDifficulty(new DifficultyVO(404, 404, 2));
			server.addDifficultyAnswer(new AnswerVO(404, 9, 0));
			server.setEventDispatchTime(404, 1, 25);
			server.setEventTimeLimit(404, 3, 25);
			
			server.addIncident(new IncidentVO(405,"Student Burgulary", "Student reports a burglary in-progress.  Male student noticed two female students removing furniture from dormitory lobby.", "i_civriot.gif", 1));
			server.setSuccessMSG(405, "The students were stopped before they could complete their 'master' plan");
			server.setFailureMSG(405, "The students stole the couch only to move it into the elevator where they rode it all day, until they eventually broke the elevator. Where was the Police?");
			server.addDifficulty(new DifficultyVO(405, 405, 5));
			server.addDifficultyAnswer(new AnswerVO(405, 1, 0));
			server.addDifficultyAnswer(new AnswerVO(405, 2, 0));
			server.setEventDispatchTime(405, 1, 40);
			server.setEventTimeLimit(405, 3, 40);
			
			server.addIncident(new IncidentVO(406,"How NOT to cook", "Kitchen fire reported at local eatery. Cooks have been unable to extinguish flames. It seems the propane tank that fuels the grill is leaking and may explode!", "i_nresfire.gif", 2));
			server.setSuccessMSG(406, "The propane tank was dismantled and the fire was put out thanks to your help");
			server.setFailureMSG(406, "The propane tank exploded and the fire went out of control. ");
			server.addDifficulty(new DifficultyVO(406, 406, 5));
			server.addDifficultyAnswer(new AnswerVO(406, 6, 0));
			server.addDifficultyAnswer(new AnswerVO(406, 8, 0));
			server.setEventDispatchTime(406, 2, 04);
			server.setEventTimeLimit(406, 4, 04);
			
			server.addIncident(new IncidentVO(407,"Tanker Collision", "A tanker carrying aqueous ammonia has collided with a large truck carrying various caustic chemicals. Units are needed to clear the area of on-lookers and control the chemical fire.", "i_vehinc.gif", 3));
			server.setSuccessMSG(407, "The spilt chemicals from the tanker were cleaned up, and the injured driver was treated! The surrounding crowds have also been dispersed");
			server.setFailureMSG(407, "The chemicals are melting the street and causing one heck of a mess. This event required action from all three team members.");
			server.addDifficulty(new DifficultyVO(407, 407, 6));
			server.addDifficultyAnswer(new AnswerVO(407, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(407, 6, 0));
			server.addDifficultyAnswer(new AnswerVO(407, 9, 0));
			server.setEventDispatchTime(407, 2, 36);
			server.setEventTimeLimit(407, 5, 36);
			
			server.addIncident(new IncidentVO(408,"Mr. Clean Burns", "A recent batch of Mr. Clean floor soap was reported as contaminated causing a 'mysterious' side effect. Please send units to investigate and obtain these chemicals from local residents for proper disposal.", "i_chemical.gif", 2));
			server.setSuccessMSG(408, "The contaminated Mr. Clean supply was rounded up before any harm could be done  ");
			server.setFailureMSG(408, "The contaminated Mr. Clean supply was not collected and the 'mysterious' side effect was discovered to be super slippery floors - you know like the kind seen in cartoons. Several Injuries resulted");
			server.addDifficulty(new DifficultyVO(408, 408, 4));
			server.addDifficultyAnswer(new AnswerVO(408, 7, 0));
			server.setEventDispatchTime(408, 3, 01);
			server.setEventTimeLimit(408, 5, 01);
			
			server.addIncident(new IncidentVO(409,"Fire Hydrant Hijinks", "Open fire hydrant reported in front of dorms on Pollock Road.  Caller reports seeing a group of students with a large wrench running from the scene. Send units to repair the hydrant and to arrest the suspects.", "i_civriot.gif", 2));
			server.setSuccessMSG(409, "The fire hydrant was closed off and the students who opened it have apologized. ");
			server.setFailureMSG(409, "The water was completely drained from the fire hydrant and the hooligans have run away");
			server.addDifficulty(new DifficultyVO(409, 409, 4));
			server.addDifficultyAnswer(new AnswerVO(409, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(409, 4, 0));
			server.setEventDispatchTime(409, 3, 25);
			server.setEventTimeLimit(409, 5, 25);
			
			server.addIncident(new IncidentVO(410,"Safety First!", "Send units to conduct residence hall fire safety compliance inspections. Approximately 2 buildings need to be reviewed.", "i_checkpoint.gif", 2));
			server.setSuccessMSG(410, "The inspections were completed successfully.");
			server.setFailureMSG(410, "The inspections were not completed, here's to hoping no fires happen or else we'll be liable!");
			server.addDifficulty(new DifficultyVO(410, 410, 4));
			server.addDifficultyAnswer(new AnswerVO(410, 4, 0));
			server.setEventDispatchTime(410, 3, 45);
			server.setEventTimeLimit(410, 5, 45);
			
			server.addIncident(new IncidentVO(411,"Protest", "Cordon off a small abortion rights demonstration and arrest rioting demonstrators at the rally.  Units are advised to use pepper spray when necessary.", "i_civdemo.gif", 3));
			server.setSuccessMSG(411, "The protest went on peacefully without the need for violence, good job!");
			server.setFailureMSG(411, "The protest became violent as Pro Life and Pro Choice demonstrators began fighting");
			server.addDifficulty(new DifficultyVO(411, 411, 5));
			server.addDifficultyAnswer(new AnswerVO(411, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(411, 3, 0));
			server.setEventDispatchTime(411, 3, 50);
			server.setEventTimeLimit(411, 6, 50);
				
			server.addIncident(new IncidentVO(412,"Chemical Fire", "Local chemistry building caught on fire this afternoon as the result of an experiment 'gone horribly wrong'. Send units to quell the fire and clean up the mess.", "i_nresfire.gif", 2));
			server.setSuccessMSG(412, "The damage was minimal thanks to your quick action and clean-up");
			server.setFailureMSG(412, "The laboratory is ruined! If only fire and hazmat would have acted sooner!");
			server.addDifficulty(new DifficultyVO(412, 412, 3));
			server.addDifficultyAnswer(new AnswerVO(412, 6, 0));
			server.addDifficultyAnswer(new AnswerVO(412, 9, 0));
			server.setEventDispatchTime(412, 4, 24);
			server.setEventTimeLimit(412, 6, 24);
			
			server.addIncident(new IncidentVO(413,"CSI - State College", "Units needed at poisoning crime scene for interrogation of witnesses, to collect samples of remaining biological material, and to examine the security and safety of the structure.", "i_death.gif", 4));
			server.setSuccessMSG(413, "Like most episodes of CSI, this one concluded with a happy ending");
			server.setFailureMSG(413, "The crime scene was contaminated due to a lack of investigators from all teams present on the scene. The local DA is very unhappy");
			server.addDifficulty(new DifficultyVO(413, 413, 6));
			server.addDifficultyAnswer(new AnswerVO(413, 1, 0));
			server.addDifficultyAnswer(new AnswerVO(413, 4, 0));
			server.addDifficultyAnswer(new AnswerVO(413, 7, 0));
			server.setEventDispatchTime(413, 5, 16);
			server.setEventTimeLimit(413, 8, 16);
			
			server.addIncident(new IncidentVO (414, "Fire Fire Everywhere", "An individual reports a large fire on South Allen St.  Units requested on scene, arson is suspected.", "i_smoke.gif", 3));		
			server.setSuccessMSG(414, "The fire has been put out successfully and the apartment owner has been arrested.");
			server.setFailureMSG(414, "Unfortunately the fire has spread to neighboring apartments, and the apartment owner was not brought to justice");
			server.addDifficulty(new DifficultyVO(414, 414, 6));		
			server.addDifficultyAnswer(new AnswerVO(414, 2, 0) );
			server.addDifficultyAnswer(new AnswerVO(414, 4, 0) );
			server.addDifficultyAnswer(new AnswerVO(414, 6, 0) );
			server.setEventDispatchTime(414, 5, 31);
			server.setEventTimeLimit(414, 8, 31);
			
			server.addIncident(new IncidentVO(415,"Two Tickets to the Gun Show", "Anonymous tip reports students in East Hall dormitory hording heavy firearms in footlocker in preparation for a campus attack.  Perform a floor search and remove any weapons discovered. Use of Lethal Force is authorized.", "i_shoot.gif", 3));
			server.setSuccessMSG(415, "Several firearms were found the locker of an estranged student, we believe you averted a new catasrophe!");
			server.setFailureMSG(415, "No firearms were found, as no units were sent to look for them, lets hope nothing bad happens!");
			server.addDifficulty(new DifficultyVO(415, 415, 4));
			server.addDifficultyAnswer(new AnswerVO(415, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(415, 3, 0));		
			server.setEventDispatchTime(415, 5, 56);
			server.setEventTimeLimit(415, 7, 56);
					
			server.addIncident(new IncidentVO(416,"Fireworks", "Store manager sees individuals with fireworks and it starts a large fire. Units needed to putout the flames so that units can then collect the remaning explosive material.", "i_bombexp.gif", 3));
			server.setSuccessMSG(416, "The fireworks were retrieved and the fire put out. No victims were reported.");
			server.setFailureMSG(416, "The fire was fueled by the fireworks and escalated out of control. Several student were badly burned and one casualty was reported.");
			server.addDifficulty(new DifficultyVO(416, 416, 4));
			server.addDifficultyAnswer(new AnswerVO(416, 6, 0));
			server.addDifficultyAnswer(new AnswerVO(416, 8, 0));
			server.setEventDispatchTime(416, 6, 21);
			server.setEventTimeLimit(416, 9, 21);
			
			server.addIncident(new IncidentVO(417,"Light up the Dark Room", "Inventory chemicals have expired at the local camera shop.  Investigate former employee's claims regarding illegal dumping of silver nitrate.", "i_chemical.gif", 1));
			server.setSuccessMSG(417, "The employee was found dumping the chemicals into Spruce Creek, caught red-handed he was promptly arrested before he could dump the chemicals");
			server.setFailureMSG(417, "The employee was found dumping the chemicals into Spruce Creek, unfortunately he could not be stopped before he killed all the fish in the stream");
			server.addDifficulty(new DifficultyVO(417, 417, 2));
			server.addDifficultyAnswer(new AnswerVO(417, 7, 0));
			server.setEventDispatchTime(417, 6, 46);
			server.setEventTimeLimit(417, 8, 46);
			
			server.addIncident(new IncidentVO(418, "Collapsed Personnel", "The maintenance personnel at the Nittany Lion Apts. have discovered a collapsed resident. Units are needed to revive the victim.", "i_medic.gif",1 ));
			server.setSuccessMSG(418, "The personnel is now in critical condition at the Mt. Nittany Medical Center, the resident will live thanks to your quick action. ");
			server.setFailureMSG(418, "The personnel is now in critical condition at the Mt. Nittany Medical Center, it's doubtful the resident will survive the night. ");
			server.addDifficulty(new DifficultyVO(418, 418, 4));
			server.addDifficultyAnswer(new AnswerVO(418, 5, 0));
			server.setEventDispatchTime(418, 7, 12);
			server.setEventTimeLimit(418, 9, 12);
			
			server.addIncident(new IncidentVO(419, "Loitering Vehicle", "A suspicious Balford Cleaning Truck is parked outside Citizens Bank on South Atherton. Inside the bank seems very crowded.", "i_vehhij.gif", 4));
			server.setSuccessMSG(419, "The Balford Vehicle was a front for a group of robbers. Fortunately you prevented their intentions of robbingn the bank");
			server.setFailureMSG(419, "The Balford Vehicle contained a group of robbers who proceeded to steal some 'fat loot' and get away!");
			server.addDifficulty(new DifficultyVO(419, 419, 5));
			server.addDifficultyAnswer(new AnswerVO(419, 1, 0));
			server.addDifficultyAnswer(new AnswerVO(419, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(419, 3, 0));
			server.addDifficultyAnswer(new AnswerVO(419, 4, 0));
			server.addDifficultyAnswer(new AnswerVO(419, 5, 0));
			server.addDifficultyAnswer(new AnswerVO(419, 8, 0));
			server.setEventDispatchTime(419, 7, 31);
			server.setEventTimeLimit(419, 10, 01);
			
			server.addIncident(new IncidentVO(420, "Hunting Accident", "At least two men were injured in a shotgun accident in the woods near the Schreyer House.  Extent of wounds is unknown. Please send units to arrest the assailant and treat the victim", "i_shoot.gif", 2));
			server.setSuccessMSG(420, "The hunters had misfired their weapons after the duck they were hunting flew between them, fortunately they only grazed each other");
			server.setFailureMSG(420, "One of the hunters is in serious condition at a local hospital after dismissing his injury as 'just a flesh wound'. ");
			server.addDifficulty(new DifficultyVO(420, 420, 4));
			server.addDifficultyAnswer(new AnswerVO(420, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(420, 5, 0));
			server.setEventDispatchTime(420, 7, 40);
			server.setEventTimeLimit(420, 9, 40);
			
			server.addIncident(new IncidentVO(421,"Student Death Threats", "Female student reports that a classmate has threaten to kill her by blowing her up 'into little itty bitty pieces.'  Victim reports seeing the suspect hovering around her vehicle outside her apartment days earlier.  Units are needed to retrieve the package and interview the victim.", "i_bomb.gif", 4));
			server.setSuccessMSG(421, "The bomb was disarmed and the student was arrested");
			server.setFailureMSG(421, "The bomb compeltely destroyed the student's car, and the suspect is at large and dangerous");
			server.addDifficulty(new DifficultyVO(421, 421, 7));
			server.addDifficultyAnswer(new AnswerVO(421, 1, 0));
			server.addDifficultyAnswer(new AnswerVO(421, 2, 0));
			server.addDifficultyAnswer(new AnswerVO(421, 8, 0));
			server.setEventDispatchTime(421, 4, 10);
			server.setEventTimeLimit(421, 7, 10);
			
			//	ONR Practice 1 - Circus 4.14.09
			
			server.addIncident(new IncidentVO (501, "Ride to the Circus", "A couple members of the board of trustees require an escort from the Nittany Lion Inn to the circus at Sunset Park.", "i_escort.gif", 1));		
			server.setSuccessMSG(501, "The members of the board of trustees greatly appreciated the smooth ride to the circus and would be happy to write you a future letter of recommendation.");
			server.setFailureMSG(501, "The members of the board of trustees are deeply disappointed about missing the circus and are holding an emergency meeting to discuss an increase in your college tuition.");
			server.addDifficulty(new DifficultyVO(501, 501, 1));	
			server.addDifficultyAnswer(new AnswerVO(501, 3, 0) );
			server.setEventDispatchTime(501, 0, 10);
			server.setEventTimeLimit(501, 3, 20);
			
			server.addIncident(new IncidentVO (502, "Pie Talk", "A representative is needed to deliver a talk on the toxic chemical substances that may be present in coconut cream pies.", "i_hazinc.gif", 1));		
			server.setSuccessMSG(502, "The talk was delivered successfully. The bakers now know how to adjust their shopping lists for future recipes.");
			server.setFailureMSG(502, "The bakers at the circus are confused on how to make non-toxic pies. Eye witnesses have reported seeing bakers collecting samples of dirt and rat poison as pie fillings.");
			server.addDifficulty(new DifficultyVO(502, 502, 1));	
			server.addDifficultyAnswer(new AnswerVO(502, 7, 0) );
			server.setEventDispatchTime(502, 0, 30);
			server.setEventTimeLimit(502, 3, 40);
			
			server.addIncident(new IncidentVO (503, "Woozy Ride", "Graham Spanier is feeling nauseous from his ride on the ferris wheel. Units requested on site for treatment.", "i_medic.gif", 1));		
			server.setSuccessMSG(503, "Graham Spanier is now feeling much better and is first in line for the Merry-Go-Round.");
			server.setFailureMSG(503, "Graham Spanier had to sit out all the rides and eventually ended up leaving soon after.");
			server.addDifficulty(new DifficultyVO(503, 503, 1));	
			server.addDifficultyAnswer(new AnswerVO(503, 5, 0) );
			server.setEventDispatchTime(503, 0, 55);
			server.setEventTimeLimit(503, 4, 05);
			
			server.addIncident(new IncidentVO (504, "Eating Competition", "Judges at the pie eating competition need to determine which pie filling is least toxic before starting the competition. Units requested to collect and test samples of the fillings.", "i_chemical.gif", 1));		
			server.setSuccessMSG(504, "The pie eating competition was a success! No causalities were reported.");
			server.setFailureMSG(504, "Many individuals seen vomiting at the pie eating competition. The 'winner' appears to have collapsed.");
			server.addDifficulty(new DifficultyVO(504, 504, 1));	
			server.addDifficultyAnswer(new AnswerVO(504, 7, 0) );
			server.setEventDispatchTime(504, 1, 25);
			server.setEventTimeLimit(504, 4, 25);
			
			server.addIncident(new IncidentVO (505, "Dunk Tank", "Caller reports that a group of students ran off with his clothes after dunking him in the dunk tank. Units requested to detain suspects and to interview witnesses.", "i_crimact.gif", 2));		
			server.setSuccessMSG(505, "The victim is very thankful for your assistance and is hopeful that his clothes will be returned.");
			server.setFailureMSG(505, "The victim has been seen streaking across Sunset Park and making clothes out of leaves and branches.");
			server.addDifficulty(new DifficultyVO(505, 505, 2));	
			server.addDifficultyAnswer(new AnswerVO(505, 1, 1) );
			server.addDifficultyAnswer(new AnswerVO(505, 2, 1) );
			server.setEventDispatchTime(505, 1, 50);
			server.setEventTimeLimit(505, 4, 50);
			
			server.addIncident(new IncidentVO (506, "Pie Allergy", "A director of the board of trustees is having an adverse reaction to a pie that was tossed in his face. Units requested on scene.", "i_medic.gif", 1));		
			server.setSuccessMSG(506, "The director of the board of trustees has recovered from the ordeal and has offered you a year's worth of college tuition as compensation.");
			server.setFailureMSG(506, "Due to your slow response, the director of the board of trustees has decided to seek revenge by completely getting rid of your academic program at Penn State.");
			server.addDifficulty(new DifficultyVO(506, 506, 1));	
			server.addDifficultyAnswer(new AnswerVO(506, 5, 0) );
			server.setEventDispatchTime(506, 2, 15);
			server.setEventTimeLimit(506, 5, 20);
			
			server.addIncident(new IncidentVO (507, "Clean-up & Recovery", "A group of clowns were experimenting with the flame thrower's equipment and spilled toxic fluids on a crate of TNT. Units needed to clean-up the area and to retrieve the explosives.", "i_toxinfect.gif", 2));		
			server.setSuccessMSG(507, "The toxic fluids turned out to be a carnival slushie and the TNT were old crates of hotdogs. The clean-up and recovery efforts were none-the-less successful!");
			server.setFailureMSG(507, "The toxic fluids poisoned and killed a curious child that licked the crate, which led the circus to fire poor Pyro the Magnificent.");
			server.addDifficulty(new DifficultyVO(507, 507, 2));	
			server.addDifficultyAnswer(new AnswerVO(507, 8, 1));
			server.addDifficultyAnswer(new AnswerVO(507, 9, 1));
			server.setEventDispatchTime(507, 2, 30);
			server.setEventTimeLimit(507, 5, 30);
			
			server.addIncident(new IncidentVO (508, "Annoying Monkeys", "There are a group of students dressed as monkeys that are harassing the animals in the circus. Units needed to apprehend the suspects.", "i_civriot.gif", 1));		
			server.setSuccessMSG(508, "The students were successfully apprehended. The animal trainers are eternally grateful for helping to avert a catastrophe.");
			server.setFailureMSG(508, "The elephants got really annoyed with the students and reportedly tossed one of them in a tree.");
			server.addDifficulty(new DifficultyVO(508, 508, 1));	
			server.addDifficultyAnswer(new AnswerVO(508, 2, 0) );
			server.setEventDispatchTime(508, 3, 10);
			server.setEventTimeLimit(508, 5, 30);
			
			server.addIncident(new IncidentVO (509, "Fire Juggler", "The fire juggler has caught a small tent on fire. An audience member tried to put the fire out with what later appeared to be a clown's fake fire extinguisher, which spread the flames even more! Units needed to put out the blaze and to separate the real fire extinguishers from the fake ones.", "i_fireinc.gif", 2));		
			server.setSuccessMSG(509, "The fire has been contained and all fake fire extinguishers has been discarded.");
			server.setFailureMSG(509, "The blaze has gotten out of control and has caused a stampede of the elephants, giraffes, and monkeys all across Sunset park.");
			server.addDifficulty(new DifficultyVO(509, 509, 2));	
			server.addDifficultyAnswer(new AnswerVO(509, 4, 1) );
			server.addDifficultyAnswer(new AnswerVO(509, 6, 1) );
			server.setEventDispatchTime(509, 3, 30);
			server.setEventTimeLimit(509, 5, 30);
			
			
			//ONR Training 2
				
			server.addIncident(new IncidentVO (601, "Hot Lounge", "Small fire reported in student lounge.  Investigate cause of fire.", "i_fireinc.gif", 1));		
			server.setSuccessMSG(601, "The fire was successfully extinguished.");
			server.setFailureMSG(601, "The fire got out of control and burned down several buildings in the area.");
			server.addDifficulty(new DifficultyVO(601, 601, 1));	
			server.addDifficultyAnswer(new AnswerVO(601, 4, 0) );
			server.setEventDispatchTime(601, 0, 10);
			server.setEventTimeLimit(601, 3, 20);
			
			server.addIncident(new IncidentVO (602, "Disgruntled Students", "Multiple gunshots reported at graduate student union. One student is in critical condition. The shooters are threatening to blow up the union if their final exams aren't postponed.", "i_bombthr.gif", 2));		
			server.setSuccessMSG(602, "The students were apprehended and kicked out of the school.");
			server.setFailureMSG(602, "The shooters weren't bluffing and actually blew up the student union. Students are gathering for a vigil to remember those that were lost in the explosion.");
			server.addDifficulty(new DifficultyVO(602, 602, 2));	
			server.addDifficultyAnswer(new AnswerVO(602, 2, 1) );
			server.addDifficultyAnswer(new AnswerVO(602, 5, 1) );
			server.addDifficultyAnswer(new AnswerVO(602, 8, 1) );
			server.setEventDispatchTime(602, 0, 40);
			server.setEventTimeLimit(602, 3, 40);
			
			server.addIncident(new IncidentVO (603, "Empty Store", "Investigate vandalism reported on empty downtown storefront.", "i_crimact.gif", 1));		
			server.setSuccessMSG(603, "Thanks to your excellent investigative efforts, the perpetrators were apprehended.");
			server.setFailureMSG(603, "The perpetrators were not apprehended and went on to vandalize several other storefronts downtown.");
			server.addDifficulty(new DifficultyVO(603, 603, 1));	
			server.addDifficultyAnswer(new AnswerVO(603, 1, 0) );
			server.setEventDispatchTime(603, 1, 05);
			server.setEventTimeLimit(603, 4, 05);
			
			server.addIncident(new IncidentVO (604, "Grandfather's Basement", "Citizen reports finding an unexploded grenade in the basement of their grandfather's home.  Send unit to reclaim and dispose of the explosive.", "i_bomb.gif", 1));		
			server.setSuccessMSG(604, "The grandfather was happy you confiscated the grenade before his younger grandchildren discovered it.");
			server.setFailureMSG(604, "The grenade was accidentally triggered by a large rodent. The residents are currently trying to rebuild their small home.");
			server.addDifficulty(new DifficultyVO(604, 604, 1));	
			server.addDifficultyAnswer(new AnswerVO(604, 8, 0) );
			server.setEventDispatchTime(604, 1, 25);
			server.setEventTimeLimit(604, 4, 25);
			
			server.addIncident(new IncidentVO (605, "All Smoke", "A large cloud of smoke seen coming from the Agricultural Sciences Building on campus. Suspected arson involved. Units needed to investigate the cause of the fire, question witnesses, and test samples from the site to see if the fire was chemical-related.", "i_smoke.gif", 2));		
			server.setSuccessMSG(605, "The smoke turned out to be caused from a chemical reaction in one of the labs. No civilians were apprehended.");
			server.setFailureMSG(605, "The cloud of smoke is still a mystery. Citizens are still wondering what caused the event.");
			server.addDifficulty(new DifficultyVO(605, 605, 2));	
			server.addDifficultyAnswer(new AnswerVO(605, 1, 1) );
			server.addDifficultyAnswer(new AnswerVO(605, 4, 1) );
			server.addDifficultyAnswer(new AnswerVO(605, 7, 1) );
			server.setEventDispatchTime(605, 1, 45);
			server.setEventTimeLimit(605, 4, 45);
			
			server.addIncident(new IncidentVO (606, "No Trespassing", "Caller reports that a trespasser broke in to a small local chemical storage facility and started a fire. Units needed to contain the chemical fire and apprehend the perpetrator.", "i_crimact.gif", 2));		
			server.setSuccessMSG(606, "The chemical fire was contained and the perpetrator arrested for arson.");
			server.setFailureMSG(606, "The chemical fire grew out of control and burned the building down. The perpetrator got away with arson.");
			server.addDifficulty(new DifficultyVO(606, 606, 2));	
			server.addDifficultyAnswer(new AnswerVO(606, 2, 1) );
			server.addDifficultyAnswer(new AnswerVO(606, 6, 1) );
			server.addDifficultyAnswer(new AnswerVO(606, 9, 1) );
			server.setEventDispatchTime(606, 2, 16);
			server.setEventTimeLimit(606, 5, 16);
			
			server.addIncident(new IncidentVO (607, "Student Riot", "Small crowd of rioting students reported outside of local establishment. Send units to disperse violent suspects.", "i_civdemo.gif", 1));		
			server.setSuccessMSG(607, "The riot was dispersed and violent students arrested.");
			server.setFailureMSG(607, "The riot got rowdier and caused a large traffic jam on College Ave.");
			server.addDifficulty(new DifficultyVO(607, 607, 1));	
			server.addDifficultyAnswer(new AnswerVO(607, 3, 0) );
			server.setEventDispatchTime(607, 2, 40);
			server.setEventTimeLimit(607, 5, 40);
			
			server.addIncident(new IncidentVO (608, "Costume Change", "Pyrotechnics accident during a play rehearsal has ignited a costume closet.", "i_schfire.gif", 1));
			server.setSuccessMSG(608, "The fire was put out and no causalities were reported.");
			server.setFailureMSG(608, "The fire spread from the costume closet to several other rooms in the theater. The play had to be postponed for several weeks to rebuild the playhouse.");
			server.addDifficulty(new DifficultyVO(608, 608, 1));	
			server.addDifficultyAnswer(new AnswerVO(608, 6, 0) );
			server.setEventDispatchTime(608, 3, 10);
			server.setEventTimeLimit(608, 6, 0);
			
			
			server.addIncident(new IncidentVO (609, "Black Powder", "Suspicious black powder found near several offices.  Send unit to collect and test sample.", "i_hazinc.gif", 1));		
			server.setSuccessMSG(609, "The black powder was tested in the lab and turned out to be residue of someone's morning coffee.");
			server.setFailureMSG(609, "The black powder continued to build up near the offices, which led the buildings to be condemned out of fear of the safety of the employees.");
			server.addDifficulty(new DifficultyVO(609, 609, 1));	
			server.addDifficultyAnswer(new AnswerVO(609, 7, 0) );
			server.setEventDispatchTime(609, 3, 30);
			server.setEventTimeLimit(609, 6, 0);
			
			server.addIncident(new IncidentVO (610, "Hulkamania", "i_pirho.gif", 3));		
			server.setSuccessMSG(610, "Woah, you ran wild on Hulkamania");
			server.setFailureMSG(610, "Hulkamania Ran wild on you. ");
			server.setDescription(610, 0, "Whatcha gonna do?");
			server.setDescription(610, 1, "Police");
			server.setDescription(610, 2, "Fire");
			server.setDescription(610, 3, "Hazmat");
			server.addDifficulty(new DifficultyVO(610, 610, 3, 20));				
			server.addDifficultyAnswer(new AnswerVO(610, 1, 2, 1) );
			server.addDifficultyAnswer(new AnswerVO(610, 8, 2, 3) );
			server.addDifficultyAnswer(new AnswerVO(610, 5, 2, 2) );
			server.setEventDispatchTime(610, 0, 5);
			server.setEventTimeLimit(610, 0, 30);
					
			server.addIncident(new IncidentVO (611, "", "", 3));     
	        server.setSuccessMSG(611, "");
	        server.setFailureMSG(611, "");
	        server.setDescription(611, 0, "");
	        server.addDifficulty(new DifficultyVO(611, 611, 3, 20));                
	        server.addDifficultyAnswer(new AnswerVO(611, 1, 2, 1) );  
	        server.setEventDispatchTime(611, 25, 55);
	        server.setEventTimeLimit(611, 0, 60);
			
			int[] trainingScenario = {1,2,3,4,5,6,7,8,9};
			int[] trainingScenario2 = {21, 22, 23, 24};
			int[] onrTraining = {501,502,503,504,505,506,507,508,509};
			int[] onrTraining2 = {601,602,603,604,605,606,607,608,609};
			int[] testScenario = {50, 51, 52, 53};
			//int[] onrScenario = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121};
			//int[] onrScenario = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113};
			int[] onrScenario = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119};
			//int[] onrScenario2 = {201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222};
			int[] onrScenario2 = {201, 203, 204, 205, 206, 208, 209, 210, 211, 212, 214, 215, 216, 217, 218, 219, 220, 222};

			int[] overload1 = {301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321};
			int[] overload2 = {401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421};
			int[] lame = {610} ;
			int[] empty = {611};
					
			//this.createScenario(1, "xxxxxxxxxxxxxxxy", trainingScenario); //Tailgate Training
			//this.createScenario(2, "xxxxxxxxxxxxxxxx", trainingScenario2); // Complex Events Training
			
			this.createScenario(4, "ONR Training 1", onrTraining);
			this.createScenario(5, "ONR Training 2", onrTraining2);
			this.createScenario(6, "ONR Scenario 1", onrScenario);
			this.createScenario(7, "ONR Scenario 2", onrScenario2);
			this.createScenario(11, "ONR Reflexivity (G)", empty);
			this.createScenario(12, "ONR No Reflexivity (NG)", empty);
			
			//this.createScenario(8, "xxxxxxxxxxxxxxxx", overload1); // Normal Scenario 1
			//this.createScenario(9, "xxxxxxxxxxxxxxxx", overload2); // Overload Scenario 2
			
			this.createScenario(3, "yyyyyyyyyyyyyyyypo", testScenario); // Police Only Test
			this.createScenario(10, "yyyyyyyyyyyyyyyyshort", lame);	// Hulkamania xxxxxxxxxxxxxxxx
			
			//server.setScenarioTimeLimit(1, 6, 01);
			//server.setScenarioTimeLimit(2, 4, 01);
			server.setScenarioTimeLimit(3, 2, 11);
			
			server.setScenarioTimeLimit(4, 5, 35);    // onr training 1
			server.setScenarioTimeLimit(5, 6, 15);    // onr training 2
	         
			server.setScenarioTimeLimit(6, 13, 40);   // onr scenario 1
			server.setScenarioTimeLimit(7, 15, 05);   // onr scenario 2
			server.setScenarioTimeLimit(11, 6, 9);   // onr reflexivity
			server.setScenarioTimeLimit(12, 6, 03);   // onr no reflexivity
			
			//server.setScenarioTimeLimit(8, 9, 01);
			//server.setScenarioTimeLimit(9, 10, 11);
			server.setScenarioTimeLimit(10, 1, 20);
			server.setActiveScenario(4);
			
			this.loadPauses();
			this.loadQuestions();
			this.loadBriefings();				
			
			
			comm.consoleMessage("RO - Scenario Data Loaded");		
		}
		
		public void createScenario(int scenarioID, String label, int[] incidents){
			//int scenarioID = server.getNumberofScenarios() + 1;
					
			int timeStep = 3; //number of seconds
			int recallDelay = 3;
			int trafficDelay = 5; //number of timeSteps
			
			ArrayList<Integer> incidentList = new ArrayList<Integer>();
			
			for(int i = 0; i < incidents.length; i++){
				incidentList.add(incidents[i]);
			}
			
			ScenarioVO scenario = new ScenarioVO(scenarioID, label, incidentList, timeStep, trafficDelay, recallDelay);
			server.addScenario(scenario);
			
			comm.consoleMessage("RO - " + scenario.label + " Loaded!");
		}
		
		public void loadBriefings() {
		    server.addScenarioBriefing(3, new BriefingVO(99, "<font size='12'>All emergency response personnel need to be on the lookout for the hijinks of" +
		                " Pi Rho, a group of pranksters that were recently banned from campus by the Dean. <br /><br />All Pi Rho events need to be addressed " +
		                "by all emergency management personnel within 30 minutes (simulation time) of notification of the event (i.e. 30 seconds actual time)</font>",
		                "This shit works yo... Seriously it works"));
		    server.addBriefingImage(99, "edu/psu/ist/neocities/assets/polteam.gif");
		    server.setBriefingDispatchTime(99, 0, 30);
		    server.addBriefingPermission(99, 0);
		    
		    //Performance Scenario 1 Briefing
		    server.addScenarioBriefing(6, new BriefingVO(1, "<font size='12'>We have received an inside tip that there may " +
		    		"be suspicious activity associated with banks during the course of the day.  " +
		    		"<br /><br />The emergency crisis management manual recommends that all bank-related threats be addressed " +
		    		"within the first 45 minutes (simulation time) of notification by all relevant parties " +
		    		"(i.e., 45 seconds actual time).</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
		    server.addBriefingImage(1, "edu/psu/ist/neocities/assets/polteam.gif");
	        server.setBriefingDispatchTime(1, 4, 30);
	        server.addBriefingPermission(1, 1);
	        
	        server.addScenarioBriefing(6, new BriefingVO(2, "<font size='12'>We have received an inside tip that a group of " +
	        		"crazed Ohio State fans are planning on defacing a bank along South Atherton. <br /><br /> " +
	        		"Our intelligence shows that this will occur around 2:30 today and is more likely to take place if Penn State loses the football game. " + 
	        		"</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
	        server.addBriefingImage(2, "edu/psu/ist/neocities/assets/fireteam.gif");
	        server.setBriefingDispatchTime(2, 4, 30);
	        server.addBriefingPermission(2, 2);
	        
	        server.addScenarioBriefing(6, new BriefingVO(3, "<font size='12'>We have received an inside tip that a group of crazed Ohio State fans are planning on defacing a bank along South Atherton." + 
	                "A bank-related threat should be addressed by all emergency management personnel in the following order:" +
	        		"<br /><br />1. Fire (Ambulance): to take care of any injuries and/or casualties  <br />" + 
	        		"2. Police (Investigator): to search the premises for crime scene evidence <br />" + 
	        		"3. Hazmat (Chemical Truck): to cleanup any hazardous material on scene<br />" + 
	        		"</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
	        server.addBriefingImage(3, "edu/psu/ist/neocities/assets/hazteam.gif");
	        server.setBriefingDispatchTime(3, 4, 30);
	        server.addBriefingPermission(3, 3);
	        // End Performance Scenario 1 Briefings
	        
	        // Performance Scenario 2 Briefings
	        
	        // Briefing 2a
	        
	         * 
	         *  "<br /><br /> 1.   Fire: to take care of any injuries and/or casualties.  <br />" + 
	                "2.  Police: to search the premises for crime scene evidence.<br />" + 
	                "3.  Hazmat: to cleanup any hazardous material on scene. <br />" + 
	                "</font>",
	        server.addScenarioBriefing(7, new BriefingVO(4, "<font size='12'>All emergency response personnel need to be on the lookout for the hijinks of" +
	                " Pi Rho, a group of pranksters that were recently banned from campus by the Dean. <br /><br />All Pi Rho events need to be addressed " +
	                "by all emergency management personnel within 30 minutes (simulation time) of notification of the event (i.e. 30 seconds actual time)</font>"));
	        server.addBriefingImage(4, "edu/psu/ist/neocities/assets/polteam.gif");
	        server.setBriefingDispatchTime(4, 2, 15);
	        server.addBriefingPermission(4, 1);
	        
	        server.addScenarioBriefing(7, new BriefingVO(5, "<font size='12'>All emergency response personnel need to be on the lookout for the hijinks of Pi Rho " +
	                ", a group of pranksters that were recently banned from campus by the dean <br /><br /> All Pi Rho events need to be accompanied by Investigative Units from" +
	                "each team member (in addition to the units you would normally send)</font>"));
	        server.addBriefingImage(5, "edu/psu/ist/neocities/assets/fireteam.gif");
	        server.setBriefingDispatchTime(5, 2, 15);
	        server.addBriefingPermission(5, 2);
	        
	        server.addScenarioBriefing(7, new BriefingVO(6, "<font size='12'>All emergency response personnel need to be on the lookout for the hijinks of Pi Rho " +
	                ", a group of pranksters that were recently banned from campus by the dean <br /><br /> Pi Rho likes to trademark their events by leaving a book of matches or graffiti at the scene. " +
	                "these events require additional attention from your team</font>"));
	        server.addBriefingImage(6, "edu/psu/ist/neocities/assets/hazteam.gif");
	        server.setBriefingDispatchTime(6, 2, 15);
	        server.addBriefingPermission(6, 3);
	        // End Briefing 2a
	        
	        // Briefing 2b Change 8 55 to 5 25
	        server.addScenarioBriefing(7, new BriefingVO(7, "<font size='12'>A mentally unstable student has decided that because " +
	        		"she can’t travel home for semester break, she doesn’t want anyone else to either.  She is planning on detonating a " +
	        		"suitcase bomb at the University Park Airport at around 6:45 today.  All units should be on alert" + 
	                "</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
	        server.addBriefingImage(7, "edu/psu/ist/neocities/assets/polteam.gif");
	        server.setBriefingDispatchTime(7, 5, 25);
	        server.addBriefingPermission(7, 1);
	        
	        server.addScenarioBriefing(7, new BriefingVO(8, "<font size='12'>Any events at the airport should be accompanied by all units in the following order: <br /><br /> " +
	        		"1. Police (SWAT): to clear the crowds from the airport <br />2. Hazmat (Bomb Squad): to search for and disarm any bombs on scene<br />" +
	        		"3. Fire (Ambulance): to treat any victims" +
	        		"</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
	        server.addBriefingImage(8, "edu/psu/ist/neocities/assets/fireteam.gif");
	        server.setBriefingDispatchTime(8, 5, 25);
	        server.addBriefingPermission(8, 2);
	        
	        server.addScenarioBriefing(7, new BriefingVO(9, "<font size='12'>A mentally unstable student has decided that because " +
	        		"she can’t travel home for semester break, she doesn’t want anyone else to either.  Our intelligence shows that " +
	        		"she is planning on attacking the University Park Airport.  For this event, units should arrive on scene within 60 minutes (simulation time) " +
	        		"of notification (i.e., 60 seconds actual time)." + 
	                "</font>",
	                "We have recieved new information... Please check the BRIEFINGS panel for an update"));
	        server.addBriefingImage(9, "edu/psu/ist/neocities/assets/hazteam.gif");
	        server.setBriefingDispatchTime(9, 5, 25);
	        server.addBriefingPermission(9, 3);
	        // End Briefing 2b
	        // End Scenario 2 Briefings
	        
	        // ONR Reflexivity
	        
	        server.addScenarioBriefing(11, new BriefingVO(11, "Please move onto Question 2", "Please move onto Question 2"));
	        server.addBriefingImage(11, "edu/psu/ist/neocities/assets/blank.gif");
	        server.setBriefingDispatchTime(11, 1, 30);
	        server.addBriefingPermission(11, 0);
	        
	        server.addScenarioBriefing(11, new BriefingVO(12, "Please move onto Question 3", "Please move onto Question 3"));
	        server.addBriefingImage(12, "edu/psu/ist/neocities/assets/blank.gif");
	        server.setBriefingDispatchTime(12, 3, 03);
	        server.addBriefingPermission(12, 0);
	                        
	        // End ONR Reflexivity
	        
	        // ONR No Reflexivity
	        
	        server.addScenarioBriefing(12, new BriefingVO(13, "Please move onto Question 2", "Please move onto Question 2"));
	        server.addBriefingImage(13, "edu/psu/ist/neocities/assets/blank.gif");
	        server.setBriefingDispatchTime(13, 1, 30);
	        server.addBriefingPermission(13, 0);
	        
	        server.addScenarioBriefing(12, new BriefingVO(14, "Please move onto Question 2", "Please move onto Question 3"));
	        server.addBriefingImage(14, "edu/psu/ist/neocities/assets/blank.gif");
	        server.setBriefingDispatchTime(14, 3, 00);
	        server.addBriefingPermission(14, 0);
	                        
	        // End ONR No Reflexivity
	       
	        
	        comm.consoleMessage("RO - Briefings Loaded");
		}
		
		public void loadQuestions() {

	        // Performance 1 questions...
		    server.addPauseQuestion(1, new QuestionVO(30, "The simulation is currently paused and will resume in 60 Seconds. Please try and answer as many questions as possible", "INTRO"));
		    
	        server.addPauseQuestion(1, new QuestionVO(1, "Whats color is used to indicate the LEAST severe events in NeoCITIEs?", "MC"));
	        server.addQuestionAnswer(1, new QuestionAnswerVO("Black", "Black", 0));
	        server.addQuestionAnswer(1, new QuestionAnswerVO("Orange", "Orange", 0));
	        server.addQuestionAnswer(1, new QuestionAnswerVO("Yellow", "Yellow", 0));
	        server.addQuestionAnswer(1, new QuestionAnswerVO("Green", "Green", 1));
	        
	        server.addPauseQuestion(1, new QuestionVO(2, "Based on the events you've received in the scenario, what major scheduled event is taking place at Penn State?", "SA"));
	        
	        server.addPauseQuestion(1, new QuestionVO(3,"Based on the event description, what would MOST likely happen if units didn’t arrive on scene within 60 seconds in the case of the tanker collision?","MC"));
	        server.addQuestionAnswer(3, new QuestionAnswerVO("The driver would get away","The driver would get away",0));
	        server.addQuestionAnswer(3, new QuestionAnswerVO("Traffic would start to build up.","Traffic would start to build up.",0));
	        server.addQuestionAnswer(3, new QuestionAnswerVO("On-lookers would be seriously injured","On-lookers would be seriously injured",1));
	        server.addQuestionAnswer(3, new QuestionAnswerVO("The driver would pass out","The driver would pass out",0));
	        
	        server.addPauseQuestion(1, new QuestionVO(4, "Which of the following events ONLY required YOUR resources to effectively resolve it?", "MC"));
	        server.addQuestionAnswer(4, new QuestionAnswerVO("Tanker Collision", "Tanker Collision", -9999));
	        server.addQuestionAnswer(4, new QuestionAnswerVO("Field Chemical Removal", "Field Chemical Removal", -9999));
	        server.addQuestionAnswer(4, new QuestionAnswerVO("Escort a Senator", "Escort a Senator", -9999));
	        server.addQuestionAnswer(4, new QuestionAnswerVO("Smoking Kills", "Smoking Kills", -9999));         
	        
	        server.addPauseQuestion(1, new QuestionVO(5, "Which of the following events is directly related to the Intelligence Briefing you recieved?", "MC"));
	        server.addQuestionAnswer(5,new QuestionAnswerVO("Luncheon Nausea", "Luncheon Nausea", 0));
	        server.addQuestionAnswer(5,new QuestionAnswerVO("Chemical Investigation", "Chemical Investigation", 0));
	        server.addQuestionAnswer(5,new QuestionAnswerVO("Loitering Vehicle", "Loitering Vehicle", 1));
	        server.addQuestionAnswer(5,new QuestionAnswerVO("Rush the field", "Rush the field", 0));
	        
	        server.addPauseQuestion(1, new QuestionVO(6, "Who needed to arrive FIRST at the Tanker Collision?", "MC"));
	        server.addQuestionAnswer(6,new QuestionAnswerVO("Police", "Police", 1));
	        server.addQuestionAnswer(6,new QuestionAnswerVO("Fire", "Fire", 0));
	        server.addQuestionAnswer(6,new QuestionAnswerVO("Hazmat", "Hazmat", 0));
	        server.addQuestionAnswer(6,new QuestionAnswerVO("None of the above: Order didn't matter for this event", "None of the above", 0));
	        
	        server.addPauseQuestion(1, new QuestionVO(7, "Given what they did to the bank, Penn State students will most likely retaliate on the students from which of the following schools?", "MC"));
	        server.addQuestionAnswer(7,new QuestionAnswerVO("University of Michgan", "University of Michigan", 0));
	        server.addQuestionAnswer(7,new QuestionAnswerVO("Ohio State University", "Ohio State University", 1));
	        server.addQuestionAnswer(7,new QuestionAnswerVO("Iowa State University", "Iowa State University", 0));
	        server.addQuestionAnswer(7,new QuestionAnswerVO("Michigan State University", "Michigan State University", 0));
	        
	        server.addPauseQuestion(1, new QuestionVO(8, "Which event required the MOST resources to effectively resolve it?", "MC"));
	        server.addQuestionAnswer(8,new QuestionAnswerVO("Luncheon Nausea", "Luncheon Nausea", 0));
	        server.addQuestionAnswer(8,new QuestionAnswerVO("Chemical Investigation", "Chemical Investigation", 0));
	        server.addQuestionAnswer(8,new QuestionAnswerVO("Loitering Vehicle", "Loitering Vehicle", 1));
	        server.addQuestionAnswer(8,new QuestionAnswerVO("Rush the field", "Rush the field", 0));
	        
	        server.addPauseQuestion(1, new QuestionVO(9, "Which event required units to arrive in a specific order?", "MC"));
	        server.addQuestionAnswer(9,new QuestionAnswerVO("Luncheon Nausea", "Luncheon Nausea", 0));
	        server.addQuestionAnswer(9,new QuestionAnswerVO("Chemical Investigation", "Chemical Investigation", 0));
	        server.addQuestionAnswer(9,new QuestionAnswerVO("Loitering Vehicle", "Loitering Vehicle", 1));
	        server.addQuestionAnswer(9,new QuestionAnswerVO("Rush the field", "Rush the field", 0));
	           
	        // End Performance 1
	        
	        // Test questions
	        server.addPauseQuestion(5, new QuestionVO(30, "Intro", "INTRO"));
	        server.addPauseQuestion(5, new QuestionVO(11, "Which event required units to arrive in a specific order?", "MC"));
	        server.addQuestionAnswer(11,new QuestionAnswerVO("Luncheon Nausea", "Luncheon Nausea", -9999));
	        server.addQuestionAnswer(11,new QuestionAnswerVO("Chemical Investigation", "Chemical Investigation", -9999));
	        server.addQuestionAnswer(11,new QuestionAnswerVO("Loitering Vehicle", "Loitering Vehicle", -9999));
	        server.addQuestionAnswer(11,new QuestionAnswerVO("Rush the field", "Rush the field", -9999)); 
	        // end test questions
	        
	        // Performance 2 questions
	        server.addPauseQuestion(2, new QuestionVO(31, "The simulation is currently paused and will resume in 60 Seconds. Please try and answer as many questions as possible", "INTRO"));
	        
	        server.addPauseQuestion(2, new QuestionVO(10, "What is the name of the group of pranksters?","MC"));
	        server.addQuestionAnswer(10, new QuestionAnswerVO("I Eta Pi", "I Eta Pi",0));
	        server.addQuestionAnswer(10, new QuestionAnswerVO("Sigma Delta", "Sigma Delta",0));
	        server.addQuestionAnswer(10, new QuestionAnswerVO("Alpha Gamma","Alpha Gamma" ,0));
	        server.addQuestionAnswer(10, new QuestionAnswerVO("Pi Rho", "Pi Rho",1));
	        
	        server.addPauseQuestion(2, new QuestionVO(11,"Which of the following icons indicated events linked to the pranksters?","MC"));
	        server.addQuestionImage(11, "edu/psu/ist/neocities/assets/question_images/perf2_quest2.gif");
	        server.addQuestionAnswer(11, new QuestionAnswerVO("A","A",0));
	        server.addQuestionAnswer(11, new QuestionAnswerVO("B","B",0));
	        server.addQuestionAnswer(11, new QuestionAnswerVO("C","C",0));
	        server.addQuestionAnswer(11, new QuestionAnswerVO("D","D",1));
	        
	        // #14
	        server.addPauseQuestion(2, new QuestionVO(12,"What would MOST likely happen if units didn't arrive on scene within 40 seconds in the case of the laptop-throwing student?","MC"));
	        server.addQuestionAnswer(12, new QuestionAnswerVO("The student would move on to throwing Molotov cocktails. ","The student would move on to throwing Molotov cocktails. ",0));
	        server.addQuestionAnswer(12, new QuestionAnswerVO("The gas pipes would start to leak. ","The gas pipes would start to leak. ",1));
	        server.addQuestionAnswer(12, new QuestionAnswerVO("The student would jump off the roof. ","The student would jump off the roof. ",0));
	        server.addQuestionAnswer(12, new QuestionAnswerVO("The media crews would arrive and give Penn State bad publicity. ","The media crews would arrive and give Penn State bad publicity.",0));        
	        
	        server.addPauseQuestion(2, new QuestionVO(13 ,"Name a common element for identifying the prankster group: ","SA"));
	        
	        server.addPauseQuestion(2, new QuestionVO(14,"Which event was caused by the pranksters?","MC"));
	        server.addQuestionAnswer(14, new QuestionAnswerVO("Overflowing toilet","Overflowing toilet",1));
	        server.addQuestionAnswer(14, new QuestionAnswerVO("Possible Student Rave","Possible Student Rave",0));
	        server.addQuestionAnswer(14, new QuestionAnswerVO("Naughty Snow Angels","Naughty Snow Angels",0));
	        server.addQuestionAnswer(14, new QuestionAnswerVO("Swine Flu Scare","Swine Flu Scare",0));
	        
	        server.addPauseQuestion(2, new QuestionVO(15,"What was painted on the Nittany Lion shrine","MC"));
	        server.addQuestionAnswer(15, new QuestionAnswerVO("American Flag","American Flag",0));
	        server.addQuestionAnswer(15, new QuestionAnswerVO("Marriage Proposal","Marriage Proposal",0));
	        server.addQuestionAnswer(15, new QuestionAnswerVO("Greek Symbols","Greek Symbols",1));
	        server.addQuestionAnswer(15, new QuestionAnswerVO("Penn State Logo","Penn State Logo",0));
	        
	        server.addPauseQuestion(2, new QuestionVO(16,"Which of the following events ONLY required YOUR resources to effectively resolve it?","MC"));
	        server.addQuestionAnswer(16, new QuestionAnswerVO("Naughty Snow Angels","Naughty Snow Angels",-9999));
	        server.addQuestionAnswer(16, new QuestionAnswerVO("Buzzed Student","Buzzed Student",-9999));
	        server.addQuestionAnswer(16, new QuestionAnswerVO("Plotting at Irving’s","Plotting at Irving’s",-9999));
	        server.addQuestionAnswer(16, new QuestionAnswerVO("Sensitive Material","Sensitive Material",-9999));
	        
	        server.addPauseQuestion(2, new QuestionVO(17,"If there is an attack on the airport, which teammate should arrive on scene FIRST","MC"));
	        server.addQuestionAnswer(17, new QuestionAnswerVO("Police","Police",1));
	        server.addQuestionAnswer(17, new QuestionAnswerVO("Fire","Fire",0));
	        server.addQuestionAnswer(17, new QuestionAnswerVO("Hazmat","Hazmat",0));
	        server.addQuestionAnswer(17, new QuestionAnswerVO("None of the Above: Order doesn't matter for this event","None of the Above: Order doesn't matter for this event",0));
	        
	        server.addPauseQuestion(2, new QuestionVO(18 ,"What time will the attack on the airport be? ","SA"));
	        

	        server.addPauseQuestion(2, new QuestionVO(19 ,"Based on who has ALWAYS been the CORRECT SOLUTION to the prankster events so far, which specific responce unit will you most likely have to send to the next prankster event? ","MC"));
	        server.addQuestionAnswer(19, new QuestionAnswerVO("Police","Police",0));
	        server.addQuestionAnswer(19, new QuestionAnswerVO("Fire","Fire",0));
	        server.addQuestionAnswer(19, new QuestionAnswerVO("Hazmat","Hazmat",1));
	        server.addQuestionAnswer(19, new QuestionAnswerVO("None of the Above","None of the Above",0));

	        // Training 2 questions...
	        server.addPauseQuestion(3, new QuestionVO(32, "The simulation is currently paused and will resume in 15 Seconds. Please try and answer as many questions as possible", "INTRO"));
	        
	        server.addPauseQuestion(3, new QuestionVO(20, "Please rate the effectiveness of the training you just recieved...", "MC"));
	        server.addQuestionAnswer(20, new QuestionAnswerVO("1-Not Effective at all", "1", -9999));
	        server.addQuestionAnswer(20, new QuestionAnswerVO("2", "2", -9999));
	        server.addQuestionAnswer(20, new QuestionAnswerVO("3-Neutral", "3", -9999));
	        server.addQuestionAnswer(20, new QuestionAnswerVO("4", "4", -9999));
	        server.addQuestionAnswer(20, new QuestionAnswerVO("5-Very Effective", "5", -9999));
	        
	        server.addPauseQuestion(3, new QuestionVO(21, "How ready do you feel for an actual performance session?", "MC"));
	        server.addQuestionAnswer(21, new QuestionAnswerVO("1-Not Ready at all", "1", -9999));
	        server.addQuestionAnswer(21, new QuestionAnswerVO("2", "2", -9999));
	        server.addQuestionAnswer(21, new QuestionAnswerVO("3-Neutral", "3", -9999));
	        server.addQuestionAnswer(21, new QuestionAnswerVO("4", "4", -9999));
	        server.addQuestionAnswer(21, new QuestionAnswerVO("5-Very Ready", "5", -9999));
	        
	        // End Training 2 questions

	        //End performance 2
	        
	        
	        Template:
	        server.addPauseQuestion(2, new QuestionVO(,"","MC"));
	        server.addQuestionAnswer(, new QuestionAnswerVO("","",0));
	        server.addQuestionAnswer(, new QuestionAnswerVO("","",0));
	        server.addQuestionAnswer(, new QuestionAnswerVO("","",0));
	        server.addQuestionAnswer(, new QuestionAnswerVO("","",0));
	        
	        comm.consoleMessage("RO- Scenario Pause Questions Loaded");
		}
		
		public void loadPauses() {
			
			String instructions = "Please wait for the scenario to resume.";
			int introSeconds = 3;
			
			// ONR Training 2 Pause
			server.addScenarioPause(5, new PauseVO(3, instructions, "Please wait for the scenario to resume.", introSeconds));
	        server.setPauseDispatchTime(3, 5, 25);
	        server.setPauseDuration(3, 0, 15);
			
	        // ONR Scenario 1 Pause
			server.addScenarioPause(6, new PauseVO(1, instructions, "Please wait for the scenario to resume.", introSeconds));
			server.setPauseDispatchTime(1, 9, 25);
			server.setPauseDuration(1, 1, 03);
			
			// ONR Scenario 2 Pause
			server.addScenarioPause(7, new PauseVO(2, instructions, "Please wait for the scenario to resume.", introSeconds));
			server.setPauseDispatchTime(2, 9, 25); // should be 9 25
			server.setPauseDuration(2,1, 03); // should be 1 10
		
			// Police only test scenario
			
			server.addScenarioPause(3,new PauseVO(5, instructions, "Please wait for the scenario to resume.", introSeconds));
			server.setPauseDispatchTime(5, 0, 15);
			server.setPauseDuration(5, 0, 20);
			
			comm.consoleMessage("RO - Scenario Pauses Loaded");
		
		}
}
*/