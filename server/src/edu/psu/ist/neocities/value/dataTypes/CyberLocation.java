package edu.psu.ist.neocities.value.dataTypes;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

import edu.psu.ist.neocities.interfaces.LocationInterface;

@Root
@Default (DefaultType.FIELD)
public class CyberLocation implements LocationInterface {
	public String cyberName;
	
	public String cyberAddress;
	
	public String image;

	public CyberLocation(String cyberName, String cyberAddress, String image) {
		super();
		this.cyberName = cyberName;
		this.cyberAddress = cyberAddress;
		this.image = image;
	}

	public CyberLocation() {
		super();
	}
	
	
}
