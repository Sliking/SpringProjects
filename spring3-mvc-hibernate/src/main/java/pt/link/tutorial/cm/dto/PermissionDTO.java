package pt.link.tutorial.cm.dto;

import java.util.ArrayList;
import java.util.List;

public class PermissionDTO {

	private List<Integer> permissionIDList = new ArrayList<Integer>();
	private Integer profileID;
	private String profileName;
	
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Integer getProfileID() {
		return profileID;
	}

	public void setProfileID(Integer profileID) {
		this.profileID = profileID;
	}

	public List<Integer> getPermissionIDList() {
		return permissionIDList;
	}

	public void setPermissionIDList(List<Integer> permissionIDList) {
		this.permissionIDList = permissionIDList;
	}
	
}
