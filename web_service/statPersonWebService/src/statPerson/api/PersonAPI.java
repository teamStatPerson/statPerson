package statPerson.api;

import java.util.List;

import statPerson.element.account.Account;
import statPerson.element.person.Person;

public interface PersonAPI {
	Integer addPerson(Account administrator, Person person);
	List<Person> getPersons(Account account);
	List<Person> getPersons(String email, String password);
	void removePerson(Integer IDadministrator, Integer IDperson);
}
