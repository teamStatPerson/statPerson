package statPerson.api;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;

@Path("/administratorAPI")
public class AdministratorRest {

/*
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello(){
        return "<html><title>Hello Jersey</title><body><h1>Hello Jersey</h1></body></html>";
    }

	@GET
	@Path("{email}/{password}")
	public Response getAdministrator(@PathParam("email") String email, @PathParam("password") String password) throws Exception {
		String date = email + "/" + password;
		if(1 > 0)
			throw new Exception("fsd");

		   return Response.status(200)
			.entity("getUserHistory is called, year/month/day : " + date)
			.build();
	}
	
	AdministratorAPI_Dao administratorDAO = new AdministratorAPI_Dao();
	@GET
	//@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Administrator getAdministrator(@FormParam("email") String email, @FormParam("password") String password) {
		try {
			return administratorDAO.getAdministrator(email, password);
		} catch (AdministratorNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCorrectInputData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 
	@PUT
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Administrator addPrimaryAdministrator(@FormParam("email") String email,
			@FormParam("password") String password, @Context HttpServletResponse servletResponse)
			{
		try {
			return administratorDAO.addPrimaryAdministrator(email, password);
		} catch (NotCorrectInputData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@DELETE
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void removeAdministrator(@FormParam("email") String email, @FormParam("password") String password)
			{
		try {
			administratorDAO.removeAdministrator(email, password);
		} catch (NotCorrectInputData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OPTIONS
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, DELETE</operations>";
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "test_message";
	}
	*/
}
