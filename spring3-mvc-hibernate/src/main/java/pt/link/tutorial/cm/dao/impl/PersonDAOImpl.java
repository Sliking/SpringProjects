package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IPersonDAO;
import pt.link.tutorial.cm.domain.Person;

@Repository
public class PersonDAOImpl implements IPersonDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdatePerson(Person person) {
		System.out.println(person.getId());
		sessionFactory.getCurrentSession().saveOrUpdate(person);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Person> listPersons() {
		return sessionFactory.getCurrentSession().createQuery("from Person").list();
	}

	@Override
	@Transactional
	public void removePerson(Integer id) {
		Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, id);
		
		if (person != null) {
			sessionFactory.getCurrentSession().delete(person);
		}	
	}

	@Override
	@Transactional
	public Person getPerson(Integer id) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
	}
}
