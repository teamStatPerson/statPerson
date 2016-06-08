package com.proba.statperson.view.user.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.proba.statperson.R;
import com.proba.statperson.events.ReceivedStatisticsEvent;
import com.proba.statperson.interfaces.TotalStatSite;
import com.proba.statperson.util.TotalStatHashMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Konstantin on 02.06.2016.
 */
public class TotalStatListFragment extends ListFragment {

    //    private DailyStatDate mCallback;
    private TotalStatSite mCallback;

    ArrayList<TotalStatHashMap> totalStatList;

    public TotalStatListFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        totalStatList = new ArrayList<TotalStatHashMap>();
        ListAdapter adapter = new SimpleAdapter(getActivity(), totalStatList, R.layout.total_stat_list_item,
                new String[]{TotalStatHashMap.PERSON, TotalStatHashMap.QUANTITY}, new int[]{
                R.id.tvPerson, R.id.tvQty});
        setListAdapter(adapter);

    }

    @Subscribe
    public void onReceiveOverallStatistics(ReceivedStatisticsEvent overallStatistics) {
        mCallback.removeProgressBar();
//        mCallback.initFAB();

        ListAdapter adapter = new SimpleAdapter(getActivity(), totalStatList, R.layout.total_stat_list_item,
                new String[]{TotalStatHashMap.PERSON, TotalStatHashMap.QUANTITY}, new int[]{
                R.id.tvPerson, R.id.tvQty});

        totalStatList.add(new TotalStatHashMap(getString(R.string.name), getString(R.string.quantity)));

        for (Map.Entry<String, Integer> entry : overallStatistics.message.entrySet()) {
            totalStatList.add(new TotalStatHashMap(entry.getKey(), entry.getValue().toString()));
        }

        setListAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (TotalStatSite) activity;
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
}
