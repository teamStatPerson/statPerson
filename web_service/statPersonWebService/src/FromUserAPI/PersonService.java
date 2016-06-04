package ru.geekbrains.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.geekbrains.userapi.database.DatebaseClass;
import ru.geekbrains.userapi.model.Person;

public class PersonService {
	public static PersonService instance;
	private static Map<Integer, Person> persons = DatebaseClass.getPersons();

	public PersonService() {
		persons.put(1, new Person(1, "Путин"));
		persons.put(2, new Person(2, "Медведев"));
		persons.put(3, new Person(3, "Навальный"));
	}
	
	public static PersonService getInstance(){
		if (instance == null) {
			instance = new PersonService();
		}
		return instance;	
	}
	
	public List<Person> getAllPersons() {
		return new ArrayList<Person> (persons.values());
	}

	public Person getPerson(int id) {
		return persons.get(id);
	}
	
	public Person addPerson(Person person) {
		person.setId(persons.size() + 1);
		persons.put(person.getId(), person);
		return person;
	}
	
	public Person updatePerson(Person person) {
		if (person.getId() <= 0) {
			return null;
		}
		persons.put(person.getId(), person);
		return person;
	}
	
	public Person removePerson(int id) {
		return persons.remove(id);
	}

}
