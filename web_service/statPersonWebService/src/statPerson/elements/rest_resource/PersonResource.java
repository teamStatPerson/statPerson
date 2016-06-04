package ru.geekbrains.userapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.geekbrains.userapi.model.Person;
import ru.geekbrains.userapi.service.PersonService;

@Path("/persons")
public class PersonResource {
	PersonService personService = PersonService.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getPersons() {
		return personService.getAllPersons();
	}
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPerson(@PathParam("personId") int personId ) {
		return personService.getPerson(personId);		
	}
	
}
