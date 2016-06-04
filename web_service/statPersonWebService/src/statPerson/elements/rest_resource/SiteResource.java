package ru.geekbrains.userapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.geekbrains.userapi.model.Site;
import ru.geekbrains.userapi.service.SiteService;

@Path("/sites")
public class SiteResource {
	SiteService siteService = new SiteService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Site> getSites() {
		return siteService.getAllSites();
	}

	@GET
	@Path("/{siteId}")
	@Produces(MediaType.APPLICATION_XML)
	public Site getSite(@PathParam("siteId") int siteId) {
		return siteService.getSite(siteId);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Site addSite(@FormParam("name") String name) {
		Site site = new Site();
		site.setName(name);
		site.setId(siteService.getAllSites().size() + 1);

		siteService.addSite(site);
		
		return site;
	}
}
