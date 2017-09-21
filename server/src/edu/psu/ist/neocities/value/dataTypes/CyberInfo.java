package edu.psu.ist.neocities.value.dataTypes;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

import edu.psu.ist.neocities.interfaces.InfoInterface;

@Root
@Default (DefaultType.FIELD)
public class CyberInfo implements InfoInterface {

	public String infectionType;
	
	public int sevarity;
	
	public String infectionTime;
	
	public CyberInfo() {
		
	}
	
	public CyberInfo(String infectionType, int sevarity, String infectionTime) {
		super();
		this.infectionType = infectionType;
		this.sevarity = sevarity;
		this.infectionTime = infectionTime;
	}

	public String toString()
	{
		return "blah";
	}
}
