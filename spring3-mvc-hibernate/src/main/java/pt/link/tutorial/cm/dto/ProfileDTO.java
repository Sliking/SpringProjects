package pt.link.tutorial.cm.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {

	private static final long serialVersionUID = 1L;
	
	private List<Integer> profileIDList = new ArrayList<Integer>();
	
	private Integer id;
	private String name;
	private String username;
	
	
	public List<Integer> getProfileIDList() {
		return profileIDList;
	}
	public void setProfileIDList(List<Integer> profileIDList) {
		this.profileIDList = profileIDList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	public List<Integer> getprofileIDList(){
		return profileIDList;
	}
	public void setprofileIDList(List<Integer> profileIDList){
		this.profileIDList = profileIDList;
	}
	public void addelementtoprofileIDList(Integer element){
		profileIDList.add(element);
	}
	
	
}
