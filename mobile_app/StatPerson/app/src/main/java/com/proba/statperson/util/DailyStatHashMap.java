package com.proba.statperson.util;

import java.util.HashMap;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class DailyStatHashMap extends HashMap<String, String> {

    public static final String DAILYDATE = "dailydate";
    public static final String QUANTITY = "quantity";

    public DailyStatHashMap(String dailydate, String quantity) {
        super();
        super.put(DAILYDATE, dailydate);
        super.put(QUANTITY, quantity);
    }
}
