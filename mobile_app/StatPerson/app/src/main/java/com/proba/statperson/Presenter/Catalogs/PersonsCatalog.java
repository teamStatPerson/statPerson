package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.FakeWebServiceAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import statPerson.element.account.Account;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by vadik on 01.06.2016.
 */
public class PersonsCatalog implements ICatalog {
    //    IModel model;
    private IView view;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        PersonsListTask personsListTask = new PersonsListTask();
        personsListTask.execute();
    }

    class PersonsListTask extends AsyncTask<Void, Void, List<Person>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Person> doInBackground(Void... params) {
            Person person = new Person();
            person.setName("Васек");
            List<Person> persons = new ArrayList<>();
            persons.add(person);
            Person person2 = new Person();
            person2.setName("Санек");
            persons.add(person2);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            List<Person> persons;
//            Account account = new Account();
//            account.setEmail("q@q.com");
//            account.setPassword("paswword");
//            FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//            persons = fakeWebServiceAPI.getPersons(account);
            return persons;
        }

        @Override
        protected void onPostExecute(List<Person> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new NewCatalogElementsListEvent(getPersonNamesFromArray(result)));
        }

        private String[] getPersonNamesFromArray(List<Person> catalogElements) {
            String[] personsNames = new String[catalogElements.size()];
            for (int i = 0; i < personsNames.length; i++) {
                personsNames[i] = catalogElements.get(i).getName();
            }
            return personsNames;
        }

    }

    @Override
    public void adminDeleteElement(Object object) {
        Person person;
        person = (Person) object;
        PersonsDeleteElementTask personsDeleteElementTask = new PersonsDeleteElementTask();
        personsDeleteElementTask.execute(person);
    }


    class PersonsDeleteElementTask extends AsyncTask<Person, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Person... persons) {
            Account account = new Account();
            account.setEmail("q@q.com");
            account.setPassword("paswword");

            for (Person person : persons) {
//                FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//                fakeWebServiceAPI.removePerson(account, person);
            }

//            Person person = new Person();
//            person.setName("Васек");
//            List<Person> persons = new ArrayList<>();
//            persons.add(person);
//            Person person2 = new Person();
//            person2.setName("Санек");
//            persons.add(person2);

//            List<Person> persons;
//            Account account = new Account();
//            account.setEmail("q@q.com");
//            account.setPassword("paswword");
//            FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//            persons = fakeWebServiceAPI.getPersons(account);
//            return persons;
            return "Done";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            EventBus.getDefault().post(new EditCatalogElementsEvent(result));
            adminGetListOfCatalogElements(null);
        }

    }


}
