package statPerson.elements.rest_resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.elements.Users;

@Path("/users")
public interface UserResource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Users> getUsers();

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	public Users getUser(@PathParam("userId") int userId);
	
}
