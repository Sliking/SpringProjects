package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.Person;

public interface IPersonService {
	
	public void addOrUpdatePerson(Person person);

	public List<Person> listPersons();

	public void removePerson(Integer id);

	public Person getPerson(Integer id);
}
