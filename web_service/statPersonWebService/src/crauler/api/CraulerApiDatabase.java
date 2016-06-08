package crauler.api;

import java.util.List;

import crauler.api.interfaces.iCraulerAPI;
import statPerson.element.keyword.Keyword;
import statPerson.element.keyword.KeywordDao;
import statPerson.element.page.Page;
import statPerson.element.page.PageDao;
import statPerson.element.person.Person;
import statPerson.element.person_page_rank.PersonPageRankDao;
import statPerson.element.site.Site;

public class CraulerApiDatabase implements iCraulerAPI {

	@Override
	public void addPage(Site site, Page page) {
		PageDao.addPage(page.getUrl(), site.getId(), page.getFoundDateTime(), page.getLastScanDate(), page.getHtml());
	}

	@Override
	public void addRank(Person person, Page page, int rank) {
		PersonPageRankDao.addPersonPageRank(person.getId(), page.getId(), rank);
	}

	@Override
	public List<Keyword> getKeywords(Person person) {
		return KeywordDao.getKeywordOfPerson(person.getId());
	}

}
