package ru.geekbrains.userapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.geekbrains.userapi.model.Keyword;
import ru.geekbrains.userapi.service.KeywordService;
import ru.geekbrains.userapi.service.PersonService;

@Path("/keywords")
public class KeywordResource {
	KeywordService keywordService = new KeywordService();
	PersonService personService = new PersonService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Keyword> getPersonKeywords(@PathParam("PersonId") int personId) {
		personService = PersonService.getInstance();

		return keywordService.getPersonKeywors(personService.getPerson(personId));
	}

}
