package statPerson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import elements.Administrator;
import exceptions.AdministratorManyAccounts;
import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;

@Path("/AdministratorAPI")
public class AdministratorAPI_Service {

	AdministratorAPI_Dao administratorDAO = new AdministratorAPI_Dao();

	@GET
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Administrator getAdministrator(@FormParam("email") String email, @FormParam("password") String password)
			throws AdministratorNotExist, AdministratorManyAccounts, NotCorrectInputData {
		return administratorDAO.getAdministrator(email, password);
	}

	@PUT
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Administrator addPrimaryAdministrator(@FormParam("email") String email,
			@FormParam("password") String password, @Context HttpServletResponse servletResponse)
			throws AdministratorManyAccounts, NotCorrectInputData {
		return administratorDAO.addPrimaryAdministrator(email, password);
	}

	@DELETE
	@Path("/administrators")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void removeAdministrator(@FormParam("email") String email, @FormParam("password") String password)
			throws NotCorrectInputData {
		administratorDAO.removeAdministrator(email, password);
	}
}
