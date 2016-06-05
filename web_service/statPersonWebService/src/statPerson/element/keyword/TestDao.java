package statPerson.element.keyword;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestDao {

	@Test
	public void test() {
		Keyword in = new Keyword("nnnnnn", 0);
		Integer id = KeywordDao.addKeyword(in.getName(), in.getPersonId());
		assertNotNull(id);
		Keyword out = KeywordDao.getKeywordOfPerson(in.getPersonId()).get(0);
		assertEquals(in.getName(), out.getName());
		KeywordDao.removeKeywordFromPerson(id);
	}
}
