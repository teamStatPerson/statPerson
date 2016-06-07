package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Person;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import statPerson.element.account.Account;
import statPerson.element.site.Site;

/**
 * Created by vadik on 01.06.2016.
 */
public class SitesCatalog implements ICatalog {
    private IView view;

    ArrayList<CatalogElement> sitesList;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        SitesListTask sitesListTask = new SitesListTask();
        sitesListTask.execute();
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

            sitesList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                CatalogElement person = new Person("FakeSite #_" + i);
                sitesList.add(person);

            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return sitesList;
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

    @Override
    public void adminDeleteElement(Object object) {
        Site site;
        site = (Site) object;
        SitesDeleteElementTask sitesDeleteElementTask = new SitesDeleteElementTask();
        sitesDeleteElementTask.execute(site);
    }

    class SitesDeleteElementTask extends AsyncTask<Site, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Site... sites) {
            Account account = new Account();
            account.setEmail("q@q.com");
            account.setPassword("paswword");

            for (Site site : sites) {
//                FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//                fakeWebServiceAPI.removeSite(account, site);
            }

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
