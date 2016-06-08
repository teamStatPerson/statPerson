package com.proba.statperson.view.user.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.proba.statperson.R;
import com.proba.statperson.events.SetDateFromEvent;
import com.proba.statperson.events.SetDateTillEvent;
import com.proba.statperson.interfaces.DailyStatDate;
import com.proba.statperson.util.DailyStatHashMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class DailyStatListFragment extends ListFragment {

    public String dateFrom;
    public String dateTill;
    private DailyStatDate mCallback;

    ArrayList<DailyStatHashMap> dailyStatList;

    public DailyStatListFragment() {

    }

    public String getStartDate(String startDate) {
        dateFrom = startDate;
        return dateFrom;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dailyStatList = new ArrayList<DailyStatHashMap>();

        ListAdapter adapter = new SimpleAdapter(getActivity(), dailyStatList, R.layout.daily_stat_list_item,
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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onSetDateFromEvent(SetDateFromEvent event) {
//
//        dateFrom = event.date;
//
//        dailyStatList.add(new DailyStatHashMap(dateFrom, "11")); // добавляем начальную дату на первую строку
//
//        ListAdapter adapter = new SimpleAdapter(getActivity(), dailyStatList, R.layout.daily_stat_list_item,
//                new String[]{DailyStatHashMap.DAILYDATE, DailyStatHashMap.QUANTITY}, new int[]{
//                R.id.tvDate, R.id.tvQty});
//        setListAdapter(adapter);
    }

    @Subscribe
    public void onSetDateTillEvent(SetDateTillEvent event) {
//
//        dateTill = event.date;
//
//        // добавляем fake даты на промежуточные строки
//        String[] fake_date = getResources().getStringArray(R.array.fake_date_array);
//        int i;
//        for (i = 0; i < fake_date.length; i++) {
//            dailyStatList.add(new DailyStatHashMap(fake_date[i], " " + i));
//        }
//        dailyStatList.add(new DailyStatHashMap(dateTill, "22")); // добавляем конечную дату на последнюю строку
//
//        ListAdapter adapter = new SimpleAdapter(getActivity(), dailyStatList, R.layout.daily_stat_list_item,
//                new String[]{DailyStatHashMap.DAILYDATE, DailyStatHashMap.QUANTITY}, new int[]{
//                R.id.tvDate, R.id.tvQty});
//        setListAdapter(adapter);
    }
}
