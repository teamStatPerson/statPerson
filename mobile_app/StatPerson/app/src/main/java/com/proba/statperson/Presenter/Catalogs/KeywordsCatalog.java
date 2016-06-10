package com.proba.statperson.presenter.Catalogs;

import android.os.AsyncTask;

import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewPersonKeywordsListEvent;
import com.proba.statperson.interfaces.ICatalog;
import com.proba.statperson.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;

/**
 * Created by vadik on 01.06.2016.
 */
public class KeywordsCatalog implements ICatalog {

    private IView view;

    private int personID;

    ArrayList<Keyword> keywords;

    @Override
    public void adminGetListOfCatalogElements(int personID) {
        this.personID = personID;
        KeywordsListTask keywordssListTask = new KeywordsListTask();
        keywordssListTask.execute(personID);
    }

    class KeywordsListTask extends AsyncTask<Integer, Void, ArrayList<Keyword>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Keyword> doInBackground(Integer... personID) {
//            Administrator administrator = new Administrator();
//            keywords = AdministratorAPI.getKeywords(administrator, person);
            keywords = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Keyword keyword = new Keyword("somePerson" + Arrays.toString(personID) + "'s fakeKeyword #" + i, 0);
                keywords.add(keyword);
            }

//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return keywords;
        }

        @Override
        protected void onPostExecute(ArrayList<Keyword> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new NewPersonKeywordsListEvent(result));
        }

//        private String[] getKeywordsNamesFromArray(ArrayList<Keyword> catalogElements) {
//            String[] keywordsNames = new String[catalogElements.size()];
//            for (int i = 0; i < keywordsNames.length; i++) {
//                keywordsNames[i] = catalogElements.get(i).getName();
//            }
//            return keywordsNames;
//        }
    }

    @Override
    public void adminDeleteElement(Object object) {
        Keyword keyword;
        keyword = (Keyword) object;
        KeywordsDeleteElementTask keywordsDeleteElementTask = new KeywordsDeleteElementTask();
        keywordsDeleteElementTask.execute(keyword);
    }



    class KeywordsDeleteElementTask extends AsyncTask<Keyword, Void, String> {

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
            adminGetListOfCatalogElements(personID);
        }
    }

    @Override
    public void adminAddElement(Object object) {
        Keyword keyword;
        keyword = (Keyword) object;
        KeywordsAddElementTask sitesAddElementTask = new KeywordsAddElementTask();
        sitesAddElementTask.execute(keyword);
    }

    class KeywordsAddElementTask extends AsyncTask<Keyword, Void, String> {

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
//                fakeWebServiceAPI.addKeyword(account, keyword);
            }

            return "Done";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            EventBus.getDefault().post(new EditCatalogElementsEvent(result));
            adminGetListOfCatalogElements(personID);
        }
    }

}
