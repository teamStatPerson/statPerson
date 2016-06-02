package test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import elements.Administrator;
import exceptions.AdministratorManyAccounts;
import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.AdministratorAPI_Dao;
import statPerson.Utils;

public class AdministratorAPI_Service_Test {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/UserManagement/rest/UserService/users";

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
		//try {
			Form form = getForm(administratorInput.getEmail(), administratorInput.getPassword());
			Administrator call = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML)
					.get(Administrator.class);
			Assert.assertNull(call);
		//} catch (AdministratorNotExist e1) {
			try {
				administratorOutput = administratorDAO.addPrimaryAdministrator(administratorInput.getEmail(),
						administratorInput.getPassword());
			} catch (AdministratorManyAccounts e) {
				e.printStackTrace();
			} catch (NotCorrectInputData e) {
				e.printStackTrace();
			}

			Assert.assertTrue(administratorInput.getEmail().equals(administratorOutput.getEmail()));
			Assert.assertTrue(administratorInput.getPassword().equals(administratorOutput.getPassword()));
		//} catch (AdministratorManyAccounts e1) {
		//	e1.printStackTrace();
		//} catch (NotCorrectInputData e1) {
		//	e1.printStackTrace();
		//}
	}

	@After
	public void removeTestAdministrator() {
		try {
			administratorDAO.removeAdministrator(administratorOutput.getEmail(), administratorOutput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
	}

}
