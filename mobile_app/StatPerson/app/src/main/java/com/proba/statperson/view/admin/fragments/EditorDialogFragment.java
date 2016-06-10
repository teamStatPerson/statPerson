package com.proba.statperson.view.admin.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.proba.statperson.R;
import com.proba.statperson.interfaces.EditorDialogListener;

/**
 * Created by Konstantin on 05.06.2016.
 */
public class EditorDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {
    private EditText mEditText;
    public static int oldElementID;
    public static int catalogIndex;
    public static int personID;

    public static final String CATALOG_INDEX = "catalogIndex";
    public static final String ELEMENT_NAME = "elementName";
    public static final String PERSON_NAME = "personID";

    public EditorDialogFragment() {
        // Пустой конструктор должен быть прописан для DialogFragment
    }

    public static EditorDialogFragment newInstance(int elementID, int index, int ID) {
        String title = "";
        EditorDialogFragment editorDialogFragment = new EditorDialogFragment();
        oldElementID = elementID;
        catalogIndex = index;
        personID = ID;
//        Bundle args = new Bundle();
////        args.putString(TITLE, title);
//        args.putString(ELEMENT_NAME, elementName);
//        args.putInt(CATALOG_INDEX, index);
//        args.putString(PERSON_NAME, personID);
//        editorDialogFragment.setArguments(args);
        return editorDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        savedInstanceState.getString("name");
//        Toast.makeText(getActivity(), savedInstanceState.getString("name"), Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.dialog_fragment_editor, container);
        mEditText = (EditText) view.findViewById(R.id.editTextName);
        getDialog().setTitle(R.string.type);
        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditorDialogListener activity = (EditorDialogListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString(), oldElementID, catalogIndex, personID);
            this.dismiss();
            return true;
        }
        return false;
    }
}
