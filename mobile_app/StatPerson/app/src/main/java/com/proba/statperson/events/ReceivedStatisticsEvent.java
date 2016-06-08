package com.proba.statperson.events;

import java.util.HashMap;

/**
 * Created by vadik on 04.06.2016.
 */
public class ReceivedStatisticsEvent {
    public final HashMap<String, Integer> message;

    public ReceivedStatisticsEvent(HashMap<String, Integer> message) {
        this.message = message;
    }
}
