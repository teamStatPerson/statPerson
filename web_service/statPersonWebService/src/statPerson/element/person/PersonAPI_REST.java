package statPerson.element.person;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.element.administrator_person.AdministratorPerson;
import statPerson.element.administrator_person.AdministratorPersonDao;

@Path("/PersonAPI")
public class PersonAPI_REST implements PersonAPI {

	@Override
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Integer addPerson(@FormParam("administratorId") Integer administratorId,
			@FormParam("personName") String personName) {
		Integer idPerson = PersonDao.addPerson(personName);
		AdministratorPersonDao.addPersonToAdministrator(administratorId, idPerson);
		return idPerson;
	}

	@Override
	@GET
	@Path("/{administratorId}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getPersons(@PathParam("administratorId") Integer administratorId) {
		List<AdministratorPerson> administratorPersons = AdministratorPersonDao.getAllPersonAccount(administratorId);
		List<Person> persons = new ArrayList<Person>();
		for(AdministratorPerson that: administratorPersons){
			persons.add(PersonDao.getPerson(that.getIdPerson()));
		}
		return persons;
	}

	@Override
	@DELETE
	@Path("/{administratorId}/{personId}")
	public void removePerson(@PathParam("administratorId") Integer administratorId,
			@PathParam("personId") Integer personId) {
		AdministratorPersonDao.removePersonFromAdministrator(administratorId, personId);
		PersonDao.removePerson(personId);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getAllPersons() {
		return PersonDao.getAllPerson();
	}

}
