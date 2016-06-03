package com.proba.statperson.view.admin.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.proba.statperson.R;

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

    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    final String[] keyWordsPutin = new String[]{"Путиным", "Путину", "Путина", "Путине"};
    final String[] keyWordsMedvedev = new String[]{"Медведевым", "Медведеву", "Медведева", "Медведеве"};
    final String[] keyWordsNavalny = new String[]{"Навальным", "Навальному", "Навального", "Навальном"};


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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, keyWordsPutin);
//        setListAdapter(adapter);

        view = inflater.inflate(R.layout.fragment_key_words, null);

        view.findViewById(R.id.textViewPerson).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.textViewPersonName).setVisibility(View.INVISIBLE);

        setOnClickListenerOnPersonsPopup(view);

        return view;
    }

    private void setOnClickListenerOnPersonsPopup(View view) {
        TextView tvChoosePerson = (TextView) view.findViewById(R.id.textViewPerson);
        TextView tvChoosePerson2 = (TextView) view.findViewById(R.id.textViewPersonName);

        tvChoosePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuPersons(v);
            }
        });

        tvChoosePerson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenuPersons(v);
            }
        });
    }

    private void showPopupMenuPersons(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.inflate(R.menu.popupmenu_persons);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.putin:
                                Toast.makeText(getActivity(),
                                        "Вы выбрали Путин",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.medvedev:
                                Toast.makeText(getActivity(),
                                        "Вы выбрали Медведев",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navalny:
                                Toast.makeText(getActivity(),
                                        "Вы выбрали Навальный",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getActivity(), "onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
