package com.proba.statperson.presenter;

import com.proba.statperson.Constants;
import com.proba.statperson.presenter.Catalogs.KeywordsCatalog;
import com.proba.statperson.presenter.Catalogs.PersonsCatalog;
import com.proba.statperson.presenter.Catalogs.SitesCatalog;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IModel;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.presenter.Statistics.DailyStatistics;
import com.proba.statperson.presenter.Statistics.OverallStatistics;

import statPerson.element.keyword.Keyword;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by vadik on 30.05.2016.
 */
public class PresenterImpl implements IPresenter {
    private IModel model;

    @Override
    public void adminGetListOfCatalogElements(int catalogIndex, int personID) {
        ICatalog catalog;
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                catalog = new PersonsCatalog();
                catalog.adminGetListOfCatalogElements(0);
                break;
            case Constants.SITES_CATALOG_INDEX:
                catalog = new SitesCatalog();
                catalog.adminGetListOfCatalogElements(0);
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                catalog = new KeywordsCatalog();
                catalog.adminGetListOfCatalogElements(personID);
                break;
        }
    }

    @Override
    public void adminDeleteElement(int elementID, int catalogIndex, int personId) {
        ICatalog catalog;
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                catalog = new PersonsCatalog();
                catalog.adminDeleteElement(new Person("elementID"));
                break;
            case Constants.SITES_CATALOG_INDEX:
                catalog = new SitesCatalog();
                catalog.adminDeleteElement(new Site("elementID", null));
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                catalog = new KeywordsCatalog();
                catalog.adminDeleteElement(new Keyword("elementID", personId));
                break;
        }
    }

    @Override
    public void adminAddElement(String elementName, int catalogIndex, int personId) {
        ICatalog catalog;
        switch (catalogIndex) {
            case Constants.PERSONS_CATALOG_INDEX:
                catalog = new PersonsCatalog();
                catalog.adminAddElement(new Person(elementName));
                break;
            case Constants.SITES_CATALOG_INDEX:
                catalog = new SitesCatalog();
                catalog.adminAddElement(new Site(elementName, null));
                break;
            case Constants.KEYWORDS_CATALOG_INDEX:
                catalog = new KeywordsCatalog();
                catalog.adminAddElement(new Keyword(elementName, 0));
                break;
        }
    }

    @Override
    public void userGetOverallStatistics(Site site) {
        OverallStatistics overallStatistics = new OverallStatistics();
        overallStatistics.userGetOverallStatistics(site);
    }

    @Override
    public void userGetDailyStatistics(Site site, Person person, String dateTill, String dateFrom) {
        DailyStatistics dailyStatistics = new DailyStatistics();
        dailyStatistics.userGetDailyStatistics(site, person, dateTill, dateFrom);
    }

}
