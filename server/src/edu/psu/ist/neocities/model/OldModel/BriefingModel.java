package edu.psu.ist.neocities.model.OldModel;

import java.util.ArrayList;
import java.util.List;
import edu.psu.ist.neocities.value.OldValue.*;

public final class BriefingModel {

    /****************************************************************
     * Singleton Design
     ****************************************************************/
    private static BriefingModel instance = new BriefingModel();
    
    public static BriefingModel instance(){
        return instance;
    }
    
    /****************************************************************
     * Variables
     ****************************************************************/
    public List<BriefingVO> data = new ArrayList<BriefingVO>();
    
    /****************************************************************
     * Functions
     ****************************************************************/
    public BriefingVO getBriefing(int briefingID){
        BriefingVO briefing = new BriefingVO();
        
        for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).getBriefingID() == briefingID){               
                briefing = data.get(i);
                break;
            }
        }
        
        return briefing;       
    }
    
    //used by NCServer to set dispatchTime of the pause event
    public void setBriefingDispatchTime( int briefingID, int minute, int seconds){
        
        long ms = ((minute * 60) + seconds) * 1000;
        
        for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).getBriefingID() == briefingID){               
                data.get(i).setDispatchTime(ms);
                break;
            }
        }
    }
    
    public void addBriefingPermission(int briefingID, int permission) {
        for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).getBriefingID() == briefingID){               
                data.get(i).addPermission(permission);
                break;
            }
        }
    }
    
    public void addBriefingImage(int briefingID, String image) {
        for (int i = 0; i <= data.size() - 1; i++){
            if (data.get(i).getBriefingID() == briefingID){               
                data.get(i).setImage(image);
                break;
            }
        }
    }
    
    public void clearModel()
    {
        this.data = new ArrayList<BriefingVO>();
    }
    
}
