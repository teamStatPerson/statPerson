package ru.geekbrains.userapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Keyword {
	private int id;
	private String name;
	private Person person;

	public Keyword() {
		
	}
	
	public Keyword(int id, String name, Person person) {
		this.id = id;
		this.name = name;
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
