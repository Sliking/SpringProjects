package pt.link.tutorial.cm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROFILE")
public class Profile {

	@Id
	@Column(name="PROFILE_ID")
	private Integer profile_id;
	
	@Column(name="PROFILE_NAME")
	private String profile_name;
	
	@Column(name="PERMISSIONS_ID")
	private String permissions_id;

	public Integer getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(Integer profile_id) {
		this.profile_id = profile_id;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	public String getPermissions_id() {
		return permissions_id;
	}

	public void setPermissions_id(String permissions_id) {
		this.permissions_id = permissions_id;
	}
	
}
