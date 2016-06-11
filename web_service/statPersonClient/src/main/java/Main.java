import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class Main {
    public static final String REST_SERVICE_URL = "http://192.168.0.5:8080/statPerson/rest/PersonAPI";
    public static final String DEMO_SERVICE_URL = "http://localhost:8080/statPerson/rest/restDemo";

    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        // Demo
        String demo = client.target(DEMO_SERVICE_URL).request().get(String.class);
        System.out.println(demo);

        // List of persons
        WebTarget target = client.target(REST_SERVICE_URL);


        Response response = target.request().accept(MediaType.APPLICATION_XML).get();
        String xml = response.readEntity(String.class);

        Persons persons = new Persons();
        JAXBContext context = JAXBContext.newInstance(Persons.class);
        Unmarshaller um = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        persons = (Persons) um.unmarshal(reader);

        for (Person person : persons.getPersons())
            System.out.println(person);
    }
}
