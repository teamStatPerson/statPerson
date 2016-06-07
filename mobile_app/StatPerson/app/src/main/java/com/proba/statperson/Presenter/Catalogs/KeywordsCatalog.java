package com.proba.statperson.presenter.Catalogs;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.PersonKeywordsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.Person;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;

/**
 * Created by vadik on 01.06.2016.
 */
public class KeywordsCatalog implements ICatalog {

    private IView view;

    ArrayList<Keyword> keywords;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        KeywordsListTask keywordssListTask = new KeywordsListTask();
        keywordssListTask.execute(param);
    }

    class KeywordsListTask extends AsyncTask<String, Void, ArrayList<Keyword>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Keyword> doInBackground(String... personName) {
//            Administrator administrator = new Administrator();
//            keywords = AdministratorAPI.getKeywords(administrator, person);
            keywords = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Keyword keyword = new Keyword(Arrays.toString(personName) + "'s fakeKeyword #" + i, 0);
                keywords.add(keyword);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return keywords;
        }

        @Override
        protected void onPostExecute(ArrayList<Keyword> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new PersonKeywordsListEvent(getKeywordsNamesFromArray(result)));
        }

        private String[] getKeywordsNamesFromArray(ArrayList<Keyword> catalogElements) {
            String[] keywordsNames = new String[catalogElements.size()];
            for (int i = 0; i < keywordsNames.length; i++) {
                keywordsNames[i] = catalogElements.get(i).getName();
            }
            return keywordsNames;
        }
    }

    @Override
    public void adminDeleteElement(Object object) {
        Keyword keyword;
        keyword = (Keyword) object;
        SitesDeleteElementTask sitesDeleteElementTask = new SitesDeleteElementTask();
        sitesDeleteElementTask.execute(keyword);
    }

    class SitesDeleteElementTask extends AsyncTask<Keyword, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Keyword... keywords) {
            Account account = new Account();
            account.setEmail("q@q.com");
            account.setPassword("paswword");

            for (Keyword keyword : keywords) {
//                FakeWebServiceAPI fakeWebServiceAPI = new FakeWebServiceAPI();
//                fakeWebServiceAPI.removeKeyword(account, keyword);
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
