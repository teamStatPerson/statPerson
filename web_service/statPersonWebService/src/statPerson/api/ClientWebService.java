package statPerson.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import exceptions.NotCorrectInputData;
import statPerson.MapToXml;
import statPerson.element.account.Account;
import statPerson.element.account.AccountDao;
import statPerson.element.administrator_person.AdministratorPerson;
import statPerson.element.administrator_person.AdministratorPersonDao;
import statPerson.element.administrator_price.AdministratorPrice;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.person.PersonDao;
import statPerson.element.price.Price;
import statPerson.element.site.Site;

public class ClientWebService implements interfaceAPI{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //даты принимаем как строки в виде гггг-мм-дд, надо их преобразовывать в Date 

	@Path("/admin/add")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public Integer addPrimaryAdministrator(@HeaderParam("email") String email, @HeaderParam("password") String password) {
		int id = 0;
		try {
			id = AccountDao.addPrimaryAdministrator(email, password);
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Path("/admin/addlinked")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public Integer addSecondaryAdministrator(@HeaderParam("email") String email, @HeaderParam("password") String password, @HeaderParam("idlinked") int idLinkedAdministrator) {
		int id = 0;
		try {
			id = AccountDao.addSecondaryAdministrator(email, password, idLinkedAdministrator);
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		return id;
	}

	@Path("/user/add")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public Integer addUser(@HeaderParam("email") String email, @HeaderParam("password") String password, @HeaderParam("idlinked") int idLinkedAdministrator) {
		int id = 0;
		try {
			id = AccountDao.addUser(email, password, idLinkedAdministrator);
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void changePassword(Account account, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getAccount(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccount(Integer idAccount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Keyword> getKeywords(Account administrator, Integer IDperson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addKeyword(Account administrator, String keywordName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeKeyword(Account administrator, Integer IDkeyword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer addPerson(Account administrator, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersons(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersons(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePerson(Integer IDadministrator, Integer IDperson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Price> getPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPriceToPrimaryAdministrator(Integer idAccount, Integer idPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AdministratorPrice> getAllPricesAdministrator(Integer idAdministrator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addSite(Integer administratorId, String URLname, Calendar startDateStatistics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> getSites(Integer administratorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSite(Integer administratorId, Integer siteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int amountPagesFromSite(Site site) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int amountPagesInDBbutNeverScan(Site site) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int amountPagesInDBscanned(Site site) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Date, Integer> getRankByPerDayByPerson(Person person, Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Path("/user/add")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public String getNewPagesCount(@HeaderParam("account") Integer accountId, @HeaderParam("beginDate") String beginDateString, @HeaderParam("endDate") String endDateString) {
		Map<Person, Integer> PersonsRank = new HashMap<Person, Integer>();
		try {
			Date beginDate = dateFormat.parse(beginDateString);
			Date endDate = dateFormat.parse(endDateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		List<AdministratorPerson> administratorsPersons = AdministratorPersonDao.getAllPersonAccount(accountId);
		for (AdministratorPerson administratorPerson : administratorsPersons) {
			int personId = administratorPerson.getIdPerson();
			//тут надо по персон ид получить статистику
		}
		
		return MapToXml.PersonsRankToXML(PersonsRank);
	}

	@Override
	public Map<Person, Page> getStatisticPersonNewPages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Site, Integer> getStatisticSiteAmountPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
