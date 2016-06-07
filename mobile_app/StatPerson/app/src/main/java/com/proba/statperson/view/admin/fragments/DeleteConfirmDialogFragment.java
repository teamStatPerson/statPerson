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

    private DeleteConfirmListener mDeleteConfirmListenerListener;
    Context mContext;
    static String elementName;
    static int catalogIndex;
    private IPresenter presenter;

    public DeleteConfirmDialogFragment() {
        mContext = getActivity();
    }

    public static DeleteConfirmDialogFragment newInstance(MenuItem item, int index, String personName) {
        String title = "";
        elementName = item.getTitle().toString();
        catalogIndex = index;
        DeleteConfirmDialogFragment deleteConfirmDialogFragment = new DeleteConfirmDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
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
                        switch (catalogIndex) {
                            case Constants.PERSONS_CATALOG_INDEX:
//                                Person person = new Person(elementName);
                                presenter.adminDeleteElement(elementName, Constants.PERSONS_CATALOG_INDEX, 0);
                                break;
                            case Constants.SITES_CATALOG_INDEX:
//                                Site site = new Site(elementName, null);
                                presenter.adminDeleteElement(elementName, Constants.SITES_CATALOG_INDEX, 0);
                                break;
                            case Constants.KEYWORDS_CATALOG_INDEX:
//                                Keyword keyword = new Keyword(elementName, 0);
                                presenter.adminDeleteElement(elementName, Constants.KEYWORDS_CATALOG_INDEX, 0);
                                break;
                        }
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
