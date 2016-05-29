import java.util.*;

public class UserAPIWithFakeData{

    public Map<Site, Integer> GetPersonStatistics(int PersonID) {
        Map<Site, Integer> statistics = new HashMap<Site, Integer>();

        statistics.put(new Site(1, "www.lenta.ru"), 3);
        statistics.put(new Site(2, "www.rbc.ru"), 6);

        return statistics;
    }

    public Map<Person, Integer> GetSiteStatistics(int PersonID) {
        Map<Person, Integer> statistics = new HashMap<Person, Integer>();

        statistics.put(new Person(1, "Путин"), 7);
        statistics.put(new Person(2, "Медведев"), 2);

        return statistics;
    }

}