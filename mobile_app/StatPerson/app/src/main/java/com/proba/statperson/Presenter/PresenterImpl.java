package com.proba.statperson.presenter;

import com.proba.statperson.Constants;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;
import com.proba.statperson.presenter.CatalogElement.Site;
import com.proba.statperson.presenter.Catalogs.KeywordsCatalog;
import com.proba.statperson.presenter.Catalogs.PersonsCatalog;
import com.proba.statperson.presenter.Catalogs.SitesCatalog;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IModel;
import com.proba.statperson.interfaces.IPresenter;

/**
 * Created by vadik on 30.05.2016.
 */
public class PresenterImpl implements IPresenter {
    private IModel model;

    @Override
    public void adminGetListOfCatalogElements(int catalogIndex, String param) {
        ICatalog catalog;
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                catalog = new PersonsCatalog();
                catalog.adminGetListOfCatalogElements(null);
                break;
            case Constants.SITES_CATALOG_INDEX:
                catalog = new SitesCatalog();
                catalog.adminGetListOfCatalogElements(null);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                catalog = new KeywordsCatalog();
                catalog.adminGetListOfCatalogElements(param);
                break;
        }

    }

    @Override
    public void userGetOverallStatistics(Site site) {

    }

}
