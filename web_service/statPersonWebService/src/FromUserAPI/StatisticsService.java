package ru.geekbrains.userapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ru.geekbrains.userapi.model.Person;
import ru.geekbrains.userapi.model.Site;

public class StatisticsService {
	private static Map<Person, Map<Site, Integer>> personsSiteRank = new HashMap<>();

	public StatisticsService() {
		final Site s1 = new Site(1, "lenta.ru");
		final Site s2 = new Site(2, "rbc.ru");
		final Site s3 = new Site(3, "ria.ru");

		PersonService personService = PersonService.getInstance();
		List<Person> persons = personService.getAllPersons();
		final Random random = new Random();

		for (Person person : persons) {
			HashMap<Site, Integer> map = new HashMap<>();
			map.put(s1, random.nextInt(50));
			map.put(s2, random.nextInt(50));
			map.put(s3, random.nextInt(50));
			personsSiteRank.put(person, map);
		}
	}

	public Map<Person, Map<Site, Integer>> getAllStatistics() {
		return personsSiteRank;
	}

	public Map<Site, Integer> getPersonStatistics(Person person) {
		return personsSiteRank.get(person);
	}

	public Map<Date, Map<Site, Integer>> getPersonStatisticsInPeriod(Person person, String beginDateString,	String endDateString) {
		Date beginDate = DateUtils.DateFromString(beginDateString);
		Date endDate = DateUtils.DateFromString(endDateString);

		final Site s1 = new Site(1, "lenta.ru");
		final Site s2 = new Site(2, "rbc.ru");
		final Site s3 = new Site(3, "ria.ru");
		final Random random = new Random();
	
		long timeAdj = 24*60*60*1000;
		Map<Date, Map<Site, Integer>> stat = new HashMap<>();

		System.out.println(beginDate.toString());
		while (beginDate.before(endDate) || beginDate.equals(endDate)) {
			HashMap<Site, Integer> map = new HashMap<>();
			map.put(s1, random.nextInt(50));
			map.put(s2, random.nextInt(50));
			map.put(s3, random.nextInt(50));
			
			stat.put(beginDate, map);
			
			beginDate = new Date (beginDate.getTime () + timeAdj);
			System.out.println(beginDate.toString());
		}

		return stat;
	}

}
