package com.proba.statperson.view.admin.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.InputType;
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
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.events.EditCatalogElementsEvent;
import com.proba.statperson.events.NewCatalogElementsListEvent;
import com.proba.statperson.events.PersonKeywordsListEvent;
import com.proba.statperson.view.admin.AdminActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private String chosenPerson;
    TextView tvChoosePerson2;

//    final String[] keyWordsPutin = new String[]{"Путиным", "Путину", "Путина", "Путине"};
//    final String[] keyWordsMedvedev = new String[]{"Медведевым", "Медведеву", "Медведева", "Медведеве"};
//    final String[] keyWordsNavalny = new String[]{"Навальным", "Навальному", "Навального", "Навальном"};

    private String[] persons;

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

        view = inflater.inflate(R.layout.fragment_key_words, null);

        view.findViewById(R.id.textViewPerson).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.textViewPersonName).setVisibility(View.INVISIBLE);

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

        switch (item.getItemId())
        {
            case R.id.edit:
                FragmentManager editManager = getFragmentManager();
                EditorDialogFragment editorDialogFragment = EditorDialogFragment.newInstance(item.getTitle().toString(),
                        Constants.KEYWORDS_CATALOG_INDEX, chosenPerson);
                editorDialogFragment.show(editManager, "dialog_editor");
                break;
            case R.id.delete:
                DeleteConfirmDialogFragment deleteConfirmDialogFragment = DeleteConfirmDialogFragment.newInstance(item,
                        Constants.KEYWORDS_CATALOG_INDEX, chosenPerson);
                FragmentManager deleteManager = getFragmentManager();
                deleteConfirmDialogFragment.show(deleteManager, "dialog_delete");
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
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

    @Subscribe
    public void displayCatalogElements(NewCatalogElementsListEvent catalogElements) {
        removeProgressBar();

        persons = catalogElements.message;

        view.findViewById(R.id.textViewPerson).setVisibility(View.VISIBLE);
        view.findViewById(R.id.textViewPersonName).setVisibility(View.VISIBLE);

        setOnClickListenerOnPersonsPopup(view);
    }

    @Subscribe
    public void displayPersonsKeywords(PersonKeywordsListEvent catalogElements) {
        removeProgressBar();

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, catalogElements.message);
                android.R.layout.simple_list_item_multiple_choice, catalogElements.message);
        setListAdapter(adapter);
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

    private void setOnClickListenerOnPersonsPopup(View view) {
        TextView tvChoosePerson = (TextView) view.findViewById(R.id.textViewPerson);
        tvChoosePerson2 = (TextView) view.findViewById(R.id.textViewPersonName);

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

    public void showPopupMenuPersons(View v) {
        PopupMenu popupMenu = populatePopupMenu(new PopupMenu(getActivity(), v));

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        setProgressBar();
//                        Person person = new Person(item.getTitle().toString());
//                        Toast.makeText(getActivity(), person.getName(), Toast.LENGTH_SHORT).show();
                        chosenPerson = item.getTitle().toString();
                        ((AdminActivity) getActivity()).
                                getCatalogElements(Constants.KEYWORDS_CATALOG_INDEX, chosenPerson);
                        tvChoosePerson2.setText(chosenPerson);
                        return false;
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getActivity(), "onDismiss",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    private PopupMenu populatePopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.popupmenu_persons);
        popupMenu.getMenu().clear();

        for (String person : persons) {
            popupMenu.getMenu().add(person);
        }

        return popupMenu;
    }

    private void setOnClickListenerFab() {
        new MaterialDialog.Builder(getActivity()) //https://github.com/afollestad/material-dialogs
                .title(R.string.add)
                .content(R.string.add_key_word)
                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        Toast.makeText(getActivity(), "Вы подтвердили добавление ключевого слова: " + input,
                                Toast.LENGTH_LONG).show();
                        // TODO: 08.06.2016 handle person ids
                        ((AdminActivity) getActivity()).addElement(input, Constants.KEYWORDS_CATALOG_INDEX, 0);
                    }
                }).show();
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
                Snackbar.make(view, "KeyWordsFragment", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
