package crauler.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import crauler.api.interfaces.iCraulerAPI;
import statPerson.element.keyword.Keyword;
import statPerson.element.keyword.KeywordDao;
import statPerson.element.page.Page;
import statPerson.element.page.PageDao;
import statPerson.element.person.Person;
import statPerson.element.person.PersonDao;
import statPerson.element.person_page_rank.PersonPageRankDao;
import statPerson.element.site.Site;

public class CraulerApiDatabase implements iCraulerAPI {

	@Override
	public Page addPage(Site site, String page, String html) {
		Page pageDB = PageDao.getByPage(page);
		if (pageDB == null) {
			pageDB = PageDao.addPage(page, site.getId(), Calendar.getInstance(), Calendar.getInstance(), html);
		} else {
			pageDB = PageDao.updatePage(pageDB.getId(), html);
		}
		return pageDB;
	}

	@Override
	public void addRank(Person person, Integer pageId, String keyword, int rank) {
		PersonPageRankDao.addPersonPageRank(person.getId(), pageId, rank);
	}

	@Override
	public List<String> getKeywords(Person person) {
		List<Keyword> keywords = KeywordDao.getKeywordOfPerson(person.getId());
		List<String> strings = new ArrayList<String>();
		strings.add(PersonDao.getPerson(person.getId()).getName());
		for (Keyword keyword : keywords)
			strings.add(keyword.getName());
		return strings;
	}
}
