package com.proba.statperson.view.user;

/**
 * Created by Konstantin on 03.06.2016.
 */
public class DateFormater {

    int day;
    int month;

    public static String DayFormater(int day) {
        String sday = day + "";
        if (sday.length() < 2)

        {
            sday = "0" + sday;
        }
        return sday;
    }

    public static String MonthFormater(int month) {
        month = month + 1;
        String smonth = month + "";
        if (smonth.length() < 2)

        {
            smonth = "0" + smonth;
        }
        return smonth;
    }
}
