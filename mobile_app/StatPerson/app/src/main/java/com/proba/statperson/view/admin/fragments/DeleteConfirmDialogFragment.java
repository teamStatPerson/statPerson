package com.proba.statperson.view.admin.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.proba.statperson.R;
import com.proba.statperson.interfaces.DeleteConfirmListener;

/**
 * Created by Konstantin on 05.06.2016.
 */
public class DeleteConfirmDialogFragment extends DialogFragment {

    private DeleteConfirmListener mDeleteConfirmListenerListener;
    Context mContext;

    public DeleteConfirmDialogFragment() {
        mContext = getActivity();
    }

    public static DeleteConfirmDialogFragment newInstance() {
        String title = "";

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
