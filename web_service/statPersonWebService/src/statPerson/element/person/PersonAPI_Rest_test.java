package statPerson.element.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/PersonAPI")
public class PersonAPI_Rest_test implements PersonAPI {

	static List<Person> persons = new ArrayList<Person>();
	static {
		persons.add(new Person("Putin"));
		persons.add(new Person("Putinf"));
		persons.add(new Person("Putina"));
	}

	@Override
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Integer addPerson(@FormParam("administratorId") Integer administratorId,
			@FormParam("personName") String personName) {
		persons.add(new Person(personName));
		return 0;//todo
	}

	@Override
	@GET
	@Path("/{administratorId}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getPersons(@PathParam("administratorId") Integer administratorId) {
		return persons;
	}

	@Override
	@DELETE
	@Path("/{administratorId}/{personId}")
	public void removePerson(@PathParam("administratorId") Integer administratorId,
			@PathParam("personId") Integer personId) {
		for (int i = 0; i < persons.size(); i++) {
			if (personId == persons.get(i).getId()) {
				persons.remove(i);
				break;
			}
		}
	}

}
