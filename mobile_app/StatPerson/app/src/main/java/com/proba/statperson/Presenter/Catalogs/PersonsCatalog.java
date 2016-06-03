package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;
import com.proba.statperson.view.admin.fragments.FragmentPersons;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadik on 01.06.2016.
 */
public class PersonsCatalog implements ICatalog {
    //    IModel model;
    private IView view;

    ArrayList<CatalogElement> persons;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        PersonsListTask personsListTask = new PersonsListTask();
        personsListTask.execute();
    }

    class PersonsListTask extends AsyncTask<Void, Void, ArrayList<CatalogElement>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CatalogElement> doInBackground(Void... params) {
//            Administrator administrator = new Administrator();
//            keywords = AdministratorAPI.getPersons(administrator);

            persons = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                CatalogElement person = new Person("FakeChuvak #_" + i);
                persons.add(person);

            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return persons;
        }

        @Override
        protected void onPostExecute(ArrayList<CatalogElement> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new NewCatalogElementsListEvent(getPersonNamesFromArray(result)));
        }

        private String[] getPersonNamesFromArray(ArrayList<CatalogElement> catalogElements) {
            String[] personsNames = new String[catalogElements.size()];
            for (int i = 0; i < personsNames.length; i++) {
                personsNames[i] = catalogElements.get(i).getName();
            }
            return personsNames;
        }
    }


}
