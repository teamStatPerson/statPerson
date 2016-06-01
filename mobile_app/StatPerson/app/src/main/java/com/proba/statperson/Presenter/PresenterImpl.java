package com.proba.statperson.presenter;

import com.proba.statperson.Constants;
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
    public void adminGetListOfCatalogElements(int catalogIndex) {
        ICatalog catalog;
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                catalog = new PersonsCatalog();
                catalog.adminGetListOfCatalogElements();
                break;
            case Constants.SITES_CATALOG_INDEX:
                catalog = new SitesCatalog();
                catalog.adminGetListOfCatalogElements();
                break;
        }
    }

}
