package com.proba.statperson.view.user.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.proba.statperson.R;
import com.proba.statperson.interfaces.TotalStatSite;
import com.proba.statperson.util.TotalStatHashMap;

import java.util.ArrayList;

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
        totalStatList.add(new TotalStatHashMap(getString(R.string.name), getString(R.string.quantity))); // добавляем заголовок
        totalStatList.add(new TotalStatHashMap("Putin", "11"));
        totalStatList.add(new TotalStatHashMap("Medvedev", "22"));
        totalStatList.add(new TotalStatHashMap("Navalny", "33"));
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
}
