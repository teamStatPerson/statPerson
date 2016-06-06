package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadik on 01.06.2016.
 */
public class SitesCatalog implements ICatalog {
    private IView view;

    ArrayList<CatalogElement> sites;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        SitesListTask sitesListTask = new SitesListTask();
        sitesListTask.execute();
    }

    @Override
    public void adminDeleteElement(Object object) {

    }

    class SitesListTask extends AsyncTask<Void, Void, ArrayList<CatalogElement>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CatalogElement> doInBackground(Void... params) {
//            Administrator administrator = new Administrator();
//            keywords = AdministratorAPI.getPersons(administrator);

            sites = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                CatalogElement person = new Person("FakeSite #_" + i);
                sites.add(person);

            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return sites;
        }

        @Override
        protected void onPostExecute(ArrayList<CatalogElement> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new NewCatalogElementsListEvent(getSitesNamesFromArray(result)));
        }

        //// TODO: 02.06.2016 ref-ng, trough interface:
        private String[] getSitesNamesFromArray(ArrayList<CatalogElement> catalogElements) {
            String[] sitesNames = new String[catalogElements.size()];
            for (int i = 0; i < sitesNames.length; i++) {
                sitesNames[i] = catalogElements.get(i).getName();
            }
            return sitesNames;
        }
    }
}
