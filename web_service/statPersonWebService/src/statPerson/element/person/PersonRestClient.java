package statPerson.element.person;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PersonRestClient implements PersonAPI {
	private static final Client client = ClientBuilder.newClient();
	private static final String REST_SERVICE_URL = "http://localhost:8080/statPerson/rest/PersonAPI/";

	@Override
	public Integer addPerson(Integer administratorId, String personName) {
		Form form = new Form();
		form.param("administratorId", administratorId.toString());
		form.param("personName", personName);

		String callResult = client.target(REST_SERVICE_URL)
				.request(MediaType.TEXT_PLAIN)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		return new Integer(3232);
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
		String callResult = client.target(REST_SERVICE_URL).path("/{administratorId}")
				.resolveTemplate("administratorId", administratorId).path("/{personId}")
				.resolveTemplate("personId", personId).request(MediaType.APPLICATION_XML).delete(String.class);
	}

}
