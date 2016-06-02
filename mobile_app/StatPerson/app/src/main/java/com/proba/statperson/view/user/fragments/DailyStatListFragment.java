package com.proba.statperson.view.user.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.proba.statperson.R;
import com.proba.statperson.events.SetDateEvent;
import com.proba.statperson.interfaces.DailyStatDate;
import com.proba.statperson.util.DailyStatHashMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class DailyStatListFragment extends ListFragment {

    public Calendar initialDate;
    //    public Date startDate;
//    public Date endDate;
    public Date oneDay;
    public String dateFrom;
    public String dateTill;
    private DailyStatDate mCallback;

    public DailyStatListFragment() {

    }

    public Calendar InitialDate(Calendar initialDate) {
        this.initialDate = initialDate;
        initialDate = Calendar.getInstance();
        return initialDate;
    }

    public String fromDate() {
        final Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR) + "";
        String month = c.get(Calendar.MONTH) + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = c.get(Calendar.DAY_OF_MONTH) + 20 + "";
        if (day.length() < 2) {
            day = "0" + day;
        }
        return (day + "." + month + "." + year);
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
        return (day + "." + month + "." + year);
    }

    public String getStartDate(String startDate) {
        dateFrom = startDate;
        return dateFrom;
    }

    /*
        public void getEndDate (String endDate) {
            dateTill = endDate;
            return dateTill;
        }
    */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dateTill = tillDate();

        ArrayList<DailyStatHashMap> list = new ArrayList<DailyStatHashMap>();

        list.add(new DailyStatHashMap(dateFrom, "11"));
        list.add(new DailyStatHashMap(dateTill, "222"));

        ListAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.daily_stat_list_item,
                new String[]{DailyStatHashMap.DAILYDATE, DailyStatHashMap.QUANTITY}, new int[]{
                R.id.tvDate, R.id.tvQty});
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (DailyStatDate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement DailyStatDate");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // регистрация приемника при старте фрагмента
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // отписываемся от регистрации при закрытии фрагмента
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onSetDateEvent(SetDateEvent event) {
        dateFrom = event.date;

        ArrayList<DailyStatHashMap> list = new ArrayList<DailyStatHashMap>();

        list.add(new DailyStatHashMap(dateFrom, "11"));

        ListAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.daily_stat_list_item,
                new String[]{DailyStatHashMap.DAILYDATE, DailyStatHashMap.QUANTITY}, new int[]{
                R.id.tvDate, R.id.tvQty});
        setListAdapter(adapter);
    }
}
