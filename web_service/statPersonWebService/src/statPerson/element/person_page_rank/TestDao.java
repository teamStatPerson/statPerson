package statPerson.element.person_page_rank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestDao {

	@Test
	public void test() {
		PersonPageRank in = new PersonPageRank(10, 10, 22342342);
		Integer id = PersonPageRankDao.addPersonPageRank(in.getPersonId(), in.getPageId(), in.getRank());
		assertNotNull(id);
		PersonPageRank out = PersonPageRankDao.getPersonPageRankByPage(in.getPageId()).get(0);
		assertEquals(in.getRank(), out.getRank());
		PersonPageRankDao.removePersonPageRank(id);
	}
}
