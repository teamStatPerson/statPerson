package statPerson.rest_demo;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class TestRestDemo {
	
	@Test
	public void test1(){
        Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri("http://localhost").port(8080).build());
		Builder d  = target.path("statPerson").path("rest").path("restDemo").request();
        String responseMsg = d.get(String.class);
        
        assertEquals(RestDemo.testTest, responseMsg);
	}

	@Test
	public void test2(){
		Client client = ClientBuilder.newClient();
		Response responce = client.target("http://localhost:8080/statPerson/rest/restDemo").request(MediaType.TEXT_HTML).get();

		assertTrue(responce.getLength() > 0);
		assertTrue(responce.getStatus() == 200);
	}
}
