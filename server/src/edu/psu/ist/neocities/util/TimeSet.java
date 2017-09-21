package edu.psu.ist.neocities.util;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;


@Root
@Default (DefaultType.PROPERTY)
public class TimeSet
{
	@Attribute (required = false)
	public int min;
	
	@Attribute (required = false)
	public int sec;
	
	@Attribute (required = false)
	public int timeSteps = -1;
	
	public TimeSet()
	{
		super();
	}
	
	public TimeSet(int min, int sec)
	{
		this.min = min;
		this.sec = sec;
	}
	
	public int getTimeSteps(int tsLength)
	{
		if (timeSteps != -1)
		{
			return timeSteps;
		}
		
		return Math.abs(((min*60)+sec)/tsLength);
	}
	
	public long getTSinMS (int tsLength)
	{
		
		return this.getTimeSteps(tsLength) * (1000 * tsLength);
	}
	
	
	public long calcTotalMS ()
	{
		return this.calculateTotalSeconds() * 1000;
	}
	
	public int calculateTotalSeconds ()
	{
		return (min * 60) + sec;
	}
	

	
	public void setTimeSteps(int numTimeSteps, int tsLength){
		
		int seconds = numTimeSteps*tsLength;
	
		this.min = seconds/60;
		this.sec = seconds%60;
	}
	
	public String toString()
	{
		return this.min + ":" + this.sec;
	}
	
}
