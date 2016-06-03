package com.proba.statperson.events;

/**
 * Created by vadik on 03.06.2016.
 */
public class PersonKeywordsListEvent {
    public final String[] message;

    public PersonKeywordsListEvent(String[] message) {
        this.message = message;
    }
}
