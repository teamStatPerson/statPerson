package statPerson.elements.rest_resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.elements.Sites;


@Path("/sites")
public interface SiteResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Sites> getSites();

	@GET
	@Path("/{siteId}")
	@Produces(MediaType.APPLICATION_XML)
	public Sites getSite(@PathParam("siteId") int siteId);
	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Sites addSite(@FormParam("name") String name);
}
