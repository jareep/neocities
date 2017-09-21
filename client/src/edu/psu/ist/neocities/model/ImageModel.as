package edu.psu.ist.neocities.model
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.ImageVO;
	
	import mx.collections.ArrayCollection;
	
	import mx.controls.Alert;
	
	public class ImageModel extends ModelFactory
	{
		public function ImageModel()
		{
			super();
			trace("load ImageModel");
		}
		
		[Bindable]
		public var incidentIcons : ArrayCollection;
		
		[Bindable]
		public var resourceIcons : ArrayCollection;
		
		/****************************************************************
	     * Incident Icons
 	     * 	                
 	     * These variables embed the incident icons for the available  	                
 	     * resource units
         *
	     ****************************************************************/								

		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_bomb.gif")]
		private var bomb : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_bombexp.gif")]
		private var bombexp : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_bombthr.gif")]
		private var bombthr : Class;	
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_checkpoint.gif")]
		private var checkpoint : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_chemical.gif")]
		private var chemical : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_civdemo.gif")]
		private var civdemo : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_civriot.gif")]
		private var civriot : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_crimact.gif")]
		private var crimact : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_deadbody.gif")]
		private var deadbody : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_death.gif")]
		private var death : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_escort.gif")]
		private var escort : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_fireinc.gif")]
		private var fireinc : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/ignore.gif")]
		private var ignore : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_hazinc.gif")]
		private var hazinc : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_medic.gif")]
		private var medic : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_nresfire.gif")]
		private var nresfire : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_radioact.gif")]
		private var radioact : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_resfire.gif")]
		private var resfire : Class;
				
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_schfire.gif")]
		private var schfire : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_shoot.gif")]
		private var shoot : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_smoke.gif")]
		private var smoke : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_toxicgas.gif")]
		private var toxicgas : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_toxinfect.gif")]
		private var toxinfect : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_vehhij.gif")]
		private var vehhij : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_vehinc.gif")]
		private var vehinc : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_normal/i_pirho.gif")]
		private var pirho : Class;
		
		//*******************************************Small Icons
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_bomb.gif")]
		private var bombsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_bombexp.gif")]
		private var bombexpsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_bombthr.gif")]
		private var bombthrsm : Class;	
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_checkpoint.gif")]
		private var checkpointsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_chemical.gif")]
		private var chemicalsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_civdemo.gif")]
		private var civdemosm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_civriot.gif")]
		private var civriotsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_crimact.gif")]
		private var crimactsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_deadbody.gif")]
		private var deadbodysm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_death.gif")]
		private var deathsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_escort.gif")]
		private var escortsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_fireinc.gif")]
		private var fireincsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/ignore.gif")]
		private var ignoresm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_hazinc.gif")]
		private var hazincsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_medic.gif")]
		private var medicsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_nresfire.gif")]
		private var nresfiresm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_radioact.gif")]
		private var radioactsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_resfire.gif")]
		private var resfiresm : Class;
				
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_schfire.gif")]
		private var schfiresm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_shoot.gif")]
		private var shootsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_smoke.gif")]
		private var smokesm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_toxicgas.gif")]
		private var toxicgassm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_toxinfect.gif")]
		private var toxinfectsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_vehhij.gif")]
		private var vehhijsm : Class;
	
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_vehinc.gif")]
		private var vehincsm : Class;
		
		[Bindable]
		[Embed (source="edu/psu/ist/neocities/assets/event_icons_small/i_pirho.gif")]
		private var pirhosm : Class;
						
		public function getIncidentIcon( icon : String ) : Class {
            var iconClass:Class;
            
            var extension : int = icon.indexOf(".");
                  
            if(extension != 0) {
            	//trace("Icon = " + icon + ", .extension = " + extension.toString() + "Raw Icon = " + icon.substring(2, extension) );
            	icon = icon.substring(2, extension);
            }

            if (icon == "bomb") {
                iconClass = bomb;
            } else if (icon == "bombexp") {
                iconClass = bombexp;
            } else if (icon == "bombthr") {
                iconClass = bombthr;
            } else if (icon == "checkpoint") {
                iconClass = checkpoint;
            } else if (icon == "chemical") {
                iconClass = chemical;
            } else if (icon == "civdemo") {
                iconClass = civdemo;
            } else if (icon == "civriot") {
                iconClass = civriot;
            } else if (icon == "crimact") {
                iconClass = crimact;
            } else if (icon == "deadbody") {
                iconClass = deadbody;
            } else if (icon == "death") {
                iconClass = death;
            } else if (icon == "escort") {
                iconClass = escort;
            } else if (icon == "fireinc") {
                iconClass = fireinc;
            } else if (icon == "ignore") {
            	iconClass = ignore;
            } else if (icon == "hazinc") {
                iconClass = hazinc;
            } else if (icon == "medic") {
                iconClass = medic;
            } else if (icon == "nresfire") {
                iconClass = nresfire;
            } else if (icon == "radioact") {
                iconClass = radioact;
            } else if (icon == "resfire") {
                iconClass = resfire;
            } else if (icon == "schfire") {
                iconClass = schfire;
            } else if (icon == "shoot") {
                iconClass = shoot;
            } else if (icon == "smoke") {
                iconClass = smoke;
            } else if (icon == "toxicgas") {
                iconClass = toxicgas;
            } else if (icon == "toxinfect") {
                iconClass = toxinfect;
            } else if (icon == "vehhij") {
                iconClass = vehhij;
            } else if (icon == "vehinc") {
                iconClass = vehinc;
            }
            else if (icon == "pirho") {
            	iconClass = pirho;
            }
            
                

            return iconClass;
        }
        
        public function getSmallIncidentIcon( icon : String ) : Class {
                        
            var iconClass:Class;
            
            var extension : int = icon.indexOf(".");
            
           
              
            if(extension != 0) {
            	Alert.show("Icon = " + icon + ", .extension = " + extension.toString() + "Raw Icon = " + icon.substring(2, extension) );
            	icon = icon.substring(2, extension);
            }

            if (icon == "bomb") {
                iconClass = bombsm;
            } else if (icon == "bombexp") {
                iconClass = bombexpsm;
            } else if (icon == "bombthr") {
                iconClass = bombthrsm;
            } else if (icon == "checkpoint") {
                iconClass = checkpointsm;
            } else if (icon == "chemical") {
                iconClass = chemicalsm;
            } else if (icon == "civdemo") {
                iconClass = civdemosm;
            } else if (icon == "civriot") {
                iconClass = civriotsm;
            } else if (icon == "crimact") {
                iconClass = crimactsm;
            } else if (icon == "deadbody") {
                iconClass = deadbodysm;
            } else if (icon == "death") {
                iconClass = deathsm;
            } else if (icon == "escort") {
                iconClass = escortsm;
            } else if (icon == "fireinc") {
                iconClass = fireincsm;
            } else if (icon == "ignore") {
            	iconClass = ignoresm;
            } else if (icon == "hazinc") {
                iconClass = hazincsm;
            } else if (icon == "medic") {
                iconClass = medicsm;
            } else if (icon == "nresfire") {
                iconClass = nresfiresm;
            } else if (icon == "radioact") {
                iconClass = radioactsm;
            } else if (icon == "resfire") {
                iconClass = resfiresm;
            } else if (icon == "schfire") {
                iconClass = schfiresm;
            } else if (icon == "shoot") {
                iconClass = shootsm;
            } else if (icon == "smoke") {
                iconClass = smokesm;
            } else if (icon == "toxicgas") {
                iconClass = toxicgassm;
            } else if (icon == "toxinfect") {
                iconClass = toxinfectsm;
            } else if (icon == "vehhij") {
                iconClass = vehhijsm;
            } else if (icon == "vehinc") {
                iconClass = vehincsm;
            } else if (icon == "pirho") {
            	iconClass = pirhosm;
            }
                

            return iconClass;
        }
        
		public function populateIconArray() : void {
			incidentIcons = new ArrayCollection();
			
			incidentIcons.addItem( new ImageVO( "Bomb", "edu/psu/ist/neocities/assets/event_icons_normal/i_bomb.gif", "bomb" ));
			incidentIcons.addItem( new ImageVO( "Bomb Explosion", "edu/psu/ist/neocities/assets/event_icons_normal/i_bombexp.gif", "bombexp" ) );
			incidentIcons.addItem( new ImageVO( "Bomb Unknown", "edu/psu/ist/neocities/assets/event_icons_normal/i_bombthr.gif", "bombthr" ) );
			incidentIcons.addItem( new ImageVO( "Checkpoint", "edu/psu/ist/neocities/assets/event_icons_normal/i_checkpoint.gif", "checkpoint") );			
			incidentIcons.addItem( new ImageVO( "Chemical", "edu/psu/ist/neocities/assets/event_icons_normal/i_chemical.gif", "chemical") );			
			incidentIcons.addItem( new ImageVO( "Civilian Demonstration", "edu/psu/ist/neocities/assets/event_icons_normal/i_civdemo.gif", "civdemo") );
			incidentIcons.addItem( new ImageVO( "Civilian Riot", "edu/psu/ist/neocities/assets/event_icons_normal/i_civriot.gif", "civriot") );
			incidentIcons.addItem( new ImageVO( "Criminal Act", "edu/psu/ist/neocities/assets/event_icons_normal/i_crimact.gif", "crimact") );
			incidentIcons.addItem( new ImageVO( "Dead Body", "edu/psu/ist/neocities/assets/event_icons_normal/i_deadbody.gif", "deadbody") );
			incidentIcons.addItem( new ImageVO( "Death", "edu/psu/ist/neocities/assets/event_icons_normal/i_death.gif", "death") );
			incidentIcons.addItem( new ImageVO( "Escort", "edu/psu/ist/neocities/assets/event_icons_normal/i_escort.gif", "escort") );
			incidentIcons.addItem( new ImageVO( "Fire", "edu/psu/ist/neocities/assets/event_icons_normal/i_fireinc.gif", "fireinc") );
			incidentIcons.addItem( new ImageVO( "Hazmat", "edu/psu/ist/neocities/assets/event_icons_normal/i_hazinc.gif", "hazinc") );
			incidentIcons.addItem( new ImageVO( "Medic", "edu/psu/ist/neocities/assets/event_icons_normal/i_medic.gif", "medic"));
			incidentIcons.addItem( new ImageVO( "Non-Residential Fire", "edu/psu/ist/neocities/assets/event_icons_normal/i_nresfire.gif", "nresfire") );
			incidentIcons.addItem( new ImageVO( "Radioactive", "edu/psu/ist/neocities/assets/event_icons_normal/i_radioact.gif", "radioact") );
			incidentIcons.addItem( new ImageVO( "Residential Fire", "edu/psu/ist/neocities/assets/event_icons_normal/i_resfire.gif", "resfire") );
			incidentIcons.addItem( new ImageVO( "School Fire", "edu/psu/ist/neocities/assets/event_icons_normal/i_schfire.gif", "schfire") );
			incidentIcons.addItem( new ImageVO( "Shoot", "edu/psu/ist/neocities/assets/event_icons_normal/i_shoot.gif", "shoot") );
			incidentIcons.addItem( new ImageVO( "Smoke", "edu/psu/ist/neocities/assets/event_icons_normal/i_smoke.gif", "smoke") );
			incidentIcons.addItem( new ImageVO( "Toxic Gas", "edu/psu/ist/neocities/assets/event_icons_normal/i_toxicgas.gif", "toxicgas") );
			incidentIcons.addItem( new ImageVO( "Infectious Waste", "edu/psu/ist/neocities/assets/event_icons_normal/i_toxinfect.gif", "toxinfect") );
			incidentIcons.addItem( new ImageVO( "Vehicle", "edu/psu/ist/neocities/assets/event_icons_normal/i_vehhij.gif", "vehhij") );
			incidentIcons.addItem( new ImageVO( "Vehicle Accident", "edu/psu/ist/neocities/assets/event_icons_normal/i_vehinc.gif", "vehinc") );
			incidentIcons.addItem( new ImageVO( "Pi Rho", "edu/psu/ist/neocities/assets/event_icons_normal/i_pirho.gif", "pirho") );
		
		}	
		
	}//end of class
}//end of package