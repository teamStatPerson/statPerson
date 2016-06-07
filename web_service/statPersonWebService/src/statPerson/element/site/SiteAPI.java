package statPerson.element.site;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/SiteAPI")
public interface SiteAPI {
	
	//@POST
	//@Path("/{administratorId}/{URLname}/{startDateStatistics}")
	Integer addSite(@PathParam("administratorId") Integer administratorId,@PathParam("URLname") String URLname,@PathParam("startDateStatistics") Calendar startDateStatistics);
	
	//@GET
	//@Path("/{administratorId}")
	//@Produces(MediaType.APPLICATION_XML)
	List<Site> getSites(@PathParam("administratorId")Integer administratorId);

	//@DELETE
	//@Path("/{administratorId}/{siteId}")
	void removeSite(Integer administratorId, Integer siteId);
}
