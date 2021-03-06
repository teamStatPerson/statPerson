package com.proba.statperson.view.admin.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewPersonsListEvent;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.view.admin.AdminActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import statPerson.element.person.Person;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPersons.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPersons#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPersons extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private IPresenter presenter;

    List<Person> persons;

    public FragmentPersons() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPersons.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPersons newInstance(String param1, String param2) {
        FragmentPersons fragment = new FragmentPersons();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
//                android.R.layout.simple_list_item_1, persons);
//        setListAdapter(adapter);

        view = inflater.inflate(R.layout.fragment_persons, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setListAdapter(getListAdapter());
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
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

        switch (item.getItemId()) {
            case R.id.edit:
                FragmentManager editManager = getFragmentManager();
                EditorDialogFragment editorDialogFragment =
                        EditorDialogFragment.newInstance(getElementID(item.getTitle().toString()),
                        Constants.PERSONS_CATALOG_INDEX, 0);
                editorDialogFragment.show(editManager, "dialog_editor");
                break;
            case R.id.delete:
                DeleteConfirmDialogFragment deleteConfirmDialogFragment =
                        DeleteConfirmDialogFragment.newInstance(getElementID(item.getTitle().toString()),
                        Constants.PERSONS_CATALOG_INDEX, 0);
                FragmentManager deleteManager = getFragmentManager();
                deleteConfirmDialogFragment.show(deleteManager, "dialog_delete");
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private int getElementID(String personName) {
        int ID = 0;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getName().equals(personName)) {
                ID = persons.get(i).getId();
                break;
            }
        }
        return ID;
    }

    private void setOnClickListenerFab() {
        new MaterialDialog.Builder(getActivity()) //https://github.com/afollestad/material-dialogs
                .title(R.string.add)
                .content(R.string.add_person)
                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getActivity(), "Вы подтвердили добавление личности: " + input,
                                Toast.LENGTH_LONG).show();
                        ((AdminActivity) getActivity()).addElement(input, Constants.PERSONS_CATALOG_INDEX, 0);
                    }
                }).show();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String prompt = "Вы выбрали: "
                + getListView().getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), prompt, Toast.LENGTH_LONG).show();
    }

    @Subscribe
    public void displayCatalogElements(NewPersonsListEvent catalogElements) {
        removeProgressBar();

        persons = catalogElements.message;

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, catalogElements.message);
                android.R.layout.simple_list_item_single_choice, getPersonsNamesFromArray(persons));
        setListAdapter(adapter);
    }

    private String[] getPersonsNamesFromArray(List<Person> catalogElements) {
        String[] personsNames = new String[catalogElements.size()];
        for (int i = 0; i < personsNames.length; i++) {
            personsNames[i] = catalogElements.get(i).getName();
        }
        return personsNames;
    }

    @Subscribe
    public void catalogUpdate(EditCatalogElementsEvent catalogElements) {
        setProgressBar();
        Toast.makeText(getActivity(), catalogElements.message, Toast.LENGTH_SHORT).show();
    }

    private void removeProgressBar() {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private void setProgressBar() {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        AdminActivity adminActivity = (AdminActivity) getActivity();
        adminActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListenerFab();
//                presenter = new PresenterImpl();
//                presenter.addElement();
            }
        });
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
