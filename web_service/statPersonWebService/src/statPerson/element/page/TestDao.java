package statPerson.element.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

public class TestDao {

	@Test
	public void test() {
		Page in = new Page("nnnnnn",0, Calendar.getInstance(), Calendar.getInstance(),"sdsdsdsd");
		Integer id = PageDao.addPage(
				in.getUrl(), 
				in.getSiteId(), 
				in.getFoundDateTime(), 
				in.getLastScanDate(), 
				in.getHtml()
				);
		assertNotNull(id);
		Page out = PageDao.getAllPage(0).get(0);
		assertNotNull(out);
		assertEquals(in.getUrl(), out.getUrl());
		PageDao.removeAllPage(0);
	}
}
