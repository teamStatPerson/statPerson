package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewPersonsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import statPerson.element.account.Account;
import statPerson.element.person.Person;

/**
 * Created by vadik on 01.06.2016.
 */
public class PersonsCatalog implements ICatalog {
    //    IModel model;
    private IView view;

    @Override
    public void adminGetListOfCatalogElements(int personID) {
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
//            Person person = new Person();
//            person.setName("Васек");
            List<Person> persons = new ArrayList<>();
//            persons.add(person);
//            Person person2 = new Person();
//            person2.setName("Санек");
//            persons.add(person2);
//
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            String DEMO_SERVICE_URL = "http://146.66.177.105:8080/statPerson/rest/restDemo";
//            Client client = ClientBuilder.newClient();
//            String demo = client.target(DEMO_SERVICE_URL).request().get(String.class);
//            System.out.println(demo);

//            PersonAPI_REST_Client client =  new PersonAPI_REST_Client();
//            persons =  client.getAllPersons();

//            Client client = ClientBuilder.newClient();
//            String REST_SERVICE_URL = "http://146.66.177.105:8080/statPerson/rest/PersonAPI/";
//            GenericType<List<Person>> list = new GenericType<List<Person>>() {
//            };
//            persons  = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).get(list);


//            persons = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).get(list);

//            List<Person> persons;
//            Account account = new Account();
//            account.setEmail("q@q.com");
//            account.setPassword("paswword");
//            FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//            persons = fakeWebServiceAPI.getPersons(account);

            persons.add(new Person(0, "somePerson1"));
            persons.add(new Person(1, "somePerson2"));
            return persons;
        }

        @Override
        protected void onPostExecute(List<Person> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new NewPersonsListEvent(result));
        }

//        private String[] getPersonNamesFromArray(List<Person> catalogElements) {
//            String[] personsNames = new String[catalogElements.size()];
//            for (int i = 0; i < personsNames.length; i++) {
//                personsNames[i] = catalogElements.get(i).getName();
//            }
//            return personsNames;
//        }

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
            adminGetListOfCatalogElements(0);
        }

    }

    @Override
    public void adminAddElement(Object object) {
        Person person;
        person = (Person) object;
        PersonsAddElementTask personsAddElementTask = new PersonsAddElementTask();
        personsAddElementTask.execute(person);
    }

    class PersonsAddElementTask extends AsyncTask<Person, Void, String> {

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
//                fakeWebServiceAPI.addPerson(account, person);
            }

            return "Done";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            EventBus.getDefault().post(new EditCatalogElementsEvent(result));
            adminGetListOfCatalogElements(0);
        }

    }
}
