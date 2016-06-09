package com.proba.statperson.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.proba.statperson.R;
import com.proba.statperson.view.admin.AdminActivity;
import com.proba.statperson.view.user.UserActivity;


/**
 * Created by vadik on 28.05.2016.
 */
public class MainActivity extends AppCompatActivity {
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup radioGroup;
        radioGroup = (RadioGroup) findViewById(R.id.rg_choose_role);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                startUserOrAdminActivity(checkedRadioButtonId);
            }
        });
    }

    private void startUserOrAdminActivity(int checkedRadioButtonId) {
        switch (checkedRadioButtonId) {
            case R.id.rb_admin:
                new MaterialDialog.Builder(this)
                        .title(R.string.login)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .customView(R.layout.admin_login, true)
                        .positiveText(R.string.enter)
                        .negativeText(R.string.cancel)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                View dialogView = dialog.getView();
                                String admin_email = ((EditText) dialogView.findViewById(R.id.admin_email)).getText().toString();
                                String admin_pass = ((EditText) dialogView.findViewById(R.id.admin_pass)).getText().toString();
//                                Toast.makeText(getApplicationContext(), admin_email + admin_pass, Toast.LENGTH_LONG).show();
                                Intent intentAdmin = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intentAdmin);
                            }
                        })
                        .show();
                break;
            case R.id.rb_user:
                Intent intentUser = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intentUser);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
