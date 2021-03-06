package com.proba.statperson.view.admin.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.proba.statperson.events.NewSitesListEvent;
import com.proba.statperson.view.admin.AdminActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import statPerson.element.site.Site;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSites.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSites extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    private OnFragmentInteractionListener mListener;
    public FloatingActionButton fab;

    List<Site> sites;

    public FragmentSites() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSites.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSites newInstance(String param1, String param2) {
        FragmentSites fragment = new FragmentSites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
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
                        Constants.SITES_CATALOG_INDEX, 0);
                editorDialogFragment.show(editManager, "dialog_editor");
                break;
            case R.id.delete:
                DeleteConfirmDialogFragment deleteConfirmDialogFragment =
                        DeleteConfirmDialogFragment.newInstance(getElementID(item.getTitle().toString()),
                        Constants.SITES_CATALOG_INDEX, 0);
                FragmentManager deleteManager = getFragmentManager();
                deleteConfirmDialogFragment.show(deleteManager, "dialog_delete");
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private int getElementID(String sitesName) {
        int ID = 0;
        for (int i = 0; i < sites.size(); i++) {
            if (sites.get(i).getName().equals(sitesName)) {
                ID = sites.get(i).getId();
                break;
            }
        }
        return ID;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String prompt = "Вы выбрали: "
                + getListView().getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), prompt, Toast.LENGTH_LONG).show();
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

//        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, sites);
//        setListAdapter(adapter);

        view = inflater.inflate(R.layout.fragment_sites, null);
        return view;
    }

    @Subscribe
    public void displayCatalogElements(NewSitesListEvent catalogElements) {
        removeProgressBar();
        // TODO: 03.06.2016 handle exceptions

        sites = catalogElements.message;

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, catalogElements.message);
                android.R.layout.simple_list_item_single_choice, getSitesNamesFromArray(sites));
        setListAdapter(adapter);
    }

    private String[] getSitesNamesFromArray(List<Site> catalogElements) {
            String[] sitesNames = new String[catalogElements.size()];
            for (int i = 0; i < sitesNames.length; i++) {
                sitesNames[i] = catalogElements.get(i).getName();
            }
            return sitesNames;
        }


        @Subscribe
    public void catalogUpdate(EditCatalogElementsEvent catalogElements) {
        setProgressBar();
        Toast.makeText(getActivity(), catalogElements.message, Toast.LENGTH_SHORT).show();
    }

    private void setProgressBar() {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void removeProgressBar() {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
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

            }
        });
    }

    private void setOnClickListenerFab() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.add)
                .content(R.string.add_site)
                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getActivity(), "Вы подтвердили добавление сайта: " + input,
                                Toast.LENGTH_LONG).show();
                        ((AdminActivity) getActivity()).addElement(input, Constants.SITES_CATALOG_INDEX, 0);
                    }
                }).show();
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
