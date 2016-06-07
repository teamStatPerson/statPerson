package com.proba.statperson.util;

import java.util.HashMap;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class TotalStatHashMap extends HashMap<String, String> {

    public static final String PERSON = "person";
    public static final String QUANTITY = "quantity";

    public TotalStatHashMap(String person, String quantity) {
        super();
        super.put(PERSON, person);
        super.put(QUANTITY, quantity);
    }
}
