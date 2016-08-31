package pt.link.tutorial.cm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSONS")
public class Person {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;

	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	
	public String getFirstname() {
		return firstname;
	}
	public Integer getId() {
		return id;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}

	
	
	
}
