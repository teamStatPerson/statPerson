package statPerson.elements.rest_resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/statistics")
public interface StatisticsResource {
	
	@GET
	@Path("/person/{personId}")
	@Produces(MediaType.APPLICATION_XML)
	public String getPersonStatistics(@PathParam("personId") int personId, @QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate);

}
