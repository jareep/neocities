package edu.psu.ist.neocities.value;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.core.Persister;


import edu.psu.ist.neocities.interfaces.LocationInterface;
import edu.psu.ist.neocities.value.dataTypes.CyberLocation;
import edu.psu.ist.neocities.value.dataTypes.TestInfoData;

@Root
@Default (DefaultType.FIELD)

public class LocationVO {
    
    @Attribute
    public int id;
    
    public String name;

	public String type;
    
	@Element (required = false)
	public LocationInterface data = new CyberLocation();
    
    @Element (required = false)
    public double importance = 1;
    
    @Element (required = false)
    public boolean pendLock = true;
    
    @Element (required = false)
    public String locationImage = "";
    
    @ElementList (required = false)
    public List<Integer> connections = new ArrayList<Integer>();
    
    @Transient
    public String dataXML;
    
    @Transient
    public List<String> feedback = new ArrayList<String>(); 
    
    @Transient
    public int activeEvents = 0;
    
    @Transient
    public int pendingUnits = 0;
    
    @Transient
    public int numInformation = 0;
   
    @Transient
    public double avgSeverity = 0.0;
    
    
    
    public LocationVO()
    {
        super();
    }
    
    public LocationVO(int id, String type, LocationInterface data,
			double importance) {
		super();
		this.id = id;
		this.type = type;
		this.data = data;
		this.importance = importance;
		this.activeEvents = 0;
		
	}

    
    
    /***********************************************
     *                 Getters                     *  
     ***********************************************/
    
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public LocationInterface getData() {
        return data;
    }

    public double getImportance() {
        return importance;
    }

    
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(LocationInterface data) {
        this.data = data;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }
    
    
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
        
    
    public void addEvent()
    {
    	this.activeEvents++;
    }
    
    public void removeEvent()
    {
    	if (this.activeEvents > 0) { this.activeEvents--; }
    }
    
    public void addPendingUnit()
    {
    	if (this.pendingUnits <= this.activeEvents) { this.pendingUnits ++; }
    }
    
    public void removePendingUnit()
    {
    	if (this.pendingUnits > 0) { this.pendingUnits--; }
    }
    
    public boolean dispatchEligilble()
    {
    	if (!this.pendLock) { return true; }
    	
    	if (this.pendingUnits > this.activeEvents) { return false; }
    	
    	if (this.pendingUnits == this.activeEvents)
    	{
    		if (this.activeEvents == 0) { return true; } else { return false; }
    	}
    	
    	return true;
    }
    
    public void addFeedbackString(String feedback)
    {
    	this.feedback.add(feedback);
    }
    
    public void addConnection(int locationID)
    {
        this.connections.add(locationID);
    }
    
    
    public void setDataXML() throws Exception
    {
        StringWriter sw = new StringWriter();
        
        Serializer serializer = new Persister();
        
        // Writes this object as XML to the string writer
        serializer.write(this.data, sw);
        
        // sets as string
        this.dataXML = sw.toString();
        
    }
    
    
    
    /*
    public String toString()
    {
        StringWriter sw = new StringWriter();
        
        Serializer serializer = new Persister();
        
        try {
            serializer.write(this, sw);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sw.toString();
        
    }*/
    
}


