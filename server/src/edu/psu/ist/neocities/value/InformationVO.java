package edu.psu.ist.neocities.value;

import java.io.StringWriter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.core.Persister;

import edu.psu.ist.neocities.interfaces.InfoInterface;
import edu.psu.ist.neocities.util.TimeSet;

@Root
@Default (DefaultType.FIELD)
public class InformationVO {
    
    /*************************************************************************************************
     * Author: Vince Mancuso
     * Date: 5/31/2011
     * Description:
     * This function will be the holder for all information pieces in NeoCITIES.
     *                                                   
     * This is programmed to implement the SimpleXML framework for data binding.                     
     *************************************************************************************************/
    
	/*****XML SET VARIABLES******/
    @Attribute
    public int id;              // The ID of the information piece

    public String type;            // What type of information, this MUST correspond to the
                            
    public TimeSet dispatchTime;    // What time will this information be dispatched (in timesteps)
    
    @Element (required = false)
    public int eventID = -1;         // The ID of the event that this information corresponds to (-1 if dummy)
    
    @Element (required = false)
    public int locationID = -1;		// The ID of the location that the information corresponds to (-1 if none)
    
    @Element (required = false)
    public int roleID = -1;          // If this information is available to only one player, their role id (-1 if visible by all)
    
    @Element (required = false)
    public int numReturns = 0;      // Number of returns that the information is permitted, default is 0
    
    public InfoInterface data;
    
    @Element (required = false)
    public InfoInterface returnData = null;
    
    /*****SYSTEM MANIPULATION VARIABLES******/
    @Transient
    public String dataXML;
    
    @Transient
    public String returnDataXML;
    
    //Basic constructor (must be empty for purposes of XML binding
    public InformationVO() {
        super();    
    }
    
    
    
    public InformationVO(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}



	/***********************************************
     *                 Getters                     *  
     ***********************************************/
    public int getInformationId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public TimeSet getDispatchTime() {
        return (this.dispatchTime);
    }

    public int getEventID() {
        return this.eventID;
    }

    public int getRoleID() {
        return this.roleID;
    }

    public int getNumReturns() {
        return this.numReturns;
    }

    public InfoInterface getData() {
        return this.data;
    }
    
    public InfoInterface getReturnData() {
        return this.returnData;
    }

    public String getDataXML() {
        return dataXML;
    }

    public String getReturnDataXML() {
        return returnDataXML;
    }
    
    
    /***********************************************
     *                 Setters                     *  
     ***********************************************/
    public void setInformationId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDispatchTime(TimeSet dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setNumReturns(int numReturns) {
        this.numReturns = numReturns;
    }

    public void setData(InfoInterface data) {
        this.data = data;
    }
    
    public void setReturnData(InfoInterface returnData) {
        this.returnData = returnData;
    }
    
    /***********************************************
     *                 Helpers                     *  
     ***********************************************/
    
    /**
     * This function populates the return and regular data into a string... This may be required so that the info
     * can be passed through BlazeDS... Though it may be removed if found to be unnecessary
     * @throws Exception
     */
    public void setDataXML() throws Exception
    {
        StringWriter sw = new StringWriter();
        
        Serializer serializer = new Persister();
        
        // Writes this object as XML to the string writer
        serializer.write(this.data, sw);
        
        // sets as string
        this.dataXML = sw.toString();
        
        if (this.returnData != null)
        {
            sw = new StringWriter();
            serializer.write(this.returnData, sw);
            
            this.returnDataXML = sw.toString();
        }
    }
    
}
