package com.proba.statperson.view.user.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.proba.statperson.R;
import com.proba.statperson.util.DailyStatHashMap;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class DailyStatListFragment extends ListFragment {

    public String startDate = "21.05.2016";
    public String endDate;


    public String fromDate() {
        final Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        String month = c.get(Calendar.MONTH)  + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = c.get(Calendar.DAY_OF_MONTH) + 20 + "";
        if (day.length() < 2) {
            day = "0" + day;
        }
        return (day + "." + month  + "." + year);
    }

    public String tillDate() {
        final Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        String month = c.get(Calendar.MONTH) + 1 + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = c.get(Calendar.DAY_OF_MONTH) + "";
        if (day.length() < 2) {
            day = "0" + day;
        }
        return (day + "." + month  + "." + year);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        startDate = fromDate();
        endDate   = tillDate();

        ArrayList<DailyStatHashMap> list = new ArrayList<DailyStatHashMap>();

        list.add(new DailyStatHashMap(startDate, "11"));
        list.add(new DailyStatHashMap (endDate, "22"));

        ListAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.daily_stat_list_item,
                new String[]{DailyStatHashMap.DAILYDATE, DailyStatHashMap.QUANTITY}, new int[]{
                R.id.tvDate, R.id.tvQty});
        setListAdapter(adapter);
    }
}
