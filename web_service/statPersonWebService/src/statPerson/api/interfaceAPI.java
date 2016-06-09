package statPerson.api;

import java.util.Date;
import java.util.Map;

import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.person.PersonAPI;
import statPerson.element.site.Site;
import statPerson.element.site.SiteAPI;

public interface interfaceAPI extends AccountAPI,KeywordAPI,PersonAPI,PriceAPI,SiteAPI{
	/**********************************************/
	
	// ADMINISTRATOR STATISTIC
	// par.5.1.1
	int amountPagesFromSite(Site site);
	
	// par.5.1.2
	int amountPagesInDBbutNeverScan(Site site);
	
	// par.5.1.3
	int amountPagesInDBscanned(Site site);
	
	/**********************************************/
	
	// USER STATISTIC
	// par.6.1.1 ДТЗ
	// par.2.1.1 Функциональные требования
	Map<Date, Integer> getRankByPerDayByPerson(Person person, Date beginDate, Date endDate);
	
	// par.6.1.2
	Map<Person, Page> getStatisticPersonPage();
	
	// par.6.1.3
	Map<Person, Page> getStatisticPersonNewPages();
	
	// par.6.2.2
	Map<Site, Integer> getStatisticSiteAmountPage();
	
	/**********************************************/
}
