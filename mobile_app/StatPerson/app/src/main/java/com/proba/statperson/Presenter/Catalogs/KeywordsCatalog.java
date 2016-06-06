package com.proba.statperson.presenter.Catalogs;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.proba.statperson.events.PersonKeywordsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;
import com.proba.statperson.presenter.CatalogElement.CatalogElement;
import com.proba.statperson.presenter.CatalogElement.Keyword;
import com.proba.statperson.presenter.CatalogElement.Person;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadik on 01.06.2016.
 */
public class KeywordsCatalog implements ICatalog {

    private IView view;

    ArrayList<CatalogElement> keywords;

    @Override
    public void adminGetListOfCatalogElements(String param) {
        KeywordsListTask keywordssListTask = new KeywordsListTask();
        keywordssListTask.execute(param);
    }

    @Override
    public void adminDeleteElement(Object object) {

    }

    class KeywordsListTask extends AsyncTask<String, Void, ArrayList<CatalogElement>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CatalogElement> doInBackground(String... personName) {
//            Administrator administrator = new Administrator();
//            keywords = AdministratorAPI.getKeywords(administrator, person);
            keywords = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                CatalogElement keyword = new Keyword(Arrays.toString(personName) + "'s fakeKeyword #" + i);
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
        protected void onPostExecute(ArrayList<CatalogElement> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new PersonKeywordsListEvent(getKeywordsNamesFromArray(result)));
        }

        private String[] getKeywordsNamesFromArray(ArrayList<CatalogElement> catalogElements) {
            String[] keywordsNames = new String[catalogElements.size()];
            for (int i = 0; i < keywordsNames.length; i++) {
                keywordsNames[i] = catalogElements.get(i).getName();
            }
            return keywordsNames;
        }
    }
}
