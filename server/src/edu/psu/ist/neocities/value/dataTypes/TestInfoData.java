package edu.psu.ist.neocities.value.dataTypes;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

import edu.psu.ist.neocities.interfaces.InfoInterface;

@Root
@Default (DefaultType.FIELD)
public class TestInfoData implements InfoInterface {
	
	public String name;
	
	public String x;
	
	public String y;
	
	public TestInfoData()
	{
		//super();
	}

	public TestInfoData(String name, String x, String y) {
		//super();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "blah";
	}
	
	

}
