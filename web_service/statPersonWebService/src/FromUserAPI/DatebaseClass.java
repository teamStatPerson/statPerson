package ru.geekbrains.userapi.database;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrains.userapi.model.Person;
import ru.geekbrains.userapi.model.Site;
import ru.geekbrains.userapi.model.User;

public class DatebaseClass {

	private static Map<Integer, User> users = new HashMap<>();
	private static Map<Integer, Site> sites = new HashMap<>();
	private static Map<Integer, Person> persons = new HashMap<>();
	
	public static Map<Integer, User> getUsers() {
		return users;
	}
	
	public static Map<Integer, Site> getSites() {
		return sites;
	}

	public static Map<Integer, Person> getPersons() {
		return persons;
	}
	
}
