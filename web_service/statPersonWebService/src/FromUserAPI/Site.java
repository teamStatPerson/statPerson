package ru.geekbrains.userapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Site {
	private int id;
	private String name;

	public Site() {
	}

	public Site(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
