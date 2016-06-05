package com.proba.statperson.interfaces;

import android.app.DialogFragment;

/**
 * Created by Konstantin on 05.06.2016.
 */
public interface DeleteConfirmListener {
    public void onDialogPositiveClick(DialogFragment dialog);
    public void onDialogNegativeClick(DialogFragment dialog);
}