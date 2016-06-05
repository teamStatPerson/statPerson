package statPerson.crauler.elements;

import java.util.Calendar;

import org.junit.Test;

import statPerson.crauler.elements.main.java.RepositorySP;
import statPerson.crauler.elements.main.java.entity.Pages;

public class ClauserTest {

	@Test
	public void test(){
	    Pages testPage = new Pages();
	    testPage.setSiteId(1);
	    testPage.setUrl("http://lenta.ru");
	    testPage.setFoundDateTime(Calendar.getInstance());
	    testPage.setHtml("http://lenta.ru");
	
	    RepositorySP<Pages> sp = new RepositorySP<Pages>();
	    Integer id = sp.addEntity(testPage);
	    
	    sp.removeEntityById(id);
	}
}
