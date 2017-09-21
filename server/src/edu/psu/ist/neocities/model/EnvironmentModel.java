package edu.psu.ist.neocities.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

import edu.psu.ist.neocities.control.CommController;
import edu.psu.ist.neocities.value.LocationVO;
import edu.psu.ist.neocities.value.ResourceVO;
import edu.psu.ist.neocities.value.RoleVO;
import edu.psu.ist.neocities.test.*;

@Root
@Default (DefaultType.FIELD)
public class EnvironmentModel {
	
	@Transient
	public static EnvironmentModel instance = new EnvironmentModel();
	
	@Transient
	private CommController comm = CommController.instance();
	
	@Attribute
	public String EnvironmentName;
	
	@ElementList
	public List<LocationVO> Locations;
	
	public List<RoleVO> Roles;
	
	public List<ResourceVO> Resources;
	
	
	public static EnvironmentModel instance()
	{
		return instance;
	}
	
	public EnvironmentModel () 
	{
		super();
	}
	
	public void setEnvironmentName(String EnvironmentName)
	{
		this.EnvironmentName = EnvironmentName;
		
		comm.consoleMessage("========Loading Environment " + this.EnvironmentName + "========");
	}
	
	public void setLocations(List<LocationVO> Locations)
	{
		this.Locations = Locations;
		
		for (int i = 0; i < Locations.size(); i++)
		{
			Locations.get(i).addFeedbackString(Locations.get(i).name + " Has Been Loaded");
		}
		
		comm.consoleMessage("Loaded " + this.Locations.size() + " Locations into Environment " + this.EnvironmentName);
	}
	
	public void setRoles (List<RoleVO> Roles)
	{
		this.Roles = Roles;
		comm.consoleMessage("Loaded " + this.Roles.size() + " Roles into Environment " + this.EnvironmentName);
	}
	
	public void setResources (List<ResourceVO> Resources)
	{
		this.Resources = Resources;
		comm.consoleMessage("Loaded " + this.Resources.size() + " Resources into Environment " + this.EnvironmentName);
	}
	
	public void resetModel()
	{
		if (this.Resources != null) { this.Resources.clear(); }
		if (this.Roles != null) { this.Roles.clear(); }
		this.EnvironmentName = null;
		if (this.Locations != null) { this.Locations.clear(); }
		
		instance = new EnvironmentModel();
	}
	
	public RoleVO getRole(int roleID)
	{
		for (int i = 0; i < Roles.size(); i++)
		{
			if (Roles.get(i).roleID == roleID)
			{
				return Roles.get(i);
			}
		}
		
		return null;
	}
	
	public LocationVO getLocation(int locationID)
	{
		for (int i = 0; i < Locations.size(); i++)
		{
			if (Locations.get(i).id == locationID)
			{
				return Locations.get(i);
			}
		}
		
		return null;
	}
	
	public void addFeedback(int locationID, String feedback)
	{
		for (int i = 0; i < Locations.size(); i++)
		{
			if (Locations.get(i).id == locationID)
			{
				Locations.get(i).addFeedbackString(feedback);
			}
		}
	}
	
	public ResourceVO getResource(int resourceID)
	{
		for (int i = 0; i < Resources.size(); i++)
		{
			if (Resources.get(i).id == resourceID)
			{
				return Resources.get(i);
			}
		}
		
		return null;
	}
	
	public void dispatchResource (int resourceID){//TESTTEMP//
		
		for (int i = 0; i <= Resources.size() - 1; i++){
			if (Resources.get(i).id == resourceID){
				if (!Resources.get(i).isAction())
				{
					Resources.get(i).dispatched++;
					Resources.get(i).available = Resources.get(i).total - Resources.get(i).dispatched;
				}
				
				
				comm.consoleMessage("Resource: Dispatch " + Resources.get(i).resourceName + " - " 
						+ Resources.get(i).available + " / " + Resources.get(i).dispatched + " / " + Resources.get(i).total);
				break;
			}//end of if
		}//end of loop
	}
	
	public void returnResource (int resourceID ){
		
		for (int i = 0; i <= Resources.size() - 1; i++){
			if (Resources.get(i).id == resourceID){
				if (!Resources.get(i).isAction())
				{
					Resources.get(i).dispatched--;
					Resources.get(i).available = Resources.get(i).total - Resources.get(i).dispatched;
				}
					comm.consoleMessage("Resource: Dispatch " + Resources.get(i).resourceName + " - " 
						+ Resources.get(i).available + " / " + Resources.get(i).dispatched + " / " + Resources.get(i).total);
				break;
			}//end of if
		}//end of loop
	}
	
	// if addSubtract is true it adds events, if false it subtracts events
	public void toggleLocationEvent (int locationID, boolean addSubtract)
	{
		for (int i = 0; i < Locations.size(); i++)
		{
			if (Locations.get(i).id == locationID)
			{
				if (addSubtract) { Locations.get(i).addEvent(); } else { Locations.get(i).removeEvent(); }; 
			}
		}
	}
	
	public void togglePendingUnits (int locationID, boolean addSubtract)
	{
		for (int i = 0; i < Locations.size(); i++)
		{
			if (Locations.get(i).id == locationID)
			{
				if (addSubtract) { Locations.get(i).addPendingUnit(); } else { Locations.get(i).removePendingUnit(); }; 
			}
		}
	}
	
	
	/*************************************
	 * TESTING FUNCTIONS                 * 
	 *************************************/
	
	public void addRole(RoleVO role)
	{
		if (Roles == null) { Roles = new ArrayList<RoleVO>(); }
		
		Roles.add(role);
	}
	
	public void addLocation(LocationVO location)
	{
		if (Locations == null) { Locations = new ArrayList<LocationVO>(); }
		
		Locations.add(location);
	}
	
	public void addResource (ResourceVO resource)
	{
		if (Resources == null) { Resources = new ArrayList<ResourceVO>(); }
		
		Resources.add(resource);
	}

}
