package statPerson.element.person;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class PersonAPI_REST_Client implements PersonAPI {
	private static final Client client = ClientBuilder.newClient();
	private static final String REST_SERVICE_URL = "http://146.66.177.105:8080/statPerson/rest/PersonAPI/";

	@Override
	public Integer addPerson(Integer administratorId, String personName) {
		Form form = new Form();
		form.param("administratorId", administratorId.toString());
		form.param("personName", personName);
		Integer callResult = client.target(REST_SERVICE_URL).request(MediaType.TEXT_PLAIN)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Integer.class);
		return callResult;
	}

	@Override
	public List<Person> getPersons(Integer administratorId) {
		GenericType<List<Person>> list = new GenericType<List<Person>>() {
		};
		String url = REST_SERVICE_URL + administratorId;
		List<Person> persons = client.target(url).request(MediaType.APPLICATION_XML).get(list);
		return persons;
	}

	@Override
	public void removePerson(Integer administratorId, Integer personId) {
		client.target(REST_SERVICE_URL).path("/{administratorId}")
				.resolveTemplate("administratorId", administratorId).path("/{personId}")
				.resolveTemplate("personId", personId).request(MediaType.APPLICATION_XML).delete(String.class);
	}

	@Override
	public List<Person> getAllPersons() {
		GenericType<List<Person>> list = new GenericType<List<Person>>() {
		};
		return client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).get(list);
	}
}
