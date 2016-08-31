package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IContactDAO;
import pt.link.tutorial.cm.domain.Contact;
import pt.link.tutorial.cm.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {
		
	@Autowired
	private IContactDAO contactDAO;

	@Transactional
	@Override
	public void addOrUpdateContact(Contact contact) {
		contactDAO.addOrUpdateContact(contact);
	}
	
	@Transactional
	public List<Contact> listContacts() {
		return contactDAO.listContacts();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);
	}

	@Transactional
	public Contact getContact(Integer id) {
		return contactDAO.getContact(id);
	}

	/**
	 * @return the contactDAO
	 */
	public IContactDAO getContactDAO() {
		return contactDAO;
	}

	/**
	 * @param contactDAO the contactDAO to set
	 */
	public void setContactDAO(IContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByFirstNameAsc() {
		return contactDAO.listContactsOrderByFirstNameAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByFirstNameDesc() {
		return contactDAO.listContactsOrderByFirstNameDesc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByLastNameAsc() {
		return contactDAO.listContactsOrderByLastNameAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByLastNameDesc() {
		return contactDAO.listContactsOrderByLastNameDesc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderBirthDayAsc() {
		return contactDAO.listContactsOrderBirthDayAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderBirthDayDesc() {
		return contactDAO.listContactsOrderBirthDayDesc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByEmailAsc() {
		return contactDAO.listContactsOrderByEmailAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByEmailDesc() {
		return contactDAO.listContactsOrderByEmailDesc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByTelephoneAsc() {
		return contactDAO.listContactsOrderByTelephoneAsc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByTelephoneDesc() {
		return contactDAO.listContactsOrderByTelephoneDesc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByAddressAsc() {
		return contactDAO.listContactsOrderByAddressAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByAddressDesc() {
		return contactDAO.listContactsOrderByAddressDesc();
	}

	@Override
	@Transactional
	public List<Contact> listContactsOrderByZipCodeAsc() {
		return contactDAO.listContactsOrderByZipCodeAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByZipCodeDesc() {
		return contactDAO.listContactsOrderByZipCodeDesc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByClubAsc() {
		return contactDAO.listContactsOrderByClubAsc();
	}
	
	@Override
	@Transactional
	public List<Contact> listContactsOrderByClubDesc() {
		return contactDAO.listContactsOrderByClubDesc();
	}
}