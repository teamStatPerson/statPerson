package com.proba.statperson.presenter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import statPerson.api.interfaceAPI;
import statPerson.element.account.Account;
import statPerson.element.administrator_price.AdministratorPrice;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.price.Price;
import statPerson.element.site.Site;

/**
 * Created by vadik on 06.06.2016.
 */
public class FakeWebServiceAPI implements interfaceAPI {
    @Override
    public Integer addPrimaryAdministrator(String s, String s1) {
        return null;
    }

    @Override
    public Integer addSecondaryAdministrator(String s, String s1, int i) {
        return null;
    }

    @Override
    public Integer addUser(String s, String s1, int i) {
        return null;
    }

    @Override
    public void removeAccount(Integer integer) {

    }

    @Override
    public List<Person> getPersons(Account account) {
        return null;
    }

    @Override
    public List<Person> getPersons(String s, String s1) {
        return null;
    }

    @Override
    public void changePassword(Account account, String s) {

    }

    @Override
    public List<Site> getSites(Account account) {
        return null;
    }

    @Override
    public Integer addSite(Account account, Site site) {
        return null;
    }

    @Override
    public void removeSite(Account account, Site site) {

    }

    @Override
    public Integer addPerson(Account account, Person person) {
        return null;
    }

    @Override
    public void removePerson(Account account, Person person) {

    }

    @Override
    public List<Price> getPrices() {
        return null;
    }

    @Override
    public void addPriceToPrimaryAdministrator(Integer integer, Integer integer1) {

    }

    @Override
    public List<AdministratorPrice> getAllPricesAdministrator(Integer integer) {
        return null;
    }

    @Override
    public int amountPagesFromSite(Site site) {
        return 0;
    }

    @Override
    public int amountPagesInDBbutNeverScan(Site site) {
        return 0;
    }

    @Override
    public int amountPagesInDBscanned(Site site) {
        return 0;
    }

    @Override
    public Map<Date, Integer> getRankByPerDayByPerson(Person person, Date date, Date date1) {
        return null;
    }

    @Override
    public Map<Person, Page> getStatisticPersonPage() {
        return null;
    }

    @Override
    public Map<Person, Page> getStatisticPersonNewPages() {
        return null;
    }

    @Override
    public Map<Site, Integer> getStatisticSiteAmountPage() {
        return null;
    }

    @Override
    public Keyword[] getKeywords(Account account, Person person) {
        return new Keyword[0];
    }

    @Override
    public void addKeyword(Account account, Keyword keyword) {

    }

    @Override
    public void removeKeyword(Account account, Keyword keyword) {

    }
}
