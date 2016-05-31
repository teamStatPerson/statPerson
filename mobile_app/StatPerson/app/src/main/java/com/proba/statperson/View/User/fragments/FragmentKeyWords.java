package com.proba.statperson.view.user.fragments;

import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.R;
import com.proba.statperson.events.PersonEvent;
import com.proba.statperson.interfaces.SetPersonName;
import com.proba.statperson.utils.EventBus;

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

    private OnFragmentInteractionListener mListener;

    private SetPersonName mSetPersonName;

    static TextView textViewPersonName;

    static String[] keyWordsPerson = new String[]{"", "", "", ""};
    static String[] keyWordsPutin = new String[]{"Путиным", "Путину", "Путина", "Путине"};
    static String[] keyWordsMedvedev = new String[]{"Медведевым", "Медведеву", "Медведева", "Медведеве"};
    static String[] keyWordsNavalny = new String[]{"Навальным", "Навальному", "Навального", "Навальном"};


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, keyWordsPerson);
        setListAdapter(adapter);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public String[] onPersonEvent(PersonEvent event) {
        if (event.person.equals("putin")) {
            keyWordsPerson = keyWordsPutin;
            mSetPersonName.showPersonName(getString(R.string.putin));
            Toast.makeText(getActivity(), "Putin", Toast.LENGTH_LONG).show();
        } else if (event.person.equals("medvedev")) {
            keyWordsPerson = keyWordsMedvedev;
            mSetPersonName.showPersonName(getString(R.string.medvedev));
        } else if (event.person.equals("navalny")) {
            keyWordsPerson = keyWordsNavalny;
            mSetPersonName.showPersonName(getString(R.string.navalny));
        } else {
            return keyWordsPerson;
        }
        return keyWordsPerson;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_key_words, container, false);
        textViewPersonName = (TextView) rootView.findViewById(R.id.textViewPersonName);
        return rootView;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSetPersonName = (SetPersonName)getActivity();
    }

    public static void changeText(String data) {
        textViewPersonName.setText(data);
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

    @Override
    public void onStart() {
        super.onStart();
        // регистрация приемника при старте фрагмента
        EventBus.getInstance().register(this);
    }

    @Override
    public void onStop() {
        // отписываемся от регистрации при закрытии фрагмента
        EventBus.getInstance().unregister(this);
        super.onStop();
    }
}
