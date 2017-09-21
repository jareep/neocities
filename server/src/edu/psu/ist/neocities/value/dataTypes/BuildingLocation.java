package edu.psu.ist.neocities.value.dataTypes;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

import edu.psu.ist.neocities.interfaces.LocationInterface;

@Root
@Default (DefaultType.FIELD)
public class BuildingLocation implements LocationInterface {
	public String buildingName;
	
	public String buildingAddress;
		
	public String buildingImage;

	public BuildingLocation(String buildingName, String buildingAddress,
			String buildingImage) {
		super();
		this.buildingName = buildingName;
		this.buildingAddress = buildingAddress;
		this.buildingImage = buildingImage;
	}

	public BuildingLocation() {
		super();
	}
	
	
}
