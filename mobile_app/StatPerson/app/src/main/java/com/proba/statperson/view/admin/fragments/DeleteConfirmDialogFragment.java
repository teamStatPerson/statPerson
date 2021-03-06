package com.proba.statperson.view.admin.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.proba.statperson.Constants;
import com.proba.statperson.R;
import com.proba.statperson.interfaces.DeleteConfirmListener;
import com.proba.statperson.interfaces.IPresenter;
import com.proba.statperson.presenter.PresenterImpl;

import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by Konstantin on 05.06.2016.
 */
public class DeleteConfirmDialogFragment extends DialogFragment {

    public static final String CATALOG_INDEX = "catalogIndex";
    public static final String ELEMENT_ID = "elementID";
    public static final String PERSON_ID = "personID";
    public static final String TITLE = "title";
    private DeleteConfirmListener mDeleteConfirmListenerListener;
    Context mContext;
    static String elementName;
    static int catalogIndex;
    private IPresenter presenter;

    public DeleteConfirmDialogFragment() {
        mContext = getActivity();
    }

    public static DeleteConfirmDialogFragment newInstance(int elementID, int index, int personID) {
        String title = "";
        DeleteConfirmDialogFragment deleteConfirmDialogFragment = new DeleteConfirmDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(ELEMENT_ID, elementID);
        args.putInt(CATALOG_INDEX, index);
        args.putInt(PERSON_ID, personID);
        deleteConfirmDialogFragment.setArguments(args);
        return deleteConfirmDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.sure)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter = new PresenterImpl();
                        mDeleteConfirmListenerListener.onDialogPositiveClick(DeleteConfirmDialogFragment.this);

                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mDeleteConfirmListenerListener
                                .onDialogNegativeClick(DeleteConfirmDialogFragment.this);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mDeleteConfirmListenerListener = (DeleteConfirmListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
