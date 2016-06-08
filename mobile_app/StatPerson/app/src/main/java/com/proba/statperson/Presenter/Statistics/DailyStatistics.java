package com.proba.statperson.presenter.Statistics;

import android.os.AsyncTask;

import com.proba.statperson.events.ReceivedStatisticsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by vadik on 04.06.2016.
 */
public class DailyStatistics {
    HashMap<String, Integer> dailyStatistics;
    private Site site;
    private Person person;
    private String dateFrom;
    private String dateTill;

    public void userGetDailyStatistics(Site site, Person person, String dateTill, String dateFrom) {
        this.site = site;
        this.person = person;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        GetDailyStatisticsTask getDailyStatisticsTask = new GetDailyStatisticsTask();
        getDailyStatisticsTask.execute();
    }

    class GetDailyStatisticsTask extends AsyncTask<Void, Void, HashMap<String, Integer>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected HashMap<String, Integer> doInBackground(Void... params) {
            dailyStatistics = new HashMap<>();

            for (int i = 0; i < 5; i++) {
//                CatalogElement person = new Person("FakeSite #_" + i);
//                sites.add(person);
                dailyStatistics.put(dateFrom, i * i);
            }
            for (int i = 0; i < 5; i++) {
//                CatalogElement person = new Person("FakeSite #_" + i);
//                sites.add(person);
                dailyStatistics.put(dateTill, i * i);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return dailyStatistics;
        }

        @Override
        protected void onPostExecute(HashMap<String, Integer> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new ReceivedStatisticsEvent(result));
        }

    }
}
