package com.proba.statperson.presenter.CatalogElement;

/**
 * Created by vadik on 30.05.2016.
 */
abstract public class CatalogElement {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public CatalogElement(String name) {
        this.name = name;
    }
}
