package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.Person;

public interface IPersonDAO {
	public void addOrUpdatePerson(Person person);

	public List<Person> listPersons();

	public void removePerson(Integer id);

	public Person getPerson(Integer id);
}
