package statPerson.rest_demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/restDemo")
public class RestDemo {
	
	public static final String testTest = "Rest Demo";

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String restDemo(){
        return testTest;
    }
}
