package test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import elements.Administrator;
import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.AdministratorAPI_Dao;
import statPerson.Utils;

public class AdministratorAPI_Service_Test {
/*
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/statPerson/rest/AdministratorAPI/administrators";
	private String REST_SERVICE_URL_START = "http://localhost:8080";

	private Administrator administratorInput;
	private Administrator administratorOutput;
	AdministratorAPI_Dao administratorDAO;

	@Before
	public void createTestAdministator() {
		client = ClientBuilder.newClient();
		administratorDAO = new AdministratorAPI_Dao();
		administratorInput = new Administrator("test@test.ru", "test_password", Utils.getCurrentTime(), false);
	}

	private Form getForm(String email, String password) {
		Form form = new Form();
		form.param("email", email);
		form.param("password", password);
		return form;
	}

	@Test
	public void testDAO() {
		// try {
		Form form = getForm(administratorInput.getEmail(), administratorInput.getPassword());
		Administrator call = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML)
				.get(Administrator.class);
		Assert.assertNull(call);
		// } catch (AdministratorNotExist e1) {
		try {
			administratorOutput = administratorDAO.addPrimaryAdministrator(administratorInput.getEmail(),
					administratorInput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}

		Assert.assertTrue(administratorInput.getEmail().equals(administratorOutput.getEmail()));
		Assert.assertTrue(administratorInput.getPassword().equals(administratorOutput.getPassword()));
		// } catch (AdministratorManyAccounts e1) {
		// e1.printStackTrace();
		// } catch (NotCorrectInputData e1) {
		// e1.printStackTrace();
		// }
	}

	@Test
	public void testDAOfunctions() {
		String str = client.target("http://localhost:8080/statPerson/rest/AdministratorAPI/").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		System.out.println(str);
		Assert.assertNotNull(str);
	}

	@Test
	public void testDAO2() {
        
        Client c = ClientBuilder.newClient();
		WebTarget target = c.target(UriBuilder.fromUri("http://localhost").port(8080).build());
		
        String responseMsg = target.path("statPerson").path("rest").path("hello").request().get(String.class);
        
        System.out.println("I am here");
        assertEquals("Got it!", responseMsg);
	}
	
	@After
	public void removeTestAdministrator() {
		try {
			Assert.assertNotNull(administratorDAO);
			administratorDAO.removeAdministrator(administratorOutput.getEmail(), administratorOutput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
	}
*/
}
