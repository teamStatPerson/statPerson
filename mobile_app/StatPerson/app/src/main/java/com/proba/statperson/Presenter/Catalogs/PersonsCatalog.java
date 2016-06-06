package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.FakeWebServiceAPI;
import com.proba.statperson.view.admin.fragments.FragmentPersons;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import statPerson.element.account.Account;
import statPerson.element.person.Person;

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
            List<Person> persons;
            Account account = new Account();
            account.setEmail("q@q.com");
            account.setPassword("paswword");
            FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
            persons = fakeWebServiceAPI.getPersons(account);
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


}
