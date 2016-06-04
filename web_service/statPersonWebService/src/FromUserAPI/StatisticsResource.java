package ru.geekbrains.userapi.resources;

import java.util.Date;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ru.geekbrains.userapi.model.Person;
import ru.geekbrains.userapi.model.Site;
import ru.geekbrains.userapi.service.MapToXML;
import ru.geekbrains.userapi.service.PersonService;
import ru.geekbrains.userapi.service.StatisticsService;

@Path("/statistics")
public class StatisticsResource {
	StatisticsService statistiscsService = new StatisticsService();
	PersonService personService = PersonService.getInstance();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "statistika";
	}
	
	@GET
	@Path("/person/{personId}")
	@Produces(MediaType.APPLICATION_XML)
	public String getPersonStatistics(@PathParam("personId") int personId, @QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate) {
		Person person = personService.getPerson(personId);

		if (!(beginDate == null) & !(endDate == null)) {
			Map<Date, Map<Site, Integer>> stat = statistiscsService.getPersonStatisticsInPeriod(person, beginDate, endDate);
			return MapToXML.StatisticsInPeriodToXML(stat);
		} else {
			Map<Site, Integer> stat = statistiscsService.getPersonStatistics(person); 
			return MapToXML.StatisticsToXML(stat);
		}
	}

}
