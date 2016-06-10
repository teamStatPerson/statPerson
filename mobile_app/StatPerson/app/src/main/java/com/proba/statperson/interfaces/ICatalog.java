package com.proba.statperson.interfaces;

import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;

/**
 * Created by vadik on 01.06.2016.
 */
public interface ICatalog {
    void adminGetListOfCatalogElements(int personID);

    void adminDeleteElement(Object object);

    void adminAddElement(Object object);
}
