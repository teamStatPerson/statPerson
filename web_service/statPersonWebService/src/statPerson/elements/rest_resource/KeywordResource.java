package statPerson.elements.rest_resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.elements.Keywords;


@Path("/keywords")
public interface KeywordResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Keywords> getPersonKeywords(@PathParam("PersonId") int personId);

}
