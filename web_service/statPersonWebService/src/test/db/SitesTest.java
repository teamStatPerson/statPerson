package test.db;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import statPerson.elements.Sites;
import statPerson.elements.dao.SitesDao;

public class SitesTest {

	private Sites site;
	private Integer idSite;

	@Before
	public void before() {
	}

	@Test
	public void test() {
		site = new Sites("test site.ru", Calendar.getInstance());
		idSite = SitesDao.addSite(site);
		Sites out = SitesDao.getSite(idSite);
		Assert.assertNotNull(site);
		Assert.assertNotNull(out);
		Assert.assertEquals(site.getName(),out.getName());
		System.out.println("SitesTest... success");
	}
	

	@After
	public void after() {
		SitesDao.removeSite(idSite);
	}

}
