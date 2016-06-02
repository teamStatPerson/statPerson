package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadik on 01.06.2016.
 */
public class PersonsCatalog implements ICatalog {
    //    IModel model;
    List<CatalogElement> persons;

    @Override
    public void adminGetListOfCatalogElements() {
        PersonsListTask personsListTask = new PersonsListTask();
        personsListTask.execute();
    }

    class PersonsListTask extends AsyncTask<Void, Void, List<CatalogElement>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<CatalogElement> doInBackground(Void... params) {
//            Administrator administrator = new Administrator();
//            persons = AdministratorAPI.getPersons(administrator);

            persons = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                CatalogElement person = new Person("FakeChuvak" + i);
                persons.add(person);
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return persons;
        }

        @Override
        protected void onPostExecute(List<CatalogElement> result) {
            super.onPostExecute(result);

        }
    }


}
