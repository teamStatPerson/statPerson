package ru.geekbrains.userapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date DateFromString(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException ex){
			ex.printStackTrace();
		}
		return date;
	}	

	public static String StringFromDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String dateString = format.format(date);

		return dateString;
	}	
}
