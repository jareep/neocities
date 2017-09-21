package edu.psu.ist.neocities.value;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root
@Default (DefaultType.FIELD)
public class RoleVO {
    @Attribute
    public int roleID; //[***]
    
    public String roleName;
    
    @Element (required=false)
    public String roleImage;
    
    public RoleVO()
    {
        super();
    }

    public RoleVO(int roleID, String roleName, String roleImage) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.roleImage = roleImage;
	}

	public int getId() {
        return roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleImage() {
        return roleImage;
    }

    public void setRoleID(int id) {
        this.roleID = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleImage(String roleImage) {
        this.roleImage = roleImage;
    }
    
    

}
