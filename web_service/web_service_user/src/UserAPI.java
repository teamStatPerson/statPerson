import java.sql.SQLException;
import java.util.*;

public class UserAPI{
	DBConnector dbConnector;

	public Map<Site, Integer> GetPersonStatistics(int PersonID) throws SQLException {
		dbConnector = DBConnector.getInstance();
		dbConnector.MakeQuery("Select ID From Pages Where SiteID=" + String.valueOf(PersonID));

		Map<Site, Integer> statistics = new HashMap<Site, Integer>();
		return statistics;
	}

	public Map<Person, Integer> GetSiteStatistics(int PersonID) {
		Map<Person, Integer> statistics = new HashMap<Person, Integer>();
		return statistics;
	}

}