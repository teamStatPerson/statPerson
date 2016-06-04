package ru.geekbrains.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.geekbrains.userapi.database.DatebaseClass;
import ru.geekbrains.userapi.model.Site;

public class SiteService {
	private static Map<Integer, Site> sites = DatebaseClass.getSites();

	public SiteService(){
		sites.put(1, new Site(1, "lenta.ru"));
		sites.put(2, new Site(2, "rbc.ru"));
		sites.put(3, new Site(3, "ria.ru"));
	}
	
	public List<Site> getAllSites() {
		return new ArrayList<Site> (sites.values());
	}
	
	public Site getSite(int id) {
		return sites.get(id);
	}
	
	public Site addSite(Site site) {
		site.setId(sites.size() + 1);
		sites.put(site.getId(), site);
		return site;
	}

	public Site updatesite(Site site) {
		if (site.getId() <= 0) {
			return null;
		}
		sites.put(site.getId(), site);
		return site;
	}
	
	public Site removeSite(int id) {
		return sites.remove(id);
	}
}
