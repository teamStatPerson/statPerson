package com.proba.statperson.presenter.Statistics;

import android.os.AsyncTask;

import com.proba.statperson.events.ReceivedStatisticsEvent;


import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import statPerson.element.site.Site;

/**
 * Created by vadik on 04.06.2016.
 */
public class OverallStatistics {

    HashMap<String, Integer> overallStatistics;

    public void userGetOverallStatistics(Site site) {
        GetOverallStatisticsTask getOverallStatisticsTask = new GetOverallStatisticsTask();
        getOverallStatisticsTask.execute(site);
    }

    class GetOverallStatisticsTask extends AsyncTask<Site, Void, HashMap<String, Integer>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected HashMap<String, Integer> doInBackground(Site... params) {
            overallStatistics = new HashMap<>();

            for (int i = 0; i < 10; i++) {
//                CatalogElement person = new Person("FakeSite #_" + i);
//                sites.add(person);
                overallStatistics.put("FakeChuvak_#" + i, i * i);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return overallStatistics;
        }

        @Override
        protected void onPostExecute(HashMap<String, Integer> result) {
            super.onPostExecute(result);

            EventBus.getDefault().post(new ReceivedStatisticsEvent(result));
        }
    }
}
