package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IPersonDAO;
import pt.link.tutorial.cm.domain.Person;
import pt.link.tutorial.cm.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private IPersonDAO personDAO;
	
	@Transactional
	public void addOrUpdatePerson(Person person) {
		personDAO.addOrUpdatePerson(person);
	}

	@Transactional
	public List<Person> listPersons() {
		return personDAO.listPersons();
	}

	@Transactional
	public void removePerson(Integer id) {
		personDAO.removePerson(id);
	}

	@Transactional
	public Person getPerson(Integer id) {
		return personDAO.getPerson(id);
	}

}
