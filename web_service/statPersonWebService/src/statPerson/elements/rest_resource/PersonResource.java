package statPerson.elements.rest_resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.elements.Persons;

@Path("/persons")
public interface PersonResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Persons> getPersons();
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_XML)
	public Persons getPerson(@PathParam("personId") int personId );
	
}
