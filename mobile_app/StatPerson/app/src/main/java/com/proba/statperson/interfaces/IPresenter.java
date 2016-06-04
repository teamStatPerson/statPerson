package com.proba.statperson.interfaces;

import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;
import com.proba.statperson.presenter.CatalogElement.Site;

/**
 * Created by vadik on 30.05.2016.
 */
public interface IPresenter {
    // TODO: 03.06.2016 refactor: do without Person arg (?)
    void adminGetListOfCatalogElements(int catalogIndex, String param);

    void userGetOverallStatistics(Site site);
}
