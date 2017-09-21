package edu.psu.ist.neocities.model.OldModel;
import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.neocities.value.*;

/**
 * @author bhellar
 * @date 9-11-08
 * @description stores in a list the RoleVO Data
 */
public final class RoleModel {

	/****************************************************************
	 * Singleton Design
	 ****************************************************************/
	private static RoleModel instance = new RoleModel();
	
	private RoleModel() {
		// Required for Singleton Design Pattern
	}
			
	public static RoleModel instance(){
		return instance;
	}
	
	/****************************************************************
	 * Variables
	 ****************************************************************/
	public List<RoleVO> data = new ArrayList<RoleVO>();
			
}
