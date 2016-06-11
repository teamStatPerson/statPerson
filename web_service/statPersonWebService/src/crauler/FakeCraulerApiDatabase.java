package crauler;

import java.util.ArrayList;
import java.util.List;

import crauler.api.interfaces.iCraulerAPI;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class FakeCraulerApiDatabase implements iCraulerAPI {

	private static int idPage = 0;
	
	@Override
	public List<String> getKeywords(Person person) {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("Путин");
		keywords.add("Путина");
		keywords.add("Путином");
		keywords.add("Путинской");
		keywords.add("Путиным");
		keywords.add("Владимир Владимирович");
		keywords.add("Президент России");
		keywords.add("Президентом России");
		keywords.add("Президент Российской Федерации");
		keywords.add("Президентом Российской Федерации");
		return keywords;
	}

	@Override
	public Page addPage(Site site, String page, String html) {
		Page pageDB = new Page();
		pageDB.setId(idPage++);
		pageDB.setUrl(page);
		System.out.println("DB.addPage["+site.getName()+","+page+"("+pageDB.getId()+")"+"]");
		return pageDB;
	}

	@Override
	public void addRank(Person person, Integer pageId, String keyword, int rank) {
		System.out.println("**DB.addRank["+person.getName()+","+pageId+","+keyword+","+rank+"]");
	}
}
