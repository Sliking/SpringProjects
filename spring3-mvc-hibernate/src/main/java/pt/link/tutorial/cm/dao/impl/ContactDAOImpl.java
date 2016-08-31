package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IContactDAO;
import pt.link.tutorial.cm.domain.Contact;

@Repository
public class ContactDAOImpl implements IContactDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addOrUpdateContact(Contact contact) {
		System.out.println(contact.getId());
		
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContacts() {
		return sessionFactory.getCurrentSession().createQuery("from Contact").list();
	}

	@Override
	@Transactional
	public void removeContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
		
		if (contact != null) {
			sessionFactory.getCurrentSession().delete(contact);
		}
	}
	
	@Override
	@Transactional
	public Contact getContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
		
		return contact;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByFirstNameAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by firstname asc").list();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByFirstNameDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by firstname desc").list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByLastNameAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by lastname asc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByLastNameDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by lastname desc").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderBirthDayAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by birthday asc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderBirthDayDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by birthday desc").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByEmailAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by email asc").list();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByEmailDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by email desc").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByTelephoneAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by telephone asc").list();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByTelephoneDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by telephone desc").list();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByAddressAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by address asc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByAddressDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by address desc").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByZipCodeAsc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by zipcode asc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByZipCodeDesc() {
		return sessionFactory.getCurrentSession().createQuery("from Contact order by zipcode desc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByClubAsc(){
		return sessionFactory.getCurrentSession().createQuery("from Contact order by club asc").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contact> listContactsOrderByClubDesc(){
		return sessionFactory.getCurrentSession().createQuery("from Contact order by club desc").list();
	}
	
}