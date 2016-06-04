package ru.geekbrains.userapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ru.geekbrains.userapi.model.Keyword;
import ru.geekbrains.userapi.model.Person;

public class KeywordService {
	private Map<Person, ArrayList<Keyword>> personsKeywors = new HashMap<>();

	public KeywordService() {
		PersonService personService = PersonService.getInstance();
		List<Person> persons = personService.getAllPersons();

		for (Person person : persons) {
			ArrayList<Keyword> keywords = new ArrayList<>();
			keywords.add(new Keyword(1, person.getName(), person));
			keywords.add(new Keyword(2, person.getName()+"ым", person));
			keywords.add(new Keyword(3, person.getName()+"у", person));
			personsKeywors.put(person, keywords);
		}
	}
	
	public List<Keyword> getPersonKeywors(Person person) {
		return personsKeywors.get(person);
	}

}
