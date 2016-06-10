package com.proba.statperson.events;

import java.util.List;

import statPerson.element.person.Person;

/**
 * Created by vadik on 02.06.2016.
 */
public class NewPersonsListEvent {
    public final List<Person> message;

    public NewPersonsListEvent(List<Person> message) {
        this.message = message;
    }
}
