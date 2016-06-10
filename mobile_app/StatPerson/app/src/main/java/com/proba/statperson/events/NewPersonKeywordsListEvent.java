package com.proba.statperson.events;

import java.util.List;

import statPerson.element.keyword.Keyword;

/**
 * Created by vadik on 03.06.2016.
 */
public class NewPersonKeywordsListEvent {
    public final List<Keyword> message;

    public NewPersonKeywordsListEvent(List<Keyword> message) {
        this.message = message;
    }
}
