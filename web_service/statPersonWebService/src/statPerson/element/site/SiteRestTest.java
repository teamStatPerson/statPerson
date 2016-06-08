package statPerson.element.site;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.element.account.AccountDao;
import statPerson.element.administrator_site.AdministratorSiteDao;

public class SiteRestTest implements SiteAPI{

	@Override
	public Integer addSite(Integer administratorId, String URLname, Calendar startDateStatistics) {
		Integer idSite = SiteDao.addSite(URLname, startDateStatistics);
		AdministratorSiteDao.addAdministratorSite(administratorId, idSite);
		return idSite;
	}

	@Override
	public List<Site> getSites(Integer administratorId) {
		return AdministratorSiteDao.getAllSitesAdministrator(administratorId);
	}

	@Override
	public void removeSite(Integer administratorId, Integer siteId) {
		AdministratorSiteDao.removeSiteFromAdministrator(administratorId, siteId);
	}

	@GET
	@Path("/test/{string}")
	String getSites(@PathParam("string")String string){
		return string;
	}
	
	@Test
	public void test(){
		Integer accountId = null;
		try {
			accountId = AccountDao.addPrimaryAdministrator("PUPKIN3", "password2");
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		SiteRestTest test = new SiteRestTest();
		Integer id = test.addSite(accountId, "LENTA.RU", Calendar.getInstance());
		Assert.assertNotNull(id);
		for(int i=0;i<test.getSites(accountId).size();i++)
			System.out.println(test.getSites(accountId).get(i).getName());
		Assert.assertEquals(test.getSites(accountId).size(), 1);
		test.removeSite(accountId, id);
		AccountDao.removeAccount(accountId);
		Assert.assertNull(AccountDao.getAccountById(accountId));
	}

}
