package com.proba.statperson.interfaces;


import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by vadik on 30.05.2016.
 */
public interface IPresenter {
    // TODO: 03.06.2016 refactor: do without Person arg (?)
    void adminGetListOfCatalogElements(int catalogIndex, String param);

    void userGetOverallStatistics(Site site);

    void userGetDailyStatistics(Site site, Person person, String dateTill, String dateFrom);

    void adminDeleteElement(String name, int catalogIndex, int personId);
}
