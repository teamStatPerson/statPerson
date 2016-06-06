package com.proba.statperson.interfaces;


import java.util.Objects;

import statPerson.element.keyword.Keyword;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by vadik on 30.05.2016.
 */
public interface IPresenter {
    // TODO: 03.06.2016 refactor: do without Person arg (?)
    void adminGetListOfCatalogElements(int catalogIndex, String param);

    void userGetOverallStatistics(Site site);

    void addElement(Object object);

//    void addSite(Site site);
//
//    void addKeyword(Keyword keyword, Person person);
}
