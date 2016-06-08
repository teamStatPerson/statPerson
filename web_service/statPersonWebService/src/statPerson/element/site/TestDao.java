package statPerson.element.site;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

public class TestDao {

	@Test
	public void test() {
		Site in = new Site("www.test.ru", Calendar.getInstance());
		Integer id = SiteDao.addSite(in.getName(), in.getStartDateStatistics());
		assertNotNull(id);
		Site out = SiteDao.getSite(id);
		assertEquals(in.getName(), out.getName());
		SiteDao.removeSite(id);
	}
}
