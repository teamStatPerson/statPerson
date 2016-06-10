package com.proba.statperson.events;

import java.util.List;

import statPerson.element.site.Site;

/**
 * Created by vadik on 10.06.2016.
 */
public class NewSitesListEvent {
    public final List<Site> message;

    public NewSitesListEvent(List<Site> message) {
        this.message = message;
    }
}
