package edu.psu.ist.neocities.value.dataTypes;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Element;

import edu.psu.ist.neocities.interfaces.InfoInterface;

@Root
@Default (DefaultType.FIELD)
public class AlertData implements InfoInterface {
	
	//public String SNORTID;
	
	//public String SNORTDESCR;
	
	public String DATE;
	
	public String TIMESTAMP;
	
	public String SOURCEIP;
	
	@Element (required = false)
	public String SOURCEPORT = "";
	
	//public String DESTIP;
	
	@Element (required = false)
	public String DESTPORT = "";
	
	@Element (required = false)
	public String TAB = "av";

	@Element (required = false)
	public String USER = "";
	
	@Element (required = false)
	public String EVENTID = "";
	
	@Element (required = false)
	public String LOGONTYPE = "";
	
	@Element (required = false)
	public String DESCRIPTION = "";
	
	@Element (required = false)
	public String STATUS = "";
	
	@Element (required = false)
	public String UPDATE = "";
	
	@Element (required = false)
	public String KB = "";
	
	@Element (required = false)
	public String CLASSIFICATION = "";
	
	@Element (required = false)
	public String APPROVEDDATE = "";
	
	@Element (required = false)
	public String FILENAME = "";
	
	@Element (required = false)
	public String RISKTYPE = "";
	
	@Element (required = false)
	public String ACTION = "";
	
	@Element (required = false)
	public String ORIGINALLOCATION = "";
	
	@Element (required = false)
	public String ACTIONDESCRIPTION = "";

	public AlertData()
	{
		
	}
	
	public AlertData(String dATE,
			String tIMESTAMP, String sOURCEIP, String sOURCEPORT,
			String dESTPORT, String tAB, String uSER, String eVENTID,
			String lOGONTYPE, String dESCRIPTION, String sTATUS, String uPDATE,
			String kB, String cLASSIFICATION, String aPPROVEDDATE,
			String fILENAME, String rISKTYPE, String aCTION, String oRIGINALLOCATION,
			String aCTIONDESCRIPTION) {
		super();
		TAB = tAB;
		//SNORTID = sNORTID;
		//SNORTDESCR = sNORTDESCR;
		DATE = dATE;
		TIMESTAMP = tIMESTAMP;
		SOURCEIP = sOURCEIP;
		SOURCEPORT = sOURCEPORT;
		//DESTIP = dESTIP;
		DESTPORT = dESTPORT;
		USER = uSER;
		EVENTID = eVENTID;
		LOGONTYPE = lOGONTYPE;
		DESCRIPTION = dESCRIPTION;
		STATUS = sTATUS;
		UPDATE = uPDATE;
		KB = kB;
		CLASSIFICATION = cLASSIFICATION;
		APPROVEDDATE = aPPROVEDDATE;
		FILENAME = fILENAME;
		RISKTYPE = rISKTYPE;
		ACTION = aCTION;
		ORIGINALLOCATION = oRIGINALLOCATION;
		ACTIONDESCRIPTION = aCTIONDESCRIPTION;
	}
	
	
	
	

}
