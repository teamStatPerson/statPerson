package statPerson.rest.administrator_price;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.element.administrator_price.AdministratorPrice;
import statPerson.element.administrator_price.AdministratorPriceDao;

@Path("/administratorPrice")
public class RestAdministratorPrice {

	@GET
	@Path("/{idAdministrator}")
	@Produces(MediaType.APPLICATION_XML)
	public List<AdministratorPrice> getAdministratorPrices(@PathParam("idAdministrator") int idAdministrator) {
		return AdministratorPriceDao.getAllPricesAdministrator((Integer)idAdministrator);
	}
}
