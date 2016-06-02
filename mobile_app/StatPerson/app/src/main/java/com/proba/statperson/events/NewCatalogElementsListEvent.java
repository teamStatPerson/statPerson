package com.proba.statperson.events;

/**
 * Created by vadik on 02.06.2016.
 */
public class NewCatalogElementsListEvent {
    public final String[] message;

    public NewCatalogElementsListEvent(String[] message) {
        this.message = message;
    }
}
