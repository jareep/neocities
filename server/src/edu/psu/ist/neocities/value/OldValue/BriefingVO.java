package edu.psu.ist.neocities.value.OldValue;

import java.util.ArrayList;
import java.util.List;

public class BriefingVO {
    private int briefingID;
    private String briefing;
    private String image;
    private String alert;
    
    private List<Number> permissions = new ArrayList<Number>();
                         
    private long dispatchTime = 0;
    
    public BriefingVO() {
        
    }
    

    public BriefingVO(int _briefingID, String _briefing)
    {
        this.briefingID = _briefingID;
        this.briefing = _briefing;
    }
    
    public BriefingVO(int _briefingID, String _briefing, String _alert)
    {
        this.briefingID = _briefingID;
        this.briefing = _briefing;
        this.alert = _alert;
    }
    
    public int getBriefingID() {
        return briefingID;
    }
    public String getBriefing() {
        return briefing;
    }
    public String getImage() {
        return image;
    }
    public long getDispatchTime() {
        return dispatchTime;
    }
    public int getPermissionLength() {
        return permissions.size();
    }
    public String getAlert () {
        return this.alert;
    }
    public List<Number> getPermissions() {
        return permissions;
    }
    public void addPermission(int permission) {
        permissions.add(permission);
    }
    public void setAlert (String alert) {
        this.alert = alert;
    }
    
    public void setBriefingID(int briefingID) {
        this.briefingID = briefingID;
    }
    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setDispatchTime(long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }
    public void setPermissions(List<Number> permissions)
    {
        this.permissions = permissions;
    }
    
    
    
}
