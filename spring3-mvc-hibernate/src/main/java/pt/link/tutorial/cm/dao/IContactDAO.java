package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.Contact;

public interface IContactDAO {
	
	public void addOrUpdateContact(Contact contact);

	public List<Contact> listContacts();

	public void removeContact(Integer id);

	public Contact getContact(Integer id);

	public List<Contact> listContactsOrderByFirstNameAsc();
	
	public List<Contact> listContactsOrderByFirstNameDesc();

	public List<Contact> listContactsOrderByLastNameAsc();
	
	public List<Contact> listContactsOrderByLastNameDesc();

	public List<Contact> listContactsOrderBirthDayAsc();
	
	public List<Contact> listContactsOrderBirthDayDesc();

	public List<Contact> listContactsOrderByEmailAsc();

	public List<Contact> listContactsOrderByEmailDesc();

	public List<Contact> listContactsOrderByTelephoneAsc();
	
	public List<Contact> listContactsOrderByTelephoneDesc();

	public List<Contact> listContactsOrderByAddressAsc();
	
	public List<Contact> listContactsOrderByAddressDesc();

	public List<Contact> listContactsOrderByZipCodeAsc();
	
	public List<Contact> listContactsOrderByZipCodeDesc();
	
	public List<Contact> listContactsOrderByClubAsc();
	
	public List<Contact> listContactsOrderByClubDesc();	
}