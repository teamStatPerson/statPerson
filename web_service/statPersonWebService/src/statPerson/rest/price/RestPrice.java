package statPerson.rest.price;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import statPerson.element.price.Price;
import statPerson.element.price.PriceDao;

@Path("/price")
public class RestPrice {
sdfsdf
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Price> getPrices(){
        return PriceDao.getPrices();
    }
}