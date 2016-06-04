package com.proba.statperson.view.admin.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.R;
import com.proba.statperson.events.SetMedvedevEvent;
import com.proba.statperson.events.SetNavalnyEvent;
import com.proba.statperson.events.SetPutinEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentKeyWords.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentKeyWords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentKeyWords extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextView textViewPersonName;

    private OnFragmentInteractionListener mListener;

    final String[] keyWordsPutinArray = new String[]{"Путиным", "Путину", "Путина", "Путине"};
    private ArrayList<String> keyWordsPutinList = new ArrayList<>(Arrays.asList(keyWordsPutinArray));
    final String[] keyWordsMedvedevArray = new String[]{"Медведевым", "Медведеву", "Медведева", "Медведеве"};
    private ArrayList<String> keyWordsMedvedevList = new ArrayList<>(Arrays.asList(keyWordsMedvedevArray));
    final String[] keyWordsNavalnyArray = new String[]{"Навальным", "Навальному", "Навального", "Навальном"};
    private ArrayList<String> keyWordsNavalnyList = new ArrayList<>(Arrays.asList(keyWordsNavalnyArray));
// Fragment FragmentKeyWords{56d4736} not attached to Activity
// private ArrayList<String> keyWordsPutinList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.putin_array)));
//    final String[] keyWordsPutin = getResources().getStringArray(R.array.putin_array);
//    final String[] keyWordsMedvedev = new String[] {getResources().getStringArray(R.array.medvedev_array);
//    final String[] keyWordsNavalny = getResources().getStringArray(R.array.navalny_array);

    public FragmentKeyWords() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentKeyWords.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentKeyWords newInstance(String param1, String param2) {
        FragmentKeyWords fragment = new FragmentKeyWords();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String prompt = "Вы выбрали: "
                + getListView().getItemAtPosition(position).toString() + "\n";

        prompt += "Выбранные элементы: \n";
        int count = getListView().getCount();
        SparseBooleanArray sparseBooleanArray = getListView()
                .getCheckedItemPositions();
        for (int i = 0; i < count; i++) {
            if (sparseBooleanArray.get(i)) {
                prompt += getListView().getItemAtPosition(i).toString() + "\n";
            }
        }
        Toast.makeText(getActivity(), prompt, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        registerForContextMenu(getListView());
        super.onActivityCreated(savedState);
        setHasOptionsMenu(true);

        setListAdapter(getListAdapter());
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                Toast.makeText(getActivity(), "On long click listener", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.edit:
                Toast.makeText(getActivity(), "Edit clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.delete:
                Toast.makeText(getActivity(), "Delete clicked", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    /*
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = parent.getItemAtPosition(position).toString();

            Toast.makeText(getActivity(), selectedItem + " выбран.",
                    Toast.LENGTH_SHORT).show();

            return true;
        }
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_key_words, null);
        textViewPersonName = (TextView) rootView.findViewById(R.id.textViewPersonName);
/*        keyWordsAdapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, keyWordsPutin);

            android.R.layout.simple_list_item_multiple_choice, keyWordsPutinList);
        setListAdapter(keyWordsAdapter);
  */      return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }
    */

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
    public void onSetPutinEvent(SetPutinEvent event) {
        textViewPersonName.setText(getString(R.string.putin));
        ListAdapter keyWordsAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, keyWordsPutinList);
        setListAdapter(keyWordsAdapter);
    }

    @Subscribe
    public void onSetMedvedevEvent(SetMedvedevEvent event) {
        textViewPersonName.setText(getString(R.string.medvedev));
        ListAdapter keyWordsAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, keyWordsMedvedevList);
        setListAdapter(keyWordsAdapter);
    }

    @Subscribe
    public void onSetNavalnyEvent(SetNavalnyEvent event) {
        textViewPersonName.setText(getString(R.string.navalny));
        ListAdapter keyWordsAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, keyWordsNavalnyList);
        setListAdapter(keyWordsAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
