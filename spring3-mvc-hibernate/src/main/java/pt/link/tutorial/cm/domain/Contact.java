package pt.link.tutorial.cm.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="CONTACTS")
public class Contact {
		
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="FIRSTNAME")
	private String firstname;

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;

	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="TELEPHONE")
	private Integer telephone;
	
	@Column(name="BIRTHDAY")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date birthDay;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ZIPCODE")
	private String zipCode;
	
	@Column(name="TESTFIELD")
	private String testField;
	
	@Column(name="CLUB")
	private String club;
	
	@ManyToOne
    @JoinColumn(name="ID_PERSON")
	private Person person;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Contact other = (Contact) obj;
		
		if (this.firstname == other.firstname ||
				this.lastname == other.lastname ||
				this.email == other.email ||
				this.telephone == other.telephone)
			return true;
		
		return false;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstname() {
		return firstname;
	}
	public Integer getId() {
		return id;
	}
	public String getLastname() {
		return lastname;
	}
	
	public Person getPerson() {
		return person;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * @return the club
	 */
	public String getClub() {
		return club;
	}
	
	/**
	 * @param club the club to set
	 */
	public void setClub(String club) {
		this.club = club;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", telephone=" + telephone
				+ ", club=" + club + "]";
	}
}
